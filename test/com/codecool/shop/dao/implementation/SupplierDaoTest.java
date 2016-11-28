package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.Before;

public class SupplierDaoTest {

    SupplierDao supplierDao;
    Supplier supplier;

    @Before
    public void setUp(){
        supplier = new Supplier("codecool", "learning school");
    }

}
