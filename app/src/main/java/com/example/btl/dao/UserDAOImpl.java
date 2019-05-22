package com.example.btl.dao;

import com.example.btl.funtional.MySocket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class UserDAOImpl implements UserDAO {
    private Socket socket;
    public UserDAOImpl(){
        this.socket = MySocket.getInstance().socket;

    }
    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<User>();
        System.out.println("get All");
        socket.connect();
        socket.emit("user/list");
        socket.on("user/list", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
//                String data = args[0].toString();
                // get the extra data from the fired event and display a toast
//                        Toast.makeText(ChatBoxActivity.this,data,Toast.LENGTH_SHORT).show();
//                System.out.println(data);
                JSONArray data = (JSONArray) args[0];
                for (int i = 0; i < data.length(); i++){
                    try {
                        JSONObject o = data.getJSONObject(i);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return null;
    }

    @Override
    public User getById(int _id) {
        return null;
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
