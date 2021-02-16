package br.com.gasomed.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gasomed.modelo.Medico;
import br.com.gasomed.util.ConexaoFactory;

public class MedicoRepository {

	public void Salvar(Medico medico) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO medico");
		sql.append("(nome, crm, estado) ");
		sql.append("VALUES (?, ?, ?)");

		Connection conexao = ConexaoFactory.RetornaConexao();
		PreparedStatement prep = conexao.prepareStatement(sql.toString());
		prep.setString(1, medico.getNome());
		prep.setString(2, medico.getCrm());
		prep.setString(3, medico.getEstado());
		prep.execute();
		conexao.close();
	}

	public void Excluir(Medico medico) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM medico ");
		sql.append("WHERE id = ?");

		Connection conexao = ConexaoFactory.RetornaConexao();
		PreparedStatement prepara = conexao.prepareStatement(sql.toString());
		prepara.setLong(1, medico.getId());
		prepara.execute();
		conexao.close();
	}

	public void Editar(Medico medico) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE medico ");
		sql.append("SET nome = ?, ");
		sql.append("crm = ?, ");
		sql.append("estado = ? ");
		sql.append("WHERE id = ?");

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, medico.getNome());
		pre.setString(2, medico.getCrm());
		pre.setString(3, medico.getEstado());
		pre.setLong(4, medico.getId());
		pre.executeUpdate();
		con.close();
	}

	public Medico BuscarPorId(Long id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome, crm, estado ");
		sql.append("FROM medico ");
		sql.append("WHERE id = ?");

		Medico medico = null;
		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setLong(1, id);

		ResultSet resultado = pre.executeQuery();

		if (resultado.next()) {
			medico = new Medico();
			medico.setId(resultado.getLong("id"));
			medico.setNome(resultado.getString("nome"));
			medico.setCrm(resultado.getString("crm"));
			medico.setEstado(resultado.getString("estado"));
		}

		return medico;
	}

	public List<Medico> Listar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome, crm, estado ");
		sql.append("FROM medico ");

		List<Medico> lista = new ArrayList<Medico>();
		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		ResultSet re = pre.executeQuery();

		while (re.next()) {
			Medico medico = new Medico();
			medico.setId(re.getLong("id"));
			medico.setNome(re.getString("nome"));
			medico.setCrm(re.getString("crm"));
			medico.setEstado(re.getString("estado"));
			lista.add(medico);
		}

		return lista;
	}

	public List<Medico> ListarPorNome(String nome) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM medico ");
		sql.append("WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC");

		List<Medico> lista = new ArrayList<Medico>();

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, "%" + nome + "%");

		ResultSet resultado = pre.executeQuery();

		while (resultado.next()) {
			Medico medico = new Medico();
			medico.setId(resultado.getLong("id"));
			medico.setNome(resultado.getString("nome"));
			medico.setCrm(resultado.getString("crm"));
			medico.setEstado(resultado.getString("estado"));
			lista.add(medico);
		}

		return lista;
	}
	
	public Medico BuscarPorNome(String nome) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM medico ");
		sql.append("WHERE nome = ? ");

		Medico medico = null;

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, nome);

		ResultSet resultado = pre.executeQuery();

		while(resultado.next()) {
			medico = new Medico();
			medico.setId(resultado.getLong("id"));
			medico.setNome(resultado.getString("nome"));
			medico.setCrm(resultado.getString("crm"));
			medico.setEstado(resultado.getString("estado"));
		}

		return medico;
	}
	
	public Medico VerificarSeExiste(String nome, String crm, String estado) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM medico ");
		sql.append("WHERE nome = ? AND crm = ? AND estado = ? ");

		Medico medico = null;

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, nome);
		pre.setString(2, crm);
		pre.setString(3, estado);

		ResultSet resultado = pre.executeQuery();

		while(resultado.next()) {
			medico = new Medico();
			medico.setId(resultado.getLong("id"));
			medico.setNome(resultado.getString("nome"));
			medico.setCrm(resultado.getString("crm"));
			medico.setEstado(resultado.getString("estado"));
		}

		return medico;
	}
	
	public boolean VerTabelaVazia() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(id) FROM medico");

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
		sql.append("FROM medico ");

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
