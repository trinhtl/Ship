package com.example.btl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btl.R;
import com.example.btl.dao.Package;

import java.util.List;

//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.RecyclerView.ViewHolder;

public class ActivePackageAdapter extends BaseAdapter {
    private List<Package> listData;
    private LayoutInflater layoutInflater;
    private Context context;

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
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Package activePackage = this.listData.get(position);

        holder.sendAddress.setText(activePackage.getSendAddress());
        holder.recieveAddress.setText(activePackage.getRecieveAddress());
        holder.advanceMoney.setText(activePackage.getAdvanceMoney());
        holder.shipCost.setText(activePackage.getShipCost());
        holder.sendUser.setText("send user");
        holder.recieveUser.setText("recieveUser");
        return convertView;
    }
    static class ViewHolder{
        TextView sendAddress;
        TextView recieveAddress;
        TextView advanceMoney;
        TextView shipCost;
        TextView sendUser;
        TextView recieveUser;
    }
}
