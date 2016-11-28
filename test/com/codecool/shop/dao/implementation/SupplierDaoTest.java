package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Supplier;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SupplierDaoTest extends DaoTest{

    @Test
    public void testAdd() throws Exception{
        supplierDao.add(supplierFirst);
        Supplier findFromDao = supplierDao.find(supplierFirst.getId());
        assertEquals(supplierFirst, findFromDao );
    }

    @Test
    public void testFindNotFound() throws Exception{
        Supplier unknown = supplierDao.find(12345);

        assertNull(unknown);

    }

    @Test
    public void testFind() throws Exception{
        supplierDao.add(supplierFirst);
        supplierDao.add(supplierSecond);

        Supplier findFromDaoFirst = supplierDao.find(supplierFirst.getId());
        Supplier findFromDaoSecond = supplierDao.find(supplierSecond.getId());

        assertEquals(supplierFirst, findFromDaoFirst);
        assertEquals(supplierSecond, findFromDaoSecond);

    }

    @Test
    public void testRemove() throws Exception{
        supplierDao.add(supplierFirst);
        supplierDao.add(supplierSecond);

        supplierDao.remove(supplierFirst.getId());

        List <Supplier> suppliers;
        suppliers = supplierDao.getAll();

        assertEquals(1, suppliers.size());
    }

    @Test
    public void testGetAll() throws Exception{
        supplierDao.add(supplierFirst);
        supplierDao.add(supplierSecond);

        List<Supplier> suppliers;
        suppliers = supplierDao.getAll();

        assertEquals(2, suppliers.size());
        assertTrue(suppliers.contains(supplierFirst));
        assertTrue(suppliers.contains(supplierSecond));

    }

}
