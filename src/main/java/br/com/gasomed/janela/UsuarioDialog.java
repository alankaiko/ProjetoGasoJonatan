package br.com.gasomed.janela;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.gasomed.listener.UsuarioListener;
import br.com.gasomed.util.CampoMaiusculoUtil;
import br.com.gasomed.util.RetiraEspaco;

public class UsuarioDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private final JPanel painel = new JPanel();
	private JTextField TLogin, TCpf;
	private JLabel LCpf, LLogin, LSenha;
	private JPasswordField TSenha;
	private JScrollPane scrollpane;
	private JTable tabela;
	private JButton BSair, BGravar, BExcluir, BEditar;
	private RetiraEspaco retiraespaco;

	@SuppressWarnings("unused")
	private UsuarioListener listener;

	public UsuarioDialog() {
		this.MontarDados();
		this.MontarComponentes();

		this.listener = new UsuarioListener(this);
	}

	private void MontarDados() {
		setBounds(100, 100, 620, 380);
		setTitle("CADASTRO DE USUÁRIOS");
		getContentPane().setLayout(new BorderLayout());
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(painel, BorderLayout.CENTER);
		setModal(true);
		setResizable(false);
		painel.setLayout(null);
	}

	private void MontarComponentes() {
		LLogin = new JLabel("Login:");
		LLogin.setFont(new Font("Dialog", Font.BOLD, 13));
		LLogin.setBounds(10, 15, 46, 14);
		painel.add(LLogin);
		
		TLogin = new JTextField();
		TLogin.setDocument(new CampoMaiusculoUtil());
		TLogin.setBounds(60, 10, 248, 23);
		TLogin.addKeyListener(retiraespaco); 
		painel.add(TLogin);
		TLogin.setColumns(10);

		scrollpane = new JScrollPane();
		scrollpane.setBounds(10, 65, 584, 245);
		painel.add(scrollpane);
		this.tabela = new JTable();

		

		LCpf = new JLabel("CPF:");
		LCpf.setFont(new Font("Dialog", Font.BOLD, 13));
		LCpf.setBounds(10, 42, 46, 14);
		painel.add(LCpf);

		TCpf = new JTextField();
		TCpf.setColumns(10);
		TCpf.setBounds(60, 37, 534, 23);
		painel.add(TCpf);

		LSenha = new JLabel("Senha:");
		LSenha.setFont(new Font("Dialog", Font.BOLD, 13));
		LSenha.setBounds(318, 15, 46, 14);
		painel.add(LSenha);

		TSenha = new JPasswordField();
		TSenha.setBounds(367, 11, 226, 23);
		TSenha.addKeyListener(retiraespaco);
		painel.add(TSenha);
		
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

	public JTextField getTLogin() {
		return TLogin;
	}
	
	public void setTLogin(JTextField tLogin) {
		TLogin = tLogin;
	}

	public JTextField getTCpf() {
		return TCpf;
	}

	public void setTCpf(JTextField tCpf) {
		TCpf = tCpf;
	}

	public JPasswordField getTSenha() {
		return TSenha;
	}

	public void setTSenha(JPasswordField tSenha) {
		TSenha = tSenha;
	}

	public JScrollPane getScrollpane() {
		return scrollpane;
	}

	public void setScrollpane(JScrollPane scrollpane) {
		this.scrollpane = scrollpane;
	}

	public JTable getTabela() {
		return tabela;
	}

	public void setTabela(JTable tabela) {
		this.tabela = tabela;
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

	public JButton getBExcluir() {
		return BExcluir;
	}

	public void setBExcluir(JButton bExcluir) {
		BExcluir = bExcluir;
	}

	public JButton getBEditar() {
		return BEditar;
	}

	public void setBEditar(JButton bEditar) {
		BEditar = bEditar;
	}

}
