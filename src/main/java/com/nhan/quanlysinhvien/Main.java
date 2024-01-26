package com.nhan.quanlysinhvien;

import com.nhan.quanlysinhvien.dao.MySQLConnection;
import com.nhan.quanlysinhvien.view.LoginFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    private static boolean isDarkMode = true;

    public static void main(String[] args) {
        
       MySQLConnection.getConnection();
       MySQLConnection.init();
       MySQLConnection.closeConnection();
        java.awt.EventQueue.invokeLater(() -> {
            try {
                // Set the initial Look and Feel
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

                LoginFrame frame = new LoginFrame();
                frame.setVisible(true);
                frame.setTitle("Student Management System");

                JButton switchButton = new JButton("Switch Theme");
                switchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Toggle between dark mode and default mode
                        toggleTheme();
                    }
                });

             

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });        
    }

    private static void toggleTheme() {
        isDarkMode = !isDarkMode;
        try {
            if (isDarkMode) {
                // Set dark mode Look and Feel
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                setDarkModeColors();
            } else {
                // Set default Look and Feel (you can change it to your default Look and Feel)
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }

            // Update the UI
            SwingUtilities.updateComponentTreeUI(SwingUtilities.getWindowAncestor(new JFrame()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setDarkModeColors() {
        // Customize dark mode colors here (similar to the previous example)
        UIManager.put("nimbusBase", new Color(18, 30, 49));
        UIManager.put("nimbusBlueGrey", new Color(169, 176, 190));
        UIManager.put("control", new Color(22, 26, 35));
        
    }
}
