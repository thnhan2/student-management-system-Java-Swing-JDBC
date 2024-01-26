/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.nhan.quanlysinhvien.view.newpackage;

import com.nhan.quanlysinhvien.model.LoginHistory;
import com.nhan.quanlysinhvien.model.LoginHistoryDTO;
import com.nhan.quanlysinhvien.model.Student;
import com.nhan.quanlysinhvien.service.LoginHistoryService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huunh
 */
public class LoginHistoryInternalFrame extends javax.swing.JInternalFrame {

    private final LoginHistoryService service;
    public LoginHistoryInternalFrame() {
        initComponents();
        service = new LoginHistoryService();
        displayTable();
    }
 private void displayTable() {
        List<LoginHistoryDTO> listStudent;
        listStudent = (ArrayList<LoginHistoryDTO>)service.getAllUserHistoryLogin();
        
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.addColumn("User Id");
        model.addColumn("Time Login");
        
        for (LoginHistoryDTO i : listStudent) {
            Object[] rowData = {i.getUserId(), i.getTimeLogin()};
            model.addRow(rowData);
        }
        jTable1.setModel(model);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
