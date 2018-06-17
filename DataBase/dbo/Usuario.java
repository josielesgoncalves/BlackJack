package dbo;

/**
* A classe implementa os métodos getters e setters para as variáveis que serão usadas para conectar ou cadastrar um usuário. 
*/
public class Usuario {
	private String email;
	private String nome;
	private String senha;
	private int moedas;
	
	public Usuario(){}
        
	public Usuario(String email, String nome, String senha, int moedas) throws Exception {
		this.setEmail(email);
		this.setNome(nome);
		this.setSenha(senha);
		this.setMoedas(moedas);				
	}

	public void setEmail(String email) throws Exception {
		if(email.isEmpty())
                    throw new Exception("Email invalido");		
		else
                    this.email = email;
			
	}

	public void setNome(String nome) throws Exception {
		if (nome.isEmpty())
			throw new Exception("Nome nao fornecido");
		else
			this.nome = nome;
	}

	public void setSenha(String senha) throws Exception {
		if (senha.isEmpty())
			throw new Exception("Senha invalida");
		else
			this.senha = senha;
	}
	
	public void setMoedas(int moedas) { this.moedas = moedas; }
	
	public String getEmail() {
		if (this.email.isEmpty()) 
			throw new java.lang.Error("Data:User => NEW_ERROR: EMPTY EMAIL.");
		else
			return this.email;		 
	}

	public String getNome() { return this.nome;	}

	public String getSenha() { return this.senha; }
	
	public int getMoedas(){ return this.moedas; }	
	
}
