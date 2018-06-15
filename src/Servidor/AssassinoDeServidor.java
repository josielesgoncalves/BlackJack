/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.*;

/**
 *
 * @author Josiele
 */
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
