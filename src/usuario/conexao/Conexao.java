package usuario.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "mayara2608";
	private static final String URL = "jdbc:mysql://localhost";

	public static Connection createConnectionToMySql() throws SQLException {

		
		Connection conn = DriverManager.getConnection(URL + "?user="+USERNAME+"&password="+PASSWORD);
		
		Statement stmt = conn.createStatement();	
		
		stmt.execute("CREATE DATABASE IF NOT EXISTS desafiofinalctt");
		
		stmt = conn.createStatement();
		
		stmt.execute("USE desafiofinalctt");
		
		stmt = conn.createStatement();
		
		stmt.execute("CREATE TABLE IF NOT EXISTS usuarios(ID INT AUTO_INCREMENT PRIMARY KEY, Nome VARCHAR(60) NOT NULL, Email VARCHAR(130) NOT NULL, Senha VARCHAR(50) NOT NULL, Data_Cadastro DATETIME)");
		
		
		return conn;
	}
	
	public static void main(String[] args) throws SQLException {
		try {
		createConnectionToMySql();
	}catch (Exception e) {
		e.printStackTrace();
	}

}
}
