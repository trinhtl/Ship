package com.example.btl.dao;

import java.util.List;

public interface PackageDAO {
    List<Package> getAll();
    Package getById(int _id);
    void insert(Package _package);
    void update(Package _package);
    void delete(Package _package);
}
