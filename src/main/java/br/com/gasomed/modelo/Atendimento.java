package br.com.gasomed.modelo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Atendimento implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String hospital;
	private String medico;
	private String convenio;
	private String procedimento;
	private String leito;
	private Date data;
	private Time hora;
	private String ph;
	private String po;
	private String pco;
	private String hco;
	private String co2total;
	private String be;
	private String o2sat;
	private String na;
	private String k;
	private String file;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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
	
	public String getProcedimento() {
		return procedimento;
	}
	
	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	public String getLeito() {
		return leito;
	}

	public void setLeito(String leito) {
		this.leito = leito;
	}

	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}

	public Time getHora() {
		return hora;
	}
	
	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public String getPco() {
		return pco;
	}

	public void setPco(String pco) {
		this.pco = pco;
	}

	public String getHco() {
		return hco;
	}

	public void setHco(String hco) {
		this.hco = hco;
	}

	public String getCo2total() {
		return co2total;
	}

	public void setCo2total(String co2total) {
		this.co2total = co2total;
	}

	public String getBe() {
		return be;
	}

	public void setBe(String be) {
		this.be = be;
	}

	public String getO2sat() {
		return o2sat;
	}

	public void setO2sat(String o2sat) {
		this.o2sat = o2sat;
	}

	public String getNa() {
		return na;
	}

	public void setNa(String na) {
		this.na = na;
	}

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atendimento other = (Atendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Atendimento [id=" + id + ", nome=" + nome + ", hospital=" + hospital + ", medico=" + medico
				+ ", convenio=" + convenio + ", procedimento=" + procedimento + ", leito=" + leito + ", data=" + data
				+ ", hora=" + hora + ", ph=" + ph + ", po=" + po + ", pco=" + pco + ", hco=" + hco + ", co2total="
				+ co2total + ", be=" + be + ", o2sat=" + o2sat + ", na=" + na + ", k=" + k + ", file=" + file + "]";
	}

	
}
