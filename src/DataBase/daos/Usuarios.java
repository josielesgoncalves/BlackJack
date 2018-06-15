package DataBase.daos;

import java.sql.*;
import DataBase.*;
import DataBase.core.*;
import DataBase.dbo.*;

public class Usuarios 
{
	
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

	public void alterar(Usuario usuario) throws Exception {
		if (usuario == null)
			throw new Exception("Usuario nao fornecido");

		if (!cadastrado(usuario.getEmail()))
			throw new Exception("Nao cadastrado");

		try {
			String sql;

			sql = "UPDATE bjUser "
				+ "SET NOME = ?, SENHA=?, MOEDAS=?"
				+ "WHERE EMAIL = ?;";

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
