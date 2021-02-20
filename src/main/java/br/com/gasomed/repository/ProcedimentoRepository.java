package br.com.gasomed.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gasomed.modelo.Procedimento;
import br.com.gasomed.util.ConexaoFactory;

public class ProcedimentoRepository {

	public void Salvar(Procedimento procedimento) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO procedimento");
		sql.append("(nome) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.RetornaConexao();
		PreparedStatement prep = conexao.prepareStatement(sql.toString());
		prep.setString(1, procedimento.getNome());
		prep.execute();
		conexao.close();
	}

	public void Excluir(Procedimento procedimento) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM procedimento ");
		sql.append("WHERE id = ?");

		Connection conexao = ConexaoFactory.RetornaConexao();
		PreparedStatement aff = conexao.prepareStatement(sql.toString());
		aff.setLong(1, procedimento.getId());
		aff.execute();
		conexao.close();
	}

	public void Editar(Procedimento procedimento) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE procedimento ");
		sql.append("SET nome = ? ");
		sql.append("WHERE id = ?");

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, procedimento.getNome());
		pre.setLong(2, procedimento.getId());
		pre.executeUpdate();
		con.close();
	}

	public Procedimento BuscarPorId(Long id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome ");
		sql.append("FROM procedimento ");
		sql.append("WHERE id = ?");

		Procedimento procedimento = null;
		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setLong(1, id);

		ResultSet resultado = pre.executeQuery();

		if (resultado.next()) {
			procedimento = new Procedimento();
			procedimento.setId(resultado.getLong("id"));
			procedimento.setNome(resultado.getString("nome"));
		}

		return procedimento;
	}

	public List<Procedimento> Listar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome ");
		sql.append("FROM procedimento ");

		List<Procedimento> lista = new ArrayList<Procedimento>();
		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		ResultSet re = pre.executeQuery();

		while (re.next()) {
			Procedimento procedimento = new Procedimento();
			procedimento.setId(re.getLong("id"));
			procedimento.setNome(re.getString("nome"));
			lista.add(procedimento);
		}

		return lista;
	}

	public List<Procedimento> ListarPorNome(String nome) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM procedimento ");
		sql.append("WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC");

		List<Procedimento> lista = new ArrayList<Procedimento>();

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, "%" + nome + "%");

		ResultSet resultado = pre.executeQuery();

		while (resultado.next()) {
			Procedimento procedimento = new Procedimento();
			procedimento.setId(resultado.getLong("id"));
			procedimento.setNome(resultado.getString("nome"));
			lista.add(procedimento);
		}

		return lista;
	}
	
	public Procedimento BuscarPorNome(String nome) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM procedimento ");
		sql.append("WHERE nome = ? ");

		Procedimento procedimento = null;

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, nome);

		ResultSet resultado = pre.executeQuery();

		while(resultado.next()) {
			procedimento = new Procedimento();
			procedimento.setId(resultado.getLong("id"));
			procedimento.setNome(resultado.getString("nome"));
		}

		return procedimento;
	}
	
	public boolean VerTabelaVazia() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(id) FROM procedimento");

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());

		ResultSet resultado = pre.executeQuery();

		if(!resultado.next()) 
			return false;
		else
			return true;		
	}
	
	public List<String> ListarSomenteNomes() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nome ");
		sql.append("FROM procedimento ");

		List<String> lista = new ArrayList<String>();
		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		ResultSet re = pre.executeQuery();

		while (re.next()) {
			String nome = re.getString("nome");
			lista.add(nome);
		}

		return lista;
	}
}
