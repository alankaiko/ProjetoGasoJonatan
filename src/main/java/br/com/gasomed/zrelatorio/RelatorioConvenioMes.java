package br.com.gasomed.zrelatorio;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import br.com.gasomed.repository.filtro.AtendimentoFiltro;
import br.com.gasomed.util.ConexaoFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class RelatorioConvenioMes {

	public void RelatorioPorPessoa(AtendimentoFiltro filtro) throws Exception {		
		String caminho = "C:\\temp";
		this.CriarPasta(caminho);
		Connection conexao = ConexaoFactory.RetornaConexao();
		
		Map<String, Object> parametros = new HashMap<>();	
		parametros.put("convenio", filtro.getConvenio());
		parametros.put("hospital", filtro.getHospital());
		parametros.put("datainicial", filtro.getDatainicial());
		parametros.put("procedimento", filtro.getProcedimento());
		parametros.put("datafinal", filtro.getDatafinal());		
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/conveniomes.jasper");	
		JasperPrint impressao = null;
		
        try {
            impressao = JasperFillManager.fillReport(inputStream, parametros, conexao);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
        
        String nomefinal = caminho + "\\"  + Calendar.getInstance().getTimeInMillis() + ".pdf";
        JasperExportManager.exportReportToPdfFile(impressao, nomefinal);	
        
        Desktop desktop = Desktop.getDesktop();  
		desktop.open(new File(nomefinal));
	}
	
	private void CriarPasta(String caminho) throws IOException {
		File file = new File(caminho);
		
		if(!file.exists()) {
			file.mkdir();
		}
	}
}
