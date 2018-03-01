/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bring.LEDColorChooser.popup;

import de.bring.LEDColorChooser.LEDColorChooser;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Wohnzimmer
 */
public class PopupJFrame extends JFrame {
    protected LEDColorChooser parent;
    private final int margin = 10;
    
    /**
     * Creates new form PopupJFrame
     * @param parent
     */
    public PopupJFrame(LEDColorChooser parent) {
        this.parent = parent;
        JFrame jFrame = this;
        
        setAlwaysOnTop(true);
        setType(java.awt.Window.Type.UTILITY);
        setUndecorated(true);
        getContentPane().setBackground(java.awt.SystemColor.window);
        getContentPane().setForeground(java.awt.SystemColor.windowText);
        getRootPane().setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder));
        this.setResizable(false);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                jFrame.setVisible(false);
            }
        });
    }
    
    /**
     * Get the Height of the Tray
     * @return height
     */
    public int getTrayHeight() {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        return scrnSize.height - winSize.height;
    }
    
    /**
     * Sets the PopupJFrame to the bottom left Corner
     * @param jFrame
     */
    protected void setFrameLocation(javax.swing.JFrame jFrame) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - jFrame.getWidth() - margin;
        int y = (int) rect.getMaxY() - jFrame.getHeight() - getTrayHeight() - margin;
        jFrame.setLocation(x, y);
    }
}
