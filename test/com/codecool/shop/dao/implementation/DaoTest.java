package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.implementation.database.DatabaseConfig;
import com.codecool.shop.dao.implementation.database.DatabaseSwitcher;
import com.codecool.shop.dao.implementation.database.DatabaseType;
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

    DatabaseConfig db = DatabaseConfig.getInstance();

    //generate example data before tests to use them
    @Before
    public void setUp(){
        DatabaseSwitcher.getInstance().setDatabaseType(DatabaseType.TEST);
        productCategoryFirst = new ProductCategory(1111, "test", "oldschool", "first test for dao");
        productCategorySecond = new ProductCategory(1112, "test2", "oldschool2", "second test for dao");
        supplierFirst = new Supplier(1111, "codecool.bp.1", "spring class");
        supplierSecond = new Supplier(1112, "codecool.bp.2", "autumn class");
        productFirst = new Product(1111, "daotest",49.9f, "USD", "Newbie to testing", productCategoryFirst, supplierFirst);
        productSecond = new Product(1112, "daotest2",59.9f, "USD", "Getting start for testing", productCategorySecond, supplierSecond);

    }

    //after tests remove unused elements
    @After
    public void tearDown() throws Exception {
        //DatabaseSwitcher.getInstance().setDatabaseType(DatabaseType.REAL);
        productCategoryFirst = null;
        productCategorySecond = null;
        supplierFirst = null;
        supplierSecond = null;
        productFirst = null;
        productSecond = null;

    }
}
