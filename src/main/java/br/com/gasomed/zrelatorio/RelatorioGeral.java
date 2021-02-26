package br.com.gasomed.zrelatorio;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.gasomed.repository.filtro.AtendimentoFiltro;
import br.com.gasomed.util.ConexaoFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class RelatorioGeral {

	public void RelatorioPorPessoa(AtendimentoFiltro filtro) throws Exception {		
		String caminho = "C:\\temp";
		this.CriarPasta(caminho);
		Connection conexao = ConexaoFactory.RetornaConexao();
		Map<String, Object> parametros = new HashMap<>();	
		parametros.put("dataini", filtro.getDatainicial());
		parametros.put("datafini", filtro.getDatafinal());	
		parametros.put("hosp",filtro.getHospital() + "");
		parametros.put("conv", filtro.getConvenio() + "");
		parametros.put("procedim", filtro.getProcedimento() + "");
		parametros.put("medic", filtro.getMedico() + "");	
		parametros.put("SUBREPORT_DIR", "jaspter//");
		
		InputStream inputStream = this.getClass().getResourceAsStream("/jaspter/porhospital.jasper");	
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

	
	public String TransformandoEmString(Date data){
		String dat= null;
		
		if(data != null){
			Calendar calen = Calendar.getInstance();
			calen.setTime(data);
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
			dat = df.format(calen.getTime());
			
		}
		
		return dat;
	}
}
