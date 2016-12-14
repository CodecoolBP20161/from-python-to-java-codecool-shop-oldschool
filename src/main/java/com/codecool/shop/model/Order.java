package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order implements CartInterface {

    private List<LineItem> lineItems = new ArrayList();

    private Customer customer;

    private OrderStatus orderStatus;

    private int id;

    public Order() {
    }

    public Order(int id, Customer customer){
        this.id = id;
        this.customer = customer;

    }

    public Order(int id, Customer customer, OrderStatus orderStatus){
        this(id, customer);
        this.orderStatus = orderStatus;

    }



    //add lineItem to order lineItems attribute if it is new increase quantity if it is exist
    public void addProduct(Product product) {
        LineItem itemToBeAdded = new LineItem(product);

        if (lineItems.contains(itemToBeAdded)) {
            find(itemToBeAdded).increaseQuantity();
        } else {
            lineItems.add(itemToBeAdded);
        }
    }

    // find lineItem from a list
    public LineItem find(LineItem item) {
        for (int i = 0; i < lineItems.size(); i++) {
            if (lineItems.get(i).equals(item)) {
                return lineItems.get(i);
            }
        }
        return null;
    }

    //Total price in for the products in the cart
    public int getTotalPrice() {
        int result = 0;
        for (int i = 0; i < lineItems.size(); i++) {
            result += lineItems.get(i).getProduct().getDefaultPrice() * lineItems.get(i).getQuantity();
        }
        return result;
    }

    //sub total for products
    //TODO: testSumLineItemQuantitiesInCart
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
        return "Cart{" +
                "lineItems=" + lineItems +
                '}';
    }
}
