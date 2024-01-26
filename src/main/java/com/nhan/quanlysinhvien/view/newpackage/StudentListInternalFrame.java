/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.nhan.quanlysinhvien.view.newpackage;

import com.nhan.quanlysinhvien.model.Student;
import com.nhan.quanlysinhvien.service.StudentService;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author huunh
 */
public class StudentListInternalFrame extends javax.swing.JInternalFrame {

    private final StudentService service;
    private DefaultTableModel model;

    public StudentListInternalFrame() {
        initComponents();
        service = new StudentService();
        displayTable();

        jTable1.setComponentPopupMenu(studentTablePopupmenu);
        
        
        
        
    }
    private void displayTable() {
        ArrayList<Student> listStudent = (ArrayList<Student>) service.getAllStudent();

        
model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Student ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Phone Number");
        for (Student i : listStudent) {
            Object[] rowData = {i.getStudentId(), i.getName(), i.getAge(), i.getPhoneNumber()};
            model.addRow(rowData);
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);
        jTable1.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        studentTablePopupmenu = new javax.swing.JPopupMenu();
        itemDelete = new javax.swing.JMenuItem();
        itemInfo = new javax.swing.JMenuItem();
        itemAddCerfiticate = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        itemDelete.setText("Delete");
        itemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDeleteActionPerformed(evt);
            }
        });
        studentTablePopupmenu.add(itemDelete);

        itemInfo.setText("Info");
        itemInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemInfoActionPerformed(evt);
            }
        });
        studentTablePopupmenu.add(itemInfo);

        itemAddCerfiticate.setText("Add Certificate");
        studentTablePopupmenu.add(itemAddCerfiticate);

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

    private void itemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDeleteActionPerformed
        // delete selected student
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(StudentListInternalFrame.this, "Please select student to delete.", "delete student", JOptionPane.DEFAULT_OPTION);
        } else {
            int confirm = JOptionPane.showConfirmDialog(StudentListInternalFrame.this, "Are you sure delete this student?");
            if (confirm == JOptionPane.YES_OPTION) {
                int stId = Integer.parseInt(String.valueOf(jTable1.getValueAt(row, 0)));

                if (service.deleteStudentById(stId)) {
                    JOptionPane.showMessageDialog(rootPane, "delete successfully");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Delete failed. Student has certificate");
                }

                model.setRowCount(0);
                displayTable();
            }
        }
    }//GEN-LAST:event_itemDeleteActionPerformed

    private void itemInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemInfoActionPerformed
        
    }//GEN-LAST:event_itemInfoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemAddCerfiticate;
    private javax.swing.JMenuItem itemDelete;
    private javax.swing.JMenuItem itemInfo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPopupMenu studentTablePopupmenu;
    // End of variables declaration//GEN-END:variables
}
