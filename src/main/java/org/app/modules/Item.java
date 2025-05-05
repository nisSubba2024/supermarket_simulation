package org.app.modules;

public class Item {

    private double price;
    private int quantity;

    public Item(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }


    @Override
    public String toString() {
        return "Price: $" + getPrice() + ", Qty: " + getQuantity();
    }
}
