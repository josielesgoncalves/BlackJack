public class MainServer 
{
    static final int PORT = 5000;
    
    public static void main(String[] args) 
    {	
		AssassinoDeServidor assassino = new AssassinoDeServidor();
        assassino.start();
		
		Server servidor = new Server();
		servidor.start();
    }    
}
