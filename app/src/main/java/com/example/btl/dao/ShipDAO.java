package com.example.btl.dao;

import java.util.List;

public interface ShipDAO {
    List<User> getAll();
    User getById(int _id);
    void insert(Ship ship);
    void update(Ship ship);
    void delete(Ship ship);
}
