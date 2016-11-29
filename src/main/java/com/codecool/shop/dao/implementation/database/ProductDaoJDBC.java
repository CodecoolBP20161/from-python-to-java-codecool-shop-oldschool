package com.codecool.shop.dao.implementation.database;


import com.codecool.shop.dao.DataStorageFactory;
import com.codecool.shop.dao.ProductCategoryDao;
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

import static java.lang.Math.abs;


public class ProductDaoJDBC implements ProductDao {

    @Override
    public void add(Product product) {
        product.setId(abs(UUID.randomUUID().hashCode())); // generating random id for products
        String query = "INSERT INTO products (id, " +
                                             "name, " +
                                             "description, " +
                                             "default_price, " +
                                             "default_currency, " +
                                             "product_category, " +
                                             "supplier) " +
                       "VALUES (" + product.getId() + ", '" +
                                     product.getName() + "', '" +
                                     product.getDescription() + "', " +
                                     product.getDefaultPrice() + ", '" +
                                     product.getDefaultCurrency() + "', " +
                                     product.getProductCategory().getId() + ", " +
                                     product.getSupplier().getId() + ");";
        System.out.println(query);
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
        //String query = "SELECT * FROM products RIGHT JOIN suppliers ON products.supplier=suppliers.id WHERE suppliers.id='" + supplier.getId() + "';";
        String query = "SELECT * FROM products WHERE supplier ='" + supplier.getId() + "';";
        return this.getProducts(query);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        //String query = "SELECT * FROM products RIGHT JOIN product_categories ON products.product_category=product_categories.id WHERE product_categories.id='" + productCategory.getId() + "';";
        String query = "SELECT * FROM products WHERE product_category ='" + productCategory.getId() + "';";
        return this.getProducts(query);
    }
}
