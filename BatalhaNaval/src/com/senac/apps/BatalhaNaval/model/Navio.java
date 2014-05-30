package com.senac.apps.BatalhaNaval.model;

public class Navio {

	String tipo;
	String padrao;

	public Navio() {
		this.padrao = ".";
		this.tipo = null;
	}
	
	public Navio(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPadrao() {
		return padrao;
	}

	public void setPadrao(String padrao) {
		this.padrao = padrao;
	}

}