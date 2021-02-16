package br.com.gasomed.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.gasomed.modelo.Atendimento;
import br.com.gasomed.util.ConexaoFactory;

public class AtendimentoRepository {

	public Long Salvar(Atendimento atendimento) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO atendimento" );
		sql.append("(nome, hospital, medico, convenio, leito, data, hora, ph, po, pco, hco, co2total, be, o2sat, na, k, file) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

		Connection conexao = ConexaoFactory.RetornaConexao();

		PreparedStatement prep = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		prep.setString(1, atendimento.getNome());
		prep.setString(2, atendimento.getHospital());
		prep.setString(3, atendimento.getMedico());
		prep.setString(4, atendimento.getConvenio());
		prep.setString(5, atendimento.getLeito());
		prep.setDate(6, atendimento.getData());
		prep.setTime(7, atendimento.getHora());
		prep.setString(8, atendimento.getPh());
		prep.setString(9, atendimento.getPo());
		prep.setString(10, atendimento.getPco());
		prep.setString(11, atendimento.getHco());
		prep.setString(12, atendimento.getCo2total());
		prep.setString(13, atendimento.getBe());
		prep.setString(14, atendimento.getO2sat());
		prep.setString(15, atendimento.getNa());
		prep.setString(16, atendimento.getK());
		prep.setString(17, atendimento.getFile());
		prep.execute();
		
		final ResultSet rs = prep.getGeneratedKeys();
		Long valor = 0L;
		if (rs.next()) {
		    valor = rs.getLong(1);
		}
		conexao.close();
		
		return valor;
	}

	public void Excluir(Atendimento atendimento) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM atendimento ");
		sql.append("WHERE id = ?");

