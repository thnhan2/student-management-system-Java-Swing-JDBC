/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhan.quanlysinhvien.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author huunh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginHistoryDTO {

    private int sessionId;
    private int userId;
    private String timeLogin;
}
