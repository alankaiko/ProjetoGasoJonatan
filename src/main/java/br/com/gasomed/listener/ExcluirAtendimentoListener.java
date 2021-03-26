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

import br.com.gasomed.janela.ExcluirAtendimentoDialog;
import br.com.gasomed.modelo.Atendimento;
import br.com.gasomed.modelo.AtendimentoExcluido;
import br.com.gasomed.modelo.Usuario;
import br.com.gasomed.service.AtendimentoExcluidoService;
import br.com.gasomed.service.AtendimentoService;
import br.com.gasomed.util.MensagemPainelUtil;

public class ExcluirAtendimentoListener implements ActionListener {
	private ExcluirAtendimentoDialog tela;
	private Atendimento atendimento;
	private Usuario usuario;

	public ExcluirAtendimentoListener(ExcluirAtendimentoDialog tela) {
		this.tela = tela;
		this.AdicionaListener();
		this.UsandoTAB();
		this.TeclaEsc();
	}

	private void AdicionaListener() {
		this.tela.getBCancelar().addActionListener(this);
		this.tela.getBExcluir().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(this.tela.getBExcluir()))
			this.ExcluirValor();
		else if (evento.getSource().equals(this.tela.getBCancelar()))
			this.tela.dispose();
		else 
			MensagemPainelUtil.Erro("Preencha todos os campos!!");
	}

	public void EditarValor(Long id, Usuario usuario) {
		this.usuario = usuario;
		AtendimentoService service = new AtendimentoService();
		this.atendimento = service.BuscandoId(id);
		
		this.tela.getTNome().setText(this.atendimento.getNome());
		this.tela.getTHospital().setText(this.atendimento.getHospital());
		this.tela.getTLeito().setText(this.atendimento.getLeito());
		this.tela.getTData().setText(this.atendimento.getData() + "");
		this.tela.getTHora().setText(this.atendimento.getHora() + "");
		this.tela.getTMedico().setText(this.atendimento.getMedico());
		this.tela.getTConvenio().setText(this.atendimento.getConvenio());
		this.tela.getTNatureza().setText(this.atendimento.getProcedimento());
		
		this.tela.getTPh().setText(this.atendimento.getPh());
		this.tela.getThco2().setText(this.atendimento.getHco());
		this.tela.getTO2sat().setText(this.atendimento.getO2sat());
		
		this.tela.getTPo2().setText(this.atendimento.getPo());
		this.tela.getTCo2().setText(this.atendimento.getCo2total());
		this.tela.getTNa().setText(this.atendimento.getNa());
		
		this.tela.getTPco2().setText(this.atendimento.getPco());
		this.tela.getTBe().setText(this.atendimento.getBe());
		this.tela.getTK().setText(this.atendimento.getK());
	}
	
	
	private void ExcluirValor() {
		int valor = JOptionPane.showConfirmDialog(null, "Deseja Excluir esse registro?");

		if(valor == 0) {
			this.ClonarParaAtendimentoExcluido();
			AtendimentoService service = new AtendimentoService();
			service.Remover(this.atendimento);
		}
		
		this.tela.dispose();
	}
	
	private void ClonarParaAtendimentoExcluido() {
		AtendimentoExcluido excluido = new AtendimentoExcluido();
		excluido.setNome(this.atendimento.getNome());
		excluido.setHospital(this.atendimento.getHospital());
		excluido.setMedico(this.atendimento.getMedico());
		excluido.setConvenio(this.atendimento.getConvenio());
		excluido.setLeito(this.atendimento.getLeito());
		excluido.setData(this.atendimento.getData());
		excluido.setProcedimento(this.atendimento.getProcedimento());
		excluido.setPh(this.atendimento.getPh());
		excluido.setPo(this.atendimento.getPo());
		excluido.setPco(this.atendimento.getPco());
		excluido.setHco(this.atendimento.getHco());
		excluido.setCo2total(this.atendimento.getCo2total());
		excluido.setBe(this.atendimento.getBe());
		excluido.setO2sat(this.atendimento.getO2sat());
		excluido.setNa(this.atendimento.getNa());
		excluido.setK(this.atendimento.getK());
		excluido.setFile(this.atendimento.getFile());
		
		excluido.setLogin(this.usuario.getLogin());
		excluido.setCpf(this.usuario.getSenha());
		excluido.setDataexclusao(new java.sql.Date(new java.util.Date().getTime()));
		excluido.setHoraexclusao(new java.sql.Time(new java.util.Date().getTime()));
		
		AtendimentoExcluidoService service = new AtendimentoExcluidoService();
		service.Salvar(excluido);
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
		this.tela.getBExcluir().addKeyListener(new KeyAdapter() {  
            public void keyPressed(KeyEvent e) {  
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
                	tela.getBExcluir().doClick();
                }  
            }  
        });
		
		this.tela.getBCancelar().addKeyListener(new KeyAdapter() {  
            public void keyPressed(KeyEvent e) {  
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
                	tela.getBCancelar().doClick();  
                }  
            }  
        });
	}

}
