/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bring.LEDColorChooser;

import java.awt.MenuItem;
import java.awt.event.ActionEvent;

/**
 *
 * @author Wohnzimmer
 */
public class ContextMenu extends java.awt.PopupMenu{
    
    public ContextMenu(LEDColorChooser parent) {
        super();
        
        MenuItem dimmerItem = new MenuItem("Dimmer");
        MenuItem swingItem = new MenuItem("Dimmer Swing");
        MenuItem cChangeItem = new MenuItem("Color Changer");
        MenuItem exitItem = new MenuItem("Beenden");
    
        
        this.add(dimmerItem);
        this.add(swingItem);
        this.addSeparator();
        this.add(cChangeItem);
        this.addSeparator();
        this.add(exitItem);
        
        
        dimmerItem.addActionListener((ActionEvent e) -> {
            parent.dimmer();
        });
        cChangeItem.addActionListener((ActionEvent e) -> {
            parent.changer();
        });
        swingItem.addActionListener((ActionEvent e) -> {
            parent.hopper();
        });
        exitItem.addActionListener((ActionEvent e) -> {
            parent.exitProgram(0);
        });
        
    }
}
