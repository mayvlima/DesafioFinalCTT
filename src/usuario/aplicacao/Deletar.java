package usuario.aplicacao;

import java.util.List;
import java.util.Scanner;

import usuario.metodos.UsuarioMetodos;
import usuario.model.Usuario;

public class Deletar {
	public static Scanner sc = new Scanner(System.in);
	public static Scanner scInt = new Scanner(System.in);
	public static Usuario usuario = new Usuario();
	public static UsuarioMetodos usuarioMetodos = new UsuarioMetodos();	
	
	public void deletarCadastro() {
		if(usuarioMetodos.getUsuarios().isEmpty()) {
			System.out.println("\nA LISTA DE CADASTROS ESTÁ VAZIA!");
			System.out.println("ADICIONE PELO MENOS UM CADASTRO!");
		}else {
			int a = pergunta("\nDeseja ver a lista de cadastros antes?", "1 - SIM", "2 - NÃO");
			
			if(a == 1) {
				List<Usuario> usuarios = usuarioMetodos.getUsuarios();					
				for (Usuario u : usuarios) {
					System.out.println("--------------------------------");
					System.out.println("ID: " + u.getId() + " - " + u.getNome());				
				}
				deletar();							
				
			}else {
				deletar();
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
	
	
	public static void deletar() {
		boolean continuar = true;
		int b = 1;
		do {			
			usuario = new Usuario();
			System.out.println("\nDigite o ID do registro desejado:");
			usuario.setId(scInt.nextInt());

			usuario = usuarioMetodos.getUsuarioWithId(usuario);

			if (usuario != null) {
				System.out.println("\n--------------------------------");
				System.out.println(usuario);
				System.out.println("\n--------------------------------");
				b = pergunta("Esse é o cadastro desejado?", "1 - SIM", "2 - NÃO");
				if(b == 1) {
					usuarioMetodos.delete(usuario);
					int c = pergunta("\nDeseja deletar um novo usuário?", "1 - SIM", "2 - NÃO");
					if(c == 2) {
					continuar = false;
					}
				} else {
					int d = pergunta("\nDigite o número:", "1 - TENTAR NOVAMENTE", "2 - SAIR");
					if (d == 2) {
						continuar = false;
						System.out.println("\nDELETAR CADASTRO CANCELADA!");
					}							
				}
								
			} else {				
				System.out.println("\nNÃO HÁ CADASTRO COM ESSE NÚMERO DE ID!");
				int e = pergunta("\nDigite:", "1 - TENTAR NOVAMENTE", "2 - SAIR");
				if (e == 2) {
					continuar = false;					
				}
			}					
			
		} while (continuar == true);	
	}
}
