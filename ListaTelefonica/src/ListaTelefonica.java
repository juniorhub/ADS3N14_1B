import java.io.BufferedReader;
import java.io.FileReader;

public class ListaTelefonica {
	
	public static void main(String[] args) {
		
		/*Arquivo arquivo = new Arquivo();
		
		arquivo.deletarArquivo();
		arquivo.deletarDiretorio();
		arquivo.criaArquivo();
		arquivo.escreveArquivo();*/
		
		ListaOrdenada<String> contatos = new ListaOrdenada<String>();
	
		try {
		
		FileReader arq = new FileReader("C:\\ListaTelefonica\\lista.txt");
		BufferedReader lerArq = new BufferedReader(arq);

		Nodo<String> novoContato = new Nodo<String>(lerArq.readLine());
		
		contatos.insert(novoContato);
		
		for (int i = 0; i < 19; i++) {
			
		Nodo<String> proximosContatos = new Nodo<String>(lerArq.readLine());
		contatos.append(proximosContatos);
		
		}
		
		arq.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		contatos.print();
	}
	
}