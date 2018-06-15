package Servidor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Cliente.Cliente;
import Cliente.Solicitacao;
import DataBase.BD;
import DataBase.dbo.Usuario;
import Jogo.Baralho.*;
import Jogo.Partida.*;

public class Jogo 
{	
	private Solicitacao resposta;
	private Cliente jogador;
	
	public Jogo(Solicitacao solicitacao) { resposta = tratarSolicitacao(solicitacao); }
	
	public Solicitacao getResposta(){ return resposta; }
	
	private Solicitacao tratarSolicitacao(Solicitacao solicitacao) {
		Solicitacao _resp = null;		
		try
		{
			switch (solicitacao.getComando()) 
			{
				case "LOG":
					_resp = logarUsuario(solicitacao);	
					break;
				case "CAD":
					_resp = cadastrarUsuario(solicitacao);				
					break;
				case "PAR":
					_resp = carregarPartidas(solicitacao);				
					break;
				case "CRI":
			    	_resp = criarPartida(solicitacao);               
			    	break;
				case "ENT":
					_resp = entrarPartida(solicitacao);               
			    	break;
				case "APO":
					_resp = apostar(solicitacao);
			    	break;
				case "INI":
					_resp = iniciarPartida(solicitacao);
			    	break;
				case "COM":
					_resp = comprarCarta(solicitacao);               
			    	break;
				case "EOC":
					_resp = pararCompraCartas(solicitacao);               
			    	break;
				case "SAI":
					_resp = sairPartida(solicitacao);               
			    	break;	
				default:
					_resp = new Solicitacao("ERR", "Solicitacao invalida");
					break;
			}		
		}catch(Exception e){e.printStackTrace();}
			
		System.out.println("Resposta: " + _resp.getComando());
		return _resp;
	}
	
	private Solicitacao logarUsuario(Solicitacao solicitacao) throws Exception {
		Solicitacao _resp;
		boolean cadastrado = BD.USUARIOS.cadastrado(solicitacao.getComplemento1()); 
		if (cadastrado)
		{	
			Usuario usuario = BD.USUARIOS.getUsuario(solicitacao.getComplemento1());			
			jogador = new Cliente(usuario.getEmail(), usuario.getNome(), usuario.getSenha(), usuario.getMoedas());
			//TODO: verificar se jogador já não está na lista de jogadores, se sim, tirar e colocar na ultima posição
			Server.JOGADORES.adiciona(jogador);
			
			_resp = new Solicitacao("SUC", usuario.getNome(), usuario.getEmail(), String.valueOf(usuario.getMoedas()));
		                         
		}
		else
		    _resp = new Solicitacao("ERR", "Usuario nao cadastrado");
		return _resp;
	}
	
	private Solicitacao cadastrarUsuario(Solicitacao solicitacao) throws Exception {
		
		Solicitacao _resp;
		if (BD.USUARIOS.cadastrado(solicitacao.getComplemento1()))
		    _resp = new Solicitacao("ERR", "Usuario ja cadastrado");
		else 
		{
			jogador = new Cliente(solicitacao.getComplemento1(), solicitacao.getComplemento2(), solicitacao.getComplemento3(), 1000);
		    Server.JOGADORES.adiciona(jogador); 
		    
		    BD.USUARIOS.inserir(jogador);
		    _resp = new Solicitacao("SUC", solicitacao.getComplemento1(), solicitacao.getComplemento2(), "1000");		                        
		}
		return _resp;
	}
	
	private Solicitacao carregarPartidas(Solicitacao solicitacao) {
		
		Solicitacao _resp = null;
		if(!Server.PARTIDAS.vazio())
		{	
			int i = Server.ITERADOR;
			if(i < Server.PARTIDAS.tamanho())
			{
				Partida par = Server.PARTIDAS.pega(i);
	        	_resp = new Solicitacao("PAR", par.getNome(), par.getStatus().toString());
			}
			else		
				_resp = new Solicitacao("EOP");	
			
			i++;
			Server.ITERADOR = i;
		}       	
		else
		{
			_resp = new Solicitacao("EOP", "Nao existem partidas para serem carregadas");
		}
		return _resp;
	}
	
