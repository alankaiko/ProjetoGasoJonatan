package br.com.gasomed.service;


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
}
