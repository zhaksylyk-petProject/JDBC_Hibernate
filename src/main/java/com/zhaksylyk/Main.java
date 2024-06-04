package com.zhaksylyk;

import com.zhaksylyk.util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Class<Driver> driverClass = Driver.class;
        try (var connection = ConnectionManager.open()) {
            System.out.println(connection.getTransactionIsolation());
        }
    }
}
