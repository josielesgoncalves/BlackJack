import java.io.IOException;
import java.util.*;

import Principal.*;
import Util.ListaEncadeada;
import dbo.Usuario;
import Baralho.*;
import Partida.*;

/**
* A classe Controlador tem como funcao retornar os resultados que são obtidos pelo
* servidor após o envio de cada comando do jogo 
*/
public class Controlador 
{	
	private Solicitacao resposta;
	private Cliente jogador;
	
	public Controlador(Solicitacao solicitacao) 
	{ 
		resposta = tratarSolicitacao(solicitacao);
	}
	
	public Solicitacao getResposta(){ return resposta; }
	
	
	/**
	* O método retorna a resposta de uma solicitacao enviada pelo cliente
	*/
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
					_resp = pararJogadas(solicitacao);               
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
	
	/**
	* O método verifica se o usuario esta cadastrado, e caso esteja, efetua o login do mesmo
	*/
	
	private Solicitacao logarUsuario(Solicitacao solicitacao) throws Exception {
		Solicitacao _resp;
		Server.ITERADOR_PARTIDAS = 0;
		
		boolean cadastrado = BD.USUARIOS.cadastrado(solicitacao.getComplemento1()); 
		if (cadastrado)
		{	
			Usuario usuario = verificarUsuarioLogado(solicitacao);		
			
			_resp = new Solicitacao("SUC", usuario.getNome(), usuario.getEmail(), String.valueOf(usuario.getMoedas()));
		                         
		}
		else
		    _resp = new Solicitacao("ERR", "Usuario nao cadastrado");
		return _resp;
	}
	
