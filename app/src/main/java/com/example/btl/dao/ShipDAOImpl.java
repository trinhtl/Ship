package com.example.btl.dao;

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

public class ShipDAOImpl implements ShipDAO {
    private Socket socket;
    public ShipDAOImpl(){
        this.socket = MySocket.getInstance().socket;
    }
    @Override
    public List<Ship> getAll() {
        final List<Ship> shipList = new ArrayList<>();
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
                                shipList.add(
                                        new Ship(o.optInt("id"),
                                                o.optInt("idPackage"),
                                                o.optInt("idOwner"),
                                                o.optString("status"),
                                                o.optString("shippedAt")
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

        return shipList;
    }

    @Override
    public User getById(int _id) {
        return null;
    }

    @Override
    public void insert(Ship ship) {
        socket.connect();
        socket.emit("ship/add", ship.getIdPackage(), ship.getIdShipper(), ship.getStatus(), ship.getShippedAt());
    }

    @Override
    public void update(int idPackage, int idShipper, String status) {
        socket.connect();
        socket.emit("ship/update/shipper/status", idPackage, idShipper, status);
    }

    @Override
    public void delete(Ship ship) {

    }

    @Override
    public void cancel(int idPackage) {
        socket.connect();
        socket.emit("ship/shipper/cancel", idPackage);
    }

    @Override
    public void recieve(int idPakage, int idShipper) {
        socket.connect();
        socket.emit("ship/recieve", idPakage, idShipper);
    }

    @Override
    public void shipped(int idPackage, String shippedAt) {
        socket.connect();
        socket.emit("ship/shipped", idPackage, shippedAt);
    }

}
