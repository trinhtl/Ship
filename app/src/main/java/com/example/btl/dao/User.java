package com.example.btl.dao;

import java.sql.Date;

public class User {
    private int id;
    private String role;
    private String phone;
    private String name;
    private String email;

    public User(String role, String phone, String name, String email, String avatar) {
        this.role = role;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }

    private String avatar;
    private Date updateTime;
    private Date createTime;

//    public boolean insert(int id, String role, String phone, String name, String email, String avatar, Date updateTime, Date createTime){
//        return true;
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
