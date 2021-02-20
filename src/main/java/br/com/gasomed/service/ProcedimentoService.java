package br.com.gasomed.service;

import java.util.List;

import br.com.gasomed.modelo.Procedimento;
import br.com.gasomed.repository.ProcedimentoRepository;
import br.com.gasomed.util.MensagemPainelUtil;

public class ProcedimentoService {
	private ProcedimentoRepository repositorio = new ProcedimentoRepository();

	public void Salvar(Procedimento procedimento) {
		try {
			if(procedimento.getId() == null) {
				this.repositorio.Salvar(procedimento);
				MensagemPainelUtil.Sucesso("PROCEDIMENTO CADASTRADO COM SUCESSO");
			} else {
				this.repositorio.Editar(procedimento);
				MensagemPainelUtil.Sucesso("EDIÇÃO CONCLUÍDA");
			}
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Salvar!! " + e.getMessage());
		}

	}

	public void Remover(Procedimento procedimento) {
		try {
			this.repositorio.Excluir(procedimento);
			MensagemPainelUtil.Sucesso("PROCEDIMENTO EXCLUÍDO!!");
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Remover!! " + e.getMessage());
		}

	}

	public List<Procedimento> ListandoProcedimento() {
		try {
			return repositorio.Listar();
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar!! " + e.getMessage());
			return null;
		}

	}

	public Procedimento BuscandoId(Long id) {
		try {
			return this.repositorio.BuscarPorId(id);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por ID!! " + e.getMessage());
			return null;
		}

	}

	public List<Procedimento> ListandoPorNome(String nome) {
		try {
			return this.repositorio.ListarPorNome(nome);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar valores por Nome!! " + e.getMessage());
			return null;
		}

	}
	
	public Procedimento BuscandoPorNome(String nome) {
		try {
			return this.repositorio.BuscarPorNome(nome);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por Nome!! " + e.getMessage());
			return null;
		}
	}
	
	public boolean VerificaSeTemDados() {
		try {
			return this.repositorio.VerTabelaVazia();
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por Nome!!" + e.getMessage());
			return false;
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
}
