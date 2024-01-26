/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhan.quanlysinhvien.service;

import com.nhan.quanlysinhvien.dao.CertificateDAO;
import com.nhan.quanlysinhvien.model.Certificate;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author huunh
 */
public class CertificateService {

    private final CertificateDAO certificateDAO;

    public CertificateService() {
        this.certificateDAO = new CertificateDAO();
    }

    public List<Certificate> getAllCertificates() throws SQLException {
        return certificateDAO.getAllCertificates();
    }

    public List<Certificate> getAllCertificateByStudentId(int studentId) throws SQLException {
        return certificateDAO.getAllCertificateByStudentId(studentId);
    }

    public boolean addNewCertificate(Certificate certificate) throws SQLException {
        return certificateDAO.addNewCertificate(certificate);
    }

    public boolean deleteCertificate(int certificateId) throws SQLException {
        return certificateDAO.deleteCertificate(certificateId);
    }

    public boolean updateCertificate(Certificate certificate) throws SQLException {
        return certificateDAO.updateCertificate(certificate);
    }
    
    public void deleteCertificateOfStudent(int id) {
        certificateDAO.deleteCertificateOfStudent(id);
    }
    public Certificate getCertificateById(int id) {
        return certificateDAO.getById(id);
    }
}

