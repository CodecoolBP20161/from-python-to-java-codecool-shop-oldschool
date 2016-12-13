package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.List;

public class LineItemDaoJDBC implements LineItemDao {
    @Override
    public void add(LineItem lineItem) {
        String query = "INSERT INTO customers (id, " +
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
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<LineItem> getAll() {
        return null;
    }

    @Override
    public List<LineItem> getBy(Order order) {
        return null;
    }

    @Override
    public List<LineItem> getBy(Product product) {
        return null;
    }
}
