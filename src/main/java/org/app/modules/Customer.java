package org.app.modules;

public class Customer {

    private String customerName;
    private int customerId;
    private Inventory inventory;
    private Cart cart;

    public Customer(int customerId, String customerName, Inventory inventory) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.cart = new Cart(inventory);
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Cart getCustomerCart() {
        return this.cart;
    }

    public String getCart() {
        return cart.toString();
    }

    public void displayCustomer() {
        System.out.println("ID: " + customerId + "\nName: " + customerName);
        cart.displayCart();
    }

}
