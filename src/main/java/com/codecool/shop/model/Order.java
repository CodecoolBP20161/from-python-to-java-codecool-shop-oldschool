package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order implements CartInterface {

    private List<LineItem> lineItems = new ArrayList();

    private Customer customer;

    private OrderStatus orderStatus;

    private int id;

    public Order() {
        this.id = IdFactory.getInstance().id(this.getClass());
    }

    public Order(Customer customer, OrderStatus orderStatus) {
        this();
        this.customer = customer;
        this.orderStatus = orderStatus;

    }


    public void addProduct(Product product) {
        LineItem itemToBeAdded = new LineItem(product);

        if (lineItems.contains(itemToBeAdded)) {
            find(itemToBeAdded).increaseQuantity();
        } else {
            lineItems.add(itemToBeAdded);
        }
    }

    public LineItem find(LineItem item) {
        return lineItems.stream().filter(i -> i.equals(item)).findFirst().orElse(null);
    }

    public int getTotalPrice() {
        int result = 0;
        for (int i = 0; i < lineItems.size(); i++) {
            result += lineItems.get(i).getProduct().getDefaultPrice() * lineItems.get(i).getQuantity();
        }
        return result;
    }

    public int sumLineItemQuantitiesInCart() {
        int sum = 0;
        for (int i = 0; i < lineItems.size(); i++) {
            sum += lineItems.get(i).getQuantity();
        }
        return sum;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "lineItems=" + lineItems +
                ", customer=" + customer +
                ", orderStatus=" + orderStatus +
                ", id=" + id +
                '}';
    }
}