package com.example.btl.dao;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.btl.funtional.MySocket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class PackageDAOImpl implements PackageDAO {
    private Socket socket;
    final List<Package> packageList = new ArrayList<>();
    public PackageDAOImpl(){
        this.socket = MySocket.getInstance().socket;
    }
    @Override
    public List<Package> getAll() {
        final List<Package> packageList = new ArrayList<>();
        socket.connect();
        socket.emit("package/list");
        socket.on("package/list", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        JSONArray data = (JSONArray) args[0];
                        for (int i = 0; i < data.length(); i++){
                            try {
                                JSONObject o = data.getJSONObject(i);
                                packageList.add(
                                        new Package(o.optInt("id"),
                                                o.optInt("idOwner"),
                                                o.optString("shipCost"),
                                                o.optString("advanceMoney"),
                                                o.optString("sendAddress"),
                                                o.optString("recieveAddress"),
                                                o.optString("description")
                                        )
                                );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

            }
        });

        return packageList;
    }

    @Override
    public Package getById(int _id) {
        return null;
    }

    @Override
    public void insert(Package p) {
        socket.connect();
        socket.emit("package/add", p.getIdOwner(), p.getShipCost(), p.getAdvanceMoney(), p.getSendAddress(), p.getRecieveAddress(), p.getDescription());
    }

    @Override
    public void update(Package _package) {

    }

    @Override
    public void delete(Package _package) {

    }
    private class packageAsync extends AsyncTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            socket.connect();
            System.out.println("Zô");
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            System.out.println("Zô");
            final Object[] o = new Object[1];
            socket.emit("package/list");
            socket.on("package/list", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                   o[0] = args;
                    System.out.println(o);
                }
            });
            return o;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            JSONArray data = (JSONArray) o;
            for (int i = 0; i < data.length(); i++){
                try {
                    JSONObject r = data.getJSONObject(i);
                    packageList.add(
                            new Package(r.optInt("id"),
                                    r.optInt("idOwner"),
                                    r.optString("shipCost"),
                                    r.optString("advanceMoney"),
                                    r.optString("sendAddress"),
                                    r.optString("recieveAddress"),
                                    r.optString("description")
                            )
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
