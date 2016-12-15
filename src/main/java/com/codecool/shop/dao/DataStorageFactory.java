package com.codecool.shop.dao;


import com.codecool.shop.dao.implementation.database.*;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;


public class DataStorageFactory {

    private static DataStorage dataStorage = DataStorage.DATABASE;

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
                result = new ProductCategoryDaoJDBC();
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
                result = new SupplierDaoJDBC();
                break;
            default:
                result = null;
        }
        return result;
    }

    public static CustomerDao customerDaoFactory()  {
        CustomerDao result;
        switch (dataStorage) {
//            case MEMORY:
//                result = throws new NotImplementedException();
//                break;
            case DATABASE:
                result = new CustomerDaoJDBC();
                break;
            default:
                result = null;
        }
        return result;
    }

    public static OrderDao orderDaoFactory()  {
        OrderDao result;
        switch (dataStorage) {
//            case MEMORY:
//                result = throws new NotImplementedException();
//                break;
            case DATABASE:
                result = new OrderDaoJDBC();
                break;
            default:
                result = null;
        }
        return result;
    }

    public static LineItemDao lineItemDaoFactory()  {
        LineItemDao result;
        switch (dataStorage) {
//            case MEMORY:
//                result = throws new NotImplementedException();
//                break;
            case DATABASE:
                result = new LineItemDaoJDBC();
                break;
            default:
                result = null;
        }
        return result;
    }


}
