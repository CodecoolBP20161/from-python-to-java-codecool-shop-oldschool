package com.codecool.shop.model;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderTest {

    Order order = null;
    LineItem lineItem = null;
    Product product = null;
    Product product2 = null;

    @Before
    public void setUp(){
        order = new Order();
        lineItem = mock(LineItem.class);
        product = mock(Product.class);
        product2 = mock(Product.class);
    }

    @Test
    public void testAddProduct() throws Exception{
        order.addProduct(product);

        assertEquals(1,order.getLineItems().size());

        order.addProduct(product);

        assertEquals(1,order.getLineItems().size());
        assertEquals(2, order.getLineItems().get(0).getQuantity());
    }


    @Test
    public void testFind(){
        order.addProduct(product);
        when(product.getId()).thenReturn(1);
        LineItem result = new LineItem(product);
        order.find(result);
        assertEquals(result.getProduct().getId(),order.getLineItems().get(0).getProduct().getId() );

    }

    @Test
    public void testGetTotalPrice(){
        when(product.getDefaultPrice()).thenReturn(100f);
        when(product2.getDefaultPrice()).thenReturn(200f);
        when(product.getId()).thenReturn(1);
        when(product2.getId()).thenReturn(2);
        order.addProduct(product);
        order.addProduct(product);
        order.addProduct(product2);

        assertEquals(400, order.getTotalPrice());

    }

    @Test
    public void testSumLineItemQuantitiesInCart(){
        when(product.getId()).thenReturn(1);
        when(product2.getId()).thenReturn(2);
        order.addProduct(product);
        order.addProduct(product);
        order.addProduct(product2);

        assertEquals(3, order.sumLineItemQuantitiesInCart());

    }
    @After
    public void tearDown(){
        order.getLineItems().clear();
    }

}
