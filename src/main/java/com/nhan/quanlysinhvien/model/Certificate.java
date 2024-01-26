package com.nhan.quanlysinhvien.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Certificate {
private int certificateId;
private int StudentId;
private String certificateName;
private Date issueDate;
private Date validUntil;
}
