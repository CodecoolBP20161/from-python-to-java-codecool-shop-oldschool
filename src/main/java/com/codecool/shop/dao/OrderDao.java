package com.codecool.shop.dao;


import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Customer;

import java.util.List;

public interface OrderDao {

    void add(Cart order);
    Cart find(int id);
    void remove(int id);

    List<Cart> getAll();
    List<Cart> getBy(Customer customer);
}
