package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductCategoryDaoTest {


    ProductCategoryDao productCategoryDao;
    ProductCategory productCategory;

    @Before
    public void setUp(){
        productCategoryDao = ProductCategoryDaoMem.getInstance();
        productCategory = new ProductCategory("test", "oldschool", "first test for dao");
        System.out.println("productCategory = " + productCategory);
    }

    @Test
    public void testAdd() throws Exception{
        productCategoryDao.add(productCategory);
        ProductCategory findFromDao = productCategoryDao.find(productCategory.getId());
        assertEquals(productCategory, findFromDao);
    }

    @Test
    public void testFind() throws Exception{

    }

    @Test
    public void testRemove() throws Exception{

    }

    @Test
    public void testGetAll() throws Exception{

    }


}