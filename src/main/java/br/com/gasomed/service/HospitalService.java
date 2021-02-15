package br.com.gasomed.service;

import java.util.List;

import br.com.gasomed.modelo.Hospital;
import br.com.gasomed.repository.HospitalRepository;
import br.com.gasomed.util.MensagemPainelUtil;

public class HospitalService {
	private HospitalRepository repositorio = new HospitalRepository();

	public void Salvar(Hospital hospital) {
		try {
			if(hospital.getId() == null) {
				this.repositorio.Salvar(hospital);
				MensagemPainelUtil.Sucesso("HOSPITAL CADASTRADO COM SUCESSO");
			} else {
				this.repositorio.Editar(hospital);
				MensagemPainelUtil.Sucesso("EDIÇÃO CONCLUÍDA");
			}
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Salvar!! " + e.getMessage());
		}

	}

	public void Remover(Hospital hospital) {
		try {
			this.repositorio.Excluir(hospital);
			MensagemPainelUtil.Sucesso("HOSPITAL EXCLUÍDO!!");
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Remover!! " + e.getMessage());
		}

	}

	public List<Hospital> ListandoHospital() {
		try {
			return repositorio.Listar();
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar!! " + e.getMessage());
			return null;
		}

	}

	public Hospital BuscandoId(Long id) {
		try {
			return this.repositorio.BuscarPorId(id);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por ID!! " + e.getMessage());
			return null;
		}

	}

	public List<Hospital> ListandoPorNome(String nome) {
		try {
			return this.repositorio.ListarPorNome(nome);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Listar valores por Nome!! " + e.getMessage());
			return null;
		}

	}
	
	public Hospital BuscandoPorNome(String nome) {
		try {
			return this.repositorio.BuscarPorNome(nome);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Buscar por Nome!! " + e.getMessage());
			return null;
		}
	}
}
