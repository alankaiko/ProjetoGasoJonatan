package br.com.gasomed.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gasomed.modelo.Usuario;
import br.com.gasomed.util.ConexaoFactory;

public class UsuarioRepository {

	public void Salvar(Usuario usuario) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO usuario");
		sql.append("(login, cpf, senha) ");
		sql.append("VALUES (?,?,?)");

		Connection conexao = ConexaoFactory.RetornaConexao();
		PreparedStatement prep = conexao.prepareStatement(sql.toString());
		prep.setString(1, usuario.getLogin());
		prep.setString(2, usuario.getCpf());
		prep.setString(3, usuario.getSenha());
		prep.execute();
		conexao.close();
	}

	public void Excluir(Usuario usuario) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM usuario ");
		sql.append("WHERE id = ?");

		Connection conexao = ConexaoFactory.RetornaConexao();
		PreparedStatement aff = conexao.prepareStatement(sql.toString());
		aff.setLong(1, usuario.getId());
		aff.execute();
		conexao.close();
	}

	public void Editar(Usuario usuario) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE usuario ");
		sql.append("SET login = ? ,");
		sql.append("cpf = ? ,");
		sql.append("senha = ? ");
		sql.append("WHERE id = ?");

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, usuario.getLogin());
		pre.setString(2, usuario.getCpf());
		pre.setString(3, usuario.getSenha());
		pre.setLong(4, usuario.getId());
		pre.executeUpdate();
		con.close();
	}

	public Usuario BuscarPorId(Long id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM usuario ");
		sql.append("WHERE id = ?");

		Usuario usuario = null;
		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setLong(1, id);

		ResultSet resultado = pre.executeQuery();

		if (resultado.next()) {
			usuario = new Usuario();
			usuario.setId(resultado.getLong("id"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setCpf(resultado.getString("cpf"));
			usuario.setSenha(resultado.getString("senha"));
		}

		return usuario;
	}

	public List<Usuario> Listar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, login, cpf, senha ");
		sql.append("FROM usuario ");

		List<Usuario> lista = new ArrayList<Usuario>();
		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		ResultSet re = pre.executeQuery();

		while (re.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(re.getLong("id"));
			usuario.setLogin(re.getString("login"));
			usuario.setCpf(re.getString("cpf"));
			usuario.setSenha(re.getString("senha"));
			lista.add(usuario);
		}

		return lista;
	}

	public List<Usuario> ListarPorNome(String login) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM usuario ");
		sql.append("WHERE login LIKE ? ");
		sql.append("ORDER BY login ASC");

		List<Usuario> lista = new ArrayList<Usuario>();

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, "%" + login + "%");

		ResultSet resultado = pre.executeQuery();

		while (resultado.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getLong("id"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setCpf(resultado.getString("cpf"));
			usuario.setSenha(resultado.getString("senha"));
			lista.add(usuario);
		}

		return lista;
	}
	
	public Usuario BuscarPorLogin(String login) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM usuario ");
		sql.append("WHERE login = ? ");

		Usuario usuario = null;

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, login);

		ResultSet resultado = pre.executeQuery();

		while(resultado.next()) {
			usuario = new Usuario();
			usuario.setId(resultado.getLong("id"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setCpf(resultado.getString("cpf"));
			usuario.setSenha(resultado.getString("senha"));
		}

		return usuario;
	}
	
	public boolean VerTabelaVazia() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(id) FROM usuario");

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());

		ResultSet resultado = pre.executeQuery();

		if(!resultado.next())
			return false;
		else
			return true;
	}
	
	public List<String> ListarSomenteUsuarios() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT login ");
		sql.append("FROM usuario ");

		List<String> lista = new ArrayList<String>();
		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		ResultSet re = pre.executeQuery();

		while (re.next()) {
			String login = re.getString("login");
			lista.add(login);
		}

		return lista;
	}
	
	public Usuario VerificarSeExiste(String login, String cpf) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM usuario ");
		sql.append("WHERE login = ? AND cpf = ? ");

		Usuario usuario = null;

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, login);
		pre.setString(2, cpf);

		ResultSet resultado = pre.executeQuery();

		while(resultado.next()) {
			usuario = new Usuario();
			usuario.setId(resultado.getLong("id"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setCpf(resultado.getString("cpf"));
			usuario.setSenha(resultado.getString("senha"));
		}

		return usuario;
	}
	
	public Usuario AutenticaGamb(String login, String senha) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM usuario ");
		sql.append("WHERE login = ? AND senha = ? ");

		Usuario usuario = null;

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, login);
		pre.setString(2, senha);

		ResultSet resultado = pre.executeQuery();

		while(resultado.next()) {
			usuario = new Usuario();
			usuario.setId(resultado.getLong("id"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setCpf(resultado.getString("cpf"));
			usuario.setSenha(resultado.getString("senha"));
		}

		return usuario;
	}
}
