package com.codecool.shop.example;


import com.codecool.shop.dao.*;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;


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

    public void product(int id, String name, float defaultPrice, String defaultCurrency, String description, ProductCategory productCategory, Supplier supplier) {
        ProductDao productDataStore = DataStorageFactory.productDaoFactory();
        productDataStore.add(new Product(id, name, defaultPrice, defaultCurrency, description, productCategory, supplier));
    }

    public void customer(int id,
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
        customerDataStore.add(new Customer(id, name, email, phone, billingCountry, billingCity, billingZipcode, billingAddress, shippingCountry, shippingCity, shippingZipcode, shippingAddress));
    }
}
