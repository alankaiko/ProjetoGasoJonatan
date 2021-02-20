package br.com.gasomed.service;

import java.sql.Date;
import java.util.List;

import br.com.gasomed.modelo.Atendimento;
import br.com.gasomed.repository.AtendimentoRepository;
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
			return this.repositorio.BuscarRelatorio(nome, datainicial, datafinal);
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
	
	public List<Atendimento> ListarAtendGeral(String convenio, String hospital, String medico, String procedimento, Date datainicial, Date datafinal){
		try {
			return this.repositorio.BuscarRelatorioGeral(convenio, hospital, medico, procedimento, datainicial, datafinal);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar Nomes!!" + e.getMessage());
			return null;
		}
	}
	
	public List<Atendimento> ListarConvenioMes(String convenio, String hospital, String procedimento, String medico, Date datainicial, Date datafinal){
		try {
			return this.repositorio.ListarConvenioMes(convenio, hospital, procedimento, medico, datainicial, datafinal);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar Nomes!!" + e.getMessage());
			return null;
		}
	}
}
