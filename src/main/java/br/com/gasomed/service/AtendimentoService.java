package br.com.gasomed.service;


import java.util.Date;
import java.util.List;

import br.com.gasomed.modelo.Atendimento;
import br.com.gasomed.repository.AtendimentoRepository;
import br.com.gasomed.repository.filtro.AtendimentoFiltro;
import br.com.gasomed.util.MensagemPainelUtil;

public class AtendimentoService {
	private AtendimentoRepository repositorio = new AtendimentoRepository();

	public Long Salvar(Atendimento atendimento) {
		try {
			return this.repositorio.Salvar(atendimento);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Salvar!! " + e.getMessage());
			return 0L;
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
	
	public List<Atendimento> BuscarRelatorio(String nome, Date datainicial, Date datafinal){
		try {
			return this.repositorio.BuscarRelatorio(nome, new java.sql.Date(datainicial.getTime()), new java.sql.Date(datafinal.getTime()));
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar Relatórios!! " + e.getMessage());
			return null;
		}
	}
	
	public List<String> ListarSoNomes(){
		try {
			return this.repositorio.ListarSomenteNomes();
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar Nomes!!" + e.getMessage());
			return null;
		}
	}
	
	public List<Atendimento> ListarAtendGeral(AtendimentoFiltro filtro){
		try {
			return this.repositorio.BuscarRelatorioGeral(filtro);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar Nomes!!" + e.getMessage());
			return null;
		}
	}
	
	public List<Atendimento> ListarRelatorios(AtendimentoFiltro filtro){
		try {
			return this.repositorio.BuscarRelatorioGeral(filtro);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar Nomes!!" + e.getMessage());
			return null;
		}
	}
}
