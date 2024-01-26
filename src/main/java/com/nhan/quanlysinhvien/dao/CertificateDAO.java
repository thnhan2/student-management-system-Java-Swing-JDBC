/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhan.quanlysinhvien.dao;

import com.nhan.quanlysinhvien.model.Certificate;

import java.sql.Connection;
import java.sql.Date;
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
public class CertificateDAO {

    private final Connection connection;

    public CertificateDAO() {
        Connection connectionDB = MySQLConnection.getConnection();
        this.connection = connectionDB;
    }

    public List<Certificate> getAllCertificates() throws SQLException {
        List<Certificate> certificates = new ArrayList<>();
        String sql = "SELECT * FROM certificates";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                certificates.add(new Certificate(
                        resultSet.getInt("certificateId"),
                        resultSet.getInt("studentId"),
                        resultSet.getString("certificateName"),
                        resultSet.getDate("issueDate"),
                        resultSet.getDate("validUntil")
                ));
            }
        }
        return certificates;
    }

    public List<Certificate> getAllCertificateByStudentId(int studentId) throws SQLException {
        List<Certificate> certificates = new ArrayList<>();
        String sql = "SELECT * FROM certificates WHERE studentId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                certificates.add(new Certificate(
                        resultSet.getInt("certificateId"),
                        resultSet.getInt("studentId"),
                        resultSet.getString("certificateName"),
                        resultSet.getDate("issueDate"),
                        resultSet.getDate("validUntil")
                ));
            }
        }
        return certificates;
    }

    public boolean addNewCertificate(Certificate certificate) throws SQLException {
        String sql = "INSERT INTO certificates (studentId, certificateName, issueDate, validUntil) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, certificate.getStudentId());
            statement.setString(2, certificate.getCertificateName());
            statement.setDate(3, certificate.getIssueDate());
            statement.setDate(4, certificate.getValidUntil());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteCertificate(int certificateId) throws SQLException {
        String sql = "DELETE FROM certificates WHERE certificateId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, certificateId);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateCertificate(Certificate certificate) throws SQLException {
        String sql = "UPDATE certificates SET studentId = ?, certificateName = ?, issueDate = ?, validUntil = ? WHERE certificateId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, certificate.getStudentId());
            statement.setString(2, certificate.getCertificateName());
            statement.setDate(3, certificate.getIssueDate());
            statement.setDate(4, certificate.getValidUntil());
            statement.setInt(5, certificate.getCertificateId());
            return statement.executeUpdate() > 0;
        }
    }
   
    public void deleteCertificateOfStudent(int id) {
        String sql = "DELETE FROM certificates WHERE studentId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CertificateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Certificate getById(int id) {
        Certificate certificate = null;
        String sql = "SELECT * FROM certificates WHERE certificateId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Lấy thông tin từ ResultSet và tạo đối tượng Certificate
                    int certificateId = resultSet.getInt("certificateId");
                    int studentId = resultSet.getInt("studentId");
                    String certificateName = resultSet.getString("certificateName");
                    Date issueDate = resultSet.getDate("issueDate");
                    Date validDate = resultSet.getDate("validUntil");

                    certificate = new Certificate(certificateId, studentId, certificateName, issueDate, validDate);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CertificateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return certificate;
    }
}


