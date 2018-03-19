/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utilities;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;


/**
 * Colección de métodos útiles y standalone, que no requieren de la creación de una clase
 * @author kevin
 */
public class Utils {

    public Utils() {
    }
    
    /**
     * Añade espacios a strings que contengan muchos caracteres consecutivos sin espacios
     * @param input El string a modificar
     * @param charMax El número máximo de caracteres sin espacio entre ellos
     * @return 
     */
    public static String wrapText (String input, int charMax) {
        String output = "";
        int charsWOSpace = 0;
        for (char ch : input.toString().toCharArray()) {
            if (ch == ' ') charsWOSpace = 0; 
            else charsWOSpace++;
            
            if (charsWOSpace >= charMax) {
                output += " ";
                charsWOSpace = 0;
            }
            output += ch;
        }
        return output;
    }
   
    public static final int DATE_UI = 1;
    public static final int DATE_DB = 2;
    
    public static String formatDate (Date date, int type) {
        if (type == Utils.DATE_UI)
            return new SimpleDateFormat("dd/MM/yyyy").format(date);
        else
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    
    /**
     * Hace que el componente parametrado obedezca a su prefferedSize
     * @param comp 
     */
    public static void obeySize (Component comp) {
        comp.setPreferredSize(comp.getPreferredSize());
        comp.setMaximumSize(comp.getPreferredSize());
        comp.setMinimumSize(comp.getPreferredSize());
        comp.setSize(comp.getPreferredSize());
        comp.revalidate();
    }
}
