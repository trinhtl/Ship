package com.example.btl.dao;

import java.util.List;

public interface UserDAO {
    List<User> getAll();
    User getById(int _id);
    void insert(User user);
    void update(User user);
    void delete(User user);
    User validate(String phone, String password);
}
