package com.codecool.shop.model;

import java.util.List;

public class Order implements Orderable {
    private List LINEITEMS;

    public List getLINEITEMS() {
        return LINEITEMS;
    }

    public void addLineItem(int productId) {
        LineItem lineItem = new Lineitem(productId);
        LINEITEMS.add(lineItem);
    }
}
