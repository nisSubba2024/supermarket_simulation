# 🛒 Grocery Store Simulation in Java

This is a console-based **Grocery Store Simulation** written in Java. The project models a small-scale store checkout system with features like inventory management, customer queueing, cart management, and basic transactions.

---

## 📂 Project Structure

```
org/
└── app/
    ├── Main.java         // Entry point of the application
    └── modules/
        ├── Cart.java         // Represents customer's shopping cart
        ├── Customer.java     // Models a customer
        ├── Inventory.java    // Handles inventory operations
        ├── Item.java         // Represents individual items in inventory
        └── Queue.java        // Manages customer checkout queue (FIFO)
```

---

## 🚀 Features

### 🧺 Inventory Management (`Inventory.java`)
- Add items with price and quantity.
- Increase/decrease quantity.
- Remove items.
- Display the current inventory.

### 🧾 Item Class (`Item.java`)
- Stores item price and quantity.
- Used internally by `Inventory`.

### 🧍 Customer Management (`Customer.java`)
- Each customer has an ID, name, and a cart.
- Customers can add items to their cart, checkout, or view total.

### 🛒 Cart Functionality (`Cart.java`)
- Add/remove items from the customer's cart.
- Calculates total cost of the cart.
- Syncs with inventory to adjust available stock.

### 🔄 Customer Queue (`Queue.java`)
- Custom doubly linked list implementation (FIFO).
- Enqueue/dequeue customers at checkout.
- Displays current queue and supports customer lookup.

### 🧠 Main Simulation (`Main.java`)
- Adds items to inventory.
- Creates customer instances.
- Enqueues customers to the checkout queue.
- Dequeues and processes them.
- Demonstrates the full functionality of the store simulation.

---

## 🛠️ Technologies Used

- **Java 24+**
- Object-Oriented Programming (OOP)
- Data Structure: HashMap and Custom Linked List
