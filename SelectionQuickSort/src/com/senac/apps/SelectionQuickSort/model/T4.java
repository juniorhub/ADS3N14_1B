package com.senac.apps.SelectionQuickSort.model;

public class T4 {
	
	private int comparacoes = 0;
	private int trocas = 0;
	
	public int getComparacoes() {
		return comparacoes;
	}
	public void addComparacoes() {
		this.comparacoes++;
	}
	public int getTrocas() {
		return trocas;
	}
	public void addTrocas() {
		this.trocas++;
	}
	public void setComparacoes(int comparacoes) {
		this.comparacoes = comparacoes;
	}
	public void setTrocas(int trocas) {
		this.trocas = trocas;
	}

}