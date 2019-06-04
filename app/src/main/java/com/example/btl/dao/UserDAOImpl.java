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
        final List<User> userList = new ArrayList<User>();
        System.out.println("get All");
        socket.connect();
        socket.emit("user/list");
        socket.on("user/list", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONArray data = (JSONArray) args[0];
                for (int i = 0; i < data.length(); i++){
                    try {
                        JSONObject o = data.getJSONObject(i);
                        User newUser = new User(
                                o.optString("phone"),
                                o.optString("name"),
                                o.optString("email"),
                                o.optString("avatar")
                        );
                        userList.add(newUser);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return userList;
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

    @Override
    public User validate(String phone, String password) {
        socket.connect();
        socket.emit("user/validate", phone, password);
        final User[] userLogin = new User[1];
        System.out.println("One");
        socket.on("user/validate", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                System.out.println("go");
                System.out.println("Running....");
                JSONObject data = (JSONObject) args[0];
                userLogin[0] = new User(data.optInt("id"),
                        data.optString("name"),
                        data.optString("phone"),
                        data.optString("password"),
                        data.optString("avatar"));
            }
        });
        return userLogin[0];
    }
}
