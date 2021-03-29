package br.com.gasomed.tabela;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.gasomed.modelo.AtendimentoExcluido;
import br.com.gasomed.util.ListasUtil;

public class AtendimentoExcTabela extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	
	private String[] colunas = new String[] { "ID", "NOME", "DATA", "HORA","LOGIN" ,"DT EXC", "HR EXC" };
	private List<AtendimentoExcluido> linhas;
	private static final int ID = 0;
	private static final int NOME = 1;
	private static final int DATACRIACAO = 2;
	private static final int HORACRIACAO = 3;
	private static final int LOGIN = 4;
	private static final int DATAEXCLUSAO = 5;
	private static final int HORAEXCLUSAO = 6;
    
    public AtendimentoExcTabela() {
        linhas = new ArrayList<AtendimentoExcluido>();
    }
     
    public AtendimentoExcTabela(List<AtendimentoExcluido> atendimento) {
        linhas = new ArrayList<AtendimentoExcluido>(atendimento);
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
        case DATACRIACAO:
            return String.class;
        case HORACRIACAO:
            return String.class;
        case LOGIN:
            return String.class;
        case DATAEXCLUSAO:
            return String.class;
        case HORAEXCLUSAO:
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
		AtendimentoExcluido dados = linhas.get(rowIndex);
 
        switch (columnIndex) {
        case ID:
        	return dados.getId();
        case NOME:
            return dados.getNome();
        case DATACRIACAO:
        	return dados.getData();
        case HORACRIACAO:
        	return dados.getHora();
        case LOGIN:
        	return dados.getLogin();
        case DATAEXCLUSAO:
        	return ListasUtil.TransformaDataEmString(dados.getDataexclusao());
        case HORAEXCLUSAO:
        	return ListasUtil.TransformaHoraEmString(dados.getHoraexclusao());
        default: 
            throw new IndexOutOfBoundsException("Coluna fora dos limites");
        }
    }
	
	public void AdicionaHOSPITAL(AtendimentoExcluido atendimento) {
	    linhas.add(atendimento);
	    int ultimoIndice = getRowCount() - 1;
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	public void AdicionaListaDeAtendimentos(List<AtendimentoExcluido> atendimento) {
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
		AtendimentoExcluido dados = linhas.get(linha);
		
        switch (coluna) {
        case ID:
        	dados.setId((Long) valor);
            break;
        case NOME:
        	dados.setNome((String) valor);
            break;
        case DATACRIACAO:
        	dados.setData((Date) valor);
            break;
        case HORACRIACAO:
        	dados.setHora((Time) valor);
            break;
        case LOGIN:
        	dados.setLogin((String) valor);
            break;
        case DATAEXCLUSAO:
        	dados.setDataexclusao((Date) valor);
            break;
        case HORAEXCLUSAO:
        	dados.setHoraexclusao((Time) valor);
            break;
        default:
            throw new IndexOutOfBoundsException("Coluna fora dos limites");
        }
         
        fireTableCellUpdated(linha, coluna); 
    }
    
}