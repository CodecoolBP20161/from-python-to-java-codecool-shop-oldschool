package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


public abstract class DaoTest {

    ProductDao productDao;
    ProductCategoryDao productCategoryDao;
    SupplierDao supplierDao;

    Product productFirst;
    Product productSecond;

    Supplier supplierFirst;
    Supplier supplierSecond;

    ProductCategory productCategoryFirst;
    ProductCategory productCategorySecond;



    @Before
    public void setUp(){
//        productDao = ProductDaoMem.getInstance();
//        productCategoryDao = ProductCategoryDaoMem.getInstance();
//        supplierDao = SupplierDaoMem.getInstance();

        productCategoryFirst = new ProductCategory("test", "oldschool", "first test for dao");
        productCategorySecond = new ProductCategory("test2", "oldschool2", "second test for dao");
        supplierFirst = new Supplier("codecool.bp.1", "spring class");
        supplierSecond = new Supplier("codecool.bp.2", "autumn class");
        productFirst = new Product("daotest",49.9f, "USD", "Newbie to testing", productCategoryFirst, supplierFirst);
        productSecond = new Product("daotest2",59.9f, "USD", "Getting start for testing", productCategorySecond, supplierSecond);
    }

    @After
    public void tearDown() throws Exception {
        productCategoryFirst = null;
        productCategorySecond = null;
        supplierFirst = null;
        supplierSecond = null;
        productFirst = null;
        productSecond = null;

        productDao.getAll().clear();
        productCategoryDao.getAll().clear();
        supplierDao.getAll().clear();

    }
}
