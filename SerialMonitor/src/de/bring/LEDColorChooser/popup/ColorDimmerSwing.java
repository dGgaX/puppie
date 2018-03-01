/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bring.LEDColorChooser.popup;

import de.bring.LEDColorChooser.LEDColorChooser;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wohnzimmer
 */
public class ColorDimmerSwing extends PopupJFrame {
    private boolean started = false;
    private boolean changeable = false;
    Thread thread = new Thread(){
        public void run() {
            boolean down = true;
            int subDim = parent.getDimCur();
            while(true) {
                while(started) {
                    
                    int wait = jSlider3.getValue() / (2 * (jSlider1.getValue() - jSlider2.getValue()));
                    try {
                        parent.setDimmer(subDim);
                        if (down) {
                            subDim--;
                            if (subDim < jSlider2.getValue()) {
                                down = false;
                            }
                        } else {
                            subDim++;
                            if (subDim > jSlider1.getValue()) {
                                down = true;
                            }
                        }
                        sleep(wait);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ColorDimmerSwing.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                while(!started) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ColorDimmerSwing.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    };

    /**
     * Creates new form ColorDimmer
     * @param parent
     */
    public ColorDimmerSwing(LEDColorChooser parent) {
        super(parent);
            
        initComponents();
        setFrameLocation(this);

        thread.start();
        
        jSlider1.setMaximum(parent.getDimMax());
        jSlider1.setMinimum(parent.getDimMin());
        jSlider1.setValue(parent.getDimCur());
        doLabel();
    }

    private void doLabel() {
        jLabel1.setText(String.valueOf(Math.round((float)jSlider1.getValue()*100.0f/(float)jSlider1.getMaximum()))+"%");
        jLabel2.setText(String.valueOf(Math.round((float)jSlider2.getValue()*100.0f/(float)jSlider1.getMaximum()))+"%");
        jLabel3.setText(String.valueOf(jSlider3.getValue()) + "ms");
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jSlider2 = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
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

        jSlider1.setBackground(java.awt.SystemColor.window);
        jSlider1.setForeground(java.awt.SystemColor.windowText);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel1.setBackground(java.awt.SystemColor.window);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setLabelFor(jSlider1);
        jLabel1.setText("jLabel1");

        jSlider2.setBackground(java.awt.SystemColor.window);
        jSlider2.setForeground(java.awt.SystemColor.windowText);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jLabel2.setBackground(java.awt.SystemColor.window);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setLabelFor(jSlider1);
        jLabel2.setText("jLabel1");

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
        jLabel3.setLabelFor(jSlider1);
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
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jSlider3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        if (this.isVisible() && changeable) {
            parent.setDimmer(jSlider1.getValue());
            float relMinDim = (float)jSlider2.getValue()/(float)jSlider2.getMaximum();
            jSlider2.setMaximum(jSlider1.getValue());
            jSlider2.setValue(Math.round(jSlider1.getValue() * relMinDim));
            doLabel();
        }
    }//GEN-LAST:event_jSlider1StateChanged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jSlider1.setMaximum(parent.getDimMax());
        jSlider1.setMinimum(parent.getDimMin());
        jSlider1.setValue(parent.getDimCur());
        doLabel();
        changeable = true;
    }//GEN-LAST:event_formWindowOpened

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
        if (this.isVisible() && changeable) {
            doLabel();
        }
    }//GEN-LAST:event_jSlider2StateChanged

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider3;
    // End of variables declaration//GEN-END:variables
}