		Connection conexao = ConexaoFactory.RetornaConexao();
		PreparedStatement aff = conexao.prepareStatement(sql.toString());
		aff.setLong(1, atendimento.getId());
		aff.execute();
		conexao.close();
	}
	
	public void Editar(Atendimento atendimento) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE atendimento ");
		sql.append("SET nome = ? ");
		sql.append("SET hospital = ? ");
		sql.append("SET medico = ? ");
		sql.append("SET convenio = ? ");
		sql.append("SET leito = ? ");
		sql.append("SET data = ? ");
		sql.append("SET hora = ? ");
		sql.append("SET ph = ? ");
		sql.append("SET po = ? ");
		sql.append("SET pco = ? ");
		sql.append("SET hco = ? ");
		sql.append("SET co2total = ? ");
		sql.append("SET be = ? ");
		sql.append("SET o2sat = ? ");
		sql.append("SET na = ? ");
		sql.append("SET k = ? ");
		sql.append("SET file = ? ");
		sql.append("WHERE id = ?");

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, atendimento.getNome());
		pre.setString(2, atendimento.getHospital());
		pre.setString(3, atendimento.getMedico());
		pre.setString(4, atendimento.getConvenio());
		pre.setString(5, atendimento.getLeito());
		pre.setDate(6, (Date) atendimento.getData());
		pre.setTime(7, atendimento.getHora());
		pre.setString(8, atendimento.getPh());
		pre.setString(9, atendimento.getPo());
		pre.setString(10, atendimento.getPco());
		pre.setString(11, atendimento.getHco());
		pre.setString(12, atendimento.getCo2total());
		pre.setString(13, atendimento.getBe());
		pre.setString(14, atendimento.getO2sat());
		pre.setString(15, atendimento.getNa());
		pre.setString(16, atendimento.getK());
		pre.setString(17, atendimento.getFile());
		
		pre.setLong(18, atendimento.getId());
		pre.executeUpdate();
		con.close();
	}
	
	public Atendimento BuscarPorId(Long id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome, hospital, medico, convenio, leito, data, hora, ph, po, pco, hco, co2total, be, o2sat, na, k, file ");
		sql.append("FROM atendimento ");
		sql.append("WHERE id = ?");

		Atendimento atendimento = null;

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setLong(1, id);

		ResultSet resultado = pre.executeQuery();

		if (resultado.next()) {
			atendimento = new Atendimento();
			atendimento.setId(resultado.getLong("id"));
			atendimento.setNome(resultado.getString("nome"));
			atendimento.setHospital(resultado.getString("hospital"));
			atendimento.setMedico(resultado.getString("medico"));
			atendimento.setConvenio(resultado.getString("convenio"));
			atendimento.setLeito(resultado.getString("leito"));
			atendimento.setData(resultado.getDate("data"));
			atendimento.setHora(resultado.getTime("hora"));
			atendimento.setPh(resultado.getString("ph"));
			atendimento.setPo(resultado.getString("po"));
			atendimento.setPco(resultado.getString("pco"));
			atendimento.setHco(resultado.getString("hco"));
			atendimento.setCo2total(resultado.getString("co2total"));
			atendimento.setBe(resultado.getString("be"));
			atendimento.setO2sat(resultado.getString("o2sat"));
			atendimento.setNa(resultado.getString("na"));
			atendimento.setK(resultado.getString("k"));
			atendimento.setFile(resultado.getString("file"));
		}

		return atendimento;
	}
	
	public List<Atendimento> Listar() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome, hospital, medico, convenio, leito, data, hora, ph, po, pco, hco, co2total, be, o2sat, na, k, file ");
		sql.append("FROM atendimento ");

		List<Atendimento> lista = new ArrayList<Atendimento>();

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		ResultSet resultado = pre.executeQuery();

		while (resultado.next()) {
			Atendimento atendimento = new Atendimento();
			atendimento.setId(resultado.getLong("id"));
			atendimento.setNome(resultado.getString("nome"));
			atendimento.setHospital(resultado.getString("hospital"));
			atendimento.setMedico(resultado.getString("medico"));
			atendimento.setConvenio(resultado.getString("convenio"));
			atendimento.setLeito(resultado.getString("leito"));
			atendimento.setData(resultado.getDate("data"));
			atendimento.setHora(resultado.getTime("hora"));
			atendimento.setPh(resultado.getString("ph"));
			atendimento.setPo(resultado.getString("po"));
			atendimento.setPco(resultado.getString("pco"));
			atendimento.setHco(resultado.getString("hco"));
			atendimento.setCo2total(resultado.getString("co2total"));
			atendimento.setBe(resultado.getString("be"));
			atendimento.setO2sat(resultado.getString("o2sat"));
			atendimento.setNa(resultado.getString("na"));
			atendimento.setK(resultado.getString("k"));
			atendimento.setFile(resultado.getString("file"));
			lista.add(atendimento);
		}

		return lista;
	}
	
	public List<Atendimento> ListarPorNome(String nome) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM atendimento ");
		sql.append("WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC");

		List<Atendimento> lista = new ArrayList<Atendimento>();

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, "%" + nome + "%");

		ResultSet resultado = pre.executeQuery();

		while (resultado.next()) {
			Atendimento atendimento = new Atendimento();
			atendimento.setId(resultado.getLong("id"));
			atendimento.setNome(resultado.getString("nome"));
			atendimento.setHospital(resultado.getString("hospital"));
			atendimento.setMedico(resultado.getString("medico"));
			atendimento.setConvenio(resultado.getString("convenio"));
			atendimento.setLeito(resultado.getString("leito"));
			atendimento.setData(resultado.getDate("data"));
			atendimento.setHora(resultado.getTime("hora"));
			atendimento.setPh(resultado.getString("ph"));
			atendimento.setPo(resultado.getString("po"));
			atendimento.setPco(resultado.getString("pco"));
			atendimento.setHco(resultado.getString("hco"));
			atendimento.setCo2total(resultado.getString("co2total"));
			atendimento.setBe(resultado.getString("be"));
			atendimento.setO2sat(resultado.getString("o2sat"));
			atendimento.setNa(resultado.getString("na"));
			atendimento.setK(resultado.getString("k"));
			atendimento.setFile(resultado.getString("file"));
			lista.add(atendimento);
		}

		return lista;
	}
	
	public Atendimento BuscarPorNome(String nome) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM atendimento ");
		sql.append("WHERE nome = ? ");

		Atendimento atendimento = null;

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setString(1, nome);

		ResultSet resultado = pre.executeQuery();

		while(resultado.next()) {
			atendimento = new Atendimento();
			atendimento.setId(resultado.getLong("id"));
			atendimento.setNome(resultado.getString("nome"));
			atendimento.setHospital(resultado.getString("hospital"));
			atendimento.setMedico(resultado.getString("medico"));
			atendimento.setConvenio(resultado.getString("convenio"));
			atendimento.setLeito(resultado.getString("leito"));
			atendimento.setData(resultado.getDate("data"));
			atendimento.setHora(resultado.getTime("hora"));
			atendimento.setPh(resultado.getString("ph"));
			atendimento.setPo(resultado.getString("po"));
			atendimento.setPco(resultado.getString("pco"));
			atendimento.setHco(resultado.getString("hco"));
			atendimento.setCo2total(resultado.getString("co2total"));
			atendimento.setBe(resultado.getString("be"));
			atendimento.setO2sat(resultado.getString("o2sat"));
			atendimento.setNa(resultado.getString("na"));
			atendimento.setK(resultado.getString("k"));
			atendimento.setFile(resultado.getString("file"));
		}

		return atendimento;
	}
	
	public List<Atendimento> BuscarRelatorio(String nome, Date datainicial, Date datafinal) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome, hospital, medico, convenio, leito, data, hora ");
		sql.append("FROM atendimento ");
		sql.append("WHERE (data BETWEEN ? AND ?) AND nome LIKE ? ");

		List<Atendimento> lista = new ArrayList<Atendimento>();

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setDate(1, datainicial);
		pre.setDate(2, datafinal);
		pre.setString(3, "%" + nome + "%");

		ResultSet resultado = pre.executeQuery();

		while(resultado.next()) {
			Atendimento atendimento = new Atendimento();
			atendimento.setId(resultado.getLong("id"));
			atendimento.setNome(resultado.getString("nome"));
			atendimento.setHospital(resultado.getString("hospital"));
			atendimento.setMedico(resultado.getString("medico"));
			atendimento.setConvenio(resultado.getString("convenio"));
			atendimento.setLeito(resultado.getString("leito"));
			atendimento.setData(resultado.getDate("data"));
			atendimento.setHora(resultado.getTime("hora"));
			lista.add(atendimento);
		}

		return lista;
	}
	
	public List<String> ListarSomenteNomes() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nome ");
		sql.append("FROM atendimento ");

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
	
	public List<Atendimento> BuscarRelatorioGeral(String convenio, String hospital, String medico, Date datainicial, Date datafinal) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, nome, hospital, medico, convenio, leito, data, hora ");
		sql.append("FROM atendimento ");
		sql.append("WHERE (data BETWEEN ? AND ?) ");
		
		if(convenio != "") {
			sql.append(" AND convenio = ? ");
			System.out.println("rodou convenio");
		}
		
		if(hospital != "") {
			sql.append(" AND hospital = ? ");
			System.out.println("rodou hospital");
		}
		
		if(medico != "") {
			sql.append(" AND medico = ? ");
			System.out.println("rodou medico");
		}

		List<Atendimento> lista = new ArrayList<Atendimento>();

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setDate(1, datainicial);
		pre.setDate(2, datafinal);
		pre.setString(3, convenio);
		pre.setString(4, hospital);
		pre.setString(5, medico);

		ResultSet resultado = pre.executeQuery();

		while(resultado.next()) {
			Atendimento atendimento = new Atendimento();
			atendimento.setId(resultado.getLong("id"));
			atendimento.setNome(resultado.getString("nome"));
			atendimento.setHospital(resultado.getString("hospital"));
			atendimento.setMedico(resultado.getString("medico"));
			atendimento.setConvenio(resultado.getString("convenio"));
			atendimento.setLeito(resultado.getString("leito"));
			atendimento.setData(resultado.getDate("data"));
			atendimento.setHora(resultado.getTime("hora"));
			lista.add(atendimento);
		}

		return lista;
	}
}
