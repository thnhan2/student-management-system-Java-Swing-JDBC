/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.nhan.quanlysinhvien.view;

import com.nhan.quanlysinhvien.dao.MySQLConnection;
import com.nhan.quanlysinhvien.dao.UserRole;
import com.nhan.quanlysinhvien.model.Certificate;
import com.nhan.quanlysinhvien.model.Student;
import com.nhan.quanlysinhvien.model.UserAccount;
import com.nhan.quanlysinhvien.service.CertificateService;
import com.nhan.quanlysinhvien.service.StudentService;
import com.nhan.quanlysinhvien.service.UserAccountService;
import com.nhan.quanlysinhvien.view.newpackage.AddStudentIF;
import com.nhan.quanlysinhvien.view.newpackage.CertificateIF;
import com.nhan.quanlysinhvien.view.newpackage.CertificateManagement;
import com.nhan.quanlysinhvien.view.newpackage.LoginHistoryInternalFrame;
import com.nhan.quanlysinhvien.view.newpackage.StudentInfoIF;
import com.nhan.quanlysinhvien.view.newpackage.StudentListInternalFrame;
import com.nhan.quanlysinhvien.view.newpackage.UserAccountIF;
import com.nhan.quanlysinhvien.view.newpackage.UserManagement;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author huunh
 */
public class HomeFrame extends javax.swing.JFrame {

    private UserAccount currentUser;
    private UserAccountService service;
    private StudentService studentService;
    private CertificateService certificateService;
    
