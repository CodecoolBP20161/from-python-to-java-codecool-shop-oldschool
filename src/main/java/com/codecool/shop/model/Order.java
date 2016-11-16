package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order implements Orderable {
    private List lineItems = new ArrayList();

    public List getLineItems() {
        return lineItems;
    }

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }
}
