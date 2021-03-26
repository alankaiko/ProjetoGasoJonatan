package br.com.gasomed.janela;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.gasomed.listener.LoginListener;
import br.com.gasomed.util.CampoMaiusculoUtil;
import javax.swing.JPasswordField;

public class LoginDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private final JPanel painel = new JPanel();
	private JTextField TLogin;
	private JLabel LLogin;
	private JButton BVoltar, BLogin;
	private Long codigo;

	@SuppressWarnings("unused")
	private LoginListener listener;
	private JPasswordField TSenha;

	public LoginDialog(Long codigo) {
		this.MontarDados();
		this.MontarComponentes();
		this.codigo = codigo;

		this.listener = new LoginListener(this);
	}

	private void MontarDados() {
		setBounds(100, 100, 291, 195);
		setTitle("CADASTRO DE CONVENIO");
		getContentPane().setLayout(new BorderLayout());
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(painel, BorderLayout.CENTER);
		setModal(true);
		setResizable(false);
		painel.setLayout(null);
	}

	private void MontarComponentes() {
		TLogin = new JTextField();
		TLogin.setDocument(new CampoMaiusculoUtil());
		TLogin.setBounds(10, 36, 255, 23);
		painel.add(TLogin);
		TLogin.setColumns(10);

		LLogin = new JLabel("Login:");
		LLogin.setFont(new Font("Dialog", Font.BOLD, 13));
		LLogin.setBounds(10, 15, 46, 14);
		painel.add(LLogin);

		BVoltar = new JButton();
		BVoltar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BVoltar.setText("Voltar");
		BVoltar.setBounds(54, 125, 75, 22);
		painel.add(BVoltar);

		BLogin = new JButton();
		BLogin.setFont(new Font("Tahoma", Font.PLAIN, 10));
		BLogin.setText("Login");
		BLogin.setBounds(139, 125, 75, 22);
		painel.add(BLogin);

		JLabel LSenha = new JLabel("Senha:");
		LSenha.setFont(new Font("Dialog", Font.BOLD, 13));
		LSenha.setBounds(10, 70, 46, 14);
		painel.add(LSenha);
		
		TSenha = new JPasswordField();
		TSenha.setBounds(10, 95, 255, 23);
		painel.add(TSenha);
	}

	public JTextField getTLogin() {
		return TLogin;
	}

	public void setTLogin(JTextField tLogin) {
		TLogin = tLogin;
	}

	public JButton getBVoltar() {
		return BVoltar;
	}

	public void setBVoltar(JButton bVoltar) {
		BVoltar = bVoltar;
	}

	public JButton getBLogin() {
		return BLogin;
	}

	public void setBLogin(JButton bLogin) {
		BLogin = bLogin;
	}

	public JPasswordField getTSenha() {
		return TSenha;
	}
	
	public void setTSenha(JPasswordField tSenha) {
		TSenha = tSenha;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	public JPanel getPainel() {
		return painel;
	}
}
