package com.example.btl.dao;
//hihi
public class User {
    private int id;
    private String name;
    private String phone;
    private String password;
    private String avatar;

    public User(String name, String phone, String password, String avatar) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.avatar = "a";
    }
    public User(int id, String name, String phone, String password, String avatar) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.avatar = "a";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return id + "-" + name + "-" + phone + "-" + password + "-" + avatar;
    }
}
