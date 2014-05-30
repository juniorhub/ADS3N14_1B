package com.senac.apps.SelectionQuickSort;

import com.senac.apps.SelectionQuickSort.controller.SelectionQuickSortController;
import com.senac.apps.SelectionQuickSort.view.ConsoleView;

public class SelectionQuickSort {
	
	public static void main(String[] args) {
		
		ConsoleView view = new ConsoleView();
		SelectionQuickSortController controller = new SelectionQuickSortController();
		String command = "";
		
		view.message("Digite 'ajuda' para comandos validos.");
		while (!command.equals("s")) {
			command = view.read("Comando").toLowerCase();
			if (command.equals("i"))
				controller.insertionSort();
			if (command.equals("q"))
				controller.execQuickSort();
			if (command.equals("c"))
				controller.comparacaoSort();
			if (command.equals("ajuda"))
				view.message("\nAjuda [I]nsertionSort [Q]uickSort [C]omparacao [S]air");
		}
		
	}

}
