package com.senac.apps.SelectionQuickSort.view;

import static java.lang.System.out;
import java.util.Scanner;

	public class ConsoleView {

		private Scanner teclado = new Scanner(System.in);

		public void message(String message) {
			out.println(message);
		}
		
		public void message(int message) {
			out.print(message);
		}

		public String read(String prompt) {
			out.print("\n" + prompt + ": ");
			return teclado.nextLine();
		}
		
		public int read() {
			return teclado.nextInt();
		}
		
		public void printLista(int[] lista) {
			for (int i = 0; i < lista.length; i++) {
				out.print(lista[i] + "\t");
			}
		}

		public void logError(String message) {
			message("Error: " + message);
		}

}