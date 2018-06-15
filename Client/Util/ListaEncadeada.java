package Util;

public class ListaEncadeada<X> 
{
	private No primeiro;
	private No ultimo;	
	private int tamanho;	
	
	public No getPrimeiro() { return primeiro; }	
	public void setPrimeiro(No _primeiro) { this.primeiro = _primeiro; }
    
    public No getUltimo() { return ultimo; }
    public void setUltimo(No _ultimo) { this.ultimo = _ultimo; }
	
    public void criaNo() { primeiro = null; }

    /**
     * O metodo adiciona um novo item no inicio da lista encadeada
     */
    private void adicionaInicio(X item)
    {    	
    	No novo = new No(primeiro, item);
    	primeiro = novo;
    	if(tamanho == 0)
    		ultimo = primeiro;
    	tamanho++;    	
    }  
    
    /**
     * O metodo adiciona um novo item no final da lista encadeada
     */
    public void adiciona(X item)
    {
    	if(tamanho == 0) 
    		adicionaInicio(item);
    	else
    	{
    		No novo = new No(item);
    		ultimo.setProximo(novo);
    		ultimo = novo;
    		tamanho++;
    	}
    }
    
    /**
     * O metodo adiciona uma nova partida na posicao pré definida da lista encadeada
     */
    public void adiciona(int posicao, X item) 
    {
	  if(posicao == 0){ adicionaInicio(item); }
	  
	  else if(posicao == tamanho){ adiciona(item); }
	  
	  else 
	  {
		  No anterior = this.pegaNo(posicao - 1);
		  No nova = new No(anterior.getProximo(), item);
		  anterior.setProximo(nova);
		  tamanho++;
	  }
	}
    
    /**
     * O metodo verifica se a posicao passada da lista encadeada esta ocupada ou nao
     */
    private boolean posicaoOcupada(int posicao)
    {
    	return posicao >=0 && posicao < tamanho;
    }
    
    /**
     * O metodo busca elemento na posicao desejada em tempo não linear
     */    
    private No pegaNo(int posicao)
    {
    	if(!posicaoOcupada(posicao)){throw new IllegalArgumentException("Posicao nao existe");}
    	
    	No atual = primeiro;
    	for(int i = 0; i < posicao; i++)
    		atual = atual.getProximo();
    	return atual;
    }
    
    
    /**
     * O metodo busca elemento na posicao desejada em tempo linear
     */   
    public X pega(int posicao) { return pegaNo(posicao).getInfo(); }
    
    
    /**
     * O metodo busca elemento na lista encadeada
     */
    public No pega(X item) {
    	for(No n = primeiro; n != null; n = n.getProximo())
    		if(n.getInfo() == item)
    			return n;
    	
    	return null; /* nao achou o elemento*/
    }
    
    /**
     * O metodo remove elemento do inicio na lista encadeada
     */
    public void removeInicio()
    {
    	if(!posicaoOcupada(0))
    		throw new IllegalArgumentException("Posicao nao existe");
    	primeiro = primeiro.getProximo();
    	tamanho--;
    	
    	if(tamanho == 0)
    		ultimo = null;
    }

    /**
     * O metodo remove elemento da lista encadeada
     */
    public void remove(X item) {
    	
    	No anterior = null; /*objeto para o elemento anterior*/
    	No n = primeiro; /*objeto para percorrer a lista*/
    	
    	while(n != null && n.getProximo().getInfo() != item) /*procura elemento na lista, guardando anterior*/
    	{
    		anterior = n;
    		n = n.getProximo();
    	}
    	
    	if(n == null)  /*verifica se achou elemento*/
    		return; /*nao achou: mantem no da forma como esta*/
    	
    	if(anterior == null)
    		primeiro = n.getProximo(); /*retira elemento do inicio*/
    	else
    		anterior.setProximo(n.getProximo()); /*retira elemento do meio da lista*/ 
    	
    	tamanho--;
    }
    
    /**
     * O metodo se lista encadeada esta vazia
     */
    public boolean vazio() {
    	if(primeiro == null)
    		return true;
    	else
    		return false;    	
    }
    
    /**
     * O metodo verifica se a lista encadeada contem o elemento desejado
     */
    public boolean contem(X _info) 
    {
    	No atual = primeiro;

    	while (atual != null) {
    		if (atual.getInfo().equals(_info)) return true;
    	    atual = atual.getProximo();
    	  }
    	  return false;
    }
    
    /**
     * O metodo retorna a quantidade de elementos que a lista encadeada possui
     */
    public int tamanho(){ return tamanho; }
    
	@Override
	public boolean equals(Object obj) {
		if (this==obj)
			return true;
		
		if (obj==null)
			return false;
		
		if (this.getClass() != obj.getClass())
			return false;
		
		@SuppressWarnings("unchecked")
		ListaEncadeada<X> lista = (ListaEncadeada<X>)obj;
		
		No pThis =this .primeiro;
		No pLista=lista.primeiro;
		
		while (pThis!=null && pLista!=null)
		{
			if (!pThis.getInfo().equals(pLista.getInfo()))
				return false;
			
			pThis = pThis .getProximo();
			pLista= pLista.getProximo();
		}
		
		if (pThis!=null || pLista!=null)
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int ret=666;
		
		No atual = this.primeiro;		
		while (atual != null)
		{
      		ret = 7*ret + atual.getInfo().hashCode();
			atual = atual.getProximo();
		}		
		return ret;
	}
	
	@Override
	public String toString() {
		String ret = "{";
		
		No atual = this.primeiro;
		
		while(atual!=null)
		{
			ret = ret + atual.getInfo();			
			if (atual.getProximo() != null)
				ret=ret+",";			
			atual=atual.getProximo();
		}		
		return ret + "}";
	}
	
	private class No {
		
		private No proximo;
		private X info;
			
		public No(No _proximo, X _info)
		{
			this.setInfo (_info);
			this.setProximo(_proximo);
		}
		
		public No(X _info){ this.setInfo(_info); }
		
		public No getProximo() { return proximo; }
		public void setProximo(No proximo) { this.proximo = proximo; }
		
		public X getInfo() { return info; }
		public void setInfo(X info) { this.info = info; }
		
	}
}
