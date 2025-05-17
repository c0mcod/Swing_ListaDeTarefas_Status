package br.com.lista.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1234";
	private static final String DATA_BASE_URL = "jdbc:mysql://localhost:3306/listadeatividade";

	public static Connection createConnectionToMySQL() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection(DATA_BASE_URL, USERNAME, PASSWORD);

		return connection;
	}

	public static void main(String[] args) throws Exception {

		Connection con = createConnectionToMySQL();

		if (con != null) {
			System.out.println("conexão obtida");
			con.close();
		}
	}

}
