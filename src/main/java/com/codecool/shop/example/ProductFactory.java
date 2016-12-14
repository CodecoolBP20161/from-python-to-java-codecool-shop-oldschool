package com.codecool.shop.example;


import com.codecool.shop.dao.*;
import com.codecool.shop.model.*;


public class ProductFactory {

    private static ProductFactory instance = null;

    public static synchronized ProductFactory getInstance() {
        if (instance == null) {
            instance = new ProductFactory();
        }
        return instance;
    }

    public ProductCategory productCategory(int id, String name, String department, String description) {
        ProductCategoryDao productCategoryDataStore = DataStorageFactory.productCategoryDaoFactory();
        ProductCategory result = new ProductCategory(id, name, department, description);
        productCategoryDataStore.add(result);
        return result;
    }

    public Supplier supplier(int id, String name, String description) {
        SupplierDao supplierDataStore = DataStorageFactory.supplierDaoFactory();
        Supplier result = new Supplier(id, name, description);
        supplierDataStore.add(result);
        return result;
    }

    public Product product(int id, String name, float defaultPrice, String defaultCurrency, String description, ProductCategory productCategory, Supplier supplier) {
        ProductDao productDataStore = DataStorageFactory.productDaoFactory();
        Product result = new Product(id, name, defaultPrice, defaultCurrency, description, productCategory, supplier);
        productDataStore.add(result);
        return result;
    }

    public Customer customer(int id,
                         String name,
                         String email,
                         String phone,
                         String billingCountry,
                         String billingCity,
                         String billingZipcode,
                         String billingAddress,
                         String shippingCountry,
                         String shippingCity,
                         String shippingZipcode,
                         String shippingAddress) {
        CustomerDao customerDataStore = DataStorageFactory.customerDaoFactory();
        Customer result = new Customer(id, name, email, phone, billingCountry, billingCity, billingZipcode, billingAddress, shippingCountry, shippingCity, shippingZipcode, shippingAddress);
        customerDataStore.add(result);
        return result;
    }

    public Order order(int id, Customer customer, OrderStatus orderStatus) {
        OrderDao orderDataStore = DataStorageFactory.orderDaoFactory();
        Order result = new Order(id, customer, orderStatus);
        orderDataStore.add(result);
        return result;
    }

    public LineItem lineItem(int id, Product product, Order order, int quantity, float subtotalPrice) {
        LineItemDao lineItemDataStore = DataStorageFactory.lineItemDaoFactory();
        LineItem result = new LineItem(id, product, order, quantity, subtotalPrice);
        lineItemDataStore.add(result);
        return result;
    }


}
