package com.example.btl.dao;

import java.util.List;

public interface ShipDAO {
    List<Ship> getAll();
    User getById(int _id);
    void insert(Ship ship);
    void update(int idPackage, int idShipper, String status);
    void delete(Ship ship);
    void cancel(int idPackage);
    void recieve(int idPakage, int idShipper);
    void shipped(int idPackage, String shippedAt);
}
