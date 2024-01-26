package com.nhan.quanlysinhvien.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/sms?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to the database successfully");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed to connect to the database: " + ex.getMessage());
        }
        return connection;
    }

    public static void init() {
        try {
            Connection connection = MySQLConnection.getConnection();
            Statement statement = connection.createStatement();
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "user_account", null);
            if (!tables.next()) {
                
            String[] sqlScript = new String[]{
            
                "CREATE TABLE IF NOT EXISTS loginhistory ("
                + "    sessionId INT AUTO_INCREMENT PRIMARY KEY,"
                + "    userId INT NULL,"
                + "    timeLogin DATETIME NULL"
                + ");",
                "CREATE TABLE IF NOT EXISTS student ("
                + "    studentId INT AUTO_INCREMENT PRIMARY KEY,"
                + "    name VARCHAR(255) NOT NULL,"
                + "    age INT NOT NULL,"
                + "    phoneNumber VARCHAR(20) NULL"
                + ");",
                "CREATE TABLE IF NOT EXISTS certificates ("
                + "    certificateId INT AUTO_INCREMENT PRIMARY KEY,"
                + "    studentId INT NOT NULL,"
                + "    certificateName VARCHAR(255) NOT NULL,"
                + "    issueDate DATE NOT NULL,"
                + "    validUntil DATE NOT NULL,"
                + "    CONSTRAINT certificates_ibfk_1 FOREIGN KEY (studentId) REFERENCES student (studentId)"
                + ");",
                "CREATE TABLE IF NOT EXISTS user_account ("
                + "    userId INT AUTO_INCREMENT PRIMARY KEY,"
                + "    username VARCHAR(255) NULL,"
                + "    password VARCHAR(255) NULL,"
                + "    role VARCHAR(50) NULL,"
                + "    profilePicture BLOB NULL,"
                + "    status VARCHAR(50) NULL"
                + ");",
                "INSERT INTO sms.user_account (userId, username, password, role, profilePicture, status) "
                + "VALUES (1, 'admin', 'root', 'ADMIN', NULL, 'ACTIVE');"
            };
            tables.close();
            for (String i : sqlScript) {
//                System.out.println(i);
                statement.execute(i);
            }
            System.out.println("SQL executed successfully.");
        }} catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL failed: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection has been closed");
            }
        } catch (SQLException e) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
