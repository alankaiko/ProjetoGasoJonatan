package br.com.gasomed.janela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.gasomed.listener.BarraStatusListener;
import br.com.gasomed.listener.TelaPrincipalListener;

public class TelaPrincipalJanela extends JFrame {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private BarraStatusListener barra;
	
	@SuppressWarnings("unused")
	private TelaPrincipalListener listener;
	
	private JPanel painelcentral, rodape;
	private JLabel LStatusHora, LStatusData, LAdministracao;
	private JMenuBar menu;
	private JMenu cadastromenu, relatoriomenu;
	private JMenuItem geralrelatorio, pacienterelatorio, atendimentoitem, profissionalitem, hopitalitem, convenioitem, usuarioitem;

	public TelaPrincipalJanela() {
		this.MontarTelaPadrao();
		this.CriarComponentes();
		
		this.barra = new BarraStatusListener(this);
		this.listener = new TelaPrincipalListener(this);
	}
	
	private void MontarTelaPadrao() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipalJanela.class.getResource("/imagens/icone.ico")));
		this.setTitle("GMeD");
		this.setBounds(0, 0, 1130, 685);
		this.setLocationRelativeTo(null);
		setResizable(false);
	}
	
	@SuppressWarnings("serial")
	private void CriarComponentes() {
		this.menu = new JMenuBar();
		setJMenuBar(menu);
		
		this.cadastromenu = new JMenu("CADASTRO");
		this.cadastromenu.setFont(new Font("Dialog", Font.PLAIN, 13));
		this.menu.add(cadastromenu);
		
		this.atendimentoitem = new JMenuItem("Atendimento");
		this.atendimentoitem.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.cadastromenu.add(atendimentoitem);
		
		this.profissionalitem = new JMenuItem("Profissional");
		this.profissionalitem.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.cadastromenu.add(profissionalitem);
		
		this.convenioitem = new JMenuItem("Convenio");
		this.convenioitem.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.cadastromenu.add(convenioitem);
		
		this.hopitalitem = new JMenuItem("Hospital");
		this.hopitalitem.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.cadastromenu.add(hopitalitem);
		
		this.usuarioitem = new JMenuItem("Usuário");
		this.usuarioitem.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.cadastromenu.add(usuarioitem);
		
		this.relatoriomenu = new JMenu("RELATORIO");
		this.relatoriomenu.setFont(new Font("Dialog", Font.PLAIN, 13));
		this.menu.add(relatoriomenu);
		
		this.geralrelatorio = new JMenuItem("Geral");
		this.geralrelatorio.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.relatoriomenu.add(geralrelatorio);
		
		this.pacienterelatorio = new JMenuItem("Paciente");
		pacienterelatorio.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.relatoriomenu.add(pacienterelatorio);
		
		final Image fundoLogin = new ImageIcon(getClass().getResource("/imagens/tela.jpg")).getImage();
		this.painelcentral = new JPanel(new BorderLayout()) {
            @Override public void paintComponent(Graphics g) {
                g.drawImage(fundoLogin, 0, 0, null);
            }
        };
		painelcentral.setBackground(Color.WHITE);
		painelcentral.setBounds(0, 0, 1130, 685);
		this.painelcentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelcentral);
		painelcentral.setLayout(null);
		
		this.rodape = new JPanel();
		rodape.setBounds(new Rectangle(0, 593, 1130, 29));
		this.rodape.setBackground(Color.WHITE);
		this.painelcentral.add(rodape);
		rodape.setLayout(null);
		
		this.LAdministracao = new JLabel();
		LAdministracao.setBounds(10, 5, 300, 20);
		this.LAdministracao.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.rodape.add(LAdministracao);
		
		this.LStatusData = new JLabel();
		LStatusData.setBounds(710, 5, 300, 20);
		this.LStatusData.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.rodape.add(LStatusData);
		
		this.LStatusHora = new JLabel();
		LStatusHora.setBounds(1010, 5, 100, 20);
		this.LStatusHora.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.rodape.add(LStatusHora);
	}
	

	public JLabel getLStatusHora() {
		return LStatusHora;
	}

	public void setLStatusHora(JLabel lStatusHora) {
		LStatusHora = lStatusHora;
	}

	public JLabel getLStatusData() {
		return LStatusData;
	}

	public void setLStatusData(JLabel lStatusData) {
		LStatusData = lStatusData;
	}

	public JLabel getLAdministracao() {
		return LAdministracao;
	}

	public void setLAdministracao(JLabel lAdministracao) {
		LAdministracao = lAdministracao;
	}

	public JMenu getCadastromenu() {
		return cadastromenu;
	}

	public void setCadastromenu(JMenu cadastromenu) {
		this.cadastromenu = cadastromenu;
	}

	public JMenuItem getGeralrelatorio() {
		return geralrelatorio;
	}

	public void setGeralrelatorio(JMenuItem geralrelatorio) {
		this.geralrelatorio = geralrelatorio;
	}

	public JMenuItem getAtendimentoitem() {
		return atendimentoitem;
	}

	public void setAtendimentoitem(JMenuItem atendimentoitem) {
		this.atendimentoitem = atendimentoitem;
	}

	public JMenuItem getHopitalitem() {
		return hopitalitem;
	}

	public void setHopitalitem(JMenuItem hopitalitem) {
		this.hopitalitem = hopitalitem;
	}

	public JMenuItem getConvenioitem() {
		return convenioitem;
	}

	public void setConvenioitem(JMenuItem convenioitem) {
		this.convenioitem = convenioitem;
	}
	
	public JMenuItem getProfissionalitem() {
		return profissionalitem;
	}
	
	public void setProfissionalitem(JMenuItem profissionalitem) {
		this.profissionalitem = profissionalitem;
	}
	
	public JMenuItem getPacienterelatorio() {
		return pacienterelatorio;
	}
	
	public void setPacienterelatorio(JMenuItem pacienterelatorio) {
		this.pacienterelatorio = pacienterelatorio;
	}
	
	public JPanel getPainelcentral() {
		return painelcentral;
	}
	
	public void setPainelcentral(JPanel painelcentral) {
		this.painelcentral = painelcentral;
	}
	
	public JMenuItem getUsuarioitem() {
		return usuarioitem;
	}
	
	public void setUsuarioitem(JMenuItem usuarioitem) {
		this.usuarioitem = usuarioitem;
	}
}
