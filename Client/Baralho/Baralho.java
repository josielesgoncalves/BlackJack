package Baralho;

import java.io.Serializable;
import java.util.*;

/**
* A classe constroi um baralho de 4 naipes e 13 valores, totalizando 52 cartas.
*/
@SuppressWarnings("serial")
public class Baralho implements Serializable
{		
	private List<Carta> cartas;
	private Naipe naipes[];
	private Valor valores[];
	
	private int topo; //Numero de cartas na mesa	
	
	/**
	* Inicializa um baralho de 52 cartas com 4 naipes e 13 valores para cada naipe
	*/
	public Baralho()
	{
		topo = 0;
		cartas = new ArrayList<Carta>();
		naipes = Naipe.values();
		valores = Valor.values();
		
		for(int i = 0; i < naipes.length; i++)
			for(int j = 0; j < valores.length; j++)
			{
				Carta carta = new Carta(naipes[i], valores[j]);
				cartas.add(carta);
				topo++;
			}	
		
		Collections.shuffle(cartas);
	}
	/**
	* @return Carta
	* O metodo pega a carta que estao no topo da pilha de baralho
	*/
	public Carta comprarCarta() 
	{
		topo--;
		return cartas.remove(topo);	
	}
	/**
	 * @return int
	 * Retorna o numero de cartas que ainda estao na mesa
	 */
	public int topo() { return topo; }
	

	/**
	 * @return bool
	 * Verifica se a pilha de baralho esta vazia 
	 */
	public boolean baralhoVazio(){return cartas.isEmpty();}
	
	/**
	 * Este metodo zera a mesa, ou seja, todas as cartas da mesa voltam para o baralho.
	 */
    public void resetar(){ topo = 0; }

}
