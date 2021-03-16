package usuario.aplicacao;

import java.sql.SQLException;
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
				verCadastro();
				
				continuar = continuar("\nDeseja retornar ao menu?", "1 - SIM", "2 - NÃO");
				
				break;
				
			case 2:
				int a = continuar("\nO que você deseja fazer?","1 - Procurar usuário por ID ", "2 - Ver todos os cadastros");
								
				if(a == 1) {
					do {
					System.out.println("\nDigite o ID do registro que deseja visualizar:");
					usuario.setId(scInt.nextInt());
					
					if(usuarioMetodos.getUsuarioWithId(usuario)) {						
						verCadastro();
					}else {
						System.out.println("\nESCOLHA UM ID VÁLIDO!");
					}
					}while(!usuarioMetodos.getUsuarioWithId(usuario));
					
				}else if(a == 2) {
					verTodosCadastros();
				}else {
					System.out.println("Escolha uma opção disponível!");
				}
				
				continuar = continuar("\nDeseja retornar ao menu?", "1 - SIM", "2 - NÃO");
				
				break;
				
			case 3:
				int b = continuar("\nDeseja ver a lista de cadastros antes?", "1 - SIM", "2 - NÃO");
				
				if(b == 1){
					verCadastrosSimples();
					int c = setIdDesejado();
					
					if(c== 1) {
						atualizar();							
						System.out.println("\nUSUÁRIO ATUALIZADO COM SUCESSO!");
						verCadastro();						
							
					}else {
						System.out.println("\nAtulização de cadastro cancelada!");
					}
				}else {
					int c = setIdDesejado();
					
					if(c== 1) {
						atualizar();						
						System.out.println("\nUSUÁRIO ATUALIZADO COM SUCESSO!");		
						verCadastro();					
					}else {
						System.out.println("\nAtulização de cadastro cancelada!");
					}
				}						
				
				continuar = continuar("\nDeseja retornar ao menu?", "1-SIM", "2-NÃO");
				
				break;
				
			case 4:
				
				int d = continuar("\nDeseja ver a lista de cadastro antes?", "1-SIM", "2-NÃO");
				
				if(d == 1){
					verCadastrosSimples();
					int e  = setIdDesejado();					
						if(e == 1) {							
							usuarioMetodos.delete(usuario);	
						}else {
							System.out.println("\nDeletar cadastro cancelado!");
						}
					
				}else {
					int e  = setIdDesejado();					
					if(e == 1) {					
						usuarioMetodos.delete(usuario);					
					}else {
						System.out.println("\nDeletar cadastro cancelado!");
					}
				}	
				
				continuar = continuar("\nDeseja retornar ao menu?", "1 - SIM", "2 - NÃO");
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
			System.out.println("2 - Procurar cadastro");
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
	
	public static int continuar(String frase1, String frase2, String frase3) {		
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
	
	public static void receberNome() {
		String nome = "";
		do {
			System.out.println("\nDigite o nome: ");			
			nome = sc.nextLine();				
			if(nome.trim().equals("")) {
				System.out.println("Digite um nome válido!\n");
			}else {
				usuario.setNome(nome);
			}	
			
			}while(nome.trim().equals(""));		
		
	}
	
	public static void receberEmail() {
		String email = "";
		do {
			System.out.println("\nDigite o email: ");
			email = sc.nextLine();		
			if(email.trim().equals("")) {
				System.out.println("Digite um email válido!\n");
			}else {
				usuario.setEmail(email);
			}	
			
			}while(email.trim().equals(""));
	}
	
	public static void receberSenha() {
		String senha = "";
		do {
			System.out.println("\nDigite o senha: ");
			senha = sc.nextLine();		
			if(senha.trim().equals("")) {
				System.out.println("Digite uma senha válido!\n");
			}else {
				usuario.setSenha(senha);
			}	
			
			}while(senha.trim().equals(""));
	}
	
	public static void dataAtual() {
		usuario.setDataCadastro(new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
	}
	
	
	
	public static void setUsuario() {
		receberNome();
		receberEmail();
		receberSenha();
		dataAtual();
	}
	
	public static int setIdDesejado() {
		do {
			System.out.println("\nDigite o ID do registro desejado:");
			usuario.setId(scInt.nextInt());
			
			if(usuarioMetodos.getUsuarioWithId(usuario)) {		
			verCadastro();
			System.out.println("\nEsse é o cadastro desejado: ");
			}else {
				System.out.println("\nESCOLHA UM ID VÁLIDO!");
			}
		}while(!usuarioMetodos.getUsuarioWithId(usuario));
		int r = continuar("\nDigite:", "1-SIM", "2-NÃO");
		return r;
	}
	
	public static void atualizar() {
		int r = continuar("\nDeseja atualizar o nome?","1 - SIM","2 - NÃO");
		if(r == 1) {
		receberNome();
		usuarioMetodos.updateNome(usuario);
		}
		
		r = continuar("\nDeseja atualizar o email?","1 - SIM","2 - NÃO");
		if(r == 1) {
		receberEmail();
		usuarioMetodos.updateEmail(usuario);
		}
		
		r = continuar("\nDeseja atualizar o senha?","1 - SIM","2 - NÃO");
		if(r == 1) {
		receberSenha();
		usuarioMetodos.updateSenha(usuario);
		}
	}
	
	public static void verTodosCadastros() {
		usuarioMetodos.getUsuarios();
		for(Usuario u : usuarioMetodos.getUsuarios()) {						
			System.out.println(u.toString());
			System.out.println("\n--------------------------------");
		}
	}
	public static void verCadastrosSimples() {
		usuarioMetodos.getUsuarioWithId(usuario);
		for(Usuario u : usuarioMetodos.getUsuarios()) {	
			System.out.println("--------------------------------");
			System.out.println("ID = "+u.getId()+": " + u.getNome());
			
		}
	}
	public static Usuario verCadastro() {		
		usuarioMetodos.getUsuarioWithId(usuario);
		System.out.println("\n--------------------------------");
		System.out.println(usuario.toString());
		System.out.println("\n--------------------------------");
		return usuario;
	}
	
}