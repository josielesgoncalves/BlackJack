package View;

import Principal.*;
import  javax.swing.*;
import java.awt.HeadlessException;
/**
 * Esta classe cria a tela onde ser� exibida a partida selecionada pelo jogador.
 */
public class PartidaView extends JFrame {
	private static final long serialVersionUID = 1L;
    
	private String partida;
    private String cartas;
    private Cliente cliente;
    
    public PartidaView(String _partida, Cliente _cliente) {
        
    	partida = _partida;
    	cliente = _cliente;
        
        initComponents();
        setVisibleButtons(false);
        this.setLocationRelativeTo(null);
    }
    
    
    private void initComponents() {

        btnIniciar = new JButton();
        btnComprar = new JButton();
        btnAbandonar = new JButton();
        btnPassar = new JButton();
        txtMoedas = new JTextField();
        btnApostar = new JButton();
        lbApostar = new JLabel();
        lbJogador1 = new JLabel();
        jSeparator1 = new JSeparator();
        jSeparator2 = new JSeparator();
        
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        setTitle("Partida");
        setBackground(new java.awt.Color(0, 153, 0));
        setSize(new java.awt.Dimension(600, 400));

        btnIniciar.setText("Iniciar partida");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnComprar.setText("Comprar carta");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        btnAbandonar.setText("Abandonar partida");
        btnAbandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbandonarActionPerformed(evt);
            }
        });

        btnPassar.setText("Parar de comprar");
        btnPassar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPassarActionPerformed(evt);
            }
        });

        btnApostar.setText("Apostar");
        btnApostar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApostarActionPerformed(evt);
            }
        });

        lbApostar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        
        lbJogador1.setText("");

        jSeparator1.setOrientation( SwingConstants.VERTICAL);

         GroupLayout layout = new  GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup( GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(lbApostar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2,  GroupLayout.PREFERRED_SIZE, 556,  GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(txtMoedas,  GroupLayout.PREFERRED_SIZE, 60,  GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApostar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbJogador1,  GroupLayout.PREFERRED_SIZE, 524,  GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1,  GroupLayout.PREFERRED_SIZE, 10,  GroupLayout.PREFERRED_SIZE)
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                    .addComponent(btnComprar,  GroupLayout.PREFERRED_SIZE, 145,  GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciar,  GroupLayout.PREFERRED_SIZE, 145,  GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbandonar,  GroupLayout.PREFERRED_SIZE, 145,  GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPassar,  GroupLayout.PREFERRED_SIZE, 145,  GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup( GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnIniciar)
                .addGap(65, 65, 65)
                .addComponent(btnComprar)
                .addGap(59, 59, 59)
                .addComponent(btnPassar)
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btnAbandonar)
                .addGap(91, 91, 91))
            .addGroup( GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbApostar,  GroupLayout.PREFERRED_SIZE, 52,  GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator2,  GroupLayout.PREFERRED_SIZE, 9,  GroupLayout.PREFERRED_SIZE)
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApostar)
                    .addComponent(txtMoedas,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(lbJogador1,  GroupLayout.PREFERRED_SIZE, 218,  GroupLayout.PREFERRED_SIZE)
                .addContainerGap( GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );

        pack();
    }

    /**
     * O metodo define a visibilidade dos botoes
     */
    private void setVisibleButtons(boolean visible)
    {
    	btnIniciar.setVisible(visible);
        btnComprar.setVisible(visible);
        btnPassar.setVisible(visible);
    	btnPassar.setVisible(visible);    	
    	btnAbandonar.setVisible(visible);
    	lbJogador1.setVisible(visible);
        
        lbApostar.setVisible(!visible);
        txtMoedas.setVisible(!visible);
        btnApostar.setVisible(!visible);
        
        
        if(!visible)
        	lbApostar.setText("Quantas moedas voce quer apostar?");               
        else
        	lbApostar.setText("Partida " + partida);  
    }
    
    /**
     * O m�todo define a habilita��o dos bot�es
     */
    private void setEnabledButtons(boolean enable)
    {	
    	btnComprar.setEnabled(enable);
        btnPassar.setEnabled(enable);
    	btnPassar.setEnabled(enable);    	
    	btnAbandonar.setEnabled(enable);
    	
    }
    
    private void btnApostarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApostarActionPerformed
        try {
            if(!txtMoedas.getText().isEmpty()){
             
            	String moedasApostadas = txtMoedas.getText();            	
            	Integer.parseInt(moedasApostadas);
				                
                Solicitacao solicitacao = new Solicitacao("APO", moedasApostadas, cliente.getEmail());                
                cliente.enviar(solicitacao);

                Solicitacao resposta = cliente.receber();
                if(resposta.getComando().equals("SUC"))
                {   
                    JOptionPane.showMessageDialog(null, "Voce possui " + resposta.getMessage() + 
                                                  " moedas" , "Mensagem" , JOptionPane.INFORMATION_MESSAGE);
                    
                    setVisibleButtons(true);
                    setEnabledButtons(false);
                }
                
                else
                 JOptionPane.showMessageDialog(null, "Voce nao possui saldo para jogar" , "Mensagem" ,
                                             JOptionPane.INFORMATION_MESSAGE);
              
            }
            
            else
                JOptionPane.showMessageDialog(null, "O campo de aposta nao pode estar vazio" , "Mensagem" ,
						JOptionPane.INFORMATION_MESSAGE);
            
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor com formato invalido" , "Alerta" ,
						JOptionPane.INFORMATION_MESSAGE);
        }
        
            
    }

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
        Solicitacao solicitacao = new Solicitacao("INI", cliente.getEmail());
        
        cliente.enviar(solicitacao);        
        Solicitacao resposta = cliente.receber();
        
        if(resposta.getComando().equals("SUC"))
        {
        	JOptionPane.showMessageDialog(null, resposta.getMessage() , "Mensagem" ,
					JOptionPane.INFORMATION_MESSAGE);
        	setEnabledButtons(true);
        	
        	solicitacao = new Solicitacao("COM", cliente.getEmail());            
        	cliente.enviar(solicitacao);
                
            resposta = cliente.receber();
            if(resposta.getComando().equals("CAR"))
            {
            	cartas = resposta.getComplemento1() + "<br>";
            	String pontos =  resposta.getComplemento2();
            	             	
                solicitacao = new Solicitacao("COM", cliente.getEmail());                
                cliente.enviar(solicitacao);
                
                resposta = cliente.receber();
                if(resposta.getComando().equals("CAR"))
                {
                	cartas += " " + resposta.getComplemento1()  + "<br>";
                	pontos =  resposta.getComplemento2();
                	                	
                	lbJogador1.setText("<html>Pontos: "+ pontos + "<br>" + cartas +"</html>");          
                	System.out.println("Voce possui: " + cartas);
                }
                
                JOptionPane.showMessageDialog(null, "Voce possui duas cartas" , "Mensagem" ,
						JOptionPane.INFORMATION_MESSAGE);            
            }            
        }
        else
        	JOptionPane.showMessageDialog(null, resposta.getMessage() , "Mensagem" ,
					JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {                                           
         
        Solicitacao solicitacao = new Solicitacao("COM", cliente.getEmail());            
        cliente.enviar(solicitacao);

        Solicitacao resposta = cliente.receber(); 
        if(resposta.getComando().equals("CAR"))
        {        	
        	cartas += " " + resposta.getComplemento1()  + "<br>";
        	String pontos =  resposta.getComplemento2();
        	        	
        	lbJogador1.setText("<html>Pontos: "+ pontos + "<br>" + cartas +"</html>");
        	System.out.println("Voce possui: " + cartas);
        }
        
    }

    private void btnPassarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
        Solicitacao solicitacao = new Solicitacao("EOC", cliente.getEmail());        
        cliente.enviar(solicitacao);
        Solicitacao resposta = cliente.receber();
        
        System.out.println(resposta.getMessage());
        
        if(resposta.getComando().equals("ERR"))
        	JOptionPane.showMessageDialog(null, resposta.getMessage() + " Clique novamente no botao para verificar se a partida foi finalizada" , "Mensagem" ,
					JOptionPane.INFORMATION_MESSAGE);
        else
        {
        	if(resposta.getComando().equals("WIN"))
        	{
        		String vencedor = "Vencedor: Nome: " + resposta.getComplemento1() + ", Email: " + resposta.getComplemento2();
        		JOptionPane.showMessageDialog(null, vencedor, "Mensagem" ,
    					JOptionPane.INFORMATION_MESSAGE);
        	}
        	else
        		JOptionPane.showMessageDialog(null, "Saldo atualizado: " + resposta.getMessage(), "Mensagem" ,
    					JOptionPane.INFORMATION_MESSAGE);
        		
        	
        }
    }

    private void btnAbandonarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        int dialogResult = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Cancelar",
                        JOptionPane.YES_NO_OPTION);
        if (dialogResult == 0)
        {
        	Solicitacao solicitacao = new Solicitacao("SAI", cliente.getEmail());
        	cliente.enviar(solicitacao);

        	this.setVisible(false);
        }                 
    }  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private  JButton btnAbandonar;
    private  JButton btnApostar;
    private  JButton btnComprar;
    private  JButton btnIniciar;
    private  JButton btnPassar;
    private  JSeparator jSeparator1;
    private  JSeparator jSeparator2;
    private  JLabel lbApostar;
    private  JLabel lbJogador1;
    private  JTextField txtMoedas;
    // End of variables declaration//GEN-END:variables
}
