package com.senac.estruturas;

import com.senac.estruturas.Nodo;

public class Arvore<T extends Comparable<T>> {

	int comparacoes;

	private Nodo<T> raiz; // o único campo de dado em Arvore
	
	public int getComparacoes() {
		return comparacoes;
	}
	public void setComparacoes(int comparacoes) {
		this.comparacoes = comparacoes;
	}
	
	public Nodo<T> getRaiz() {
		return raiz;
	}
	public void setRaiz(Nodo<T> raiz) {
		this.raiz = raiz;
	}
	  
    public Arvore() { // construtor  
        raiz = null;  //nenhum nó na arvore  
    } 
    
    /* 
     * O método pesquisa busca na arvore um nó com base 
     * na chave que lhe é passado pelo parâmetro chave 
     */   
    public Nodo<T> pesquisa(T nome)  
    { // assume-se que a árvore não está vazia  
        Nodo<T> atual = raiz; // começa na raiz
        comparacoes = 0;
		
		while (atual != null) {
			T nomeAtual = atual.getData();
			int cmp = nome.compareTo(nomeAtual);
			comparacoes++;
			if (cmp == 0) {
				return atual;
			}
			if (cmp < 0) {
				atual = atual.getFilhoEsquerda();
			}
			if (cmp > 0) {
				atual = atual.getFilhoDireita();
			}
	}
		
		return atual;
		
    }
    
    /* 
     * O método pesquisa busca na arvore um nó com base 
     * na chave que lhe é passado pelo parâmetro chave 
     */   
    public void calculaAltura(T nome)  
    { // assume-se que a árvore não está vazia  
        Nodo<T> atual = raiz; // começa na raiz
        int altura = 0; // Mostra o nível do nodo
		
		while (atual != null) {
			T nomeAtual = atual.getData();
			int cmp = nome.compareTo(nomeAtual);
			if (cmp == 0) {
				altura++;
				break;
			}
			if (cmp < 0) {
				altura++;
				atual = atual.getFilhoEsquerda();
			}
			if (cmp > 0) {
				altura++;
				atual = atual.getFilhoDireita();
			}
	}
		
		System.out.println("\nAltura: " + altura);
		  
    } // fim do método pesquisa  
    /* 
     * O método insere insere um nó na arvore, recebendo como parâmetro 
     * os dados do nó 
     */   
    public void insere(T pessoa) {  
    	Nodo<T> novo = new Nodo<T>(); // cria Nodovo nó
        novo.setData(pessoa);
        if (raiz == null) { // sem nó na raiz  
            raiz = novo;
        }
        else // raiz ocupada  
        {
        	Nodo<T> atual = raiz; // começa na raiz  
            Nodo<T> parente;
            
            while (true) // (sai internamente)  
            {  
                parente = atual;
                T parentePesquisa = parente.getData();
                int cmp = pessoa.compareTo(parentePesquisa);
               
    			if (cmp < 0) { // vai para esquerda?			
    				atual = atual.getFilhoEsquerda();
    				if (atual == null) // se fim da linha,  
    				{ // insere a esquerda  
                      parente.setFilhoEsquerda(novo);
                      return;  
    				}
    			
    			} else // ou vai para direita?
    			
    			if (cmp > 0) {
    				atual = atual.getFilhoDireita();
                  	if (atual == null) // if end of the line  
                    { // insert on right  
                        parente.setFilhoDireita(novo);
                        return;  
                    }  
    			
    			} // fim do else ir para direita  
            } // fim do while  
        } // fim do else não raiz  
    } // fim do método insere  
  
    /* 
     * O método delete apaga um nó da árvore passado como 
     * parâmetro pela variável chave 
     */  
    public boolean delete(Nodo<T> nodo)  
    { // assume arvore não vazia  
        Nodo<T> atual = raiz;  
        Nodo<T> parente = raiz;  
        boolean eFilhoEsquerda = true;  
        T nome = nodo.getData();
  
        while (atual.getData() != nodo.getData()) // busca nó  
        {  
            parente = atual;
            
            T nomeAtual = atual.getData();
			int cmp = nome.compareTo(nomeAtual);
			    
            if (cmp < 0) // vai para esquerda?  
            {  
                eFilhoEsquerda = true;  
                atual = atual.getFilhoEsquerda();  
            } else // ou para direita?  
            {  
                eFilhoEsquerda = false;  
                atual = atual.getFilhoDireita();  
            }  
            if (atual == null) // fim da linha  
                return false; // não o encontrou  
        } // fim do while  
        // encontrou nó para eliminar  
  
        // se não há filho, simplesmente elimina-o  
        if (atual.getFilhoEsquerda() == null && atual.getFilhoDireita() == null) {  
            if (atual == raiz) // se raiz,  
                raiz = null; // a árvore está vazia  
            else if (eFilhoEsquerda)  
                parente.setFilhoEsquerda(null); // desconecta  
            else  
                // from parent  
                parente.setFilhoDireita(null);  
        }  
  
        // se não há filho à direita, substitui por subárvore à esquerda  
        else if (atual.getFilhoDireita() == null)  
            if (atual == raiz)  
                raiz = atual.getFilhoDireita();  
            else if (eFilhoEsquerda)        // filho a esquerda do pai  
                parente.setFilhoDireita(atual.getFilhoEsquerda());  
            else                            // filho a direita do pai  
                parente.setFilhoDireita(atual.getFilhoDireita());  
  
        // se não há filho à esquerda, substitui por subárvore à direita  
        else if (atual.getFilhoEsquerda() == null)  
            if (atual == raiz)  
                raiz = atual.getFilhoDireita();  
            else if (eFilhoEsquerda)        // filho a esquerda do pai  
                parente.setFilhoEsquerda(atual.getFilhoDireita());  
            else                            // filho a direita do pai  
                parente.setFilhoDireita(atual.getFilhoDireita());  
  
        else // dois filhos, assim substitua-o com o sucessor iNodorder  
        {  
            // pega o sucessor do Nodo para deletar o atual  
            Nodo<T> successor = getSuccessor(atual);  
  
            // connecta parente do atual ao successor  
            if (atual == raiz)  
                raiz = successor;  
            else if (eFilhoEsquerda)  
                parente.setFilhoEsquerda(successor);  
            else  
                parente.setFilhoDireita(successor);  
  
            // conecte sucessor ao filho à esquerda do atual  
            successor.setFilhoEsquerda(atual.getFilhoEsquerda());  
        } // fim do else dois filhos  
        // (sucessor não pode ter filho à esquerda)  
        return true; // sucesso  
    } // fim do método delete()  
    // -------------------------------------------------------------  
  
    // retorna nó com próximo valor mais alto depois de deleteNodo  
    // vai para filho à direita, então para descendentes à esquerda  
    private Nodo<T> getSuccessor(Nodo<T> deleteNodo) {  
        Nodo<T> sucessorParente = deleteNodo;  
        Nodo<T> sucessor = deleteNodo;  
        Nodo<T> atual = deleteNodo.getFilhoDireita(); // vai para filho à direita  
        while (atual != null){ // até não mais filhos a esquerda  
            sucessorParente = sucessor;  
            sucessor = atual;  
            atual = atual.getFilhoDireita(); // vai para filho à esquerda  
        }       // se sucessor não é filho à direita faz conexão  
        if (sucessor != deleteNodo.getFilhoDireita()){  
            sucessorParente.setFilhoDireita(sucessor.getFilhoDireita());  
            sucessor.setFilhoDireita(deleteNodo.getFilhoDireita());  
        }  
        return sucessor;  
    }  
}