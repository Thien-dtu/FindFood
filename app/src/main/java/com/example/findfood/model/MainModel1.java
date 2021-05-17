package com.example.findfood.model;

import android.widget.TextView;

public class MainModel1 {

    String tenquan, diachi;

    public MainModel1() {

    }

    public MainModel1(String tenquan, String diachi) {
        this.tenquan = tenquan;
        this.diachi = diachi;
    }

    public String getTenquan() {
        return tenquan;
    }

    public void setTenquan(String tenquan) {
        this.tenquan = tenquan;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}

