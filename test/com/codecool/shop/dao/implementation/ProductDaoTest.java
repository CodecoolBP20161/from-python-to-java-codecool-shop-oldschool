package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import org.junit.Before;
import org.junit.Test;

public class ProductDaoTest {

        ProductDao productDao;
        Product product;
        Supplier supplier;
        ProductCategory productCategory;

        @Before
        public void setUp(){
            productCategory = new ProductCategory("test", "oldschool", "first test for dao");
            supplier = new Supplier("codecool", "learning school");
            product = new Product("daotest",49.9f, "USD", "Newbie to testing", productCategory, supplier);
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

    @Test
    public void testGetBySupplier() throws Exception{

    }

    @Test
    public void testGetByProductCategory() throws Exception{

    }


}
