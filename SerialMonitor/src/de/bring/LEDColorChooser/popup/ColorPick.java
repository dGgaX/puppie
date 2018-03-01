/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bring.LEDColorChooser.popup;

import de.bring.LEDColorChooser.LEDColorChooser;
import java.awt.Color;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
//import org.jhotdraw.color.ColorWheelChooser;

/**
 *
 * @author Karima
 */
public class ColorPick extends PopupJFrame {
    
    /**
     * Create new form ColorPick
     * @param parent
     */
    public ColorPick(LEDColorChooser parent) {
        super(parent);
        
        initComponents();
        setFrameLocation(this);

        jColorChooser1.getSelectionModel().addChangeListener((ChangeEvent e) -> {
            parent.setColor(jColorChooser1.getColor());
        });
    }
    
    public void setColor(Color color) {
        jColorChooser1.setColor(color);
    }    
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();

        setTitle("Color");
        setAlwaysOnTop(true);
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        AbstractColorChooserPanel[] panels = jColorChooser1.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            if(!accp.getDisplayName().equals("RGB")) {
                //    jColorChooser1.removeChooserPanel(accp);
            }
        }
        //jColorChooser1.addChooserPanel(new ColorWheelChooser());
        jColorChooser1.setBackground(java.awt.SystemColor.window);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jColorChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jColorChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
            jColorChooser1.setColor(parent.getLEDColor());
    }//GEN-LAST:event_formWindowOpened
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JColorChooser jColorChooser1;
    // End of variables declaration//GEN-END:variables
}
