package br.com.gasomed.janela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.gasomed.listener.ExcluirAtendimentoListener;
import br.com.gasomed.modelo.Usuario;
import br.com.gasomed.util.CampoMaiusculoUtil;

public class ExcluirAtendimentoDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private final JPanel painel = new JPanel();
	private JLabel LNome, LHospital, LLeito, LConvenio, LMedico, LPh, LHco2, LO2sat, LPo2, LCo2, LNa, Lpco2, LBe, LK, LNatureza;
	private JButton BExcluir, BCancelar;
	private JTextField TNome, TLeito, TPh, Thco2, TO2sat, TNa, TCo2, TPo2, TK, TBe, TPco2;
	private JTextField THospital, TMedico, TNatureza, THora, TConvenio, TData;
	private ExcluirAtendimentoListener listener;

	public ExcluirAtendimentoDialog(Long codigo, Usuario usuario) {
		this.MontarDados();
		this.MontarComponentes();
		
		this.listener = new ExcluirAtendimentoListener(this);
		this.listener.EditarValor(codigo, usuario);
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
	
	private void MontarComponentes() {
		TNome = new JTextField();
		TNome.setEditable(false);
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
		TLeito.setEditable(false);
		TLeito.setDocument(new CampoMaiusculoUtil());
		TLeito.setColumns(10);
		TLeito.setBounds(80, 70, 265, 23);
		painel.add(TLeito);
		
		LLeito = new JLabel("Leito:");
		LLeito.setHorizontalAlignment(SwingConstants.RIGHT);
		LLeito.setFont(new Font("Dialog", Font.BOLD, 13));
		LLeito.setBounds(10, 75, 60, 14);
		painel.add(LLeito);
		
		LConvenio = new JLabel("Convenio:");
		LConvenio.setHorizontalAlignment(SwingConstants.RIGHT);
		LConvenio.setFont(new Font("Dialog", Font.BOLD, 13));
		LConvenio.setBounds(361, 75, 75, 14);
		painel.add(LConvenio);
		
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
		TPh.setEditable(false);
		TPh.setDocument(new CampoMaiusculoUtil());
		TPh.setColumns(10);
		TPh.setBounds(80, 148, 140, 23);
		painel.add(TPh);
		
		Thco2 = new JTextField();
		Thco2.setEditable(false);
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
		TO2sat.setEditable(false);
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
		TNa.setEditable(false);
		TNa.setDocument(new CampoMaiusculoUtil());
		TNa.setColumns(10);
		TNa.setBounds(275, 208, 140, 23);
		painel.add(TNa);
		
		TCo2 = new JTextField();
		TCo2.setEditable(false);
		TCo2.setDocument(new CampoMaiusculoUtil());
		TCo2.setColumns(10);
		TCo2.setBounds(275, 178, 140, 23);
		painel.add(TCo2);
		
		TPo2 = new JTextField();
		TPo2.setEditable(false);
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
		TK.setEditable(false);
		TK.setDocument(new CampoMaiusculoUtil());
		TK.setColumns(10);
		TK.setBounds(490, 208, 140, 23);
		painel.add(TK);
		
		TBe = new JTextField();
		TBe.setEditable(false);
		TBe.setDocument(new CampoMaiusculoUtil());
		TBe.setColumns(10);
		TBe.setBounds(490, 178, 140, 23);
		painel.add(TBe);
		
		TPco2 = new JTextField();
		TPco2.setEditable(false);
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
		
		BExcluir = new JButton();
		BExcluir.setBackground(new Color(255, 204, 153));
		BExcluir.setText("EXCLUIR");
		BExcluir.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BExcluir.setBounds(555, 248, 75, 22);
		painel.add(BExcluir);
		
		BCancelar = new JButton();
		BCancelar.setText("Cancelar");
		BCancelar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BCancelar.setBounds(471, 248, 75, 22);
		painel.add(BCancelar);
		
		JLabel LData = new JLabel("Data:");
		LData.setHorizontalAlignment(SwingConstants.RIGHT);
		LData.setFont(new Font("Dialog", Font.BOLD, 13));
		LData.setBounds(370, 45, 66, 14);
		painel.add(LData);
		
		LNatureza = new JLabel("Nat. Exame:");
		LNatureza.setHorizontalAlignment(SwingConstants.RIGHT);
		LNatureza.setFont(new Font("Dialog", Font.BOLD, 13));
		LNatureza.setBounds(230, 110, 85, 14);
		painel.add(LNatureza);
		
		JLabel LHora = new JLabel("Hora:");
		LHora.setHorizontalAlignment(SwingConstants.RIGHT);
		LHora.setFont(new Font("Dialog", Font.BOLD, 13));
		LHora.setBounds(446, 110, 50, 14);
		painel.add(LHora);
		
		THospital = new JTextField();
		THospital.setEditable(false);
		THospital.setColumns(10);
		THospital.setBounds(80, 40, 265, 23);
		painel.add(THospital);
		
		TMedico = new JTextField();
		TMedico.setEditable(false);
		TMedico.setColumns(10);
		TMedico.setBounds(80, 108, 140, 23);
		painel.add(TMedico);
		
		TNatureza = new JTextField();
		TNatureza.setEditable(false);
		TNatureza.setColumns(10);
		TNatureza.setBounds(318, 108, 124, 23);
		painel.add(TNatureza);
		
		THora = new JTextField();
		THora.setEditable(false);
		THora.setColumns(10);
		THora.setBounds(504, 108, 124, 23);
		painel.add(THora);
		
		TConvenio = new JTextField();
		TConvenio.setEditable(false);
		TConvenio.setColumns(10);
		TConvenio.setBounds(446, 71, 184, 23);
		painel.add(TConvenio);
		
		TData = new JTextField();
		TData.setEditable(false);
		TData.setColumns(10);
		TData.setBounds(446, 41, 184, 23);
		painel.add(TData);

	}
	
	public JButton getBCancelar() {
		return BCancelar;
	}
	
	public void setBCancelar(JButton bCancelar) {
		BCancelar = bCancelar;
	}
	
	public JButton getBExcluir() {
		return BExcluir;
	}
	
	public void setBExcluir(JButton bExcluir) {
		BExcluir = bExcluir;
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

	public JTextField getTHospital() {
		return THospital;
	}

	public void setTHospital(JTextField tHospital) {
		THospital = tHospital;
	}

	public JTextField getTMedico() {
		return TMedico;
	}

	public void setTMedico(JTextField tMedico) {
		TMedico = tMedico;
	}

	public JTextField getTNatureza() {
		return TNatureza;
	}

	public void setTNatureza(JTextField tNatureza) {
		TNatureza = tNatureza;
	}

	public JTextField getTHora() {
		return THora;
	}

	public void setTHora(JTextField tHora) {
		THora = tHora;
	}

	public JTextField getTConvenio() {
		return TConvenio;
	}

	public void setTConvenio(JTextField tConvenio) {
		TConvenio = tConvenio;
	}

	public JTextField getTData() {
		return TData;
	}

	public void setTData(JTextField tData) {
		TData = tData;
	}
	
	
}
