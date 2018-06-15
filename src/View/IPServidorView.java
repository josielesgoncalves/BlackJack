package View;

import javax.swing.JOptionPane;
import Cliente.*;

public class IPServidorView extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	
    public IPServidorView() {
        initComponents();
        this.setLocationRelativeTo(null);        
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtIPServidor = new javax.swing.JTextField();
        btnConectar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Servidor:");

        btnConectar.setText("Conectar!");
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(txtIPServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConectar)
                .addGap(154, 154, 154))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIPServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnConectar)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        try 
        {
            if(!txtIPServidor.getText().equals("") || txtIPServidor.getText() != null)
            {
                    Main.HOST = txtIPServidor.getText();
                    
                    System.out.println("Cliente conectado");
            		
                    new LoginUsuarioView().setVisible(true);
                    this.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, 
                              "Verificar se o servidor esta preenchido!", 
                              "Alerta", 
                              JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, 
                              e.getMessage(), 
                              "Alerta", 
                              JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnConectarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConectar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtIPServidor;
    // End of variables declaration//GEN-END:variables
}
