package br.com.gasomed.tabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.gasomed.modelo.Usuario;

public class UsuarioTabela extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	
	private String[] colunas = new String[] { "ID", "LOGIN", "CPF" };
	private List<Usuario> linhas;
	private static final int CODIGO = 0;
    private static final int LOGIN = 1;
    private static final int CPF = 2;
    
    public UsuarioTabela() {
        linhas = new ArrayList<Usuario>();
    }
     
    public UsuarioTabela(List<Usuario> usuario) {
        linhas = new ArrayList<Usuario>(usuario);
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
        case LOGIN:
            return String.class;
        case CPF:
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
		Usuario dados = linhas.get(rowIndex);
 
        switch (columnIndex) {
        case CODIGO:
            return dados.getId();
        case LOGIN:
            return dados.getLogin();
        case CPF:
            return dados.getCpf();
        default: 
            throw new IndexOutOfBoundsException("Coluna fora dos limites");
        }
    }
	
	public void AdicionaUsuario(Usuario usuario) {
	    linhas.add(usuario);
	    int ultimoIndice = getRowCount() - 1;
	    fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}
	 
	public void AdicionaListaDeUsuario(List<Usuario> usuario) {
	    int indice = getRowCount();
	    linhas.addAll(usuario);
	    fireTableRowsInserted(indice, indice + usuario.size());
	}

	public void Limpar() {
	    linhas.clear();
	    fireTableDataChanged();
	}
	
	@Override
    public void setValueAt(Object valor, int linha, int coluna) {
		Usuario dados = linhas.get(linha);
 
        switch (coluna) {
        case CODIGO:
        	dados.setId((Long) valor);
            break;
        case LOGIN:
        	dados.setLogin((String) valor);
            break;
        case CPF:
        	dados.setCpf((String) valor);
            break;
        default:
            throw new IndexOutOfBoundsException("Coluna fora dos limites");
        }
         
        fireTableCellUpdated(linha, coluna); 
    }
    
}