package org.app.modules;


import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<String, Item> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    public void addItem(String itemName, Item itemDetail) {

        if (items.containsKey(itemName)) {
            System.out.println("Item " + itemName + " already exists");
            System.out.println("Please edit the quantity");
            return;
        }

        try {
            items.put(itemName, itemDetail);
            System.out.println(itemName + " Added to Inventory.");
        } catch (Exception e) {
            System.out.println("Error: " + itemName + " could not be added: " + e.getMessage());
        }
    }


    public double getItemPrice(String itemName) {

        if (items.containsKey(itemName)) {
            return items.get(itemName).getPrice();
        }
        return 0;
    }

    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    public void removeItem(String itemName) {
        if (!items.containsKey(itemName)) {
            System.out.println(itemName + " does not exist");
            return;
        }

        items.remove(itemName);
        System.out.println(itemName + " removed from Inventory.");
    }

    public boolean hasItem(String itemName) {
        return items.containsKey(itemName);
    }

    public int totalItems() {
        return items.size();
    }

    public void displayInventory() {
        if (items.isEmpty()) {
            System.out.println("\n=====================================================");
            System.out.println("Inventory is empty");
            System.out.println("\n=====================================================");
            return;
        }

        System.out.println("\n=====================================================");
        System.out.println("\t\t\t\tInventory:");
        items.forEach((key, value) -> {
            System.out.println(key + " {" + value + "}");
        });
        System.out.println("\n=====================================================");
    }

    public int getQty(String itemName) {
        return items.get(itemName).getQuantity();
    }


    public void decreaseItemQty(String itemName, int qty) {
        items.get(itemName).setQuantity(items.get(itemName).getQuantity() - qty);

    }

    public void increaseItemQty(String itemName, int qty) {
        items.get(itemName).setQuantity(items.get(itemName).getQuantity() + qty);
    }


}
