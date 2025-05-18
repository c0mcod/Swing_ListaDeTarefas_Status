package br.com.lista.table;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.lista.dao.ListaDAO;
import br.com.lista.model.ListadeAtividade;

public class TableMethod {
	static String[] colunas = { "ID", "Titulo", "Descrição", "Data", "Status" };
	static DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
	static JTable tabela = new JTable(modelo);
	public static JScrollPane scroll = new JScrollPane(tabela);
	
	public void atualizarTable() throws SQLException {
		ListaDAO dao1 = new ListaDAO();
		List<ListadeAtividade> lista = dao1.getLista();
		modelo.setRowCount(0);

		for (ListadeAtividade l : lista) {
			Object[] row = { l.getId(), l.getTitulo(), l.getDescricao(), l.getData_criacao(), l.getStatus() };
			modelo.addRow(row);
		}
	}
}
