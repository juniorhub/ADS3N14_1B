package com.senac.apps.ListaTelefonica.model;

public class Pessoa implements Comparable<Pessoa> {

	private String nome;
	private String telefone;
	
	public Pessoa()
	{
		nome = null;
		telefone = null;
	}
	
	public Pessoa(String nome)
	{
		this.nome = nome;
		this.telefone = null;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int compareTo(Pessoa cmp) {
		return nome.compareTo(cmp.getNome());
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
