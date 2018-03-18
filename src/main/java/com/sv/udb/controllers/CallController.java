/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controllers;

import com.sv.udb.models.Call;
import com.sv.udb.models.Complaint_type;
import com.sv.udb.models.School;
import com.sv.udb.models.User;
import com.sv.udb.resources.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author kevin
 */
public class CallController {
    Connection conn;

    public CallController() {
        conn = new ConnectionDB().getConn();
    }
    
    public boolean addCall (School school, boolean viable, Complaint_type complaint_type, User user, String description) {
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO calls VALUES(null,?,?,?,?,?,NOW(),0)");
            cmd.setInt(1, school.getId());
            cmd.setBoolean(2, viable);
            cmd.setInt(3, complaint_type.getId());
            cmd.setInt(4, user.getId());
            cmd.setString(5, description);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al guardar denuncia: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion al guardar denuncia: " + e.getMessage());
            }
        }
        
        return resp;
    }
    
    public Call getLast () {
        Call resp = null;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM calls WHERE id = (SELECT MAX(id) FROM calls)");
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp = new Call(
                        rs.getInt(1), 
                        new SchoolController().getOne(rs.getInt(2)),
                        rs.getBoolean(3),
                        new ComplaintTypeController().getOne(rs.getInt(4)),
                        new UserController().getOne(rs.getInt(5)),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getBoolean(8));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar denuncia: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión al consultar denuncia: " + ex.getMessage());
            }
        }
        return resp;
    }
}
