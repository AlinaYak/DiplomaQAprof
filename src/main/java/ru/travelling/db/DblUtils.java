package ru.travelling.db;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class DblUtils {
    @SneakyThrows
    private static Connection getConnection() {
        String dbUrl = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        final Connection connection = DriverManager.getConnection(dbUrl, login, password);
        return connection;
    }

    @SneakyThrows
    private static String getPaymentId() {
        String payment_id = null;
        var idSQL = "SELECT payment_id FROM order_entity order by created desc;";
        try (var connection = getConnection();
             var statusStatement = connection.prepareStatement(idSQL)) {
            try (var resultSet = statusStatement.executeQuery()) {
                if (resultSet.next()) {
                    payment_id = resultSet.getString("payment_id");
                }
            }
        }
        return payment_id;
    }

    @SneakyThrows
    public static String getStatusForPayment() {
        String statusSQL = "SELECT status FROM payment_entity WHERE transaction_id =?; ";
        String status = null;
        try (var connection = getConnection();
             var statusStatement = connection.prepareStatement(statusSQL)) {
            statusStatement.setString(1, getPaymentId());
            try (var resultSet = statusStatement.executeQuery()) {
                if (resultSet.next()) {
                    status = resultSet.getString("status");
                }
            }
        }
        return status;
    }

    @SneakyThrows
    public static String getStatusForCredit() {
        String statusSQL = "SELECT status FROM credit_request_entity WHERE bank_id =?; ";
        String status = null;
        try (var connection = getConnection();
             var statusStatement = connection.prepareStatement(statusSQL)) {
            statusStatement.setString(1, getPaymentId());
            try (var resultSet = statusStatement.executeQuery()) {
                if (resultSet.next()) {
                    status = resultSet.getString("status");
                }
            }
        }
        return status;
    }
}
