package br.com.gasomed.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import br.com.gasomed.janela.ExcluirAtendimentoDialog;
import br.com.gasomed.janela.LoginDialog;
import br.com.gasomed.modelo.Usuario;
import br.com.gasomed.service.UsuarioService;
import br.com.gasomed.util.MensagemPainelUtil;

public class LoginListener implements ActionListener {
	private LoginDialog tela;

	public LoginListener(LoginDialog tela) {
		this.tela = tela;
		this.AdicionaListener();
		this.UsandoTAB();
		this.TeclaEsc();
	}

	private void AdicionaListener() {
		this.tela.getBVoltar().addActionListener(this);
		this.tela.getBLogin().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(this.tela.getBLogin()))
			this.Logar();
		
		else if (evento.getSource().equals(this.tela.getBVoltar()))
			this.tela.dispose();		
		
	}
	
	private void Logar() {
		UsuarioService service = new UsuarioService();
		Usuario usuario = service.Autentica(this.tela.getTLogin().getText(), this.tela.getTSenha().getText());
		
		if(usuario == null)
			MensagemPainelUtil.Erro("Usuario ou Senha invalidos");
		else {
			this.tela.dispose();
			this.AbreTelaExclusao(usuario);
		}
			
	}
	
	private void AbreTelaExclusao(Usuario usuario) {
		ExcluirAtendimentoDialog excluir = new ExcluirAtendimentoDialog(this.tela.getCodigo(), usuario);
		excluir.setLocationRelativeTo(this.tela.getPainel());
		excluir.setVisible(true);
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
		this.tela.getRootPane().setDefaultButton(this.tela.getBLogin());
		this.tela.getBLogin().addKeyListener(new KeyAdapter() {  
            public void keyPressed(KeyEvent e) {  
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
                	tela.getBLogin().doClick();
                }  
            }  
        });
		
		this.tela.getBVoltar().addKeyListener(new KeyAdapter() {  
            public void keyPressed(KeyEvent e) {  
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
                	tela.getBVoltar().doClick();  
                }  
            }  
        });
	}
	
}
