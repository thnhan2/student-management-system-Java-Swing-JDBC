package com.nhan.quanlysinhvien.model;

import com.nhan.quanlysinhvien.dao.UserRole;
import com.nhan.quanlysinhvien.dao.UserStatus;
import java.awt.Image;
import java.sql.Blob;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ImageIcon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserAccount {

    private int userId;
    private String username;
    private String userPassword;
    private UserRole role;
    private Blob profilePicture;
    private UserStatus status;

    public UserAccount(String username, String userPassword, UserRole role,
            Blob profilePicture, UserStatus status) {
        this.username = username;
        this.userPassword = userPassword;
        this.role = role;
        this.profilePicture = profilePicture;
        this.status = status;
    }
    public void setProfilePicture(byte[] profilePictureData) {
        try {
            this.profilePicture = new SerialBlob(profilePictureData);
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi ở đây
        }
    }
}
