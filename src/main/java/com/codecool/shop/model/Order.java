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
        LineItem chosenItem;

        if (lineItems.contains(newItem)) {
            chosenItem = lineItems.get(lineItems.indexOf(newItem));
            chosenItem.setQuantity(chosenItem.getQuantity() + 1);
        } else {
            lineItems.add(newItem);
        }
        System.out.println("lineItems = " + lineItems);
    }

    public int sumLineItem() {
        int result = 0;
        for (int i = 0; i < lineItems.size(); i++) {
            result += lineItems.get(i).getUnitPrice() * lineItems.get(i).getQuantity();
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
}
