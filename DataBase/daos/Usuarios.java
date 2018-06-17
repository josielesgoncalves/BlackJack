package daos;

import java.sql.*;

import Principal.BD;
import core.*;
import dbo.Usuario;

/**
 * A classe permite que sejam criados os métodos que rodas comandos SQL. 
 */
public class Usuarios 
{
	/**
	 * @return boolean
	 * @param String email
	 * @throws java.lang.Exception
	 * O método verifica se o usuário já possui cadastrado no banco de dados
	 */
	public boolean cadastrado(String email) throws Exception {
		
		boolean retorno = false;
		try {
			String sql;
			sql = "SELECT * FROM bjUser WHERE EMAIL = ?;";

			BD.COMANDO.prepareStatement(sql);
			BD.COMANDO.setString(1, email);
			
			MeuResultSet resultado = (MeuResultSet) BD.COMANDO.executeQuery();
			retorno = resultado.first();

		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar usuario");
		}

		return retorno;
	}

	/**	 
	 * @param Usuario usuario
	 * @throws java.lang.Exception
	 * O método permite que um novo usuário seja inserido no banco de dados.
	 */
	public void inserir(Usuario usuario) throws Exception {
		if (usuario == null)
			throw new Exception("Usuario nao fornecido");

		try {
			String sql;

			sql = "INSERT INTO bjUser(EMAIL, NOME, SENHA, MOEDAS) VALUES (?, ?, ?, ?);";

			BD.COMANDO.prepareStatement(sql);

			BD.COMANDO.setString(1, usuario.getEmail());
			BD.COMANDO.setString(2, usuario.getNome());
			BD.COMANDO.setString(3, usuario.getSenha());
			BD.COMANDO.setInt(4, usuario.getMoedas());
			
			BD.COMANDO.executeUpdate();
			BD.COMANDO.commit();
		} catch (SQLException erro) {
			throw new Exception("Erro ao inserir usuario");
		}
	}

	/**	 
	 * @param String email
	 * @throws java.lang.Exception
	 * O método permite que um usuário seja excluido no banco de dados.
	 */
	public void excluir(String email) throws Exception {
		if (!cadastrado(email))
			throw new Exception("Nao cadastrado");

		try {
			String sql;

			sql = "DELETE FROM bjUser WHERE EMAIL = ?;";

			BD.COMANDO.prepareStatement(sql);

			BD.COMANDO.setString(1, email);

			BD.COMANDO.executeUpdate();
			BD.COMANDO.commit();
		} catch (SQLException erro) {
			throw new Exception("Erro ao excluir usuario");
		}
	}

	/**	
	 * @param Usuario usuario 
	 * @throws java.lang.Exception
	 * O método permite que os dados do usuário sejam alterados e salvos no banco de dados.
	 */
	public void alterar(Usuario usuario) throws Exception {
		if (usuario == null)
			throw new Exception("Usuario nao fornecido");

		if (!cadastrado(usuario.getEmail()))
			throw new Exception("Nao cadastrado");

		try {
			String sql;

			sql = "UPDATE bjUser SET NOME = ?, SENHA = ?, MOEDAS = ? WHERE EMAIL = ?;";

			BD.COMANDO.prepareStatement(sql);

			BD.COMANDO.setString(1, usuario.getNome());
			BD.COMANDO.setString(2, usuario.getSenha());
			BD.COMANDO.setInt(3, usuario.getMoedas());
			BD.COMANDO.setString(4, usuario.getEmail());
			
			BD.COMANDO.executeUpdate();
			BD.COMANDO.commit();
		} catch (SQLException erro) {
			throw new Exception("Erro ao atualizar dados de usuario");
		}
	}

	/**
	 * @param String email	
	 * @return Usuario
	 * @throws java.lang.Exception 
	 * O método permite que os dados do usuário sejam puxados do banco de dados através do email.
	 */
	public Usuario getUsuario(String email) throws Exception {
		Usuario usuario = null;

		try {
			String sql;

			sql = "SELECT * FROM bjUser WHERE EMAIL = ?;";

			BD.COMANDO.prepareStatement(sql);

			BD.COMANDO.setString(1, email);

			MeuResultSet resultado = (MeuResultSet) BD.COMANDO.executeQuery();

			if (!resultado.first())
				throw new Exception("Nao cadastrado");

			usuario = new Usuario(resultado.getString("EMAIL"), resultado.getString("NOME"),
					resultado.getString("SENHA"), resultado.getInt("MOEDAS"));
			
		} catch (SQLException erro) {
			throw new Exception("Erro ao procurar usuario");
		}

		return usuario;
	}

	/**
	 * @return Lista de Usuarios	
	 * @throws java.lang.Exception 
	 * O método permite que os todos os dados de todos os usuários sejam puxados do banco de dados.
	 */
	public MeuResultSet getUsuarios() throws Exception {
		MeuResultSet resultado = null;

		try {
			String sql;

			sql = "SELECT * FROM bjUser;";

			BD.COMANDO.prepareStatement(sql);

			resultado = (MeuResultSet) BD.COMANDO.executeQuery();
		} catch (SQLException erro) {
			throw new Exception("Erro ao recuperar usuarios");
		}

		return resultado;
	}
}
