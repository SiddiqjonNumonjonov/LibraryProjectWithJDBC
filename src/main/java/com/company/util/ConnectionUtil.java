package com.company.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection() {
        try {
            return DriverManager.
                    getConnection("jdbc:postgresql://localhost:5432/LibraryProject",
                            "userjon", "12345"); // <2>
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
