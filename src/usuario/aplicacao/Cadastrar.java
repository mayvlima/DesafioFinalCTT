package usuario.aplicacao;

import java.util.Scanner;

import usuario.metodos.UsuarioMetodos;
import usuario.model.Usuario;

public class Cadastrar {

	public static Scanner sc = new Scanner(System.in);
	public static Scanner scInt = new Scanner(System.in);
	public static Usuario usuario = new Usuario();
	public static UsuarioMetodos usuarioMetodos = new UsuarioMetodos();	
	
	public void novoCadastro() {
		int continuar;
		do {			
		receberDado("Nome");
		receberDado("Email");
		receberDado("Senha");
		dataAtual();
		
		usuarioMetodos.save(usuario);	
		usuario.setId(usuarioMetodos.getLastId());
		usuarioMetodos.getUsuarioWithId(usuario);
		
		System.out.println("\n--------------------------------");
		System.out.println(usuario);
		System.out.println("\n--------------------------------"); 
		
		continuar = pergunta("Deseja cadastrar um novo usuário?", "1 - SIM", "2 - NÃO");
		}while(continuar == 1);
		 
	}
	
	public static void receberDado(String string) {
		String dado;
		do {
			System.out.println("\nDigite o " + string.toUpperCase() + ":");
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
	
	public static void dataAtual() {
		usuario.setDataCadastro(new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
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
	
}
