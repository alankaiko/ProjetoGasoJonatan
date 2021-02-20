package br.com.gasomed.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

import br.com.gasomed.janela.ProcedimentoDialog;
import br.com.gasomed.modelo.Procedimento;
import br.com.gasomed.service.ProcedimentoService;
import br.com.gasomed.tabela.ProcedimentoTabela;
import br.com.gasomed.util.MensagemPainelUtil;

public class ProcedimentoListener implements ActionListener {
	private ProcedimentoDialog tela;
	private ProcedimentoTabela tabela;
	private Procedimento procedimento;

	public ProcedimentoListener(ProcedimentoDialog tela) {
		this.tela = tela;
		this.procedimento = new Procedimento();
		this.AdicionaListener();
		this.TabelaDeProcedimento();
		this.UsandoTAB();
		this.TeclaEsc();
	}

	private void AdicionaListener() {
		this.tela.getBGravar().addActionListener(this);
		this.tela.getBSair().addActionListener(this);
		this.tela.getBEditar().addActionListener(this);
		this.tela.getBExcluir().addActionListener(this);
	}

	private void TabelaDeProcedimento() {
		ProcedimentoService service = new ProcedimentoService();
		tabela = new ProcedimentoTabela(service.ListandoProcedimento());
		this.tela.getTabela().setModel(tabela);
		this.tela.getTabela().getColumnModel().getColumn(0).setPreferredWidth(10);
		this.tela.getTabela().getColumnModel().getColumn(1).setPreferredWidth(410);
		this.tela.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.tela.getTabela().changeSelection(0, 0, false, false);
		// this.gerenciamento.getTable().setRowSelectionInterval(0, 0);
		this.tela.getTabela().setFocusable(false);
		this.tela.getScrollpane().setViewportView(this.tela.getTabela());
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(this.tela.getBSair()))
			this.tela.dispose();

		if (evento.getSource().equals(this.tela.getBGravar()) && this.ValidandoField())
			this.AdicionarValor();
		
		if (evento.getSource().equals(this.tela.getBEditar()))
			this.EditarValor(this.SelecionaLinha());
		
		if (evento.getSource().equals(this.tela.getBExcluir()))
			this.ExcluirValor(this.SelecionaLinha());
		
		this.TabelaDeProcedimento();
	}
	
	private void AdicionarValor() {
		ProcedimentoService service = new ProcedimentoService();
		Procedimento confere = service.BuscandoPorNome(this.tela.getTNome().getText());
		
		if(confere == null) {
			this.procedimento.setNome(this.tela.getTNome().getText());
			
			service.Salvar(this.procedimento);
			this.tela.getTNome().setText("");
			this.procedimento = new Procedimento();
		}else {
			MensagemPainelUtil.Erro("PROCEDIMENTO JÁ CADASTRADO");
		}
	}
	
	private void EditarValor(Long id) {
		ProcedimentoService service = new ProcedimentoService();
		this.procedimento = service.BuscandoId(id);
		this.tela.getTNome().setText(this.procedimento.getNome());
	}
	
	private void ExcluirValor(Long id) {
		ProcedimentoService service = new ProcedimentoService();
		this.procedimento = service.BuscandoId(id);
		int valor = JOptionPane.showConfirmDialog(null, "Deseja Excluir " + this.procedimento.getNome() + "?");

		if(valor == 0)
			service.Remover(procedimento);
		else
			this.procedimento = new Procedimento();
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
		
		this.tela.getBEditar().addKeyListener(new KeyAdapter() {  
            public void keyPressed(KeyEvent e) {  
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
                	tela.getBEditar().doClick();  
                }  
            }  
        });
		
		this.tela.getBExcluir().addKeyListener(new KeyAdapter() {  
            public void keyPressed(KeyEvent e) {  
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
                	tela.getBExcluir().doClick();  
                }  
            }  
        });
	}
	
	private Long SelecionaLinha(){
		int linha = this.tela.getTabela().getSelectedRow();
		Long id = (Long) this.tela.getTabela().getValueAt(linha,0);
		
		return id;
	}
	
	private boolean ValidandoField(){
		return !this.tela.getTNome().getText().isEmpty();
	}

}
