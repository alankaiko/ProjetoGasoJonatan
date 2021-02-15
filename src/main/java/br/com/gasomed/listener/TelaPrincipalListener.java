package br.com.gasomed.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import br.com.gasomed.janela.AtendimentoDialog;
import br.com.gasomed.janela.ConvenioDialog;
import br.com.gasomed.janela.HospitalDialog;
import br.com.gasomed.janela.ProfissionalDialog;
import br.com.gasomed.janela.TelaPrincipalJanela;

public class TelaPrincipalListener implements ActionListener {
	private TelaPrincipalJanela tela;
	
	public TelaPrincipalListener(TelaPrincipalJanela tela) {
		this.tela = tela;
		this.AdicionaListener();
	}
	
	private void AdicionaListener(){	
		this.tela.getAtendimentoitem().addActionListener(this);
		this.tela.getConvenioitem().addActionListener(this);
		this.tela.getHopitalitem().addActionListener(this);
		this.tela.getProfissionalitem().addActionListener(this);	
		
		this.tela.getGeralrelatorio().addActionListener(this);
		this.tela.getPacienterelatorio().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		if(evento.getSource().equals(this.tela.getAtendimentoitem()))
			this.AbreTelaAtendimento();
		
		if(evento.getSource().equals(this.tela.getConvenioitem()))
			this.AbreTelaConvenio();
		
		if(evento.getSource().equals(this.tela.getProfissionalitem()))
			this.AbreTelaProfissional();
		
		if(evento.getSource().equals(this.tela.getHopitalitem()))
			this.AbreTelaHospital();
		
		if(evento.getSource().equals(this.tela.getGeralrelatorio()))
			this.AbreTelaGeralRelatorio();
		
		if(evento.getSource().equals(this.tela.getPacienterelatorio()))
			this.AbreTelaPacienteRelatorio();
	}
	
	private void AbreTelaConvenio(){
		ConvenioDialog tela = new ConvenioDialog();
		tela.setLocationRelativeTo(this.tela.getPainelcentral());
		tela.setVisible(true);
	}
	
	private void AbreTelaHospital(){
		HospitalDialog tela = new HospitalDialog();
		tela.setLocationRelativeTo(this.tela.getPainelcentral());
		tela.setVisible(true);
	}
	
	private void AbreTelaProfissional(){
		ProfissionalDialog tela = new ProfissionalDialog();
		tela.setLocationRelativeTo(this.tela.getPainelcentral());
		tela.setVisible(true);
	}
	
	private void AbreTelaAtendimento(){
		AtendimentoDialog tela = new AtendimentoDialog();
		tela.setLocationRelativeTo(this.tela.getPainelcentral());
		tela.setVisible(true);
	}
	
	private void AbreTelaGeralRelatorio(){
		
	}
	
	private void AbreTelaPacienteRelatorio(){
	
	}
	
	@SuppressWarnings("serial")
	public void TeclaEsc(){
        JRootPane meurootpane = this.tela.getRootPane();  
        meurootpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");  
        meurootpane.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {  
  
            public void actionPerformed(ActionEvent evento) {  
            	System.exit(0);
            }  
        });  
    }  
}

















