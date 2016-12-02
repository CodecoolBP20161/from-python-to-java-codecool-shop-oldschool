package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order implements OrderInterface {

    private List<LineItem> lineItems = new ArrayList();

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public Order() {
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

    @Override
    public String toString() {
        return "Order{" +
                "lineItems=" + lineItems +
                '}';
    }
}
