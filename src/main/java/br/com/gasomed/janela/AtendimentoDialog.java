package br.com.gasomed.janela;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.gasomed.listener.AtendimentoListener;
import br.com.gasomed.modelo.EnumNatureza;
import br.com.gasomed.util.CampoMaiusculoUtil;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.Vector;
import javax.swing.JSeparator;

public class AtendimentoDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private final JPanel painel = new JPanel();
	private JLabel LNome, LHospital, LLeito, LConvenio, LMedico, LPh, LHco2, LO2sat, LPo2, LCo2, LNa, Lpco2, LBe, LK, LNatureza;
	private JButton BGravar, BSair;
	private JDateChooser datacad;
	private JComboBox<String> combohospital, combomedico, comboconvenio, combonatureza;
	private JTextField TNome, TLeito, TPh, Thco2, TO2sat, TNa, TCo2, TPo2, TK, TBe, TPco2;
	
	@SuppressWarnings("unused")
	private AtendimentoListener listener;

	public AtendimentoDialog() {
		this.MontarDados();
		this.MontarComponentes();
		
		this.listener = new AtendimentoListener(this);
	}
	
	private void MontarDados() {
		setBounds(100, 100, 660, 315);
		setTitle("CADASTRO DE ATENDIMENTO");
		getContentPane().setLayout(new BorderLayout());
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(painel, BorderLayout.CENTER);
		setModal(true);
		setResizable(false);
		painel.setLayout(null);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void MontarComponentes() {
		TNome = new JTextField();
		TNome.setDocument(new CampoMaiusculoUtil());
		TNome.setBounds(80, 10, 550, 23);
		painel.add(TNome);
		TNome.setColumns(10);
		
		LNome = new JLabel("Nome:");
		LNome.setHorizontalAlignment(SwingConstants.RIGHT);
		LNome.setFont(new Font("Dialog", Font.BOLD, 13));
		LNome.setBounds(10, 15, 60, 14);
		painel.add(LNome);
		
		LHospital = new JLabel("Hospital:");
		LHospital.setHorizontalAlignment(SwingConstants.RIGHT);
		LHospital.setFont(new Font("Dialog", Font.BOLD, 13));
		LHospital.setBounds(10, 45, 60, 14);
		painel.add(LHospital);
		
		TLeito = new JTextField();
		TLeito.setDocument(new CampoMaiusculoUtil());
		TLeito.setColumns(10);
		TLeito.setBounds(80, 70, 265, 23);
		painel.add(TLeito);
		
		LLeito = new JLabel("Leito:");
		LLeito.setHorizontalAlignment(SwingConstants.RIGHT);
		LLeito.setFont(new Font("Dialog", Font.BOLD, 13));
		LLeito.setBounds(10, 75, 60, 14);
		painel.add(LLeito);
		
		combohospital = new JComboBox<String>();
		combohospital.setBackground(Color.WHITE);
		combohospital.setBounds(80, 40, 265, 23);
		painel.add(combohospital);
		
		LConvenio = new JLabel("Convenio:");
		LConvenio.setHorizontalAlignment(SwingConstants.RIGHT);
		LConvenio.setFont(new Font("Dialog", Font.BOLD, 13));
		LConvenio.setBounds(361, 75, 75, 14);
		painel.add(LConvenio);
		 
		combomedico = new JComboBox<String>();
		combomedico.setBackground(Color.WHITE);
		combomedico.setBounds(80, 105, 265, 23);
		painel.add(combomedico);
		
		comboconvenio = new JComboBox<String>();
		comboconvenio.setBackground(Color.WHITE);
		comboconvenio.setBounds(446, 70, 184, 23);
		painel.add(comboconvenio);
		
		LMedico = new JLabel("Medico:");
		LMedico.setHorizontalAlignment(SwingConstants.RIGHT);
		LMedico.setFont(new Font("Dialog", Font.BOLD, 13));
		LMedico.setBounds(10, 110, 60, 14);
		painel.add(LMedico);
		
		LPh = new JLabel("PH:");
		LPh.setHorizontalAlignment(SwingConstants.RIGHT);
		LPh.setFont(new Font("Dialog", Font.BOLD, 13));
		LPh.setBounds(10, 153, 60, 14);
		painel.add(LPh);
		
		TPh = new JTextField();
		TPh.setDocument(new CampoMaiusculoUtil());
		TPh.setColumns(10);
		TPh.setBounds(80, 148, 140, 23);
		painel.add(TPh);
		
		Thco2 = new JTextField();
		Thco2.setDocument(new CampoMaiusculoUtil());
		Thco2.setColumns(10);
		Thco2.setBounds(80, 178, 140, 23);
		painel.add(Thco2);
		
		LHco2 = new JLabel("HCO2:");
		LHco2.setHorizontalAlignment(SwingConstants.RIGHT);
		LHco2.setFont(new Font("Dialog", Font.BOLD, 13));
		LHco2.setBounds(10, 183, 60, 14);
		painel.add(LHco2);
		
		TO2sat = new JTextField();
		TO2sat.setDocument(new CampoMaiusculoUtil());
		TO2sat.setColumns(10);
		TO2sat.setBounds(80, 208, 140, 23);
		painel.add(TO2sat);
		
		LO2sat = new JLabel("O2 SAT:");
		LO2sat.setHorizontalAlignment(SwingConstants.RIGHT);
		LO2sat.setFont(new Font("Dialog", Font.BOLD, 13));
		LO2sat.setBounds(10, 213, 60, 14);
		painel.add(LO2sat);
		
		TNa = new JTextField();
		TNa.setDocument(new CampoMaiusculoUtil());
		TNa.setColumns(10);
		TNa.setBounds(275, 208, 140, 23);
		painel.add(TNa);
		
		TCo2 = new JTextField();
		TCo2.setDocument(new CampoMaiusculoUtil());
		TCo2.setColumns(10);
		TCo2.setBounds(275, 178, 140, 23);
		painel.add(TCo2);
		
		TPo2 = new JTextField();
		TPo2.setDocument(new CampoMaiusculoUtil());
		TPo2.setColumns(10);
		TPo2.setBounds(275, 148, 140, 23);
		painel.add(TPo2);
		
		LPo2 = new JLabel("PO2:");
		LPo2.setHorizontalAlignment(SwingConstants.RIGHT);
		LPo2.setFont(new Font("Dialog", Font.BOLD, 13));
		LPo2.setBounds(230, 153, 39, 14);
		painel.add(LPo2);
		
		LCo2 = new JLabel("CO2:");
		LCo2.setHorizontalAlignment(SwingConstants.RIGHT);
		LCo2.setFont(new Font("Dialog", Font.BOLD, 13));
		LCo2.setBounds(230, 183, 39, 14);
		painel.add(LCo2);
		
		LNa = new JLabel("NA:");
		LNa.setHorizontalAlignment(SwingConstants.RIGHT);
		LNa.setFont(new Font("Dialog", Font.BOLD, 13));
		LNa.setBounds(230, 213, 39, 14);
		painel.add(LNa);
		
		TK = new JTextField();
		TK.setDocument(new CampoMaiusculoUtil());
		TK.setColumns(10);
		TK.setBounds(490, 208, 140, 23);
		painel.add(TK);
		
		TBe = new JTextField();
		TBe.setDocument(new CampoMaiusculoUtil());
		TBe.setColumns(10);
		TBe.setBounds(490, 178, 140, 23);
		painel.add(TBe);
		
		TPco2 = new JTextField();
		TPco2.setDocument(new CampoMaiusculoUtil());
		TPco2.setColumns(10);
		TPco2.setBounds(490, 148, 140, 23);
		painel.add(TPco2);
		
		Lpco2 = new JLabel("PCO2:");
		Lpco2.setHorizontalAlignment(SwingConstants.RIGHT);
		Lpco2.setFont(new Font("Dialog", Font.BOLD, 13));
		Lpco2.setBounds(430, 153, 50, 14);
		painel.add(Lpco2);
		
		LBe = new JLabel("BE:");
		LBe.setHorizontalAlignment(SwingConstants.RIGHT);
		LBe.setFont(new Font("Dialog", Font.BOLD, 13));
		LBe.setBounds(430, 183, 50, 14);
		painel.add(LBe);
		
		LK = new JLabel("K:");
		LK.setHorizontalAlignment(SwingConstants.RIGHT);
		LK.setFont(new Font("Dialog", Font.BOLD, 13));
		LK.setBounds(430, 213, 50, 14);
		painel.add(LK);
		
		BGravar = new JButton();
		BGravar.setText("GRAVAR");
		BGravar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BGravar.setBounds(555, 248, 75, 22);
		painel.add(BGravar);
		
		BSair = new JButton();
		BSair.setText("SAIR");
		BSair.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BSair.setBounds(471, 248, 75, 22);
		painel.add(BSair);
		
		JLabel LData = new JLabel("Data:");
		LData.setHorizontalAlignment(SwingConstants.RIGHT);
		LData.setFont(new Font("Dialog", Font.BOLD, 13));
		LData.setBounds(370, 45, 66, 14);
		painel.add(LData);
		
		datacad = new JDateChooser(new Date());
		datacad.setDateFormatString("EEEE - dd-MM-yyyy");
		datacad.setBounds(446, 40, 184, 23);
		painel.add(datacad);
		
		LNatureza = new JLabel("Nat. Exame:");
		LNatureza.setHorizontalAlignment(SwingConstants.RIGHT);
		LNatureza.setFont(new Font("Dialog", Font.BOLD, 13));
		LNatureza.setBounds(351, 110, 85, 14);
		painel.add(LNatureza);
		
		combonatureza = new JComboBox<String>();
		combonatureza.setModel(new DefaultComboBoxModel(EnumNatureza.values()));
		combonatureza.setBackground(Color.WHITE);
		combonatureza.setBounds(446, 105, 184, 23);
		painel.add(combonatureza);

	}

	public JButton getBGravar() {
		return BGravar;
	}

	public void setBGravar(JButton bGravar) {
		BGravar = bGravar;
	}

	public JButton getBSair() {
		return BSair;
	}

	public void setBSair(JButton bSair) {
		BSair = bSair;
	}

	public JComboBox<String> getCombohospital() {
		return combohospital;
	}

	public void setCombohospital(JComboBox<String> combohospital) {
		this.combohospital = combohospital;
	}

	public JComboBox<String> getCombomedico() {
		return combomedico;
	}

	public void setCombomedico(JComboBox<String> combomedico) {
		this.combomedico = combomedico;
	}

	public JComboBox<String> getComboconvenio() {
		return comboconvenio;
	}

	public void setComboconvenio(JComboBox<String> comboconvenio) {
		this.comboconvenio = comboconvenio;
	}

	public JTextField getTNome() {
		return TNome;
	}

	public void setTNome(JTextField tNome) {
		TNome = tNome;
	}

	public JTextField getTLeito() {
		return TLeito;
	}

	public void setTLeito(JTextField tLeito) {
		TLeito = tLeito;
	}

	public JTextField getTPh() {
		return TPh;
	}

	public void setTPh(JTextField tPh) {
		TPh = tPh;
	}

	public JTextField getThco2() {
		return Thco2;
	}

	public void setThco2(JTextField thco2) {
		Thco2 = thco2;
	}

	public JTextField getTO2sat() {
		return TO2sat;
	}

	public void setTO2sat(JTextField tO2sat) {
		TO2sat = tO2sat;
	}

	public JTextField getTNa() {
		return TNa;
	}

	public void setTNa(JTextField tNa) {
		TNa = tNa;
	}

	public JTextField getTCo2() {
		return TCo2;
	}

	public void setTCo2(JTextField tCo2) {
		TCo2 = tCo2;
	}

	public JTextField getTPo2() {
		return TPo2;
	}

	public void setTPo2(JTextField tPo2) {
		TPo2 = tPo2;
	}

	public JTextField getTK() {
		return TK;
	}

	public void setTK(JTextField tK) {
		TK = tK;
	}

	public JTextField getTBe() {
		return TBe;
	}

	public void setTBe(JTextField tBe) {
		TBe = tBe;
	}

	public JTextField getTPco2() {
		return TPco2;
	}

	public void setTPco2(JTextField tPco2) {
		TPco2 = tPco2;
	}
	
	public JDateChooser getDatacad() {
		return datacad;
	}
	
	public void setDatacad(JDateChooser datacad) {
		this.datacad = datacad;
	}
	
	public JComboBox<String> getCombonatureza() {
		return combonatureza;
	}
	
	public void setCombonatureza(JComboBox<String> combonatureza) {
		this.combonatureza = combonatureza;
	}
}
