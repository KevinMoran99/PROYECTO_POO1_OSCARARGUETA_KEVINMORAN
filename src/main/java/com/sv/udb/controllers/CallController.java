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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kevin
 */
public class CallController {
    Connection conn;
    
    //Constantes que identifican el tipo de búsqueda en search
    public static final int BY_SCHOOL = 1;
    public static final int BY_TYPE = 2;
    public static final int BY_DESCRIPTION = 3;
    public static final int BY_VIABLE = 4;
    public static final int BY_USER = 5;
    public static final int NO_FIELD = 6;

    public CallController() {
        conn = new ConnectionDB().getConn();
    }
    
    public List<Call> getAll () {
        List<Call> resp = new ArrayList<>();
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM calls");
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp.add(new Call(
                    rs.getInt(1), 
                    new SchoolController().getOne(rs.getInt(2)),
                    rs.getBoolean(3),
                    new ComplaintTypeController().getOne(rs.getInt(4)),
                    new UserController().getOne(rs.getInt(5)),
                    rs.getString(6),
                    rs.getDate(7),
                    rs.getBoolean(8))
                );
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
    
    public Call getOne (int id) {
        Call resp = null;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM calls WHERE id = ?");
            cmd.setInt(1, id);
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
    
    public boolean updateCall (int id, boolean talk_given) {
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("UPDATE calls SET talk_given = ? WHERE id = ?");
            cmd.setBoolean(1, talk_given);
            cmd.setInt(2, id);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al modificar denuncia" + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion al modificar denuncia: " + e.getMessage());
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
    
    /**
     * Devuelve todas las denuncias bajo parámetros de búsqueda
     * @param type int indicando el campo por el que se filtrará
     * @param param El parámetro con el que se compararán los filtros
     * @param from La fecha desde la que se filtrará
     * @param to La fecha hasta la que se filtrará
     * @return 
     */
    public List<Call> search (int type, Object param, String from, String to){
        List<Call> resp = new ArrayList<>();
        try {
            //Filtros de fecha
            if (!from.equals("")) {
                from = " AND call_date >= '" + from + " 00:00:00'";
            }
            if (!to.equals("")) {
                to = " AND call_date <= '" + to + " 00:00:00'";
            }
            
            PreparedStatement cmd = null;
            
            switch (type) {
                case CallController.BY_SCHOOL:
                    cmd = this.conn.prepareStatement("SELECT * FROM calls WHERE school_id = ?" + from + to);
                    cmd.setInt(1, ((School)param).getId());
                    break;
                case CallController.BY_TYPE:
                    cmd = this.conn.prepareStatement("SELECT * FROM calls WHERE complaint_id = ?" + from + to);
                    cmd.setInt(1, ((Complaint_type)param).getId());
                    break;
                case CallController.BY_DESCRIPTION:
                    cmd = this.conn.prepareStatement("SELECT * FROM calls WHERE description LIKE ?" + from + to);
                    cmd.setString(1, String.valueOf("%" + param + "%"));
                    break;
                case CallController.BY_VIABLE:
                    cmd = this.conn.prepareStatement("SELECT * FROM calls WHERE viable = ?" + from + to);
                    if ((String.valueOf(param)).equals("Es viable"))
                        cmd.setInt(1, 1);
                    else
                        cmd.setInt(1, 0);
                    break;
                case CallController.BY_USER:
                    cmd = this.conn.prepareStatement("SELECT * FROM calls WHERE user_id = ?" + from + to);
                    cmd.setInt(1, ((User)param).getId());
                    break;
                default:
                    cmd = this.conn.prepareStatement("SELECT * FROM calls WHERE true" + from + to);
                    break;
            }
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp.add(new Call(
                    rs.getInt(1), 
                    new SchoolController().getOne(rs.getInt(2)),
                    rs.getBoolean(3),
                    new ComplaintTypeController().getOne(rs.getInt(4)),
                    new UserController().getOne(rs.getInt(5)),
                    rs.getString(6),
                    rs.getDate(7),
                    rs.getBoolean(8))
                );
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar denuncias: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión al consultar denuncias: " + ex.getMessage());
            }
        }
        return resp;
    }
}
