package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.Before;

public class ProductCategoryDaoTest {


    ProductCategoryDao productCategoryDao;
    ProductCategory productCategory;

    @Before
    public void setUp(){
        productCategory = new ProductCategory("test", "oldschool", "first test for dao");
    }

}