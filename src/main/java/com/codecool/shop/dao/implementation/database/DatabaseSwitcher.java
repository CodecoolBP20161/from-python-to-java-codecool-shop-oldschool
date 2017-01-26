package com.codecool.shop.dao.implementation.database;


public class DatabaseSwitcher {

    private static DatabaseSwitcher instance = null;

    private DatabaseType databaseType = DatabaseType.REAL;

    public static synchronized DatabaseSwitcher getInstance() {
        if (instance == null) {
            instance = new DatabaseSwitcher();
        }
        return instance;
    }
    //switch between real database and database for testing purpose
    public String database() {
        String result = null;
        switch (databaseType) {
            case TEST:
                result = "src/main/resources/connection.test.properties";
                break;
            case REAL:
                result = "src/main/resources/connection.properties";
                break;
        }
        return result;
    }


    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

}




