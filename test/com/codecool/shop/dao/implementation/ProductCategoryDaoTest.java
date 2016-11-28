package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductCategoryDaoTest {


    ProductCategoryDao productCategoryDao;
    ProductCategory productCategoryFirst;
    ProductCategory productCategorySecond;


    @Before
    public void setUp(){
        productCategoryDao = ProductCategoryDaoMem.getInstance();
        productCategoryFirst = new ProductCategory("test", "oldschool", "first test for dao");
        productCategorySecond = new ProductCategory("test2", "oldschool2", "second test for dao");

    }

    @Test
    public void testAdd() throws Exception{
        productCategoryDao.add(productCategoryFirst);
        ProductCategory findFromDao = productCategoryDao.find(productCategoryFirst.getId());

        assertEquals(productCategoryFirst, findFromDao);
    }

    @Test
    public void testFindNotFound() throws Exception{
        ProductCategory unknown = productCategoryDao.find(12345);

        assertNull(unknown);

    }

    @Test
    public void testFind() throws Exception{
        productCategoryDao.add(productCategoryFirst);
        productCategoryDao.add(productCategorySecond);

        ProductCategory findFromDaoFirst = productCategoryDao.find(productCategoryFirst.getId());
        ProductCategory findFromDaoSecond = productCategoryDao.find(productCategorySecond.getId());

        assertEquals(productCategoryFirst, findFromDaoFirst);
        assertEquals(productCategorySecond, findFromDaoSecond);

    }

    @Test
    public void testRemove() throws Exception{

    }

    @Test
    public void testGetAll() throws Exception{
        productCategoryDao.add(productCategoryFirst);
        productCategoryDao.add(productCategorySecond);

        List <ProductCategory> productCategories;
        productCategories = productCategoryDao.getAll();

        assertEquals(2, productCategories.size());
        assertTrue(productCategories.contains(productCategoryFirst));
        assertTrue(productCategories.contains(productCategorySecond));

    }


}