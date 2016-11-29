package com.codecool.shop.example;

import com.codecool.shop.dao.DataStorageFactory;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.database.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;
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

    public ProductCategory productCategory(String name, String department, String description) {
        ProductCategoryDao productCategoryDataStore = DataStorageFactory.productCategoryDaoFactory();
        ProductCategory result = new ProductCategory(name, department, description);
        productCategoryDataStore.add(result);
        return result;
    }

    public Supplier supplier(String name, String description) {
        SupplierDao supplierDataStore = DataStorageFactory.supplierDaoFactory();
        Supplier result = new Supplier(name, description);
        supplierDataStore.add(result);
        return result;
    }

    public void product(String name, float defaultPrice, String defaultCurrency, String description, ProductCategory productCategory, Supplier supplier) {
        ProductDao productDataStore = DataStorageFactory.productDaoFactory();
        productDataStore.add(new Product(name, defaultPrice, defaultCurrency, description, productCategory, supplier));
    }
}
