package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order implements Orderable {
    private List<LineItem> lineItems = new ArrayList();

    public List getLineItems() {
        return lineItems;
    }

    public Order() {

    }

    public void addProduct(Product product) {
        LineItem newItem = new LineItem(product);

        if (lineItems.size() == 0) {
            lineItems.add(newItem);
        } else {
            for (int i = 0; i < lineItems.size(); i++) {
                if (lineItems.get(i).getProduct().getId() == product.getId()) {
                    lineItems.get(i).setQuantity(lineItems.get(i).getQuantity() + 1);
                    lineItems.get(i).setUnitPrice(lineItems.get(i).getProduct().getDefaultPrice() * lineItems.get(i).getQuantity());
                } else {
                    lineItems.add(newItem);
                }
            }
        }
    }

    public int sumLineItem() {
        int result = 0;
        for (int i = 0; i < lineItems.size(); i++) {
            result += lineItems.get(i).getProduct().getDefaultPrice() * lineItems.get(i).getQuantity();
        }

        return result;
    }

    public int getNumberOfLineItemsInCart() {
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
