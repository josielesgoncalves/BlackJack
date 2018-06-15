package Jogo.Partida;

import Jogo.Baralho.*;
import DataBase.dbo.Usuario;
import java.util.*;

/**
 * A classe jogador define os dados que ele precisa para jogar uma partida
 */

public class Jogador extends Usuario
{
	private List<Carta> cartas;
    private int pontuacao;    
    private boolean parouJogar;
    private Partida partida;
    
	
	/**
     * O construtor herda da classe usuario dados que um jogador precisa
     */
    public Jogador(){}
    
    public Jogador(String email, String nome, String senha, int moedas) throws Exception {
        super(email, nome, senha, moedas);
    }
    
    public List<Carta> getCartas() { return cartas;	}
    public void setCarta(Carta carta) {
        cartas = new ArrayList<Carta>();
        cartas.add(carta);
    }
    
    public int getPontuacao() { return pontuacao; }
    public void setPontuacao(int pontuacao) { this.pontuacao = pontuacao; }
    
    public boolean isParouJogar() { return parouJogar; }
    public void setParouJogar(boolean parouJogar) { this.parouJogar = parouJogar; }
    
    public Partida getPartida() { return partida; }
	public void setPartida(Partida partida) { this.partida = partida; }

 
}
