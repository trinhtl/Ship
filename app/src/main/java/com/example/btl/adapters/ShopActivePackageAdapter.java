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
import com.example.btl.ShopNavigatorMenu;
import com.example.btl.dao.Package;
import com.example.btl.dao.PackageDAOImpl;

import java.util.List;

import static com.example.btl.MainActivity.MyPREFERENCES;

//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.RecyclerView.ViewHolder;

public class ShopActivePackageAdapter extends BaseAdapter {
    private List<Package> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    SharedPreferences sharedpreferences;
    public ShopActivePackageAdapter(List<Package> listData, Context context) {
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
        ViewHolder holder;
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.shop_active_package, null);
            holder = new ViewHolder();
            holder.sendAddress = (TextView) convertView.findViewById(R.id.begin);
            holder.recieveAddress = (TextView) convertView.findViewById(R.id.destination);
            holder.advanceMoney = (TextView) convertView.findViewById(R.id.Money);
            holder.shipCost = (TextView) convertView.findViewById(R.id.ShipMoney);
            holder.sendUser = (TextView) convertView.findViewById(R.id.sendUser);
            holder.recieveUser = (TextView) convertView.findViewById(R.id.receiveUser);
            holder.cancel = convertView.findViewById(R.id.cancelActivePackage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Package activePackage = this.listData.get(position);

        holder.sendAddress.setText(activePackage.getSendAddress());
        holder.recieveAddress.setText(activePackage.getRecieveAddress());
        holder.advanceMoney.setText(activePackage.getAdvanceMoney());
        holder.shipCost.setText(activePackage.getShipCost());
        holder.sendUser.setText(sharedpreferences.getString("name", "")  + " sdt: " + sharedpreferences.getString("phone", ""));
        holder.recieveUser.setText(activePackage.getShipper().getName() + " sdt: " + activePackage.getShipper().getPhone());
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageDAOImpl packageDAO = new PackageDAOImpl();
                packageDAO.delete(activePackage.getId());
                try {
                    Thread.currentThread().join(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent shop = new Intent(v.getContext(), ShopNavigatorMenu.class);
                v.getContext().startActivity(shop);
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
        Button cancel;
    }
}
