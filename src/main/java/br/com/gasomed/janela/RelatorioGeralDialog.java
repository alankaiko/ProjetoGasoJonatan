package br.com.gasomed.janela;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import br.com.gasomed.listener.RelatorioGeralListener;

public class RelatorioGeralDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private final JPanel painel = new JPanel();
	private JLabel LInicial, LFinal;
	private JScrollPane scrollpane;
	private JDateChooser datainicial, datafinal;
	private JCheckBox checkhospital, checkconvenio, checkmedico, checkprocedimento;
	private JComboBox<String> combohospital, comboconvenio, combomedico, comboprocedimento;
	private JTable tabela;
	private JButton BSair, BAbrir, BGerar, BImprimir;
	
	@SuppressWarnings("unused")
	private RelatorioGeralListener listener;	


	public RelatorioGeralDialog() {
		this.MontarDados();
		this.MontarComponentes();
		
		this.listener = new RelatorioGeralListener(this);
	}
	
	private void MontarDados() {
		setBounds(100, 100, 900, 470);
		setTitle("RELATÓRIO GERAL");
		getContentPane().setLayout(new BorderLayout());
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(painel, BorderLayout.CENTER);
		setModal(true);
		setResizable(false);
		painel.setLayout(null);
	}
	
	private void MontarComponentes() {
		scrollpane = new JScrollPane();
		scrollpane.setBounds(10, 87, 867, 315);
		painel.add(scrollpane);
		this.tabela = new JTable();
		
		BSair = new JButton();
		BSair.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BSair.setText("Sair");
		BSair.setBounds(717, 405, 75, 22);
		painel.add(BSair);
		
		BAbrir = new JButton();
		BAbrir.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BAbrir.setText("ABRIR");
		BAbrir.setBounds(802, 405, 75, 22);
		painel.add(BAbrir);
		
		BGerar = new JButton();
		BGerar.setText("GERAR");
		BGerar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BGerar.setBounds(747, 28, 130, 48);
		painel.add(BGerar);
		
		LInicial = new JLabel("Data Inicial:");
		LInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		LInicial.setFont(new Font("Dialog", Font.BOLD, 13));
		LInicial.setBounds(10, 68, 114, 14);
		painel.add(LInicial);
		
		LFinal = new JLabel("Data Final:");
		LFinal.setHorizontalAlignment(SwingConstants.RIGHT);
		LFinal.setFont(new Font("Dialog", Font.BOLD, 13));
		LFinal.setBounds(388, 68, 91, 14);
		painel.add(LFinal);
	
		
		datainicial = new JDateChooser(new Date());
		datainicial.setBounds(134, 65, 250, 20);
		datainicial.setDateFormatString("EEEE - dd-MM-yyyy");
		painel.add(datainicial);
		
		datafinal = new JDateChooser(new Date());
		datafinal.setBounds(489, 65, 250, 20);
		datafinal.setDateFormatString("EEEE - dd-MM-yyyy");
		painel.add(datafinal);
		
		BImprimir = new JButton();
		BImprimir.setText("Imprimir Laudo");
		BImprimir.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BImprimir.setBounds(10, 405, 115, 22);
		BImprimir.setEnabled(false);
		painel.add(BImprimir);
		
		checkhospital = new JCheckBox("Hospital:");
		checkhospital.setFont(new Font("Dialog", Font.BOLD, 13));
		checkhospital.setBounds(10, 12, 120, 23);
		painel.add(checkhospital);
		
		checkconvenio = new JCheckBox("Convênio:");
		checkconvenio.setFont(new Font("Dialog", Font.BOLD, 13));
		checkconvenio.setBounds(394, 12, 90, 23);
		painel.add(checkconvenio);
		
		checkmedico = new JCheckBox("Médico:");
		checkmedico.setFont(new Font("Dialog", Font.BOLD, 13));
		checkmedico.setBounds(394, 38, 90, 23);
		painel.add(checkmedico);
		
		combohospital = new JComboBox<String>();
		combohospital.setBounds(134, 12, 250, 20);
		combohospital.setEnabled(false);
		painel.add(combohospital);
		
		comboconvenio = new JComboBox<String>();
		comboconvenio.setBounds(489, 12, 250, 20);
		comboconvenio.setEnabled(false);
		painel.add(comboconvenio);
		
		combomedico = new JComboBox<String>();
		combomedico.setBounds(489, 38, 250, 20);
		combomedico.setEnabled(false);
		painel.add(combomedico);
		
		checkprocedimento = new JCheckBox("Procedimento:");
		checkprocedimento.setFont(new Font("Dialog", Font.BOLD, 13));
		checkprocedimento.setBounds(10, 38, 120, 23);
		painel.add(checkprocedimento);
		
		comboprocedimento = new JComboBox<String>();
		comboprocedimento.setEnabled(false);
		comboprocedimento.setBounds(134, 38, 250, 20);
		painel.add(comboprocedimento);
		
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

	public JCheckBox getCheckhospital() {
		return checkhospital;
	}

	public void setCheckhospital(JCheckBox checkhospital) {
		this.checkhospital = checkhospital;
	}

	public JCheckBox getCheckconvenio() {
		return checkconvenio;
	}

	public void setCheckconvenio(JCheckBox checkconvenio) {
		this.checkconvenio = checkconvenio;
	}

	public JCheckBox getCheckmedico() {
		return checkmedico;
	}

	public void setCheckmedico(JCheckBox checkmedico) {
		this.checkmedico = checkmedico;
	}

	public JComboBox<String> getCombohospital() {
		return combohospital;
	}

	public void setCombohospital(JComboBox<String> combohospital) {
		this.combohospital = combohospital;
	}

	public JComboBox<String> getComboconvenio() {
		return comboconvenio;
	}

	public void setComboconvenio(JComboBox<String> comboconvenio) {
		this.comboconvenio = comboconvenio;
	}

	public JComboBox<String> getCombomedico() {
		return combomedico;
	}

	public void setCombomedico(JComboBox<String> combomedico) {
		this.combomedico = combomedico;
	}
	
	public JCheckBox getCheckprocedimento() {
		return checkprocedimento;
	}
	
	public void setCheckprocedimento(JCheckBox checkprocedimento) {
		this.checkprocedimento = checkprocedimento;
	}
	
	public JComboBox<String> getComboprocedimento() {
		return comboprocedimento;
	}
	
	public void setComboprocedimento(JComboBox<String> comboprocedimento) {
		this.comboprocedimento = comboprocedimento;
	}
}
