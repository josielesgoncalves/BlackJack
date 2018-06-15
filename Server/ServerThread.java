import Principal.*;
import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    
    private Socket conexao;
    private Solicitacao resposta;
    
    public ServerThread(Socket cnx) throws Exception {
        if (cnx == null)
            throw new Exception("Conexao ausente");

        this.conexao = cnx;
    }
    
    public void run() {
        try {
	            
        	ObjectOutputStream _obj = new ObjectOutputStream(conexao.getOutputStream());
            ObjectInputStream obj = new ObjectInputStream(conexao.getInputStream());
            
            Solicitacao solicitacao = (Solicitacao) obj.readObject();
            
            String ipCliente = this.conexao.getInetAddress().getHostAddress();
    		System.out.println("Solicitacao " + solicitacao.getComando() + " de " + ipCliente);
    		
            Controlador ts = new Controlador(solicitacao);
            resposta = ts.getResposta(); 
            
            _obj.writeObject(resposta);
            _obj.flush();
            
            conexao.close();
            obj.close();
            _obj.close();
            
            

        }

        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}