package br.com.gasomed.zrelatorio;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import br.com.gasomed.modelo.Atendimento;
import br.com.gasomed.util.MensagemPainelUtil;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

public class GerandoRelatorio {

	public boolean CriarDocumento(Atendimento atendimento) throws Exception {
		try {
			File arquivofinal = new File("atd\\docs\\" + atendimento.getFile() + ".docx");
			FileOutputStream fos = new FileOutputStream(arquivofinal.getAbsoluteFile());
			System.out.println(arquivofinal.getAbsolutePath());
			XWPFDocument doc = new XWPFDocument(OPCPackage.open("modelo\\base.docx"));
			this.IterarDocumento(doc, atendimento);
			doc.write(fos);		
			return true;
		} catch (Exception e) {
			MensagemPainelUtil.Erro("Erro ao Criar documento!! " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	private void IterarDocumento(XWPFDocument documentopadrao, Atendimento atendimento) {
		for(XWPFParagraph paragrafo : documentopadrao.getParagraphs()) {
			for(XWPFRun texto : paragrafo.getRuns()) {
				String nome = texto.toString();
				
				if(nome.contains("[P_NAME]")) {
					nome = nome.replace("[P_NAME]", atendimento.getNome());
					texto.setText(nome, 0);
				}

				if(nome.contains("[DATA]")) {
					SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
					nome = nome.replace("[DATA]", formato.format(atendimento.getData()));
					texto.setText(nome, 0);		
				}

				if(nome.contains("[D_NAME]")) {
					nome = nome.replace("[D_NAME]", atendimento.getMedico());
					texto.setText(nome, 0);		
				}
				
				//if(nome.contains("[HORAs]")) {
				//	nome = nome.replace("[HORAs]", atendimento.getNome());
				//	texto.setText(nome, 0);
				//}
				
				if(nome.contains("[PH]")) {
					nome = nome.replace("[PH]", atendimento.getPh());
					texto.setText(nome, 0);
				}
				
				if(nome.contains("[PO]")) {
					nome = nome.replace("[PO]", atendimento.getPo());
					texto.setText(nome, 0);
				}
				
				if(nome.contains("[PCO]")) {
					nome = nome.replace("[PCO]", atendimento.getPco());
					texto.setText(nome, 0);
				}
				
				if(nome.contains("[HCO]")) {
					nome = nome.replace("[HCO]", atendimento.getHco());
					texto.setText(nome, 0);
				}
				
				if(nome.contains("[CO]")) {
					nome = nome.replace("[CO]", atendimento.getCo2total());
					texto.setText(nome, 0);
				}
				
				if(nome.contains("[BE]")) {
					nome = nome.replace("[BE]", atendimento.getBe());
					texto.setText(nome, 0);
				}
				
				if(nome.contains("[O2SAT]")) {
					nome = nome.replace("[O2SAT]", atendimento.getO2sat());
					texto.setText(nome, 0);
				}
				
				if(nome.contains("[NA]")) {
					nome = nome.replace("[NA]", atendimento.getNa());
					texto.setText(nome, 0);
				}
				
				if(nome.contains("[K]")) {
					nome = nome.replace("[K]", atendimento.getK());
					texto.setText(nome, 0);
				}
			}
		}		
	}
	
	public void CriarPdf(Atendimento atendimento) throws Exception{
		InputStream strem = new FileInputStream(new File("atd\\docs\\" + atendimento.getFile() + ".docx"));
		XWPFDocument d = new XWPFDocument(strem);
		PdfOptions pdf = PdfOptions.create();
		
		File arquivofinal = new File("atd\\" + atendimento.getFile() + ".pdf");
		OutputStream out = new FileOutputStream(arquivofinal.getAbsoluteFile());
		PdfConverter.getInstance().convert(d, out, pdf);
		out.close();
		
		Desktop desktop = Desktop.getDesktop();  
		desktop.open(arquivofinal.getAbsoluteFile()); 
	}
}