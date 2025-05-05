package org.app;

import org.app.modules.Customer;
import org.app.modules.Inventory;
import org.app.modules.Item;
import org.app.modules.Queue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void welcomeMessage() {
        System.out.println("\n#################################################");
        System.out.println("\t\t\t\tWelcome to the Supermarket!!!");
        System.out.println("\n#################################################");
    }

    public static void mainMenu() {
        System.out.println("\n=====================================================");
        System.out.println("\t\t\tAdmin Menu");
        System.out.println("1. Create a Customer.");
        System.out.println("2. Check out a Customer.");
        System.out.println("3. Check Inventory.");
        System.out.println("4. Check All Customers in the Supermarket.");
        System.out.println("5. Switch to a Customer view");
        System.out.println("0. Quit program.");
        System.out.println("\n=====================================================");
        System.out.print("Enter your choice: ");
    }

    public static void checkOutMenu() {
        System.out.println("\n=====================================================");
        System.out.println("\t\t\tCheckout a Customer");
        System.out.println("1. View current Queue");
        System.out.println("2. Check out a customer.");
        System.out.println("3. Exit and go back to Main Menu.");
        System.out.println("\n=====================================================");
        System.out.print("Enter your choice: ");
    }

    public static void createCustomerMenu() {
        System.out.println("\n=====================================================");
        System.out.println("\t\t\tCreate a New Customer.");
        System.out.println("1. Add customer.");
        System.out.println("2. Exit and go back to main menu.");
        System.out.println("\n=====================================================");
        System.out.print("Enter your choice: ");
    }

    public static void inventoryMenu() {
        System.out.println("\n=====================================================");
        System.out.println("\t\t\tInventory Management");
        System.out.println("1. Check current inventory.");
        System.out.println("2. Add new item.");
        System.out.println("3. Remove item.");
        System.out.println("4. Edit item.");
        System.out.println("5. Exit and go back to main menu.");
        System.out.println("=====================================================");
        System.out.print("Enter your choice: ");
    }

    public static void editItemMenu() {
        System.out.println("\n=====================================================");
        System.out.println("\t\t\tEdit Item");
        System.out.println("1. Change item name.");
        System.out.println("2. Update item price.");
        System.out.println("3. Update item quantity.");
        System.out.println("4. Exit and go back to inventory menu.");
        System.out.println("\n=====================================================");
        System.out.print("Enter your choice: ");
    }

    // customer view point menu
    public static void customerMainMenu() {
        System.out.println("\n=====================================================");
        System.out.println("\t\t\tCustomers Menu");
        System.out.println("1. Check inventory");
        System.out.println("2. Check your cart");
        System.out.println("3. Go to checkout.");
        System.out.println("4. Exit the supermarket.");
        System.out.println("5. Exit and go back to Customer Selection Menu.");
        System.out.println("\n=====================================================");
        System.out.print("Enter your choice: ");
    }

    public static void customerChoiceMenu() {
        System.out.println("\n=====================================================");
        System.out.println("\t\t\tSelect a Customer");
        System.out.println("1. View All Customers");
        System.out.println("2. Select a Customer");
        System.out.println("3. Exit and go back to Main Menu.");
        System.out.println("\n=====================================================");
        System.out.print("Enter your choice: ");
    }

    public static void customerAddItemToCart() {
        System.out.println("\n=====================================================");
        System.out.println("\t\t\tCheck Inventory");
        System.out.println("1. Add item to cart");
        System.out.println("2. Exit and go back to Customer Menu.");
        System.out.println("\n=====================================================");
        System.out.print("Enter your choice: ");
    }

    public static void customerReviewCart() {
        System.out.println("\n=====================================================");
        System.out.println("\t\t\tCart Review");
        System.out.println("1. Delete Item");
        System.out.println("2. Edit Cart Item");
        System.out.println("3. Exit and go back to Customer Menu.");
        System.out.println("\n=====================================================");
        System.out.print("Enter your choice: ");
    }

    public static void customerEditCart() {
        System.out.println("\n=====================================================");
        System.out.println("\t\t\tEdit Cart Item");
        System.out.println("1. Change item quantity");
        System.out.println("2. Exit and go back to Cart Review Menu.");
        System.out.println("\n=====================================================");
        System.out.print("Enter your choice: ");
    }

    public static void displayCustomersArray(List<Customer> customerArray) {

        if (customerArray.isEmpty()) {
            System.out.println("\n=====================================================");
            System.out.println("\t\t\tNo Customers Found");
            System.out.println("\n=====================================================");
            return;
        }

        System.out.println("\n=====================================================");
        for (Customer customer : customerArray) {
            System.out.println("{" + "Id: " + customer.getCustomerId() + ", Name: " + customer.getCustomerName() +
                    ", Cart: " + customer.getCart());
        }
        System.out.println("\n=====================================================");
    }

    public static void readFile(File fileName, Inventory inventory) {
        try {
            Scanner inputFile = new Scanner(fileName);
            String itemName;
            double price;
            int quantity;
            while (inputFile.hasNextLine()) {
                String line = inputFile.nextLine();
                String[] lineArray = line.split(",");
                itemName = lineArray[0];
                price = Double.parseDouble(lineArray[1]);
                quantity = Integer.parseInt(lineArray[2]);
                Item newItem = new Item(price, quantity);
                inventory.addItem(itemName, newItem);
            }
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        File fileName = new File("src/main/resources/market_data.txt");
        Inventory inventory = new Inventory();
        readFile(fileName, inventory);
        List<Customer> customerArray = new ArrayList<>();
        Queue queue = new Queue();

        int choice;
        welcomeMessage();
        do {
            mainMenu();
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    int customerChoice;
                    do {
                        createCustomerMenu();
                        customerChoice = input.nextInt();

                        input.nextLine();
                        if (customerChoice == 1) {
                            String customerName;
                            int customerId;
                            System.out.print("Customer ID: ");
                            customerId = input.nextInt();
                            input.nextLine();
                            System.out.print("Customer Name: ");
                            customerName = input.nextLine();
                            Customer customer = new Customer(customerId, customerName, inventory);
                            customerArray.add(customer);
                            System.out.println(customerName + " created.");
                        }


                    } while (customerChoice != 2);
                    break;
                case 2:
                    int checkOutChoice;
                    do {
                        checkOutMenu();
                        checkOutChoice = input.nextInt();
                        input.nextLine();

                        if (checkOutChoice == 2) {
                            String dequeuedCustomer = queue.dequeue();
                            System.out.println(dequeuedCustomer);
                            Customer removedCustomer = queue.getCustomer(dequeuedCustomer);
                            customerArray.remove(removedCustomer);
                        }

                        if (checkOutChoice == 1) {
                            queue.displayQueue();
                        }

                    } while (checkOutChoice != 3);
                    break;
                case 3:
                    int inventoryChoice;

                    do {
                        inventoryMenu();
                        inventoryChoice = input.nextInt();
                        input.nextLine();

                        switch (inventoryChoice) {
                            case 1:
                                inventory.displayInventory();
                                break;
                            case 2:
                                System.out.println("Adding new item... ...");
                                String itemName;
                                double price;
                                int quantity;
                                System.out.print("Enter item name: ");
                                itemName = input.nextLine();
                                System.out.print("Enter item price: ");
                                price = Double.parseDouble(input.nextLine());
                                input.nextLine();
                                System.out.print("Enter item quantity: ");
                                quantity = Integer.parseInt(input.nextLine());
                                input.nextLine();

                                inventory.addItem(itemName, new Item(price, quantity));
                                break;
                            case 3:
                                System.out.println("Removing old item... ...");
                                String itemToRemove;
                                System.out.print("Enter item name: ");
                                itemToRemove = input.nextLine();
                                inventory.removeItem(itemToRemove);
                                break;
                            case 4:
                                int editItemChoice;
                                do {
                                    editItemMenu();
                                    editItemChoice = input.nextInt();
                                    input.nextLine();

                                    switch (editItemChoice) {
                                        case 1:
                                            String itemToRename;
                                            System.out.print("Enter item name: ");
                                            itemToRename = input.nextLine();

                                            if (inventory.hasItem(itemToRename)) {
                                                System.out.println(itemToRename + " found in the inventory.");
                                                String renameItem;

                                                do {
                                                    System.out.print("What do you want to rename to: ");
                                                    renameItem = input.nextLine();

                                                    if (inventory.hasItem(renameItem)) {
                                                        System.out.println(renameItem + " is already in the " +
                                                                "inventory, please choose another name.");
                                                    }


                                                } while (inventory.hasItem(renameItem));
                                                double itemToRenamePrice;
                                                int itemToRenameQuantity;
                                                itemToRenamePrice = inventory.getItem(itemToRename).getPrice();
                                                itemToRenameQuantity = inventory.getItem(itemToRename).getQuantity();
                                                inventory.removeItem(itemToRename);
                                                inventory.addItem(renameItem, new Item(itemToRenamePrice, itemToRenameQuantity));

                                            }
                                            break;
                                        case 2:
                                            double newItemPrice;
                                            String itemPriceToChange;
                                            System.out.print("Enter item you want to change price of:  ");
                                            itemPriceToChange = input.nextLine();
                                            if (inventory.hasItem(itemPriceToChange)) {
                                                System.out.print("Enter " + itemPriceToChange + " new price: ");
                                                newItemPrice = input.nextDouble();
                                                input.nextLine();
                                                inventory.getItem(itemPriceToChange).setPrice(newItemPrice);
                                            } else {
                                                System.out.println("Item not found.");
                                            }
                                            break;
                                        case 3:
                                            int newItemQty;
                                            String itemQtyToChange;
                                            System.out.print("Enter item you want to change qty of:  ");
                                            itemQtyToChange = input.nextLine();
                                            if (inventory.hasItem(itemQtyToChange)) {
                                                System.out.print("Enter " + itemQtyToChange + " new qty: ");
                                                newItemQty = input.nextInt();
                                                input.nextLine();
                                                inventory.getItem(itemQtyToChange).setQuantity(newItemQty);
                                            } else {
                                                System.out.println("Item not found.");
                                            }
                                            break;
                                    }

                                } while (editItemChoice != 4);
                                break;
                        }
                        System.out.println("Updating Inventory .....");

                    } while (inventoryChoice != 5);

                    break;
                case 4:
                    displayCustomersArray(customerArray);
                    break;
                case 5:
                    int selectionChoice;
                    do {
                        customerChoiceMenu();
                        selectionChoice = input.nextInt();
                        input.nextLine();

                        switch (selectionChoice) {
                            case 1:
                                displayCustomersArray(customerArray);
                                break;
                            case 2:
                                int customerId;
                                System.out.print("Enter customer ID: ");
                                customerId = input.nextInt();
                                input.nextLine();
                                Customer selectedCustomer = null;
                                for (Customer customer : customerArray) {
                                    if (customer.getCustomerId() == customerId) {
                                        selectedCustomer = customer;
                                    }
                                }

                                if (selectedCustomer == null) {
                                    System.out.println("Customer not found.");
                                } else {
                                    String customerName = selectedCustomer.getCustomerName();
                                    System.out.println(customerName + " is currently shopping.");

                                    int currentCustomerChoice;
                                    do {
                                        customerMainMenu();
                                        currentCustomerChoice = input.nextInt();
                                        input.nextLine();

                                        switch (currentCustomerChoice) {
                                            case 1:
                                                inventory.displayInventory();
                                                customerAddItemToCart();
                                                int inventoryItemChoice;
                                                inventoryItemChoice = input.nextInt();
                                                input.nextLine();
                                                if (inventoryItemChoice == 1) {
                                                    String itemName;
                                                    System.out.print("Enter item name: ");
                                                    itemName = input.nextLine();
                                                    int itemQty;
                                                    System.out.print("Enter item quantity: ");
                                                    itemQty = input.nextInt();
                                                    selectedCustomer.getCustomerCart().addToCart(itemName, itemQty);
                                                }
                                                break;
                                            case 2:
                                                System.out.println(selectedCustomer.getCustomerCart());
                                                int reviewItemChoice;

                                                do {
                                                    customerReviewCart();

                                                    reviewItemChoice = input.nextInt();
                                                    input.nextLine();

                                                    switch (reviewItemChoice) {
                                                        case 1:
                                                            String itemName;
                                                            System.out.print("Enter item name: ");
                                                            itemName = input.nextLine();
                                                            selectedCustomer.getCustomerCart().deleteCartItem((itemName));
                                                            break;
                                                        case 2:
                                                            selectedCustomer.getCart();
                                                            int editItemChoice;

                                                            do {
                                                                customerEditCart();
                                                                editItemChoice = input.nextInt();
                                                                input.nextLine();
                                                                String itemEditName;
                                                                if (editItemChoice == 1) {
                                                                    System.out.print("Enter item name: ");
                                                                    itemEditName = input.nextLine();
                                                                    System.out.print("Enter item quantity: ");
                                                                    int itemQty = input.nextInt();
                                                                    input.nextLine();
                                                                    selectedCustomer.getCustomerCart().updateItemQty(itemEditName, itemQty);
                                                                }

                                                            } while (editItemChoice != 2);

                                                            break;
                                                    }
                                                } while (reviewItemChoice != 3);


                                                break;
                                            case 3:
                                                queue.enqueue(selectedCustomer);
                                                break;
                                            case 4:

                                                System.out.println(selectedCustomer.getCustomerName() + " has left the " +
                                                        "Supermarket.");
                                                customerArray.remove(selectedCustomer);
                                                break;
                                        }

                                    } while (currentCustomerChoice != 5);

                                }
                                break;
                        }

                    } while (selectionChoice != 3);
            }

        } while (choice != 0);

        input.close();

    }
}