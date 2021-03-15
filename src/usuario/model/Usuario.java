package usuario.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Usuario {

	private int id;
	private String nome;
	private String email;
	private String senha;
	private Timestamp dataCadastro;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Timestamp getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return "\nID: " + getId() + "\nNome: " + getNome() + "\nEmail: " + getEmail() + "\nSenha: " + getSenha() + "\nData de Cadastro: " + df.format(getDataCadastro());				
			
	}
	
	
	
}
