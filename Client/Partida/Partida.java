package Partida;

import Baralho.*;
import Util.*;

/**
 * A classe jogo define os dados que cada partida deve possuir
 */

public class Partida
{
	private int totalJogadores;
	
    private ListaEncadeada<Jogador> jogadores;    
    private String nome;
    private StatusPartida status;
    private Baralho baralho;
    private int moedasApostadas;
    
    /**
     * O construtor seta o nome da partida, o status e o baralho
     */
    public Partida(String _nome, StatusPartida _status, Baralho _baralho)
    {
        setNome(_nome);
        setStatus(_status);
        setBaralho(_baralho);
    }  
    
    public String getNome(){return nome;}
    public void setNome(String _nome){nome = _nome;}

    public StatusPartida getStatus(){return status;}
    public void setStatus(StatusPartida _status){status = _status;}

    public ListaEncadeada<Jogador> getJogadores() { return jogadores; }
    public void setJogadores(Jogador _jogador) 
    { 
    	if(this.jogadores == null)
    	{
    		jogadores = new ListaEncadeada<Jogador>();
    		jogadores.adiciona(_jogador);        	
    	}
    	else jogadores.adiciona(_jogador);
    	
    	this.totalJogadores = jogadores.tamanho();    		
    }

    public int getTotalJogadores() { return totalJogadores;	}

	public Baralho getBaralho() { return baralho; }
    public void setBaralho(Baralho baralho) { this.baralho = baralho; }
    
    public int getMoedasApostadas() { return moedasApostadas; }
	public void setMoedasApostadas(int moedasApostadas) { this.moedasApostadas = moedasApostadas; }
    
}