package com.senac.apps.BatalhaNaval;

import com.senac.apps.BatalhaNaval.controller.BatalhaNavalController;
import com.senac.apps.BatalhaNaval.view.ConsoleView;

public class BatalhaNaval {
	
	public static void main(String[] args) {
		
		BatalhaNavalController controller = new BatalhaNavalController();
		ConsoleView view = new ConsoleView();
		String command = "";
		
		view.message("Digite 'ajuda' para comandos validos.");
		while (!command.equals("sair")) {
			command = view.read("Comando").toLowerCase();
			if (command.equals("jogar"))
				controller.inicializaJogo();
			if (command.equals("ajuda"))
				view.message("\nAjuda Jogar Sair");
		}
		
	}

}