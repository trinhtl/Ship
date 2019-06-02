package com.example.btl.dao;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.btl.fragments.PackageListFragment;
import com.example.btl.funtional.MySocket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ItemDAOImpl extends Activity implements ItemDAO {
    private Socket socket;
    public ItemDAOImpl(){
        this.socket = MySocket.getInstance().socket;
    }
    final List<Item> itemList = new ArrayList<>();

    @Override
    public List<Item> getAll(final PackageListFragment pk) {
        System.out.println("Step into ");
        socket.connect();
        socket.emit("item/list");
        socket.on("item/list", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        System.out.println("Running....");
                        JSONArray data = (JSONArray) args[0];
                        for (int i = 0; i < data.length(); i++){
                            try {
                                JSONObject user = data.getJSONObject(i);
                                System.out.println("user: " + user.toString());
                                JSONArray userPackages = (JSONArray) user.opt("packages");
                                System.out.println("userPackages: " + userPackages);
                                for(int j = 0; j < userPackages.length(); j++){
                                    JSONObject userPackage = (JSONObject) userPackages.getJSONObject(j);
                                    System.out.println("userPackage: " + userPackage);
                                    Item newItem = new Item(
                                            userPackage.optInt("id"),
                                            user.optString("avatar"),
                                            user.optString("name"),
                                            userPackage.optString("shipCost"),
                                            userPackage.optString("advanceMoney"),
                                            userPackage.optString("createdAt"),
                                            userPackage.optString("sendAddress"),
                                            userPackage.optString("recieveAddress"),
                                            userPackage.optString("description")
                                    );
                                    System.out.println("item: " + newItem.toString());
                                    itemList.add(newItem);
                                    pk.listPackageAdapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

            }
        });

        return itemList;
    }

    @Override
    public User getById(int _id) {
        return null;
    }

    @Override
    public void insert(Item item) {

    }

    @Override
    public void update(Item item) {

    }

    @Override
    public void delete(Item item) {

    }
    private class ItemAsync extends AsyncTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            socket.connect();
            System.out.println("Pre");
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            System.out.println("Doing");
            socket.emit("item/list");
            final Object[] o = new Object[1];
            socket.on("item/list", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    JSONArray data = (JSONArray) args[0];

                    for (int i = 0; i < data.length(); i++){
                        try {
                            JSONObject user = data.getJSONObject(i);
                            System.out.println("user: " + user.toString());
                            JSONArray userPackages = (JSONArray) user.opt("packages");
                            System.out.println("userPackages: " + userPackages);
                            for(int j = 0; j < userPackages.length(); j++){
                                JSONObject userPackage = (JSONObject) userPackages.getJSONObject(j);
                                System.out.println("userPackage: " + userPackage);
                                Item newItem = new Item(
                                        userPackage.optInt("id"),
                                        user.optString("avatar"),
                                        user.optString("name"),
                                        userPackage.optString("shipCost"),
                                        userPackage.optString("advanceMoney"),
                                        userPackage.optString("createdAt"),
                                        userPackage.optString("sendAddress"),
                                        userPackage.optString("recieveAddress"),
                                        userPackage.optString("description")
                                );
                                System.out.println("item: " + newItem.toString());
                                itemList.add(newItem);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            return o;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (o == null) return;
            System.out.println("Done");
            JSONArray data = null;
            try {
                data = (JSONArray)new JSONTokener(o.toString()).nextValue();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < data.length(); i++){
                try {
                    JSONObject user = data.getJSONObject(i);
                    System.out.println("user: " + user.toString());
                    JSONArray userPackages = (JSONArray) user.opt("packages");
                    System.out.println("userPackages: " + userPackages);
                    for(int j = 0; j < userPackages.length(); j++){
                        JSONObject userPackage = (JSONObject) userPackages.getJSONObject(j);
                        System.out.println("userPackage: " + userPackage);
                        Item newItem = new Item(
                                userPackage.optInt("id"),
                                user.optString("avatar"),
                                user.optString("name"),
                                userPackage.optString("shipCost"),
                                userPackage.optString("advanceMoney"),
                                userPackage.optString("createdAt"),
                                userPackage.optString("sendAddress"),
                                userPackage.optString("recieveAddress"),
                                userPackage.optString("description")
                        );
                        System.out.println("item: " + newItem.toString());
                        itemList.add(newItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
