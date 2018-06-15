package Cliente;

import java.io.Serializable;

public class Solicitacao implements Serializable {
   
	private static final long serialVersionUID = 1L;
	
	private String comando;
    private String complemento1;
    private String complemento2;
    private String complemento3;

    private String message;
    
    public Solicitacao(String _comando) {
        setComando(_comando);        
    }
    
    public Solicitacao(String _comando, String message) {
        setComando(_comando);
        setMessage(message);
    }

	public Solicitacao(String _comando, String complemento1, String complemento2) {
            setComando(_comando);
            setComplemento1(complemento1);
            setComplemento2(complemento2);
    }
    
    public Solicitacao(String _comando, String complemento1, String complemento2, String complemento3) {
            setComando(_comando);
            setComplemento1(complemento1);
            setComplemento2(complemento2);
            setComplemento3(complemento3);
            
    }
    
    public String getComando() {
		return comando;
	}
	public void setComando(String comando) {
		this.comando = comando;
	}
	public String getComplemento1() {
		return complemento1;
	}
	public void setComplemento1(String complemento1) {
		this.complemento1 = complemento1;
	}
	public String getComplemento2() {
		return complemento2;
	}
	public void setComplemento2(String complemento2) {
		this.complemento2 = complemento2;
	}
	public String getComplemento3() {
		return complemento3;
	}
	public void setComplemento3(String complemento3) {
		this.complemento3 = complemento3;
	}
	
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
