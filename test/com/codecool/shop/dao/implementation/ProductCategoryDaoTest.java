package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.Before;
import org.junit.Test;

public class ProductCategoryDaoTest {


    ProductCategoryDao productCategoryDao;
    ProductCategory productCategory;

    @Before
    public void setUp(){
        productCategory = new ProductCategory("test", "oldschool", "first test for dao");
    }

    @Test
    public void testAdd() throws Exception{

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