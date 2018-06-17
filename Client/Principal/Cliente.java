 package Principal;

import java.io.*;
import java.net.Socket;
import Partida.Jogador;

public class Cliente extends Jogador {
	
	private Solicitacao resposta;
	
	public Cliente(){}
	public Cliente(String email, String nome, String senha, int moedas) throws Exception {
        super(email, nome, senha, moedas);
    }
	/**
	 * O método permite que o cliente envie um pedido de conexão para o servidor 
	 * através da criação do socket cliente. 
	 * Ao receber a resposta do servidor, ela é enviada para a view e informar o 
	 * usuário.    
	 */
	public void enviar(Solicitacao solicitacao)
	{	
		try 
		{	
			Socket cliente = new Socket(MainClient.HOST, MainClient.PORT);
			
			ObjectOutputStream transmissor = new ObjectOutputStream(cliente.getOutputStream());
			ObjectInputStream receptor = new ObjectInputStream(cliente.getInputStream());
			
			transmissor.writeObject(solicitacao);
			transmissor.flush();
			
			resposta = (Solicitacao) receptor.readObject();		
			System.out.println(resposta.getComando());
			
			transmissor.close();
			receptor.close();
			cliente.close();
			
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	/**
	 * @return Solicitacao
	 * O método retorna o tipo de resposta que o seridor enviou ao cliente.     
	 */
	public Solicitacao receber() { return resposta;	}
		
}