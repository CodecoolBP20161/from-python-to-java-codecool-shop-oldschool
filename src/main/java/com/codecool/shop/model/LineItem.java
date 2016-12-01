package com.codecool.shop.model;


public class LineItem {

    private Product product;
    private int quantity;
    private float subtotalPrice;

    //constructor
    public LineItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.subtotalPrice = product.getDefaultPrice();
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

    public float getSubtotalPrice() {
        return subtotalPrice;
    }

    public void setSubtotalPrice(float subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
    }

    //TODO: testIncreaseQuatntity
    public void increaseQuantity() {
        quantity += 1;
        setSubtotalPrice(quantity * product.getDefaultPrice());
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", subtotalPrice=" + subtotalPrice +
                '}';
    }
    //overrided equals and hashcode method for comparison by id
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!LineItem.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        LineItem other = (LineItem) obj;
        return this.product.getId() == other.product.getId();
    }

    @Override
    public int hashCode() {
        return product.getId();
    }
}
