package com.codecool.shop.dao.implementation.database;


public class DatabaseSwitcher {

    private static DatabaseSwitcher instance = null;

    private DatabaseType databaseType = DatabaseType.REAL;

    //singleton pattern
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

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

}




