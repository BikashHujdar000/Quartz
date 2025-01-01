//package com.example.bikash.Optimized;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class TestConnection {
//    public static void main(String[] args) {
//        String url = "jdbc:sqlserver://103.94.159.179:1433;databaseName=test_qt;encrypt=true;trustServerCertificate=true;";
//        String username = "sa";
//        String password = "_SBD@t@S0lution12!@";
//
//        try (Connection conn = DriverManager.getConnection(url, username, password)) {
//            System.out.println("Connected to the database!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}