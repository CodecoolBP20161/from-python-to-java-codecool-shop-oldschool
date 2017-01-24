package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.DataStorageFactory;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.shop.dao.implementation.database.DatabaseConnector.getConnection;

public class LineItemDaoJDBC implements LineItemDao {
    @Override
    public void add(LineItem lineItem) {
        String query = "INSERT INTO line_items (id, " +
                "order_id, " +
                "product, " +
                "quantity, " +
                "subtotal_price) " +
                "VALUES (?, ?, ?, ?, ?);";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, lineItem.getId());
            preparedStatement.setInt(2, lineItem.getOrder());
            preparedStatement.setInt(3, lineItem.getProduct().getId());
            preparedStatement.setInt(4, lineItem.getQuantity());
            preparedStatement.setFloat(5, lineItem.getSubtotalPrice());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public LineItem find(int id) {

        String query = "SELECT * FROM line_items WHERE id =?;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

             preparedStatement.setInt(1, id);
             ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Product product = DataStorageFactory.productDaoFactory().find(resultSet.getInt("product"));
                LineItem lineItem = new LineItem(
                        product,
                        resultSet.getInt("order_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getLong("subtotal_price"));
                lineItem.setId(id);
                return lineItem;

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
        String query = "DELETE FROM line_items WHERE id = ?;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private List<LineItem> getLineItems(String query) {
        List<LineItem> lineItemList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = DataStorageFactory.productDaoFactory().find(resultSet.getInt("product"));
                LineItem lineItem = new LineItem(
                        product,
                        resultSet.getInt("order_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getLong("subtotal_price"));
                lineItem.setId(resultSet.getInt("id"));
                lineItemList.add(lineItem);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lineItemList;
    }

    @Override
    public List<LineItem> getAll() {
        String query = "SELECT * FROM line_items;";
        return this.getLineItems(query);
    }

    @Override
    public List<LineItem> getBy(Order order) {

        String query = "SELECT * FROM line_items WHERE order_id ='" + order.getId() + "';";
        return this.getLineItems(query);
    }

    @Override
    public List<LineItem> getBy(Product product) {

        String query = "SELECT * FROM line_items WHERE product ='" + product.getId() + "';";
        return this.getLineItems(query);
    }
}
