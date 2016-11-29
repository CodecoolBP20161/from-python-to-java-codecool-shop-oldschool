package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.database.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ProductCategoryDaoTest extends DaoTest{

    private ProductCategoryDao productCategoryDao;

    public ProductCategoryDaoTest(ProductCategoryDao productCategoryDao){
        this.productCategoryDao = productCategoryDao;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(
                new Object[]{ProductCategoryDaoMem.getInstance()},
                new Object[]{new ProductCategoryDaoJDBC()}
        );
    }

    @Test
    public void testAdd() throws Exception{
        productCategoryDao.add(productCategoryFirst);
        ProductCategory findFromDao = productCategoryDao.find(productCategoryFirst.getId());
        System.out.println("find object:"+findFromDao);

        assertEquals(productCategoryFirst, findFromDao);
    }

    @Test
    public void testFindNotFound() throws Exception{
        ProductCategory unknown = productCategoryDao.find(12345);

        assertNull(unknown);

    }

    @Test
    public void testFind() throws Exception{
        productCategoryDao.add(productCategoryFirst);
        productCategoryDao.add(productCategorySecond);

        ProductCategory findFromDaoFirst = productCategoryDao.find(productCategoryFirst.getId());
        ProductCategory findFromDaoSecond = productCategoryDao.find(productCategorySecond.getId());

        assertEquals(productCategoryFirst, findFromDaoFirst);
        assertEquals(productCategorySecond, findFromDaoSecond);

    }

    @Test
    public void testRemove() throws Exception{
        productCategoryDao.add(productCategoryFirst);
        productCategoryDao.add(productCategorySecond);

        productCategoryDao.remove(productCategoryFirst.getId());

        List <ProductCategory> productCategoriesRemove;
        productCategoriesRemove = productCategoryDao.getAll();

        assertEquals(1, productCategoriesRemove.size());
    }

    @Test
    public void testGetAll() throws Exception{
        productCategoryDao.add(productCategoryFirst);
        productCategoryDao.add(productCategorySecond);

        List <ProductCategory> productCategories;
        productCategories = productCategoryDao.getAll();

        assertEquals(2, productCategories.size());
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