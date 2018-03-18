/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controllers;

import com.sv.udb.models.Provider;
import com.sv.udb.models.Call;
import com.sv.udb.resources.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author kevin
 */
public class ProvAsignController {
    Connection conn;

    public ProvAsignController() {
        conn = new ConnectionDB().getConn();
    }
    
    public boolean addAuthAsign (Call call, Provider provider) {
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO provider_asigns VALUES(null,?,?)");
            cmd.setInt(1, call.getId());
            cmd.setInt(2, provider.getId());
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al guardar proveedores relacionados a denuncia" + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion al guardar proveedores relacionados a denuncia: " + e.getMessage());
            }
        }
        
        return resp;
    }
}
