import java.net.*;
import Principal.*;
import Partida.*;
import Util.ListaEncadeada;

/**
* A classe recebe pedidos de conexoes enviadas pelo cliente para efetuar o tratamento das solicitações
* feitas por ele.
* Tambem controla as partidas que sao criadas pelo cliente, alem de ser responsavel pelo start
* da thread aberta
*/
public class Server 
{	
	
	private Socket conexao;
	
	public static ListaEncadeada<Cliente> JOGADORES = new ListaEncadeada<Cliente>();	 
	public static ListaEncadeada<Partida> PARTIDAS = new ListaEncadeada<Partida>();
	public static int ITERADOR_PARTIDAS = 0;
	
	public Server() {}
		
	public void start()
	{
		try 
		{
			for(int i = 0; i < PARTIDAS.tamanho(); i++)
				if(PARTIDAS.pega(i).getTotalJogadores() == 0)
					PARTIDAS.remove(PARTIDAS.pega(i));		
		
			@SuppressWarnings("resource")
			ServerSocket pedido = new ServerSocket(MainServer.PORT);
	        while(true) 
	        {
	            conexao = pedido.accept();
	            System.out.printf("Cliente conectado: %s%n", conexao.getInetAddress());
	            
	            new ServerThread(conexao).start();
	            
	        }		
		} catch (Exception e) {
			System.err.println("Problemas em subir o servidor");
		}
	}
}
