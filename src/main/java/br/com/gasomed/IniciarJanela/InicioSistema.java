package br.com.gasomed.IniciarJanela;

import java.awt.EventQueue;

import br.com.gasomed.janela.TelaPrincipalJanela;

public class InicioSistema {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalJanela frame = new TelaPrincipalJanela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
