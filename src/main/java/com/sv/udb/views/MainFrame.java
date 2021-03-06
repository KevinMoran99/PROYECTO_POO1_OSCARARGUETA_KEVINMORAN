/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.views;

import com.sv.udb.controllers.AuthAsignController;
import com.sv.udb.controllers.AuthorityController;
import com.sv.udb.controllers.CallController;
import com.sv.udb.controllers.ComplaintTypeController;
import com.sv.udb.controllers.ProvAsignController;
import com.sv.udb.controllers.ProviderController;
import com.sv.udb.utilities.ReportGenerator;
import com.sv.udb.controllers.SchoolController;
import com.sv.udb.controllers.UserController;
import com.sv.udb.views.dialogs.SearchSchool;
import com.sv.udb.models.Authority;
import com.sv.udb.models.Authority_asign;
import com.sv.udb.models.Call;
import com.sv.udb.models.Complaint_type;
import com.sv.udb.models.Provider;
import com.sv.udb.models.Provider_asign;
import com.sv.udb.models.School;
import com.sv.udb.models.User;
import com.sv.udb.models.User_type;
import com.sv.udb.utilities.Animations;
import com.sv.udb.utilities.Utils;
import com.sv.udb.views.dialogs.SearchAuthority;
import com.sv.udb.views.dialogs.SearchProvider;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public class MainFrame extends javax.swing.JFrame {

    User user; //Usuario logeado
    JButton[] adminBtns, userBtns; //Botones del menu de admin, y de personal, respectivamente
    //Variables que alojaran los objetos padres de las nuevas denuncias y de las denuncias seleccionadas
    School callSchool;
    int currentId = 0;

    /**
     * Creates new form AdminFrame
     *
     * @param user
     */
    public MainFrame(User user) {
        this.user = user;
        initComponents();

        lblUser.setText(user.getName() + " " + user.getLastname());

        //Estilizando componentes
        Animations.initStyle(this.getContentPane());
        
        //Botones del menu
        adminBtns = new JButton[5];
        adminBtns[0] = btnMenuUser;
        adminBtns[1] = btnMenuType;
        adminBtns[2] = btnMenuSchool;
        adminBtns[3] = btnMenuAuth;
        adminBtns[4] = btnMenuProv;

        userBtns = new JButton[4];
        userBtns[0] = btnMenuNew;
        userBtns[1] = btnMenuCalls;
        userBtns[2] = btnMenuReports;
        userBtns[3] = btnMenuProfile;

        //Invisibilizando botones según usuario logeado
        for (JButton btn : user.getUser_type().getId() == 1 ? userBtns : adminBtns) {
            btn.setVisible(false);
        }

        //Vista inicial
        if (user.getUser_type().getId() == 1) {
            showCard("crdUser");
            refreshAdmin();
        } else {
            showCard("crdCalls");
            refreshPnlCalls();
        }

        //Todos los label de error
        JLabel[] errorList = {
            errUserName, errUserLastname, errUserEmail, errUserPass, errUserType, errUserState,
            errTypeName, errTypeState, errTypeAction,
            errSchoolName, errSchoolAddress, errSchoolState,
            errAuthName, errAuthState,
            errProvName, errProvState,
            errNewSchool, errNewDescription, errNewType, errNewList,
            errProfileName, errProfileEmail, errProfilePass
        };

        //Ocultando los label de error
        for (JLabel err : errorList) {
            Animations.hide(err, 255, 0, 0);
        }

        
        //Haciendo datepickers no editables
        dtpCallsFrom.getEditor().setEditable(false);
        dtpCallsTo.getEditor().setEditable(false);
        dtpReportsFrom.getEditor().setEditable(false);
        dtpReportsTo.getEditor().setEditable(false);
        
        //Ocultando componentes
        Animations.invisibilizeComponents(this, cmbUserSearch, txtUserSearch, cmbTypeSearch, txtTypeSearch,
                cmbSchoolSearch, txtSchoolSearch, cmbAuthSearch, txtAuthSearch, cmbProvSearch, 
                txtProvSearch, pnlCallsParam, btnCallsSchoolSearch, lblList, scrLstNew, 
                btnNewListSearch, btnNewListDel, lblDetailArchived);
        
        Component[] obeySizes = {
            pnlCallsParam, lblCallsSchool, lblDetailUser, lblDetailDate, lblDetailSchool, lblDetailType
        };
        
        for(Component comp : obeySizes)
            Utils.obeySize(comp);

        //llenando tabla de usuarios
        fillUserTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        btnMenuUser = new javax.swing.JButton();
        btnMenuSchool = new javax.swing.JButton();
        btnMenuAuth = new javax.swing.JButton();
        btnMenuProv = new javax.swing.JButton();
        btnMenuType = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        lblUser = new org.jdesktop.swingx.JXLabel();
        btnMenuCalls = new javax.swing.JButton();
        btnMenuNew = new javax.swing.JButton();
        btnMenuReports = new javax.swing.JButton();
        btnMenuProfile = new javax.swing.JButton();
        pnlMain = new javax.swing.JPanel();
        pnlUser = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cmbUserSearchType = new javax.swing.JComboBox<>();
        txtUserSearch = new javax.swing.JTextField();
        cmbUserSearch = new javax.swing.JComboBox<>();
        btnUserSearchReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtUserLastame = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        errUserName = new javax.swing.JLabel();
        errUserLastname = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUserEmail = new javax.swing.JTextField();
        errUserEmail = new javax.swing.JLabel();
        errUserPass = new javax.swing.JLabel();
        txtUserPass = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        errUserType = new javax.swing.JLabel();
        errUserState = new javax.swing.JLabel();
        cmbUserType = new javax.swing.JComboBox<>();
        cmbUserState = new javax.swing.JComboBox<>();
        btnUserClear = new javax.swing.JButton();
        btnUserAction = new javax.swing.JButton();
        pnlType = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        cmbTypeSearchType = new javax.swing.JComboBox<>();
        txtTypeSearch = new javax.swing.JTextField();
        cmbTypeSearch = new javax.swing.JComboBox<>();
        btnTypeSearchReset = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblType = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtTypeName = new javax.swing.JTextField();
        errTypeName = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        errTypeAction = new javax.swing.JLabel();
        errTypeState = new javax.swing.JLabel();
        cmbTypeAction = new javax.swing.JComboBox<>();
        cmbTypeState = new javax.swing.JComboBox<>();
        btnTypeClear = new javax.swing.JButton();
        btnTypeAction = new javax.swing.JButton();
        pnlSchool = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        cmbSchoolSearchType = new javax.swing.JComboBox<>();
        txtSchoolSearch = new javax.swing.JTextField();
        cmbSchoolSearch = new javax.swing.JComboBox<>();
        btnSchoolSearchReset = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSchool = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        txtSchoolName = new javax.swing.JTextField();
        errSchoolName = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        errSchoolAddress = new javax.swing.JLabel();
        errSchoolState = new javax.swing.JLabel();
        cmbSchoolState = new javax.swing.JComboBox<>();
        btnSchoolClear = new javax.swing.JButton();
        btnSchoolAction = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSchoolAddress = new javax.swing.JTextArea();
        pnlAuth = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        cmbAuthSearchType = new javax.swing.JComboBox<>();
        txtAuthSearch = new javax.swing.JTextField();
        cmbAuthSearch = new javax.swing.JComboBox<>();
        btnAuthSearchReset = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblAuth = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        txtAuthName = new javax.swing.JTextField();
        errAuthName = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        errAuthState = new javax.swing.JLabel();
        cmbAuthState = new javax.swing.JComboBox<>();
        btnAuthClear = new javax.swing.JButton();
        btnAuthAction = new javax.swing.JButton();
        pnlProv = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        cmbProvSearchType = new javax.swing.JComboBox<>();
        txtProvSearch = new javax.swing.JTextField();
        cmbProvSearch = new javax.swing.JComboBox<>();
        btnProvSearchReset = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblProv = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        txtProvName = new javax.swing.JTextField();
        errProvName = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        errProvState = new javax.swing.JLabel();
        cmbProvState = new javax.swing.JComboBox<>();
        btnProvClear = new javax.swing.JButton();
        btnProvAction = new javax.swing.JButton();
        pnlCalls = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        cmbCallsSearchType = new javax.swing.JComboBox<>();
        btnAuthSearchReset1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblCalls = new javax.swing.JTable();
        dtpCallsFrom = new org.jdesktop.swingx.JXDatePicker();
        jLabel30 = new javax.swing.JLabel();
        dtpCallsTo = new org.jdesktop.swingx.JXDatePicker();
        jLabel31 = new javax.swing.JLabel();
        btnCallsDetail = new javax.swing.JButton();
        btnCallsSchoolSearch = new javax.swing.JButton();
        pnlCallsParam = new javax.swing.JPanel();
        cmbCallsSearch = new javax.swing.JComboBox<>();
        lblCallsSchool = new javax.swing.JLabel();
        txtCallsSearch = new javax.swing.JTextField();
        pnlNew = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        errNewSchool = new javax.swing.JLabel();
        btnNewClear = new javax.swing.JButton();
        btnNewSave = new javax.swing.JButton();
        btnNewSchoolSearch = new javax.swing.JButton();
        lblNewSchool = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtNewDesc = new javax.swing.JTextArea();
        errNewDescription = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        cmbNewType = new javax.swing.JComboBox<>();
        errNewType = new javax.swing.JLabel();
        chbNewViable = new javax.swing.JCheckBox();
        lblList = new javax.swing.JLabel();
        scrLstNew = new javax.swing.JScrollPane();
        lstNew = new javax.swing.JList<>();
        errNewList = new javax.swing.JLabel();
        btnNewListSearch = new javax.swing.JButton();
        btnNewListDel = new javax.swing.JButton();
        pnlCallDetail = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel37 = new javax.swing.JLabel();
        btnDetailBack = new javax.swing.JButton();
        btnDetailSave = new javax.swing.JButton();
        lblDetailSchool = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblAsigns = new javax.swing.JLabel();
        lblDetailArchived = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lblDetailUser = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        lblDetailDate = new javax.swing.JLabel();
        lblDetailType = new javax.swing.JLabel();
        btnDetailReport = new javax.swing.JButton();
        chbDetailTalk = new javax.swing.JCheckBox();
        scrAsigns = new javax.swing.JScrollPane();
        tblAsigns = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        lblDetailDescription = new javax.swing.JTextArea();
        pnlReports = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        btnReport1 = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        dtpReportsFrom = new org.jdesktop.swingx.JXDatePicker();
        jLabel47 = new javax.swing.JLabel();
        dtpReportsTo = new org.jdesktop.swingx.JXDatePicker();
        jLabel48 = new javax.swing.JLabel();
        btnReport2 = new javax.swing.JButton();
        btnReport3 = new javax.swing.JButton();
        pnlProfile = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        errProfileName = new javax.swing.JLabel();
        errProfileEmail = new javax.swing.JLabel();
        errProfilePass = new javax.swing.JLabel();
        btnUserClear1 = new javax.swing.JButton();
        btnUserAction1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtProfileLastName = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtProfileName = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        btnEditName = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnEditEmail = new javax.swing.JButton();
        txtProfileEmail = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnEditPass = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        txtProfilePass = new javax.swing.JPasswordField();
        txtProfileConfirm = new javax.swing.JPasswordField();
        jLabel56 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        pnlMenu.setBackground(new java.awt.Color(135, 202, 182));
        pnlMenu.setMaximumSize(new java.awt.Dimension(234, 32767));

        btnLogout.setBackground(new java.awt.Color(94, 151, 103));
        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/power-signal.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setDoubleBuffered(true);
        btnLogout.setIconTextGap(10);
        btnLogout.setMargin(null);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnMenuUser.setBackground(new java.awt.Color(0, 74, 101));
        btnMenuUser.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnMenuUser.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user-silhouette.png"))); // NOI18N
        btnMenuUser.setText("Usuarios");
        btnMenuUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuUser.setIconTextGap(10);
        btnMenuUser.setMargin(null);
        btnMenuUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuUserActionPerformed(evt);
            }
        });

        btnMenuSchool.setBackground(new java.awt.Color(94, 151, 103));
        btnMenuSchool.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnMenuSchool.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuSchool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/college-graduation.png"))); // NOI18N
        btnMenuSchool.setText("Escuelas");
        btnMenuSchool.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuSchool.setIconTextGap(10);
        btnMenuSchool.setMargin(null);
        btnMenuSchool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuSchoolActionPerformed(evt);
            }
        });

        btnMenuAuth.setBackground(new java.awt.Color(94, 151, 103));
        btnMenuAuth.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnMenuAuth.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuAuth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/police-shield-with-a-star-symbol.png"))); // NOI18N
        btnMenuAuth.setText("Autoridades");
        btnMenuAuth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuAuth.setIconTextGap(10);
        btnMenuAuth.setMargin(null);
        btnMenuAuth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuAuthActionPerformed(evt);
            }
        });

        btnMenuProv.setBackground(new java.awt.Color(94, 151, 103));
        btnMenuProv.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnMenuProv.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/wifi.png"))); // NOI18N
        btnMenuProv.setText("Proveedores");
        btnMenuProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuProv.setIconTextGap(10);
        btnMenuProv.setMargin(null);
        btnMenuProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuProvActionPerformed(evt);
            }
        });

        btnMenuType.setBackground(new java.awt.Color(94, 151, 103));
        btnMenuType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnMenuType.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/telephone.png"))); // NOI18N
        btnMenuType.setText("Tipos de denuncias");
        btnMenuType.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuType.setIconTextGap(10);
        btnMenuType.setMargin(null);
        btnMenuType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuTypeActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 119, 198));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Usuario conectado:");

        lblUser.setForeground(new java.awt.Color(255, 255, 255));
        lblUser.setText("Ninguno");
        lblUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUser.setLineWrap(true);
        lblUser.setMaximumSize(new java.awt.Dimension(230, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnMenuCalls.setBackground(new java.awt.Color(0, 74, 101));
        btnMenuCalls.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnMenuCalls.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuCalls.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/shopping-list.png"))); // NOI18N
        btnMenuCalls.setText("Lista de denuncias");
        btnMenuCalls.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuCalls.setIconTextGap(10);
        btnMenuCalls.setMargin(null);
        btnMenuCalls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuCallsActionPerformed(evt);
            }
        });

        btnMenuNew.setBackground(new java.awt.Color(94, 151, 103));
        btnMenuNew.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnMenuNew.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/telephone.png"))); // NOI18N
        btnMenuNew.setText("Nueva denuncia");
        btnMenuNew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuNew.setIconTextGap(10);
        btnMenuNew.setMargin(null);
        btnMenuNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuNewActionPerformed(evt);
            }
        });

        btnMenuReports.setBackground(new java.awt.Color(94, 151, 103));
        btnMenuReports.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnMenuReports.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folded-newspaper.png"))); // NOI18N
        btnMenuReports.setText("Reportes");
        btnMenuReports.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuReports.setIconTextGap(10);
        btnMenuReports.setMargin(null);
        btnMenuReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuReportsActionPerformed(evt);
            }
        });

        btnMenuProfile.setBackground(new java.awt.Color(94, 151, 103));
        btnMenuProfile.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnMenuProfile.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user-silhouette.png"))); // NOI18N
        btnMenuProfile.setText("Mi perfil");
        btnMenuProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuProfile.setIconTextGap(10);
        btnMenuProfile.setMargin(null);
        btnMenuProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMenuUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMenuSchool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMenuAuth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMenuProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMenuType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMenuCalls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMenuNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMenuReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMenuProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnMenuUser, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnMenuType, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnMenuSchool, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnMenuAuth, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnMenuProv, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnMenuCalls, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnMenuNew, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnMenuReports, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnMenuProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlMain.setLayout(new java.awt.CardLayout());

        pnlUser.setBackground(new java.awt.Color(255, 255, 255));
        pnlUser.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlUserComponentShown(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Usuarios");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Buscar por:");

        cmbUserSearchType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbUserSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Nombre", "Apellido", "Email", "Tipo de usuario", "Estado" }));
        cmbUserSearchType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUserSearchTypeActionPerformed(evt);
            }
        });

        txtUserSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUserSearch.setForeground(new java.awt.Color(6, 43, 51));
        txtUserSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserSearchActionPerformed(evt);
            }
        });
        txtUserSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserSearchKeyReleased(evt);
            }
        });

        cmbUserSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbUserSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbUserSearch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbUserSearchPropertyChange(evt);
            }
        });

        btnUserSearchReset.setBackground(new java.awt.Color(121, 121, 101));
        btnUserSearchReset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUserSearchReset.setForeground(new java.awt.Color(255, 255, 255));
        btnUserSearchReset.setText("Revertir");
        btnUserSearchReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserSearchReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserSearchResetActionPerformed(evt);
            }
        });

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Email", "Tipo", "Estado"
            }
        ));
        tblUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUser);
        if (tblUser.getColumnModel().getColumnCount() > 0) {
            tblUser.getColumnModel().getColumn(2).setResizable(false);
            tblUser.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        txtUserName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUserName.setForeground(new java.awt.Color(6, 43, 51));

        txtUserLastame.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUserLastame.setForeground(new java.awt.Color(6, 43, 51));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Apellido:");

        errUserName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errUserName.setForeground(new java.awt.Color(255, 0, 0));
        errUserName.setText("Error:");
        errUserName.setName(""); // NOI18N

        errUserLastname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errUserLastname.setForeground(new java.awt.Color(255, 0, 0));
        errUserLastname.setText("Error:");
        errUserLastname.setName(""); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Email:");

        txtUserEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUserEmail.setForeground(new java.awt.Color(6, 43, 51));
        txtUserEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserEmailKeyReleased(evt);
            }
        });

        errUserEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errUserEmail.setForeground(new java.awt.Color(255, 0, 0));
        errUserEmail.setText("Error:");
        errUserEmail.setName(""); // NOI18N

        errUserPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errUserPass.setForeground(new java.awt.Color(255, 0, 0));
        errUserPass.setText("Error:");
        errUserPass.setName(""); // NOI18N

        txtUserPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUserPass.setForeground(new java.awt.Color(6, 43, 51));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Contraseña:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Tipo de usuario:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Estado:");

        errUserType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errUserType.setForeground(new java.awt.Color(255, 0, 0));
        errUserType.setText("Error:");
        errUserType.setName(""); // NOI18N

        errUserState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errUserState.setForeground(new java.awt.Color(255, 0, 0));
        errUserState.setText("Error:");
        errUserState.setName(""); // NOI18N

        cmbUserType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbUserType.setForeground(new java.awt.Color(6, 43, 51));
        cmbUserType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Personal" }));

        cmbUserState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbUserState.setForeground(new java.awt.Color(6, 43, 51));
        cmbUserState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        btnUserClear.setBackground(new java.awt.Color(121, 121, 101));
        btnUserClear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUserClear.setForeground(new java.awt.Color(255, 255, 255));
        btnUserClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear-button.png"))); // NOI18N
        btnUserClear.setText("Limpiar");
        btnUserClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserClear.setIconTextGap(6);
        btnUserClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserClearActionPerformed(evt);
            }
        });

        btnUserAction.setBackground(new java.awt.Color(0, 50, 180));
        btnUserAction.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUserAction.setForeground(new java.awt.Color(255, 255, 255));
        btnUserAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-icon.png"))); // NOI18N
        btnUserAction.setText("Añadir");
        btnUserAction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserAction.setIconTextGap(6);
        btnUserAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUserLayout = new javax.swing.GroupLayout(pnlUser);
        pnlUser.setLayout(pnlUserLayout);
        pnlUserLayout.setHorizontalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(pnlUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(pnlUserLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cmbUserSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUserSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(cmbUserSearch, 0, 140, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addComponent(btnUserSearchReset))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUserLayout.createSequentialGroup()
                        .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(errUserName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errUserLastname)
                            .addComponent(jLabel4)
                            .addComponent(txtUserLastame, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(errUserType))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(189, 189, 189))
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbUserType, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(errUserEmail, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUserLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(errUserPass)
                                    .addComponent(jLabel6)
                                    .addComponent(txtUserPass, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlUserLayout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlUserLayout.createSequentialGroup()
                                        .addComponent(errUserState)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cmbUserState, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addComponent(btnUserClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUserAction, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlUserLayout.setVerticalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbUserSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUserSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserSearchReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(txtUserLastame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errUserName)
                    .addComponent(errUserLastname))
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(txtUserPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errUserEmail)
                    .addComponent(errUserPass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUserState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(errUserType)
                    .addComponent(errUserState))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUserClear)
                    .addComponent(btnUserAction))
                .addContainerGap())
        );

        errUserName.getAccessibleContext().setAccessibleName("errUserName");

        pnlMain.add(pnlUser, "crdUser");

        pnlType.setBackground(new java.awt.Color(255, 255, 255));
        pnlType.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlTypeComponentShown(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("Tipos de denuncias");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Buscar por:");

        cmbTypeSearchType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbTypeSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Nombre", "Acción tomada", "Estado" }));
        cmbTypeSearchType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTypeSearchTypeActionPerformed(evt);
            }
        });

        txtTypeSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTypeSearch.setForeground(new java.awt.Color(6, 43, 51));
        txtTypeSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTypeSearchKeyReleased(evt);
            }
        });

        cmbTypeSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbTypeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTypeSearch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbTypeSearchPropertyChange(evt);
            }
        });

        btnTypeSearchReset.setBackground(new java.awt.Color(121, 121, 101));
        btnTypeSearchReset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTypeSearchReset.setForeground(new java.awt.Color(255, 255, 255));
        btnTypeSearchReset.setText("Revertir");
        btnTypeSearchReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTypeSearchReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTypeSearchResetActionPerformed(evt);
            }
        });

        tblType.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Accion", "Estado"
            }
        ));
        tblType.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTypeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblType);
        if (tblType.getColumnModel().getColumnCount() > 0) {
            tblType.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Nombre:");

        txtTypeName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTypeName.setForeground(new java.awt.Color(6, 43, 51));

        errTypeName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errTypeName.setForeground(new java.awt.Color(255, 0, 0));
        errTypeName.setText("Error:");
        errTypeName.setName(""); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Acción tomada:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Estado:");

        errTypeAction.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errTypeAction.setForeground(new java.awt.Color(255, 0, 0));
        errTypeAction.setText("Error:");
        errTypeAction.setName(""); // NOI18N

        errTypeState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errTypeState.setForeground(new java.awt.Color(255, 0, 0));
        errTypeState.setText("Error:");
        errTypeState.setName(""); // NOI18N

        cmbTypeAction.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbTypeAction.setForeground(new java.awt.Color(6, 43, 51));
        cmbTypeAction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Remitir con autoridad competente", "Tomar contacto con ISP y colegio" }));

        cmbTypeState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbTypeState.setForeground(new java.awt.Color(6, 43, 51));
        cmbTypeState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        btnTypeClear.setBackground(new java.awt.Color(121, 121, 101));
        btnTypeClear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTypeClear.setForeground(new java.awt.Color(255, 255, 255));
        btnTypeClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear-button.png"))); // NOI18N
        btnTypeClear.setText("Limpiar");
        btnTypeClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTypeClear.setIconTextGap(6);
        btnTypeClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTypeClearActionPerformed(evt);
            }
        });

        btnTypeAction.setBackground(new java.awt.Color(0, 50, 180));
        btnTypeAction.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTypeAction.setForeground(new java.awt.Color(255, 255, 255));
        btnTypeAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-icon.png"))); // NOI18N
        btnTypeAction.setText("Añadir");
        btnTypeAction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTypeAction.setIconTextGap(6);
        btnTypeAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTypeActionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTypeLayout = new javax.swing.GroupLayout(pnlType);
        pnlType.setLayout(pnlTypeLayout);
        pnlTypeLayout.setHorizontalGroup(
            pnlTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(pnlTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTypeLayout.createSequentialGroup()
                        .addGroup(pnlTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(errTypeAction))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlTypeLayout.createSequentialGroup()
                        .addGroup(pnlTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTypeLayout.createSequentialGroup()
                                .addGroup(pnlTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(errTypeName)
                                    .addComponent(jLabel11))
                                .addGap(52, 52, 52)
                                .addGroup(pnlTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlTypeLayout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(189, 189, 189))
                                    .addComponent(cmbTypeState, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pnlTypeLayout.createSequentialGroup()
                                        .addComponent(errTypeState)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jScrollPane2)
                            .addGroup(pnlTypeLayout.createSequentialGroup()
                                .addGroup(pnlTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(pnlTypeLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbTypeSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTypeSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(cmbTypeSearch, 0, 140, Short.MAX_VALUE))
                                .addGap(35, 35, 35)
                                .addComponent(btnTypeSearchReset))
                            .addGroup(pnlTypeLayout.createSequentialGroup()
                                .addComponent(btnTypeClear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTypeAction, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlTypeLayout.createSequentialGroup()
                                .addComponent(cmbTypeAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        pnlTypeLayout.setVerticalGroup(
            pnlTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbTypeSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTypeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTypeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTypeSearchReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(pnlTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTypeLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTypeState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(errTypeState))
                    .addGroup(pnlTypeLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errTypeName)))
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTypeAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(errTypeAction)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addGroup(pnlTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTypeClear)
                    .addComponent(btnTypeAction))
                .addContainerGap())
        );

        pnlMain.add(pnlType, "crdType");

        pnlSchool.setBackground(new java.awt.Color(255, 255, 255));
        pnlSchool.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                pnlSchoolComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlSchoolComponentShown(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("Escuelas");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Buscar por:");

        cmbSchoolSearchType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbSchoolSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Nombre", "Dirección", "Estado" }));
        cmbSchoolSearchType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSchoolSearchTypeActionPerformed(evt);
            }
        });

        txtSchoolSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSchoolSearch.setForeground(new java.awt.Color(6, 43, 51));
        txtSchoolSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSchoolSearchKeyReleased(evt);
            }
        });

        cmbSchoolSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbSchoolSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cmbSchoolSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSchoolSearchActionPerformed(evt);
            }
        });
        cmbSchoolSearch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbSchoolSearchPropertyChange(evt);
            }
        });

        btnSchoolSearchReset.setBackground(new java.awt.Color(121, 121, 101));
        btnSchoolSearchReset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSchoolSearchReset.setForeground(new java.awt.Color(255, 255, 255));
        btnSchoolSearchReset.setText("Revertir");
        btnSchoolSearchReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSchoolSearchReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSchoolSearchResetActionPerformed(evt);
            }
        });

        tblSchool.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Dirección", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSchool.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSchool.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSchoolMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSchool);
        if (tblSchool.getColumnModel().getColumnCount() > 0) {
            tblSchool.getColumnModel().getColumn(1).setResizable(false);
            tblSchool.getColumnModel().getColumn(1).setHeaderValue("Dirección");
        }

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Nombre:");

        txtSchoolName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSchoolName.setForeground(new java.awt.Color(6, 43, 51));

        errSchoolName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errSchoolName.setForeground(new java.awt.Color(255, 0, 0));
        errSchoolName.setText("Error:");
        errSchoolName.setName(""); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Dirección");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Estado:");

        errSchoolAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errSchoolAddress.setForeground(new java.awt.Color(255, 0, 0));
        errSchoolAddress.setText("Error:");
        errSchoolAddress.setName(""); // NOI18N

        errSchoolState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errSchoolState.setForeground(new java.awt.Color(255, 0, 0));
        errSchoolState.setText("Error:");
        errSchoolState.setName(""); // NOI18N

        cmbSchoolState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbSchoolState.setForeground(new java.awt.Color(6, 43, 51));
        cmbSchoolState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        btnSchoolClear.setBackground(new java.awt.Color(121, 121, 101));
        btnSchoolClear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSchoolClear.setForeground(new java.awt.Color(255, 255, 255));
        btnSchoolClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear-button.png"))); // NOI18N
        btnSchoolClear.setText("Limpiar");
        btnSchoolClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSchoolClear.setIconTextGap(6);
        btnSchoolClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSchoolClearActionPerformed(evt);
            }
        });

        btnSchoolAction.setBackground(new java.awt.Color(0, 50, 180));
        btnSchoolAction.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSchoolAction.setForeground(new java.awt.Color(255, 255, 255));
        btnSchoolAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-icon.png"))); // NOI18N
        btnSchoolAction.setText("Añadir");
        btnSchoolAction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSchoolAction.setIconTextGap(6);
        btnSchoolAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSchoolActionActionPerformed(evt);
            }
        });

        txtSchoolAddress.setColumns(20);
        txtSchoolAddress.setLineWrap(true);
        txtSchoolAddress.setRows(2);
        jScrollPane4.setViewportView(txtSchoolAddress);

        javax.swing.GroupLayout pnlSchoolLayout = new javax.swing.GroupLayout(pnlSchool);
        pnlSchool.setLayout(pnlSchoolLayout);
        pnlSchoolLayout.setHorizontalGroup(
            pnlSchoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(pnlSchoolLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSchoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSchoolLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlSchoolLayout.createSequentialGroup()
                        .addGroup(pnlSchoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addGroup(pnlSchoolLayout.createSequentialGroup()
                                .addGroup(pnlSchoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSchoolName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(errSchoolName)
                                    .addComponent(jLabel14))
                                .addGap(52, 52, 52)
                                .addGroup(pnlSchoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlSchoolLayout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(189, 189, 189))
                                    .addComponent(cmbSchoolState, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pnlSchoolLayout.createSequentialGroup()
                                        .addComponent(errSchoolState)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jScrollPane3)
                            .addGroup(pnlSchoolLayout.createSequentialGroup()
                                .addGroup(pnlSchoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(pnlSchoolLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbSchoolSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlSchoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSchoolSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(cmbSchoolSearch, 0, 140, Short.MAX_VALUE))
                                .addGap(35, 35, 35)
                                .addComponent(btnSchoolSearchReset))
                            .addGroup(pnlSchoolLayout.createSequentialGroup()
                                .addComponent(btnSchoolClear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSchoolAction, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(pnlSchoolLayout.createSequentialGroup()
                        .addComponent(errSchoolAddress)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlSchoolLayout.setVerticalGroup(
            pnlSchoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSchoolLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSchoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cmbSchoolSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSchoolSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSchoolSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSchoolSearchReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(pnlSchoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSchoolLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSchoolState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(errSchoolState))
                    .addGroup(pnlSchoolLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSchoolName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errSchoolName)))
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errSchoolAddress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(pnlSchoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSchoolClear)
                    .addComponent(btnSchoolAction))
                .addContainerGap())
        );

        pnlMain.add(pnlSchool, "crdSchool");

        pnlAuth.setBackground(new java.awt.Color(255, 255, 255));
        pnlAuth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pnlAuthFocusGained(evt);
            }
        });
        pnlAuth.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                pnlAuthComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlAuthComponentShown(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setText("Autoridades");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Buscar por:");

        cmbAuthSearchType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbAuthSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Nombre", "Estado" }));
        cmbAuthSearchType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAuthSearchTypeActionPerformed(evt);
            }
        });

        txtAuthSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAuthSearch.setForeground(new java.awt.Color(6, 43, 51));
        txtAuthSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAuthSearchKeyReleased(evt);
            }
        });

        cmbAuthSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbAuthSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cmbAuthSearch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbAuthSearchPropertyChange(evt);
            }
        });

        btnAuthSearchReset.setBackground(new java.awt.Color(121, 121, 101));
        btnAuthSearchReset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAuthSearchReset.setForeground(new java.awt.Color(255, 255, 255));
        btnAuthSearchReset.setText("Revertir");
        btnAuthSearchReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAuthSearchReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuthSearchResetActionPerformed(evt);
            }
        });

        tblAuth.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Estado"
            }
        ));
        tblAuth.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblAuth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAuthMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblAuth);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Nombre:");

        txtAuthName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAuthName.setForeground(new java.awt.Color(6, 43, 51));

        errAuthName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errAuthName.setForeground(new java.awt.Color(255, 0, 0));
        errAuthName.setText("Error:");
        errAuthName.setName(""); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Estado:");

        errAuthState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errAuthState.setForeground(new java.awt.Color(255, 0, 0));
        errAuthState.setText("Error:");
        errAuthState.setName(""); // NOI18N

        cmbAuthState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbAuthState.setForeground(new java.awt.Color(6, 43, 51));
        cmbAuthState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        btnAuthClear.setBackground(new java.awt.Color(121, 121, 101));
        btnAuthClear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAuthClear.setForeground(new java.awt.Color(255, 255, 255));
        btnAuthClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear-button.png"))); // NOI18N
        btnAuthClear.setText("Limpiar");
        btnAuthClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAuthClear.setIconTextGap(6);
        btnAuthClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuthClearActionPerformed(evt);
            }
        });

        btnAuthAction.setBackground(new java.awt.Color(0, 50, 180));
        btnAuthAction.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAuthAction.setForeground(new java.awt.Color(255, 255, 255));
        btnAuthAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-icon.png"))); // NOI18N
        btnAuthAction.setText("Añadir");
        btnAuthAction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAuthAction.setIconTextGap(6);
        btnAuthAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuthActionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAuthLayout = new javax.swing.GroupLayout(pnlAuth);
        pnlAuth.setLayout(pnlAuthLayout);
        pnlAuthLayout.setHorizontalGroup(
            pnlAuthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(pnlAuthLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAuthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAuthLayout.createSequentialGroup()
                        .addGroup(pnlAuthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAuthName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(errAuthName)
                            .addComponent(jLabel21))
                        .addGap(52, 52, 52)
                        .addGroup(pnlAuthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAuthLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(189, 189, 189))
                            .addComponent(cmbAuthState, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlAuthLayout.createSequentialGroup()
                                .addComponent(errAuthState)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane5)
                    .addGroup(pnlAuthLayout.createSequentialGroup()
                        .addGroup(pnlAuthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(pnlAuthLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(cmbAuthSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlAuthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAuthSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(cmbAuthSearch, 0, 140, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addComponent(btnAuthSearchReset))
                    .addGroup(pnlAuthLayout.createSequentialGroup()
                        .addComponent(btnAuthClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAuthAction, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlAuthLayout.setVerticalGroup(
            pnlAuthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAuthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAuthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cmbAuthSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAuthSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAuthSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAuthSearchReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addGroup(pnlAuthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAuthLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbAuthState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(errAuthState))
                    .addGroup(pnlAuthLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAuthName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errAuthName)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addGroup(pnlAuthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAuthClear)
                    .addComponent(btnAuthAction))
                .addContainerGap())
        );

        pnlMain.add(pnlAuth, "crdAuth");

        pnlProv.setBackground(new java.awt.Color(255, 255, 255));
        pnlProv.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                pnlProvComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlProvComponentShown(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel22.setText("Proveedores");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Buscar por:");

        cmbProvSearchType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbProvSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Nombre", "Estado" }));
        cmbProvSearchType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvSearchTypeActionPerformed(evt);
            }
        });
        cmbProvSearchType.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbProvSearchTypePropertyChange(evt);
            }
        });

        txtProvSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProvSearch.setForeground(new java.awt.Color(6, 43, 51));
        txtProvSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProvSearchKeyReleased(evt);
            }
        });

        cmbProvSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbProvSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cmbProvSearch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbProvSearchPropertyChange(evt);
            }
        });

        btnProvSearchReset.setBackground(new java.awt.Color(121, 121, 101));
        btnProvSearchReset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnProvSearchReset.setForeground(new java.awt.Color(255, 255, 255));
        btnProvSearchReset.setText("Revertir");
        btnProvSearchReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProvSearchReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProvSearchResetActionPerformed(evt);
            }
        });

        tblProv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Estado"
            }
        ));
        tblProv.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProvMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblProv);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Nombre:");

        txtProvName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProvName.setForeground(new java.awt.Color(6, 43, 51));

        errProvName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errProvName.setForeground(new java.awt.Color(255, 0, 0));
        errProvName.setText("Error:");
        errProvName.setName(""); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Estado:");

        errProvState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errProvState.setForeground(new java.awt.Color(255, 0, 0));
        errProvState.setText("Error:");
        errProvState.setName(""); // NOI18N

        cmbProvState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbProvState.setForeground(new java.awt.Color(6, 43, 51));
        cmbProvState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        btnProvClear.setBackground(new java.awt.Color(121, 121, 101));
        btnProvClear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnProvClear.setForeground(new java.awt.Color(255, 255, 255));
        btnProvClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear-button.png"))); // NOI18N
        btnProvClear.setText("Limpiar");
        btnProvClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProvClear.setIconTextGap(6);
        btnProvClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProvClearActionPerformed(evt);
            }
        });

        btnProvAction.setBackground(new java.awt.Color(0, 50, 180));
        btnProvAction.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnProvAction.setForeground(new java.awt.Color(255, 255, 255));
        btnProvAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-icon.png"))); // NOI18N
        btnProvAction.setText("Añadir");
        btnProvAction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProvAction.setIconTextGap(6);
        btnProvAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProvActionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProvLayout = new javax.swing.GroupLayout(pnlProv);
        pnlProv.setLayout(pnlProvLayout);
        pnlProvLayout.setHorizontalGroup(
            pnlProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(pnlProvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProvLayout.createSequentialGroup()
                        .addGroup(pnlProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProvName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(errProvName)
                            .addComponent(jLabel25))
                        .addGap(52, 52, 52)
                        .addGroup(pnlProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlProvLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(189, 189, 189))
                            .addComponent(cmbProvState, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlProvLayout.createSequentialGroup()
                                .addComponent(errProvState)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane6)
                    .addGroup(pnlProvLayout.createSequentialGroup()
                        .addGroup(pnlProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(pnlProvLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(cmbProvSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtProvSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(cmbProvSearch, 0, 140, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addComponent(btnProvSearchReset))
                    .addGroup(pnlProvLayout.createSequentialGroup()
                        .addComponent(btnProvClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnProvAction, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlProvLayout.setVerticalGroup(
            pnlProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cmbProvSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProvSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProvSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProvSearchReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addGroup(pnlProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProvLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbProvState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(errProvState))
                    .addGroup(pnlProvLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProvName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errProvName)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addGroup(pnlProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProvClear)
                    .addComponent(btnProvAction))
                .addContainerGap())
        );

        pnlMain.add(pnlProv, "crdProv");

        pnlCalls.setBackground(new java.awt.Color(255, 255, 255));
        pnlCalls.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                pnlCallsComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlCallsComponentShown(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel28.setText("Lista de denuncias");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Buscar por:");

        cmbCallsSearchType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbCallsSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "Escuela", "Tipo", "Descripción", "Viable", "Registradas por mí" }));
        cmbCallsSearchType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCallsSearchTypeActionPerformed(evt);
            }
        });

        btnAuthSearchReset1.setBackground(new java.awt.Color(121, 121, 101));
        btnAuthSearchReset1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAuthSearchReset1.setForeground(new java.awt.Color(255, 255, 255));
        btnAuthSearchReset1.setText("Revertir");
        btnAuthSearchReset1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAuthSearchReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuthSearchReset1ActionPerformed(evt);
            }
        });

        tblCalls.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Escuela", "Tipo", "Descripción", "Viable", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCalls.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane7.setViewportView(tblCalls);

        dtpCallsFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dtpCallsFromActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("Fecha:");

        dtpCallsTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dtpCallsToActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("-");

        btnCallsDetail.setBackground(new java.awt.Color(0, 50, 180));
        btnCallsDetail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCallsDetail.setForeground(new java.awt.Color(255, 255, 255));
        btnCallsDetail.setText("Ver detalle de denuncia");
        btnCallsDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCallsDetail.setIconTextGap(6);
        btnCallsDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCallsDetailActionPerformed(evt);
            }
        });

        btnCallsSchoolSearch.setBackground(new java.awt.Color(204, 204, 204));
        btnCallsSchoolSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCallsSchoolSearch.setText("...");
        btnCallsSchoolSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCallsSchoolSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCallsSchoolSearchActionPerformed(evt);
            }
        });

        pnlCallsParam.setBackground(new java.awt.Color(255, 255, 255));
        pnlCallsParam.setMaximumSize(new java.awt.Dimension(140, 2147483647));
        pnlCallsParam.setPreferredSize(new java.awt.Dimension(140, 23));
        pnlCallsParam.setLayout(new java.awt.CardLayout());

        cmbCallsSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbCallsSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCallsSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCallsSearchActionPerformed(evt);
            }
        });
        pnlCallsParam.add(cmbCallsSearch, "callsCmb");

        lblCallsSchool.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCallsSchool.setForeground(new java.awt.Color(6, 43, 51));
        lblCallsSchool.setText("Ninguna");
        lblCallsSchool.setMaximumSize(new java.awt.Dimension(140, 17));
        lblCallsSchool.setPreferredSize(new java.awt.Dimension(140, 23));
        pnlCallsParam.add(lblCallsSchool, "callsLbl");

        txtCallsSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCallsSearch.setForeground(new java.awt.Color(6, 43, 51));
        txtCallsSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCallsSearchKeyReleased(evt);
            }
        });
        pnlCallsParam.add(txtCallsSearch, "callsTxt");

        javax.swing.GroupLayout pnlCallsLayout = new javax.swing.GroupLayout(pnlCalls);
        pnlCalls.setLayout(pnlCallsLayout);
        pnlCallsLayout.setHorizontalGroup(
            pnlCallsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator6)
            .addGroup(pnlCallsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCallsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(pnlCallsLayout.createSequentialGroup()
                        .addGroup(pnlCallsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCallsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbCallsSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlCallsLayout.createSequentialGroup()
                                .addComponent(dtpCallsFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(pnlCallsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlCallsParam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtpCallsTo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(pnlCallsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAuthSearchReset1)
                            .addComponent(btnCallsSchoolSearch)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCallsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCallsDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel28))
                .addContainerGap())
        );
        pnlCallsLayout.setVerticalGroup(
            pnlCallsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCallsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCallsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCallsParam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCallsSearchType)
                    .addGroup(pnlCallsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29)
                        .addComponent(btnCallsSchoolSearch)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCallsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dtpCallsFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(dtpCallsTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(btnAuthSearchReset1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCallsDetail)
                .addContainerGap())
        );

        pnlMain.add(pnlCalls, "crdCalls");

        pnlNew.setBackground(new java.awt.Color(255, 255, 255));
        pnlNew.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlNewComponentShown(evt);
            }
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                pnlNewComponentHidden(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel32.setText("Nueva denuncia");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Escuela desde la que se reporta:");

        errNewSchool.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errNewSchool.setForeground(new java.awt.Color(255, 0, 0));
        errNewSchool.setText("Seleccione la escuela desde la que se está denunciando");
        errNewSchool.setName(""); // NOI18N

        btnNewClear.setBackground(new java.awt.Color(121, 121, 101));
        btnNewClear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNewClear.setForeground(new java.awt.Color(255, 255, 255));
        btnNewClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear-button.png"))); // NOI18N
        btnNewClear.setText("Limpiar");
        btnNewClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewClear.setIconTextGap(6);
        btnNewClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewClearActionPerformed(evt);
            }
        });

        btnNewSave.setBackground(new java.awt.Color(0, 50, 180));
        btnNewSave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNewSave.setForeground(new java.awt.Color(255, 255, 255));
        btnNewSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-icon.png"))); // NOI18N
        btnNewSave.setText("Añadir");
        btnNewSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewSave.setIconTextGap(6);
        btnNewSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewSaveActionPerformed(evt);
            }
        });

        btnNewSchoolSearch.setBackground(new java.awt.Color(204, 204, 204));
        btnNewSchoolSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNewSchoolSearch.setText("...");
        btnNewSchoolSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewSchoolSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewSchoolSearchActionPerformed(evt);
            }
        });

        lblNewSchool.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNewSchool.setForeground(new java.awt.Color(6, 43, 51));
        lblNewSchool.setText("Ninguna");
        lblNewSchool.setPreferredSize(new java.awt.Dimension(302, 17));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("Descripción:");

        txtNewDesc.setColumns(20);
        txtNewDesc.setForeground(new java.awt.Color(6, 43, 51));
        txtNewDesc.setLineWrap(true);
        txtNewDesc.setRows(2);
        jScrollPane8.setViewportView(txtNewDesc);

        errNewDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errNewDescription.setForeground(new java.awt.Color(255, 0, 0));
        errNewDescription.setText("Proporcione una descripción de la denuncia");
        errNewDescription.setName(""); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("Tipo de denuncia:");

        cmbNewType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbNewType.setForeground(new java.awt.Color(6, 43, 51));
        cmbNewType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbNewType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNewTypeActionPerformed(evt);
            }
        });

        errNewType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errNewType.setForeground(new java.awt.Color(255, 0, 0));
        errNewType.setText("Elija un tipo de denuncia");
        errNewType.setName(""); // NOI18N

        chbNewViable.setBackground(new java.awt.Color(255, 255, 255));
        chbNewViable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chbNewViable.setSelected(true);
        chbNewViable.setText("La denuncia es viable");
        chbNewViable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbNewViableActionPerformed(evt);
            }
        });

        lblList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblList.setText("Autoridades a notificar:");

        lstNew.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lstNew.setForeground(new java.awt.Color(6, 43, 51));
        lstNew.setModel(new DefaultListModel());
        lstNew.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstNewValueChanged(evt);
            }
        });
        scrLstNew.setViewportView(lstNew);

        errNewList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errNewList.setForeground(new java.awt.Color(255, 0, 0));
        errNewList.setText("Seleccione al menos una autoridad a notificar");
        errNewList.setName(""); // NOI18N

        btnNewListSearch.setBackground(new java.awt.Color(204, 204, 204));
        btnNewListSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNewListSearch.setText("...");
        btnNewListSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewListSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewListSearchActionPerformed(evt);
            }
        });

        btnNewListDel.setBackground(new java.awt.Color(121, 121, 101));
        btnNewListDel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNewListDel.setForeground(new java.awt.Color(255, 255, 255));
        btnNewListDel.setText("Desvincular autoridad");
        btnNewListDel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewListDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewListDelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNewLayout = new javax.swing.GroupLayout(pnlNew);
        pnlNew.setLayout(pnlNewLayout);
        pnlNewLayout.setHorizontalGroup(
            pnlNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator7)
            .addGroup(pnlNewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNewLayout.createSequentialGroup()
                        .addGroup(pnlNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlNewLayout.createSequentialGroup()
                                .addComponent(btnNewClear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNewSave, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlNewLayout.createSequentialGroup()
                                .addGroup(pnlNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(errNewSchool, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(errNewDescription, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(pnlNewLayout.createSequentialGroup()
                        .addComponent(cmbNewType, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chbNewViable)
                        .addGap(64, 64, 64))
                    .addGroup(pnlNewLayout.createSequentialGroup()
                        .addGroup(pnlNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlNewLayout.createSequentialGroup()
                                .addComponent(lblList)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNewListSearch))
                            .addGroup(pnlNewLayout.createSequentialGroup()
                                .addGroup(pnlNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(errNewType))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(pnlNewLayout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNewSchool, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNewSchoolSearch)
                        .addContainerGap())
                    .addGroup(pnlNewLayout.createSequentialGroup()
                        .addComponent(errNewList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNewListDel)
                        .addContainerGap())
                    .addGroup(pnlNewLayout.createSequentialGroup()
                        .addComponent(scrLstNew)
                        .addContainerGap())))
        );
        pnlNewLayout.setVerticalGroup(
            pnlNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(lblNewSchool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewSchoolSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errNewSchool)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errNewDescription)
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbNewType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbNewViable))
                .addGap(7, 7, 7)
                .addComponent(errNewType)
                .addGap(18, 18, 18)
                .addGroup(pnlNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblList)
                    .addComponent(btnNewListSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrLstNew, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNewListDel)
                    .addComponent(errNewList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(pnlNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewClear)
                    .addComponent(btnNewSave))
                .addContainerGap())
        );

        pnlMain.add(pnlNew, "crdNew");

        pnlCallDetail.setBackground(new java.awt.Color(255, 255, 255));
        pnlCallDetail.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                pnlCallDetailComponentHidden(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel36.setText("Detalle de denuncia");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText("Escuela desde la que se reporta:");

        btnDetailBack.setBackground(new java.awt.Color(121, 121, 101));
        btnDetailBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDetailBack.setForeground(new java.awt.Color(255, 255, 255));
        btnDetailBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrow-left-direction.png"))); // NOI18N
        btnDetailBack.setText("Volver");
        btnDetailBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetailBack.setIconTextGap(6);
        btnDetailBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailBackActionPerformed(evt);
            }
        });

        btnDetailSave.setBackground(new java.awt.Color(0, 50, 180));
        btnDetailSave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDetailSave.setForeground(new java.awt.Color(255, 255, 255));
        btnDetailSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-icon.png"))); // NOI18N
        btnDetailSave.setText("Guardar cambios");
        btnDetailSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetailSave.setIconTextGap(6);
        btnDetailSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailSaveActionPerformed(evt);
            }
        });

        lblDetailSchool.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDetailSchool.setForeground(new java.awt.Color(6, 43, 51));
        lblDetailSchool.setText("Ninguna");
        lblDetailSchool.setMaximumSize(new java.awt.Dimension(321, 17));
        lblDetailSchool.setPreferredSize(new java.awt.Dimension(366, 17));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setText("Descripción:");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("Tipo de denuncia:");

        lblAsigns.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblAsigns.setText("Autoridades/Proveedores notificados:");

        lblDetailArchived.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDetailArchived.setForeground(new java.awt.Color(255, 0, 0));
        lblDetailArchived.setText("ARCHIVADA");
        lblDetailArchived.setToolTipText("Denuncia no viable");

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setText("Registrada por usuario:");

        lblDetailUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDetailUser.setForeground(new java.awt.Color(6, 43, 51));
        lblDetailUser.setText("Ninguno");
        lblDetailUser.setMaximumSize(new java.awt.Dimension(321, 17));
        lblDetailUser.setPreferredSize(new java.awt.Dimension(366, 17));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setText("Fecha de registro:");

        lblDetailDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDetailDate.setForeground(new java.awt.Color(6, 43, 51));
        lblDetailDate.setText("Ninguna");
        lblDetailDate.setMaximumSize(new java.awt.Dimension(321, 17));
        lblDetailDate.setPreferredSize(new java.awt.Dimension(366, 17));

        lblDetailType.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDetailType.setForeground(new java.awt.Color(6, 43, 51));
        lblDetailType.setText("Ninguna");
        lblDetailType.setMaximumSize(new java.awt.Dimension(321, 17));
        lblDetailType.setPreferredSize(new java.awt.Dimension(366, 17));

        btnDetailReport.setBackground(new java.awt.Color(0, 50, 180));
        btnDetailReport.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDetailReport.setForeground(new java.awt.Color(255, 255, 255));
        btnDetailReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folded-newspaper.png"))); // NOI18N
        btnDetailReport.setText("Generar reporte");
        btnDetailReport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetailReport.setIconTextGap(6);
        btnDetailReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailReportActionPerformed(evt);
            }
        });

        chbDetailTalk.setBackground(new java.awt.Color(255, 255, 255));
        chbDetailTalk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chbDetailTalk.setText("Se impartieron charlas en el centro escolar para prevenir fenómenos similares");

        tblAsigns.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proveedor", "Retiró el contenido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrAsigns.setViewportView(tblAsigns);
        if (tblAsigns.getColumnModel().getColumnCount() > 0) {
            tblAsigns.getColumnModel().getColumn(1).setResizable(false);
        }

        lblDetailDescription.setEditable(false);
        lblDetailDescription.setColumns(20);
        lblDetailDescription.setForeground(new java.awt.Color(6, 43, 51));
        lblDetailDescription.setLineWrap(true);
        lblDetailDescription.setRows(2);
        jScrollPane11.setViewportView(lblDetailDescription);

        javax.swing.GroupLayout pnlCallDetailLayout = new javax.swing.GroupLayout(pnlCallDetail);
        pnlCallDetail.setLayout(pnlCallDetailLayout);
        pnlCallDetailLayout.setHorizontalGroup(
            pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator8)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCallDetailLayout.createSequentialGroup()
                .addGroup(pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCallDetailLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCallDetailLayout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDetailArchived))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCallDetailLayout.createSequentialGroup()
                                .addGroup(pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCallDetailLayout.createSequentialGroup()
                                            .addComponent(btnDetailBack)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnDetailReport, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCallDetailLayout.createSequentialGroup()
                                            .addComponent(jLabel40)
                                            .addGap(60, 60, 60)
                                            .addComponent(lblDetailUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(pnlCallDetailLayout.createSequentialGroup()
                                            .addComponent(jLabel41)
                                            .addGap(92, 92, 92)
                                            .addComponent(lblDetailDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCallDetailLayout.createSequentialGroup()
                                            .addGroup(pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel37)
                                                .addComponent(jLabel39))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblDetailType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblDetailSchool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(pnlCallDetailLayout.createSequentialGroup()
                                            .addGap(166, 166, 166)
                                            .addComponent(btnDetailSave)))
                                    .addComponent(scrAsigns, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlCallDetailLayout.createSequentialGroup()
                        .addGroup(pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCallDetailLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(chbDetailTalk))
                            .addGroup(pnlCallDetailLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel38))
                            .addGroup(pnlCallDetailLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblAsigns)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlCallDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCallDetailLayout.setVerticalGroup(
            pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCallDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(lblDetailArchived))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(lblDetailUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(lblDetailDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(lblDetailSchool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(lblDetailType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblAsigns)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrAsigns, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chbDetailTalk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnDetailSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCallDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetailBack)
                    .addComponent(btnDetailReport))
                .addContainerGap())
        );

        pnlMain.add(pnlCallDetail, "crdCallDetail");

        pnlReports.setBackground(new java.awt.Color(255, 255, 255));
        pnlReports.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlReportsComponentShown(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel42.setText("Reportes");

        btnReport1.setBackground(new java.awt.Color(100, 189, 244));
        btnReport1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnReport1.setForeground(new java.awt.Color(255, 255, 255));
        btnReport1.setText("Denuncias por tipo");
        btnReport1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReport1.setIconTextGap(6);
        btnReport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReport1ActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel46.setText("Fecha:");

        dtpReportsFrom.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                dtpReportsFromComponentShown(evt);
            }
        });
        dtpReportsFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dtpReportsFromActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setText("Desde:");

        dtpReportsTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dtpReportsToActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setText("Hasta:");

        btnReport2.setBackground(new java.awt.Color(0, 119, 198));
        btnReport2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnReport2.setForeground(new java.awt.Color(255, 255, 255));
        btnReport2.setText("Denuncias por viabilidad");
        btnReport2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReport2.setIconTextGap(6);
        btnReport2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReport2ActionPerformed(evt);
            }
        });

        btnReport3.setBackground(new java.awt.Color(8, 80, 127));
        btnReport3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnReport3.setForeground(new java.awt.Color(255, 255, 255));
        btnReport3.setText("Top 10 instituciones con más denuncias");
        btnReport3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReport3.setIconTextGap(6);
        btnReport3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReport3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlReportsLayout = new javax.swing.GroupLayout(pnlReports);
        pnlReports.setLayout(pnlReportsLayout);
        pnlReportsLayout.setHorizontalGroup(
            pnlReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator9)
            .addGroup(pnlReportsLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dtpReportsFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dtpReportsTo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(pnlReportsLayout.createSequentialGroup()
                .addGroup(pnlReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlReportsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel46)))
                    .addGroup(pnlReportsLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(pnlReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnReport3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReport2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReport1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlReportsLayout.setVerticalGroup(
            pnlReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReportsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel46)
                .addGap(25, 25, 25)
                .addGroup(pnlReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dtpReportsTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtpReportsFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48)
                    .addComponent(jLabel47))
                .addGap(56, 56, 56)
                .addComponent(btnReport1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnReport2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnReport3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        pnlMain.add(pnlReports, "crdReports");

        pnlProfile.setBackground(new java.awt.Color(255, 255, 255));
        pnlProfile.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                pnlProfileComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                pnlProfileComponentShown(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel43.setText("Mi perfil");

        errProfileName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errProfileName.setForeground(new java.awt.Color(255, 0, 0));
        errProfileName.setText("Error:");
        errProfileName.setName(""); // NOI18N

        errProfileEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errProfileEmail.setForeground(new java.awt.Color(255, 0, 0));
        errProfileEmail.setText("Error:");
        errProfileEmail.setName(""); // NOI18N

        errProfilePass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        errProfilePass.setForeground(new java.awt.Color(255, 0, 0));
        errProfilePass.setText("Error:");
        errProfilePass.setName(""); // NOI18N

        btnUserClear1.setBackground(new java.awt.Color(121, 121, 101));
        btnUserClear1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUserClear1.setForeground(new java.awt.Color(255, 255, 255));
        btnUserClear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear-button.png"))); // NOI18N
        btnUserClear1.setText("Limpiar");
        btnUserClear1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserClear1.setIconTextGap(6);
        btnUserClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserClear1ActionPerformed(evt);
            }
        });

        btnUserAction1.setBackground(new java.awt.Color(0, 50, 180));
        btnUserAction1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUserAction1.setForeground(new java.awt.Color(255, 255, 255));
        btnUserAction1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-icon.png"))); // NOI18N
        btnUserAction1.setText("Guardar cambios");
        btnUserAction1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUserAction1.setIconTextGap(6);
        btnUserAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserAction1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(231, 231, 231));

        txtProfileLastName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProfileLastName.setForeground(new java.awt.Color(6, 43, 51));
        txtProfileLastName.setEnabled(false);

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel49.setText("Apellido:");

        txtProfileName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProfileName.setForeground(new java.awt.Color(6, 43, 51));
        txtProfileName.setEnabled(false);

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setText("Nombre:");

        btnEditName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEditName.setText("EDITAR");
        btnEditName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel49)
                    .addComponent(jLabel45)
                    .addComponent(txtProfileName, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(txtProfileLastName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnEditName, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProfileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(txtProfileLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addComponent(btnEditName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(231, 231, 231));

        btnEditEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEditEmail.setText("EDITAR");
        btnEditEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditEmailActionPerformed(evt);
            }
        });

        txtProfileEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProfileEmail.setForeground(new java.awt.Color(6, 43, 51));
        txtProfileEmail.setEnabled(false);

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel50.setText("Email:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProfileEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnEditEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEditEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProfileEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(231, 231, 231));

        btnEditPass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEditPass.setText("EDITAR");
        btnEditPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPassActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel51.setText("Contraseña:");

        txtProfilePass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProfilePass.setForeground(new java.awt.Color(6, 43, 51));
        txtProfilePass.setEnabled(false);

        txtProfileConfirm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProfileConfirm.setForeground(new java.awt.Color(6, 43, 51));
        txtProfileConfirm.setEnabled(false);

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel56.setText("Repetir contraseña:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51)
                    .addComponent(txtProfilePass, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56)
                    .addComponent(txtProfileConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnEditPass, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEditPass, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProfilePass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProfileConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlProfileLayout = new javax.swing.GroupLayout(pnlProfile);
        pnlProfile.setLayout(pnlProfileLayout);
        pnlProfileLayout.setHorizontalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator10)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProfileLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlProfileLayout.createSequentialGroup()
                                .addComponent(btnUserClear1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                                .addComponent(btnUserAction1))
                            .addGroup(pnlProfileLayout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlProfileLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlProfileLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(errProfileName)
                                    .addComponent(errProfileEmail)
                                    .addComponent(errProfilePass))))))
                .addContainerGap())
        );
        pnlProfileLayout.setVerticalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errProfileName)
                .addGap(23, 23, 23)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errProfileEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errProfilePass)
                .addGap(59, 59, 59)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUserClear1)
                    .addComponent(btnUserAction1))
                .addContainerGap())
        );

        pnlMain.add(pnlProfile, "crdProfile");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlMenu.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Cambia el panel actual
     *
     * @param cardName Nombre del panel a mostrar
     */
    private void showCard(String cardName) {
        CardLayout card = (CardLayout) pnlMain.getLayout();
        card.show(pnlMain, cardName);
    }

    /**
     * Cambia un botón de acción, de 'Añadir' a 'Modificar' y viceversa
     *
     * @param btn El botón a cambiar
     * @param changeToSave true si se cambiará a 'Añadir', false si se cambiará
     * a 'Modificar'
     */
    private void toggleAction(JButton btn, boolean changeToSave) {
        if (changeToSave) {
            btn.setText("Añadir");
            btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-icon.png")));
        } else {
            btn.setText("Modificar");
            btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh-button.png")));
        }
    }

    /**
     * Resalta el botón del menú seleccionado y normaliza los demás
     *
     * @param btn
     */
    private void selectItem(JButton item) {
        for (JButton btn : adminBtns) {
            btn.setBackground(new Color(94, 151, 103));
        }
        for (JButton btn : userBtns) {
            btn.setBackground(new Color(94, 151, 103));
        }
        item.setBackground(new Color(0, 74, 101));
    }

    /**
     * Refrezca la información de los controles de modo admin
     */
    private void refreshAdmin() {

    }

    /**
     * Refrezca la información de los controles de modo personal
     */
    private void refreshUser () {
        
        
    }

    /*----------------------------- ------------------------------------------
       -------------------------------▲---------------------------------------
       ---------------- ------------ ▲‌ ▲--------------------------------------
       --------------------------CODIGO DE NUEVA DENUNCIA ---------------------*/
    
    private void refreshPnlNew () {
        //pnlNew
        DefaultComboBoxModel newModel = (DefaultComboBoxModel) cmbNewType.getModel();
        newModel.removeAllElements();
        
        newModel.addElement("Ninguno");
        
        for (Complaint_type type : new ComplaintTypeController().getAll(true))
            newModel.addElement(type);
    }
    
    /**
     * Muestra u oculta la lista de autoridades/proveedores en función de el
     * tipo de denuncia seleccionado, y de si la denuncia está marcada como
     * viable o no. Si se detecta un cambio de "Acción a tomar" (atributo del
     * tipo de denuncia), se limpia la lista
     */
    private void showNewList() {
        try {
            if (((Complaint_type) cmbNewType.getSelectedItem()).getTaken_action().equals("Remitir con autoridad competente") && lblList.getText().equals("Proveedores a notificar:")) {

                lblList.setText("Autoridades a notificar:");
                btnNewListDel.setText("Desvincular autoridad");
                errNewList.setText("Seleccione al menos una autoridad a notificar");
                ((DefaultListModel) lstNew.getModel()).removeAllElements();

            } else if (((Complaint_type) cmbNewType.getSelectedItem()).getTaken_action().equals("Tomar contacto con ISP y colegio") && lblList.getText().equals("Autoridades a notificar:")) {
                lblList.setText("Proveedores a notificar:");
                btnNewListDel.setText("Desvincular proveedor");
                errNewList.setText("Seleccione al menos un proveedor a notificar");
                ((DefaultListModel) lstNew.getModel()).removeAllElements();
            }

            if (chbNewViable.isSelected()) {
                Animations.visibilizeComponents(this, lblList, scrLstNew, btnNewListSearch);
            } else {
                Animations.invisibilizeComponents(this, lblList, scrLstNew, btnNewListSearch, btnNewListDel);
                Animations.hide(errNewList, 255, 0, 0);
            }

        } catch (Exception e) {
            Animations.invisibilizeComponents(this, lblList, scrLstNew, btnNewListSearch, btnNewListDel);
            Animations.hide(errNewList, 255, 0, 0);
        }
    }

    /**
     * Devuelve los campos de pnlNew a sus valores por defecto
     */
    private void clearPnlNew() {
        callSchool = null;
        lblNewSchool.setText("Ninguna");
        txtNewDesc.setText("");
        cmbNewType.setSelectedIndex(0);
        chbNewViable.setSelected(true);
        ((DefaultListModel)lstNew.getModel()).removeAllElements();
        Animations.invisibilizeComponents(this, lblList, btnNewListSearch, scrLstNew, btnNewListDel);
        
        JLabel[] errorList = {
            errNewSchool, errNewDescription, errNewType, errNewList,};

        //Ocultando los label de error
        for (JLabel err : errorList) {
            Animations.hide(err, 255, 0, 0);
        }
    }
    
    
    /*----------------------------- ------------------------------------------
       -------------------------------▲---------------------------------------
       ---------------- ------------ ▲‌ ▲--------------------------------------
       --------------------------CODIGO DE LISTA DE DENUNCIAS------------------*/
    
    private void refreshPnlCalls () {
        //pnlCalls
        DefaultTableModel callsModel = (DefaultTableModel) tblCalls.getModel();
        while (callsModel.getRowCount() > 0) callsModel.removeRow(0);
        
        for (Call call : new CallController().getAll()) {
            callsModel.addRow(new Object[]{
                call.getSchool(),
                call.getComplaint_type(),
                call,
                call.getViable() ? "Es viable" : "No es viable",
                Utils.formatDate(call.getCall_date(), Utils.DATE_UI)
            });
        }
    }
    
    private void clearPnlCalls () {
        cmbCallsSearchType.setSelectedIndex(0);
        callSchool = null;
        lblCallsSchool.setText("Ninguna");
        txtCallsSearch.setText("");
        dtpCallsFrom.setDate(null);
        dtpCallsFrom.getMonthView().setUpperBound(null);
        dtpCallsTo.setDate(null);
        dtpCallsTo.getMonthView().setLowerBound(null);
        Animations.invisibilizeComponents(this, pnlCallsParam, btnCallsSchoolSearch);
    }
    
    private void triggerCallsSearch () {
        try {
            int type = 0;
            Object param = null;
            String from = "";
            String to = "";
            
            switch(String.valueOf(cmbCallsSearchType.getSelectedItem())) {
                case "Escuela":
                    type = CallController.BY_SCHOOL;
                    param = callSchool;
                    break;
                case "Tipo":
                    type = CallController.BY_TYPE;
                    param = cmbCallsSearch.getSelectedItem();
                    break;
                case "Descripción":
                    type = CallController.BY_DESCRIPTION;
                    param = txtCallsSearch.getText().trim();
                    break;
                case "Viable":
                    type = CallController.BY_VIABLE;
                    param = cmbCallsSearch.getSelectedItem();
                    break;
                case "Registradas por mí":
                    type = CallController.BY_USER;
                    param = user;
                    break;
                case "N/A":
                    type = CallController.NO_FIELD;
                    break;
            }
            
            //Seteando fechas si fueron seleccionadas
            try {from = Utils.formatDate(dtpCallsFrom.getDate(), Utils.DATE_DB);} catch (Exception e) {}
            try {to = Utils.formatDate(dtpCallsTo.getDate(), Utils.DATE_DB);} catch (Exception e) {}
            
            DefaultTableModel model = (DefaultTableModel) tblCalls.getModel();
            while (model.getRowCount() > 0) model.removeRow(0);

            for (Call object : new CallController().search(type, param, from, to)) {
                model.addRow(new Object[]{
                    object.getSchool(),
                    object.getComplaint_type(),
                    object,
                    object.getViable() ? "Es viable" : "No es viable",
                    Utils.formatDate(object.getCall_date(), Utils.DATE_UI)
                });
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    /**
     * Cambia el contenido del filtro en pnlCalls
     * @param cardName Nombre del panel a mostrar
     */
    private void showCallsCard(String cardName) {
        CardLayout card = (CardLayout) pnlCallsParam.getLayout();
        card.show(pnlCallsParam, cardName);
    }


    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        Login form = new Login();
        form.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnMenuUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuUserActionPerformed
        showCard("crdUser");
        selectItem((JButton) evt.getSource());
    }//GEN-LAST:event_btnMenuUserActionPerformed

    private void cmbUserSearchTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUserSearchTypeActionPerformed
        if (cmbUserSearchType.getSelectedIndex() == 0) {
            Animations.invisibilizeComponents(this, txtUserSearch, cmbUserSearch);
        } else if (cmbUserSearchType.getSelectedIndex() <= 3) {
            Animations.toggleVisible(this, txtUserSearch, cmbUserSearch);
        } else {
            Animations.toggleVisible(this, cmbUserSearch, txtUserSearch);
        }
        DefaultComboBoxModel df = new DefaultComboBoxModel();
        if(cmbUserSearchType.getSelectedIndex()==4){
            
            df.addElement(new User_type(1,"Administrador"));
            df.addElement(new User_type(2,"Personal"));
        }else if(cmbUserSearchType.getSelectedIndex()==5){
            df.addElement("Activo");
            df.addElement("Inactivo");
        }
        cmbUserSearch.setModel(df);

    }//GEN-LAST:event_cmbUserSearchTypeActionPerformed

    private void btnUserClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserClearActionPerformed
        clearUserFields();
        fillUserTable();
    }//GEN-LAST:event_btnUserClearActionPerformed

    private void cmbTypeSearchTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeSearchTypeActionPerformed
        if (cmbTypeSearchType.getSelectedIndex() == 0) {
            Animations.invisibilizeComponents(this, txtTypeSearch, cmbTypeSearch);
        } else if (cmbTypeSearchType.getSelectedIndex() <= 1) {
            Animations.toggleVisible(this, txtTypeSearch, cmbTypeSearch);
        } else {
            Animations.toggleVisible(this, cmbTypeSearch, txtTypeSearch);
            DefaultComboBoxModel df = new DefaultComboBoxModel();
            if (cmbTypeSearchType.getSelectedIndex() == 2) {
                df.addElement("Remitir con autoridad competente");
                df.addElement("Tomar contacto con ISP y colegio");
            } else {
                df.addElement("Activo");
                df.addElement("Inactivo");
            }
            cmbTypeSearch.setModel(df);
        }
    }//GEN-LAST:event_cmbTypeSearchTypeActionPerformed

    private void btnTypeClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTypeClearActionPerformed
        // TODO add your handling code here:
        clearTypeFields();
        fillTypeTable();
    }//GEN-LAST:event_btnTypeClearActionPerformed

    private void btnMenuTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuTypeActionPerformed
        showCard("crdType");
        selectItem((JButton) evt.getSource());
    }//GEN-LAST:event_btnMenuTypeActionPerformed

    private void cmbSchoolSearchTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSchoolSearchTypeActionPerformed
        if (cmbSchoolSearchType.getSelectedIndex() == 0) {
            Animations.invisibilizeComponents(this, txtSchoolSearch, cmbSchoolSearch);
        } else if (cmbSchoolSearchType.getSelectedIndex() <= 2) {
            Animations.toggleVisible(this, txtSchoolSearch, cmbSchoolSearch);
        } else {
            Animations.toggleVisible(this, cmbSchoolSearch, txtSchoolSearch);
        }
    }//GEN-LAST:event_cmbSchoolSearchTypeActionPerformed

    private void btnSchoolClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSchoolClearActionPerformed
        // TODO add your handling code here:
        clearSchoolFields();
        fillSchoolTable();
    }//GEN-LAST:event_btnSchoolClearActionPerformed

    private void btnMenuSchoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuSchoolActionPerformed
        showCard("crdSchool");
        selectItem((JButton) evt.getSource());
    }//GEN-LAST:event_btnMenuSchoolActionPerformed

    private void cmbAuthSearchTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAuthSearchTypeActionPerformed
        if (cmbAuthSearchType.getSelectedIndex() == 0) {
            Animations.invisibilizeComponents(this, txtAuthSearch, cmbAuthSearch);
        } else if (cmbAuthSearchType.getSelectedIndex() <= 1) {
            Animations.toggleVisible(this, txtAuthSearch, cmbAuthSearch);
        } else {
            Animations.toggleVisible(this, cmbAuthSearch, txtAuthSearch);
        }
    }//GEN-LAST:event_cmbAuthSearchTypeActionPerformed

    private void btnAuthClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuthClearActionPerformed
        // TODO add your handling code here:
        clearAuthFields();
        fillAuthTable();
    }//GEN-LAST:event_btnAuthClearActionPerformed

    private void btnMenuAuthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuAuthActionPerformed
        showCard("crdAuth");
        selectItem((JButton) evt.getSource());
    }//GEN-LAST:event_btnMenuAuthActionPerformed

    private void cmbProvSearchTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvSearchTypeActionPerformed
        if (cmbProvSearchType.getSelectedIndex() == 0) {
            Animations.invisibilizeComponents(this, txtProvSearch, cmbProvSearch);
        } else if (cmbProvSearchType.getSelectedIndex() <= 1) {
            Animations.toggleVisible(this, txtProvSearch, cmbProvSearch);
        } else {
            Animations.toggleVisible(this, cmbProvSearch, txtProvSearch);
        }
    }//GEN-LAST:event_cmbProvSearchTypeActionPerformed

    private void btnProvClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvClearActionPerformed
        // TODO add your handling code here:
        clearProvFields();
        fillProvTable();
    }//GEN-LAST:event_btnProvClearActionPerformed

    private void btnMenuProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuProvActionPerformed
        showCard("crdProv");
        selectItem((JButton) evt.getSource());
    }//GEN-LAST:event_btnMenuProvActionPerformed

    private void btnMenuCallsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuCallsActionPerformed
        showCard("crdCalls");
        selectItem((JButton) evt.getSource());
    }//GEN-LAST:event_btnMenuCallsActionPerformed

    private void btnMenuNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuNewActionPerformed
        showCard("crdNew");
        selectItem((JButton) evt.getSource());
    }//GEN-LAST:event_btnMenuNewActionPerformed

    private void btnMenuReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReportsActionPerformed
        showCard("crdReports");
        selectItem((JButton) evt.getSource());
    }//GEN-LAST:event_btnMenuReportsActionPerformed

    private void btnMenuProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuProfileActionPerformed
        showCard("crdProfile");
        selectItem((JButton) evt.getSource());
    }//GEN-LAST:event_btnMenuProfileActionPerformed

    private void cmbCallsSearchTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCallsSearchTypeActionPerformed
        switch (cmbCallsSearchType.getSelectedIndex()) {
            case 0:
            case 5:
                Animations.invisibilizeComponents(this, pnlCallsParam, btnCallsSchoolSearch);
                break;
            case 1:
                showCallsCard("callsLbl");
                Animations.visibilizeComponents(this, pnlCallsParam, btnCallsSchoolSearch);
                break;
            case 2:
                DefaultComboBoxModel model2 = new DefaultComboBoxModel();
                for (Complaint_type type : new ComplaintTypeController().getAll(true)) {
                    model2.addElement(type);
                }
                cmbCallsSearch.setModel(model2);
                showCallsCard("callsCmb");
                Animations.toggleVisible(this, pnlCallsParam, btnCallsSchoolSearch);
                break;
            case 3:
                showCallsCard("callsTxt");
                Animations.toggleVisible(this, pnlCallsParam, btnCallsSchoolSearch);
                break;
            case 4:
                DefaultComboBoxModel model4 = new DefaultComboBoxModel();
                model4.addElement("Es viable");
                model4.addElement("No es viable");
                cmbCallsSearch.setModel(model4);
                showCallsCard("callsCmb");
                Animations.toggleVisible(this, pnlCallsParam, btnCallsSchoolSearch);
                break;
        }
        triggerCallsSearch();
    }//GEN-LAST:event_cmbCallsSearchTypeActionPerformed

    private void btnNewClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewClearActionPerformed
        clearPnlNew();
    }//GEN-LAST:event_btnNewClearActionPerformed

    private void btnNewListSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewListSearchActionPerformed
        try {
            if (((Complaint_type) cmbNewType.getSelectedItem()).getTaken_action().equals("Remitir con autoridad competente")) {
                //Añadir autoridad
                SearchAuthority dialog = new SearchAuthority();
                int result = JOptionPane.showConfirmDialog(this, dialog, "Seleccionar autoridad", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    Authority callAuth = dialog.getValue();

                    //Validando si no se seleccionó autoridad
                    if (callAuth != null) {
                        //Validando datos repetidos
                        boolean isRepeated = false;
                        for (int i = 0; i < (lstNew.getModel()).getSize(); i++) {
                            if ((((DefaultListModel) lstNew.getModel()).getElementAt(i)).toString().equals(callAuth.toString())) {
                                isRepeated = true;
                                break;
                            }
                        }
                        if (isRepeated) {
                            JOptionPane.showMessageDialog(this, "Esa autoridad ya fue seleccionada", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        } else {
                            //La autoridad es añadida
                            ((DefaultListModel) lstNew.getModel()).addElement(callAuth);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No seleccionó ninguna autoridad", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                //Añadir proveedor
                SearchProvider dialog = new SearchProvider();
                int result = JOptionPane.showConfirmDialog(this, dialog, "Seleccionar proveedor", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    Provider callProv = dialog.getValue();

                    //Validando si no se seleccionó proveedor
                    if (callProv != null) {
                        //Validando datos repetidos
                        boolean isRepeated = false;
                        for (int i = 0; i < (lstNew.getModel()).getSize(); i++) {
                            if ((((DefaultListModel) lstNew.getModel()).getElementAt(i)).toString().equals(callProv.toString())) {
                                isRepeated = true;
                                break;
                            }
                        }
                        if (isRepeated) {
                            JOptionPane.showMessageDialog(this, "Ese proveedor ya fue seleccionado", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        } else {
                            ((DefaultListModel) lstNew.getModel()).addElement(callProv);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No seleccionó ningún proveedor", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnNewListSearchActionPerformed

    private void cmbNewTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNewTypeActionPerformed
        showNewList();
    }//GEN-LAST:event_cmbNewTypeActionPerformed

    private void btnDetailBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailBackActionPerformed
        showCard("crdCalls");
    }//GEN-LAST:event_btnDetailBackActionPerformed

    private void chbNewViableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbNewViableActionPerformed
        showNewList();
    }//GEN-LAST:event_chbNewViableActionPerformed

    private void btnNewSchoolSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewSchoolSearchActionPerformed
        try {
            SearchSchool dialog = new SearchSchool();
            int result = JOptionPane.showConfirmDialog(this, dialog, "Seleccionar escuela", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
            lblNewSchool.setText("Ninguna");
            if (result == JOptionPane.OK_OPTION) {
                callSchool = dialog.getValue();
                lblNewSchool.setText(callSchool.toString());
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNewSchoolSearchActionPerformed

    private void btnDetailSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailSaveActionPerformed
        try {
            boolean modifyCall = false, modifyAsign = false;
                
            //Determinando si la llamada debe ser modificada
            //Operación XOR
            //Si los valores son distintos, debe ocurrir la modificación
            modifyCall = new CallController().getOne(currentId).isTalk_given() ^ chbDetailTalk.isSelected();

            for (int i = 0; i < tblAsigns.getModel().getRowCount(); i++) {
                //Operación XOR
                //Compara los checkbox con el content_removed del objeto. Si son distintos, debe ocurrir la modificación
                modifyAsign = ((Provider_asign)tblAsigns.getModel().getValueAt(i, 0)).isContent_removed() ^ (boolean)tblAsigns.getModel().getValueAt(i, 1);
                if (modifyAsign) {
                    break;
                }
            }

            //Si no se detectaron modificaciones
            if (!modifyCall && !modifyAsign) {
                JOptionPane.showMessageDialog(this, "No se han hecho cambios en la denuncia", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            
            //Si se detectaron modificaciones
            else {
                //Confirmación
                if (JOptionPane.showConfirmDialog(this, "¿Desea guardar los cambios? Esta operación es irreversible") == JOptionPane.OK_OPTION) {
                    boolean success = true;
                    if (modifyCall) {
                        //Modificando talk_given de la llamada
                        if (!new CallController().updateCall(currentId, chbDetailTalk.isSelected())) {
                            JOptionPane.showMessageDialog(this, "Ocurrió un error al modificar la denuncia", "Operación denegada", JOptionPane.ERROR_MESSAGE);
                            success = false;
                        }
                    }
                    if (modifyAsign) {
                        //Modificando content_removed de los proveedores
                        for (int i = 0; i < tblAsigns.getModel().getRowCount(); i++) {
                            if (!new ProvAsignController().updateProvAsign(
                                    ((Provider_asign)tblAsigns.getModel().getValueAt(i, 0)).getId(), 
                                    (boolean)tblAsigns.getModel().getValueAt(i, 1))
                            ) {
                                JOptionPane.showMessageDialog(this, "Ocurrió un error al modificar un proveedor relacionado a la denuncia", "Operación denegada", JOptionPane.ERROR_MESSAGE);
                                success = false;
                            }
                        }
                    }
                    
                    //Si todas las operaciones fueron exitosas
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Cambios guardados", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                        showCard("crdCalls");
                    }
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnDetailSaveActionPerformed

    private void btnCallsDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCallsDetailActionPerformed
        try {
            int row = tblCalls.getSelectedRow();
            if (row >= 0) {
                //Mostrando datos de la denuncia
                Call call = (Call) tblCalls.getValueAt(row, 2);
                currentId = call.getId();
                lblDetailUser.setText(call.getUser().getName() + " " + call.getUser().getLastname());
                lblDetailUser.setToolTipText(call.getUser().getName() + " " + call.getUser().getLastname());
                lblDetailDate.setText(Utils.formatDate(call.getCall_date(), Utils.DATE_UI));
                lblDetailSchool.setText(call.getSchool().getName());
                lblDetailSchool.setToolTipText(call.getSchool().getName());
                lblDetailType.setText(call.getComplaint_type().getName());
                lblDetailType.setToolTipText(call.getComplaint_type().getName());
                lblDetailDescription.setText(call.getDescription());
                lblDetailArchived.setVisible(!call.getViable());
                chbDetailTalk.setSelected(call.isTalk_given());
                chbDetailTalk.setEnabled(!call.isTalk_given());
                
                //Si la denuncia no es viable, se ocultan los controles de la parte inferior del panel
                if (!call.getViable()) {
                    Animations.invisibilizeComponents(this, lblAsigns, scrAsigns, chbDetailTalk, btnDetailSave);
                }
                //Si la denuncia es viable, se muestran los controles y se calculan sus valores
                else {
                    Animations.visibilizeComponents(this, lblAsigns, scrAsigns);
                    
                    //Si es una denuncia relacionada a autoridades
                    if (call.getComplaint_type().getTaken_action().equals("Remitir con autoridad competente")) {
                        Animations.invisibilizeComponents(this, chbDetailTalk, btnDetailSave);
                        lblAsigns.setText("Autoridades notificadas: ");

                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("Autoridad");

                        for (Authority_asign asign : new AuthAsignController().getAsigns(call)) {
                            model.addRow(new Object[]{asign.getAuthority().getName()});
                        }
                        tblAsigns.setModel(model);

                    }
                    //Si es una denuncia relacionada a proveedores
                    else {
                        Animations.visibilizeComponents(this, chbDetailTalk, btnDetailSave);
                        lblAsigns.setText("Proveedores notificados: ");

                        //Estableciendo clase del modelo
                        DefaultTableModel model = new DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "Proveedor", "Retiró el contenido"
                            }
                        ) {
                            Class[] types = new Class [] {
                                java.lang.Object.class, java.lang.Boolean.class
                            };
                            boolean[] canEdit = new boolean [] {
                                false, true
                            };

                            public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                            }

                            //Determina que celdas son editables
                            //Las únicas celdas editables deberían ser las que contengan checkboxes,
                            //en las que el atributo Content_removed del objeto relacionado sea false
                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                try {
                                    if (((Provider_asign)this.getValueAt(rowIndex, 0)).isContent_removed())
                                        return false;
                                    else
                                        return canEdit [columnIndex];
                                } catch (Exception e) {
                                    return canEdit [columnIndex];
                                }
                            }
                        };


                        for (Provider_asign asign : new ProvAsignController().getAsigns(call)) {
                            model.addRow(new Object[]{asign, asign.isContent_removed()});
                        }

                        tblAsigns.setModel(model);
                    }

                }
                
                
                //Mostrando panel
                showCard("crdCallDetail");
            }
            else {
                JOptionPane.showMessageDialog(this, "Seleccione una denuncia", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnCallsDetailActionPerformed

    private void btnUserClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserClear1ActionPerformed
        refreshPnlProfile();
    }//GEN-LAST:event_btnUserClear1ActionPerformed

    private void btnEditNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditNameActionPerformed
        txtProfileName.setEnabled(true);
        txtProfileLastName.setEnabled(true);
    }//GEN-LAST:event_btnEditNameActionPerformed

    private void btnEditEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditEmailActionPerformed
        txtProfileEmail.setEnabled(true);
    }//GEN-LAST:event_btnEditEmailActionPerformed

    private void btnEditPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPassActionPerformed
        txtProfilePass.setEnabled(true);
        txtProfileConfirm.setEnabled(true);
    }//GEN-LAST:event_btnEditPassActionPerformed

    /*----------------------------- ------------------------------------------
     -------------------------------▲-----------------------------------------
     ---------------- ------------ ▲‌ ▲---------------------------------------
    --------------------------CODIGO DE AUTH ---------------------------------*/
    //llenar tabla
    private void fillAuthTable() {

        DefaultTableModel df = (DefaultTableModel) tblAuth.getModel();
        while (df.getRowCount() > 0) {
            df.removeRow(0);
        };

        for (Authority obj : new AuthorityController().getAll(false)) {
            df.addRow(new Object[]{obj, obj.isState() ? "Activo" : "Inactivo"});
        }
    }

    private void clearAuthFields() {
        txtAuthName.setText("");
        cmbAuthState.setSelectedIndex(0);
        btnAuthAction.setText("Añadir");
        this.currentId = 0;
        cmbAuthSearchType.setSelectedIndex(0);
        Animations.hide(errAuthName, 255, 0, 0);
    }

    private void triggerAuthSearch() {
        try {
            String param = "";
            int type = 0;

            switch (String.valueOf(cmbAuthSearchType.getSelectedItem())) {
                case "Nombre":
                    type = AuthorityController.BY_NAME;
                    param = txtAuthSearch.getText().trim();
                    break;
                case "Estado":
                    type = AuthorityController.BY_STATE;
                    param = cmbAuthSearch.getSelectedItem().toString();
                    break;
                case "N/A":
                    type = AuthorityController.NO_FIELD;
                    clearAuthFields();
                    fillAuthTable();
                    break;
            }

            DefaultTableModel model = (DefaultTableModel) tblAuth.getModel();
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            for (Authority object : new AuthorityController().search(type, param, false)) {

                model.addRow(new Object[]{object, object.isState() ? "Activo" : "Inactivo"});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void pnlAuthFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pnlAuthFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_pnlAuthFocusGained

    private void pnlAuthComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlAuthComponentShown
        // TODO add your handling code here:
        fillAuthTable();
    }//GEN-LAST:event_pnlAuthComponentShown

    private void tblAuthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAuthMouseClicked
        // TODO add your handling code here:
        clearAuthFields();
        Animations.hide(errAuthName, 255, 0, 0);
        int fila = this.tblAuth.getSelectedRow();
        if (fila >= 0) {
            Authority obje = (Authority) this.tblAuth.getValueAt(fila, 0);
            txtAuthName.setText(obje.getName());
            cmbAuthState.setSelectedIndex(obje.isState() ? 0 : 1);
            this.currentId = obje.getId();
            btnAuthAction.setText("Modificar");
        }
    }//GEN-LAST:event_tblAuthMouseClicked

    private void pnlAuthComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlAuthComponentHidden
        // TODO add your handling code here:
        clearAuthFields();
    }//GEN-LAST:event_pnlAuthComponentHidden

    private void lstNewValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstNewValueChanged
        //Mostrando boton de desvicular autoridad/proveedor
        try {
            if (lstNew.getSelectedIndex() > -1) {
                btnNewListDel.setVisible(true);
            } else {
                btnNewListDel.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_lstNewValueChanged

    private void btnNewListDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewListDelActionPerformed
        //Desvinculando autoridad/proveedor
        try {
            if (lstNew.getSelectedIndex() > -1) {
                ((DefaultListModel) lstNew.getModel()).remove(lstNew.getSelectedIndex());
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo desvincular el objeto seleccionado");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnNewListDelActionPerformed

    private void btnNewSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewSaveActionPerformed
        //Guardar denuncia
        try {
            //Indica si se pasan todas las validaciones
            boolean isValid = true;

            //Ocultando los label de error
            JLabel[] errorList = {
                errNewSchool, errNewDescription, errNewType, errNewList,};
            for (JLabel err : errorList) {
                Animations.hide(err, 255, 0, 0);
            }

            //Validaciones
            if (callSchool == null) {
                new Animations().appear(errNewSchool, 255, 0, 0);
                isValid = false;
            }
            if (txtNewDesc.getText().trim().equals("")) {
                new Animations().appear(errNewDescription, 255, 0, 0);
                isValid = false;
            }
            if (cmbNewType.getSelectedIndex() == 0) {
                new Animations().appear(errNewType, 255, 0, 0);
                isValid = false;
            }
            if (lstNew.getModel().getSize() < 1 && chbNewViable.isSelected() && cmbNewType.getSelectedIndex() != 0) {
                new Animations().appear(errNewList, 255, 0, 0);
                isValid = false;
            }

            //Si pasa todas las validaciones, guarda
            if (isValid) {
                if (new CallController().addCall(
                        callSchool,
                        chbNewViable.isSelected(),
                        (Complaint_type) cmbNewType.getSelectedItem(),
                        user,
                        txtNewDesc.getText().trim()
                )) {
                    Call call = new CallController().getLast();
                    if (chbNewViable.isSelected()) {
                        //Si la denuncia es viable, se hará el insert de las autoridades/proveedores relacionados
                        for (int i = 0; i < (lstNew.getModel()).getSize(); i++) {
                            if (((Complaint_type) cmbNewType.getSelectedItem()).getTaken_action().equals("Remitir con autoridad competente")) {
                                if (!(new AuthAsignController().addAuthAsign(call, (Authority) (((DefaultListModel) lstNew.getModel()).getElementAt(i))))) {
                                    JOptionPane.showMessageDialog(this, "Error al guardar autoridad relacionada a denuncia", "Operación denegada", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            else {
                                if (!(new ProvAsignController().addProvAsign(call, (Provider)(((DefaultListModel) lstNew.getModel()).getElementAt(i))))) {
                                    JOptionPane.showMessageDialog(this, "Error al guardar proveedor relacionado a denuncia", "Operación denegada", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        }

                    }
                    
                    JOptionPane.showMessageDialog(this, "Denuncia guardada", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                    ReportGenerator.detailReport(call.getId());
                    clearPnlNew();
                } else {
                    JOptionPane.showMessageDialog(this, "La denuncia no pudo ser guardada", "Operación denegada", JOptionPane.WARNING_MESSAGE);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_btnNewSaveActionPerformed

    private void btnAuthActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuthActionActionPerformed
        // TODO add your handling code here:
        if ("Añadir".equals(btnAuthAction.getText())) {
            if (txtAuthName.getText().trim().isEmpty()) {
                errAuthName.setText("Campo vacio");
                new Animations().appear(errAuthName, 255, 0, 0);
            } else {
                Animations.hide(errAuthName, 255, 0, 0);
                boolean state = true;
                if (cmbAuthState.getSelectedIndex() == 1) {
                    state = false;
                }
                if (new AuthorityController().addAuthority(txtAuthName.getText(), state)) {
                    JOptionPane.showMessageDialog(this, "Agregado con exito");
                    fillAuthTable();
                    clearAuthFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar");
                }
            }
        }

        if ("Modificar".equals(btnAuthAction.getText())) {
            System.err.println("dos");
            if (txtAuthName.getText().trim().isEmpty()) {
                errAuthName.setText("Campo vacio");
                new Animations().appear(errAuthName, 255, 0, 0);
            } else {
                Animations.hide(errAuthName, 255, 0, 0);
                boolean state = true;
                if (cmbAuthState.getSelectedIndex() == 1) {
                    state = false;
                }
                if (new AuthorityController().updateAuthority(currentId, txtAuthName.getText(), state)) {
                    JOptionPane.showMessageDialog(this, "Actualizado con exito");
                    fillAuthTable();
                    clearAuthFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar");
                }
            }
        }
    }//GEN-LAST:event_btnAuthActionActionPerformed

    private void txtAuthSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAuthSearchKeyReleased
        // TODO add your handling code here:
        triggerAuthSearch();
    }//GEN-LAST:event_txtAuthSearchKeyReleased

    private void cmbAuthSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbAuthSearchPropertyChange
        // TODO add your handling code here:
        triggerAuthSearch();
    }//GEN-LAST:event_cmbAuthSearchPropertyChange

    private void btnAuthSearchResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuthSearchResetActionPerformed
        // TODO add your handling code here:
        clearAuthFields();
        fillAuthTable();
    }//GEN-LAST:event_btnAuthSearchResetActionPerformed

    /*----------------------------- ------------------------------------------
     -------------------------------▲-----------------------------------------
     ---------------- ------------ ▲‌ ▲---------------------------------------
    --------------------------CODIGO DE PROV ---------------------------------*/
    //llenar tabla
    private void fillProvTable() {

        DefaultTableModel df = (DefaultTableModel) tblProv.getModel();
        while (df.getRowCount() > 0) {
            df.removeRow(0);
        };

        for (Provider obj : new ProviderController().getAll(false)) {
            df.addRow(new Object[]{obj, obj.isState() ? "Activo" : "Inactivo"});
        }
    }

    private void clearProvFields() {
        txtProvName.setText("");
        cmbProvState.setSelectedIndex(0);
        btnProvAction.setText("Añadir");
        this.currentId = 0;
        cmbProvSearchType.setSelectedIndex(0);
        Animations.hide(errAuthName, 255, 0, 0);
    }

    private void triggerProvSearch() {
        try {
            String param = "";
            int type = 0;

            switch (String.valueOf(cmbProvSearchType.getSelectedItem())) {
                case "Nombre":
                    type = ProviderController.BY_NAME;
                    param = txtProvSearch.getText().trim();
                    break;
                case "Estado":
                    type = ProviderController.BY_STATE;
                    param = cmbProvSearch.getSelectedItem().toString();
                    break;
                case "N/A":
                    type = ProviderController.NO_FIELD;
                    clearProvFields();
                    fillProvTable();
                    break;
            }

            DefaultTableModel model = (DefaultTableModel) tblProv.getModel();
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            for (Provider object : new ProviderController().search(type, param, false)) {
                model.addRow(new Object[]{object, object.isState() ? "Activo" : "Inactivo"});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void pnlProvComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlProvComponentShown
        // TODO add your handling code here:
        fillProvTable();
    }//GEN-LAST:event_pnlProvComponentShown

    private void pnlProvComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlProvComponentHidden
        // TODO add your handling code here:
        clearProvFields();
    }//GEN-LAST:event_pnlProvComponentHidden

    private void tblProvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProvMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        clearProvFields();
        Animations.hide(errProvName, 255, 0, 0);
        int fila = this.tblProv.getSelectedRow();
        if (fila >= 0) {
            Provider obje = (Provider) this.tblProv.getValueAt(fila, 0);
            txtProvName.setText(obje.getName());
            cmbProvState.setSelectedIndex(obje.isState() ? 0 : 1);
            this.currentId = obje.getId();
            btnProvAction.setText("Modificar");
        }
    }//GEN-LAST:event_tblProvMouseClicked

    private void txtProvSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvSearchKeyReleased
        // TODO add your handling code here:
        triggerProvSearch();
    }//GEN-LAST:event_txtProvSearchKeyReleased

    private void cmbProvSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbProvSearchPropertyChange
        // TODO add your handling code here:
        triggerProvSearch();
    }//GEN-LAST:event_cmbProvSearchPropertyChange

    private void btnProvActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvActionActionPerformed
        // TODO add your handling code here:// TODO add your handling code here:
        if ("Añadir".equals(btnProvAction.getText())) {
            if (txtProvName.getText().trim().isEmpty()) {
                errProvName.setText("Campo vacio");
                new Animations().appear(errProvName, 255, 0, 0);
            } else {
                new Animations().hide(errProvName, 255, 0, 0);
                boolean state = true;
                if (cmbProvState.getSelectedIndex() == 1) {
                    state = false;
                }
                if (new ProviderController().addProvider(txtProvName.getText(), state)) {
                    JOptionPane.showMessageDialog(this, "Agregado con exito");
                    fillProvTable();
                    clearProvFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar");
                }
            }
        }

        if ("Modificar".equals(btnProvAction.getText())) {
            System.err.println("dos");
            if (txtProvName.getText().trim().isEmpty()) {
                errProvName.setText("Campo vacio");
                new Animations().appear(errProvName, 255, 0, 0);
            } else {
                Animations.hide(errProvName, 255, 0, 0);
                boolean state = true;
                if (cmbProvState.getSelectedIndex() == 1) {
                    state = false;
                }
                if (new ProviderController().updateProvider(currentId, txtProvName.getText(), state)) {
                    JOptionPane.showMessageDialog(this, "Actualizado con exito");
                    fillProvTable();
                    clearProvFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar");
                }
            }
        }

    }//GEN-LAST:event_btnProvActionActionPerformed

    private void btnProvSearchResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvSearchResetActionPerformed
        // TODO add your handling code here:
        clearProvFields();
        fillProvTable();
    }//GEN-LAST:event_btnProvSearchResetActionPerformed

    private void btnCallsSchoolSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCallsSchoolSearchActionPerformed
        try {
            SearchSchool dialog = new SearchSchool();
            int result = JOptionPane.showConfirmDialog(this, dialog, "Seleccionar escuela", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
            lblCallsSchool.setText("Ninguna");
            if (result == JOptionPane.OK_OPTION) {
                callSchool = dialog.getValue();
                lblCallsSchool.setText(callSchool.toString());
                triggerCallsSearch();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCallsSchoolSearchActionPerformed

    private void pnlNewComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlNewComponentShown
        refreshPnlNew();
    }//GEN-LAST:event_pnlNewComponentShown

    private void pnlNewComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlNewComponentHidden
        clearPnlNew();
    }//GEN-LAST:event_pnlNewComponentHidden

    private void pnlCallsComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlCallsComponentShown
        refreshPnlCalls();
    }//GEN-LAST:event_pnlCallsComponentShown

    private void pnlCallsComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlCallsComponentHidden
        clearPnlCalls();
    }//GEN-LAST:event_pnlCallsComponentHidden

    private void cmbCallsSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCallsSearchActionPerformed
        triggerCallsSearch();
    }//GEN-LAST:event_cmbCallsSearchActionPerformed

    private void txtCallsSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCallsSearchKeyReleased
        triggerCallsSearch();
    }//GEN-LAST:event_txtCallsSearchKeyReleased

    private void dtpCallsFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dtpCallsFromActionPerformed
        dtpCallsTo.getMonthView().setLowerBound(dtpCallsFrom.getDate());
        triggerCallsSearch();
    }//GEN-LAST:event_dtpCallsFromActionPerformed

    private void dtpCallsToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dtpCallsToActionPerformed
        dtpCallsFrom.getMonthView().setUpperBound(dtpCallsTo.getDate());
        triggerCallsSearch();
    }//GEN-LAST:event_dtpCallsToActionPerformed

    private void btnAuthSearchReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuthSearchReset1ActionPerformed
        clearPnlCalls();
        refreshPnlCalls();
    }//GEN-LAST:event_btnAuthSearchReset1ActionPerformed

    private void pnlCallDetailComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlCallDetailComponentHidden
        //Limpiando pnlCallDetail
        currentId = 0;
    }//GEN-LAST:event_pnlCallDetailComponentHidden
    /*----------------------------- ------------------------------------------
     -------------------------------▲-----------------------------------------
     ---------------- ------------ ▲‌ ▲---------------------------------------
    --------------------------CODIGO DE SCHOOL ---------------------------------*/
    //llenar tabla
    private void fillSchoolTable() {

        DefaultTableModel df = (DefaultTableModel) tblSchool.getModel();
        while (df.getRowCount() > 0) {
            df.removeRow(0);
        };

        for (School obj : new SchoolController().getAll(false)) {
            df.addRow(new Object[]{obj, obj.getAddress(), obj.isState() ? "Activo" : "Inactivo"});
        }
    }

    private void clearSchoolFields() {
        txtSchoolName.setText("");
        txtSchoolAddress.setText("");
        cmbSchoolState.setSelectedIndex(0);
        btnSchoolAction.setText("Añadir");
        this.currentId = 0;
        cmbSchoolSearchType.setSelectedIndex(0);
        Animations.hide(errSchoolName, 255, 0, 0);
        Animations.hide(errSchoolAddress, 255, 0, 0);
    }

    private void triggerSchoolSearch() {
        try {
            String param = "";
            int type = 0;

            switch (String.valueOf(cmbSchoolSearchType.getSelectedItem())) {
                case "Nombre":
                    type = SchoolController.BY_NAME;
                    param = txtSchoolSearch.getText().trim();
                    break;
                case "Dirección":
                    type = SchoolController.BY_ADDRESS;
                    param = txtSchoolSearch.getText().trim();
                    break;
                case "Estado":
                    type = SchoolController.BY_STATE;
                    param = cmbSchoolSearch.getSelectedItem().toString();
                    break;
                case "N/A":
                    type = SchoolController.NO_FIELD;
                    clearSchoolFields();
                    fillSchoolTable();
                    break;
            }

            DefaultTableModel model = (DefaultTableModel) tblSchool.getModel();
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            for (School object : new SchoolController().search(type, param, false)) {
                model.addRow(new Object[]{object, object.getAddress(), object.isState() ? "Activo" : "Inactivo"});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void pnlSchoolComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlSchoolComponentShown
        // TODO add your handling code here:
        fillSchoolTable();
    }//GEN-LAST:event_pnlSchoolComponentShown

    private void txtSchoolSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSchoolSearchKeyReleased
        // TODO add your handling code here:
        triggerSchoolSearch();
    }//GEN-LAST:event_txtSchoolSearchKeyReleased

    private void cmbSchoolSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSchoolSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSchoolSearchActionPerformed

    private void cmbSchoolSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbSchoolSearchPropertyChange
        // TODO add your handling code here:
        triggerSchoolSearch();
    }//GEN-LAST:event_cmbSchoolSearchPropertyChange

    private void pnlSchoolComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlSchoolComponentHidden
        // TODO add your handling code here:
        clearSchoolFields();
    }//GEN-LAST:event_pnlSchoolComponentHidden

    private void btnSchoolActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSchoolActionActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:// TODO add your handling code here:
        if ("Añadir".equals(btnSchoolAction.getText())) {
            boolean flag = true;
            if (txtSchoolName.getText().trim().isEmpty()) {
                errSchoolName.setText("Campo vacio");
                new Animations().appear(errSchoolName, 255, 0, 0);
                flag = false;
            }
            if (txtSchoolAddress.getText().trim().isEmpty()) {
                errSchoolAddress.setText("Campo vacio");
                new Animations().appear(errSchoolAddress, 255, 0, 0);
                flag = false;
            }
            if (flag) {
                Animations.hide(errSchoolName, 255, 0, 0);
                Animations.hide(errSchoolAddress, 255, 0, 0);
                boolean state = true;
                if (cmbSchoolState.getSelectedIndex() == 1) {
                    state = false;
                }
                if (new SchoolController().addSchool(txtSchoolName.getText(), txtSchoolAddress.getText(), state)) {
                    JOptionPane.showMessageDialog(this, "Agregado con exito");
                    fillSchoolTable();
                    clearSchoolFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar");
                }
            }
        }

        if ("Modificar".equals(btnSchoolAction.getText())) {
            System.err.println("dos");
            boolean flag = true;
            if (txtSchoolName.getText().trim().isEmpty()) {
                errSchoolName.setText("Campo vacio");
                new Animations().appear(errSchoolName, 255, 0, 0);
                flag = false;
            }
            if (txtSchoolAddress.getText().trim().isEmpty()) {
                errSchoolAddress.setText("Campo vacio");
                new Animations().appear(errSchoolAddress, 255, 0, 0);
                flag = false;
            }
            if (flag) {
                Animations.hide(errSchoolName, 255, 0, 0);
                Animations.hide(errSchoolAddress, 255, 0, 0);
                boolean state = true;
                if (cmbSchoolState.getSelectedIndex() == 1) {
                    state = false;
                }
                if (new SchoolController().updateSchool(currentId, txtSchoolName.getText(), txtSchoolAddress.getText(), state)) {
                    JOptionPane.showMessageDialog(this, "Modificado con exito");
                    fillSchoolTable();
                    clearSchoolFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar");
                }
            }
        }
    }//GEN-LAST:event_btnSchoolActionActionPerformed

    private void tblSchoolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSchoolMouseClicked
        // TODO add your handling code here:
        clearSchoolFields();
        Animations.hide(errSchoolName, 255, 0, 0);
        Animations.hide(errSchoolAddress, 255, 0, 0);
        int fila = this.tblSchool.getSelectedRow();
        if (fila >= 0) {
            School obje = (School) this.tblSchool.getValueAt(fila, 0);
            txtSchoolName.setText(obje.getName());
            txtSchoolAddress.setText(obje.getAddress());
            cmbSchoolState.setSelectedIndex(obje.isState() ? 0 : 1);
            this.currentId = obje.getId();
            btnSchoolAction.setText("Modificar");
        }
    }//GEN-LAST:event_tblSchoolMouseClicked

    private void btnSchoolSearchResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSchoolSearchResetActionPerformed
        // TODO add your handling code here:
        clearSchoolFields();
        fillSchoolTable();
    }//GEN-LAST:event_btnSchoolSearchResetActionPerformed

    /*----------------------------- ------------------------------------------
     -------------------------------▲-----------------------------------------
     ---------------- ------------ ▲‌ ▲---------------------------------------
    -------------------CODIGO DE COMPLAINT TYPE ---------------------------------*/
    //llenar tabla
    private void fillTypeTable() {

        DefaultTableModel df = (DefaultTableModel) tblType.getModel();
        while (df.getRowCount() > 0) {
            df.removeRow(0);
        };

        for (Complaint_type obj : new ComplaintTypeController().getAll(false)) {
            df.addRow(new Object[]{obj, obj.getTaken_action(), obj.isState() ? "Activo" : "Inactivo"});
        }
    }

    private void clearTypeFields() {
        txtTypeName.setText("");
        cmbTypeAction.setSelectedIndex(0);
        cmbTypeState.setSelectedIndex(0);
        btnTypeAction.setText("Añadir");
        this.currentId = 0;
        cmbTypeSearchType.setSelectedIndex(0);
        Animations.hide(errTypeName, 255, 0, 0);
        //Animations.hide(errSchoolAddress, 255, 0, 0);
    }

    private void triggerTypeSearch() {
        try {
            String param = "";
            int type = 0;

            switch (String.valueOf(cmbTypeSearchType.getSelectedItem())) {
                case "Nombre":
                    type = ComplaintTypeController.BY_NAME;
                    param = txtTypeSearch.getText().trim();
                    break;
                case "Acción tomada":
                    type = ComplaintTypeController.BY_ACTION;
                    param = cmbTypeSearch.getSelectedItem().toString();
                    break;
                case "Estado":
                    type = ComplaintTypeController.BY_STATE;
                    param = cmbTypeSearch.getSelectedItem().toString();
                    break;
                case "N/A":
                    type = ComplaintTypeController.NO_FIELD;
                    clearProvFields();
                    fillProvTable();
                    break;
            }

            DefaultTableModel model = (DefaultTableModel) tblType.getModel();
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            for (Complaint_type object : new ComplaintTypeController().search(type, param, false)) {
                model.addRow(new Object[]{object, object.getTaken_action(), object.isState() ? "Activo" : "Inactivo"});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void pnlTypeComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlTypeComponentShown
        // TODO add your handling code here:
        fillTypeTable();
    }//GEN-LAST:event_pnlTypeComponentShown

    private void btnTypeActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTypeActionActionPerformed
        // TODO add your handling code here:
        if ("Añadir".equals(btnTypeAction.getText())) {
            if (txtTypeName.getText().trim().isEmpty()) {
                errTypeName.setText("Campo vacio");
                new Animations().appear(errTypeName, 255, 0, 0);
            } else {
                Animations.hide(errTypeName, 255, 0, 0);
                boolean state = true;
                if (cmbTypeState.getSelectedIndex() == 1) {
                    state = false;
                }
                if (new ComplaintTypeController().addComplaintType(txtTypeName.getText(), String.valueOf(cmbTypeAction.getSelectedItem()), state)) {
                    JOptionPane.showMessageDialog(this, "Agregado con exito");
                    fillTypeTable();
                    clearTypeFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar");
                }
            }
        }

        if ("Modificar".equals(btnTypeAction.getText())) {
            System.err.println("dos");
            if (txtTypeName.getText().trim().isEmpty()) {
                errTypeName.setText("Campo vacio");
                new Animations().appear(errTypeName, 255, 0, 0);
            } else {
                Animations.hide(errTypeName, 255, 0, 0);
                boolean state = true;
                if (cmbTypeState.getSelectedIndex() == 1) {
                    state = false;
                }
                if (new ComplaintTypeController().updateComplaintType(currentId, txtTypeName.getText(), String.valueOf(cmbTypeAction.getSelectedItem()), state)) {
                    JOptionPane.showMessageDialog(this, "Modificado con exito");
                    fillTypeTable();
                    clearTypeFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar");
                }
            }
        }
    }//GEN-LAST:event_btnTypeActionActionPerformed

    private void tblTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTypeMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        clearTypeFields();
        Animations.hide(errTypeName, 255, 0, 0);
        int fila = this.tblType.getSelectedRow();
        if (fila >= 0) {
            Complaint_type obje = (Complaint_type) this.tblType.getValueAt(fila, 0);
            txtTypeName.setText(obje.getName());
            cmbTypeAction.setSelectedItem(obje.getTaken_action());
            cmbTypeState.setSelectedIndex(obje.isState() ? 0 : 1);
            this.currentId = obje.getId();
            btnTypeAction.setText("Modificar");
        }
    }//GEN-LAST:event_tblTypeMouseClicked

    private void btnTypeSearchResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTypeSearchResetActionPerformed
        // TODO add your handling code here:
        clearTypeFields();
        fillTypeTable();
    }//GEN-LAST:event_btnTypeSearchResetActionPerformed

    private void txtTypeSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTypeSearchKeyReleased
        // TODO add your handling code here:
        triggerTypeSearch();
    }//GEN-LAST:event_txtTypeSearchKeyReleased

    private void cmbTypeSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbTypeSearchPropertyChange
        // TODO add your handling code here:
        triggerTypeSearch();
    }//GEN-LAST:event_cmbTypeSearchPropertyChange

    /*----------------------------- ------------------------------------------
     -------------------------------▲-----------------------------------------
     ---------------- ------------ ▲‌ ▲---------------------------------------
    -------------------CODIGO DE USUARIOS ---------------------------------*/
    
    private void triggerUserSearch(){
        try {
            String param = "";
            int type = 0;

            switch (String.valueOf(cmbUserSearchType.getSelectedItem())) {
                case "Nombre":
                    type = UserController.BY_NAME;
                    param = txtUserSearch.getText().trim();
                    break;
                case "Apellido":
                    type = UserController.BY_LASTNAME;
                    param = txtUserSearch.getText().trim();
                    break;
                case "Email":
                    type = UserController.BY_EMAIL;
                    param = txtUserSearch.getText().trim();
                    break;
                case "Tipo de usuario":
                    type = UserController.BY_USER_TYPE;
                    User_type temp =  (User_type) cmbUserSearch.getSelectedItem();
                    param = String.valueOf(temp.getId());
                    break;
                case "Estado":
                    type = UserController.BY_STATE;
                    param = cmbUserSearch.getSelectedItem().toString();
                    break;
                case "N/A":
                    type = AuthorityController.NO_FIELD;
                    clearAuthFields();
                    fillAuthTable();
                    break;
            }

            DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            for (User object : new UserController().search(type, param, false)) {
                String temp ="Administrador";
                if(object.getUser_type().getId()==2){
                    temp="Personal";
                }
                model.addRow(new Object[]{object,object.getLastname(),object.getEmail(),temp,object.isState() ? "Activo" : "Inactivo"});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //llenar tabla
    private void fillUserTable() {

        DefaultTableModel df = (DefaultTableModel) tblUser.getModel();
        while (df.getRowCount() > 0) {
            df.removeRow(0);
        };

        for (User obj : new UserController().getAll(false)) {
            System.err.println(obj.isState());
            df.addRow(new Object[]{obj, obj.getLastname(), obj.getEmail(), obj.getUser_type().getName(), obj.isState() ? "Activo" : "Inactivo"});
        }
    }

    private void clearUserFields() {
        txtUserName.setText("");
        txtUserLastame.setText("");
        txtUserEmail.setText("");
        txtUserPass.setText("");
        cmbUserState.setSelectedIndex(0);
        cmbUserType.setSelectedIndex(0);
        btnUserAction.setText("Añadir");
        this.currentId = 0;
        cmbUserSearchType.setSelectedIndex(0);
        Animations.hide(errUserName, 255, 0, 0);
        Animations.hide(errUserLastname, 255, 0, 0);
        Animations.hide(errUserEmail, 255, 0, 0);
        Animations.hide(errUserPass, 255, 0, 0);
        //Animations.hide(errSchoolAddress, 255, 0, 0);
    }
    private void pnlUserComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlUserComponentShown
        // TODO add your handling code here:
        fillUserTable();
    }//GEN-LAST:event_pnlUserComponentShown

    private void btnUserSearchResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserSearchResetActionPerformed
        // TODO add your handling code here:
        clearUserFields();
        fillUserTable();
    }//GEN-LAST:event_btnUserSearchResetActionPerformed

    private void btnUserActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        boolean flag = true;
        if (txtUserName.getText().trim().isEmpty()) {
            errUserName.setText("Campo vacio");
            new Animations().appear(errUserName, 255, 0, 0);
            flag = false;
        } else {
            Animations.hide(errUserName, 255, 0, 0);
        }
        if (txtUserLastame.getText().trim().isEmpty()) {
            errUserLastname.setText("Campo vacio");
            new Animations().appear(errUserLastname, 255, 0, 0);
            flag = false;
        } else {
            Animations.hide(errUserLastname, 255, 0, 0);
        }
        if (!new Utils().validate(txtUserEmail.getText())) {
            errUserEmail.setText("Formato de correo no valido");
            new Animations().appear(errUserEmail, 255, 0, 0);
            flag = false;
        } else {
            Animations.hide(errUserEmail, 255, 0, 0);
        }
        
        if ("Añadir".equals(btnUserAction.getText())) {
            if (txtUserPass.getText().trim().length() < 6) {
                errUserPass.setText("Clave demasiado corta");
                new Animations().appear(errUserPass, 255, 0, 0);
                flag = false;
            } else {
                Animations.hide(errUserPass, 255, 0, 0);
            }
            if (flag) {
                boolean state = true;
                int index = 1;
                if (cmbUserType.getSelectedIndex() != 0) {
                    index = 2;
                }
                if (cmbUserState.getSelectedIndex() == 1) {
                    state = false;
                }
                if (new UserController().addUser(txtUserName.getText(), txtUserLastame.getText(), txtUserEmail.getText(), txtUserPass.getText(), index, state)) {
                    JOptionPane.showMessageDialog(this, "Agregado con exito");
                    fillUserTable();
                    clearUserFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar");
                }
            }

        }

        if ("Modificar".equals(btnUserAction.getText())) {
            if (txtUserPass.getText().trim().length() < 6 && !txtUserPass.getText().trim().isEmpty()) {
                errUserPass.setText("Clave demasiado corta");
                new Animations().appear(errUserPass, 255, 0, 0);
                flag = false;
            } else {
                Animations.hide(errUserPass, 255, 0, 0);
            }
            //Validando que el propio usuario no pueda inhabilitarse
            if (currentId == user.getId()) {
                if (cmbUserType.getSelectedIndex() != 0) {
                    errUserType.setText("No puede cambiar su tipo");
                    new Animations().appear(errUserType, 255, 0, 0);
                    flag = false;
                }
                else {
                    Animations.hide(errUserType, 255, 0, 0);
                }
                if (cmbUserState.getSelectedIndex() == 1) {
                    errUserState.setText("No puede inhabilitarse a sí mismo");
                    new Animations().appear(errUserState, 255, 0, 0);
                    flag = false;
                }
                else {
                    Animations.hide(errUserState, 255, 0, 0);
                }
            }
            if (flag) {
                boolean state = true;
                int index = 1;
                
                if (cmbUserType.getSelectedIndex() != 0) {
                    index = 2;
                }
                if (cmbUserState.getSelectedIndex() == 1) {
                    state = false;
                }
                if (new UserController().updateUser(currentId,txtUserName.getText(), txtUserLastame.getText(), txtUserEmail.getText(), txtUserPass.getText(), index, state)) {
                    JOptionPane.showMessageDialog(this, "Modificado con exito");
                    fillUserTable();
                    clearUserFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar");
                }
            }
        }
    }//GEN-LAST:event_btnUserActionActionPerformed

    private void txtUserEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserEmailKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtUserEmailKeyReleased

    private void tblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserMouseClicked
        // TODO add your handling code here:
        //clearUserFields();
        int fila = this.tblUser.getSelectedRow();
        if (fila >= 0) {
            User obje = (User) this.tblUser.getValueAt(fila, 0);
            txtUserName.setText(obje.getName());
            txtUserLastame.setText(obje.getLastname());
            txtUserEmail.setText(obje.getEmail());
            int userType = 0;
            if(obje.getUser_type().getId()==2){
                userType=1;
            }
            cmbUserType.setSelectedIndex(userType);
            cmbUserState.setSelectedIndex(obje.isState() ? 0 : 1);
            this.currentId = obje.getId();
            btnUserAction.setText("Modificar");
        }
    }//GEN-LAST:event_tblUserMouseClicked

    private void cmbProvSearchTypePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbProvSearchTypePropertyChange
        // TODO add your handling code here:4/5
        
    }//GEN-LAST:event_cmbProvSearchTypePropertyChange

    private void cmbUserSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbUserSearchPropertyChange
        // TODO add your handling code here:
        triggerUserSearch();
    }//GEN-LAST:event_cmbUserSearchPropertyChange

    private void txtUserSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserSearchActionPerformed

    private void txtUserSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserSearchKeyReleased
        // TODO add your handling code here:
        triggerUserSearch();
    }//GEN-LAST:event_txtUserSearchKeyReleased

    private void btnDetailReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailReportActionPerformed
        ReportGenerator.detailReport(currentId);
        JOptionPane.showMessageDialog(this, "Reporte generado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnDetailReportActionPerformed

    private void btnReport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReport1ActionPerformed
        // TODO add your handling code here:
        
        try{
            String from = Utils.formatDate(dtpReportsFrom.getDate(), Utils.DATE_DB);
            String to = Utils.formatDate(dtpReportsTo.getDate(), Utils.DATE_DB);
            ReportGenerator rp = new ReportGenerator();
            rp.typeReport(from,to, ReportGenerator.ONE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Seleccione fecha inicio y fecha fin");
        }
        
    }//GEN-LAST:event_btnReport1ActionPerformed

    private void dtpReportsFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dtpReportsFromActionPerformed
        // TODO add your handling code here:
        dtpReportsTo.getMonthView().setLowerBound(dtpReportsFrom.getDate());
    }//GEN-LAST:event_dtpReportsFromActionPerformed

    private void dtpReportsToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dtpReportsToActionPerformed
        // TODO add your handling code here:
        dtpReportsFrom.getMonthView().setUpperBound(dtpReportsTo.getDate());
        triggerCallsSearch();
    }//GEN-LAST:event_dtpReportsToActionPerformed

    private void dtpReportsFromComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_dtpReportsFromComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_dtpReportsFromComponentShown

    private void pnlReportsComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlReportsComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlReportsComponentShown

    private void btnReport2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReport2ActionPerformed
        // TODO add your handling code here:
        try{
            String from = Utils.formatDate(dtpReportsFrom.getDate(), Utils.DATE_DB);
            String to = Utils.formatDate(dtpReportsTo.getDate(), Utils.DATE_DB);
            ReportGenerator rp = new ReportGenerator();
            rp.typeReport(from,to, ReportGenerator.TWO);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Seleccione fecha inicio y fecha fin");
        }
    }//GEN-LAST:event_btnReport2ActionPerformed

    private void btnReport3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReport3ActionPerformed
        // TODO add your handling code here:
        try{
            String from = Utils.formatDate(dtpReportsFrom.getDate(), Utils.DATE_DB);
            String to = Utils.formatDate(dtpReportsTo.getDate(), Utils.DATE_DB);
            ReportGenerator rp = new ReportGenerator();
            rp.typeReport(from,to, ReportGenerator.THREE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Seleccione fecha inicio y fecha fin");
        }
    }//GEN-LAST:event_btnReport3ActionPerformed

    /*----------------------------- ------------------------------------------
     -------------------------------▲-----------------------------------------
     ---------------- ------------ ▲‌ ▲---------------------------------------
    --------------------------CODIGO DE PROFILE-------------------------------*/
    private void refreshPnlProfile () {
        txtProfileName.setEnabled(false);
        txtProfileLastName.setEnabled(false);
        txtProfileEmail.setEnabled(false);
        txtProfilePass.setEnabled(false);
        txtProfileConfirm.setEnabled(false);
        
        Animations.hide(errProfileName, 255, 0, 0);
        Animations.hide(errProfileEmail, 255, 0, 0);
        Animations.hide(errProfilePass, 255, 0, 0);
        
        txtProfileName.setText(user.getName());
        txtProfileLastName.setText(user.getLastname());
        txtProfileEmail.setText(user.getEmail());
        txtProfilePass.setText("");
        txtProfileConfirm.setText("");
        
        lblUser.setText(user.getName() + " " + user.getLastname());
    }
    
    
    private void btnUserAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserAction1ActionPerformed
        try {
            boolean flag = true;
            if (!txtProfileName.isEnabled() && !txtProfileEmail.isEnabled() && !txtProfilePass.isEnabled()) {
                JOptionPane.showMessageDialog(this, "No se han hecho cambios en sus datos", "Aviso", JOptionPane.WARNING_MESSAGE);
                flag = false;
            }
            if (txtProfileName.isEnabled() && (txtProfileName.getText().trim().isEmpty() || txtProfileLastName.getText().trim().isEmpty())) {
                errProfileName.setText("Llene ambos campos");
                new Animations().appear(errProfileName, 255, 0, 0);
                flag = false;
            } else {
                Animations.hide(errProfileName, 255, 0, 0);
            }
            if (txtProfileEmail.isEnabled() && !new Utils().validate(txtProfileEmail.getText())) {
                errProfileEmail.setText("Formato de correo no válido");
                new Animations().appear(errProfileEmail, 255, 0, 0);
                flag = false;
            } else {
                Animations.hide(errProfileEmail, 255, 0, 0);
            }
            if (txtProfilePass.isEnabled() && (txtProfilePass.getText().trim().isEmpty() || txtProfileConfirm.getText().trim().isEmpty())) {
                errProfilePass.setText("Llene ambos campos");
                new Animations().appear(errProfilePass, 255, 0, 0);
                flag = false;
            } 
            else if (txtProfilePass.isEnabled() && !(txtProfilePass.getText().trim().equals(txtProfileConfirm.getText().trim()))) {
                errProfilePass.setText("Las claves ingresadas no concuerdan");
                new Animations().appear(errProfilePass, 255, 0, 0);
                flag = false;
            }
            else if (txtProfilePass.isEnabled() && txtProfilePass.getText().trim().length() < 6) {
                errProfilePass.setText("Clave demasiado corta");
                new Animations().appear(errProfilePass, 255, 0, 0);
                flag = false;
            }
            else {
                Animations.hide(errProfilePass, 255, 0, 0);
            }


            if (flag) {
                String name = user.getName();
                String lastname = user.getLastname();
                String email = user.getEmail();
                if (txtProfileName.isEnabled()) {
                    name = txtProfileName.getText().trim();
                    lastname = txtProfileLastName.getText().trim();
                }
                if (txtProfileEmail.isEnabled()) {
                    email = txtProfileEmail.getText().trim();
                }
                if (new UserController().updateUser(user.getId(),name,lastname,email, txtProfilePass.getText(), user.getUser_type().getId(), true)) {
                    JOptionPane.showMessageDialog(this, "Perfil modificado con éxito");
                    user = new UserController().getOne(user.getId());
                    refreshPnlProfile();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar");
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }//GEN-LAST:event_btnUserAction1ActionPerformed

    private void pnlProfileComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlProfileComponentHidden
        refreshPnlProfile();
    }//GEN-LAST:event_pnlProfileComponentHidden

    private void pnlProfileComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlProfileComponentShown
        refreshPnlProfile();
    }//GEN-LAST:event_pnlProfileComponentShown

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            /*for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }*/
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAuthAction;
    private javax.swing.JButton btnAuthClear;
    private javax.swing.JButton btnAuthSearchReset;
    private javax.swing.JButton btnAuthSearchReset1;
    private javax.swing.JButton btnCallsDetail;
    private javax.swing.JButton btnCallsSchoolSearch;
    private javax.swing.JButton btnDetailBack;
    private javax.swing.JButton btnDetailReport;
    private javax.swing.JButton btnDetailSave;
    private javax.swing.JButton btnEditEmail;
    private javax.swing.JButton btnEditName;
    private javax.swing.JButton btnEditPass;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMenuAuth;
    private javax.swing.JButton btnMenuCalls;
    private javax.swing.JButton btnMenuNew;
    private javax.swing.JButton btnMenuProfile;
    private javax.swing.JButton btnMenuProv;
    private javax.swing.JButton btnMenuReports;
    private javax.swing.JButton btnMenuSchool;
    private javax.swing.JButton btnMenuType;
    private javax.swing.JButton btnMenuUser;
    private javax.swing.JButton btnNewClear;
    private javax.swing.JButton btnNewListDel;
    private javax.swing.JButton btnNewListSearch;
    private javax.swing.JButton btnNewSave;
    private javax.swing.JButton btnNewSchoolSearch;
    private javax.swing.JButton btnProvAction;
    private javax.swing.JButton btnProvClear;
    private javax.swing.JButton btnProvSearchReset;
    private javax.swing.JButton btnReport1;
    private javax.swing.JButton btnReport2;
    private javax.swing.JButton btnReport3;
    private javax.swing.JButton btnSchoolAction;
    private javax.swing.JButton btnSchoolClear;
    private javax.swing.JButton btnSchoolSearchReset;
    private javax.swing.JButton btnTypeAction;
    private javax.swing.JButton btnTypeClear;
    private javax.swing.JButton btnTypeSearchReset;
    private javax.swing.JButton btnUserAction;
    private javax.swing.JButton btnUserAction1;
    private javax.swing.JButton btnUserClear;
    private javax.swing.JButton btnUserClear1;
    private javax.swing.JButton btnUserSearchReset;
    private javax.swing.JCheckBox chbDetailTalk;
    private javax.swing.JCheckBox chbNewViable;
    private javax.swing.JComboBox<String> cmbAuthSearch;
    private javax.swing.JComboBox<String> cmbAuthSearchType;
    private javax.swing.JComboBox<String> cmbAuthState;
    private javax.swing.JComboBox<String> cmbCallsSearch;
    private javax.swing.JComboBox<String> cmbCallsSearchType;
    private javax.swing.JComboBox<String> cmbNewType;
    private javax.swing.JComboBox<String> cmbProvSearch;
    private javax.swing.JComboBox<String> cmbProvSearchType;
    private javax.swing.JComboBox<String> cmbProvState;
    private javax.swing.JComboBox<String> cmbSchoolSearch;
    private javax.swing.JComboBox<String> cmbSchoolSearchType;
    private javax.swing.JComboBox<String> cmbSchoolState;
    private javax.swing.JComboBox<String> cmbTypeAction;
    private javax.swing.JComboBox<String> cmbTypeSearch;
    private javax.swing.JComboBox<String> cmbTypeSearchType;
    private javax.swing.JComboBox<String> cmbTypeState;
    private javax.swing.JComboBox<String> cmbUserSearch;
    private javax.swing.JComboBox<String> cmbUserSearchType;
    private javax.swing.JComboBox<String> cmbUserState;
    private javax.swing.JComboBox<String> cmbUserType;
    private org.jdesktop.swingx.JXDatePicker dtpCallsFrom;
    private org.jdesktop.swingx.JXDatePicker dtpCallsTo;
    private org.jdesktop.swingx.JXDatePicker dtpReportsFrom;
    private org.jdesktop.swingx.JXDatePicker dtpReportsTo;
    private javax.swing.JLabel errAuthName;
    private javax.swing.JLabel errAuthState;
    private javax.swing.JLabel errNewDescription;
    private javax.swing.JLabel errNewList;
    private javax.swing.JLabel errNewSchool;
    private javax.swing.JLabel errNewType;
    private javax.swing.JLabel errProfileEmail;
    private javax.swing.JLabel errProfileName;
    private javax.swing.JLabel errProfilePass;
    private javax.swing.JLabel errProvName;
    private javax.swing.JLabel errProvState;
    private javax.swing.JLabel errSchoolAddress;
    private javax.swing.JLabel errSchoolName;
    private javax.swing.JLabel errSchoolState;
    private javax.swing.JLabel errTypeAction;
    private javax.swing.JLabel errTypeName;
    private javax.swing.JLabel errTypeState;
    private javax.swing.JLabel errUserEmail;
    private javax.swing.JLabel errUserLastname;
    private javax.swing.JLabel errUserName;
    private javax.swing.JLabel errUserPass;
    private javax.swing.JLabel errUserState;
    private javax.swing.JLabel errUserType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lblAsigns;
    private javax.swing.JLabel lblCallsSchool;
    private javax.swing.JLabel lblDetailArchived;
    private javax.swing.JLabel lblDetailDate;
    private javax.swing.JTextArea lblDetailDescription;
    private javax.swing.JLabel lblDetailSchool;
    private javax.swing.JLabel lblDetailType;
    private javax.swing.JLabel lblDetailUser;
    private javax.swing.JLabel lblList;
    private javax.swing.JLabel lblNewSchool;
    private org.jdesktop.swingx.JXLabel lblUser;
    private javax.swing.JList<String> lstNew;
    private javax.swing.JPanel pnlAuth;
    private javax.swing.JPanel pnlCallDetail;
    private javax.swing.JPanel pnlCalls;
    private javax.swing.JPanel pnlCallsParam;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlNew;
    private javax.swing.JPanel pnlProfile;
    private javax.swing.JPanel pnlProv;
    private javax.swing.JPanel pnlReports;
    private javax.swing.JPanel pnlSchool;
    private javax.swing.JPanel pnlType;
    private javax.swing.JPanel pnlUser;
    private javax.swing.JScrollPane scrAsigns;
    private javax.swing.JScrollPane scrLstNew;
    private javax.swing.JTable tblAsigns;
    private javax.swing.JTable tblAuth;
    private javax.swing.JTable tblCalls;
    private javax.swing.JTable tblProv;
    private javax.swing.JTable tblSchool;
    private javax.swing.JTable tblType;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtAuthName;
    private javax.swing.JTextField txtAuthSearch;
    private javax.swing.JTextField txtCallsSearch;
    private javax.swing.JTextArea txtNewDesc;
    private javax.swing.JTextField txtProfileConfirm;
    private javax.swing.JTextField txtProfileEmail;
    private javax.swing.JTextField txtProfileLastName;
    private javax.swing.JTextField txtProfileName;
    private javax.swing.JTextField txtProfilePass;
    private javax.swing.JTextField txtProvName;
    private javax.swing.JTextField txtProvSearch;
    private javax.swing.JTextArea txtSchoolAddress;
    private javax.swing.JTextField txtSchoolName;
    private javax.swing.JTextField txtSchoolSearch;
    private javax.swing.JTextField txtTypeName;
    private javax.swing.JTextField txtTypeSearch;
    private javax.swing.JTextField txtUserEmail;
    private javax.swing.JTextField txtUserLastame;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTextField txtUserPass;
    private javax.swing.JTextField txtUserSearch;
    // End of variables declaration//GEN-END:variables
}
