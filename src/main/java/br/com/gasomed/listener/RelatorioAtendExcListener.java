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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.gasomed.janela.RelatorioAtendExcDialog;
import br.com.gasomed.modelo.AtendimentoExcluido;
import br.com.gasomed.service.AtendimentoExcluidoService;
import br.com.gasomed.tabela.AtendimentoExcTabela;
import br.com.gasomed.util.MensagemPainelUtil;

public class RelatorioAtendExcListener implements ActionListener {
	private RelatorioAtendExcDialog tela;
	private AtendimentoExcTabela tabela;

	public RelatorioAtendExcListener(RelatorioAtendExcDialog tela) {
		this.tela = tela;
		this.AdicionaListener();
		this.UsandoTAB();
		this.TeclaEsc();
	}

	private void AdicionaListener() {
		this.tela.getBSair().addActionListener(this);
		this.tela.getBGerar().addActionListener(this);
	}

	private void TabelaDeAtendimentos(List<AtendimentoExcluido> lista) {
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabela = new AtendimentoExcTabela(lista);
		this.tela.getTabela().setModel(tabela);
		this.tela.getTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
		this.tela.getTabela().getColumnModel().getColumn(1).setPreferredWidth(210);
		
		this.tela.getTabela().getColumnModel().getColumn(0).setCellRenderer(centralizado);
		this.tela.getTabela().getColumnModel().getColumn(4).setCellRenderer(centralizado);
		this.tela.getTabela().getColumnModel().getColumn(5).setCellRenderer(centralizado);
		this.tela.getTabela().getColumnModel().getColumn(6).setCellRenderer(centralizado);
		
		this.tela.getTabela().changeSelection(0, 0, false, false);
		// this.gerenciamento.getTable().setRowSelectionInterval(0, 0);
		this.tela.getTabela().setFocusable(false);
		this.tela.getScrollpane().setViewportView(this.tela.getTabela());
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(this.tela.getBGerar()))
			this.GerarRelatorio();
		else if (evento.getSource().equals(this.tela.getBSair()))
			this.tela.dispose();
	}

	private void GerarRelatorio() {
		Date datainicial = null;
		Date datafinal = null;
		
		try {
			datainicial = new Date(this.tela.getDatainicial().getDate().getTime());
			datafinal = new Date(this.tela.getDatafinal().getDate().getTime());
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Verificar Datas estao selecionadas");
		}
		
		AtendimentoExcluidoService service = new AtendimentoExcluidoService();
		this.TabelaDeAtendimentos(service.BuscarRelatorio(datainicial, datafinal));
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
	
	}
	
}
