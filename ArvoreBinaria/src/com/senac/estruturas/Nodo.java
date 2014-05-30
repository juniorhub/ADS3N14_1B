package com.senac.estruturas;

public class Nodo<T> {
	private T data;
	private Nodo<T> filhoEsquerda;
	private Nodo<T> filhoDireita;
	
	public Nodo()
	{
		data = null;
		filhoEsquerda = null;
	}

	public Nodo(T chave) {
		this.data = chave;
		this.filhoEsquerda = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Nodo<T> getFilhoEsquerda() {
		return filhoEsquerda;
	}

	public void setFilhoEsquerda(Nodo<T> filhoEsquerda) {
		this.filhoEsquerda = filhoEsquerda;
	}

	public Nodo<T> getFilhoDireita() {
		return filhoDireita;
	}

	public void setFilhoDireita(Nodo<T> filhoDireita) {
		this.filhoDireita = filhoDireita;
	}
}