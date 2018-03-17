/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Container;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;
        
/**
 * Colección de animaciones custom y cualquier otro método relacionado al GUI
 * @author kevin
 */
public class Animations {
    
    private int alpha;
    private Timer timer;

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
    public void appear(JComponent comp, int R, int G, int B){
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
    public void fade(JComponent comp, int R, int G, int B){
        alpha = 255;
        timer = new Timer(10, (ActionEvent ae) -> {
            alpha = alpha - 15;
            comp.setForeground(new Color(R, G, B, alpha));
            comp.setBackground(new Color(255, 255, 255, alpha));
            if (alpha == 0) {
                timer.stop();
            }
            
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
    
    
    /**
     * Permite que el color de background de los JButton hijos del contenedor 
     * argumentado abarque todo el botón
     * @param ctn El contenedor cuyos botones serán estilizados
     */
    public static void initStyle(Container ctn) {
        for (Component comp : ctn.getComponents()) {
            if (comp instanceof Container) 
                initStyle((Container) comp);
            if (comp instanceof JButton){
                ((JButton) comp).setContentAreaFilled(false);
                ((JButton) comp).setOpaque(true);
            } 
            if (comp instanceof JXDatePicker) {
                ((JXDatePicker) comp).setFormats(new String[]{"dd/MM/yyyy"});
            }
        }
    }
    
    
    
    /**
     * Hace que el primer componente argumentado se muestre, y el segundo se oculte
     * @param parent 
     * @param visible
     * @param invisible 
     */
    public static void toggleVisible(Component parent, JComponent visible, JComponent invisible) {
        visible.setVisible(true);
        invisible.setVisible(false);
        parent.revalidate();
    }
    
    /**
     * Hace visibles a todos los componentes argumentados
     * @param parent
     * @param comps 
     */
    public static void visibilizeComponents (Component parent, JComponent... comps) {
        for (JComponent comp : comps)
            comp.setVisible(true);
        
        parent.revalidate();
    }
    
    /**
     * Hace invisibles a todos los componentes argumentados
     * @param parent
     * @param comps 
     */
    public static void invisibilizeComponents (Component parent, JComponent... comps) {
        for (JComponent comp : comps)
            comp.setVisible(false);
        
        parent.revalidate();
    }
}
