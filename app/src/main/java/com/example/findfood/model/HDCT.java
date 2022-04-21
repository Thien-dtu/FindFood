package com.example.findfood.model;

import java.util.ArrayList;

public class HDCT {
    private String idHDCT;
    private String idHoaDon;
    String thoigian;
    String check;
    String idUser;
    ArrayList<Order> orderArrayList;
    User user;

    public HDCT() {
    }

    public HDCT(String idHDCT, String idHoaDon, String thoigian, String check, String idUser, ArrayList<Order> orderArrayList, User user) {
        this.idHDCT = idHDCT;
        this.idHoaDon = idHoaDon;
        this.thoigian = thoigian;
        this.check = check;
        this.idUser = idUser;
        this.orderArrayList = orderArrayList;
        this.user = user;
    }

    public String getIdHDCT() {
        return idHDCT;
    }

    public void setIdHDCT(String idHDCT) {
        this.idHDCT = idHDCT;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public ArrayList<Order> getOrderArrayList() {
        return orderArrayList;
    }

    public void setOrderArrayList(ArrayList<Order> orderArrayList) {
        this.orderArrayList = orderArrayList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
