package usuario.aplicacao;

import java.util.List;
import java.util.Scanner;

import usuario.metodos.UsuarioMetodos;
import usuario.model.Usuario;

public class Procurar {

	public static Scanner sc = new Scanner(System.in);
	public static Scanner scInt = new Scanner(System.in);
	public static Usuario usuario = new Usuario();
	public static UsuarioMetodos usuarioMetodos = new UsuarioMetodos();	
	
	public void procurarCadastro(){
		
		if(usuarioMetodos.getUsuarios().isEmpty()) {
			
			System.out.println("\nA LISTA DE CADASTROS ESTÁ VAZIA!");
			System.out.println("ADICIONE PELO MENOS UM CADASTRO!");
			
		} else {
			
			int i;
			
			do {
				System.out.println("\nO que você deseja fazer?");
				System.out.println("1 - Procurar usuário por ID ");
				System.out.println("2 - Procurar usuário por Nome ");
				System.out.println("3 - Ver todos os cadastros");
				i = scInt.nextInt();
					if (i != 1 && i != 2 && i != 3) {
						System.out.println("\nESCOLHA UMA OPÇÃO VÁLIDA!\n");
					}
			} while (i != 1 && i != 2 && i != 3);
			
			
			if(i == 1) {
				boolean continuar = true;
				do {			
					usuario = new Usuario();
					System.out.println("\nDigite o ID do registro desejado:");
					usuario.setId(scInt.nextInt());

					usuario = usuarioMetodos.getUsuarioWithId(usuario);

					if (usuario != null) {
						System.out.println("\n--------------------------------");
						System.out.println(usuario);
						System.out.println("\n--------------------------------");
						
						int a = pergunta("Deseja procurar outro usuário?", "1 - SIM", "2 - NÃO");
						
						if(a == 2) {
							continuar = false;
						}
						
					} else {				
						System.out.println("\nNÃO HÁ CADASTROS COM ESSE NÚMERO DE ID!");
						
						int b = pergunta("Digite:", "1 - TENTAR NOVAMENTE", "2 - SAIR");
						if (b == 2) {
							continuar = false;					
						}
					}
				} while (continuar == true);
			}
			
			
			if(i == 2) {
				boolean continuar = true;
				do {											
					receberDado("Nome");
					List<Usuario> usuarios = usuarioMetodos.getUsuarioWithName(usuario);
					
					if (usuarios.isEmpty()) {						
						System.out.println("\nNENHUM USUÁRIO COM ESSE NOME ENCONTRADO!");
						
						int c = pergunta("Digite:", "1 - TENTAR NOVAMENTE", "2 - Sair");
						
						if (c == 2) {
							continuar = false;
						}
						
					} else {
						System.out.println("\nO RESULTADO DA BUSCA FOI:");
						
						for (Usuario u : usuarios) {
							System.out.println(u);
							System.out.println("\n--------------------------------");
						}
						
						int d = pergunta("Deseja procurar outro usuário?", "1 - SIM", "2 - NÃO");
						if(d == 2) {
							continuar = false;
						}
						
					}
				}while(continuar == true);
			}
			
			if(i == 3) {
				List<Usuario> usuarios = usuarioMetodos.getUsuarios();
				for (Usuario u : usuarios) {
					System.out.println(u);
					System.out.println("\n--------------------------------");
				}
			}	
			
		}
	}
	
	public static int pergunta(String frase1, String frase2, String frase3) {
		int i;
		do {
			System.out.println(frase1);
			System.out.println(frase2);
			System.out.println(frase3);
			i = scInt.nextInt();
			if (i != 1 && i != 2) {
				System.out.println("\nESCOLHA UMA OPÇÃO VÁLIDA!\n");
			}
		} while (i != 1 && i != 2);

		return i;
	}
	
	public static void receberDado(String string) {
		String dado;
		do {
			System.out.println("\nDigite o " + string.toLowerCase() + ":");
			dado = sc.nextLine();
			if (dado.trim().equals("")) {
				System.out.println("DIGITE UM " + string.toUpperCase() + " VÁLIDO!\n");
			} else {
				if (string.equals("Nome")) {
					usuario.setNome(dado);
				} else if (string.equals("Email")) {
					usuario.setEmail(dado);
				} else if (string.equals("Senha")) {
					usuario.setSenha(dado);
				}
			}

		} while (dado.trim().equals(""));
	}
}
