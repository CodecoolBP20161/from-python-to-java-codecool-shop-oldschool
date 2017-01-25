package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.shop.dao.implementation.database.DatabaseConnector.getConnection;

public class OrderDaoJDBC implements OrderDao {
    @Override
    public void add(Order order) {
        String query = "INSERT INTO orders (id, " +
                "order_status, " +
                "customer) " +
                "VALUES (?, ?, ?);";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setObject(2, order.getOrderStatus());
            preparedStatement.setInt(3,  order.getCustomer().getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void setOrderStatus(int id, OrderStatus orderStatus) {
        String query = "UPDATE orders " +
                "SET order_status =?" +
                "WHERE id =?;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, orderStatus.toString());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Order find(int id) {

        String query = "SELECT * FROM orders WHERE id =?;";
        CustomerDao customerDao = new CustomerDaoJDBC();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
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
        String query = "DELETE FROM orders WHERE id = ?;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void setResults(List<Order> orderList, CustomerDao customerDao, ResultSet resultSet) throws SQLException {
        Customer customer = customerDao.find(resultSet.getInt("customer"));
        OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
        Order order = new Order(
                customer,
                orderStatus);
        order.setId(resultSet.getInt("id"));
        orderList.add(order);
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders;";
        List<Order> orderList = new ArrayList<>();
        CustomerDao customerDao = new CustomerDaoJDBC();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setResults(orderList, customerDao, resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public List<Order> getBy(Customer customer) {
        String query = "SELECT * FROM orders WHERE customer =?;";

        List<Order> orderList = new ArrayList<>();
        CustomerDao customerDao = new CustomerDaoJDBC();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, customer.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setResults(orderList, customerDao, resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public List<Order> getBy(OrderStatus orderStatus) {
        String query = "SELECT * FROM orders WHERE order_status =?;";

        List<Order> orderList = new ArrayList<>();
        CustomerDao customerDao = new CustomerDaoJDBC();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, orderStatus.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setResults(orderList, customerDao, resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

}
