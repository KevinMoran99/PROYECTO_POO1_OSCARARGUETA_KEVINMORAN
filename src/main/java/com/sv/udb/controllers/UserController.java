/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controllers;

import com.sv.udb.models.User;
import com.sv.udb.models.User_type;
import com.sv.udb.resources.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author kevin
 */
public class UserController {

    Connection conn;
    
    public UserController() {
        conn = new ConnectionDB().getConn();
    }
    
    public User getOne (int id) {
        User resp = null;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM users u INNER JOIN user_types ut ON u.user_type_id = ut.id WHERE u.id = ?");
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), new User_type(rs.getInt(8), rs.getString(9)) , rs.getBoolean(7));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
    
   public List<User> getAll (boolean activeOnly) {
        List<User> resp = new ArrayList<>();
        try {
            //String que definirá si se devuelven solo los activos o no
            String actCondition = activeOnly ? " WHERE state = 1" : "";
            
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM users u INNER JOIN user_types ut ON u.user_type_id = ut.id" + actCondition);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), new User_type(rs.getInt(8), rs.getString(9)) , rs.getBoolean(7)));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar usuarios: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión al consultar usuarios: " + ex.getMessage());
            }
        }
        return resp;
    }
    
    public User login (String email, String pass) {
        User resp = null;
        try {
            String query = "SELECT u.id, u.name, u.lastname, u.email, u.pass, t.*, u.state "
                    + "FROM users u INNER JOIN user_types t ON u.user_type_id = t.id "
                    + "WHERE u.email = ? AND u.pass = ?";
            PreparedStatement cmd = this.conn.prepareStatement(query);
            cmd.setString(1, email);
            cmd.setString(2, pass);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        new User_type(rs.getInt(6), rs.getString(7)),
                        rs.getBoolean(8));
            }
            
        } catch (SQLException ex) {
            System.err.println("Error al consultar usuario: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión al consultar usuario: " + ex.getMessage());
            }
        }
        return resp;
    }
    
    public boolean addUser(String name, String lastname, String email, String pass, int user_type, boolean state){
        boolean resp = false;
        BasicTextEncryptor bte = new BasicTextEncryptor();
        bte.setPassword("poo1");
        
        String encrypt = bte.encrypt(pass);
        try {
            PreparedStatement cmd = this.conn.prepareStatement("insert into users values(null,?,?,?,?,?,?)");
            cmd.setString(1, name);
            cmd.setString(2, lastname);
            cmd.setString(3, email);
            cmd.setString(4, encrypt);
            cmd.setInt(5, user_type);
            cmd.setBoolean(6, state);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al guardar usuario" + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion en usuario: " + e.getMessage());
            }
        }
        
        return resp;
    }
}
