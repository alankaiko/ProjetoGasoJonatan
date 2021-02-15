package br.com.gasomed.listener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;

import br.com.gasomed.janela.TelaPrincipalJanela;
import br.com.gasomed.util.ListasUtil;

public class BarraStatusListener {
	private TelaPrincipalJanela tela;
	
	public BarraStatusListener(TelaPrincipalJanela tela) {
		this.tela = tela;
		BarraDeStatus();
	}
	
	private void BarraDeStatus(){	 
		this.tela.getLAdministracao().setText("DOOR TECNOLOGIA (62) 99229-5900");
	    this.tela.getLAdministracao().setFont(new Font("Segoe UI", Font.PLAIN, 13));
	    this.tela.getLAdministracao().setForeground(Color.BLACK);
	    this.tela.getLAdministracao().setPreferredSize(new Dimension(75, 20));
	    
	    this.tela.getLStatusHora().setText("HORAS");
	    this.tela.getLStatusHora().setFont(new Font("Segoe UI", Font.PLAIN, 13));
	    this.tela.getLStatusHora().setForeground(Color.BLACK);
	    this.tela.getLStatusHora().setPreferredSize(new Dimension(75, 20));
	    
	    this.tela.getLStatusData().setText("DATA");
	    this.tela.getLStatusData().setFont(new Font("Segoe UI", Font.PLAIN, 13));
	    this.tela.getLStatusData().setForeground(Color.BLACK);
	    this.tela.getLStatusData().setPreferredSize(new Dimension(260, 20));
	    
	    ActionListener tarefa = new ActionListener(){
	      public void actionPerformed(ActionEvent e){
	        horasData();
	      }
	    };

	    javax.swing.Timer timer = new javax.swing.Timer(1000, tarefa);
	    timer.start();
	}
	
	private void horasData(){
		Calendar agora = Calendar.getInstance();
		int ho = agora.get(Calendar.HOUR_OF_DAY);
		int mi = agora.get(Calendar.MINUTE);
		int se = agora.get(Calendar.SECOND);
		
		int ds = agora.get(Calendar.DAY_OF_WEEK);
		int dia = agora.get(Calendar.DAY_OF_MONTH);
		int mes = agora.get(Calendar.MONTH);
		int ano = agora.get(Calendar.YEAR);
		
		this.tela.getLStatusHora().setText("Hrs: "+formatar(ho % 24) + ":" + formatar(mi) + ":" + formatar(se) + "");
		
		this.tela.getLStatusData().setText(ListasUtil.DiasSemana().get(ds -1) + ", " + formatar(dia) + " de " + ListasUtil.Meses().get(mes) + " de " + ano + "");
	}
	  
	private String formatar(int num){
		DecimalFormat df = new DecimalFormat("00");
		    
		return df.format(num);
	}
		  
}
