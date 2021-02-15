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

import br.com.gasomed.janela.ProfissionalDialog;
import br.com.gasomed.modelo.Medico;
import br.com.gasomed.service.MedicoService;
import br.com.gasomed.tabela.ProfissionalTabela;
import br.com.gasomed.util.MensagemPainelUtil;

public class ProfissionalListener implements ActionListener {
	private ProfissionalDialog tela;
	private ProfissionalTabela tabela;
	private Medico medico;

	public ProfissionalListener(ProfissionalDialog tela) {
		this.tela = tela;
		this.medico = new Medico();
		this.AdicionaListener();
		this.TabelaDeHospitais();
		this.UsandoTAB();
		this.TeclaEsc();
	}

	private void AdicionaListener() {
		this.tela.getBGravar().addActionListener(this);
		this.tela.getBSair().addActionListener(this);
		this.tela.getBEditar().addActionListener(this);
		this.tela.getBExcluir().addActionListener(this);
	}

	private void TabelaDeHospitais() {
		MedicoService service = new MedicoService();
		tabela = new ProfissionalTabela(service.ListandoMedicos());
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
		
		this.TabelaDeHospitais();
	}
	
	private void AdicionarValor() {
		MedicoService service = new MedicoService();
		Medico confere = service.VerificandoCampos(this.tela.getTNome().getText(), this.tela.getTCrm().getText(), (String)this.tela.getComboestado().getSelectedItem());
		
		if(confere == null) {
			this.medico.setNome(this.tela.getTNome().getText());
			this.medico.setCrm(this.tela.getTCrm().getText());
			this.medico.setEstado((String) this.tela.getComboestado().getSelectedItem());
			
			service.Salvar(this.medico);
			this.tela.getTNome().setText("");
			this.tela.getTCrm().setText("");
			this.tela.getComboestado().setSelectedItem("GO");
			this.medico = new Medico();
		}else {
			MensagemPainelUtil.Erro("MÉDICO JÁ CADASTRADO");
		}
	}
	
	private void EditarValor(Long id) {
		MedicoService service = new MedicoService();
		this.medico = service.BuscandoId(id);
		this.tela.getTNome().setText(this.medico.getNome());
		this.tela.getTCrm().setText(this.medico.getCrm());
		this.tela.getComboestado().setSelectedItem(this.medico.getEstado());
	}
	
	private void ExcluirValor(Long id) {
		MedicoService service = new MedicoService();
		this.medico = service.BuscandoId(id);
		int valor = JOptionPane.showConfirmDialog(null, "Deseja Excluir " + this.medico.getNome() + "?");

		if(valor == 0)
			service.Remover(medico);
		else
			this.medico = new Medico();
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
