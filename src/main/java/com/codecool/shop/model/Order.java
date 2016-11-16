package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order implements Orderable {
    private List lineItems = new ArrayList();

    public List getLineItems() {
        return lineItems;
    }

    public void addProduct(Product product) {
        LineItem newItem = new LineItem(product);
        LineItem chosenItem;
        if (lineItems.contains(newItem)){
           chosenItem = (LineItem)lineItems.get(lineItems.indexOf(newItem));
           chosenItem.setQuantity(chosenItem.getQuantity() + 1);
        }
        else {
            lineItems.add(newItem);
        }
        System.out.println("lineItems = " + lineItems);
    }
}
