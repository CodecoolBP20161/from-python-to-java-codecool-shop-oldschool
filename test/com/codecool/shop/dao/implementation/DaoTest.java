package com.codecool.shop.dao.implementation;


import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;

public abstract class DaoTest {

    Product productFirst;
    Product productSecond;

    Supplier supplierFirst;
    Supplier supplierSecond;

    ProductCategory productCategoryFirst;
    ProductCategory productCategorySecond;

    @Before
    public void setUp(){
        productCategoryFirst = new ProductCategory(1, "test", "oldschool", "first test for dao");
        productCategorySecond = new ProductCategory(2, "test2", "oldschool2", "second test for dao");
        supplierFirst = new Supplier(1, "codecool.bp.1", "spring class");
        supplierSecond = new Supplier(2, "codecool.bp.2", "autumn class");
        productFirst = new Product(1, "daotest",49.9f, "USD", "Newbie to testing", productCategoryFirst, supplierFirst);
        productSecond = new Product(2, "daotest2",59.9f, "USD", "Getting start for testing", productCategorySecond, supplierSecond);
    }

    @After
    public void tearDown() throws Exception {
        productCategoryFirst = null;
        productCategorySecond = null;
        supplierFirst = null;
        supplierSecond = null;
        productFirst = null;
        productSecond = null;

    }
}
