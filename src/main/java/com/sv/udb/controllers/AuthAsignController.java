/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controllers;

import com.sv.udb.models.Authority;
import com.sv.udb.models.Call;
import com.sv.udb.resources.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author kevin
 */
public class AuthAsignController {
    Connection conn;

    public AuthAsignController() {
        conn = new ConnectionDB().getConn();
    }
    
    public boolean addAuthAsign (Call call, Authority authority) {
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO authority_asigns VALUES(null,?,?)");
            cmd.setInt(1, call.getId());
            cmd.setInt(2, authority.getId());
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al guardar autoridades relacionadas a denuncia" + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion al guardar autoridades relacionadas a denuncia: " + e.getMessage());
            }
        }
        
        return resp;
    }
}
