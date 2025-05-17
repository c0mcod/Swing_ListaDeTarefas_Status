package br.com.lista.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;
import br.com.lista.connection.ConnectionFactory;
import br.com.lista.model.ListadeAtividade;

public class ListaDAO {
	public void inserirAtividade(ListadeAtividade lista) throws SQLException {
		String sql = "INSERT INTO atividade(titulo, descricao, data_criacao, status_atividade) VALUES (?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, lista.getTitulo());
			pstm.setString(2, lista.getDescricao());
			pstm.setDate(3, new Date(lista.getData_criacao().getTime()));
			pstm.setString(4, lista.getStatus());

			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (pstm != null) {
				pstm.close();
			}
		}
	}

	public List<ListadeAtividade> getLista() throws SQLException {
		String sql = "SELECT * FROM atividade";
		List<ListadeAtividade> lista = new ArrayList<ListadeAtividade>();

		Connection conn = null;
		PreparedStatement pstm = null;

		ResultSet rset = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			while (rset.next()) {
				ListadeAtividade list = new ListadeAtividade();
				list.setTitulo(rset.getString("id"));
				list.setDescricao(rset.getString("descricao"));
				list.setStatus(rset.getString("status_atividade"));
				list.setData_criacao(rset.getDate("data_criacao"));
				lista.add(list);
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (pstm != null) {
				pstm.close();
			}
			if (rset != null) {
				rset.close();
			}
		}
		return lista;
	}

	public void atualizarAtividade(ListadeAtividade lista) throws SQLException {
		String sql = "UPDATE atividade SET titulo = ?, descricao = ?, data_criacao = ?, status_atividade = ?"
				+ "WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, lista.getTitulo());
			pstm.setString(2, lista.getDescricao());
			pstm.setDate(3, new Date(lista.getData_criacao().getTime()));
			pstm.setString(4, lista.getStatus());

			pstm.setInt(5, lista.getId());

			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (pstm != null) {
				pstm.close();
			}
		}
	}

	public void deletarAtividade(int id) throws SQLException {
		String sql = "DELETE FROM atividade WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null) {
				conn.close();
			}
			if (pstm != null) {
				pstm.close();
			}
		}
	}
}
