package com.senac.apps.ListaTelefonica.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import com.senac.apps.ListaTelefonica.model.Pessoa;
import com.senac.apps.ListaTelefonica.view.ConsoleView;
import com.senac.estruturas.Arvore;
import com.senac.estruturas.Nodo;

public class ListaController {
	private Arvore<Pessoa> contatos;
	private ConsoleView view;
	private Nodo<Pessoa> current;
	private int tamanho;

	public ListaController(ConsoleView view) {
		this.view = view;
		this.contatos = new Arvore<Pessoa>();
		this.current = null;
		this.tamanho = 0;
	}

	public void loadFile(String filename) {
		try {
			Scanner arq = new Scanner(new FileReader(filename));
			while(arq.hasNext()) {
				String name = arq.nextLine();
				String phone = arq.nextLine();
				Pessoa pessoa = new Pessoa(name);
				pessoa.setTelefone(phone);
				if (!name.startsWith("#"))
					contatos.insere(pessoa);
			}
			current = contatos.getRaiz();
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

	public void insertContato() {
		Pessoa contato = new Pessoa();
		contato.setNome(view.read("Nome"));
		contato.setTelefone(view.read("Telefone"));
		contatos.insere(contato);
		contatos.calculaAltura(contato);
		this.tamanho = 0;
		tamanhoArvore(contatos.getRaiz());
		System.out.println("Tamanho: " + tamanho);
	}
	
	private Nodo<Pessoa> procuraContato(Pessoa chave)
	{			
		return contatos.pesquisa(chave);
	}
	
	public void searchContato() {
		Pessoa search = new Pessoa();
		search.setNome(view.read("Nome").toLowerCase());
		Nodo<Pessoa> contato = procuraContato(search);
		if (contato != null) {
			current = contato;
			System.out.printf("\nNome: %s - Telefone: %s\n\nComparacoes = %d\n",
					current.getData().getNome(), current.getData().getTelefone(), contatos.getComparacoes());
		}
	}

	public void removeContato() {
		Pessoa delete = new Pessoa();
		delete.setNome(view.read("Nome").toLowerCase());
		Nodo<Pessoa> contato = procuraContato(delete);
		if (contato != null) {
			current = contato;
			showContato();
			String op = view.read("Deseja excluir? [S]im [N]ao");
			if (current != null && op.toLowerCase().equals("s")) {
				contatos.delete(current);
			}
		}
	}
	
	/* 
     * O método travesseia apaga um nó da árvore passado como 
     * parâmetro pela variável chave 
     */  
    public void travessia(String tipoTravessia) {  
        switch (tipoTravessia) {  
        case "prefix":  
            System.out.print("\nTravessia usando prefix: \n");  
            prefix(contatos.getRaiz());  
            break;  
        case "infix":  
            System.out.print("\nTravessia usando infix:  \n");  
            infix(contatos.getRaiz());  
            break;  
        case "posfix":  
            System.out.println("\nTravessia usando posfix: ");  
            posfix(contatos.getRaiz());  
            break;  
        }    
    }  
  
    /* 
     * O método preOrder  
     */  
    private void prefix(Nodo<Pessoa> localraiz) {  
        if (localraiz != null) {  
            view.printContato(localraiz.getData().getNome(), localraiz.getData().getTelefone());
//            contatos.calculaNivel(localraiz.getData());
            prefix(localraiz.getFilhoEsquerda());  
            prefix(localraiz.getFilhoDireita());  
        }  
    }  
  
    /* 
     * O método iNodorder  
     */  
    private void infix(Nodo<Pessoa> localraiz) {  
        if (localraiz != null) {  
        	infix(localraiz.getFilhoEsquerda());  
            view.printContato(localraiz.getData().getNome(), localraiz.getData().getTelefone());
//            contatos.calculaNivel(localraiz.getData());
            infix(localraiz.getFilhoDireita());  
        }  
    }  
  
    /* 
     * O método posOrder  
     */  
    private void posfix(Nodo<Pessoa> localraiz) {  
        if (localraiz != null) {  
        	posfix(localraiz.getFilhoEsquerda());  
        	posfix(localraiz.getFilhoDireita());  
            view.printContato(localraiz.getData().getNome(), localraiz.getData().getTelefone());
//            contatos.calculaNivel(localraiz.getData());
        }  
    }
    
    private void prefixSalva(Nodo<Pessoa> localraiz, FileWriter arq) {  
        if (localraiz != null) {  
        	try {
        	if (procuraContato(localraiz.getData()) == null)
				arq.append("#"+localraiz.getData().getNome()+"\n");
			else {
				arq.append(localraiz.getData().getNome()+"\n");
				arq.append(localraiz.getData().getTelefone()+"\n");
				}
        	}
			catch (IOException e) {
			view.message(e.getMessage());
			}
//            contatos.calculaNivel(localraiz.getData());
            prefixSalva(localraiz.getFilhoEsquerda(), arq);  
            prefixSalva(localraiz.getFilhoDireita(), arq);  
			}  
        }
    
	public void saveFile(String filename) {
		FileWriter arq = null;
		try {
			arq = new FileWriter(filename,false);
			Nodo<Pessoa> iter = contatos.getRaiz();
			prefixSalva(iter, arq);
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
	
	public void buscaLargura() {
		if(contatos.getRaiz() == null)
	        System.out.println("Arvore vazia!");
	    else
	    {
	        Queue<Nodo<Pessoa>> q= new LinkedList<Nodo<Pessoa>>();
	        q.add(contatos.getRaiz());
	        while(q.peek() != null)
	        {
	        	Nodo<Pessoa> temp = q.remove();
	        	view.printContato(temp.getData().getNome(), temp.getData().getTelefone());
	            if(temp.getFilhoEsquerda() != null)
	                q.add(temp.getFilhoEsquerda());
	            if(temp.getFilhoDireita() != null)
	                q.add(temp.getFilhoDireita());
	        }
	    }
	}
	
	public void buscaProfundidade() {
		
		if(contatos.getRaiz() == null)
	        System.out.println("Arvore vazia!");
	    else
	    {
	        Stack<Nodo<Pessoa>> q= new Stack<Nodo<Pessoa>>();
	        q.push(contatos.getRaiz());
	        while(!q.empty())
	        {
	        	Nodo<Pessoa> temp = q.pop();
	        	view.printContato(temp.getData().getNome(), temp.getData().getTelefone());
	            if(temp.getFilhoEsquerda() != null)
	                q.push(temp.getFilhoEsquerda());
	            if(temp.getFilhoDireita() != null)
	                q.push(temp.getFilhoDireita());
	        }
	    }
	}
	
	public void tamanhoArvore(Nodo<Pessoa> localraiz) {  		
		if (localraiz != null) {  
            this.tamanho++;
            tamanhoArvore(localraiz.getFilhoEsquerda());  
            tamanhoArvore(localraiz.getFilhoDireita());
        }  
    } 
}