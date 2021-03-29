package br.com.gasomed.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.gasomed.modelo.AtendimentoExcluido;
import br.com.gasomed.util.ConexaoFactory;

public class AtendimentoExcluidoRepository {

	public Long Salvar(AtendimentoExcluido atendimento) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO atendimentoexcluido" );
		sql.append("(nome, hospital, medico, convenio, leito, data, hora, ph, po, pco, hco, co2total, be, o2sat, na, k, file, procedimento, login, cpf, dataexclusao, horaexclusao) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ? )");

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
		prep.setString(18, atendimento.getProcedimento());
		prep.setString(19, atendimento.getLogin());
		prep.setString(20, atendimento.getCpf());
		prep.setDate(21, atendimento.getDataexclusao());
		prep.setTime(22, atendimento.getHoraexclusao());
		prep.execute();
		
		final ResultSet rs = prep.getGeneratedKeys();
		Long valor = 0L;
		if (rs.next()) {
		    valor = rs.getLong(1);
		}
		conexao.close();
		
		return valor;
	}
	
	public List<AtendimentoExcluido> BuscarRelatorioExcluidos(Date datainicial, Date datafinal) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM atendimentoexcluido ");
		sql.append("WHERE (data BETWEEN ? AND ?) ");

		List<AtendimentoExcluido> lista = new ArrayList<AtendimentoExcluido>();

		Connection con = ConexaoFactory.RetornaConexao();
		PreparedStatement pre = con.prepareStatement(sql.toString());
		pre.setDate(1, datainicial);
		pre.setDate(2, datafinal);

		ResultSet resultado = pre.executeQuery();

		while(resultado.next()) {
			AtendimentoExcluido atendimento = new AtendimentoExcluido();
			atendimento.setId(resultado.getLong("id"));
			atendimento.setNome(resultado.getString("nome"));
			atendimento.setLogin(resultado.getString("login"));
			atendimento.setData(resultado.getDate("data"));
			atendimento.setHora(resultado.getTime("hora"));
			atendimento.setDataexclusao(resultado.getDate("dataexclusao"));
			atendimento.setHoraexclusao(resultado.getTime("horaexclusao"));
			lista.add(atendimento);
		}

		return lista;
	}
}
