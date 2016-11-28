package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoTest {

        ProductDao productDao;
        Product productFirst;
        Product productSecond;
        Supplier supplier;
        ProductCategory productCategory;

        @Before
        public void setUp(){
            productDao = ProductDaoMem.getInstance();
            productCategory = new ProductCategory("test", "oldschool", "first test for dao");
            supplier = new Supplier("codecool", "learning school");
            productFirst = new Product("daotest",49.9f, "USD", "Newbie to testing", productCategory, supplier);
            productSecond = new Product("daotest2",59.9f, "USD", "Getting start for testing", productCategory, supplier);
        }

    @Test
    public void testAdd() throws Exception{
        productDao.add(productFirst);
        Product findFromDao = productDao.find(productFirst.getId());
        assertEquals(productFirst, findFromDao );
    }

    @Test
    public void testFindNotFound() throws Exception{
        Product unknown = productDao.find(12345);

        assertNull(unknown);

    }

    @Test
    public void testFind() throws Exception{
        productDao.add(productFirst);
        productDao.add(productSecond);

        Product findFromDaoFirst = productDao.find(productFirst.getId());
        Product findFromDaoSecond = productDao.find(productSecond.getId());

        assertEquals(productFirst, findFromDaoFirst);
        assertEquals(productSecond, findFromDaoSecond);

    }

    @Test
    public void testRemove() throws Exception{

    }

    @Test
    public void testGetAll() throws Exception{
        productDao.add(productFirst);
        productDao.add(productSecond);

        List<Product> products;
        products = productDao.getAll();

        assertEquals(2, products.size());
        assertTrue(products.contains(productFirst));
        assertTrue(products.contains(productSecond));

    }

    @Test
    public void testGetBySupplier() throws Exception{

    }

    @Test
    public void testGetByProductCategory() throws Exception{

    }


}
