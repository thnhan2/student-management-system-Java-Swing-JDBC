package com.nhan.quanlysinhvien.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    private int studentId;
    private String name;
    private int age;
    private String phoneNumber;
}
