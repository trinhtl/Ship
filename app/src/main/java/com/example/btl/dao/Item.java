package com.example.btl.dao;

public class Item {
    private int id;//package id
    private String avatar;
    private String nickname;
    private String shipCost;
    private String advanceMoney;
    private String createTime;
    private String sendAddress;
    private String recieveAddress;
    private String description;

    public Item(int id, String avatar, String nickname, String shipCost, String advanceMoney, String createTime, String sendAddress, String recieveAddress, String description) {
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.shipCost = shipCost;
        this.advanceMoney = advanceMoney;
        this.createTime = createTime;
        this.sendAddress = sendAddress;
        this.recieveAddress = recieveAddress;
        this.description = description;
        if (avatar == null) this.avatar = "a";
        if (nickname == null) this.nickname = "anonymous";
        if (shipCost == null) this.shipCost = "0";
        if (advanceMoney == null) this.advanceMoney = "0";
        if (createTime == null) this.createTime = "";
        if (sendAddress == null) this.sendAddress = "";
        if (recieveAddress == null) this.recieveAddress = "";
        if (description == null) this.description = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
        return id + "-"  + avatar + "-" + nickname + "-"  + shipCost + "-"  + advanceMoney + "-"  + createTime + "-"  + sendAddress + "-"  + recieveAddress + "-"  + description;
    }
}
