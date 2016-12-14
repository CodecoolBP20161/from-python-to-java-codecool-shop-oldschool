package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.DataStorageFactory;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LineItemDaoJDBC implements LineItemDao {
    @Override
    public void add(LineItem lineItem) {
        String query = "INSERT INTO line_items (id, " +
                "order_id, " +
                "product, " +
                "quantity, " +
                "subtotal_price) " +
                "VALUES (" + lineItem.getId() + ", '" +
                lineItem.getOrder().getId() + "', '" +
                lineItem.getProduct().getId() + "', '" +
                lineItem.getQuantity() + "', '" +
                lineItem.getSubtotalPrice() + "');";
        DatabaseConnector.executeQuery(query);
    }


    @Override
    public LineItem find(int id) {

        String query = "SELECT * FROM line_items WHERE id ='" + id + "';";

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            if (resultSet.next()){
                Order order = DataStorageFactory.orderDaoFactory().find(resultSet.getInt("order_id"));
                Product product = DataStorageFactory.productDaoFactory().find(resultSet.getInt("product"));
                LineItem lineItem = new LineItem(
                        resultSet.getInt("id"),
                        product,
                        order,
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
        String query = "DELETE FROM line_items WHERE id = '" + id + "';";
        DatabaseConnector.executeQuery(query);

    }

    private List<LineItem> getLineItems(String query) {
        List<LineItem> lineItemList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                //int customer_id = resultSet.getInt("id");
                Order order = DataStorageFactory.orderDaoFactory().find(resultSet.getInt("order_id"));
                Product product = DataStorageFactory.productDaoFactory().find(resultSet.getInt("product"));
                LineItem lineItem = new LineItem(
                        resultSet.getInt("id"),
                        product,
                        order,
                        resultSet.getInt("quantity"),
                        resultSet.getLong("subtotal_price"));
                //lineItem.setId(id);
                lineItemList.add(lineItem);
                return lineItemList;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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
