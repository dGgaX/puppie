/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bring.LEDColorChooser;

import de.bring.LEDColorChooser.popup.*;
import gnu.io.CommPortIdentifier;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Wohnzimmer
 */
public class LEDColorChooser {
    private Color LEDColor = Color.BLACK;
    
    private final int dimMin = 0;
    private final int dimMax = 255;
    private int dimCur = 0;
    
    private final TrayIcon trayIcon = new TrayIcon(createImage("images/icon32.png", "tray icon"));
    private final SystemTray tray = SystemTray.getSystemTray();
    private final SerialConnection connection = new SerialConnection(this);
    
    private final ColorPick colorPick = new ColorPick(this);
    private final ColorDimmer colorDimmer = new ColorDimmer(this);
    private final ColorChange colorChange = new ColorChange(this);
    private final ColorDimmerSwing colorHop = new ColorDimmerSwing(this);
    
    /**
     * Init the Program
     */
    public LEDColorChooser() {
        initComponents();
    }
    
    /**
     * Init Components:
     *  Serial Connection
     *  TrayIcon
     *  
     */
    private void initComponents() {

//      Get Serial-Connection
//------------------------------------------------------------------------------        
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            System.exit(0);
        }
        
        if (connection.commPortSelected()) {
            connection.connect();
            if (!connection.isConnected()) {
                System.out.println("No Connection established");
                System.exit(0);
            }
        } else {
            CommPortIdentifier[] commPorts = connection.getAvailableCommPorts();
            if (commPorts.length == 0) {
                System.out.println("No Commports found");
                System.exit(0);
            } else {
                
                //CommPortSelection

            }
        }

//      Add Popup-Menu        
//------------------------------------------------------------------------------        
        trayIcon.setPopupMenu(new ContextMenu(this));
        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            System.exit(0);
        }
        
        trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                    colorPick.setVisible(true);
                }
            
            }
        });
    }
    
    /**
     *
     * @param newColor
     */
    public void setColor(Color newColor) {
        setLEDColor(newColor);
        sendColor();
    }
    
    public Color getColor() {
        return getLEDColor();
    }
    
    /**
     *
     * @param value
     */
    public void setDimmer(int value) {
        setDimCur(value);
        sendDimmer();
    }
    
    /**
     *
     */
    public void dimmer() {
        colorDimmer.setVisible(true);
    }
    
    /**
     *
     */
    public void changer() {
        colorChange.setVisible(true);
    }
    
    public void hopper() {
        colorHop.setVisible(true);
    }
    
    /**
     *
     */
    public void sendColor() {
        //start Bit
        String colorString = "*";
        //first Color
        colorString += String.valueOf(getLEDColor().getRed()) + ","  + String.valueOf(getLEDColor().getGreen()) + "," + String.valueOf(getLEDColor().getBlue());
        //stop Bit
        colorString += "*";
        connection.writeToCommPort(colorString);
    }
    /**
     *
     */
    public void sendDimmer() {
        String colorString = "&";
        //Dimmer
        colorString += String.valueOf(getDimCur());
        //stop Bit
        colorString += "&";
        connection.writeToCommPort(colorString);
    }
    /**
     *
     */
    
    public void sendToEEPROM() {
        //start Bit
        String colorString = "#";
        connection.writeToCommPort(colorString);
    }
    
    /**
     * Get Data from the SerialPort
     * @param input
     */
    public void recieveColor(String input) {
        if (input.startsWith("&") && input.endsWith("&")) {
            String[] digits = input.substring(1, input.length() - 1).split(",");
            int red = Integer.parseInt(digits[0]);
            int green = Integer.parseInt(digits[1]);
            int blue = Integer.parseInt(digits[2]);
            int dim = Integer.parseInt(digits[3]);

            
            Color recievedColor = new Color(red, green, blue);
            setLEDColor(recievedColor);
            setDimCur(dim);
        }
    }
    /**
     * Exit the Program
     * @param i
     */
    public void exitProgram(int i) {
        sendToEEPROM();
        connection.disconnect();
        tray.remove(trayIcon);
        System.exit(i);
    }
    
    /**
     * Load IconImage from File
     * @param path
     * @param description
     * @return IconImage
     */
    private static Image createImage(String path, String description) {
        URL imageURL = LEDColorChooser.class.getResource(path);
        
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
    
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            
            
            
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ColorPick.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ColorPick.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ColorPick.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ColorPick.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        LEDColorChooser ledColorChooser = new LEDColorChooser();
    }

    /**
     * @return the dimMin
     */
    public int getDimMin() {
        return dimMin;
    }

    /**
     * @return the dimMax
     */
    public int getDimMax() {
        return dimMax;
    }

    /**
     * @return the dimCur
     */
    public int getDimCur() {
        return dimCur;
    }

    /**
     * @return the LEDColor
     */
    public Color getLEDColor() {
        return LEDColor;
    }

    /**
     * @param LEDColor the LEDColor to set
     */
    public void setLEDColor(Color LEDColor) {
        this.LEDColor = LEDColor;
    }

    /**
     * @param dimCur the dimCur to set
     */
    public void setDimCur(int dimCur) {
        this.dimCur = dimCur;
    }
    
}
