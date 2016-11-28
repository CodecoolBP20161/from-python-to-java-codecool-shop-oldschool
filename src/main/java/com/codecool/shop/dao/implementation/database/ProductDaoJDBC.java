package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ProductDaoJDBC implements ProductDao {

    @Override
    public void add(Product product) {
        product.setId(UUID.randomUUID().hashCode()); // generating random id for products
        String query = "INSERT INTO products (id, " +
                                             "name, " +
                                             "description, " +
                                             "default_price, " +
                                             "default_currency, " +
                                             "product_category, " +
                                             "supplier) " +
                       "VALUES ('" + product.getId() + "', '" +
                                     product.getName() + "', '" +
                                     product.getDescription() + "', '" +
                                     product.getDefaultPrice() + "', '" +
                                     product.getDefaultCurrency() + "', '" +
                                     product.getProductCategory() + "', '" +
                                     product.getSupplier() + "');";

        DatabaseConnector.executeQuery(query);
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM products WHERE id ='" + id + "';";

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            if (resultSet.next()){
                return new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        (ProductCategory) resultSet.getObject("product_category"),
                        (Supplier) resultSet.getObject("supplier"));
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
        String query = "DELETE FROM products WHERE id = '" + id + "';";
        DatabaseConnector.executeQuery(query);
    }

    private List<Product> getProducts(String query) {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        (ProductCategory) resultSet.getObject("product_category"),
                        (Supplier) resultSet.getObject("supplier"));

                productList.add(product);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products;";
        return this.getProducts(query);
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM products JOIN suppliers ON product_categories products WHERE supplier ='" + supplier.getId() + "';";
        return this.getProducts(query);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM products JOIN suppliers ON product_categories WHERE product_category ='" + productCategory.getId() + "';";
        return this.getProducts(query);
    }
}
