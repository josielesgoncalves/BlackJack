package Principal;

import java.io.Serializable;

/**
 * A classe permite que sejam criados tipos de solicitação que serão enviados para o servidor.
 * Possui metodos getters e setters para cada variavel existente.
 * Implementa também os métodos hashCode, toString e equals.
 */
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
	
	@Override
	public String toString() {
		return "Solicitacao [comando=" + comando + ", complemento1=" + complemento1 + ", complemento2=" + complemento2
				+ ", complemento3=" + complemento3 + ", message=" + message + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comando == null) ? 0 : comando.hashCode());
		result = prime * result + ((complemento1 == null) ? 0 : complemento1.hashCode());
		result = prime * result + ((complemento2 == null) ? 0 : complemento2.hashCode());
		result = prime * result + ((complemento3 == null) ? 0 : complemento3.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		Solicitacao other = (Solicitacao) obj;
		if (comando == null) {
			if (other.comando != null)
				return false;
		} else if (!comando.equals(other.comando))
			return false;
		if (complemento1 == null) {
			if (other.complemento1 != null)
				return false;
		} else if (!complemento1.equals(other.complemento1))
			return false;
		if (complemento2 == null) {
			if (other.complemento2 != null)
				return false;
		} else if (!complemento2.equals(other.complemento2))
			return false;
		if (complemento3 == null) {
			if (other.complemento3 != null)
				return false;
		} else if (!complemento3.equals(other.complemento3))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
}
