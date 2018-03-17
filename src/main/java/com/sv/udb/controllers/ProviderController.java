/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controllers;

import com.sv.udb.models.Provider;
import com.sv.udb.resources.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class ProviderController {
    Connection conn;
    
    //Constantes que identifican el tipo de búsqueda en search
    public static final int BY_NAME = 1;
    public static final int BY_STATE = 2;
    public static final int NO_FIELD = 3;

    public ProviderController() {
        conn = new ConnectionDB().getConn();
    }
    
    /**
     * Devuelve todos los proveedores activos
     * @param activeOnly
     * @return true si se requieren solo los resultados activos, false si se requieren todos
     */
    public List<Provider> getAll (boolean activeOnly) {
        List<Provider> resp = new ArrayList<>();
        try {
            //String que definirá si se devuelven solo los activos o no
            String actCondition = activeOnly ? " WHERE state = 1" : "";
            
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM providers" + actCondition);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp.add(new Provider(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar proveedores: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión al consultar proveedores: " + ex.getMessage());
            }
        }
        return resp;
    }
    
    
    /**
     * Devuelve todas los proveedores bajo parámetros de búsqueda
     * @param type int indicando el campo por el que se filtrará
     * @param param El texto con el que se compararán los filtros
     * @param activeOnly true si se requieren solo los resultados activos, false si se requieren todos
     * @return 
     */
    public List<Provider> search (int type, Object param, boolean activeOnly){
        List<Provider> resp = new ArrayList<>();
        try {
            //String que definirá si se devuelven solo los activos o no
            String actCondition = activeOnly ? " AND state = 1" : "";
            
            PreparedStatement cmd = null;
            
            switch (type) {
                case ProviderController.BY_NAME:
                    cmd = this.conn.prepareStatement("SELECT * FROM providers WHERE name LIKE ?" + actCondition);
                    cmd.setString(1, String.valueOf("%" + param + "%"));
                    break;
                case ProviderController.BY_STATE:
                    cmd = this.conn.prepareStatement("SELECT * FROM providers WHERE state = ?");
                    if ((String.valueOf(param)).equals("Activo"))
                        cmd.setInt(1, 1);
                    else
                        cmd.setInt(1, 0);
                    break;
                default:System.out.println("xD");
                    cmd = this.conn.prepareStatement("SELECT * FROM providers WHERE true" + actCondition);
                    break;
            }
            
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp.add(new Provider(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar proveedores: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión al consultar proveedores: " + ex.getMessage());
            }
        }
        return resp;
    }
}