package br.com.gasomed.tabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.gasomed.modelo.Medico;

public class ProfissionalTabela extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	
	private String[] colunas = new String[] { "ID", "NOME", "CRM", "ESTADO" };
	private List<Medico> linhas;
	private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int CRM = 2;
    private static final int ESTADO = 3;
	 
    
    public ProfissionalTabela() {
        linhas = new ArrayList<Medico>();
    }
     
    public ProfissionalTabela(List<Medico> medico) {
        linhas = new ArrayList<Medico>(medico);
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
        case CRM:
            return String.class;
        case ESTADO:
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
		Medico dados = linhas.get(rowIndex);
 
        switch (columnIndex) {
        case CODIGO:
            return dados.getId();
        case NOME:
            return dados.getNome();
        case CRM:
            return dados.getCrm();
        case ESTADO:
            return dados.getEstado();
        default: 
            throw new IndexOutOfBoundsException("Coluna fora dos limites");
        }
    }
	
	public void AdicionaProfissional(Medico medico) {
	    linhas.add(medico);
	    int ultimoIndice = getRowCount() - 1;
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	public void AdicionaListaDeProfissional(List<Medico> medico) {
	    int indice = getRowCount();
	    linhas.addAll(medico);
	    fireTableRowsInserted(indice, indice + medico.size());
	}

	public void Limpar() {
	    linhas.clear();
	    fireTableDataChanged();
	}
	
	@Override
    public void setValueAt(Object valor, int linha, int coluna) {
		Medico dados = linhas.get(linha);
 
        switch (coluna) {
        case CODIGO:
        	dados.setId((Long) valor);
            break;
        case NOME:
        	dados.setNome((String) valor);
            break;
        case CRM:
        	dados.setCrm((String) valor);
        case ESTADO:
        	dados.setEstado((String) valor);
            break;
        default:
            throw new IndexOutOfBoundsException("Coluna fora dos limites");
        }
         
        fireTableCellUpdated(linha, coluna); 
    }    
}