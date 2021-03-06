/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaronps.irblastertest;

import gnu.io.NRSerialPort;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author krom
 */
public class IRTest extends javax.swing.JFrame
{
    
    NRSerialPort mSerialPort;
    IRYofeng mIRModule;

    /**
     * Creates new form IRTest
     */
    public IRTest()
    {
        initComponents();
        
        Set<String> ports = NRSerialPort.getAvailableSerialPorts();
        if ( ports.isEmpty() )
        {
            JOptionPane.showMessageDialog(null, "There are no serial ports!", "IRTest Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        
        for ( String sr: ports)
        {
            mPortsCombo.addItem(sr);
        }
        
        mPortsCombo.setSelectedIndex(0);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        mPortsCombo = new javax.swing.JComboBox<>();
        mConnectButton = new javax.swing.JButton();
        enterLearnModeButton = new javax.swing.JButton();
        exitLearnButton = new javax.swing.JButton();
        code40Button = new javax.swing.JButton();
        code41Button = new javax.swing.JButton();
        read40Button = new javax.swing.JButton();
        read41Button = new javax.swing.JButton();
        readBothButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IRBlaster test");

        jLabel1.setText("Ports");

        mConnectButton.setText("Connect");
        mConnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mConnectButtonActionPerformed(evt);
            }
        });

