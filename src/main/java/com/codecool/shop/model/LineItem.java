package com.codecool.shop.model;


public class LineItem {

    private Product product;
    private int quantity;
    private float unitPrice;

    public LineItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.unitPrice = product.getDefaultPrice();
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

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void increaseQuantity() {
        quantity += 1;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }

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
