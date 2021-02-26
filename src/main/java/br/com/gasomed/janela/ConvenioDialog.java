package br.com.gasomed.janela;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.gasomed.listener.ConvenioListener;
import br.com.gasomed.util.CampoMaiusculoUtil;

public class ConvenioDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private final JPanel painel = new JPanel();
	private JTextField TNome;
	private JLabel LNome;
	private JScrollPane scrollpane;
	private JTable tabela;
	private JButton BSair, BGravar, BExcluir, BEditar;
	
	@SuppressWarnings("unused")
	private ConvenioListener listener;

	public ConvenioDialog() {
		this.MontarDados();
		this.MontarComponentes();
		
		this.listener = new ConvenioListener(this);
	}
	
	private void MontarDados() {
		setBounds(100, 100, 620, 380);
		setTitle("CADASTRO DE CONVENIO");
		getContentPane().setLayout(new BorderLayout());
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(painel, BorderLayout.CENTER);
		setModal(true);
		setResizable(false);
		painel.setLayout(null);
	}
	
	private void MontarComponentes() {
		TNome = new JTextField();
		TNome.setDocument(new CampoMaiusculoUtil());
		TNome.setBounds(60, 10, 534, 23);
		painel.add(TNome);
		TNome.setColumns(10);
		
		LNome = new JLabel("Nome:");
		LNome.setFont(new Font("Dialog", Font.BOLD, 13));
		LNome.setBounds(10, 15, 46, 14);
		painel.add(LNome);
		
		scrollpane = new JScrollPane();
		scrollpane.setBounds(10, 40, 584, 270);
		painel.add(scrollpane);
		this.tabela = new JTable();
		
		BSair = new JButton();
		BSair.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BSair.setText("Sair");
		BSair.setBounds(264, 315, 75, 22);
		painel.add(BSair);
		
		BGravar = new JButton();
		BGravar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BGravar.setText("GRAVAR");
		BGravar.setBounds(519, 315, 75, 22);
		painel.add(BGravar);
		
		BExcluir = new JButton();
		BExcluir.setText("Excluir");
		BExcluir.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BExcluir.setBounds(349, 315, 75, 22);
		painel.add(BExcluir);
		
		BEditar = new JButton();
		BEditar.setText("Editar");
		BEditar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BEditar.setBounds(434, 315, 75, 22);
		painel.add(BEditar);
	}

	public JTextField getTNome() {
		return TNome;
	}

	public void setTNome(JTextField tNome) {
		TNome = tNome;
	}

	public JScrollPane getScrollpane() {
		return scrollpane;
	}

	public void setScrollpane(JScrollPane scrollpane) {
		this.scrollpane = scrollpane;
	}

	public JButton getBSair() {
		return BSair;
	}

	public void setBSair(JButton bSair) {
		BSair = bSair;
	}

	public JButton getBGravar() {
		return BGravar;
	}

	public void setBGravar(JButton bGravar) {
		BGravar = bGravar;
	}

	public JPanel getContentPanel() {
		return painel;
	}
	
	public JTable getTabela() {
		return tabela;
	}
	
	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}
	
	public JButton getBEditar() {
		return BEditar;
	}
	
	public void setBEditar(JButton bEditar) {
		BEditar = bEditar;
	}
	
	public JButton getBExcluir() {
		return BExcluir;
	}
	
	public void setBExcluir(JButton bExcluir) {
		BExcluir = bExcluir;
	}
}
