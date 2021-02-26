package br.com.gasomed.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import br.com.gasomed.janela.RelatorioPacienteDialog;
import br.com.gasomed.modelo.Atendimento;
import br.com.gasomed.service.AtendimentoService;
import br.com.gasomed.tabela.Atendimentotabela;
import br.com.gasomed.util.MensagemPainelUtil;
import br.com.gasomed.zrelatorio.RelatorioPorPaciente;

public class RelatorioPacienteListener implements ActionListener {
	private RelatorioPacienteDialog tela;
	private Atendimentotabela tabela;

	public RelatorioPacienteListener(RelatorioPacienteDialog tela) {
		this.tela = tela;
		this.AdicionaListener();
		this.UsandoTAB();
		this.TeclaEsc();
	}

	private void AdicionaListener() {
		this.tela.getBAbrir().addActionListener(this);
		this.tela.getBSair().addActionListener(this);
		this.tela.getBGerar().addActionListener(this);
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
		if (evento.getSource().equals(this.tela.getBAbrir()) && ValidandoField())
			this.CriarPDF();
		else if (evento.getSource().equals(this.tela.getBGerar()) && ValidandoField())
			this.GerarRelatorio();
		else if (evento.getSource().equals(this.tela.getBSair()))
			this.tela.dispose();
		else
			MensagemPainelUtil.Erro("Informe o nome do paciente");
	}

	private void GerarRelatorio() {
		Date datainicial = null;
		Date datafinal = null;
		String nome = null;
		
		try {
			datainicial = new Date(this.tela.getDatainicial().getDate().getTime());
			datafinal = new Date(this.tela.getDatafinal().getDate().getTime());
			nome = this.tela.getTNome().getText();
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Verificar Datas estao selecionadas");
		}
		
		AtendimentoService service = new AtendimentoService();
		this.TabelaDeAtendimentos(service.BuscarRelatorio(nome, datainicial, datafinal));
	}
	
	private void CriarPDF() {
		
		try {
			String nomepaciente = this.tela.getTNome().getText();
			
			Date dataini = this.tela.getDatainicial().getDate();
			Date datafini = this.tela.getDatafinal().getDate();

			RelatorioPorPaciente rel = new RelatorioPorPaciente();
			rel.RelatorioPorPessoa(nomepaciente, dataini, datafini);
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Verificar Datas estao selecionadas");
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
	
	private boolean ValidandoField(){
		return !this.tela.getTNome().getText().isEmpty();
	}
}
