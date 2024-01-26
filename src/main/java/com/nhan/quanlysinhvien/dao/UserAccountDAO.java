/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhan.quanlysinhvien.dao;

/**
 *
 * @author huunh
 */
import com.nhan.quanlysinhvien.model.UserAccount;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

public class UserAccountDAO {

    private final Connection connection;

    public UserAccountDAO() {
        Connection connectionDB = MySQLConnection.getConnection();
        this.connection = connectionDB;
    }

    public UserAccount getUserById(int userId) throws IOException {
        UserAccount user = null;
        String sql = "SELECT * FROM user_account WHERE userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new UserAccount();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setUserPassword(resultSet.getString("password"));
                // Sử dụng phương thức valueOf để chuyển đổi chuỗi thành enum
                user.setRole(UserRole.valueOf(resultSet.getString("role").toUpperCase()));
                // Giả định profilePicture được lưu trữ dưới dạng blob trong cơ sở dữ liệu
                Blob blob = resultSet.getBlob("profilePicture");
                if (blob != null) {
                    // Đọc blob và chuyển đổi nó thành ImageIcon
                    byte[] imageData = blob.getBytes(1, (int) blob.length());
                    user.setProfilePicture(imageData);
                }
                user.setStatus(UserStatus.valueOf(resultSet.getString("status").toUpperCase()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public List<UserAccount> getAllUser() {
        List<UserAccount> userList = new ArrayList<>();
        String sql = "SELECT * FROM user_account";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserAccount user = new UserAccount();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setUserPassword(resultSet.getString("password"));
                user.setRole(UserRole.valueOf(resultSet.getString("role").toUpperCase()));
                Blob blob = resultSet.getBlob("profilePicture");
                if (blob != null) {
                    byte[] imageData = blob.getBytes(1, (int) blob.length());
                    user.setProfilePicture(imageData);
                }
                user.setStatus(UserStatus.valueOf(resultSet.getString("status").toUpperCase()));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi ở đây nếu cần
        }
        return userList;
    }

    public boolean addNewUserAccount(UserAccount user) {
        String sql = "INSERT INTO user_account (username, password, role, profilePicture, status)"
                + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getUserPassword());
            statement.setString(3, user.getRole().toString());
            if (user.getProfilePicture() != null) {
                statement.setBlob(4, new SerialBlob((Blob) user.getProfilePicture()));
            } else {
                statement.setNull(4, Types.BLOB);
            }
            statement.setString(5, "ACTIVE");
//            statement.setString(5, user.getStatus().toString());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * editUserAccount with information is UserAccount obj
     *
     * @param user: UserAccount
     */
    public boolean editUserAccount(UserAccount user) {
        String sql = "UPDATE user_account SET username = ?, password = ?, role = ?, "
                + "profilePicture = ?, status = ? WHERE userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getUserPassword());
            statement.setString(3, user.getRole().toString());
            if (user.getProfilePicture() != null) {
                statement.setBlob(4, new SerialBlob((Blob) user.getProfilePicture()));
            } else {
                statement.setNull(4, Types.BLOB);
            }
            statement.setString(5, user.getStatus().toString());
            statement.setInt(6, user.getUserId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete user account with id
     *
     * @param userId
     */
    public boolean deleteUserAccount(int userId) {

        String sql = "DELETE FROM user_account WHERE userId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

  public List<UserAccount> getAllAdmin() {
    List<UserAccount> adminList = new ArrayList<>();
    String sql = "SELECT * FROM user_account WHERE role = 'ADMIN'";
    try (PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            UserAccount admin = new UserAccount();
            admin.setUserId(resultSet.getInt("userId"));
            admin.setUsername(resultSet.getString("username"));
            admin.setUserPassword(resultSet.getString("password"));
            admin.setRole(UserRole.ADMIN);
            Blob blob = resultSet.getBlob("profilePicture");
            if (blob != null) {
                byte[] imageData = blob.getBytes(1, (int) blob.length());
                admin.setProfilePicture(imageData);
            }
            String statusStr = resultSet.getString("status");
            if (statusStr != null) {
                admin.setStatus(UserStatus.valueOf(statusStr.toUpperCase()));
            }
            adminList.add(admin);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý lỗi ở đây nếu cần
    }
    return adminList;
}



}
