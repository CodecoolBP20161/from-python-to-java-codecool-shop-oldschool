package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.shop.dao.implementation.database.DatabaseConnector.getConnection;

public class SupplierDaoJDBC implements SupplierDao {

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO suppliers (id, " +
                "name, " +
                "description)" +
                "VALUES (?, ?, ?);";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, supplier.getId());
            preparedStatement.setString(2, supplier.getName());
            preparedStatement.setString(3,  supplier.getDescription());

            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM suppliers WHERE id ='" + id + "';";

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));

                return supplier;
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
        String query = "SELECT * FROM suppliers;";

        List<Supplier> supplierList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                int supplier_id = resultSet.getInt("id");
                Supplier supplier = new Supplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));

                supplier.setId(supplier_id);
                supplierList.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return supplierList;
    }
}
