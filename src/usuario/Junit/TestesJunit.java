package usuario.Junit;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import usuario.conexao.Conexao;
import usuario.metodos.UsuarioMetodos;
import usuario.model.Usuario;

public class TestesJunit {
	Usuario usuario = new Usuario();
	UsuarioMetodos usuarioMetodos = new UsuarioMetodos();
	Conexao conexao = new Conexao();

	@Test
	public void testSetNome(){
		usuario.setNome("Mayara");
		assertEquals("Mayara", usuario.getNome());
	}
	
	@Test
	public void testSetEmail(){
		usuario.setNome("mayara@email.com");
		assertEquals("mayara@email.com", usuario.getNome());
	}
	
	@Test
	public void testSetSenha(){
		usuario.setSenha("abc123");
		assertEquals("abc123", usuario.getSenha());
	}
	
	@Test
	public void testSetId(){
		usuario.setId(1);
		assertEquals(1, usuario.getId());
	}
	
	@Test
	public void testSetData(){
		Timestamp data = new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
		usuario.setDataCadastro(data);;
		assertEquals(data, usuario.getDataCadastro());
	}
	
	@Test
	public void testToString() {	
		Timestamp data = new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
		
		usuario.setNome("Anna Souza");
		usuario.setEmail("annasousasouza@gustr.com");
		usuario.setSenha("la9eep4Edae");	
		usuario.setDataCadastro(data);
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		String esperada = "\nID: " + usuario.getId() + "\nNome: " + usuario.getNome() + "\nEmail: " + usuario.getEmail() + "\nSenha: " + usuario.getSenha() + "\nData de Cadastro: " + df.format(usuario.getDataCadastro());
		
		assertEquals(esperada, usuario.toString());
	}
	
	@Test
	public void testGetConection() {
		try {
		Connection con = Conexao.createConnectionToMySql();
		assertNotNull(con);
		}catch (Exception e) {			
			fail("Não foi possível conectar no banco de dados");
		}
	}	
	
	@Test
	@Ignore
	public void testSave() {	
		
		Usuario usuario = new Usuario();
		
		usuario.setNome("Anna Souza");
		usuario.setEmail("annasousasouza@gustr.com");
		usuario.setSenha("la9eep4Edae");	
		usuario.setDataCadastro(new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));				
	
		assertTrue("Erro ao salvar!",usuarioMetodos.save(usuario));
	}
	
	@Test 
	public void testGetUsuarios() {
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		assertNotEquals("Não foi possível!", usuarios, usuarioMetodos.getUsuarios());		
		
	}	
	
	@Test
	public void testGetUsuarioWithId() {
		
		usuario.setId(40);		
		
		assertTrue("Não foi possível encontrar o Id!", usuarioMetodos.getUsuarioWithId(usuario));	
	}

	
	@Test
	public void testUpdateNome() {	
		
		UsuarioMetodos usuarioMetodos = new UsuarioMetodos();		
		
		Usuario usuario = new Usuario();
		
		usuario.setNome("Lourival Bernardes");
			
		usuario.setId(2);		
	
		assertEquals("Erro ao atualizar!", 1, usuarioMetodos.updateNome(usuario));
	}
	
	@Test
	public void testUpdateEmail() {	
		
		UsuarioMetodos usuarioMetodos = new UsuarioMetodos();		
		
		Usuario usuario = new Usuario();
		
		usuario.setEmail("lourivalb@email.com");
			
		usuario.setId(3);		
	
		assertEquals("Erro ao atualizar!", 1, usuarioMetodos.updateEmail(usuario));
	}
	
	@Test	
	public void testUpdateSenha() {	
		
		UsuarioMetodos usuarioMetodos = new UsuarioMetodos();		
		
		Usuario usuario = new Usuario();
		
		usuario.setSenha("ds4d5as45s");
			
		usuario.setId(3);		
	
		assertEquals("Erro ao atualizar!", 1, usuarioMetodos.updateSenha(usuario));
	}
	
	@Test	
	public void testDelete() {		
		UsuarioMetodos usuarioMetodos = new UsuarioMetodos();		
		
		Usuario usuario = new Usuario();
		
		usuario.setId(41);
	
		assertEquals("Erro ao deletar!", 1, usuarioMetodos.delete(usuario));
	}
	
	@Test
	public void testGetLastId() {
		int resultado = usuarioMetodos.getLastId();
		assertEquals(35, resultado);
	}
	
	
	
}
