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

import br.com.gasomed.janela.ConvenioDialog;
import br.com.gasomed.modelo.Convenio;
import br.com.gasomed.service.ConvenioService;
import br.com.gasomed.tabela.ConvenioTabela;
import br.com.gasomed.util.MensagemPainelUtil;

public class ConvenioListener implements ActionListener {
	private ConvenioDialog tela;
	private ConvenioTabela tabela;
	private Convenio convenio;

	public ConvenioListener(ConvenioDialog tela) {
		this.tela = tela;
		this.convenio = new Convenio();
		this.AdicionaListener();
		this.TabelaDeConvenio();
		this.UsandoTAB();
		this.TeclaEsc();
	}

	private void AdicionaListener() {
		this.tela.getBGravar().addActionListener(this);
		this.tela.getBSair().addActionListener(this);
		this.tela.getBEditar().addActionListener(this);
		this.tela.getBExcluir().addActionListener(this);
	}

	private void TabelaDeConvenio() {
		ConvenioService service = new ConvenioService();
		tabela = new ConvenioTabela(service.ListandoConvenio());
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
		if (evento.getSource().equals(this.tela.getBGravar()) && this.ValidandoField())
			this.AdicionarValor();
		
		else if (evento.getSource().equals(this.tela.getBEditar()))
			this.EditarValor(this.SelecionaLinha());
		
		else if (evento.getSource().equals(this.tela.getBExcluir()))
			this.ExcluirValor(this.SelecionaLinha());
		else if (evento.getSource().equals(this.tela.getBSair()))
			this.tela.dispose();
		else
			MensagemPainelUtil.Erro("Preencha os campos");
		
		this.TabelaDeConvenio();
	}
	
	private void AdicionarValor() {
		ConvenioService service = new ConvenioService();
		Convenio confere = service.BuscandoPorNome(this.tela.getTNome().getText());
		
		if(confere == null) {
			this.convenio.setNome(this.tela.getTNome().getText());
			
			service.Salvar(this.convenio);
			this.tela.getTNome().setText("");
			this.convenio = new Convenio();
		}else {
			MensagemPainelUtil.Erro("CONVENIO JA CADASTRADO");
		}
	}
	
	private void EditarValor(Long id) {
		ConvenioService service = new ConvenioService();
		this.convenio = service.BuscandoId(id);
		this.tela.getTNome().setText(this.convenio.getNome());
	}
	
	private void ExcluirValor(Long id) {
		ConvenioService service = new ConvenioService();
		this.convenio = service.BuscandoId(id);
		int valor = JOptionPane.showConfirmDialog(null, "Deseja Excluir " + this.convenio.getNome() + "?");

		if(valor == 0)
			service.Remover(convenio);
		else
			this.convenio = new Convenio();
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
