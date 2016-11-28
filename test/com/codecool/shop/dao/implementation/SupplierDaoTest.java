package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SupplierDaoTest {

    SupplierDao supplierDao;
    Supplier supplier;

    @Before
    public void setUp(){
        supplierDao = SupplierDaoMem.getInstance();
        supplier = new Supplier("codecool", "learning school");
    }

    @Test
    public void testAdd() throws Exception{
        supplierDao.add(supplier);
        Supplier findFromDao = supplierDao.find(supplier.getId());
        assertEquals(supplier, findFromDao );
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
