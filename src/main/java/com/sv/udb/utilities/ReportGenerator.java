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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author kevin
 */
public class ReportGenerator {
    
    private static void removeBlankPage(List<JRPrintPage> pages) {
        for (Iterator<JRPrintPage> i = pages.iterator(); i.hasNext();) {
            JRPrintPage page = i.next();
            if (page.getElements().size() == 0) {
                i.remove();
            }
        }
    }
    
    
    public static void detailReport(int id) {
        HashMap map;
        
        try {
            //Rutas de archivos
            String jrxmlFileName = new File("src/main/java/com/sv/udb/reports/Detail.jrxml").getAbsolutePath();
            String jasperFileName = new File("src/main/java/com/sv/udb/reports/Detail.jasper").getAbsolutePath();
            String pdfFileName = new File("reports/detail.pdf").getAbsolutePath();
            
            //String jrxmlFileName = new File("src/main/java/com/sv/udb/reports/SubreportProvider.jrxml").getAbsolutePath();
            //String jasperFileName = new File("src/main/java/com/sv/udb/reports/SubreportProvider.jasper").getAbsolutePath();
            
            //String jrxmlFileName = new File("src/main/java/com/sv/udb/reports/SubreportAuthority.jrxml").getAbsolutePath();
            //String jasperFileName = new File("src/main/java/com/sv/udb/reports/SubreportAuthority.jasper").getAbsolutePath();
            
            
            //Compilando jasperreport
            JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);
            
            //Conexion
            Connection conn = new ConnectionDB().getConn();
            
            //Yo lo uso porque soy cul-ero
            Call call = new CallController().getOne(id);
            
            //seteando los parametros que recibe el reporte
            map = new HashMap();
            map.put("id",String.valueOf(id));
            map.put("viable", call.getViable());
            map.put("school", call.getSchool().toString());
            map.put("address", call.getSchool().getAddress());
            map.put("date", Utils.formatDate(call.getCall_date(), Utils.DATE_UI));
            map.put("type", call.getComplaint_type().toString());
            map.put("description", call.getDescription());
            map.put("user", call.getUser().getName() + " " + call.getUser().getLastname());
            map.put("talk", call.isTalk_given());
            map.put("conn", conn);
            
            //Para generar al reporte directamente con una conexión y query (debería ser suficiente para reportes basados en tablas)
            JasperPrint print = (JasperPrint)JasperFillManager.fillReport(jasperFileName, map, conn);
            
            //Para generar al reporte a la ranger sin conexion y pasandole todos los datos que necesita
            //JasperPrint print = (JasperPrint)JasperFillManager.fillReport(jasperFileName, map, new JREmptyDataSource(1));
            
            removeBlankPage(print.getPages());
            
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
    
    
    public void typeReport() {
        HashMap map;
        
        try {
            //Rutas de archivos
            String jrxmlFileName = new File("src/main/java/com/sv/udb/reports/Type.jrxml").getAbsolutePath();
            String jasperFileName = new File("src/main/java/com/sv/udb/reports/Type.jasper").getAbsolutePath();
            String pdfFileName = new File("reports/type.pdf").getAbsolutePath();
            
            //Compilando jasperreport
            JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);
            //Conexion
            Connection conn = new ConnectionDB().getConn();
            JasperPrint print = (JasperPrint)JasperFillManager.fillReport(jasperFileName, null,conn);
            
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
