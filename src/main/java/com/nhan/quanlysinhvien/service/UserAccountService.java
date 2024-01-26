/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhan.quanlysinhvien.service;

import com.nhan.quanlysinhvien.dao.UserAccountDAO;
import com.nhan.quanlysinhvien.model.UserAccount;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author huunh
 */
public class UserAccountService {

    private final UserAccountDAO userAccountDao;

    public UserAccountService() {
        this.userAccountDao = new UserAccountDAO();
    }

    public List<UserAccount> getAllUser() {
        return userAccountDao.getAllUser();
    }

    public boolean createUserAccount(UserAccount userAccount) {
        return userAccountDao.addNewUserAccount(userAccount);
    }

    public UserAccount getUserAccountById(int userId) throws IOException {
        return userAccountDao.getUserById(userId);
    }

    public boolean updateUserAccount(UserAccount userAccount) {
        return userAccountDao.editUserAccount(userAccount);
    }

    public boolean deleteUserAccount(int userId) {
        return userAccountDao.deleteUserAccount(userId);
    }

    public List<UserAccount> getAllAdmin() {
        return userAccountDao.getAllAdmin();
    }

}
