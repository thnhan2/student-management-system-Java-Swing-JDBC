/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.nhan.quanlysinhvien.view.newpackage;

import com.nhan.quanlysinhvien.model.Certificate;
import com.nhan.quanlysinhvien.model.Student;
import com.nhan.quanlysinhvien.service.CertificateService;
import com.nhan.quanlysinhvien.service.StudentService;
import com.nhan.quanlysinhvien.view.HomeFrame;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huunh
 */
public class StudentInfoIF extends javax.swing.JInternalFrame {

    private Student student ;
    private StudentService studentService;
    private CertificateService certificateService;
    private DefaultTableModel model;
    public StudentInfoIF(Student student, String role) {
        initComponents();
        this.student = student;
        studentService = new StudentService();
        certificateService = new CertificateService();
//       
if (role.equals("EMPLOYEE")) {
    btnUpdate.setVisible(false);
    btnDelete.setVisible(false);
}
        
        try {
            showTable();
        } catch (IOException ex) {
            Logger.getLogger(StudentInfoIF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentInfoIF.class.getName()).log(Level.SEVERE, null, ex);
        } 
        System.out.println(student);
        edtId.setText(String.valueOf(student.getStudentId()));
        edtName.setText(student.getName());
        edtPhone.setText(student.getPhoneNumber());
    }

    public void showTable() throws IOException, SQLException {
        ArrayList<Certificate>  certificateList = (ArrayList<Certificate>) certificateService.getAllCertificateByStudentId(student.getStudentId());
        
         model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.addColumn("Certificate id");
        model.addColumn("studentId");
        model.addColumn("Certificate name");
        model.addColumn("issueDate");
        model.addColumn("validUntil");
        for (Certificate i : certificateList) {
            Object[] rowData = {i.getCertificateId(),
                i.getStudentId(),
                i.getCertificateName(),
                i.getIssueDate(),
                i.getValidUntil()};
                
            model.addRow(rowData);
        }
        tableCerfiticate.setModel(model);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableCerfiticate = new javax.swing.JTable();
        studentId = new javax.swing.JLabel();
        studentName = new javax.swing.JLabel();
        studentPhone = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        edtId = new javax.swing.JTextField();
        edtName = new javax.swing.JTextField();
        edtPhone = new javax.swing.JTextField();

        tableCerfiticate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableCerfiticate);

        studentId.setText("Student id");

        studentName.setText("Student name");

        studentPhone.setText("Student phone");

        btnDelete.setText("remove this Student");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update Info");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studentId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studentName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studentPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(edtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(edtId)
                    .addComponent(edtName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentId)
                    .addComponent(edtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(studentName)
                        .addComponent(edtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnUpdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentPhone)
                    .addComponent(btnDelete)
                    .addComponent(edtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String newUsername = edtName.getText();
    String strId = edtId.getText();
    int id = Integer.parseInt(strId);
    if (!strId.isEmpty() && id != student.getStudentId()) {
    }
    String phonenumber = edtPhone.getText();
    
    boolean hasChanges = !newUsername.equals(student.getName()) || 
                         !phonenumber.equals(student.getPhoneNumber());
    
    if (hasChanges && studentService.editStudentInfo(new Student(id, newUsername, student.getAge(), phonenumber))) {
        JOptionPane.showMessageDialog(rootPane, "Update successful", "Thông báo", JOptionPane.DEFAULT_OPTION);
    } else {
        JOptionPane.showMessageDialog(rootPane, "Update failed","error", JOptionPane.ERROR_MESSAGE);
    }
        
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selection = JOptionPane.showConfirmDialog(rootPane, "Are your sure delete this Student", "delete confirm", JOptionPane.YES_NO_CANCEL_OPTION);
        if (selection ==JOptionPane.NO_OPTION) {
            return ;
        }
        else if (selection == JOptionPane.YES_OPTION) {
            certificateService.deleteCertificateOfStudent(student.getStudentId());
            studentService.deleteStudentById(student.getStudentId());
            JOptionPane.showMessageDialog(rootPane, "Delete successful");
           return;
        }
        return ;
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextField edtId;
    private javax.swing.JTextField edtName;
    private javax.swing.JTextField edtPhone;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel studentId;
    private javax.swing.JLabel studentName;
    private javax.swing.JLabel studentPhone;
    private javax.swing.JTable tableCerfiticate;
    // End of variables declaration//GEN-END:variables
}
