package br.com.gasomed.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gasomed.modelo.Convenio;
import br.com.gasomed.util.ConexaoFactory;

public class ConvenioRepository {

	public void Salvar(Convenio convenio) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO convenio");
		sql.append("(nome) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.RetornaConexao();
		PreparedStatement prep = conexao.prepareStatement(sql.toString());
		prep.setString(1, convenio.getNome());
		prep.execute();
		conexao.close();
	}

	public void Excluir(Convenio convenio) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM convenio ");
		sql.append("WHERE id = ?");

		Connection conexao = ConexaoFactory.RetornaConexao();
		PreparedStatement aff = conexao.prepareStatement(sql.toString());
		aff.setLong(1, convenio.getId());
		aff.execute();
		conexao.close();
	}

	public void Editar(Convenio convenio) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE convenio ");
		sql.append("SET nome = ? ");
		sql.append("WHERE id = ?");

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, convenio.getNome());
		pre.setLong(2, convenio.getId());
		pre.executeUpdate();
		con.close();
	}

	public Convenio BuscarPorId(Long id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome ");
		sql.append("FROM convenio ");
		sql.append("WHERE id = ?");

		Convenio convenio = null;
		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setLong(1, id);

		ResultSet resultado = pre.executeQuery();

		if (resultado.next()) {
			convenio = new Convenio();
			convenio.setId(resultado.getLong("id"));
			convenio.setNome(resultado.getString("nome"));
		}

		return convenio;
	}

	public List<Convenio> Listar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome ");
		sql.append("FROM convenio ");

		List<Convenio> lista = new ArrayList<Convenio>();
		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		ResultSet re = pre.executeQuery();

		while (re.next()) {
			Convenio convenio = new Convenio();
			convenio.setId(re.getLong("id"));
			convenio.setNome(re.getString("nome"));
			lista.add(convenio);
		}

		return lista;
	}

	public List<Convenio> ListarPorNome(String nome) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM convenio ");
		sql.append("WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC");

		List<Convenio> lista = new ArrayList<Convenio>();

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, "%" + nome + "%");

		ResultSet resultado = pre.executeQuery();

		while (resultado.next()) {
			Convenio convenio = new Convenio();
			convenio.setId(resultado.getLong("id"));
			convenio.setNome(resultado.getString("nome"));
			lista.add(convenio);
		}

		return lista;
	}
	
	public Convenio BuscarPorNome(String nome) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM convenio ");
		sql.append("WHERE nome = ? ");

		Convenio convenio = null;

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, nome);

		ResultSet resultado = pre.executeQuery();

		while(resultado.next()) {
			convenio = new Convenio();
			convenio.setId(resultado.getLong("id"));
			convenio.setNome(resultado.getString("nome"));
		}

		return convenio;
	}
}
