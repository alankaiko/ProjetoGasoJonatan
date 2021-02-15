package br.com.gasomed.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gasomed.modelo.Hospital;
import br.com.gasomed.util.ConexaoFactory;

public class HospitalRepository {

	public void Salvar(Hospital hospital) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO hospital");
		sql.append("(nome) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.RetornaConexao();
		PreparedStatement prep = conexao.prepareStatement(sql.toString());
		prep.setString(1, hospital.getNome());
		prep.execute();
		conexao.close();
	}

	public void Excluir(Hospital hospital) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM hospital ");
		sql.append("WHERE id = ?");

		Connection conexao = ConexaoFactory.RetornaConexao();
		PreparedStatement aff = conexao.prepareStatement(sql.toString());
		aff.setLong(1, hospital.getId());
		aff.execute();
		conexao.close();
	}

	public void Editar(Hospital hospital) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE hospital ");
		sql.append("SET nome = ? ");
		sql.append("WHERE id = ?");

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, hospital.getNome());
		pre.setLong(2, hospital.getId());
		pre.executeUpdate();
		con.close();
	}

	public Hospital BuscarPorId(Long id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM hospital ");
		sql.append("WHERE id = ?");

		Hospital hospital = null;
		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setLong(1, id);

		ResultSet resultado = pre.executeQuery();

		if (resultado.next()) {
			hospital = new Hospital();
			hospital.setId(resultado.getLong("id"));
			hospital.setNome(resultado.getString("nome"));
		}

		return hospital;
	}

	public List<Hospital> Listar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM hospital ");

		List<Hospital> lista = new ArrayList<Hospital>();
		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		ResultSet re = pre.executeQuery();

		while (re.next()) {
			Hospital hospital = new Hospital();
			hospital.setId(re.getLong("id"));
			hospital.setNome(re.getString("nome"));
			lista.add(hospital);
		}

		return lista;
	}

	public List<Hospital> ListarPorNome(String nome) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM hospital ");
		sql.append("WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC");

		List<Hospital> lista = new ArrayList<Hospital>();

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, "%" + nome + "%");

		ResultSet resultado = pre.executeQuery();

		while (resultado.next()) {
			Hospital hospital = new Hospital();
			hospital.setId(resultado.getLong("id"));
			hospital.setNome(resultado.getString("nome"));
			lista.add(hospital);
		}

		return lista;
	}
	
	public Hospital BuscarPorNome(String nome) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM hospital ");
		sql.append("WHERE nome = ? ");

		Hospital hospital = null;

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, nome);

		ResultSet resultado = pre.executeQuery();

		while(resultado.next()) {
			hospital = new Hospital();
			hospital.setId(resultado.getLong("id"));
			hospital.setNome(resultado.getString("nome"));
		}

		return hospital;
	}
}
