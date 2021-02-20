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
import br.com.gasomed.repository.filtro.AtendimentoFiltro;
import br.com.gasomed.service.AtendimentoService;
import br.com.gasomed.service.ConvenioService;
import br.com.gasomed.service.HospitalService;
import br.com.gasomed.service.MedicoService;
import br.com.gasomed.service.ProcedimentoService;
import br.com.gasomed.tabela.Atendimentotabela;
import br.com.gasomed.util.MensagemPainelUtil;
import br.com.gasomed.zrelatorio.RelatorioConvenioMes;

public class RelatorioGeralListener implements ActionListener, ItemListener {
	private RelatorioGeralDialog tela;
	private Atendimentotabela tabela;

	public RelatorioGeralListener(RelatorioGeralDialog tela) {
		this.tela = tela;
		this.AdicionaListener();
		this.BuscarConvenio();
		this.BuscarHospital();
		this.BuscarMedico();
		this.BuscarProcedimento();
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
		this.tela.getCheckprocedimento().addItemListener(this);
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
			this.CriarPDF();
		
		if (evento.getSource().equals(this.tela.getBGerar()))
			this.BuscarDados();
	}

	private void BuscarDados() {
		Date datainicial = null;
		Date datafinal = null;
		String convenio = "";
		String hospital = "";
		String medico = "";
		String procedimento = "";
		try {
			datainicial = new Date(this.tela.getDatainicial().getDate().getTime());
			datafinal = new Date(this.tela.getDatafinal().getDate().getTime());
			
			if(this.tela.getCheckconvenio().isSelected()) 
				convenio = this.tela.getComboconvenio().getSelectedItem() + "";
			
			if(this.tela.getCheckhospital().isSelected())
				hospital = this.tela.getCombohospital().getSelectedItem() + "";
			
			if(this.tela.getCheckmedico().isSelected())
				medico = this.tela.getCombomedico().getSelectedItem() + "";
			
			if(this.tela.getCheckprocedimento().isSelected())
				medico = this.tela.getComboprocedimento().getSelectedItem() + "";
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Verificar Datas estão selecionadas");
		}
		
		AtendimentoService service = new AtendimentoService();
		this.TabelaDeAtendimentos(service.ListarAtendGeral(convenio, hospital, medico, procedimento, datainicial, datafinal));
	}
	
	private void CriarPDF() {
		AtendimentoFiltro filtro = new AtendimentoFiltro();
		
		try {
			filtro.setDatainicial(new java.sql.Date(this.tela.getDatainicial().getDate().getTime()));
			filtro.setDatafinal(new java.sql.Date(this.tela.getDatafinal().getDate().getTime()));
			
			if(this.tela.getCheckconvenio().isSelected()) 
				filtro.setConvenio(this.tela.getComboconvenio().getSelectedItem() + "");
			
			if(this.tela.getCheckhospital().isSelected())
				filtro.setHospital(this.tela.getCombohospital().getSelectedItem() + "");
			
			if(this.tela.getCheckmedico().isSelected())
				filtro.setMedico(this.tela.getCombomedico().getSelectedItem() + "");
			
			if(this.tela.getCheckprocedimento().isSelected())
				filtro.setProcedimento(this.tela.getComboprocedimento().getSelectedItem() + "");
			
			RelatorioConvenioMes rel = new RelatorioConvenioMes();
			rel.RelatorioPorPessoa(filtro);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Verificar Datas estão selecionadas");
		}		
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
		
		if(this.tela.getCheckprocedimento().isSelected())
			this.tela.getComboprocedimento().setEnabled(true);
		else
			this.tela.getComboprocedimento().setEnabled(false);
		
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void BuscarProcedimento() {
		ProcedimentoService service = new ProcedimentoService();
		List<String> lista = service.ListarSoNomes();
		this.tela.getComboprocedimento().setModel(new DefaultComboBoxModel(new Vector(lista)));
	}
}
