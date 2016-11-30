package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.database.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

//parametrized test for DB and Memory use too
@RunWith(Parameterized.class)
public class ProductCategoryDaoTest extends DaoTest{

    private ProductCategoryDao productCategoryDao;

    public ProductCategoryDaoTest(ProductCategoryDao productCategoryDao){
        this.productCategoryDao = productCategoryDao;
    }
    //instances to test both db and memory implementations
    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(
                new Object[]{ProductCategoryDaoMem.getInstance()},
                new Object[]{new ProductCategoryDaoJDBC()}
        );
    }

    @Before
    public void setUp2(){
        productCategoryDao.add(productCategoryFirst);
        productCategoryDao.add(productCategorySecond);
    }

    @Test
    public void testAdd() throws Exception{
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
        ProductCategory findFromDaoFirst = productCategoryDao.find(productCategoryFirst.getId());
        ProductCategory findFromDaoSecond = productCategoryDao.find(productCategorySecond.getId());

        assertEquals(productCategoryFirst, findFromDaoFirst);
        assertEquals(productCategorySecond, findFromDaoSecond);

    }

    @Test
    public void testRemove() throws Exception{
        assertNotNull(productCategoryDao.find(productCategoryFirst.getId()));

        productCategoryDao.remove(productCategoryFirst.getId());

        assertNull(productCategoryDao.find(productCategoryFirst.getId()));

    }

    @Test
    public void testGetAll() throws Exception{
        List <ProductCategory> productCategories;
        productCategories = productCategoryDao.getAll();

        assertTrue(productCategories.contains(productCategoryFirst));
        assertTrue(productCategories.contains(productCategorySecond));

    }

    @Override
    @After
    public void tearDown(){
        if ("ProductCategoryDaoMem".equals(productCategoryDao.getClass().getSimpleName())) {
            productCategoryDao.getAll().clear();
        }
        if ("ProductCategoryDaoJDBC".equals(productCategoryDao.getClass().getSimpleName())) {
            productCategoryDao.remove(productCategoryFirst.getId());
            productCategoryDao.remove(productCategorySecond.getId());
        }
    }



}