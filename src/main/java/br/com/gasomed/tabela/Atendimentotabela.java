package br.com.gasomed.tabela;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.gasomed.modelo.Atendimento;

public class Atendimentotabela extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	
	private String[] colunas = new String[] { "ID", "NOME", "HOSPITAL", "MEDICO", "CONVENIO", "LEITO", "DATA", "HORA" };
	private List<Atendimento> linhas;
	private static final int ID = 0;
	private static final int NOME = 1;
	private static final int HOSPITAL = 2;
	private static final int MEDICO = 3;
	private static final int CONVENIO = 4;
	private static final int LEITO = 5;
	private static final int DATA = 6;
	private static final int HORA = 7;
    
    public Atendimentotabela() {
        linhas = new ArrayList<Atendimento>();
    }
     
    public Atendimentotabela(List<Atendimento> atendimento) {
        linhas = new ArrayList<Atendimento>(atendimento);
    }
    
    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    };
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
        case ID:
            return Long.class;
        case NOME:
            return String.class;
        case HOSPITAL:
            return String.class;
        case MEDICO:
            return String.class;
        case CONVENIO:
            return String.class;
        case LEITO:
            return String.class;
        case DATA:
            return Date.class;
        case HORA:
            return Time.class;
        default:
            throw new IndexOutOfBoundsException("coluna fora dos limites");
        }
    }
	
    @Override
	public int getRowCount() {
	    return linhas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Atendimento dados = linhas.get(rowIndex);
 
        switch (columnIndex) {
        case ID:
        	return dados.getId();
        case NOME:
            return dados.getNome();
        case HOSPITAL:
        	return dados.getHospital();
        case MEDICO:
        	return dados.getMedico();
        case CONVENIO:
        	return dados.getConvenio();
        case LEITO:
        	return dados.getLeito();
        case DATA:
        	return dados.getData();
        case HORA:
        	return dados.getHora();
        default: 
            throw new IndexOutOfBoundsException("Coluna fora dos limites");
        }
    }
	
	public void AdicionaConvenio(Atendimento atendimento) {
	    linhas.add(atendimento);
	    int ultimoIndice = getRowCount() - 1;
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	public void AdicionaListaDeAtendimentos(List<Atendimento> atendimento) {
	    int indice = getRowCount();
	    linhas.addAll(atendimento);
	    fireTableRowsInserted(indice, indice + atendimento.size());
	}

	public void Limpar() {
	    linhas.clear();
	    fireTableDataChanged();
	}
	
	@Override
    public void setValueAt(Object valor, int linha, int coluna) {
		Atendimento dados = linhas.get(linha);
		
        switch (coluna) {
        case ID:
        	dados.setId((Long) valor);
            break;
        case NOME:
        	dados.setNome((String) valor);
            break;
        case HOSPITAL:
        	dados.setHospital((String) valor);
            break;
        case MEDICO:
        	dados.setMedico((String) valor);
            break;
        case CONVENIO:
        	dados.setConvenio((String) valor);
            break;
        case LEITO:
        	dados.setLeito((String) valor);
            break;
        case DATA:
        	dados.setData((Date) valor);
            break;
        case HORA:
        	dados.setHora((Time) valor);
            break;
        default:
            throw new IndexOutOfBoundsException("Coluna fora dos limites");
        }
         
        fireTableCellUpdated(linha, coluna); 
    }
    
}