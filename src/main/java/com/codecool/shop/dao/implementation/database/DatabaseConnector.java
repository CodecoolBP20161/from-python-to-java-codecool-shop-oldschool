package com.codecool.shop.dao.implementation.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


abstract class DatabaseConnector {

// Initialise database connection based on given configuration file
    private static final String DATABASE = DatabaseConfig.getInstance().getDATABASE();
    private static final String DB_USER = DatabaseConfig.getInstance().getDB_USER();
    private static final String DB_PASSWORD = DatabaseConfig.getInstance().getDB_PASSWORD();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }


    public static void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
