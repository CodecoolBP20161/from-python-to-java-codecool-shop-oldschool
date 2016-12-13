package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.model.Customer;

import java.util.List;

public class CustomerDaoJDBC implements CustomerDao {
    @Override
    public void add(Customer customer) {
        String query = "INSERT INTO customers (id, " +
                "name, " +
                "email, " +
                "phone, " +
                "billing_country, " +
                "billing_city, " +
                "billing_zipcode, " +
                "billing_address, " +
                "shipping_country, " +
                "shipping_city, " +
                "shipping_zipcode, " +
                "shipping_address) " +
                "VALUES (" + customer.getId() + ", '" +
                customer.getName() + "', '" +
                customer.getEmail() + "', '" +
                customer.getPhone() + "', '" +
                customer.getBillingCountry() + "', '" +
                customer.getBillingCity() + "', '" +
                customer.getBillingZipcode() + "', '" +
                customer.getBillingAddress() + "', '" +
                customer.getShippingCountry() + "', '" +
                customer.getShippingCity() + "', '" +
                customer.getShippingZipcode() + "', '" +
                customer.getShippingAddress() + "');" ;

        DatabaseConnector.executeQuery(query);
    }


    @Override
    public Customer find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Customer> getAll() {
        return null;
    }
}
