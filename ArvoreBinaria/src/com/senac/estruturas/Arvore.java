package com.senac.estruturas;

import com.senac.estruturas.Nodo;

public class Arvore<T extends Comparable<T>> {

	int comparacoes;

	private Nodo<T> raiz; // o �nico campo de dado em Arvore
	
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
        raiz = null;  //nenhum n� na arvore  
    } 
    
    /* 
     * O m�todo pesquisa busca na arvore um n� com base 
     * na chave que lhe � passado pelo par�metro chave 
     */   
    public Nodo<T> pesquisa(T nome)  
    { // assume-se que a �rvore n�o est� vazia  
        Nodo<T> atual = raiz; // come�a na raiz
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
     * O m�todo pesquisa busca na arvore um n� com base 
     * na chave que lhe � passado pelo par�metro chave 
     */   
    public void calculaAltura(T nome)  
    { // assume-se que a �rvore n�o est� vazia  
        Nodo<T> atual = raiz; // come�a na raiz
        int altura = 0; // Mostra o n�vel do nodo
		
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
		  
    } // fim do m�todo pesquisa  
    /* 
     * O m�todo insere insere um n� na arvore, recebendo como par�metro 
     * os dados do n� 
     */   
    public void insere(T pessoa) {  
    	Nodo<T> novo = new Nodo<T>(); // cria Nodovo n�
        novo.setData(pessoa);
        if (raiz == null) { // sem n� na raiz  
            raiz = novo;
        }
        else // raiz ocupada  
        {
        	Nodo<T> atual = raiz; // come�a na raiz  
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
        } // fim do else n�o raiz  
    } // fim do m�todo insere  
  
    /* 
     * O m�todo delete apaga um n� da �rvore passado como 
     * par�metro pela vari�vel chave 
     */  
    public boolean delete(Nodo<T> nodo)  
    { // assume arvore n�o vazia  
        Nodo<T> atual = raiz;  
        Nodo<T> parente = raiz;  
        boolean eFilhoEsquerda = true;  
        T nome = nodo.getData();
  
        while (atual.getData() != nodo.getData()) // busca n�  
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
                return false; // n�o o encontrou  
        } // fim do while  
        // encontrou n� para eliminar  
  
        // se n�o h� filho, simplesmente elimina-o  
        if (atual.getFilhoEsquerda() == null && atual.getFilhoDireita() == null) {  
            if (atual == raiz) // se raiz,  
                raiz = null; // a �rvore est� vazia  
            else if (eFilhoEsquerda)  
                parente.setFilhoEsquerda(null); // desconecta  
            else  
                // from parent  
                parente.setFilhoDireita(null);  
        }  
  
        // se n�o h� filho � direita, substitui por sub�rvore � esquerda  
        else if (atual.getFilhoDireita() == null)  
            if (atual == raiz)  
                raiz = atual.getFilhoDireita();  
            else if (eFilhoEsquerda)        // filho a esquerda do pai  
                parente.setFilhoDireita(atual.getFilhoEsquerda());  
            else                            // filho a direita do pai  
                parente.setFilhoDireita(atual.getFilhoDireita());  
  
        // se n�o h� filho � esquerda, substitui por sub�rvore � direita  
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
  
            // conecte sucessor ao filho � esquerda do atual  
            successor.setFilhoEsquerda(atual.getFilhoEsquerda());  
        } // fim do else dois filhos  
        // (sucessor n�o pode ter filho � esquerda)  
        return true; // sucesso  
    } // fim do m�todo delete()  
    // -------------------------------------------------------------  
  
    // retorna n� com pr�ximo valor mais alto depois de deleteNodo  
    // vai para filho � direita, ent�o para descendentes � esquerda  
    private Nodo<T> getSuccessor(Nodo<T> deleteNodo) {  
        Nodo<T> sucessorParente = deleteNodo;  
        Nodo<T> sucessor = deleteNodo;  
        Nodo<T> atual = deleteNodo.getFilhoDireita(); // vai para filho � direita  
        while (atual != null){ // at� n�o mais filhos a esquerda  
            sucessorParente = sucessor;  
            sucessor = atual;  
            atual = atual.getFilhoDireita(); // vai para filho � esquerda  
        }       // se sucessor n�o � filho � direita faz conex�o  
        if (sucessor != deleteNodo.getFilhoDireita()){  
            sucessorParente.setFilhoDireita(sucessor.getFilhoDireita());  
            sucessor.setFilhoDireita(deleteNodo.getFilhoDireita());  
        }  
        return sucessor;  
    }  
}