package com.senac.estruturas;

public class ListaEncadeada<T> {

	protected Nodo<T> head;
	protected Nodo<T> tail;

	public ListaEncadeada() {
		head = null;
		tail = null;
	}
	
	public void insert(Nodo<T> novo)
	{
		novo.setNext(head);
		if (head != null)
			head.setPrevious(novo);
		head = novo;
		if (tail == null)
			tail = novo;
	}
	
	public void insert(Nodo<T> novo, Nodo<T> anterior)
	{
		if (anterior == null) {
			novo.setNext(head);
			head = novo;
			if (tail == null)
				tail = head;
		} else {
			novo.setNext(anterior.getNext());
			novo.setPrevious(anterior);
			anterior.setNext(novo);
			if (anterior == tail)
				tail = novo;
		}
	}
	
	public void append(Nodo<T> novo)
	{
		if (tail != null) {
			tail.setNext(novo);
			novo.setPrevious(tail);
		} else {
			head = novo;
		}
		tail = novo;
	}
	
	public Nodo<T> getTail()
	{
		return tail;
	}
	
	public Nodo<T> getHead()
	{
		return head;
	}

	public void remove(Nodo<T> nodo) {
		Nodo<T> ant = nodo.getPrevious();
		Nodo<T> next = nodo.getNext();
		if (ant != null)
			ant.setNext(next);
		else
			head = next;
		if (next != null)
			next.setPrevious(ant);
		else
			tail = ant;
	}
}
