package br.com.gasomed.service;


import java.util.Date;
import java.util.List;

import br.com.gasomed.modelo.AtendimentoExcluido;
import br.com.gasomed.repository.AtendimentoExcluidoRepository;
import br.com.gasomed.util.MensagemPainelUtil;

public class AtendimentoExcluidoService {
	private AtendimentoExcluidoRepository repositorio = new AtendimentoExcluidoRepository();

	public Long Salvar(AtendimentoExcluido atendimento) {
		try {
			return this.repositorio.Salvar(atendimento);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Salvar!! " + e.getMessage());
			return 0L;
		}	
	}
	
	public List<AtendimentoExcluido> BuscarRelatorio(Date datainicial, Date datafinal){
		try {
			return this.repositorio.BuscarRelatorioExcluidos(new java.sql.Date(datainicial.getTime()), new java.sql.Date(datafinal.getTime()));
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar Relatórios!! " + e.getMessage());
			return null;
		}
	}
	
	
}
