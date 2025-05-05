package org.app.modules;

public class Queue {
    // implementation of queue, FIFO
    private static class Node {
        Customer customer;
        Node next;
        Node prev;

        public Node(Customer customer) {
            this.customer = customer;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;

    public Queue() {
        this.head = null;
        this.tail = null;
    }

    public void enqueue(Customer customer) {
        // add customer
        Node node = new Node(customer);

        if (head == null) {
            head = node;
        } else {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
        System.out.println(customer.getCustomerName() + " queue to checkout.");
    }

    public String dequeue() {

        if (head == null) {
            System.out.println("Queue is empty");
            return "";
        }

        String customerName = head.customer.getCustomerName();
        head = head.next;

        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        System.out.println(customerName + " has finished checking out and dequeued.");
        return customerName;
    }


    public void displayQueue() {
        if (head == null) {
            System.out.println("\n=====================================================");
            System.out.println("Queue is empty");
            System.out.println("\n=====================================================");
            return;
        }

        Node current = head;
        System.out.println("\n=====================================================");
        System.out.println("\t\t\tCurrent Checkout Queue");
        while (current != null) {
            System.out.print(current.customer.getCustomerName());
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;

        }
        System.out.println();
        System.out.println("\n=====================================================");

    }

    public Customer getCustomer(String customerName) {
        Node current = head;
        while (current != null) {
            if (current.customer.getCustomerName().equals(customerName)) {
                return current.customer;
            }

            current = current.next;
        }

        return null;
    }

    public int size() {

        if (head == null) {
            return 0;
        }

        Node current = head;
        int counter = 0;
        while (current != null) {
            counter++;
            current = current.next;
        }
        return counter;
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        Inventory inventory = new Inventory();
        inventory.addItem("banana", new Item(3.99, 5));
        inventory.addItem("apple", new Item(5.99, 3));
        inventory.addItem("orange", new Item(4.99, 5));
        inventory.displayInventory();
        Customer one = new Customer(1, "james", inventory);
        Customer two = new Customer(2, "mark", inventory);
        Customer three = new Customer(1, "julia", inventory);
        Customer four = new Customer(1, "ruby", inventory);
        queue.enqueue(one);
        queue.enqueue(two);
        queue.enqueue(three);
        queue.enqueue(four);
        queue.displayQueue();
        queue.dequeue();
        queue.displayQueue();
        System.out.println(queue.size());
    }

}
