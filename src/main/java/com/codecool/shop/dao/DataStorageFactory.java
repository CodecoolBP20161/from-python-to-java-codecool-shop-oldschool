package com.codecool.shop.dao;


import com.codecool.shop.dao.implementation.database.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.database.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;


public class DataStorageFactory {

    private static DataStorage dataStorage = DataStorage.MEMORY;

    public static ProductDao productDaoFactory(){
        ProductDao result;
        switch (dataStorage) {
            case MEMORY:
                result = ProductDaoMem.getInstance();
                break;
            case DATABASE:
                result = new ProductDaoJDBC();
                break;
            default:
                result = null;
        }
        return result;
    }

    public static ProductCategoryDao productCategoryDaoFactory(){
        ProductCategoryDao result;
        switch (dataStorage) {
            case MEMORY:
                result = ProductCategoryDaoMem.getInstance();
                break;
            case DATABASE:
                result = new ProductCategoryDaoJdbc();
                break;
            default:
                result = null;
        }
        return result;
    }

    public static SupplierDao supplierDaoFactory(){
        SupplierDao result;
        switch (dataStorage) {
            case MEMORY:
                result = SupplierDaoMem.getInstance();
                break;
            case DATABASE:
                result = null; //new SupplierDaoJDBC();
                break;
            default:
                result = null;
        }
        return result;
    }

}
