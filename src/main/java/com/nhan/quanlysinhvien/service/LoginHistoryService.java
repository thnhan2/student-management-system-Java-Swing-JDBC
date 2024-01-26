/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhan.quanlysinhvien.service;

import com.nhan.quanlysinhvien.dao.UserHistoryDAO;
import com.nhan.quanlysinhvien.model.LoginHistory;
import com.nhan.quanlysinhvien.model.LoginHistoryDTO;
import java.util.List;

/**
 *
 * @author huunh
 */
public class LoginHistoryService {

    private final UserHistoryDAO repository;

    public LoginHistoryService() {
        this.repository = new UserHistoryDAO();
    }

    public void addUserHistoryLoging(LoginHistory userHistory) {
        repository.addUserLogin(userHistory);
    }
    
    public List<LoginHistoryDTO> getAllUserHistoryLogin() {
        return repository.getAllLoginHistory();
    }
}
