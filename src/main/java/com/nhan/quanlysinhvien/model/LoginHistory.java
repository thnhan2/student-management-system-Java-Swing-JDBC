package com.nhan.quanlysinhvien.model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginHistory {
  private int sessionId;
  private int userId;
  private Date timeLogin;
}