        enterLearnModeButton.setText("Enter Learn");
        enterLearnModeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterLearnModeButtonActionPerformed(evt);
            }
        });

        exitLearnButton.setText("Exit Learn");
        exitLearnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitLearnButtonActionPerformed(evt);
            }
        });

        code40Button.setText("40");
        code40Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                code40ButtonActionPerformed(evt);
            }
        });

        code41Button.setText("41");
        code41Button.setToolTipText("");
        code41Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                code41ButtonActionPerformed(evt);
            }
        });

        read40Button.setText("Read 40");
        read40Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                read40ButtonActionPerformed(evt);
            }
        });

        read41Button.setText("Read 41");
        read41Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                read41ButtonActionPerformed(evt);
            }
        });

        readBothButton.setText("Read both");
        readBothButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readBothButtonActionPerformed(evt);
            }
        });

        logArea.setColumns(20);
        logArea.setRows(5);
        jScrollPane1.setViewportView(logArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mPortsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mConnectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(readBothButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(read40Button))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enterLearnModeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitLearnButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(code40Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(code41Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(read41Button)))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mPortsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mConnectButton)
                    .addComponent(read40Button)
                    .addComponent(readBothButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterLearnModeButton)
                    .addComponent(exitLearnButton)
                    .addComponent(code40Button)
                    .addComponent(code41Button)
                    .addComponent(read41Button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mConnectButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mConnectButtonActionPerformed
    {//GEN-HEADEREND:event_mConnectButtonActionPerformed
        final String selectedPort = (String)mPortsCombo.getSelectedItem();
//        JOptionPane.showMessageDialog(this, "The selected port was " + selectedPort);
        mConnectButton.setEnabled(false);
        
        mSerialPort = new NRSerialPort(selectedPort, 9600);
        // @note by default it will connect at 8-N-1 which is what we need.
        mSerialPort.connect();
//        mSerialPort.getSerialPortInstance().enableReceiveTimeout(5000);
        
        mIRModule = new IRYofeng(mSerialPort.getSerialPortInstance());
        logArea.append("Connected to " + selectedPort + "\n");
        
//        mSerialPort.addEventListener(lsnr);
//        mSerialPort.getInputStream()

        // @todo create a class that will send and receive commands blockingly
        // for example: ir.request_simple(byte request, byte expected);
        // ir.request_block(byte request, byte[] full buffer)
        // ir.send_block(byte[] block)
        
        // @todo there could be a "server" like in erlang which will handle the operations.. this server shall be a thread.
        
        
    }//GEN-LAST:event_mConnectButtonActionPerformed

    private void read40ButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_read40ButtonActionPerformed
    {//GEN-HEADEREND:event_read40ButtonActionPerformed
        final byte[] buffer = new byte[256];
        try
        {
            if ( mIRModule.readCustom(0x40, 1, buffer) )            
            {
                StringBuilder sb = new StringBuilder(512);
                for ( byte b: buffer )
                {
                    sb.append(String.format("%02x", b&0xff));
                }
                
                logArea.append("0x40: " + sb.toString() + "\n");
            }
            else
            {
                logArea.append("Error reading code 40\n");
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_read40ButtonActionPerformed

    private void read41ButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_read41ButtonActionPerformed
    {//GEN-HEADEREND:event_read41ButtonActionPerformed
        final byte[] buffer = new byte[256];
        try
        {
            if ( mIRModule.readCustom(0x41, 1, buffer) )            
            {
                StringBuilder sb = new StringBuilder(512);
                for ( byte b: buffer )
                {
                    sb.append(String.format("%02x", b&0xff));
                }
                
                logArea.append("0x41: " + sb.toString() + "\n");
            }
            else
            {
                logArea.append("Error reading code 40\n");
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_read41ButtonActionPerformed

    private void readBothButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_readBothButtonActionPerformed
    {//GEN-HEADEREND:event_readBothButtonActionPerformed
        final byte[] buffer = new byte[512];
        try
        {
            if ( mIRModule.readCustom(0x40, 2, buffer) )            
            {
                StringBuilder sb = new StringBuilder(512);
                
                for (int i = 0; i < 256; i++ )
                {
                    final byte b = buffer[i];
                    sb.append(String.format("%02x", b&0xff));
                }
                
                logArea.append("0x40: " + sb.toString() + "\n");
                
                sb = new StringBuilder(512);
                
                for (int i = 256; i < 512; i++ )
                {
                    final byte b = buffer[i];
                    sb.append(String.format("%02x", b&0xff));
                }
                
                logArea.append("0x41: " + sb.toString() + "\n");
                
            }
            else
            {
                logArea.append("Error reading both codes\n");
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_readBothButtonActionPerformed

    private void code40ButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_code40ButtonActionPerformed
    {//GEN-HEADEREND:event_code40ButtonActionPerformed
        try
        {
            if ( mIRModule.sendSignal(0x40) )
            {
                if ( mIRModule.isLearning() )
                {
                    logArea.append("Learn code 40 successfull\n");
                }
                else
                {
                    logArea.append("Send signal 40 successfull\n");
                }
            }
            else
            {
                if ( mIRModule.isLearning() )
                {
                    logArea.append("Learn code 40 ERROR\n");
                }
                else
                {
                    logArea.append("Send signal 40 ERROR\n");
                }
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (TimeoutException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_code40ButtonActionPerformed

    private void code41ButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_code41ButtonActionPerformed
    {//GEN-HEADEREND:event_code41ButtonActionPerformed
        try
        {
            if ( mIRModule.sendSignal(0x41) )
            {
                if ( mIRModule.isLearning() )
                {
                    logArea.append("Learn code 41 successfull\n");
                }
                else
                {
                    logArea.append("Send signal 41 successfull\n");
                }
            }
            else
            {
                if ( mIRModule.isLearning() )
                {
                    logArea.append("Learn code 41 ERROR\n");
                }
                else
                {
                    logArea.append("Send signal 41 ERROR\n");
                }
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (TimeoutException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_code41ButtonActionPerformed

    private void enterLearnModeButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_enterLearnModeButtonActionPerformed
    {//GEN-HEADEREND:event_enterLearnModeButtonActionPerformed
        try
        {
            if ( mIRModule.enterLearnMode() )
            {
                logArea.append("Enter learning mode success\n");
            }
            else
            {
                logArea.append("Enter learning mode ERROR\n");
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (TimeoutException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_enterLearnModeButtonActionPerformed

    private void exitLearnButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_exitLearnButtonActionPerformed
    {//GEN-HEADEREND:event_exitLearnButtonActionPerformed
        try
        {
            if ( mIRModule.exitLearnMode())
            {
                logArea.append("Exit learning mode success\n");
            }
            else
            {
                logArea.append("Exit learning mode ERROR\n");
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (TimeoutException ex)
        {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exitLearnButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(IRTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(IRTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(IRTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(IRTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                final IRTest it = new IRTest();
                it.setLocationRelativeTo(null);
                it.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton code40Button;
    private javax.swing.JButton code41Button;
    private javax.swing.JButton enterLearnModeButton;
    private javax.swing.JButton exitLearnButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logArea;
    private javax.swing.JButton mConnectButton;
    private javax.swing.JComboBox<String> mPortsCombo;
    private javax.swing.JButton read40Button;
    private javax.swing.JButton read41Button;
    private javax.swing.JButton readBothButton;
    // End of variables declaration//GEN-END:variables
}
