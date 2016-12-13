package com.codecool.shop.model;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderTest {

    Cart cart = null;
    LineItem lineItem = null;
    Product product = null;
    Product product2 = null;

    @Before
    public void setUp(){
        cart = new Cart();
        lineItem = mock(LineItem.class);
        product = mock(Product.class);
        product2 = mock(Product.class);
    }

    @Test
    public void testAddProduct() throws Exception{
        cart.addProduct(product);

        assertEquals(1, cart.getLineItems().size());

        cart.addProduct(product);

        assertEquals(1, cart.getLineItems().size());
        assertEquals(2, cart.getLineItems().get(0).getQuantity());
    }


    @Test
    public void testFind(){
        cart.addProduct(product);
        when(product.getId()).thenReturn(1);
        LineItem result = new LineItem(product);
        cart.find(result);
        assertEquals(result.getProduct().getId(), cart.getLineItems().get(0).getProduct().getId() );

    }

    @Test
    public void testGetTotalPrice(){
        when(product.getDefaultPrice()).thenReturn(100f);
        when(product2.getDefaultPrice()).thenReturn(200f);
        when(product.getId()).thenReturn(1);
        when(product2.getId()).thenReturn(2);
        cart.addProduct(product);
        cart.addProduct(product);
        cart.addProduct(product2);

        assertEquals(400, cart.getTotalPrice());

    }

    @Test
    public void testSumLineItemQuantitiesInCart(){
        when(product.getId()).thenReturn(1);
        when(product2.getId()).thenReturn(2);
        cart.addProduct(product);
        cart.addProduct(product);
        cart.addProduct(product2);

        assertEquals(3, cart.sumLineItemQuantitiesInCart());

    }
    @After
    public void tearDown(){
        cart.getLineItems().clear();
    }

}
