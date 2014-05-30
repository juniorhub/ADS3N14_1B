package com.senac.estruturas;

public class Nodo<T> {
	private T data;
	private Nodo<T> next;
	private Nodo<T> prev;
	
	public Nodo()
	{
		data = null;
		next = null;
	}

	public Nodo(T chave) {
		this.data = chave;
		this.next = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Nodo<T> getNext() {
		return next;
	}

	public void setNext(Nodo<T> next) {
		this.next = next;
	}

	public Nodo<T> getPrevious() {
		return prev;
	}

	public void setPrevious(Nodo<T> prev) {
		this.prev = prev;
	}
}