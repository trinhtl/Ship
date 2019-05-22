package com.example.btl.dao;

import com.example.btl.funtional.MySocket;

import java.util.List;

import io.socket.client.Socket;

public class PackageDAOImpl implements PackageDAO {
    private Socket socket;
    public PackageDAOImpl(){
        this.socket = MySocket.getInstance().socket;
    }
    @Override
    public List<User> getAll() {

        return null;
    }

    @Override
    public User getById(int _id) {
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
}
