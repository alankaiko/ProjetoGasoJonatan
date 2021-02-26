package br.com.gasomed.zrelatorio;

import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import br.com.gasomed.util.ConexaoFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class RelatorioAtendimento {

	public void RelatorioPorAtendimento(Long codigo, String caminho) throws Exception {				
		File arquivo = new File("atd\\" + caminho + Calendar.getInstance().getTimeInMillis() + ".pdf");
		
		Connection conexao = ConexaoFactory.RetornaConexao();
		Map<String, Object> parametros = new HashMap<>();	
		parametros.put("codigo", codigo);
		
		InputStream inputStream = this.getClass().getResourceAsStream("/jaspter/atendimento.jasper");	
		JasperPrint impressao = null;
		
        try {
            impressao = JasperFillManager.fillReport(inputStream, parametros, conexao);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
        
        JasperExportManager.exportReportToPdfFile(impressao, arquivo.getPath());	
        Desktop desktop = Desktop.getDesktop();  
		desktop.open(arquivo);
	}
}
