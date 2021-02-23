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

import br.com.gasomed.janela.HospitalDialog;
import br.com.gasomed.modelo.Hospital;
import br.com.gasomed.service.HospitalService;
import br.com.gasomed.tabela.HospitalTabela;
import br.com.gasomed.util.MensagemPainelUtil;

public class HospitalListener implements ActionListener {
	private HospitalDialog tela;
	private HospitalTabela tabela;
	private Hospital hospital;

	public HospitalListener(HospitalDialog tela) {
		this.tela = tela;
		this.hospital = new Hospital();
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
		HospitalService service = new HospitalService();
		tabela = new HospitalTabela(service.ListandoHospital());
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
		
		else if (evento.getSource().equals(this.tela.getBEditar()))
			this.EditarValor(this.SelecionaLinha());
		
		else if (evento.getSource().equals(this.tela.getBExcluir()))
			this.ExcluirValor(this.SelecionaLinha());
		else
			MensagemPainelUtil.Erro("Preencha os campos");
		
		this.TabelaDeHospitais();
	}
	
	private void AdicionarValor() {
		HospitalService service = new HospitalService();
		Hospital confere = service.BuscandoPorNome(this.tela.getTNome().getText());
		
		if(confere == null) {
			this.hospital.setNome(this.tela.getTNome().getText());
			
			service.Salvar(this.hospital);
			this.tela.getTNome().setText("");
			this.hospital = new Hospital();
		}else {
			MensagemPainelUtil.Erro("HOSPITAL JÁ CADASTRADO");
		}
	}
	
	private void EditarValor(Long id) {
		HospitalService service = new HospitalService();
		this.hospital = service.BuscandoId(id);
		this.tela.getTNome().setText(this.hospital.getNome());
	}
	
	private void ExcluirValor(Long id) {
		HospitalService service = new HospitalService();
		this.hospital = service.BuscandoId(id);
		int valor = JOptionPane.showConfirmDialog(null, "Deseja Excluir " + this.hospital.getNome() + "?");

		if(valor == 0)
			service.Remover(hospital);
		else
			this.hospital = new Hospital();
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