	/**
	* O método efetua o cadastramento de um novo usuario
	*/
	private Solicitacao cadastrarUsuario(Solicitacao solicitacao) throws Exception {
		
		Solicitacao _resp;
		Server.ITERADOR_PARTIDAS = 0;
		
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
	
	
	/**
	* O método carrega as partidas que existem dentro do servidor
	*/
	private Solicitacao carregarPartidas(Solicitacao solicitacao) {
		
		Solicitacao _resp = null;
		if(!Server.PARTIDAS.vazio())
		{	
			int i = Server.ITERADOR_PARTIDAS;
			if(i < Server.PARTIDAS.tamanho())
			{
				Partida par = Server.PARTIDAS.pega(i);
	        	_resp = new Solicitacao("PAR", par.getNome(), par.getStatus().toString());
			}
			else		
				_resp = new Solicitacao("EOP");	
			
			i++;
			Server.ITERADOR_PARTIDAS = i;
		}       	
		else
		{
			_resp = new Solicitacao("EOP", "Nao existem partidas para serem carregadas");
		}
		return _resp;
	}
	
	/**
	* O método cria uma nova partida no servidor após o envio do nome pelo cliente
	*/
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
	
	/**
	* O método verifica se o cliente pode entrar na partida desejada ou não.
	* Caso seja possivel, o cliente é inserido dentro da partida
	*/
	private Solicitacao entrarPartida(Solicitacao solicitacao) {
		Solicitacao _resp = null;
		jogador = verificarJogador(solicitacao.getComplemento2());
		
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
						break;
					}						                	
				}			
			}					
		}
		
		return _resp;
	}

	/**
	* O método recebe a aposta que é feita pelo cliente e seta o novo saldo
	*/
	private Solicitacao apostar(Solicitacao solicitacao) {
		
		Solicitacao _resp;
		
		int valorApostado = Integer.parseInt(solicitacao.getComplemento1());
		jogador = verificarJogador(solicitacao.getComplemento2());
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
	
	/**
	* O método inicia a partida solicitada pelo cliente
	*/
	private Solicitacao iniciarPartida(Solicitacao solicitacao) {
		Solicitacao _resp = null;
		jogador = verificarJogador(solicitacao.getMessage());
		
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
	
	/**
	* O método seta as cartas compradas pelo cliente
	*/
	private Solicitacao comprarCarta(Solicitacao solicitacao) {
		Solicitacao _resp;
		jogador = verificarJogador(solicitacao.getMessage());
				
		
		Baralho baralho = jogador.getPartida().getBaralho();
		Carta carta = baralho.comprarCarta();
		jogador.setCarta(carta);
		int pontos = jogador.getPontuacao();
		pontos = contarPontos(jogador.getCartas(), pontos);
		jogador.setPontuacao(pontos);
		Naipe naipe = carta.getNaipe();
		Valor valor = carta.getValor();
		
		_resp = new Solicitacao("CAR", "NAIPE: " + naipe + " VALOR: " + valor, String.valueOf(pontos));
		
		if(baralho.baralhoVazio())
			jogador.getPartida().setBaralho(new Baralho());
		
		return _resp;
	}
	
	/**
	* O método seta a propriedade parouJogar do cliente e verifica se todos os outros já 
	* pararam de jogar tambem para efetuar os calculos e retornar o vencedor da partida
	*/
	private Solicitacao pararJogadas(Solicitacao solicitacao) throws Exception, IOException {
		
		Solicitacao _resp;
		jogador = verificarJogador(solicitacao.getMessage());
		jogador.setParouJogar(true);
		
		Partida partida = jogador.getPartida();
		
		boolean[] todosPararam = new boolean[partida.getJogadores().tamanho()]; 
		for(int i = 0; i < partida.getTotalJogadores(); i++)
		{			
			Jogador outroJogador = partida.getJogadores().pega(i);
			if(outroJogador.isParouJogar() == true)
				todosPararam[i] = true;			
		}
		
		boolean allEqual = true;
		for (boolean aux : todosPararam) {
		    if(aux == false)
		        allEqual = false;
		}
		
		if(allEqual)
			_resp = verificarVencedor(partida);
		else
			_resp = new Solicitacao("ERR", "A partida ainda nao foi finalizada.");
		
		return _resp;
	}
	
	/**
	* O método tira o jogador do partida caso seja solicitado por ele
	*/
	private Solicitacao sairPartida(Solicitacao solicitacao) {
		
		jogador = verificarJogador(solicitacao.getMessage());
		
		try 
		{
			BD.USUARIOS.alterar(jogador);
			jogador.getPartida().getJogadores().remove(jogador);
			jogador.setPartida(null);
			
		} catch (Exception e) {	e.printStackTrace(); }
		
		return new Solicitacao("SAI");
	}
	
    private int contarPontos(List<Carta> cartasJogador, int pontos)
    {
        int resultado = 0;
        for(Carta c: cartasJogador)
        {
        	switch (c.getValor()) {
	            case AS:
					resultado = 1;
					break;				
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
            pontos += resultado;
        }
        
        for(Carta c: cartasJogador)
        	if(cartasJogador.contains(c.getValor() == Valor.VALETE || c.getValor() == Valor.DAMA || c.getValor() == Valor.REI) && cartasJogador.contains(c.getValor() == Valor.AS))
            	pontos += 11;
        
        return pontos;
    }
    
    private Cliente verificarJogador(String email)
    {
    	Cliente _jogador = null;
    	for(int i = 0; i < Server.JOGADORES.tamanho(); i++)
    		if(Server.JOGADORES.pega(i).getEmail().equals(email)){_jogador = Server.JOGADORES.pega(i); break;}
    			
		return _jogador;
    			 
    }
    
	private Usuario verificarUsuarioLogado(Solicitacao solicitacao) throws Exception {
		Usuario usuario = BD.USUARIOS.getUsuario(solicitacao.getComplemento1());
		
		jogador = verificarJogador(solicitacao.getComplemento1());
		
		if(jogador == null)
			jogador = new Cliente(usuario.getEmail(), usuario.getNome(), usuario.getSenha(), usuario.getMoedas());
		else
		{
			if(jogador.getPartida() != null)
			{
				jogador.getPartida().getJogadores().remove(jogador);
				jogador.setPartida(null);
			}			
		}
		
		if(Server.JOGADORES.contem(jogador))
			Server.JOGADORES.remove(jogador);
		Server.JOGADORES.adiciona(jogador);
		return usuario;
	}
	
	private Solicitacao verificarVencedor(Partida partida) throws Exception
	{
		Solicitacao _resp = null;
		int max = 0;
		int totalApostadoPartida = partida.getMoedasApostadas();
		ListaEncadeada<Cliente> listaAux = new ListaEncadeada<>();
		ListaEncadeada<Cliente> vencedores = new ListaEncadeada<>();
				
		
		for(int pos = 0; pos < partida.getJogadores().tamanho(); pos++)
			listaAux.adiciona((Cliente)partida.getJogadores().pega(pos));
		
		max = verificarPontuacaoVencedor(listaAux);
		
		for(int pos = 0; pos < partida.getJogadores().tamanho(); pos++)
		{
			Cliente j = (Cliente)partida.getJogadores().pega(pos);
			if(j.getPontuacao() == max)
			{
				vencedores.adiciona(j);
				listaAux.remove(j);
			}
		}		
		
		if(vencedores.tamanho() > 0)
		{
			int moedasPorVencedor = totalApostadoPartida/vencedores.tamanho();
			for(int i = 0; i < vencedores.tamanho(); i++)
			{
				Cliente vencedor = vencedores.pega(i);
				vencedor.setVencedor(true);
				vencedor.setMoedas(vencedor.getMoedas() + moedasPorVencedor);
			}
		}	   
		
		for(int i = 0; i < listaAux.tamanho(); i++)
		{
			Cliente j = listaAux.pega(i);
			if(j != null)			
				if(j.getMoedas() == 0)
					j.setMoedas(200);							  
		}	
		 
		 if(jogador.isVencedor())
			 _resp = new Solicitacao("WIN", jogador.getNome(), jogador.getEmail());
		 else
		 {
			 if(jogador.getMoedas() == 0)
				 _resp = new Solicitacao("EOW", "Voce possui ZERO moedas para apostar. "
						 + "Sua conexao sera reestabelecida em 20 minutos e voce ganhara 200 moedas para continuar.");
			 else 
				 _resp = new Solicitacao("EOW", String.valueOf(jogador.getMoedas()));
		 }
		 
		Usuario _j = new Usuario(jogador.getEmail(), jogador.getNome(), jogador.getSenha(), jogador.getMoedas());
		BD.USUARIOS.alterar(_j);
		
		return _resp;
	}

	private int verificarPontuacaoVencedor(ListaEncadeada<Cliente> listaAux) {
		
		int max = 0;
		for(int i = 0; i < listaAux.tamanho(); i++)
		{
			Cliente j1 = listaAux.pega(i);
			if(i < listaAux.tamanho() - 1)
			{
				Cliente j2 = listaAux.pega(i+1);
				if(Math.max(j1.getPontuacao(), j2.getPontuacao()) <= 21)
					max = Math.max(j1.getPontuacao(), j2.getPontuacao());
			}				
		}				
		return max;	
	}
}
