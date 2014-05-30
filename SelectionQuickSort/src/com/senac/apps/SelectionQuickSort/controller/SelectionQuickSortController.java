package com.senac.apps.SelectionQuickSort.controller;

import java.util.Random;

import com.senac.apps.SelectionQuickSort.model.T4;
import com.senac.apps.SelectionQuickSort.view.ConsoleView;

public class SelectionQuickSortController {

	private ConsoleView view = new ConsoleView();
	
	T4 t4q = new T4();
	T4 t4i = new T4();
	T4 t4qi = new T4();

	public void insertionSort() {

		int tamanho;
		Random random = new Random();

		System.out.print("\nDigite o tamanho do array: ");
		tamanho = view.read();

		int x[] = new int[tamanho];
		int i, j, eleito;

		for (i = 0; i <= (tamanho - 1); i++) {
			x[i] = random.nextInt(tamanho);
		}

		view.message("\nArray original:\n");
		view.printLista(x);

		for (i = 1; i <= (tamanho - 1); i++) {
			eleito = x[i];
			j = i - 1;
			while (j >= 0 && x[j] > eleito) {
				x[j + 1] = x[j];
				j -= 1;
				t4i.addComparacoes();
				t4i.addTrocas();
			}
			x[j + 1] = eleito;
			t4i.addTrocas();
		}

		view.message("\n");
		view.printLista(x);
		view.message("\n\nComparacoes: " + t4i.getComparacoes());
		view.message("\nTrocas: " + t4i.getTrocas());
		t4i.setComparacoes(0);
		t4i.setTrocas(0);

	}
	
	public void execQuickSort() {
		
		int tamanho;
		Random random = new Random();
		
		System.out.print("\nDigite o tamanho do array: ");
		tamanho = view.read();

		int x[] = new int[tamanho];
		int i;

		for (i = 0; i <= (tamanho - 1); i++) {
			x[i] = random.nextInt(tamanho);
		}
		
		view.message("\nArray original:\n");
		view.printLista(x);
		
		quickSort(x, 0, (x.length-1));
		view.message("\n");
		view.printLista(x);
		view.message("\n\nComparacoes: " + t4q.getComparacoes());
		view.message("\nTrocas: " + t4q.getTrocas());
		
		t4q.setComparacoes(0);
		t4q.setTrocas(0);
		
	}

	private void quickSort(int[] v, int ini, int fim) {
		
		int meio;

		if (ini < fim) {
			meio = partition(v, ini, fim);
			quickSort(v, ini, meio);
			quickSort(v, meio + 1, fim);
		}
	}

	private int partition(int[] v, int ini, int fim) {
		
		int pivo, topo, i;
		pivo = v[ini];
		topo = ini;

		for (i = ini + 1; i <= fim; i++) {
			if (v[i] < pivo) {
				v[topo] = v[i];
				v[i] = v[topo + 1];
				topo++;
				t4q.addComparacoes();
				t4q.addTrocas();
			}
		}
		v[topo] = pivo;
		t4q.addTrocas();
		return topo;
		
	}
	
	public void comparacaoSort() {
		
		int tamanho;
		Random random = new Random();

		System.out.print("\nDigite o tamanho do array: ");
		tamanho = view.read();

		int x[] = new int[tamanho];
		int i, j, eleito;

		for (i = 0; i <= (tamanho - 1); i++) {
			x[i] = random.nextInt(tamanho);
		}
		
		view.message("\nArray original:\n");
		view.printLista(x);
		
		int y[] = new int[tamanho];
		
		for (int k = 0; k < x.length; k++) {
			y[k] = x[k];
		}

		for (i = 1; i <= (tamanho - 1); i++) {
			eleito = x[i];
			j = i - 1;
			while (j >= 0 && x[j] > eleito) {
				x[j + 1] = x[j];
				j -= 1;
				t4i.addComparacoes();
				t4i.addTrocas();
			}
			x[j + 1] = eleito;
			t4i.addTrocas();
		}
		
		quickSort(y, 0, (y.length-1));
		
		view.message("\n\nInsertion Sort:\n");
		view.printLista(x);
		view.message("\n\nQuick Sort:\n");
		view.printLista(y);
		view.message("\n\nInsertion:\nComparacoes: " + t4i.getComparacoes() + "\t");
		view.message("Trocas: " + t4i.getTrocas());
		view.message("\nQuick:\nComparacoes: " + t4q.getComparacoes() + "\t");
		view.message("Trocas: " + t4q.getTrocas());
		
		t4i.setComparacoes(0);
		t4i.setTrocas(0);
		
		t4q.setComparacoes(0);
		t4q.setTrocas(0);
	}
}