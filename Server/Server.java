import java.net.*;
import Principal.*;
import Partida.*;
import Util.ListaEncadeada;

public class Server 
{	
	private int port;
	private Socket conexao;
	
	//TODO: guardar lista de cliente conectados atraves do email. Toda vez que quiser buscar informacao desse cliente, verificar pelo email dele
	public static ListaEncadeada<Cliente> JOGADORES = new ListaEncadeada<Cliente>();	 
	public static ListaEncadeada<Partida> PARTIDAS = new ListaEncadeada<Partida>();
	public static int ITERADOR = 0;
	
	public Server(int _port) { port = _port; }
		
	public void start()
	{
		try 
		{	if(ITERADOR == PARTIDAS.tamanho())
				ITERADOR = 0;
		
			@SuppressWarnings("resource")
			ServerSocket pedido = new ServerSocket(port);
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
