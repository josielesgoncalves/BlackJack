package Jogo.Baralho;

import java.io.Serializable;

public class Carta implements Serializable
{		
	private static final long serialVersionUID = 1L;
	
	private Naipe naipe;
	private Valor valor;
	
	public Carta(Naipe _naipe, Valor _valor)
	{
		naipe = _naipe;
		valor = _valor;
	}
	
	public Naipe getNaipe(){return naipe;}	
	public Valor getValor(){return valor;}
	
	@Override
	public String toString() {
		return "Carta [naipe=" + naipe + ", valor=" + valor + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naipe == null) ? 0 : naipe.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		if (naipe != other.naipe)
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}
	
}