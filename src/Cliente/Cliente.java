 package Cliente;

import java.io.*;
import java.net.Socket;
import Jogo.Partida.Jogador;

public class Cliente extends Jogador {

	private Socket cliente;	
	private ObjectOutputStream transmissor;
	private ObjectInputStream receptor;
	
	private Solicitacao resposta;
	
	public Cliente(){}
	public Cliente(String email, String nome, String senha, int moedas) throws Exception {
        super(email, nome, senha, moedas);
    }
	
	public void enviar(Solicitacao solicitacao) // transmissor
	{	
		try 
		{	
			cliente = new Socket(Main.HOST, Main.PORT);
			
			transmissor = new ObjectOutputStream(cliente.getOutputStream());
			receptor = new ObjectInputStream(cliente.getInputStream());
			
			transmissor.writeObject(solicitacao);
			transmissor.flush();
			
			resposta = (Solicitacao) receptor.readObject();		
			System.out.println(resposta.getComando());
			
			desconectar();
			
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public Solicitacao receber() { return resposta;	}
	
	public void desconectar()
	{
		try {
			transmissor.close();
			receptor.close();
			cliente.close();			
		} catch (Exception e) { e.printStackTrace(); }
	}
	
}