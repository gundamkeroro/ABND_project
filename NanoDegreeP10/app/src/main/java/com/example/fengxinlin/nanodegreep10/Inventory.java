package com.example.fengxinlin.nanodegreep10;

/**
 * Created by fengxinlin on 10/8/16.
 */
public class Inventory {
    private int id;
    private String productName;
    private int quantity;
    private double price;

    public Inventory() {
        super();
    }

    public Inventory(String productName, int quantity, double price) {
        super();
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void sale() {
        this.quantity = this.quantity - 1;
        if (this.quantity < 0) {
            this.quantity = 0;
        }
    }
}
