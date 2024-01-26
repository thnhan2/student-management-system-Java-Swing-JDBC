/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhan.quanlysinhvien.dao;

import com.nhan.quanlysinhvien.model.Student;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huunh
 */
public class StudentDAO {

    private final Connection connection;

    public StudentDAO() {
        this.connection = MySQLConnection.getConnection();

    }

    public Student getStudentById(int userId) throws IOException {
        Student student = null;
        String sql = "SELECT * FROM student WHERE studentId = ?";
        System.out.println(sql);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    student = new Student();
                    student.setStudentId(userId);
                    student.setAge(resultSet.getInt("age"));
                    student.setName(resultSet.getString("name"));
                    student.setPhoneNumber(resultSet.getString("phoneNumber"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student st = new Student();
                st.setStudentId(resultSet.getInt("studentId"));
                st.setAge(resultSet.getInt("age"));
                st.setName(resultSet.getString("name"));
                st.setPhoneNumber(resultSet.getString("phoneNumber"));
                studentList.add(st);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return studentList;
    }

    public boolean addNewStudent(Student student) {
        String sql = "INSERT INTO student (name, age, phonenumber)"
                + "VALUES (?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getPhoneNumber());

            return statement.executeUpdate() != 0;

        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean editStudentInfo(Student student) {
        String sql = "UPDATE student SET name = ?, age = ?, phonenumber = ?"
                + "WHERE studentId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getPhoneNumber());
            statement.setInt(4, student.getStudentId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean deleteStudentId(int studentId) {
        String sql = "DELETE FROM student WHERE studentId = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
