/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhan.quanlysinhvien.dao;

import com.nhan.quanlysinhvien.model.LoginHistory;
import com.nhan.quanlysinhvien.model.LoginHistoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huunh
 */
public class UserHistoryDAO {

    private final Connection connection;

    public UserHistoryDAO() {
        Connection connectionDB = MySQLConnection.getConnection();
        this.connection = connectionDB;
    }

    public void addUserLogin(LoginHistory loginHistory) {
        String query = "INSERT INTO loginHistory (userId, timeLogin) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, loginHistory.getUserId());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<LoginHistoryDTO> getAllLoginHistory() {
        List<LoginHistoryDTO> loginList = new ArrayList<>();
        String sql = "SELECT * FROM loginHistory;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LoginHistoryDTO loginHistory = new LoginHistoryDTO();
                loginHistory.setUserId(resultSet.getInt("userId"));
                loginHistory.setSessionId(resultSet.getInt("sessionId"));
                Timestamp timestamp = resultSet.getTimestamp("timeLogin");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String formattedTime = dateFormat.format(timestamp);

                loginHistory.setTimeLogin(formattedTime);
                loginList.add(loginHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginList;
    }
}
