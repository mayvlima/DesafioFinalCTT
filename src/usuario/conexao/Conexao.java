package usuario.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	private static final String URL = "";

	public static Connection createConnectionToMySql() throws SQLException {

		Connection conn = DriverManager.getConnection(URL + "?user=" + USERNAME + "&password=" + PASSWORD);

		Statement stmt = conn.createStatement();

		stmt.execute("CREATE DATABASE IF NOT EXISTS desafiofinalctt");

		stmt = conn.createStatement();

		stmt.execute("USE desafiofinalctt");

		stmt = conn.createStatement();

		stmt.execute("CREATE TABLE IF NOT EXISTS usuarios(ID INT AUTO_INCREMENT PRIMARY KEY, Nome VARCHAR(60) NOT NULL, Email VARCHAR(130) NOT NULL, Senha VARCHAR(50) NOT NULL, Data_Cadastro DATETIME)");

		return conn;
	}

	public static void main(String[] args) {
		try {
			createConnectionToMySql();
			System.out.println("Conexão realizada com sucesso!");
		} catch (SQLException e) {
			System.out.println("Não foi possivel realizar a conexão!");
			System.out.println("Verifique os dados digitados!");
			System.out.println("Erro: " + e);
		}

	}
}
