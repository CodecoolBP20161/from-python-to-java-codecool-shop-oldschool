package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {

    @Override
    public void add(ProductCategory category) {
        String query = "INSERT INTO product_categories (id, " +
                "name, " +
                "department, " +
                "description) " +
                "VALUES (" + category.getId() + ", '" +
                category.getName() + "', '" +
                category.getDepartment() + "', '" +
                category.getDescription() + "');";

        DatabaseConnector.executeQuery(query);
    }

    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM product_categories WHERE id ='" + id + "';";

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));

                productCategory.setId(id);
                return productCategory;

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
        String query = "DELETE FROM product_categories WHERE id = '" + id + "';";
        DatabaseConnector.executeQuery(query);
    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * FROM product_categories;";

        List<ProductCategory> productCategoryList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                int productCategory_id = resultSet.getInt("id");
                ProductCategory productCategory = new ProductCategory(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));

                productCategory.setId(productCategory_id);
                productCategoryList.add(productCategory);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productCategoryList;
    }

}
