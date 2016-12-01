package com.codecool.shop.dao.implementation.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    private Properties prop = new Properties();
    private InputStream input = null;

    public DatabaseConfig() {
        this.config();
    }

    private void config() {
        try {
            input = new FileInputStream(DatabaseSwitcher.getInstance().database());

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

    public String getDatabase() {
        return "jdbc:postgresql://" + prop.getProperty("url") + "/" + prop.getProperty("database");
    }

    public String getDbUser() {
        return prop.getProperty("user");
    }

    public String getDbPassword() {
        return prop.getProperty("password");
    }

}