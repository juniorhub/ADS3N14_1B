package com.senac.apps.ListaTelefonica.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.senac.apps.ListaTelefonica.model.Pessoa;
import com.senac.apps.ListaTelefonica.view.ConsoleView;
import com.senac.estruturas.ListaEncadeada;
import com.senac.estruturas.ListaOrdenada;
import com.senac.estruturas.Nodo;

public class ListaController {
	private ListaEncadeada<Pessoa> arquivo;
	private ListaOrdenada<Pessoa> contatos;
	private ArrayList<Pessoa> lista;
	private ConsoleView view;
	private Nodo<Pessoa> current;
	int comparacoes;
	
	public ListaController(ConsoleView view) {
		this.view = view;
		this.contatos = new ListaOrdenada<Pessoa>();
		this.arquivo  = new ListaEncadeada<Pessoa>();
		this.lista = new ArrayList<Pessoa>();
		this.current = null;
	}

	public void loadFile(String filename) {
		try {
			Scanner arq = new Scanner(new FileReader(filename));
			while(arq.hasNext()) {
				String name = arq.nextLine();
				String phone = arq.nextLine();
				Pessoa pessoa = new Pessoa(name);
				pessoa.setTelefone(phone);
				arquivo.insert(new Nodo<Pessoa>(pessoa));
				if (!name.startsWith("#"))
					contatos.insert(new Nodo<Pessoa>(pessoa));
			}
			current = contatos.getHead();
			while (current != null) {
				lista.add(current.getData());
				current = current.getNext();
			}
			arq.close();
		} catch (FileNotFoundException e) {
			view.logError(e.getMessage());
		}
	}

	public void showContato() {
		if (current == null) {
			view.message("\nNenhum contato existente.");
		} else {
			Pessoa contato = current.getData();
			view.printContato(contato.getNome(), contato.getTelefone());
		}
	}

	public void nextContato() {
		if (current != null) {
			current = current.getNext();
			if (current == null)
				current = contatos.getHead();
		}
		showContato();
	}

	public void previousContato() {
		if (current != null) {
			current = current.getPrevious();
			if (current == null)
				current = contatos.getTail();
		}
		showContato();
	}

	public void insertContato() {
		Pessoa contato = new Pessoa();
		contato.setNome(view.read("Nome"));
		contato.setTelefone(view.read("Telefone"));
		Nodo<Pessoa> novo = new Nodo<Pessoa>(contato);
		contatos.insert(novo);
		arquivo.append(new Nodo<Pessoa>(contato));
		current = novo;
	}

	public void removeContato() {
		String chave = view.read("Inicio do Nome").toLowerCase();
		Nodo<Pessoa> contato = procuraContato(contatos, chave);
		if (contato != null) {
			current = contato;
			showContato();
			String op = view.read("Deseja excluir? [S]im [N]ao");
			if (current != null && op.toLowerCase().equals("s")) {
				contatos.remove(current);
			}
		}
	}

	private Nodo<Pessoa> procuraContato(ListaEncadeada<Pessoa> lista, String chave)
	{
		Nodo<Pessoa> iter = lista.getHead();
		comparacoes = 0;
		while (iter != null) {
			comparacoes++;
			Pessoa contato = iter.getData();
			String nome = contato.getNome().toLowerCase();
			if (nome.startsWith(chave)) {
				return iter;
			}
			iter = iter.getNext();
		}
		return null;
	}
	
	public void searchContato() {
		String chave = view.read("Inicio do Nome").toLowerCase();
		Nodo<Pessoa> contato = procuraContato(contatos, chave);
		if (contato != null) {
			current = contato;
			System.out.printf("\nNome: %s - Telefone: %s - Comparacoes: %s\n",
					current.getData().getNome(), current.getData().getTelefone(), comparacoes);
		}
	}
	
	public void searchContatoBinario() {
		String chave = view.read("Inicio do Nome").toLowerCase();
		Pessoa contato = procuraContatoBinario(lista, chave);
		if (contato != null) {
			System.out.printf("\nNome: %s - Telefone: %s - Comparacoes: %s\n",
					contato.getNome(), contato.getTelefone(), comparacoes);
		}
	}
	
	private Pessoa procuraContatoBinario(ArrayList<Pessoa> lista, String chave) {
		int esq = 0;
		int dir = lista.size() - 1;
		int meio;
		comparacoes = 0;

		while (esq <= dir) {
			comparacoes++;
			meio = esq + ((dir - esq) / 2);
			
			if (lista.get(meio).getNome().substring(0,1).compareTo(chave) > 0) 
				dir = meio - 1;
			else if (lista.get(meio).getNome().substring(0,1).compareTo(chave) < 0)
				esq = meio + 1;
			else
				return lista.get(meio);
		}
		
		return null;
	}
	
	public void listarContato() {
		Nodo<Pessoa> iter = contatos.getHead();
		if (contatos != null) {
			while (iter != null) {
				view.printContato(iter.getData().getNome(), iter.getData().getTelefone());
				iter = iter.getNext();
			}
		}
	}

	public void saveFile(String filename) {
		FileWriter arq = null;
		try {
			arq = new FileWriter(filename,false);
			Nodo<Pessoa> iter = arquivo.getHead();
			while (iter != null) {
				Pessoa contato = iter.getData();
				if (procuraContato(contatos, contato.getNome()) == null)
					arq.append("#"+contato.getNome()+"\n");
				else
					arq.append(contato.getNome()+"\n");
				arq.append(contato.getTelefone()+"\n");
				iter = iter.getNext();
			}
		} catch (IOException e) {
			view.message(e.getMessage());
		} finally {
			if (arq != null)
				try {
					arq.close();
				} catch (IOException e) {
					view.message(e.getMessage());
				}
		}
	}

}