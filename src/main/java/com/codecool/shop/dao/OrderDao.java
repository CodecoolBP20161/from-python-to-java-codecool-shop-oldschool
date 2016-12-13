package com.codecool.shop.dao;


import com.codecool.shop.model.Order;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.OrderStatus;

import java.util.List;

public interface OrderDao {

    void add(Order order);
    Order find(int id);
    void remove(int id);

    List<Order> getAll();
    List<Order> getBy(Customer customer);
    List<Order> getBy(OrderStatus orderStatus);
}
