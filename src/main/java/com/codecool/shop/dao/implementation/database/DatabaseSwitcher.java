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
                result = "jdbc:postgresql://localhost:5432/codecoolshoptest";
                break;
            case REAL:
                result = "jdbc:postgresql://localhost:5432/codecoolshop";
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




