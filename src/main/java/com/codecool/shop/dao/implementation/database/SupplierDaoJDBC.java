package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SupplierDaoJDBC implements SupplierDao {

    @Override
    public void add(Supplier supplier) {
        supplier.setId(UUID.randomUUID().hashCode()); // generating random id for products
        String query = "INSERT INTO suppliers (id, " +
                "name, " +
                "description, " +
                "VALUES ('" + supplier.getId() + "', '" +
                supplier.getName() + "', '" +
                supplier.getDescription() + "', '";

        DatabaseConnector.executeQuery(query);
    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM suppliers WHERE id ='" + id + "';";

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                return new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description"));
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
        String query = "DELETE FROM suppliers WHERE id = '" + id + "';";
        DatabaseConnector.executeQuery(query);
    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM products;";

        List<Supplier> supplierList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description"));

                supplierList.add(supplier);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return supplierList;
    }
}