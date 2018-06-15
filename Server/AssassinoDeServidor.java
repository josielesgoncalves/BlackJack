import java.io.*;

public class AssassinoDeServidor extends Thread {
    
    public void run() {

        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        for (;;) 
        {
            System.out.print("Escreva SHUTDOWN para ");
            System.out.println("derrubar o servidor...");

            String comando = null;

            try 
            {
                comando = teclado.readLine();
            } catch (Exception erro) {}

            if (comando.toUpperCase().equals("SHUTDOWN"))
                System.exit(0);
            else
                System.err.println("Comando invalido!\n");
            }
    }
}
