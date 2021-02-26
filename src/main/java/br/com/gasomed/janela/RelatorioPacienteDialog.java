package br.com.gasomed.janela;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import br.com.gasomed.listener.RelatorioPacienteListener;
import br.com.gasomed.util.CampoMaiusculoUtil;

public class RelatorioPacienteDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private final JPanel painel = new JPanel();
	private JTextField TNome;
	private JLabel LNome, LInicial, LFinal;
	private JScrollPane scrollpane;
	private JDateChooser datainicial, datafinal;
	private JTable tabela;
	private JButton BSair, BAbrir, BGerar, BImprimir;
	
	@SuppressWarnings("unused")
	private RelatorioPacienteListener listener;	


	public RelatorioPacienteDialog() {
		this.MontarDados();
		this.MontarComponentes();
		
		this.listener = new RelatorioPacienteListener(this);
	}
	
	private void MontarDados() {
		setBounds(100, 100, 810, 475);
		setTitle("RELATORIO PACIENTE");
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
		TNome.setBounds(91, 11, 554, 20);
		painel.add(TNome);
		TNome.setColumns(10);
		
		LNome = new JLabel("Nome:");
		LNome.setFont(new Font("Dialog", Font.BOLD, 13));
		LNome.setBounds(10, 15, 46, 14);
		painel.add(LNome);
		
		scrollpane = new JScrollPane();
		scrollpane.setBounds(10, 65, 775, 342);
		painel.add(scrollpane);
		this.tabela = new JTable();
		
		BSair = new JButton();
		BSair.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BSair.setText("Sair");
		BSair.setBounds(625, 410, 75, 22);
		painel.add(BSair);
		
		BAbrir = new JButton();
		BAbrir.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BAbrir.setText("ABRIR");
		BAbrir.setBounds(710, 410, 75, 22);
		painel.add(BAbrir);
		
		BGerar = new JButton();
		BGerar.setText("GERAR");
		BGerar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BGerar.setBounds(655, 10, 130, 48);
		painel.add(BGerar);
		
		LInicial = new JLabel("Data Inicial:");
		LInicial.setFont(new Font("Dialog", Font.BOLD, 13));
		LInicial.setBounds(10, 40, 80, 14);
		painel.add(LInicial);
		
		LFinal = new JLabel("Data Final:");
		LFinal.setFont(new Font("Dialog", Font.BOLD, 13));
		LFinal.setBounds(336, 40, 80, 14);
		painel.add(LFinal);
	
		datainicial = new JDateChooser(new Date());
		datainicial.setBounds(91, 37, 230, 20);
		datainicial.setDateFormatString("EEEE - dd-MM-yyyy");
		painel.add(datainicial);
		
		datafinal = new JDateChooser(new Date());
		datafinal.setBounds(414, 37, 230, 20);
		datafinal.setDateFormatString("EEEE - dd-MM-yyyy");
		painel.add(datafinal);
		
		BImprimir = new JButton();
		BImprimir.setText("Imprimir Laudo");
		BImprimir.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BImprimir.setBounds(10, 410, 115, 22);
		BImprimir.setEnabled(false);
		painel.add(BImprimir);
		
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

	public JButton getBAbrir() {
		return BAbrir;
	}
	
	public void setBAbrir(JButton bAbrir) {
		BAbrir = bAbrir;
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
	
	public JButton getBGerar() {
		return BGerar;
	}
	
	public void setBGerar(JButton bGerar) {
		BGerar = bGerar;
	}
	
	public JButton getBImprimir() {
		return BImprimir;
	}
	
	public void setBImprimir(JButton bImprimir) {
		BImprimir = bImprimir;
	}
	
	public JDateChooser getDatainicial() {
		return datainicial;
	}
	
	public void setDatainicial(JDateChooser datainicial) {
		this.datainicial = datainicial;
	}
	
	public JDateChooser getDatafinal() {
		return datafinal;
	}
	
	public void setDatafinal(JDateChooser datafinal) {
		this.datafinal = datafinal;
	}
}
