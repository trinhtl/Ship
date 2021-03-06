package com.example.btl.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.btl.R;
import com.example.btl.ShipperNavigatorMenu;
import com.example.btl.dao.Package;
import com.example.btl.dao.ShipDAOImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.btl.MainActivity.MyPREFERENCES;

//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.RecyclerView.ViewHolder;

public class ActivePackageAdapter extends BaseAdapter {
    private List<Package> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    SharedPreferences sharedpreferences;
    public ActivePackageAdapter(List<Package> listData, Context context) {
        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.active_package, null);
            holder = new ViewHolder();
            holder.sendAddress = (TextView) convertView.findViewById(R.id.begin);
            holder.recieveAddress = (TextView) convertView.findViewById(R.id.destination);
            holder.advanceMoney = (TextView) convertView.findViewById(R.id.Money);
            holder.shipCost = (TextView) convertView.findViewById(R.id.ShipMoney);
            holder.sendUser = (TextView) convertView.findViewById(R.id.sendUser);
            holder.recieveUser = (TextView) convertView.findViewById(R.id.receiveUser);
            holder.shippedButton = convertView.findViewById(R.id.shippedButton);
            holder.cancelActiveButton = convertView.findViewById(R.id.cancelActivePackage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Package activePackage = this.listData.get(position);

        holder.sendAddress.setText(activePackage.getSendAddress());
        holder.recieveAddress.setText(activePackage.getRecieveAddress());
        holder.advanceMoney.setText(activePackage.getAdvanceMoney());
        holder.shipCost.setText(activePackage.getShipCost());
        holder.sendUser.setText(activePackage.getOwner().getName() + " sdt: " + activePackage.getOwner().getPhone());
        holder.recieveUser.setText(sharedpreferences.getString("name", "shipper") + " sđt: " + sharedpreferences.getString("phone", ""));
        holder.cancelActiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShipDAOImpl shipDAO = new ShipDAOImpl();
                shipDAO.cancel(activePackage.getId());
                try {
                    Thread.currentThread().join(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent shipperActivePackage = new Intent(v.getContext(), ShipperNavigatorMenu.class);
                v.getContext().startActivity(shipperActivePackage);
            }
        });
        holder.shippedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShipDAOImpl shipDAO = new ShipDAOImpl();
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);
                shipDAO.shipped(activePackage.getId(), formattedDate);
                try {
                    Thread.currentThread().join(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent shipperActivePackage = new Intent(v.getContext(), ShipperNavigatorMenu.class);
                v.getContext().startActivity(shipperActivePackage);
            }
        });
        return convertView;
    }
    static class ViewHolder{
        TextView sendAddress;
        TextView recieveAddress;
        TextView advanceMoney;
        TextView shipCost;
        TextView sendUser;
        TextView recieveUser;
        Button shippedButton;
        Button cancelActiveButton;
    }
}
