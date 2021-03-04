package br.com.gasomed.repository.filtro;

import java.util.Date;

public class AtendimentoFiltro {
	private String hospital;
	private String medico;
	private String convenio;
	private Date datainicial;
	private Date datafinal;

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public Date getDatainicial() {
		return datainicial;
	}

	public void setDatainicial(Date datainicial) {
		this.datainicial = datainicial;
	}

	public Date getDatafinal() {
		return datafinal;
	}

	public void setDatafinal(Date datafinal) {
		this.datafinal = datafinal;
	}

	@Override
	public String toString() {
		return "AtendimentoFiltro [hospital=" + hospital + ", medico=" + medico + ", convenio=" + convenio
				+  ", datainicial=" + datainicial + ", datafinal=" + datafinal + "]";
	}
	
	

}
