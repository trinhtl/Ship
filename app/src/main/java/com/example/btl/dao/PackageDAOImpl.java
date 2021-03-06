package com.example.btl.dao;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import com.example.btl.fragments.PackageFilterFragment;
import com.example.btl.fragments.ShipperCurrentPackageFragment;
import com.example.btl.fragments.ShopCurrentPackageFragment;
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
    SharedPreferences sharedPreferences;
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
                                                o.optString("sendAddress"),
                                                o.optString("recieveAddress"),
                                                o.optString("shipCost"),
                                                o.optString("advanceMoney"),
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
    public void delete(int id) {
        socket.connect();
        socket.emit("package/delete", id);
    }

    @Override
    public List<Package> getByIdShipper(final ShipperCurrentPackageFragment pk, int idShipper) {
        final List<Package> packageList = new ArrayList<>();
        socket.connect();
        socket.emit("package/getByIdShipper", idShipper);
        socket.on("package/getByIdShipper", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        JSONArray data = (JSONArray) args[0];
                        for (int i = 0; i < data.length(); i++){
                            try {
                                JSONObject o = data.getJSONObject(i);
                                Package newPackage = new Package(o.optInt("id"),
                                        o.optInt("idOwner"),
                                        o.optString("sendAddress"),
                                        o.optString("recieveAddress"),
                                        o.optString("shipCost"),
                                        o.optString("advanceMoney"),
                                        o.optString("description")
                                );
                                JSONObject u = (JSONObject) o.opt("user");
                                User owner = new User(u.optString("name"), u.optString("phone"));
                                newPackage.setOwner(owner);
                                packageList.add(newPackage);
                                pk.activePackageAdapter.notifyDataSetChanged();
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
    public List<Package> getByIdShop(final ShopCurrentPackageFragment pk, int idShop) {
        final List<Package> packageList = new ArrayList<>();
        socket.connect();
        socket.emit("package/getByIdShop", idShop);
        socket.on("package/getByIdShop", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        JSONArray data = (JSONArray) args[0];
                        for (int i = 0; i < data.length(); i++){
                            try {
                                JSONObject o = data.getJSONObject(i);
                                Package newPackage = new Package(o.optInt("id"),
                                        o.optInt("idOwner"),
                                        o.optString("sendAddress"),
                                        o.optString("recieveAddress"),
                                        o.optString("shipCost"),
                                        o.optString("advanceMoney"),
                                        o.optString("description")
                                );
                                JSONObject u = (JSONObject) o.opt("user");
                                if (u == null) {
                                    packageList.add(newPackage);
                                    return;
                                }
                                User shipper = new User(u.optString("name"), u.optString("phone"));
                                newPackage.setShipper(shipper);
                                packageList.add(newPackage);
                                pk.activePackageAdapter.notifyDataSetChanged();
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
    public List<Package> getAll(final PackageFilterFragment pk) {
        final List<Package> packageList = new ArrayList<>();
        socket.connect();
        socket.emit("package/list");
        socket.on("package/list", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONArray data = (JSONArray) args[0];
                for (int i = 0; i < data.length(); i++){
                    try {
                        JSONObject o = data.getJSONObject(i);
                        Package newPackage = new Package(o.optInt("id"),
                                o.optInt("idOwner"),
                                o.optString("sendAddress"),
                                o.optString("recieveAddress"),
                                o.optString("shipCost"),
                                o.optString("advanceMoney"),
                                o.optString("description")
                        );
                        pk.packageList.add(newPackage);
                        System.out.println("===================" + newPackage);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return packageList;
    }
}
