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

import br.com.gasomed.janela.UsuarioDialog;
import br.com.gasomed.modelo.Usuario;
import br.com.gasomed.service.UsuarioService;
import br.com.gasomed.tabela.UsuarioTabela;
import br.com.gasomed.util.MensagemPainelUtil;

public class UsuarioListener implements ActionListener {
	private UsuarioDialog tela;
	private UsuarioTabela tabela;
	private Usuario usuario;

	public UsuarioListener(UsuarioDialog tela) {
		this.tela = tela;
		this.usuario = new Usuario();
		this.AdicionaListener();
		this.TabelaDeUsuario();
		this.UsandoTAB();
		this.TeclaEsc();
	}

	private void AdicionaListener() {
		this.tela.getBGravar().addActionListener(this);
		this.tela.getBSair().addActionListener(this);
		this.tela.getBEditar().addActionListener(this);
		this.tela.getBExcluir().addActionListener(this);
	}

	private void TabelaDeUsuario() {
		UsuarioService service = new UsuarioService();
		tabela = new UsuarioTabela(service.ListandoUsuario());
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
		if (evento.getSource().equals(this.tela.getBGravar()) && this.ValidandoField())
			this.AdicionarValor();
		
		else if (evento.getSource().equals(this.tela.getBEditar()))
			this.EditarValor(this.SelecionaLinha());
		
		else if (evento.getSource().equals(this.tela.getBExcluir()))
			this.ExcluirValor(this.SelecionaLinha());
		else if (evento.getSource().equals(this.tela.getBSair()))
			this.tela.dispose();
		else
			MensagemPainelUtil.Erro("Preencha os campos");
		
		this.TabelaDeUsuario();
	}
	
	private void AdicionarValor() {
		UsuarioService service = new UsuarioService();
		Usuario confere = service.VerificandoCampos(this.tela.getTLogin().getText(), this.tela.getTCpf().getText());
		
		if(confere == null) {
			this.usuario.setLogin(this.tela.getTLogin().getText());
			this.usuario.setCpf(this.tela.getTCpf().getText());
			this.usuario.setSenha(this.tela.getTSenha().getText());
			
			service.Salvar(this.usuario);
			this.tela.getTLogin().setText("");
			this.usuario = new Usuario();
		}else {
			MensagemPainelUtil.Erro("USUARIO JA CADASTRADO");
		}
	}
	
	private void EditarValor(Long id) {
		UsuarioService service = new UsuarioService();
		this.usuario = service.BuscandoId(id);
		this.tela.getTLogin().setText(this.usuario.getLogin());
		this.tela.getTSenha().setText(this.usuario.getSenha());
		this.tela.getTCpf().setText(this.usuario.getCpf());
	}
	
	private void ExcluirValor(Long id) {
		UsuarioService service = new UsuarioService();
		this.usuario = service.BuscandoId(id);
		int valor = JOptionPane.showConfirmDialog(null, "Deseja Excluir " + this.usuario.getLogin() + "?");

		if(valor == 0)
			service.Remover(usuario);
		else
			this.usuario = new Usuario();
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
		return !this.tela.getTLogin().getText().isEmpty();
	}

}
