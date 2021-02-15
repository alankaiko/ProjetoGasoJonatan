package br.com.gasomed.service;

import java.util.List;

import br.com.gasomed.modelo.Atendimento;
import br.com.gasomed.repository.AtendimentoRepository;
import br.com.gasomed.util.MensagemPainelUtil;

public class AtendimentoService {
	private AtendimentoRepository repositorio = new AtendimentoRepository();

	public void Salvar(Atendimento atendimento) {
		try {
			this.repositorio.Salvar(atendimento);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Salvar!! " + e.getMessage());
		}
		
	}

	public void Remover(Atendimento atendimento) {
		try {
			this.repositorio.Excluir(atendimento);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Remover!! " + e.getMessage());
		}
		
	}

	public List<Atendimento> ListandoAtendimento() {
		try {
			return repositorio.Listar();
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar!! " + e.getMessage());
			return null;
		}
		
	}

	public Atendimento BuscandoId(Long id) {
		try {
			return this.repositorio.BuscarPorId(id);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por ID!! " + e.getMessage());
			return null;
		}
		
	}

	public List<Atendimento> ListandoPorNome(String nome) {
		try {
			return this.repositorio.ListarPorNome(nome);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar valores por Nome!! " + e.getMessage());
			return null;
		}

	}
	
	public Atendimento BuscandoPorNome(String nome) {
		try {
			return this.repositorio.BuscarPorNome(nome);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por Nome!! " + e.getMessage());
			return null;
		}
	}
}
