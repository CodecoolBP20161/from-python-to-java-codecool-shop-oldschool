package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.CustomerDao;
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


    public void setOrderStatus(int id, OrderStatus orderStatus) {
        String query = "UPDATE orders " +
                "SET order_status ='" + orderStatus + "'" +
                "WHERE id ='" + id + "';";
        DatabaseConnector.executeQuery(query);
    }


    @Override
    public Order find(int id) {

        String query = "SELECT * FROM orders WHERE id ='" + id + "';";
        CustomerDao customerDao = new CustomerDaoJDBC();

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                Customer customer = customerDao.find(resultSet.getInt("customer"));
                OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
                Order order = new Order(
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
        CustomerDao customerDao = new CustomerDaoJDBC();

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                Customer customer = customerDao.find(resultSet.getInt("customer"));
                OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
                Order order = new Order(
                        customer,
                        orderStatus);
                order.setId(resultSet.getInt("id"));
                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders;";
        return this.getOrders(query);
    }

    @Override
    public List<Order> getBy(Customer customer) {

        String query = "SELECT * FROM orders WHERE customer ='" + customer.getId() + "';";
        return this.getOrders(query);
    }

    @Override
    public List<Order> getBy(OrderStatus orderStatus) {
        //// FIXME: 2016.12.14. orderstatus is cool like this is it works????
        String query = "SELECT * FROM orders WHERE order_status ='" + orderStatus + "';";
        return this.getOrders(query);
    }

}