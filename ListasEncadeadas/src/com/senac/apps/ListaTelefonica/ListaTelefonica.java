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
			if (command.equals("avancar"))
				controller.nextContato();
			if (command.equals("voltar"))
				controller.previousContato();
			if (command.equals("inserir"))
				controller.insertContato();
			if (command.equals("excluir"))
				controller.removeContato();
			if (command.equals("procurar"))
				controller.searchContato();
			if (command.equals("listar"))
				controller.listarContato();
			if (command.equals("procurarotimizado"))
				controller.searchContatoBinario();
			if (command.equals("ajuda"))
				view.message("\nAjuda  Listar  Avancar  Voltar  Inserir  Excluir  Procurar ProcurarOtimizado Sair");
		}
		controller.saveFile("telefones.dat");
	}

}
