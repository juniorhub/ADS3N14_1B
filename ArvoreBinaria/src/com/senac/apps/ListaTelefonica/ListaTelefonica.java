//Rafael Goncalves Rodrigues

package com.senac.apps.ListaTelefonica;

import com.senac.apps.ListaTelefonica.controller.ListaController;
import com.senac.apps.ListaTelefonica.view.ConsoleView;

public class ListaTelefonica {
	
	public static void main(String[] args) {
		ConsoleView view = new ConsoleView();
		ListaController controller = new ListaController(view);
		String command = "";
		
		controller.loadFile("telefones.dat");
		view.message("Digite 'ajuda' para comandos validos.");
		while (!command.equals("sair")) {
			command = view.read("Comando").toLowerCase();
			if (command.equals("inserir"))
				controller.insertContato();
			if (command.equals("excluir"))
				controller.removeContato();
			if (command.equals("procurar"))
				controller.searchContato();
			if (command.equals("prefix"))
				controller.travessia("prefix");
			if (command.equals("infix"))
				controller.travessia("infix");
			if (command.equals("posfix"))
				controller.travessia("posfix");
			if (command.equals("buscalargura"))
				controller.buscaLargura();
			if (command.equals("buscaprofundidade"))
				controller.buscaProfundidade();
			if (command.equals("ajuda"))
				view.message("\nAjuda  Inserir  Excluir  Procurar  Prefix  Infix  Posfix  BuscaLargura  BuscaProfundidade Sair");
		}
		controller.saveFile("telefones.dat");
	}

}
