package View;

import Principal.*;
import javax.swing.*;

public class CadastroUsuarioView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
    public CadastroUsuarioView() {
    	initComponents();
        this.setLocationRelativeTo(null);        
    }

   private void initComponents() {
	   jLabel1 = new JLabel();
	   jLabel2 = new JLabel();
	   jLabel3 = new JLabel();
	   jLabel4 = new JLabel();
	   jLabel5 = new JLabel();
	   txtEmail = new JTextField();
	   txtNome = new JTextField();
	   txtSenha = new JPasswordField();
	   txtConfirmaSenha = new JPasswordField();
	   btnCadastrar = new JButton();
	   btnCancelar = new JButton();
	
	    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	
	    jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
	    jLabel1.setText("Cadastre-se para jogar!");
	
	    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
	    jLabel2.setText("Email:");
	
	    jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
	    jLabel3.setText("Senha:");
	
	    jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
	    jLabel4.setText("Nome:");
	
	    jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
	    jLabel5.setText("Confirmar senha:");
	
	    txtEmail.setToolTipText("");
	
	    btnCadastrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
	    btnCadastrar.setText("Cadastrar");
	    btnCadastrar.setPreferredSize(new java.awt.Dimension(71, 25));
	    btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            btnCadastrarActionPerformed(evt);
	        }
	    });
	
	    btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
	    btnCancelar.setText("Cancelar");
	    btnCancelar.setPreferredSize(new java.awt.Dimension(71, 25));
	    btnCancelar.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            btnCancelarActionPerformed(evt);
	        }
	    });
	
	    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setHorizontalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(layout.createSequentialGroup()
	                    .addContainerGap()
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                            .addComponent(jLabel3)
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                            .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                            .addComponent(jLabel2)
	                            .addGap(18, 18, 18)
	                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
	                .addGroup(layout.createSequentialGroup()
	                    .addGap(33, 33, 33)
	                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
	            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                .addGroup(layout.createSequentialGroup()
	                    .addComponent(jLabel4)
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGroup(layout.createSequentialGroup()
	                    .addComponent(jLabel5)
	                    .addGap(18, 18, 18)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(txtConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
	            .addContainerGap(23, Short.MAX_VALUE))
	        .addGroup(layout.createSequentialGroup()
	            .addContainerGap()
	            .addComponent(jLabel1)
	            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    );
	    layout.setVerticalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	            .addContainerGap()
	            .addComponent(jLabel1)
	            .addGap(22, 22, 22)
	            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                .addComponent(jLabel2)
	                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addComponent(jLabel4)
	                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	            .addGap(46, 46, 46)
	            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                .addComponent(jLabel3)
	                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addComponent(jLabel5)
	                .addComponent(txtConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	            .addGap(40, 40, 40)
	            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
	            .addContainerGap(23, Short.MAX_VALUE))
	    );
	
	    pack();
    }

    @SuppressWarnings("deprecation")
	private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) 
    {  
        try
        {
            String _email = txtEmail.getText();
            
            if(_email.matches("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"))
            {
            	String _nome = txtNome.getText();
                String _senha = "";
                
                if (txtSenha.getText().equals(txtConfirmaSenha.getText()))
                    _senha = txtSenha.getText();
                else
                {
                    JOptionPane.showMessageDialog(null, 
                                  "Conrfimar senha correta", 
                                  "Alerta", 
                                  JOptionPane.WARNING_MESSAGE);
                }
                
                if(_email.isEmpty() || _nome.isEmpty() || _senha.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, 
                                  "Verificar se todos os campos estao preenchidos", 
                                  "Alerta", 
                                  JOptionPane.WARNING_MESSAGE);
                }
                
                else
                {
                    Solicitacao solicitacao = new Solicitacao("CAD", _email, _nome, _senha);
                    cliente = new Cliente();
                    cliente.enviar(solicitacao);
                    
                    Solicitacao resposta = cliente.receber();
                    
                    JOptionPane.showMessageDialog(null, resposta, "Mensagem", JOptionPane.WARNING_MESSAGE);
                    
                    
                    if(resposta.getComando().equals("SUC"))
                    {
                    	JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso" , "Mensagem" ,JOptionPane.INFORMATION_MESSAGE);
                        String email = resposta.getComplemento1();
                        String nome = resposta.getComplemento2();
                        String moedas = resposta.getComplemento3();
                        
                        cliente = new Cliente(email, nome, _senha, Integer.valueOf(moedas));
                        
                        this.setVisible(false);
                    	new UsuarioView(cliente).setVisible(true);                	
                    }
                    else
                    {
                    	JOptionPane.showMessageDialog(null, resposta.getMessage(), "Mensagem" ,JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            else            
            	JOptionPane.showMessageDialog(null, "O formato do email esta incorreto", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception e) 
        {
                        JOptionPane.showMessageDialog(null, 
                              e.getMessage(), 
                              "Alerta", 
                              JOptionPane.WARNING_MESSAGE);
        }
    }                                            

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        txtNome.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        txtConfirmaSenha.setText("");

        int dialogResult = JOptionPane.showConfirmDialog(this, "Deseja cancelar o cadastro?", "Cancelar",
                        JOptionPane.YES_NO_OPTION);
        if (dialogResult == 0)
        {
            this.setVisible(false);
            new LoginUsuarioView().setVisible(true);

        }
    }//GEN-LAST:event_btnCancelarActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnCadastrar;
    private JButton btnCancelar;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JPasswordField txtConfirmaSenha;
    private JTextField txtEmail;
    private JTextField txtNome;
    private JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