	private Solicitacao criarPartida(Solicitacao solicitacao) 
	{
		Solicitacao _resp = null;		

		List<Boolean> aux = new ArrayList<Boolean>();
		for(int posicao = 0; posicao < Server.PARTIDAS.tamanho(); posicao++)
		{
			Partida par = Server.PARTIDAS.pega(posicao);
			if(par.getNome().equals(solicitacao.getMessage()))
				aux.add(true);					
		}			
		if(aux.contains(true))				
			_resp = new Solicitacao("ERR","Nome de partida ja esta sendo utilizado");
		else
		{
			Partida p = new Partida(solicitacao.getMessage(), StatusPartida.NAO_INICIADA, new Baralho());
			Server.PARTIDAS.adiciona(p);		    
		    _resp = new Solicitacao("SUC", p.getNome());		
		}		
	
		return _resp;
	}
	
	private Solicitacao entrarPartida(Solicitacao solicitacao) {
		Solicitacao _resp = null;
		jogador = getJogador(solicitacao.getComplemento2());
		
		if(_resp == null)
		{
			for(int i = 0; i < Server.PARTIDAS.tamanho(); i++)
			{
				Partida p = Server.PARTIDAS.pega(i);
				
				if(p.getStatus() == StatusPartida.INICIADA)
				    _resp = new Solicitacao("ERR", "Nao e possivel entrar numa partida inicada");
				else 
				{
					if(p.getNome().equals(solicitacao.getComplemento1()))
					{
						p.setJogadores(jogador);
						jogador.setPartida(p);
						String moedas = String.valueOf(jogador.getMoedas());
						_resp = new Solicitacao("SUC", moedas);						
					}						                	
				}			
			}					
		}
		
		return _resp;
	}

	private Solicitacao apostar(Solicitacao solicitacao) {
		
		Solicitacao _resp;
		
		int valorApostado = Integer.parseInt(solicitacao.getComplemento1());
		jogador = getJogador(solicitacao.getComplemento2());
		int totalApostadoPartida = jogador.getPartida().getMoedasApostadas();
		
		int saldo = jogador.getMoedas();
		
		if(saldo < valorApostado)                
		    _resp = new Solicitacao("ERR", "Voce nao possui esse valor para apostar");
		else
		{
		    saldo = saldo - valorApostado;
		    jogador.setMoedas(saldo);
		    _resp = new Solicitacao("SUC", String.valueOf(saldo));		    
		}
		
		 totalApostadoPartida += valorApostado;
		jogador.getPartida().setMoedasApostadas(totalApostadoPartida);
		
		return _resp;
	}
	
	private Solicitacao iniciarPartida(Solicitacao solicitacao) {
			     
		Solicitacao _resp = null;
		jogador = getJogador(solicitacao.getMessage());
		
		Partida partidaSelecionada = jogador.getPartida();
		if(partidaSelecionada.getJogadores().tamanho() > 2)
		{
			partidaSelecionada.setStatus(StatusPartida.INICIADA);			
	        _resp = new Solicitacao("SUC","A partida foi iniciada");			
		}
		else
			_resp = new Solicitacao("ERR","A partida nao possui 3 jogadores");
		
	    return _resp;
        
	}
	
	private Solicitacao comprarCarta(Solicitacao solicitacao) {
		Solicitacao _resp;
		
		jogador = getJogador(solicitacao.getMessage());
		
		Baralho baralho = jogador.getPartida().getBaralho();
		Carta carta = baralho.comprarCarta();
		jogador.setCarta(carta);
		Naipe naipe = carta.getNaipe();
		Valor valor = carta.getValor();
		
		_resp = new Solicitacao("CAR", "NAIPE: " + naipe + " VALOR: " + valor);
		
		if(baralho.baralhoVazio())
			jogador.getPartida().setBaralho(new Baralho());
		
		return _resp;
	}
	
