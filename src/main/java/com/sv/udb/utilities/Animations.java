/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utilities;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * Colección de animaciones custom y cualquier otro método relacionado al GUI
 * @author kevin
 */
public class Animations {
    
    private static int alpha;
    private static Timer timer;

    public Animations() {
    }
    
    /**
     * Hace visible a un componente con un efecto fade (para que funcione, usar el
     * método hide de esta clase en vez de setVisible(false))
     * @param comp El componente afectado
     * @param R Valor R del RGB del foreground del componente
     * @param G Valor G del RGB del foreground del componente
     * @param B Valor B del RGB del foreground del componente 
     */
    public static void appear(JComponent comp, int R, int G, int B){
        alpha = 0;
        
        timer = new Timer(10, (ActionEvent ae) -> {
            alpha = alpha + 15;
            comp.setForeground(new Color(R, G, B, alpha));
            comp.setBackground(new Color(255, 255, 255, alpha));
            if (alpha == 255) 
                timer.stop();
            
        });
        timer.start();
    }
    
    /**
     * Hace invisible a un componente con un efecto fade 
     * @param comp El componente afectado
     * @param R Valor R del RGB del foreground del componente
     * @param G Valor G del RGB del foreground del componente
     * @param B Valor B del RGB del foreground del componente 
     */
    public static void fade(JComponent comp, int R, int G, int B){
        alpha = 255;
        
        timer = new Timer(10, (ActionEvent ae) -> {
            alpha = alpha - 15;
            comp.setForeground(new Color(R, G, B, alpha));
            comp.setBackground(new Color(255, 255, 255, alpha));
            if (alpha == 0) 
                timer.stop();
            
        });
        timer.start();
    }
    
    /**
     * Hace invisible a un componente (visualmente idéntico a setVisible(false),
     * funcionamiento distinto)
     * @param comp El componente afectado
     * @param R Valor R del RGB del foreground del componente
     * @param G Valor G del RGB del foreground del componente
     * @param B Valor B del RGB del foreground del componente 
     */
    public static void hide(JComponent comp, int R, int G, int B){
        comp.setForeground(new Color(R, G, B, 0));
        comp.setBackground(new Color(255, 255, 255, 0));
    }
}
