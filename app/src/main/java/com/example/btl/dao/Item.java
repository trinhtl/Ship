package com.example.btl.dao;

import java.util.Date;

public class Item {
    private int id;
    private String avatar;
    private String nickname;
    private String shipCost;
    private String advanceMoney;
    private Date createTime;
    private String sendAddress;
    private String recieveAddress;
    private String description;

    public Item(int id, String avatar, String nickname, String shipCost, String advanceMoney, Date createTime, String sendAddress, String recieveAddress, String description) {
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.shipCost = shipCost;
        this.advanceMoney = advanceMoney;
        this.createTime = createTime;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
}
