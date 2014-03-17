import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {

	public void criaArquivo() {
	
		String diret= "C:\\ListaTelefonica";
		
		if(!new File(diret).exists()) {
		File dir = new File("C:\\ListaTelefonica");
			if (dir.mkdir()) {
				String arqui = "C:\\ListaTelefonica\\lista.txt";
				if(!new File(arqui).exists()) {
					File arq = new File("C:\\ListaTelefonica\\lista.txt");
						try {
							arq.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
		}
	
	}
	
	public void escreveArquivo() {
		
		FileWriter fw = null;
		try {
			fw = new FileWriter( "C:\\ListaTelefonica\\lista.txt", false );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedWriter bw = new BufferedWriter(fw);
		
		String[] contatos = {"Pudao - 32452312", "Clinkoz - 12452312", "Slarko - 22452312", "Mamae - 32452312", "Svenho - 32452314",
							 "SlardarMedi - 32452310", "Porcao - 32452318", "HadesMartin - 92452312", "Earth - 32452412", "SK - 38452312", 
							 "Natures - 32400312", "PL - 32452552", "Lion - 66452312", "Licao - 55452312", "Invoker - 33452312",
							 "Timber - 32222312", "Clock - 32452111", "Kunkinha - 32452000", "Omni - 99902312", "Ursa - 99902311"};
		
		try {
			for (int i = 0; i < 20; i++) {
				String contato = contatos[i];
				bw.append(contato + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deletarArquivo() {
		
		String arqui = "C:\\ListaTelefonica\\lista.txt";
		if(new File(arqui).exists()) {
			File arq = new File("C:\\ListaTelefonica\\lista.txt");
			arq.delete();
		}
	
	}
		
	public void deletarDiretorio() {
		
		String diret = "C:\\ListaTelefonica";
		if(new File(diret).exists()) {
			File dir = new File("C:\\ListaTelefonica");
			dir.delete();
		}
		
	}
	
}