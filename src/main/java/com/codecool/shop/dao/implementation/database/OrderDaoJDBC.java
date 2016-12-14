package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.DataStorageFactory;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

        String query = "SELECT * FROM orders WHERE id ='" + id + "';";

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            if (resultSet.next()){
                Customer customer = DataStorageFactory.customerDaoFactory().find(resultSet.getInt("customer"));
                OrderStatus orderStatus =  OrderStatus.valueOf(resultSet.getString("order_status"));
                Order order = new Order(
                        resultSet.getInt("id"),
                        customer,
                        orderStatus);
                order.setId(id);
                return order;

            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM orders WHERE id = '" + id + "';";
        DatabaseConnector.executeQuery(query);

    }

    private List<Order> getOrders(String query) {
        List<Order> orderList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                Customer customer = DataStorageFactory.customerDaoFactory().find(resultSet.getInt("customer"));
                OrderStatus orderStatus =  OrderStatus.valueOf(resultSet.getString("order_status"));
                Order order = new Order(
                        resultSet.getInt("id"),
                        customer,
                        orderStatus);
                //lineItem.setId(id);
                orderList.add(order);
                return orderList;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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