   public String getRole() {
       return currentUser.getRole().toString();
   }
    public HomeFrame(UserAccount currentUser) {
        initComponents();
        this.currentUser = currentUser;
        service = new UserAccountService();
        tvUsername.setText("Hello " + currentUser.getRole().toString()+", " +currentUser.getUsername());
        studentService = new StudentService();
        certificateService = new CertificateService();
        
        // authorization
        if (currentUser.getRole() == UserRole.MANAGER || currentUser.getRole() == UserRole.EMPLOYEE) {
            btnViewAllUser.setVisible(false); // view other user list
            jButton1.setVisible(false); // view login history
            jButton2.setVisible(false); // usermanagement
            if (currentUser.getRole() == UserRole.EMPLOYEE)  {
                jMenu4.setVisible(false);
                jMenu2.setVisible(false);
                btnAddStudent.setVisible(false);
                btnCertificate.setVisible(false);
            }
        }
        // clock for fun
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy thời gian hiện tại
                java.util.Date now = new java.util.Date();

                // Định dạng thời gian và hiển thị trên label
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                String formattedTime = dateFormat.format(now);
                time.setText(formattedTime);
            }
        });

        // Bắt đầu đồng hồ
        timer.start();
        
        
        

        if (currentUser.getProfilePicture() == null) {
            // Nếu không có hình ảnh, hiển thị ảnh mặc định
            ImageIcon icon = new ImageIcon(getClass().getResource("/default_avt.jpg"));
            imageViewAvatar.setIcon(icon);
        } else if (currentUser.getProfilePicture() instanceof Blob) {
            // Nếu có hình ảnh, kiểm tra kiểu và xử lý
            Blob profilePictureBlob = (Blob) currentUser.getProfilePicture();
            try {
                InputStream inputStream = profilePictureBlob.getBinaryStream();
                BufferedImage bufferedImage = ImageIO.read(inputStream);
                if (bufferedImage != null) {
                    ImageIcon icon = new ImageIcon(bufferedImage);
                    imageViewAvatar.setIcon(icon);
                } else {
                    System.out.println("Không thể đọc hình ảnh từ Blob");
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                // Xử lý lỗi ở đây
            }
        } else {
            // Xử lý trường hợp không phải kiểu Blob
            System.out.println("Profile picture không phải là kiểu Blob");
        }
        
        
        // dong an toan
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MySQLConnection.closeConnection();
                System.exit(0);
            }
        
        });
    }

    public void setData(UserAccount user) {
        this.currentUser = user;
    }
    
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return resizedImage;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelLeft = new javax.swing.JPanel();
        btnViewStudent = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnViewAllUser = new javax.swing.JButton();
        edtStudentId = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        imageViewAvatar = new javax.swing.JLabel();
        tvUsername = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnCertificate = new javax.swing.JButton();
        btnAddStudent = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnFindCertificate = new javax.swing.JButton();
        edtCertificateId = new javax.swing.JTextField();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        importStudent = new javax.swing.JMenuItem();
        importCertificate = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(java.awt.Color.white);

        panelLeft.setBackground(new java.awt.Color(255, 204, 204));

        btnViewStudent.setText("View Students");
        btnViewStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewStudentActionPerformed(evt);
            }
        });

        jButton1.setText("View Log in History");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("User Management");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnViewAllUser.setText("View All User");
        btnViewAllUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAllUserActionPerformed(evt);
            }
        });

        btnSearch.setText("Search Student");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        imageViewAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/default_avt.jpg"))); // NOI18N
        imageViewAvatar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tvUsername.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tvUsername.setText("UserName");

        jButton3.setText("Change photo");
        jButton3.setActionCommand("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnCertificate.setText("Add certificate");
        btnCertificate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCertificateActionPerformed(evt);
            }
        });

        btnAddStudent.setText("Add Student");
        btnAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStudentActionPerformed(evt);
            }
        });

        btnFindCertificate.setText("Search Certificate");
        btnFindCertificate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindCertificateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLeftLayout = new javax.swing.GroupLayout(panelLeft);
        panelLeft.setLayout(panelLeftLayout);
        panelLeftLayout.setHorizontalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLeftLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imageViewAvatar)
                .addGap(54, 54, 54))
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFindCertificate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCertificate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnViewStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnViewAllUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edtStudentId)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tvUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(edtCertificateId))
                .addContainerGap())
        );
        panelLeftLayout.setVerticalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewAllUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddStudent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewStudent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCertificate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtCertificateId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnFindCertificate)
                .addGap(42, 42, 42)
                .addComponent(imageViewAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(tvUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Student Management System");

        time.setFont(new java.awt.Font("Lucida Console", 1, 18)); // NOI18N

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(time, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(time)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(time)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logout.png"))); // NOI18N
        jMenuItem2.setText("Logout");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Export");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/export_csv.png"))); // NOI18N
        jMenuItem4.setText("Export StudentList as CSV");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/export_pdf.png"))); // NOI18N
        jMenuItem3.setText("Export Certificate list CSV");
        jMenuItem3.setActionCommand("E");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Import");

        importStudent.setText("import Student CSV");
        importStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importStudentActionPerformed(evt);
            }
        });
        jMenu4.add(importStudent);

        importCertificate.setText("import Certificate CSV");
        importCertificate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importCertificateActionPerformed(evt);
            }
        });
        jMenu4.add(importCertificate);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDesktopPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);

        // hiển thị nơi lưu
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                File fileToSave = fileChooser.getSelectedFile();

                // if not provide file name, using default name
                if (fileToSave.getName().isEmpty()) {
                    fileToSave = new File(fileToSave.getParent(), "certificate_list.csv");
                }

                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".csv")) {
                    filePath += ".csv";
                }

                List<String[]> data = new ArrayList<>();
                data.add(new String[]{"certificateId", "studentId", "certificateName", "issueDate", "validUntil"});

                certificateService = new CertificateService();
                List<Certificate> li = certificateService.getAllCertificates();

                for (Certificate i : li) {
                    String[] k = new String[]{
                        String.valueOf(i.getCertificateId()),
                        String.valueOf(i.getStudentId()),
                        i.getCertificateName(),
                        i.getIssueDate().toString(),
                        i.getValidUntil().toString()
                    };
                    data.add(k);
                }

                try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
                    for (String[] line : data) {
                        writer.writeNext(line, false);
                    }
                    System.out.println("CSV file created successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                    // Xử lý lỗi ở đây
                }
            } catch (SQLException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        // Đóng giao diện hiện tại
        dispose();

        // Mở giao diện LoginFrame
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnViewStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStudentActionPerformed
        // View table student
        StudentListInternalFrame frame = new StudentListInternalFrame();
        jDesktopPane1.add(frame);
        frame.setSize(jDesktopPane1.getSize());
        frame.setVisible(true);

        jDesktopPane1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Cập nhật kích thước của JInternalFrame khi kích thước của JDesktopPane thay đổi
                frame.setSize(jDesktopPane1.getSize());
            }
        });

    }//GEN-LAST:event_btnViewStudentActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // hien thi danh sach user dang nhap
        LoginHistoryInternalFrame frame = new LoginHistoryInternalFrame();
        jDesktopPane1.add(frame);
        frame.setSize(jDesktopPane1.getSize());
        frame.setVisible(true);

        jDesktopPane1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Cập nhật kích thước của JInternalFrame khi kích thước của JDesktopPane thay đổi
                frame.setSize(jDesktopPane1.getSize());
            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // hien thi add, edit user
        UserManagement frame = new UserManagement();
        jDesktopPane1.add(frame);
        frame.setSize(jDesktopPane1.getSize());
        frame.setVisible(true);

        jDesktopPane1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Cập nhật kích thước của JInternalFrame khi kích thước của JDesktopPane thay đổi
                frame.setSize(jDesktopPane1.getSize());
            }
        });
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnViewAllUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllUserActionPerformed
        UserAccountIF frame = new UserAccountIF();
        jDesktopPane1.add(frame);
        frame.setSize(jDesktopPane1.getSize());
        frame.setVisible(true);
        jDesktopPane1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Cập nhật kích thước của JInternalFrame khi kích thước của JDesktopPane thay đổi
                frame.setSize(jDesktopPane1.getSize());
            }
        });
    }//GEN-LAST:event_btnViewAllUserActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JFileChooser fileChooser = new JFileChooser();

        // Thiết lập bộ lọc file để chỉ hiển thị file có định dạng CSV
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);

        // Hiển thị hộp thoại chọn nơi lưu và đặt tên file
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (fileToSave.getName().isEmpty()) {
                // Nếu không nhập, sử dụng tên mặc định
                fileToSave = new File(fileToSave.getParent(), "student_list.csv");
            }

            // Đảm bảo rằng tên file có định dạng .csv
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".csv")) {
                filePath += ".csv";
            }

            List<String[]> data = new ArrayList<>();
            data.add(new String[]{"StudentId", "Name", "Age", "PhoneNumber"});

            studentService = new StudentService();
            List<Student> li = studentService.getAllStudent();

            for (Student i : li) {
                String[] k = new String[]{String.valueOf(i.getStudentId()), i.getName(), String.valueOf(i.getAge()), i.getPhoneNumber()};
                data.add(k);
            }

            try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
                for (String[] line : data) {
                    writer.writeNext(line, false);
                }
                System.out.println("CSV file created successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        int id = Integer.parseInt(edtStudentId.getText());

if (id == -1) {
    JOptionPane.showMessageDialog(HomeFrame.this, "Please select a student to view information", "Show info", JOptionPane.DEFAULT_OPTION);
} else {
    try {
        StudentService serviceSt = new StudentService();
        Student st = serviceSt.getStudentById(id);

              // Create a new internal frame for the student
        StudentInfoIF frame = new StudentInfoIF(st, getRole());
        jDesktopPane1.add(frame);
        frame.setSize(jDesktopPane1.getSize());
        frame.setVisible(true);

        // Resize internal frame when the size of JDesktopPane changes
        jDesktopPane1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frame.setSize(jDesktopPane1.getSize());
            }
        });
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(rootPane, "Student with ID " + id + " does not exist.");
    }
}


    }//GEN-LAST:event_btnSearchActionPerformed

    private void importStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importStudentActionPerformed
        // them danh sach doc tu file csv vao table student
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(HomeFrame.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String csvFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                importStudentData(csvFilePath);
            } catch (IOException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_importStudentActionPerformed

    private void importCertificateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importCertificateActionPerformed
// import certificatge
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(HomeFrame.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String csvFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                importCertificateData(csvFilePath);
            } catch (IOException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CsvValidationException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_importCertificateActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // change photo
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Read the selected image file into a byte array
                BufferedImage originalImage = ImageIO.read(selectedFile);

                // Resize the image to 64x64
                BufferedImage resizedImage = resizeImage(originalImage, 64, 64);

                // Convert the resized image to a byte array
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(resizedImage, "jpg", baos);
                byte[] imageData = baos.toByteArray();
                baos.close();

                // Set the resized image data to the user's profile picture
                currentUser.setProfilePicture(imageData);
                service.updateUserAccount(currentUser);

                // Update the label with the new image
                ImageIcon icon = new ImageIcon(resizedImage);
                imageViewAvatar.setIcon(icon);

            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error here
            }
            // Handle the SQL exception here
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnCertificateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCertificateActionPerformed
        
       
        CertificateManagement frame = new CertificateManagement();
        jDesktopPane1.add(frame);
        frame.setSize(jDesktopPane1.getSize());
        frame.setVisible(true);
        jDesktopPane1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Cập nhật kích thước của JInternalFrame khi kích thước của JDesktopPane thay đổi
                frame.setSize(jDesktopPane1.getSize());
            }
        });
        
    }//GEN-LAST:event_btnCertificateActionPerformed

    private void btnAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStudentActionPerformed
        AddStudentIF frame = new AddStudentIF();
        jDesktopPane1.add(frame);
        frame.setSize(jDesktopPane1.getSize());
        frame.setVisible(true);
        jDesktopPane1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Cập nhật kích thước của JInternalFrame khi kích thước của JDesktopPane thay đổi
                frame.setSize(jDesktopPane1.getSize());
            }
        });
    }//GEN-LAST:event_btnAddStudentActionPerformed

    private void btnFindCertificateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindCertificateActionPerformed
        CertificateIF frame = new CertificateIF(Integer.parseInt(edtCertificateId.getText()), getRole());
        jDesktopPane1.add(frame);
        frame.setSize(jDesktopPane1.getSize());
        frame.setVisible(true);
        jDesktopPane1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Cập nhật kích thước của JInternalFrame khi kích thước của JDesktopPane thay đổi
                frame.setSize(jDesktopPane1.getSize());
            }
        });
    }//GEN-LAST:event_btnFindCertificateActionPerformed

    private void importStudentData(String csvFilePath) throws IOException, CsvValidationException {
        try {
            CSVReader reader = null;
            reader = new CSVReaderBuilder(new FileReader(csvFilePath)).withSkipLines(1).build();
            String[] line;
            while ((line = reader.readNext()) != null) {
                int studentId = Integer.parseInt(line[0]);
                String name = line[1];
                int age = Integer.parseInt(line[2]);
                String phoneNumber = line[3];
                System.out.println("okkjafdoasjdflajsl");
                Student st = new Student(studentId, name, age, phoneNumber);
                System.out.println(st);
                studentService.addNewStudent(st);
                JOptionPane.showMessageDialog(rootPane, "add successful");
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, "add failed");
            Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void importCertificateData(String csvFilePath) throws IOException, CsvValidationException, ParseException, SQLException {
        try {
            CSVReader reader = null;
            SimpleDateFormat dateFormatOriginal = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFormatNew = new SimpleDateFormat("dd/MM/yyyy");

            reader = new CSVReaderBuilder(new FileReader(csvFilePath)).withSkipLines(1).build();
            String[] line;

            while ((line = reader.readNext()) != null) {
                int cerID = Integer.parseInt(line[0]);
                int stuId = Integer.parseInt(line[1]);
                String cerName = line[2];

                // Xử lý ngày với cả hai định dạng
                Date issueDate = tryParseDate(line[3], dateFormatOriginal, dateFormatNew);
                Date validDate = tryParseDate(line[4], dateFormatOriginal, dateFormatNew);

                Certificate cer = new Certificate(cerID, stuId, cerName, issueDate, validDate);
                System.out.println(cer);
                certificateService.addNewCertificate(cer);
               
            } JOptionPane.showMessageDialog(rootPane, "add successful");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error");
            Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    private Date tryParseDate(String dateString, SimpleDateFormat... dateFormats) throws ParseException {
        for (SimpleDateFormat dateFormat : dateFormats) {
            try {
                return new Date(dateFormat.parse(dateString).getTime());
            } catch (ParseException ignored) {
                // Chuyển sang định dạng tiếp theo nếu có lỗi
            }
        }
        throw new ParseException("Could not parse date: " + dateString, 0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddStudent;
    private javax.swing.JButton btnCertificate;
    private javax.swing.JButton btnFindCertificate;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnViewAllUser;
    private javax.swing.JButton btnViewStudent;
    private javax.swing.JTextField edtCertificateId;
    private javax.swing.JTextField edtStudentId;
    private javax.swing.JLabel imageViewAvatar;
    private javax.swing.JMenuItem importCertificate;
    private javax.swing.JMenuItem importStudent;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JLabel time;
    private javax.swing.JLabel tvUsername;
    // End of variables declaration//GEN-END:variables
}
