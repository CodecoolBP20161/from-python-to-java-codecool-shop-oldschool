package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderStatus;

import java.util.List;

public class OrderDaoJDBC implements OrderDao {
    @Override
    public void add(Order order) {
        String query = "INSERT INTO orders (id, " +
                "order_status, " +
                "customer) " +
                "VALUES (" + order.getId() + ", '" +
                order.getOrderStatus() + "', '" +
                order.getCustomer().getId() + "');";
        DatabaseConnector.executeQuery(query);

    }



    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public List<Order> getBy(Customer customer) {
        return null;
    }

    @Override
    public List<Order> getBy(OrderStatus orderStatus) {
        return null;
    }
}
