/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bring.LEDColorChooser.popup;

import de.bring.LEDColorChooser.LEDColorChooser;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wohnzimmer
 */
public class ColorChange extends PopupJFrame {
    private boolean started = false;
    private boolean changeable = false;
    Thread thread = new Thread(){
        public void run() {
            Color oldColor = parent.getColor();
            Color newColor = createColor();
            float counter = 0;
            while(true) {
                while(started) {
                    long wait = jSlider3.getValue() / 255;
                    try {
                        counter++;
                        if (counter >= 255) {
                            oldColor = newColor;
                            newColor = createColor();
                            counter = 0;
                        }
                        int currRed = oldColor.getRed() + (int) ((newColor.getRed() - oldColor.getRed()) * ((float) counter / 255.0f));
                        int currGreen = oldColor.getGreen() + (int) ((newColor.getGreen() - oldColor.getGreen()) * ((float) counter / 255.0f));
                        int currBlue = oldColor.getBlue() + (int) ((newColor.getBlue() - oldColor.getBlue()) * ((float) counter / 255.0f));
                        Color currentColor = new Color(currRed, currGreen, currBlue);
                        parent.setColor(currentColor);
                        sleep(wait);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ColorChange.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                while(!started) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ColorChange.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        private Color createColor() {
            int red = (int) Math.round(Math.random() * 255.0d);
            int green = (int) Math.round(Math.random() * 255.0d);
            int blue = (int) Math.round(Math.random() * 255.0d);
            return new Color(red, green, blue);
        }
    };

    /**
     * Creates new form ColorDimmer
     * @param parent
     */
    public ColorChange(LEDColorChooser parent) {
        super(parent);
            
        initComponents();
        setFrameLocation(this);

        thread.start();
        
        doLabel();
    }

    private void doLabel() {
        jLabel3.setText(String.valueOf(jSlider3.getValue()) + "ms");
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider3 = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setTitle("Dimmer");
        setName(""); // NOI18N
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jSlider3.setBackground(java.awt.SystemColor.window);
        jSlider3.setForeground(java.awt.SystemColor.windowText);
        jSlider3.setMaximum(10000);
        jSlider3.setValue(500);
        jSlider3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider3StateChanged(evt);
            }
        });

        jLabel3.setBackground(java.awt.SystemColor.window);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("jLabel1");

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jSlider3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        doLabel();
        changeable = true;
    }//GEN-LAST:event_formWindowOpened

    private void jSlider3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider3StateChanged
        if (this.isVisible() && changeable) {
            doLabel();
        }
    }//GEN-LAST:event_jSlider3StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        started = !started;
        if (started) {
            jButton1.setText("Stop");
        } else {
            jButton1.setText("Start");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        changeable = false;
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSlider jSlider3;
    // End of variables declaration//GEN-END:variables
}
