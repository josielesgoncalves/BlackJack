/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

public class Main 
{
    private static final int PORT = 5000;
    
    public static void main(String[] args) 
    {	
		AssassinoDeServidor assassino = new AssassinoDeServidor();
        assassino.start();
		
		Server servidor = new Server(PORT);
		servidor.start();
    }    
}
