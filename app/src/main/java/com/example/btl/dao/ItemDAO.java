package com.example.btl.dao;

import com.example.btl.fragments.PackageListFragment;

import java.util.List;

public interface ItemDAO {
    List<Item> getAll(PackageListFragment pk);
    User getById(int _id);
    void insert(Item item);
    void update(Item item);
    void delete(Item item);
}
