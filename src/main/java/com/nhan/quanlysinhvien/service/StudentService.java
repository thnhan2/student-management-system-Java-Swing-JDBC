/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhan.quanlysinhvien.service;

import com.nhan.quanlysinhvien.dao.StudentDAO;
import com.nhan.quanlysinhvien.model.Student;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huunh
 */
public class StudentService {

    private final StudentDAO repository;

    public StudentService() {
        this.repository = new StudentDAO();
    }

    public Student getStudentById(int id) throws IOException {
        return repository.getStudentById(id);
    }
    
    public List<Student> getAllStudent() {
        return repository.getAllStudents();
    }
    
    public boolean addNewStudent(Student student) {
        return repository.addNewStudent(student);
    }
    
    public boolean editStudentInfo(Student student) {
        return repository.editStudentInfo(student);
    }
    
    public boolean deleteStudentById(int id) {
        return repository.deleteStudentId(id);
    }

   
}
