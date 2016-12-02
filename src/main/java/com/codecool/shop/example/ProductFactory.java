package com.codecool.shop.example;


import com.codecool.shop.dao.DataStorageFactory;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
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
}
