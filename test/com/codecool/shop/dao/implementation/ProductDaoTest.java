package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoTest {

    ProductDao productDao;
    Product productFirst;
    Product productSecond;
    Supplier supplierFirst;
    Supplier supplierSecond;
    ProductCategory productCategoryFirst;
    ProductCategory productCategorySecond;

    @Before
    public void setUp(){
        productDao = ProductDaoMem.getInstance();
        productCategoryFirst = new ProductCategory("test", "oldschool", "first test for dao");
        productCategorySecond = new ProductCategory("test2", "oldschool2", "second test for dao");
        supplierFirst = new Supplier("codecool.bp.1", "spring class");
        supplierSecond = new Supplier("codecool.bp.2", "autumn class");
        productFirst = new Product("daotest",49.9f, "USD", "Newbie to testing", productCategoryFirst, supplierFirst);
        productSecond = new Product("daotest2",59.9f, "USD", "Getting start for testing", productCategorySecond, supplierSecond);
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

        assertEquals(2, products.size());
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


}
