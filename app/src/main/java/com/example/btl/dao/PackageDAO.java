package com.example.btl.dao;

import com.example.btl.fragments.ShipperCurrentPackageFragment;
import com.example.btl.fragments.ShopCurrentPackageFragment;

import java.util.List;

public interface PackageDAO {
    List<Package> getAll();
    Package getById(int _id);
    void insert(Package _package);
    void update(Package _package);
    void delete(Package _package);
    List<Package> getByIdShipper(final ShipperCurrentPackageFragment pk, int idShipper);
    List<Package> getByIdShop(final ShopCurrentPackageFragment pk, int idShop);
}
