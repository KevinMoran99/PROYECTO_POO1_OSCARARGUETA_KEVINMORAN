/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.views.dialogs;

import com.sv.udb.controllers.ProviderController;
import com.sv.udb.models.Provider;
import com.sv.udb.utilities.Animations;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public class SearchProvider extends javax.swing.JPanel {

    /**
     * Proveedor seleccionado, variable a retornar
     */
    Provider prov;
    
    /**
     * Creates new form SearchProvider
     */
    public SearchProvider() {
        initComponents();
        Animations.initStyle(this);
        
        DefaultTableModel model = (DefaultTableModel) tblProv.getModel();
        while (model.getRowCount() > 0) model.removeRow(0);
        
        for (Provider object : new ProviderController().getAll(true))
            model.addRow(new Object[]{object});
    }
    
    /**
     * Método a llamar desde el frame padre luego de dar OK en el dialog
     * @return 
     */
    public Provider getValue() {
        return prov;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel22 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        cmbProvSearchType = new javax.swing.JComboBox<>();
        txtProvSearch = new javax.swing.JTextField();
        btnProvSearchReset = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblProv = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(491, 291));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel22.setText("Proveedores");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Buscar por:");

        cmbProvSearchType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbProvSearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "N/A" }));
        cmbProvSearchType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvSearchTypeActionPerformed(evt);
            }
        });

        txtProvSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProvSearch.setForeground(new java.awt.Color(6, 43, 51));
        txtProvSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProvSearchKeyReleased(evt);
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
                "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProvMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblProv);
        if (tblProv.getColumnModel().getColumnCount() > 0) {
            tblProv.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(cmbProvSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtProvSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnProvSearchReset)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cmbProvSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProvSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProvSearchReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void triggerSearch () {
        try {
            String param = txtProvSearch.getText().trim();
            int type = 0;
            
            switch(String.valueOf(cmbProvSearchType.getSelectedItem())) {
                case "Nombre":
                    type = ProviderController.BY_NAME;
                    break;
                case "N/A":
                    type = ProviderController.NO_FIELD;
                    break;
            }
            
            DefaultTableModel model = (DefaultTableModel) tblProv.getModel();
            while (model.getRowCount() > 0) model.removeRow(0);

            for (Provider object : new ProviderController().search(type, param, true))
                model.addRow(new Object[]{object});
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void cmbProvSearchTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvSearchTypeActionPerformed
        if (cmbProvSearchType.getSelectedIndex() == 1)
            txtProvSearch.setVisible(false);
        else 
            txtProvSearch.setVisible(true);
        this.revalidate();
        triggerSearch();    
    }//GEN-LAST:event_cmbProvSearchTypeActionPerformed

    private void btnProvSearchResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvSearchResetActionPerformed
        cmbProvSearchType.setSelectedIndex(1);
        txtProvSearch.setText("");
    }//GEN-LAST:event_btnProvSearchResetActionPerformed

    private void tblProvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProvMouseClicked
        prov = (Provider) tblProv.getValueAt(tblProv.getSelectedRow(), 0);
    }//GEN-LAST:event_tblProvMouseClicked

    private void txtProvSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvSearchKeyReleased
        triggerSearch();
    }//GEN-LAST:event_txtProvSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProvSearchReset;
    private javax.swing.JComboBox<String> cmbProvSearchType;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable tblProv;
    private javax.swing.JTextField txtProvSearch;
    // End of variables declaration//GEN-END:variables
}
