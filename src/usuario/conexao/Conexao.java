package usuario.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "mayara2608";
	private static final String DATABASE_URL = "jdbc:mysql://localhost/desafiofinalctt";
	
	
	public static Connection createConnectionToMySql() throws SQLException{					
		
			Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			
			return connection;	
		
	}	
	
}
