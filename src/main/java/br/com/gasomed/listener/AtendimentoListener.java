package br.com.gasomed.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import br.com.gasomed.janela.AtendimentoDialog;
import br.com.gasomed.modelo.Atendimento;
import br.com.gasomed.modelo.Convenio;
import br.com.gasomed.modelo.Hospital;
import br.com.gasomed.modelo.Medico;
import br.com.gasomed.service.AtendimentoService;
import br.com.gasomed.service.ConvenioService;
import br.com.gasomed.service.HospitalService;
import br.com.gasomed.service.MedicoService;
import br.com.gasomed.util.MensagemPainelUtil;

public class AtendimentoListener implements ActionListener {
	private AtendimentoDialog tela;

	public AtendimentoListener(AtendimentoDialog tela) {
		this.tela = tela;
		this.AdicionaListener();
		this.UsandoTAB();
		this.TeclaEsc();
		this.CarregarConvenios();
		this.CarregarHospitais();
		this.CarregarMedicos();
	}

	private void AdicionaListener() {
		this.tela.getBGravar().addActionListener(this);
		this.tela.getBSair().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(this.tela.getBSair()))
			this.tela.dispose();

		if (evento.getSource().equals(this.tela.getBGravar()) && this.ValidandoField())
			this.AdicionarValor();
	}
	
	private void AdicionarValor() {
		AtendimentoService service = new AtendimentoService();

		try {
			service.Salvar(this.ReuneValorDosCampos());
			this.ZerarCampos();
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Salvar Atendimento");
		}
		
	}
	
	private Atendimento ReuneValorDosCampos() {
		Atendimento atendimento = new Atendimento();
		atendimento.setNome(this.tela.getTNome().getText());
		atendimento.setHospital((String) this.tela.getCombohospital().getSelectedItem());
		atendimento.setMedico((String) this.tela.getCombomedico().getSelectedItem());
		atendimento.setConvenio((String) this.tela.getComboconvenio().getSelectedItem());
		atendimento.setLeito(this.tela.getTLeito().getText());
		atendimento.setData(new Date(System.currentTimeMillis()));
		atendimento.setHora(new Time(System.currentTimeMillis()));
		atendimento.setPh(this.tela.getTPh().getText());
		atendimento.setPo(this.tela.getTPo2().getText());
		atendimento.setPco(this.tela.getTPco2().getText());
		atendimento.setHco(this.tela.getThco2().getText());
		atendimento.setCo2total(this.tela.getTCo2().getText());
		atendimento.setBe(this.tela.getTBe().getText());
		atendimento.setO2sat(this.tela.getTO2sat().getText());
		atendimento.setNa(this.tela.getTNa().getText());
		atendimento.setK(this.tela.getTK().getText());
		
		return atendimento;
	}
	
	private void ZerarCampos() {
		this.tela.getTNome().setText("");
		this.tela.getTLeito().setText("");
		this.tela.getTPh().setText("");
		this.tela.getTPo2().setText("");
		this.tela.getTPco2().setText("");
		this.tela.getThco2().setText("");
		this.tela.getTCo2().setText("");
		this.tela.getTBe().setText("");
		this.tela.getTO2sat().setText("");
		this.tela.getTNa().setText("");
		this.tela.getTK().setText("");
	}

	@SuppressWarnings("serial")
	private void TeclaEsc() {
		JRootPane meurootpane = this.tela.getRootPane();
		meurootpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE");
		meurootpane.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
			}
		});
	}
	
	private void UsandoTAB(){
		this.tela.getRootPane().setDefaultButton(this.tela.getBGravar());
		this.tela.getBGravar().addKeyListener(new KeyAdapter() {  
            public void keyPressed(KeyEvent e) {  
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
                	tela.getBGravar().doClick();
                }  
            }  
        });
		
		this.tela.getBSair().addKeyListener(new KeyAdapter() {  
            public void keyPressed(KeyEvent e) {  
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
                	tela.getBSair().doClick();  
                }  
            }  
        });
	}
	
	private boolean ValidandoField(){
		return !this.tela.getTNome().getText().isEmpty();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void CarregarConvenios() {
		ConvenioService service = new ConvenioService();
		List<String> lista = new ArrayList<String>();
		
		for(Convenio convenio : service.ListandoConvenio()) {
			lista.add(convenio.getNome());
		}
		this.tela.getComboconvenio().setModel(new DefaultComboBoxModel(new Vector(lista)));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void CarregarMedicos() {
		MedicoService service = new MedicoService();
		List<String> lista = new ArrayList<String>();
		
		for(Medico medico : service.ListandoMedicos()) {
			lista.add(medico.getNome());
		}
		this.tela.getCombomedico().setModel(new DefaultComboBoxModel(new Vector(lista)));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void CarregarHospitais() {
		HospitalService service = new HospitalService();
		List<String> lista = new ArrayList<String>();
		
		for(Hospital hospital : service.ListandoHospital()) {
			lista.add(hospital.getNome());
		}
		this.tela.getCombohospital().setModel(new DefaultComboBoxModel(new Vector(lista)));
	}

}
