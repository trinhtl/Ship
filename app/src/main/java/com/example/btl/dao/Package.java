package com.example.btl.dao;

public class Package {
    private int id;
    private int idOwner;
    private String shipCost;
    private String advanceMoney;
    private String sendAddress;
    private String recieveAddress;
    private String description;


    public Package(int idOwner, String sendAddress, String recieveAddress, String shipCost, String advanceMoney, String description) {
        this.idOwner = idOwner;
        this.shipCost = shipCost;
        this.advanceMoney = advanceMoney;
        this.sendAddress = sendAddress;
        this.recieveAddress = recieveAddress;
        this.description = description;
    }

    public Package(int id, int idOwner, String sendAddress, String recieveAddress, String shipCost, String advanceMoney, String description) {
        this.id = id;
        this.idOwner = idOwner;
        this.shipCost = shipCost;
        this.advanceMoney = advanceMoney;
        this.sendAddress = sendAddress;
        this.recieveAddress = recieveAddress;
        this.description = description;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public String getShipCost() {
        return shipCost;
    }

    public void setShipCost(String shipCost) {
        this.shipCost = shipCost;
    }

    public String getAdvanceMoney() {
        return advanceMoney;
    }

    public void setAdvanceMoney(String advanceMoney) {
        this.advanceMoney = advanceMoney;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getRecieveAddress() {
        return recieveAddress;
    }

    public void setRecieveAddress(String recieveAddress) {
        this.recieveAddress = recieveAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return idOwner + "-" + shipCost + "-" + advanceMoney + "-" + sendAddress + "-" + recieveAddress + "-" + description;
    }
}
