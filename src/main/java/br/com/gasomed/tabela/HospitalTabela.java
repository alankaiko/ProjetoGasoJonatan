package br.com.gasomed.tabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.gasomed.modelo.Hospital;

public class HospitalTabela extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	
	private String[] colunas = new String[] { "ID", "NOME" };
	private List<Hospital> linhas;
	private static final int CODIGO = 0;
    private static final int NOME = 1;	 
    
    public HospitalTabela() {
        linhas = new ArrayList<Hospital>();
    }
     
    public HospitalTabela(List<Hospital> hospital) {
        linhas = new ArrayList<Hospital>(hospital);
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
        case CODIGO:
            return Long.class;
        case NOME:
            return String.class;
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
		Hospital dados = linhas.get(rowIndex);
 
        switch (columnIndex) {
        case CODIGO:
            return dados.getId();
        case NOME:
            return dados.getNome();
        default: 
            throw new IndexOutOfBoundsException("Coluna fora dos limites");
        }
    }
	
	public void AdicionaHospital(Hospital hospital) {
	    linhas.add(hospital);
	    int ultimoIndice = getRowCount() - 1;
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	public void AdicionaListaDeHospitais(List<Hospital> hospital) {
	    int indice = getRowCount();
	    linhas.addAll(hospital);
	    fireTableRowsInserted(indice, indice + hospital.size());
	}

	public void Limpar() {
	    linhas.clear();
	    fireTableDataChanged();
	}
	
	@Override
    public void setValueAt(Object valor, int linha, int coluna) {
		Hospital dados = linhas.get(linha);
 
        switch (coluna) {
        case CODIGO:
        	dados.setId((Long) valor);
            break;
        case NOME:
        	dados.setNome((String) valor);
            break;
        default:
            throw new IndexOutOfBoundsException("Coluna fora dos limites");
        }
         
        fireTableCellUpdated(linha, coluna); 
    }
    
}