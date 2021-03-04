package br.com.gasomed.modelo;

public enum EnumNatureza {
	VENOSO("VENOSO"), ARTERIAL("ARTERIAL");
	
	private String valor;
	
	private EnumNatureza(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return this.valor;
	}
}
