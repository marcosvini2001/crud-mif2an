package br.com.belval.crud.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id = 0;
	private String email;
	private String senha;
	private String telefone;


	private boolean ativo = true;
	
	//Alt+SHIFT+S >> Generate Constructor from Superclass
	
	public Pessoa() {
	}

	//Alt+SHIFT+S >> Generate Constructor using Fields
	public Pessoa(int id, String email, String senha, String telefone) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
	}

	//Alt+SHIFT+S >> Generate Getters and Setters
	
	public int getId() {//id , leitura
		return id;
	}

	public void setId(int id) {//id, escrito
		this.id = id;
	}

	public String getEmail() {//nome, permite a leitura
		return email;
	}

	public void setEmail(String email) {//nome, permite a escrita
		this.email = email;
	}

	public String getsenha() {
		return senha;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	
	//Alt+SHIFT+S >> Generate toString()
	
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", email=" + email + ", senha=" + senha + ", telefone=" + telefone + ", ativo="
				+ ativo + "]";
	}
}
