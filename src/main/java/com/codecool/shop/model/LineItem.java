package com.codecool.shop.model;


public class LineItem {

    private int id;
    private Product product;
    private Order order;
    private int quantity;
    private float subtotalPrice;

    //constructor


    public LineItem() {
        this.id = IdFactory.getInstance().id(this.getClass());
    }

    public LineItem(Product product) {
        this();
        this.product = product;
        this.quantity = 1;
        this.subtotalPrice = product.getDefaultPrice();
    }

    public LineItem(Product product, Order order) {
        this(product);
        this.order = order;
    }


    public LineItem( Product product, Order order, int quantity, float subtotalPrice) {
        this();
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.subtotalPrice = subtotalPrice;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    //TODO: testIncreaseQuatntity
    public void increaseQuantity() {
        quantity += 1;
        setSubtotalPrice(quantity * product.getDefaultPrice());
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "id=" + id +
                ", product=" + product +
                ", order=" + order +
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