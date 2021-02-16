package br.com.gasomed.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import br.com.gasomed.janela.RelatorioGeralDialog;
import br.com.gasomed.modelo.Atendimento;
import br.com.gasomed.service.AtendimentoService;
import br.com.gasomed.service.ConvenioService;
import br.com.gasomed.service.HospitalService;
import br.com.gasomed.service.MedicoService;
import br.com.gasomed.tabela.Atendimentotabela;
import br.com.gasomed.util.MensagemPainelUtil;

public class RelatorioGeralListener implements ActionListener, ItemListener {
	private RelatorioGeralDialog tela;
	private Atendimentotabela tabela;

	public RelatorioGeralListener(RelatorioGeralDialog tela) {
		this.tela = tela;
		this.AdicionaListener();
		this.BuscarConvenio();
		this.BuscarHospital();
		this.BuscarMedico();
		this.UsandoTAB();
		this.TeclaEsc();
	}

	private void AdicionaListener() {
		this.tela.getBAbrir().addActionListener(this);
		this.tela.getBSair().addActionListener(this);
		this.tela.getBGerar().addActionListener(this);
		
		this.tela.getCheckconvenio().addItemListener(this);
		this.tela.getCheckhospital().addItemListener(this);
		this.tela.getCheckmedico().addItemListener(this);
	}

	private void TabelaDeAtendimentos(List<Atendimento> lista) {
		tabela = new Atendimentotabela(lista);
		this.tela.getTabela().setModel(tabela);
		this.tela.getTabela().getColumnModel().getColumn(0).setPreferredWidth(10);
		this.tela.getTabela().getColumnModel().getColumn(1).setPreferredWidth(210);
		this.tela.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//this.tela.getTabela().changeSelection(0, 0, false, false);
		// this.gerenciamento.getTable().setRowSelectionInterval(0, 0);
		this.tela.getTabela().setFocusable(false);
		this.tela.getScrollpane().setViewportView(this.tela.getTabela());
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(this.tela.getBSair()))
			this.tela.dispose();

		if (evento.getSource().equals(this.tela.getBAbrir()))
			this.GerarRelatorio();
		
		if (evento.getSource().equals(this.tela.getBGerar()))
			this.GerarRelatorio();
	}

	private void GerarRelatorio() {
		Date datainicial = null;
		Date datafinal = null;
		String convenio = null;
		String hospital = null;
		String medico = null;
		try {
			datainicial = new Date(this.tela.getDatainicial().getDate().getTime());
			datafinal = new Date(this.tela.getDatafinal().getDate().getTime());
			if(this.tela.getCheckconvenio().isSelected()) 
				convenio = this.tela.getComboconvenio().getSelectedItem() + "";
			
			if(this.tela.getCheckhospital().isSelected())
				hospital = this.tela.getCombohospital().getSelectedItem() + "";
			
			if(this.tela.getCheckmedico().isSelected())
				medico = this.tela.getCombomedico().getSelectedItem() + "";
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Verificar Datas estão selecionadas");
		}
		
		AtendimentoService service = new AtendimentoService();
		this.TabelaDeAtendimentos(service.ListarAtendGeral(convenio, hospital, medico, datainicial, datafinal));
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
		this.tela.getRootPane().setDefaultButton(this.tela.getBGerar());
		this.tela.getBGerar().addKeyListener(new KeyAdapter() {  
            public void keyPressed(KeyEvent e) {  
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
                	tela.getBGerar().doClick();
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
		
		this.tela.getBAbrir().addKeyListener(new KeyAdapter() {  
            public void keyPressed(KeyEvent e) {  
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
                	tela.getBAbrir().doClick();  
                }  
            }  
        });
	}
	
	@Override
	public void itemStateChanged(ItemEvent evento) {
		if(this.tela.getCheckconvenio().isSelected()) 
			this.tela.getComboconvenio().setEnabled(true);
		else 
			this.tela.getComboconvenio().setEnabled(false);
		
		
		if(this.tela.getCheckhospital().isSelected()) 
			this.tela.getCombohospital().setEnabled(true);
		else
			this.tela.getCombohospital().setEnabled(false);
		
		if(this.tela.getCheckmedico().isSelected())
			this.tela.getCombomedico().setEnabled(true);
		else
			this.tela.getCombomedico().setEnabled(false);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void BuscarHospital() {
		HospitalService service = new HospitalService();
		List<String> lista = service.ListarSoNomes();
		this.tela.getCombohospital().setModel(new DefaultComboBoxModel(new Vector(lista)));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void BuscarConvenio() {
		ConvenioService service = new ConvenioService();
		List<String> lista = service.ListarSoNomes();
		this.tela.getComboconvenio().setModel(new DefaultComboBoxModel(new Vector(lista)));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void BuscarMedico() {
		MedicoService service = new MedicoService();
		List<String> lista = service.ListarSoNomes();
		this.tela.getCombomedico().setModel(new DefaultComboBoxModel(new Vector(lista)));
	}
}
