package br.com.gasomed.service;

import java.util.List;

import br.com.gasomed.modelo.Convenio;
import br.com.gasomed.repository.ConvenioRepository;
import br.com.gasomed.util.MensagemPainelUtil;

public class ConvenioService {
	private ConvenioRepository repositorio = new ConvenioRepository();

	public void Salvar(Convenio convenio) {
		try {
			if(convenio.getId() == null) {
				this.repositorio.Salvar(convenio);
				MensagemPainelUtil.Sucesso("CONVÊNIO CADASTRADO COM SUCESSO");
			} else {
				this.repositorio.Editar(convenio);
				MensagemPainelUtil.Sucesso("EDIÇÃO CONCLUÍDA");
			}
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Salvar!! " + e.getMessage());
		}

	}

	public void Remover(Convenio convenio) {
		try {
			this.repositorio.Excluir(convenio);
			MensagemPainelUtil.Sucesso("CONVÊNIO EXCLUÍDO!!");
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Remover!! " + e.getMessage());
		}

	}

	public List<Convenio> ListandoConvenio() {
		try {
			return repositorio.Listar();
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar!! " + e.getMessage());
			return null;
		}

	}

	public Convenio BuscandoId(Long id) {
		try {
			return this.repositorio.BuscarPorId(id);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por ID!! " + e.getMessage());
			return null;
		}

	}

	public List<Convenio> ListandoPorNome(String nome) {
		try {
			return this.repositorio.ListarPorNome(nome);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar valores por Nome!! " + e.getMessage());
			return null;
		}

	}
	
	public Convenio BuscandoPorNome(String nome) {
		try {
			return this.repositorio.BuscarPorNome(nome);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por Nome!! " + e.getMessage());
			return null;
		}
	}
}
