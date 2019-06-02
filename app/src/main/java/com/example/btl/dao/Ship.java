package com.example.btl.dao;

public class Ship {
    private int id;
    private int idPackage;
    private int idShipper;
    private String status;
    private String shippedAt;

    public Ship(int id, int idPackage, int idShipper, String status, String shippedAt) {
        this.id = id;
        this.idPackage = idPackage;
        this.idShipper = idShipper;
        this.status = status;
        this.shippedAt = shippedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(int idPackage) {
        this.idPackage = idPackage;
    }

    public int getIdShipper() {
        return idShipper;
    }

    public void setIdShipper(int idShipper) {
        this.idShipper = idShipper;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippedAt() {
        return shippedAt;
    }

    public void setShippedAt(String shippedAt) {
        this.shippedAt = shippedAt;
    }

    @Override
    public String toString() {
        return id + "/" + idPackage + "/" + idShipper + "/" + status + "/" + shippedAt;
    }
}
