package org.hbdev.ecommerce.dao;

import lombok.Getter;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connection using Pattern Design Singleton
 * - Private constructor
 * - Static Variable
 * - Static Function will initialize the variable if not exist in memory
 */
@Log

public class DatabaseConnection {
    private static DatabaseConnection instance;
    @Getter
    private static Connection connection;

    private DatabaseConnection() {
        try {
            log.info("Connecting to database...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_db", "digi_user", "DIGI_$$pass$$2024");
            log.info("Connected to database Successfully!");
        } catch (ClassNotFoundException e) {
            log.warning("Driver not found");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            log.warning("Could not connect to database");
            throw new RuntimeException(e);
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

}
