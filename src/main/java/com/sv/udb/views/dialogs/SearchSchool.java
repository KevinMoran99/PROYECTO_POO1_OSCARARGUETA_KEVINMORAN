/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.views.dialogs;

import com.sv.udb.controllers.SchoolController;
import com.sv.udb.models.School;
import com.sv.udb.utilities.Animations;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public class SearchSchool extends javax.swing.JPanel {

    /**
     * Escuela seleccionada, variable a retornar
     */
    School school;
    
    /**
     * Panel utilizado como dialog para buscar escuelas
     */
    public SearchSchool() {
        initComponents();
        Animations.initStyle(this);
        
        DefaultTableModel model = (DefaultTableModel) tblSchool.getModel();
        while (model.getRowCount() > 0) model.removeRow(0);
        
        for (School object : new SchoolController().getAll(true))
            model.addRow(new Object[]{object, object.getAddress()});
    }
    
    /**
     * Método a llamar desde el frame padre luego de dar OK en el dialog
     * @return 
     */
    public School getValue() {
        return school;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        cmbSchoolSearchType = new javax.swing.JComboBox<>();
        txtSchoolSearch = new javax.swing.JTextField();
        btnSchoolSearchReset = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSchool = new javax.swing.JTable();

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("Escuelas");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Buscar por:");

        cmbSchoolSearchType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbSchoolSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Dirección", "N/A" }));
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
                "Nombre", "Dirección"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addComponent(jScrollPane3)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(cmbSchoolSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(txtSchoolSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnSchoolSearchReset))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cmbSchoolSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSchoolSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSchoolSearchReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void triggerSearch () {
        try {
            String param = txtSchoolSearch.getText().trim();
            int type = 0;
            
            switch(String.valueOf(cmbSchoolSearchType.getSelectedItem())) {
                case "Nombre":
                    type = SchoolController.BY_NAME;
                    break;
                case "Dirección":
                    type = SchoolController.BY_ADDRESS;
                    break;
                case "N/A":
                    type = SchoolController.NO_FIELD;
                    break;
            }
            
            DefaultTableModel model = (DefaultTableModel) tblSchool.getModel();
            while (model.getRowCount() > 0) model.removeRow(0);

            for (School object : new SchoolController().search(type, param, true))
                model.addRow(new Object[]{object, object.getAddress()});
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Obteniendo escuela seleccionada
    private void tblSchoolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSchoolMouseClicked
        school = (School) tblSchool.getValueAt(tblSchool.getSelectedRow(), 0);
    }//GEN-LAST:event_tblSchoolMouseClicked

    private void cmbSchoolSearchTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSchoolSearchTypeActionPerformed
        if (cmbSchoolSearchType.getSelectedIndex() == 2)
            txtSchoolSearch.setVisible(false);
        else 
            txtSchoolSearch.setVisible(true);
        this.revalidate();
        triggerSearch();
    }//GEN-LAST:event_cmbSchoolSearchTypeActionPerformed

    private void txtSchoolSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSchoolSearchKeyReleased
        triggerSearch();
    }//GEN-LAST:event_txtSchoolSearchKeyReleased

    private void btnSchoolSearchResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSchoolSearchResetActionPerformed
        cmbSchoolSearchType.setSelectedIndex(2);
        txtSchoolSearch.setText("");
    }//GEN-LAST:event_btnSchoolSearchResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSchoolSearchReset;
    private javax.swing.JComboBox<String> cmbSchoolSearchType;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tblSchool;
    private javax.swing.JTextField txtSchoolSearch;
    // End of variables declaration//GEN-END:variables
}
