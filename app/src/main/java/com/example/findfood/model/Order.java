package com.example.findfood.model;

public class Order {
    String idorder;
    Food food;
    private int soluongmua;
    User user;

    public Order() {
    }

    public Order(String idorder, Food food, int soluongmua, User user) {
        this.idorder = idorder;
        this.food = food;
        this.soluongmua = soluongmua;
        this.user = user;
    }

    public String getIdorder() {
        return idorder;
    }

    public void setIdorder(String idorder) {
        this.idorder = idorder;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getSoluongmua() {
        return soluongmua;
    }

    public void setSoluongmua(int soluongmua) {
        this.soluongmua = soluongmua;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idorder='" + idorder + '\'' +
                ", food=" + food +
                ", soluongmua=" + soluongmua +
                ", user=" + user +
                '}';
    }
}
