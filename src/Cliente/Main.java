package Cliente;

import View.*;

public class Main 
{		
	public static final int PORT = 5000;
	public static String HOST;
	
	
	public static void main(String[] args) throws Exception {
		new IPServidorView().setVisible(true);        		
	}
}
