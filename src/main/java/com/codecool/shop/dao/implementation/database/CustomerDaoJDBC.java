package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.shop.dao.implementation.database.DatabaseConnector.getConnection;

public class CustomerDaoJDBC implements CustomerDao {
    @Override
    public void add(Customer customer) {
//        String query = "INSERT INTO customers (id, " +
//                "name, " +
//                "email, " +
//                "phone, " +
//                "billing_country, " +
//                "billing_city, " +
//                "billing_zipcode, " +
//                "billing_address, " +
//                "shipping_country, " +
//                "shipping_city, " +
//                "shipping_zipcode, " +
//                "shipping_address) " +
//                "VALUES (" + customer.getId() + ", '" +
//                customer.getName() + "', '" +
//                customer.getEmail() + "', '" +
//                customer.getPhone() + "', '" +
//                customer.getBillingCountry() + "', '" +
//                customer.getBillingCity() + "', '" +
//                customer.getBillingZipcode() + "', '" +
//                customer.getBillingAddress() + "', '" +
//                customer.getShippingCountry() + "', '" +
//                customer.getShippingCity() + "', '" +
//                customer.getShippingZipcode() + "', '" +
//                customer.getShippingAddress() + "');" ;
//
//        DatabaseConnector.executeQuery(query);
//    }
        String query =  "INSERT INTO customers (id, name, email, phone, billing_country, billing_city, billing_zipcode," +
                        " billing_address, shipping_country, shipping_city, shipping_zipcode, shipping_address) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setString(5, customer.getBillingCountry());
            preparedStatement.setString(6, customer.getBillingCity());
            preparedStatement.setString(7, customer.getBillingZipcode());
            preparedStatement.setString(8, customer.getBillingAddress());
            preparedStatement.setString(9, customer.getShippingCountry());
            preparedStatement.setString(10, customer.getShippingCity());
            preparedStatement.setString(11, customer.getShippingZipcode());
            preparedStatement.setString(12, customer.getShippingAddress());
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Customer find(int id) {

        String query = "SELECT * FROM customers WHERE id ='" + id + "';";

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            if (resultSet.next()){
                Customer customer = new Customer(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("billing_country"),
                        resultSet.getString("billing_city"),
                        resultSet.getString("billing_zipcode"),
                        resultSet.getString("billing_address"),
                        resultSet.getString("shipping_country"),
                        resultSet.getString("shipping_city"),
                        resultSet.getString("shipping_zipcode"),
                        resultSet.getString("shipping_address"));
                customer.setId(id);
                return customer;

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
        String query = "DELETE FROM customers WHERE id = '" + id + "';";
        DatabaseConnector.executeQuery(query);

    }

    @Override
    public List<Customer> getAll() {

        String query = "SELECT * FROM customers;";

        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                int customer_id = resultSet.getInt("id");
                Customer customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("billing_country"),
                        resultSet.getString("billing_city"),
                        resultSet.getString("billing_zipcode"),
                        resultSet.getString("billing_address"),
                        resultSet.getString("shipping_country"),
                        resultSet.getString("shipping_city"),
                        resultSet.getString("shipping_zipcode"),
                        resultSet.getString("shipping_address"));

                customer.setId(customer_id);
                customers.add(customer);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
