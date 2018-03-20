/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utilities;

import com.sv.udb.controllers.CallController;
import com.sv.udb.models.Call;
import com.sv.udb.resources.ConnectionDB;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author kevin
 */
public class ReportGenerator {
    
    public static void detailReport(int id) {
        HashMap map;
        
        try {
            //Rutas de archivos
            String jrxmlFileName = new File("src/main/java/com/sv/udb/reports/Detail.jrxml").getAbsolutePath();
            String jasperFileName = new File("src/main/java/com/sv/udb/reports/Detail.jasper").getAbsolutePath();
            String pdfFileName = new File("reports/detail.pdf").getAbsolutePath();
            
            //Compilando jasperreport
            JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);
            
            //Conexion
            Connection conn = new ConnectionDB().getConn();
            
            //Yo lo uso porque soy cul-ero
            Call call = new CallController().getOne(id);
            
            //seteando los parametros que recibe el reporte
            map = new HashMap();
            map.put("id","xd");
            map.put("viable", call.getViable());
            
            //Para generar al reporte directamente con una conexión y query (debería ser suficiente para reportes basados en tablas)
            //JasperPrint print = (JasperPrint)JasperFillManager.fillReport(jasperFileName, map, conn);
            
            //Para generar al reporte a la ranger sin conexion y pasandole todos los datos que necesita
            JasperPrint print = (JasperPrint)JasperFillManager.fillReport(jasperFileName, map, new JREmptyDataSource(1));
            
            //guardando
            JasperExportManager.exportReportToPdfFile(print, pdfFileName);
            
            //mostrando
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(new File(pdfFileName));
                } catch (IOException ex) {
                    System.out.println("No abrió xd " + ex);
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static final int ONE = 1;
    public static final int TWO= 2;
    public static final int THREE = 3;
    public void typeReport(String from, String to, int number) {
        HashMap map;
        
        try {
            String jrxmlFileName="";
            String jasperFileName="";
            String pdfFileName="";
            switch(number){
                case ONE:
                    //Rutas de archivos
                    jrxmlFileName = new File("src/main/java/com/sv/udb/reports/Type.jrxml").getAbsolutePath();
                    jasperFileName = new File("src/main/java/com/sv/udb/reports/Type.jasper").getAbsolutePath();
                    pdfFileName = new File("reports/type.pdf").getAbsolutePath();
                    break;
                case TWO:
                    //Rutas de archivos
                    jrxmlFileName = new File("src/main/java/com/sv/udb/reports/Viability.jrxml").getAbsolutePath();
                    jasperFileName = new File("src/main/java/com/sv/udb/reports/Viability.jasper").getAbsolutePath();
                    pdfFileName = new File("reports/viability.pdf").getAbsolutePath();
                    break;
                case THREE:
                    //Rutas de archivos
                    jrxmlFileName = new File("src/main/java/com/sv/udb/reports/Top.jrxml").getAbsolutePath();
                    jasperFileName = new File("src/main/java/com/sv/udb/reports/Top.jasper").getAbsolutePath();
                    pdfFileName = new File("reports/top.pdf").getAbsolutePath();
                    break;
            }
            
            
            //Compilando jasperreport
            JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);
            //Conexion
            Connection conn = new ConnectionDB().getConn();
            System.err.println(from);
            System.err.println(to);
            map = new HashMap();
            map.put("init_date",from);
            map.put("end_date", to);
            JasperPrint print = (JasperPrint)JasperFillManager.fillReport(jasperFileName, map,conn);
            
            //guardando
            JasperExportManager.exportReportToPdfFile(print, pdfFileName);
            
            //mostrando
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(new File(pdfFileName));
                } catch (IOException ex) {
                    System.out.println("No abrió :c " + ex);
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
