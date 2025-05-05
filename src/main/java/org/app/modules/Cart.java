package org.app.modules;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String, Item> itemsCart;
    private double tax;
    private double totalCost;
    private Inventory inventory;

    public Cart(Inventory inventory) {
        this.itemsCart = new HashMap<>();
        this.tax = 0.06;
        this.totalCost = 0.0;
        this.inventory = inventory;
    }

    public void addToCart(String itemName, int qty) {

        if (!inventory.hasItem(itemName)) {
            System.out.println("Item is not in Inventory");
            return;
        }

        // check if in stock
        if (inventory.getQty(itemName) == 0) {
            System.out.println("Item is out of stock, and cannot be added to the cart");
            return;
        }

        if (inventory.getQty(itemName) < qty) {
            System.out.println("Not enough stock for this item. Please try again.");
            return;
        }

        try {
            double itemPrice = inventory.getItemPrice(itemName);
            if (itemsCart.containsKey(itemName)) {
                int currentQty = itemsCart.get(itemName).getQuantity();
                itemsCart.get(itemName).setQuantity(currentQty + qty);
                System.out.println(itemName + " qty Updated");
            } else {
                Item item = new Item(itemPrice, qty);
                itemsCart.put(itemName, item);
                System.out.println(itemName + " added to cart.");
            }
            updateTotalCost();
            inventory.decreaseItemQty(itemName, qty);
        } catch (Exception e) {
            System.out.println("Error Adding to Cart: " + e.getMessage());
        }

    }

    public void updateTotalCost() {

        if (cartCount() == 0) {
            return;
        }

        double itemTotalCost = 0;

        for (Map.Entry<String, Item> entry : itemsCart.entrySet()) {
            double itemCost = entry.getValue().getPrice() * itemsCart.get(entry.getKey()).getQuantity();
            itemTotalCost += itemCost;
        }

        double FinalTotalCost = Math.round((tax * itemTotalCost) + itemTotalCost * 100.0) / 100.0;
        setTotalCost(FinalTotalCost);


    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }


    public int cartCount() {
        int itemsQtyCount = 0;
        for (Map.Entry<String, Item> entry : itemsCart.entrySet()) {
            itemsQtyCount += entry.getValue().getQuantity();
        }
        return itemsQtyCount;
    }

    @Override
    public String toString() {
        if (itemsCart.isEmpty()) {
            return "Cart is empty.";
        }

        String result = "Cart:";
        for (Map.Entry<String, Item> entry : itemsCart.entrySet()) {
            result += "\n\t" + entry.getKey() + " - Qty: " + entry.getValue().getQuantity() +
                    ", Price: $" + entry.getValue().getPrice();
        }
        result += "\nTotal cost: " + getTotalCost();
        return result;
    }


    public double getTotalCost() {
        return totalCost;
    }

    public void deleteCartItem(String itemName) {
        if (!itemsCart.containsKey(itemName)) {
            System.out.println("Item is not in cart.");
            return;
        }

        int qty = itemsCart.get(itemName).getQuantity();

        itemsCart.remove(itemName);
        inventory.increaseItemQty(itemName, qty);
        if (!itemsCart.containsKey(itemName)) {
            System.out.println(itemName + " is removed from the cart.");
        }
        updateTotalCost();


    }

    public void updateItemQty(String itemName, int qty) {
        // decrease cart qty
        // increase inventory qty
        if (!itemsCart.containsKey(itemName)) {
            System.out.println("Item is not in cart.");
            return;

        }

        int qtyDifference;
        int currentItemQty = itemsCart.get(itemName).getQuantity();
        qtyDifference = currentItemQty - qty;
        itemsCart.get(itemName).setQuantity(qty);
        inventory.increaseItemQty(itemName, qtyDifference);
        System.out.println(itemName + " qty Updated in cart");
        System.out.println(itemName + " qty Updated in inventory");
        updateTotalCost();

    }


    public void displayCart() {

        if (itemsCart.isEmpty()) {
            System.out.println("\n=====================================================");
            System.out.println("Cart is empty.");
            System.out.println("\n=====================================================");
            return;
        }
        System.out.println("\n=====================================================");
        System.out.println("\t\t\t\tCart:");
        itemsCart.forEach((key, value) -> {
            System.out.println(key + " {" + value + "}");
        });
        System.out.println("\n=====================================================");
    }


    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addItem("banana", new Item(3.99, 5));
        inventory.addItem("apple", new Item(5.99, 3));
        inventory.addItem("orange", new Item(4.99, 5));
        inventory.displayInventory();
        Cart cart = new Cart(inventory);
        cart.addToCart("apple", 2);
        System.out.println("Total cost: " + cart.getTotalCost());
        cart.addToCart("apple", 1);
        cart.addToCart("banana", 4);
        System.out.println("Total cost: " + cart.getTotalCost());
        cart.displayCart();
        inventory.displayInventory();
        System.out.println("Update inventory");
        inventory.getItem("banana").setQuantity(10);
        inventory.getItem("apple").setQuantity(10);
        inventory.displayInventory();
        cart.addToCart("apple", 8);
        cart.addToCart("banana", 8);
        cart.displayCart();
        System.out.println("Total cost: " + cart.getTotalCost());

    }

}