	private Solicitacao pararCompraCartas(Solicitacao solicitacao) throws Exception, IOException {
		
		jogador = getJogador(solicitacao.getMessage());
		jogador.setParouJogar(true);
		
		int totalApostadoPartida = getTotalApostado();
		int pontuacao = 0;
		int max = 0;
		ListaEncadeada<Jogador> empatados; 
		Jogador vencedor = null;
		Solicitacao _resp = null;
		
		
		Partida partidaSelecionada = jogador.getPartida();
		for(int pos = 0; pos < partidaSelecionada.getJogadores().tamanho(); pos++)
		{	
			Jogador j = partidaSelecionada.getJogadores().pega(pos);
			Jogador j2 = partidaSelecionada.getJogadores().pega(pos+1);
			
			if(j.isParouJogar())
		        pontuacao = contarPontos(j.getCartas(), j);
		    j.setPontuacao(pontuacao);
		    
		    if(j.getPontuacao() == j2.getPontuacao())
		    {
		    	empatados = new ListaEncadeada<Jogador>();
		    	empatados.adiciona(j);
		    	empatados.adiciona(j2);
		    }
		    	
		 
		    if(j.getPontuacao() != j2.getPontuacao())
		    	max = Math.max(j.getPontuacao(), j2.getPontuacao());
		    if(max <= 21)
		    {	
		    	if(max == j.getPontuacao())
		        	 vencedor = j;
		         else vencedor = j2;
		    	
		    	vencedor.setMoedas(totalApostadoPartida);				    	
		    	_resp = new Solicitacao("WIN", vencedor.getEmail(), vencedor.getNome());	
		    	BD.USUARIOS.alterar(vencedor);
		    }
		    
		    _resp = new Solicitacao("EOW", String.valueOf(j.getMoedas()));
		    
		    if(j.getMoedas() == 0)		    
		    	_resp = verificarMoedas(jogador);   	
		    				    
		    BD.USUARIOS.alterar(j);		
		}		
		
		return _resp;
	}
	private Solicitacao sairPartida(Solicitacao solicitacao) {
		
		jogador = getJogador(solicitacao.getMessage());
		
		try 
		{
			BD.USUARIOS.alterar(jogador);
			jogador.getPartida().getJogadores().remove(jogador);
			
		} catch (Exception e) {	e.printStackTrace(); }
		
		return null;
	}
	
	//TODO: verificar ao longo do jogo se jogador possui moedas ou não
	private Solicitacao verificarMoedas(Jogador j) throws IOException {
		Solicitacao _resp;
		j.setMoedas(200);
		//Partida _p = j.getPartida();
		
		//conexao.connect(new InetSocketAddress(j.getEmail(), port),TIME_OUT);
		_resp = new Solicitacao("EOW", "Voce possui ZERO moedas para apostar. "
								 + "Sua conexao sera reestabelecida em 20 minutos e voce ganhara 200 moedas para continuar.");
		return _resp;
	}
	
    private int contarPontos(List<Carta> cartasJogador, Jogador _jogador)
    {
        int resultado = 0;
        int ponto = 0;        
        Valor temp = null;
        
        for(Carta c: _jogador.getCartas())
            if(c.getValor() == Valor.VALETE || c.getValor() == Valor.DAMA || c.getValor() == Valor.REI)
            	temp = c.getValor();            
        
        for(Carta c: _jogador.getCartas())
        {      
            if(c.getValor() == temp)
                if(c.getValor() == Valor.AS)
                    resultado = 11;
            else 
            	if(c.getValor() == Valor.AS)
                    resultado = 1;
            
            switch (c.getValor()) {
				case DOIS:
					resultado = 2;
					break;
				case TRES:
					resultado = 3;
					break;
				case QUATRO:
					resultado = 4;
					break;
				case CINCO:
					resultado = 5;
					break;
				case SEIS:
					resultado = 6;
					break;
				case SETE:
					resultado = 7;
					break;
				case OITO:
					resultado = 8;
					break;
				case NOVE:
					resultado = 9;
					break;
				case DEZ:
					resultado = 10;
					break;
				case VALETE:
					resultado = 10;
					break;
				case DAMA:
					resultado = 10;
					break;
				case REI:
					resultado = 10;
					break;
			default:
				break;
            }
            ponto += resultado;
        }
        return ponto;
    }
    
    private int getTotalApostado() {
		Partida partida = jogador.getPartida();
		partida.getMoedasApostadas();
		return 0;
	}
    
    private Cliente getJogador(String email)
    {
    	Cliente _jogador = null;
    	for(int i = 0; i < Server.JOGADORES.tamanho(); i++)
    		if(Server.JOGADORES.pega(i).getEmail().equals(email))
    			_jogador = Server.JOGADORES.pega(i);
		return _jogador;
    			 
    }

}
