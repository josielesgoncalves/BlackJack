package View;

import Cliente.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * A classe constroi a tela onde todas as partidas, iniciadas ou nao, serao listadas para o jogador.
 */

public class PartidasView extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;
	
    private String nomePartida;
    
    DefaultListModel<String> model;   
    private Cliente cliente;
    
    @SuppressWarnings("deprecation")
	public PartidasView(Cliente _cliente) {
    	cliente = _cliente;
        initComponents();        
        this.setLocationRelativeTo(null);
        
        
        model = new DefaultListModel<String>();
        
        String _nome = cliente.getNome();
        String _moedas = String.valueOf(cliente.getMoedas());
        
        lbInfoUsuario.setText("" + _nome + ", voce possui " + _moedas + " moedas");
        
        if(jListIniciadas.getModel().getSize() == 0)
            btnEntrar.enable(false);
        
        Solicitacao solicitacao = new Solicitacao("PAR", cliente.getEmail());
        cliente.enviar(solicitacao);        
        Solicitacao resposta = cliente.receber();
        
        while(resposta.getComando().equals("PAR"))
        {
        	String nomePartida = resposta.getComplemento1();
        	String statusPartida = resposta.getComplemento2();
        	
        	if(statusPartida.equals("INICIADA"))
        	{
        		model.addElement(nomePartida);
                jListIniciadas.setModel(model);
        	}
        	else
        	{
                model.addElement(nomePartida);
                jListEmEspera.setModel(model);  		
        	}
        	
        	solicitacao = new Solicitacao("PAR"); 
            
        	cliente.enviar(solicitacao);
            
            resposta = cliente.receber();
        }
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListIniciadas = new javax.swing.JList<>();
        btnEntrar = new javax.swing.JButton();
        btnCriar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        lbInfoUsuario = new javax.swing.JLabel();
        txtNomePartida = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListEmEspera = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jListIniciadas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jListIniciadas);

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        btnCriar.setText("Criar Partida");
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        lbInfoUsuario.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbInfoUsuario.setText("lbInfoUsuario");
        
        txtNomePartida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomePartidaKeyPressed(evt);
            }
        });

        jScrollPane2.setViewportView(jListEmEspera);

        jLabel1.setText("Partidas Iniciadas");

        jLabel2.setText("Partidas Em Espera");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbInfoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(btnCriar, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(txtNomePartida))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbInfoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEntrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCriar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomePartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnSair))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnCriarActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	nomePartida = txtNomePartida.getText();
    	
    	if(!nomePartida.isEmpty())
    	{	
            Solicitacao solicitacao = new Solicitacao("CRI", nomePartida);            
            cliente.enviar(solicitacao);            
            Solicitacao resposta = cliente.receber();
            
            if(resposta.getComando().equals("SUC"))
            {
        		String novaPartida = resposta.getMessage();
                model.addElement(novaPartida);
                jListEmEspera.setModel(model);
            	                
            }
            else
                JOptionPane.showMessageDialog(null, resposta.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
            
            txtNomePartida.setText(null);
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Insira um nome para uma nova partida", "Alerta", JOptionPane.WARNING_MESSAGE);
    	}         
    }                                        

    private void txtNomePartidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomePartidaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)        
            nomePartida = txtNomePartida.getText();
        
    }//GEN-LAST:event_txtNomePartidaKeyPressed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        if(jListEmEspera.getSelectedValue() != null)
        {	
            String partidaSelecionada = jListEmEspera.getSelectedValue();
            
            Solicitacao solicitacao = new Solicitacao("ENT", partidaSelecionada, cliente.getEmail());            
            cliente.enviar(solicitacao);
            
            Solicitacao resposta = cliente.receber();
            if(resposta.getComando().equals("SUC"))
            {   
                new PartidaView(partidaSelecionada, cliente).setVisible(true);
                this.setVisible(false);
            }
        }
    }

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {                                        
         int dialogResult = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Cancelar",
                        JOptionPane.YES_NO_OPTION);
        if (dialogResult == 0)
                this.setVisible(false); 
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriar;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jListEmEspera;
    private javax.swing.JList<String> jListIniciadas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbInfoUsuario;
    private javax.swing.JTextField txtNomePartida;
    // End of variables declaration//GEN-END:variables
    
}
