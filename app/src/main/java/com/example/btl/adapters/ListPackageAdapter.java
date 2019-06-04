package com.example.btl.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl.R;
import com.example.btl.dao.Item;

import java.util.List;

public class ListPackageAdapter extends BaseAdapter {
    private List<Item> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    SharedPreferences sharedpreferences;
    public ListPackageAdapter(List<Item> listData, Context context) {
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
            convertView = layoutInflater.inflate(R.layout.item, null);
            holder = new ViewHolder();
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.nickname = (TextView) convertView.findViewById(R.id.nickname);
            holder.advanceMoney = (TextView) convertView.findViewById(R.id.advanceMoney);
            holder.shipCost = (TextView) convertView.findViewById(R.id.shipCost);
            holder.sendAddress = (TextView) convertView.findViewById(R.id.sendAddress);
            holder.recieveAddress = (TextView) convertView.findViewById(R.id.recieveAddress);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.idPackage = (TextView) convertView.findViewById(R.id.idPackage);
            holder.createTime = (TextView) convertView.findViewById(R.id.createTime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Item item = this.listData.get(position);
        int imageId = this.getMimapResIdByName("a");
        holder.avatar.setImageResource(imageId);

        holder.nickname.setText(item.getNickname());
        holder.advanceMoney.setText("Tiền ứng: " + item.getAdvanceMoney());
        holder.shipCost.setText("Phí ship: " + item.getShipCost());
        holder.sendAddress.setText("Từ: " + item.getSendAddress());
        holder.recieveAddress.setText("Đến: " + item.getRecieveAddress());
        holder.description.setText(item.getDescription());
        holder.idPackage.setText("Số đơn: " + String.valueOf(item.getId()));
        holder.createTime.setText(String.valueOf(item.getCreateTime()));
        return convertView;
    }
    private int getMimapResIdByName(String resName){
        String pkgName = context.getPackageName();

        int resID = context.getResources().getIdentifier(resName, "mipmap", pkgName);
        Log.i("List", "Package Name: "+ pkgName +"Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    static class ViewHolder{
        ImageView avatar;
        TextView nickname;
        TextView advanceMoney;
        TextView shipCost;
        TextView sendAddress;
        TextView recieveAddress;
        TextView description;
        TextView idPackage;
        TextView createTime;
    }
}
