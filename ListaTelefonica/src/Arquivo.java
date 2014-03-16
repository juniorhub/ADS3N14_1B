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
			fw = new FileWriter( "C:\\ListaTelefonica\\lista.txt", true );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedWriter bw = new BufferedWriter(fw);
		
		try {
			bw.write("Teste");
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