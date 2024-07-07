package com.task.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDriverManager {
    private static PostgresDriverManager instance;
    private static String URL = "jdbc:postgresql://localhost:5432/TMSHomework";
    private static String USERNAME = "admin";
    private static String PASSWORD = "admin";

    private PostgresDriverManager() {
        init();
    }

    private void init() {
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PostgresDriverManager getInstance() {
        if (instance == null) {
            instance = new PostgresDriverManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}