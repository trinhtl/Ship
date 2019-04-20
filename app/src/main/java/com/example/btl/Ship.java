package com.example.btl;

import java.sql.Date;

public class Ship {
    private int id;
    private int idBill;
    private int idShipper;
    private String status;
    private Date shipTime;
    private Date updateTime;
    private Date createTime;

    public Ship(int id, int idBill, int idShipper, String status, Date shipTime, Date updateTime, Date createTime) {
        this.id = id;
        this.idBill = idBill;
        this.idShipper = idShipper;
        this.status = status;
        this.shipTime = shipTime;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
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

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
