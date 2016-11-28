package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.ProductCategory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductCategoryDaoTest extends DaoTest{


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
        productCategoryDao.add(productCategoryFirst);
        productCategoryDao.add(productCategorySecond);

        productCategoryDao.remove(productCategoryFirst.getId());

        List <ProductCategory> productCategories;
        productCategories = productCategoryDao.getAll();

        assertEquals(1, productCategories.size());
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