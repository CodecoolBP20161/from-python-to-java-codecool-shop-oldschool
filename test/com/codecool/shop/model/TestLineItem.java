package com.codecool.shop.model;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;


public class TestLineItem {
    LineItem lineItem;
    Product mockProduct;
    double DELTA = 1e-15;

    @Before
    public void setUp() {
        mockProduct = mock(Product.class);
        lineItem = new LineItem(mockProduct);
        when(mockProduct.getDefaultPrice()).thenReturn(100f);
    }

    @Test
    public void testIncreaseQuantity() throws Exception {
        lineItem.increaseQuantity();
        assertEquals(2, lineItem.getQuantity());
        assertEquals(200f, lineItem.getSubtotalPrice(), DELTA);
    }

}
