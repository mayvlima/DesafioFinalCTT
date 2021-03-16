package usuario.aplicacao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import usuario.metodos.UsuarioMetodos;
import usuario.model.Usuario;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static Scanner scInt = new Scanner(System.in);
	public static Usuario usuario = new Usuario();
	public static UsuarioMetodos usuarioMetodos = new UsuarioMetodos();
	
	public static void main(String[] args) throws SQLException {						
		
		int continuar = 1;		
		
		System.out.println("\nBEM VINDO!");
		do {			
			int r = menu();			
			
			switch (r) {
			case 1:				
				setUsuario();
				usuarioMetodos.save(usuario);				
				usuario.setId(usuarioMetodos.getLastId());
				usuarioMetodos.getUsuarioWithId(usuario);
				System.out.println("\n--------------------------------");
				System.out.println(usuario);
				System.out.println("\n--------------------------------");
				
				continuar = pergunta("\nDeseja retornar ao menu?", "1 - SIM", "2 - NÃO");
				
				break;
				
			case 2:
				int i;
				
				do {
					System.out.println("\nO que você deseja fazer?");
					System.out.println("1 - Procurar usuário por ID ");
					System.out.println("2 - Procurar usuário por Nome ");
					System.out.println("3 - Ver todos os cadastros");
					i = scInt.nextInt();
					if(i!=1 && i!=2 && i!=3) {
						System.out.println("\nESCOLHA UMA OPÇÃO VÁLIDA!\n");
					}
				}while(i!=1 && i!=2 && i!=3);
				
				if(i == 1) {
				System.out.println("");	
				setIdDesejado();
				
				}else if(i == 2) {
					
					receberDado("Nome");
					List<Usuario> usuarios = usuarioMetodos.getUsuarioWithName(usuario); 
					if(usuarios.isEmpty()) {
						System.out.println("\nNENHUM USUÁRIO COM ESSE NOME ECONTRADO!");
					}else {
						System.out.println("\nO RESULTADO DA BUSCA FOI:");
						for(Usuario u : usuarios) {						
							System.out.println(u);
							System.out.println("\n--------------------------------");
						}	
					}					
					
				}else if(i == 3){
					System.out.println("\nLISTA DE USUÁRIOS");
					verTodosCadastros();
				}				
				
				continuar = pergunta("\nDeseja retornar ao menu?", "1 - SIM", "2 - NÃO");
				
				break;
				
			case 3:
				int b = pergunta("\nDeseja ver a lista de cadastros antes?", "1 - SIM", "2 - NÃO");
				
				if(b == 1){
					verCadastrosSimples();
					setIdDesejado();					
					int c = pergunta("Esse é o cadastro desejado?", "1 - SIM", "2 - NÃO");
					
					if(c== 1) {
						atualizar();							
						System.out.println("\nUSUÁRIO ATUALIZADO COM SUCESSO!");
						System.out.println(usuario);
						System.out.println("\n--------------------------------");
							
					}else {
						System.out.println("\nATUALIZAÇÃO DE CADASTRO CANCELADA!");
					}
				}else {
					setIdDesejado();
					int c = pergunta("Esse é o cadastro desejado?", "1 - SIM", "2 - NÃO");
					
					if( c == 1) {
						atualizar();						
						System.out.println("\nUSUÁRIO ATUALIZADO COM SUCESSO!");		
						System.out.println(usuario);
						System.out.println("\n--------------------------------");			
					}else {
						System.out.println("\nAtulização de cadastro cancelada!");
					}
				}						
				
				continuar = pergunta("\nDeseja retornar ao menu?", "1-SIM", "2-NÃO");
				
				break;
				
			case 4:
				
				int d = pergunta("\nDeseja ver a lista de cadastro antes?", "1-SIM", "2-NÃO");
				
				if(d == 1){
					verCadastrosSimples();
					setIdDesejado();
					int e = pergunta("Esse é o cadastro desejado?", "1 - SIM", "2 - NÃO");
						if(e == 1) {							
							usuarioMetodos.delete(usuario);	
						}else {
							System.out.println("\nDeletar cadastro cancelado!");
						}
					
				}else {
					setIdDesejado();
					int e = pergunta("Esse é o cadastro desejado?", "1 - SIM", "2 - NÃO");				
					if(e == 1) {					
						usuarioMetodos.delete(usuario);					
					}else {
						System.out.println("\nDeletar cadastro cancelado!");
					}
				}	
				
				continuar = pergunta("\nDeseja retornar ao menu?", "1 - SIM", "2 - NÃO");
				break;
				
			case 5:
				continuar = 2;
				break;			
			}		
				
			
		}while(continuar == 1);		
	
		sc.close();
		scInt.close();
		
		System.out.println("\nPROGRAMA FINALIZADO!");		
	
	}
	
	public static int menu() {
		int i;
		do {
			
			System.out.println("\nO que você deseja fazer?");
			System.out.println("1 - Cadastrar novo usuário");
			System.out.println("2 - Procurar um cadastro");
			System.out.println("3 - Atualizar um cadastro");
			System.out.println("4 - Deletar um cadastro");
			System.out.println("5 - Sair");
			i = scInt.nextInt();
			
			if(i!=1 && i!=2 && i!=3 && i!=4 && i!=5) {
				System.out.println("\nESCOLHA UMA OPÇÃO VÁLIDA!");
			}
		}while(i!=1 && i!=2 && i!=3 && i!=4 && i!=5);
		
		return i;
	}
	
	public static int pergunta(String frase1, String frase2, String frase3) {		
		int i;
		do {
			System.out.println(frase1);
			System.out.println(frase2);
			System.out.println(frase3);
			i = scInt.nextInt();
			if(i!=1 && i!=2) {
				System.out.println("\nESCOLHA UMA OPÇÃO VÁLIDA!\n");
			}
		}while(i!=1 && i!=2);
		
		return i;
	}
	
	public static void receberDado(String string) {
		String dado;
		do {
			System.out.println("\nDigite o " + string.toLowerCase() + ":");			
			dado = sc.nextLine();				
			if(dado.trim().equals("")) {
				System.out.println("DIGITE UM " +string.toUpperCase() + " VÁLIDO!\n");
			}else {
				if(string.equals("Nome")) {
					usuario.setNome(dado);
				}else if (string.equals("Email")){
					usuario.setEmail(dado);
				}else if (string.equals("Senha")) {
					usuario.setSenha(dado);
				}
			}	
			
			}while(dado.trim().equals(""));			
	}
	
	public static void dataAtual() {
		usuario.setDataCadastro(new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
	}
	
	public static void setUsuario() {
		receberDado("Nome");
		receberDado("Email");
		receberDado("Senha");
		dataAtual();
	}		
	
	public static void setIdDesejado() {
		do {
			usuario = new Usuario();
			System.out.println("\nDigite o ID do registro desejado:");			
			usuario.setId(scInt.nextInt());
			
			usuario = usuarioMetodos.getUsuarioWithId(usuario);
			
			if(usuario != null) {
				System.out.println("\n--------------------------------");
				System.out.println(usuario);
				System.out.println("\n--------------------------------");
			}else {
				System.out.println("\nESCOLHA UM ID VÁLIDO!");
			}
		}while(usuario == null);		
	}
	
	public static void atualizar() {
		int r = pergunta("\nDeseja atualizar o nome?","1 - SIM","2 - NÃO");
		if(r == 1) {
		receberDado("Nome");
		usuarioMetodos.update(usuario, "Nome");
		}
		
		r = pergunta("\nDeseja atualizar o email?","1 - SIM","2 - NÃO");
		if(r == 1) {
		receberDado("Email");
		usuarioMetodos.update(usuario, "Email");
		}
		
		r = pergunta("\nDeseja atualizar o senha?","1 - SIM","2 - NÃO");
		if(r == 1) {
		receberDado("Senha");
		usuarioMetodos.update(usuario,"Senha");
		}
	}
	
	public static void verTodosCadastros() {
		usuarioMetodos.getUsuarios();
		for(Usuario u : usuarioMetodos.getUsuarios()) {						
			System.out.println(u);
			System.out.println("\n--------------------------------");
		}
	}
	
	public static void verCadastrosSimples() {
		usuarioMetodos.getUsuarios();
		for(Usuario u : usuarioMetodos.getUsuarios()) {	
			System.out.println("--------------------------------");
			System.out.println("ID: " + u.getId() + " - " + u.getNome());			
		}
	}
	
	
}