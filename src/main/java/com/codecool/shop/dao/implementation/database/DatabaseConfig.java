package com.codecool.shop.dao.implementation.database;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class DatabaseConfig {

    private static DatabaseConfig instance = null;
    private DatabaseType databaseType = DatabaseType.REAL;
    private Properties prop = new Properties();
    private InputStream input = null;

    // Singleton pattern to avoid further instantiation
    public static synchronized DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    // Switch between real database and database for testing purpose
    public String databaseSwitcher() {
        String configFile = null;
        switch (databaseType) {
            case REAL:
                configFile = "connection.properties";
                break;
            case TEST:
                configFile = "connection.test.properties";
                break;
        }
        return configFile;

    }


    private void config() {
        try {
            input = new FileInputStream(databaseSwitcher());

            // load a properties file
            prop.load(input);

        } catch(IOException ex){
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    public String getDATABASE() {
        return "jdbc:postgresql://" + prop.getProperty("url") + "/" + prop.getProperty("database");
    }

    public String getDB_USER() {
        return prop.getProperty("user");
    }

    public String getDB_PASSWORD() {
        return prop.getProperty("password");
    }

}