package br.com.gasomed.util;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.MaskFormatter;

public class ListasUtil {

	public static String TransformaDataEmString(Date databanco) {
		return new SimpleDateFormat("dd/MM/yyyy").format(databanco);
	}
	
	public static String TransformaHoraEmString(Time tempo) {
		return new SimpleDateFormat("HH:MM").format(tempo);
	}
	
	public static List<String> Meses() {
		List<String> lista = new ArrayList<String>();
		lista.add("Janeiro");
		lista.add("Fevereiro");
		lista.add("Marco");
		lista.add("Abril");
		lista.add("Maio");
		lista.add("Junho");
		lista.add("Julho");
		lista.add("Agosto");
		lista.add("Setembro");
		lista.add("Outubro");
		lista.add("Novembro");
		lista.add("Dezembro");

		return lista;
	}

	public static List<String> DiasSemana() {
		List<String> lista = new ArrayList<String>();
		lista.add("Domingo");
		lista.add("Segunda-Feira");
		lista.add("Terca-Feira");
		lista.add("Quarta-Feira");
		lista.add("Quinta-Feira");
		lista.add("Sexta-Feira");
		lista.add("Sabado");

		return lista;
	}

	public static List<String> Estados() {
		List<String> lista = new ArrayList<String>();
		lista.add("Acre");
		lista.add("Alagoas");
		lista.add("Amazonas");
		lista.add("Amapa");
		lista.add("Bahia");
		lista.add("Ceara");
		lista.add("Distrito Federal");
		lista.add("Espirito Santo");
		lista.add("Goias");
		lista.add("Maranhao");
		lista.add("Minas Gerais");
		lista.add("Mato Grosso do Sul");
		lista.add("Mato Grosso");
		lista.add("Para");
		lista.add("Paraiba");
		lista.add("Piaui");
		lista.add("Parana");
		lista.add("Rio de Janeiro");
		lista.add("Rio Grande do Norte");
		lista.add("Rondonia");
		lista.add("Roraima");
		lista.add("Rio Grande do Sul");
		lista.add("Santa Catarina");
		lista.add("Sergipe");
		lista.add("Sao Paulo");
		lista.add("Tocantins");

		return lista;
	}

	public static List<String> Coren() {
		List<String> lista = new ArrayList<String>();
		lista.add("");
		lista.add("Coren");
		lista.add("CRM");
		lista.add("CRF");

		return lista;
	}

	public static List<String> EstadosAbrev() {
		List<String> lista = new ArrayList<String>();
		lista.add("GO");
		lista.add("AC");
		lista.add("AL");
		lista.add("AP");
		lista.add("AM");
		lista.add("BA");
		lista.add("CE");
		lista.add("DF");
		lista.add("ES");
		lista.add("GO");
		lista.add("MA");
		lista.add("MT");
		lista.add("MS");
		lista.add("MG");
		lista.add("PA");
		lista.add("PB");
		lista.add("PR");
		lista.add("PE");
		lista.add("PI");
		lista.add("RJ");
		lista.add("RN");
		lista.add("RS");
		lista.add("RO");
		lista.add("RR");
		lista.add("SC");
		lista.add("SP");
		lista.add("SE");
		lista.add("TO");

		return lista;
	}
	
	//Metodo que adiciona mascara ao campo de Cpf
	public static MaskFormatter FormataHora(){
		MaskFormatter mascara = null;
		
		try{
			mascara = new MaskFormatter("##:##");
			mascara.setValidCharacters("0123456789");
		}
		catch(ParseException excp){
			
		}
		
		return mascara;
	}
	
}
