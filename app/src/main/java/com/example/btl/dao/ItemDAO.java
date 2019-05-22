package com.example.btl.dao;

import java.util.List;

public interface ItemDAO {
    List<User> getAll();
    User getById(int _id);
    void insert(Item item);
    void update(Item item);
    void delete(Item item);
}
