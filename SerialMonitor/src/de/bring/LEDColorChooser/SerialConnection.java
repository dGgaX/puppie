/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bring.LEDColorChooser;

import de.bring.LEDColorChooser.popup.ColorPick;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.Event;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wohnzimmer
 */
public class SerialConnection {
    
    private String lastCommand = "";
    
    private CommPortIdentifier selectedPort = null;
    private SerialPort selectedSerialPort;
    private OutputStream output = null;
    private InputStream input = null;
    private HashSet<CommPortIdentifier> h =  getAvailableSerialPorts();
    private boolean connected = false;
    private LEDColorChooser parent;
    
    
    public SerialConnection(LEDColorChooser parent) {
        this.parent = parent;
        if (h.isEmpty()) {
            System.out.println("No Com-Ports");
            System.exit(0);
        }
        if (h.size() == 1) {
            selectCommPort(0);
        }
        
        
    }
    
    public void writeToCommPort(String command) {
        if (isConnected() && output != null && !command.equals(lastCommand)) {
            try {
                output.write(command.getBytes());
                //System.out.println("> " + command);
                lastCommand = command;
            } catch (IOException ex) {
                Logger.getLogger(SerialConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public CommPortIdentifier[] getAvailableCommPorts() {
        return (CommPortIdentifier[])h.toArray();
    }
    
    public void selectCommPort(int i) {
        selectedPort = (CommPortIdentifier)h.toArray()[i];
        System.out.println("> " + selectedPort.getName());
    }
    
    public boolean commPortSelected() {
        return (selectedPort != null);
    }
    
    public boolean isConnected() {
        return connected;
    }
    
    public void connect() {
        if ( selectedPort.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            try {
                CommPort commPort = selectedPort.open(this.getClass().getName(),2000);

                if ( commPort instanceof SerialPort )
                {
                    selectedSerialPort = (SerialPort) commPort;
                    selectedSerialPort.setSerialPortParams(115200,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                    
                    input = selectedSerialPort.getInputStream();
                    output = selectedSerialPort.getOutputStream();
                    
                    connected = true;
 
                    selectedSerialPort.addEventListener((SerialPortEvent spe) -> {
                        int data;
                        byte[] buffer = new byte[1024];
                        int len = 0;
                        try {
                            while ( ( data = input.read()) > -1 )
                            {
                                if ( data == '\n' || data == '\r' ) {
                                    break;
                                }
                                buffer[len++] = (byte) data;
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(SerialConnection.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String inpString = new String(buffer,0,len);
                        //System.out.println("< " + inpString);
                        parent.recieveColor(inpString);
                    });
                    selectedSerialPort.notifyOnDataAvailable(true);
                }
                else
                {
                    System.out.println("Error: Only serial ports are handled by this example.");
                }
            } catch (Exception ex) {
                Logger.getLogger(ColorPick.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     
    }
    
    public void disconnect() {
        if (selectedSerialPort != null) {
            try {
                output.close();
                output = null;
                connected = false;
            } catch (IOException ex) {
                Logger.getLogger(ColorPick.class.getName()).log(Level.SEVERE, null, ex);
            }
            selectedSerialPort.close();
        }
    }
    
    private static HashSet<CommPortIdentifier> getAvailableSerialPorts() {
        HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
        Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
        while (thePorts.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
            switch (com.getPortType()) {
            case CommPortIdentifier.PORT_SERIAL:
                try {
                    CommPort thePort = com.open("CommUtil", 50);
                    thePort.close();
                    h.add(com);
                } catch (PortInUseException e) {
                    System.out.println("Port, "  + com.getName() + ", is in use.");
                } catch (Exception e) {
                    System.err.println("Failed to open port " +  com.getName());
                    e.printStackTrace();
                }
            }
        }
        return h;
    }
}
