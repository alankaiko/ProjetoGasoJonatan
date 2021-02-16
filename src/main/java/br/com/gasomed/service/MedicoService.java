package br.com.gasomed.service;

import java.util.List;

import br.com.gasomed.modelo.Medico;
import br.com.gasomed.repository.MedicoRepository;
import br.com.gasomed.util.MensagemPainelUtil;

public class MedicoService {
	private MedicoRepository repositorio = new MedicoRepository();

	public void Salvar(Medico medico) {
		try {
			if(medico.getId() == null) {
				this.repositorio.Salvar(medico);
				MensagemPainelUtil.Sucesso("PROFISSIONAL CADASTRADO COM SUCESSO");
			} else {
				this.repositorio.Editar(medico);
				MensagemPainelUtil.Sucesso("EDIÇÃO CONCLUÍDA");
			}
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Salvar!! " + e.getMessage());
		}

	}

	public void Remover(Medico medico) {
		try {
			this.repositorio.Excluir(medico);
			MensagemPainelUtil.Sucesso("PROFISSIONAL EXCLUÍDO!!");
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Remover!! " + e.getMessage());
		}

	}

	public List<Medico> ListandoMedicos() {
		try {
			return repositorio.Listar();
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar!! " + e.getMessage());
			return null;
		}

	}

	public Medico BuscandoId(Long id) {
		try {
			return this.repositorio.BuscarPorId(id);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por ID!! " + e.getMessage());
			return null;
		}

	}

	public List<Medico> ListandoPorNome(String nome) {
		try {
			return this.repositorio.ListarPorNome(nome);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar valores por Nome!! " + e.getMessage());
			return null;
		}

	}
	
	public Medico BuscandoPorNome(String nome) {
		try {
			return this.repositorio.BuscarPorNome(nome);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por Nome!! " + e.getMessage());
			return null;
		}
	}
	
	public Medico VerificandoCampos(String nome, String crm, String estado) {
		try {
			return this.repositorio.VerificarSeExiste(nome, crm, estado);
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
