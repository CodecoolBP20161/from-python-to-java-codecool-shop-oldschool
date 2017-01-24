package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.DataStorageFactory;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.shop.dao.implementation.database.DatabaseConnector.getConnection;


public class ProductDaoJDBC implements ProductDao {

    @Override
    public void add(Product product) {
        String query = "INSERT INTO products (id, " +
                "name, " +
                "description, " +
                "default_price, " +
                "default_currency, " +
                "product_category, " +
                "supplier) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3,  product.getDescription());
            preparedStatement.setFloat(4,  product.getDefaultPrice());
            preparedStatement.setString(5,  product.getDefaultCurrency().toString());
            preparedStatement.setInt(6,  product.getProductCategory().getId());
            preparedStatement.setInt(7,  product.getSupplier().getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM products WHERE id =?;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ProductCategory productCategory = DataStorageFactory.productCategoryDaoFactory().find(resultSet.getInt("product_category"));
                Supplier supplier = DataStorageFactory.supplierDaoFactory().find(resultSet.getInt("supplier"));

                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        productCategory,
                        supplier);
                product.setId(id);

                return product;

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
        String query = "DELETE FROM products WHERE id = ?;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Product> getProducts(String query) {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductCategory productCategory = DataStorageFactory.productCategoryDaoFactory().find(resultSet.getInt("product_category"));
                Supplier supplier = DataStorageFactory.supplierDaoFactory().find(resultSet.getInt("supplier"));
                int product_id = resultSet.getInt("id");

                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        productCategory,
                        supplier);

                product.setId(product_id);
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
        String query = "SELECT * FROM products WHERE supplier ='" + supplier.getId() + "';";
        return this.getProducts(query);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM products WHERE product_category ='" + productCategory.getId() + "';";
        return this.getProducts(query);
    }
}
