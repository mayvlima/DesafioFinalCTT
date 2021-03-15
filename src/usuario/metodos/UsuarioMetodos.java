package usuario.metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import usuario.conexao.Conexao;
import usuario.model.Usuario;

public class UsuarioMetodos {

	public boolean save(Usuario usuario) {
		
		String sql = "INSERT INTO usuarios(Nome, Email, Senha, Data_Cadastro) VALUES (?,?,?,?)";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn = Conexao.createConnectionToMySql();
			
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, usuario.getNome());
			pstm.setString(2, usuario.getEmail());
			pstm.setString(3, usuario.getSenha());
			pstm.setTimestamp(4, usuario.getDataCadastro());
			
			pstm.execute();
						
			System.out.println("\nUSUÁRIO SALVO COM SUCESSO!");
			
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			
			try {
				if(pstm != null) {
					pstm.close();
				}if(conn != null) {
					conn.close();
				}
			}catch (Exception e2) {
				e2.printStackTrace();
			}	
		}
	}
	
	
	public List<Usuario> getUsuarios(){
		
		String sql = "SELECT * FROM usuarios";
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rset = null;
		
		
		try {
			conn = Conexao.createConnectionToMySql();
			
			pstm = conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Usuario usuario = new Usuario();
				
				usuario.setId(rset.getInt("ID"));
				usuario.setNome(rset.getString("Nome"));
				usuario.setEmail(rset.getString("Email"));
				usuario.setSenha(rset.getString("Senha"));
				usuario.setDataCadastro(rset.getTimestamp("Data_Cadastro"));
				
				usuarios.add(usuario);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset != null) {
					rset.close();
				}if(pstm != null) {
					pstm.close();
				}if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return usuarios;
	}
	
	public boolean getUsuarioWithId(Usuario usuario) {		
		
		String sql = "SELECT * FROM usuarios WHERE ID = ?";	
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rset = null;		
		
		try {			
			
			conn = Conexao.createConnectionToMySql();
			
			pstm = conn.prepareStatement(sql);			
			pstm.setInt(1, usuario.getId());
			
			rset = pstm.executeQuery();
		
			
			if(rset.next()) {					
			
			usuario.setId(rset.getInt("ID"));
			usuario.setNome(rset.getString("Nome"));
			usuario.setEmail(rset.getString("Email"));
			usuario.setSenha(rset.getString("Senha"));
			usuario.setDataCadastro(rset.getTimestamp("Data_Cadastro"));		
			
			return true;
			}else {				
			return false;
			}
			
		
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		
		}finally {
			try {
				if(rset != null) {
					rset.close();
				}if(pstm != null) {
					pstm.close();
				}if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
	}
	
	public int update(Usuario usuario) {
		
		String sql = "UPDATE usuarios SET Nome = ?, Email = ?, Senha = ? WHERE ID = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		int i = 0;
		
		
		try {
			conn = Conexao.createConnectionToMySql();		
	
			
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, usuario.getNome());
			pstm.setString(2, usuario.getEmail());
			pstm.setString(3, usuario.getSenha());
			pstm.setInt(4, usuario.getId());			
			
			i = pstm.executeUpdate();		
			
			System.out.println("\nUSUÁRIO ATUALIZADO COM SUCESSO!");			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		
		}finally {
			
			try {
				if(pstm != null) {
					pstm.close();
				}if(conn != null) {
					conn.close();
				}
			}catch (Exception e2) {
				e2.printStackTrace();
			}	
		}
		
		return i;
	}
	
	public int delete(Usuario usuario) {
		String sql = "DELETE FROM usuarios WHERE ID = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		int i = 0;
		

		try {
			conn = Conexao.createConnectionToMySql();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, usuario.getId());	

			pstm.executeUpdate();
			
			System.out.println("\nCADASTRO DELETADO COM SUCESSO!");
						
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			
		} finally{		
			try {
				if(pstm != null) {
					pstm.close();
				}if(conn != null) {
					conn.close();
				}			
			}catch (Exception e2){
			e2.printStackTrace();
			}
		}
		return i;
		
	}
	
	public int getLastId() {
		String sql = "SELECT MAX(ID) as id FROM usuarios";	
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rset = null;
		
		int lastId = 0;
		
		
		try {
			conn = Conexao.createConnectionToMySql();
			
			pstm = conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			rset.next();
			
			lastId = rset.getInt("ID");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset != null) {
					rset.close();
				}if(pstm != null) {
					pstm.close();
				}if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}		
		
		return lastId;
	}	

		
}

	
