package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.database.SupplierDaoJDBC;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SupplierDaoTest extends DaoTest{

    private SupplierDao supplierDao;

    public SupplierDaoTest(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest(){
        return Arrays.asList(
                new Object[]{SupplierDaoMem.getInstance()},
                new Object[]{new SupplierDaoJDBC()}
        );
    }

    @Before
    public void setUp2(){
        supplierDao.add(supplierFirst);
        supplierDao.add(supplierSecond);
    }

    @Test
    public void testAdd() throws Exception{
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
        Supplier findFromDaoFirst = supplierDao.find(supplierFirst.getId());
        Supplier findFromDaoSecond = supplierDao.find(supplierSecond.getId());

        assertEquals(supplierFirst, findFromDaoFirst);
        assertEquals(supplierSecond, findFromDaoSecond);
    }

    @Test
    public void testRemove() throws Exception{
        assertNotNull(supplierDao.find(supplierFirst.getId()));

        supplierDao.remove(supplierFirst.getId());
        assertNull(supplierDao.find(supplierFirst.getId()));
    }

    @Test
    public void testGetAll() throws Exception{
        List<Supplier> suppliers;
        suppliers = supplierDao.getAll();

        assertTrue(suppliers.contains(supplierFirst));
        assertTrue(suppliers.contains(supplierSecond));
    }

    @Override
    @After
    public void tearDown(){
        if ("SupplierDaoMem".equals(supplierDao.getClass().getSimpleName())) {
            supplierDao.getAll().clear();
        }
        if ("SupplierDaoJDBC".equals(supplierDao.getClass().getSimpleName())) {
            supplierDao.remove(supplierFirst.getId());
            supplierDao.remove(supplierSecond.getId());
        }
    }

}
