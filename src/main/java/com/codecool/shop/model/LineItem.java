package com.codecool.shop.model;


public class LineItem {

    private Product product;
    private int quantity;
    private float unitPrice;

    public LineItem (Product product) {
        this.product = product;
        this.quantity = 1;
        this.unitPrice = product.getDefaultPrice() * this.getQuantity();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }
}