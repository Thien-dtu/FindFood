package com.example.findfood.model;

import java.util.ArrayList;

public class HDCT {
    private String idHDCT;
    private String idHoaDon;
    String ngay;
    String thoigian;
    boolean check;
    String idUser;
    ArrayList<Order> orderArrayList;

    public HDCT() {
    }

    public HDCT(String idHDCT, String idHoaDon, String ngay, String thoigian, boolean check, String idUser, ArrayList<Order> orderArrayList) {
        this.idHDCT = idHDCT;
        this.idHoaDon = idHoaDon;
        this.ngay = ngay;
        this.thoigian = thoigian;
        this.check = check;
        this.idUser = idUser;
        this.orderArrayList = orderArrayList;
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

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
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
}
