package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.database.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.database.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.database.SupplierDaoJDBC;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ProductDaoTest extends DaoTest{

    SupplierDao supplierDao;
    ProductCategoryDao productCategoryDao;
    private ProductDao productDao;

    public ProductDaoTest(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest(){
        return Arrays.asList(
                new Object[]{ProductDaoMem.getInstance()},
                new Object[]{new ProductDaoJDBC()}
        );
    }
    @Before
    public void setUp2(){
        if ("ProductDaoJDBC".equals(productDao.getClass().getSimpleName())) {
            supplierDao = new SupplierDaoJDBC();
            productCategoryDao = new ProductCategoryDaoJDBC();

            supplierDao.add(supplierFirst);
            supplierDao.add(supplierSecond);
            productCategoryDao.add(productCategoryFirst);
            productCategoryDao.add(productCategorySecond);
        }
    }


    @Test
    public void testAdd() throws Exception{
        productDao.add(productFirst);
        Product findFromDao = productDao.find(productFirst.getId());
        assertEquals(productFirst, findFromDao );
    }

    @Test
    public void testFindNotFound() throws Exception{
        Product unknown = productDao.find(12345);

        assertNull(unknown);

    }

    @Test
    public void testFind() throws Exception{
        productDao.add(productFirst);
        productDao.add(productSecond);

        Product findFromDaoFirst = productDao.find(productFirst.getId());
        Product findFromDaoSecond = productDao.find(productSecond.getId());

        assertEquals(productFirst, findFromDaoFirst);
        assertEquals(productSecond, findFromDaoSecond);

    }

    @Test
    public void testRemove() throws Exception{
        productDao.add(productFirst);
        productDao.add(productSecond);

        productDao.remove(productFirst.getId());

        List <Product> products;
        products = productDao.getAll();

        assertEquals(1, products.size());
    }

    @Test
    public void testGetAll() throws Exception{
        productDao.add(productFirst);
        productDao.add(productSecond);

        List<Product> products;
        products = productDao.getAll();

        //assertEquals(2, products.size());
        assertTrue(products.contains(productFirst));
        assertTrue(products.contains(productSecond));

    }

    @Test
    public void testGetBySupplier() throws Exception{
        productDao.add(productFirst);
        productDao.add(productSecond);

        List<Product> productBySupplier = productDao.getBy(supplierFirst);

        assertEquals(productFirst, productBySupplier.get(0));

    }

    @Test
    public void testGetByProductCategory() throws Exception{
        productDao.add(productFirst);
        productDao.add(productSecond);

        List<Product> productByCategory = productDao.getBy(productCategorySecond);

        assertEquals(productSecond, productByCategory.get(0));

    }

    @Override
    @After
    public void tearDown(){
        if ("ProductDaoMem".equals(productDao.getClass().getSimpleName())) {
            productDao.getAll().clear();
        }
        if ("ProductDaoJDBC".equals(productDao.getClass().getSimpleName())) {
            productDao.remove(productFirst.getId());
            productDao.remove(productSecond.getId());
            supplierDao.remove(supplierFirst.getId());
            supplierDao.remove(supplierSecond.getId());
            productCategoryDao.remove(productCategoryFirst.getId());
            productCategoryDao.remove(productCategorySecond.getId());
        }
    }


}
