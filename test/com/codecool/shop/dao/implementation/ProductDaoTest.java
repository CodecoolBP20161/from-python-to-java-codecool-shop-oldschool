package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import org.junit.Before;

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
}
