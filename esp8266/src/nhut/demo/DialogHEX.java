/* jSSC-Terminal - serial port terminal.
 * © Alexey Sokolov (scream3r), 2011.
 *
 * This file is part of jSSC-Terminal.
 *
 * jSSC-Terminal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jSSC-Terminal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * e-mail: scream3r.org@gmail.com
 * web-site: www.scream3r.org
 */
package nhut.demo;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import jssc.SerialPort;

/**
 *
 * @author scream3r
 */
public class DialogHEX extends javax.swing.JPanel {

    private Form parent;
    private SerialPort serialPort;

    /** Creates new form Dialog */
    public DialogHEX(Form parent, SerialPort serialPort) {
        this.parent = parent;
        this.serialPort = serialPort;
        initComponents();
        setSize(412, 131);
    }

    private void close() {
        Main.getApplet().getGlassPane().setVisible(false);
        parent.setControlsFocusable(true);
    }

    private void send() {
        String str = jTextFieldHEX.getText();
        if(str.length() > 0){
            String[] strArray = str.split(" ");
            int[] intArray = new int[strArray.length];
            try {
                for(int i = 0; i < strArray.length; i++){
                    intArray[i] = Integer.valueOf(strArray[i], 16);
                }
                byte[] buffer = new byte[intArray.length];
                for(int i = 0; i < intArray.length; i++){
                    buffer[i] = (byte)intArray[i];
                }
                if(buffer.length > 0){
                    try {
                        serialPort.writeBytes(buffer);
                    }
                    catch (Exception ex) {
                        //Message
                    }
                    finally {
                        close();
                    }
                }
            }
            catch (NumberFormatException ex) {
                jLabelMessage.setText("HEX string not correct");
            }
        }
        else {
            jLabelMessage.setText("HEX string is empty");
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jHeaderPanelHEX = new javax.swing.JPanel(){

            protected void paintComponent(Graphics g){
                GradientPaint paint = new GradientPaint(0, 0, NimbusGui.INFO_PANEL_TOP_COLOR, 0, getHeight(), NimbusGui.INFO_PANEL_BOTTOM_COLOR);
                Graphics2D graphics2D = (Graphics2D)g.create();
                graphics2D.setPaint(paint);
                graphics2D.fillRect(0, 0, getWidth(), getHeight());
            }
        }
        ;
        jHeaderLabelHEX = new javax.swing.JLabel();
        jDataPanel = new javax.swing.JPanel();
        jLabelNote = new javax.swing.JLabel();
        jTextFieldHEX = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelMessage = new javax.swing.JLabel();
        jButtonCancel = new javax.swing.JButton();
        jButtonSend = new javax.swing.JButton();

        jHeaderPanelHEX.setBorder(NimbusGui.DIALOG_PANEL_BORDER);
        jHeaderPanelHEX.setPreferredSize(new java.awt.Dimension(286, 30));

        jHeaderLabelHEX.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jHeaderLabelHEX.setForeground(NimbusGui.SECTION_LABEL_FONT_COLOR);
        jHeaderLabelHEX.setText("Send HEX");

        javax.swing.GroupLayout jHeaderPanelHEXLayout = new javax.swing.GroupLayout(jHeaderPanelHEX);
        jHeaderPanelHEX.setLayout(jHeaderPanelHEXLayout);
        jHeaderPanelHEXLayout.setHorizontalGroup(
            jHeaderPanelHEXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHeaderPanelHEXLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jHeaderLabelHEX)
                .addContainerGap(343, Short.MAX_VALUE))
        );
        jHeaderPanelHEXLayout.setVerticalGroup(
            jHeaderPanelHEXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jHeaderLabelHEX, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jDataPanel.setBackground(new java.awt.Color(231, 233, 237));
        jDataPanel.setBorder(NimbusGui.DIALOG_PANEL_BORDER);
        jDataPanel.setPreferredSize(new java.awt.Dimension(276, 75));

        jLabelNote.setFont(NimbusGui.DEFAULT_FONT);
        jLabelNote.setText("Write a HEX string, space is a separator (Example: FF OD OA).");

        jTextFieldHEX.setFont(NimbusGui.DEFAULT_FONT);
        jTextFieldHEX.setPreferredSize(new java.awt.Dimension(59, 28));
        jTextFieldHEX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHEXActionPerformed(evt);
            }
        });
        jTextFieldHEX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldHEXKeyPressed(evt);
            }
        });

        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 2));

        jLabelMessage.setFont(NimbusGui.DEFAULT_FONT);
        jLabelMessage.setForeground(new java.awt.Color(204, 0, 51));

        jButtonCancel.setFont(NimbusGui.DEFAULT_FONT);
        jButtonCancel.setText("Cancel");
        jButtonCancel.setPreferredSize(new java.awt.Dimension(90, 28));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        jButtonCancel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonCancelKeyPressed(evt);
            }
        });

        jButtonSend.setFont(NimbusGui.DEFAULT_FONT);
        jButtonSend.setText("Send");
        jButtonSend.setPreferredSize(new java.awt.Dimension(90, 28));
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });
        jButtonSend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonSendKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jDataPanelLayout = new javax.swing.GroupLayout(jDataPanel);
        jDataPanel.setLayout(jDataPanelLayout);
        jDataPanelLayout.setHorizontalGroup(
            jDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDataPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldHEX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(jLabelNote, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addGap(6, 6, 6))
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDataPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(jButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jDataPanelLayout.setVerticalGroup(
            jDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDataPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelNote)
                .addGap(3, 3, 3)
                .addComponent(jTextFieldHEX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMessage))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHeaderPanelHEX, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 131, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jHeaderPanelHEX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        close();
}//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        send();
}//GEN-LAST:event_jButtonSendActionPerformed

    private void jTextFieldHEXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHEXActionPerformed
        send();
    }//GEN-LAST:event_jTextFieldHEXActionPerformed

    private void jTextFieldHEXKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldHEXKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            close();
        }
    }//GEN-LAST:event_jTextFieldHEXKeyPressed

    private void jButtonSendKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonSendKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            close();
        }
    }//GEN-LAST:event_jButtonSendKeyPressed

    private void jButtonCancelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonCancelKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            close();
        }
    }//GEN-LAST:event_jButtonCancelKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JPanel jDataPanel;
    static javax.swing.JLabel jHeaderLabelHEX;
    private javax.swing.JPanel jHeaderPanelHEX;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JLabel jLabelNote;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldHEX;
    // End of variables declaration//GEN-END:variables

}
