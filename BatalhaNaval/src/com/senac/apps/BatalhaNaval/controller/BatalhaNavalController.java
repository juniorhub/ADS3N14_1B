package com.senac.apps.BatalhaNaval.controller;

import java.util.Random;

import com.senac.apps.BatalhaNaval.model.Navio;
import com.senac.apps.BatalhaNaval.view.ConsoleView;

public class BatalhaNavalController {

	private final static int ROWS = 10;
	private final static int COLUMNS = 10;
	private Navio[][] tabuleiro = new Navio[ROWS][COLUMNS];
	private ConsoleView view = new ConsoleView();
	private Navio padrao = new Navio();
	private Random random = new Random();
	private int linhaTabuleiro[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private String colunaTabuleiro[] = { "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J" };
	private int pontos = 15;
	private int fim = 30;
	private int p = 5, d1 = 4, d2 = 4, f1 = 3, f2 = 3, t1 = 2, t2 = 2, t3 = 2,
			s1 = 1, s2 = 1, s3 = 1, s4 = 1, s5 = 1;

	public void inicializaJogo() {

		criaTabuleiro();
		insereNavios(5, "P");
		for (int i = 0; i < 2; i++) {
			insereNavios(4, "D");
			insereNavios(3, "F");
		}
		for (int i = 0; i < 3; i++) {
			insereNavios(2, "T");
		}
		for (int i = 0; i < 5; i++) {
			insereNavios(1, "S");
		}

		controlaJogo();

	}

	private void controlaJogo() {
		do {
			mostraTabuleiro();
			ataque();
		} while (this.fim != 0 && this.pontos != 0);
	}

	private void mostraTabuleiro() {
		// Leitura de Dados
		view.message("\nPontos: " + this.pontos + "\n");
		System.out.print("  ");
		for (int i = 0; i < colunaTabuleiro.length; i++) {
			System.out.print(colunaTabuleiro[i] + " ");
		}
		for (int x = 0; x < tabuleiro.length; x++) {
			System.out.println();
			System.out.print(linhaTabuleiro[x] + " ");
			for (int y = 0; y < tabuleiro[x].length; y++) {
				System.out.print(tabuleiro[x][y].getPadrao() + " ");
			}
		}
		System.out.println();
	}

	private void mostraTipoTabuleiro() {
		// Leitura de Dados
		view.message("\nPontos: " + this.pontos + "\n");
		System.out.print("  ");
		for (int i = 0; i < colunaTabuleiro.length; i++) {
			System.out.print(colunaTabuleiro[i] + " ");
		}
		for (int x = 0; x < tabuleiro.length; x++) {
			System.out.println();
			System.out.print(linhaTabuleiro[x] + " ");
			for (int y = 0; y < tabuleiro[x].length; y++) {
				if (tabuleiro[x][y].getTipo() == null)
					System.out.print(tabuleiro[x][y].getPadrao() + " ");
				else
					System.out.print(tabuleiro[x][y].getTipo() + " ");
			}
		}
		System.out.println("");
	}

	private void criaTabuleiro() {
		// Escrita de Dados
		for (int x = 0; x < tabuleiro.length; x++)
			for (int y = 0; y < tabuleiro[x].length; y++)
				tabuleiro[x][y] = padrao;
	}

	private void insereNavios(int tamanho, String tipo) {

		char cont = 'n';
		boolean fim = false;

		do {

			int p = random.nextInt(2);

			if (p == 0) {

				int h = random.nextInt(6);
				int v = random.nextInt(10);

				int z = h + tamanho;

				for (int x = h; x < z; x++) {
					if (tabuleiro[x][v].getTipo() != null) {
						break;
					} else if (x == (z - 1)) {
						cont = 's';
					}
				}

				if (cont == 's') {
					for (int x = h; x < (h + tamanho); x++) {
						Navio navio = new Navio();
						navio.setTipo(tipo);
						tabuleiro[x][v] = navio;
					}

					fim = true;

				}

			} else {

				int h = random.nextInt(10);
				int v = random.nextInt(6);

				int z = v + tamanho;

				for (int y = v; y < z; y++) {
					if (tabuleiro[h][y].getTipo() != null) {
						break;
					} else if (y == (z - 1)) {
						cont = 's';
					}
				}

				if (cont == 's') {
					for (int y = v; y < (v + tamanho); y++) {
						Navio navio = new Navio();
						navio.setTipo(tipo);
						tabuleiro[h][y] = navio;
					}

					fim = true;

				}

			}

		} while (fim == false);

	}

	private void ataque() {

		int linha = Integer.valueOf(view.read("Linha"));
		String coluna = view.read("Coluna");
		int colunai = 0;

		switch (coluna.toLowerCase()) {

		case "a":
			colunai = 0;
			break;
		case "b":
			colunai = 1;
			break;
		case "c":
			colunai = 2;
			break;
		case "d":
			colunai = 3;
			break;
		case "e":
			colunai = 4;
			break;
		case "f":
			colunai = 5;
			break;
		case "g":
			colunai = 6;
			break;
		case "h":
			colunai = 7;
			break;
		case "i":
			colunai = 8;
			break;
		case "j":
			colunai = 9;
			break;
		default:
			view.message("\nEsta nao e uma coluna valida");
			break;
		}

		if (tabuleiro[linha][colunai].getTipo() != null) {
			if (tabuleiro[linha][colunai].getTipo() == "P") {
				view.message("\nVoce acertou um Porta-Avioes e ganhou 3 pontos!");
				this.p--;
			} else if (tabuleiro[linha][colunai].getTipo() == "D"
					&& this.d1 != 0) {
				view.message("\nVoce acertou um Destroyer e ganhou 3 pontos!");
				this.d1--;
			} else if (tabuleiro[linha][colunai].getTipo() == "D"
					&& this.d2 != 0) {
				view.message("\nVoce acertou um Destroyer e ganhou 3 pontos!");
				this.d2--;
			} else if (tabuleiro[linha][colunai].getTipo() == "F"
					&& this.f1 != 0) {
				view.message("\nVoce acertou uma Fragata e ganhou 3 pontos!");
				this.f1--;
			} else if (tabuleiro[linha][colunai].getTipo() == "F"
					&& this.f2 != 0) {
				view.message("\nVoce acertou uma Fragata e ganhou 3 pontos!");
				this.f2--;
			} else if (tabuleiro[linha][colunai].getTipo() == "T"
					&& this.t1 != 0) {
				view.message("\nVoce acertou um Torpedeiro e ganhou 3 pontos!");
				this.t1--;
			} else if (tabuleiro[linha][colunai].getTipo() == "T"
					&& this.t2 != 0) {
				view.message("\nVoce acertou um Torpedeiro e ganhou 3 pontos!");
				this.t2--;
			} else if (tabuleiro[linha][colunai].getTipo() == "T"
					&& this.t3 != 0) {
				view.message("\nVoce acertou um Torpedeiro e ganhou 3 pontos!");
				this.t3--;
			} else if (tabuleiro[linha][colunai].getTipo() == "S"
					&& this.s1 != 0) {
				view.message("\nVoce acertou um Submarino e ganhou 3 pontos!");
				this.s1--;
			} else if (tabuleiro[linha][colunai].getTipo() == "S"
					&& this.s2 != 0) {
				view.message("\nVoce acertou um Submarino e ganhou 3 pontos!");
				this.s2--;
			} else if (tabuleiro[linha][colunai].getTipo() == "S"
					&& this.s3 != 0) {
				view.message("\nVoce acertou um Submarino e ganhou 3 pontos!");
				this.s3--;
			} else if (tabuleiro[linha][colunai].getTipo() == "S"
					&& this.s4 != 0) {
				view.message("\nVoce acertou um Submarino e ganhou 3 pontos!");
				this.s4--;
			} else if (tabuleiro[linha][colunai].getTipo() == "S"
					&& this.s5 != 0) {
				view.message("\nVoce acertou um Submarino e ganhou 3 pontos!");
				this.s5--;
			}

			if (this.p == 0) {
				view.message("Voce destruiu um Porta-Avioes e ganhou 5 pontos!");
				p = 500;
				this.pontos += 5;
			} else if (this.d1 == 0) {
				view.message("Voce destruiu um Destroyer e ganhou 5 pontos!");
				d1 = 500;
				this.pontos += 5;
			} else if (this.d2 == 0) {
				view.message("Voce destruiu um Destroyer e ganhou 5 pontos!");
				d2 = 500;
				this.pontos += 5;
			} else if (this.f1 == 0) {
				view.message("Voce destruiu uma Fragata e ganhou 5 pontos!");
				f1 = 500;
				this.pontos += 5;
			} else if (this.f2 == 0) {
				view.message("Voce destruiu uma Fragata e ganhou 5 pontos!");
				f2 = 500;
				this.pontos += 5;
			} else if (this.t1 == 0) {
				view.message("Voce destruiu um Torpedeiro e ganhou 5 pontos!");
				t1 = 500;
				this.pontos += 5;
			} else if (this.t2 == 0) {
				view.message("Voce destruiu um Torpedeiro e ganhou 5 pontos!");
				t2 = 500;
				this.pontos += 5;
			} else if (this.t3 == 0) {
				view.message("Voce destruiu um Torpedeiro e ganhou 5 pontos!");
				t3 = 500;
				this.pontos += 5;
			} else if (this.s1 == 0) {
				view.message("Voce destruiu um Submarino e ganhou 5 pontos!");
				s1 = 500;
				this.pontos += 5;
			} else if (this.s2 == 0) {
				view.message("Voce destruiu um Submarino e ganhou 5 pontos!");
				s2 = 500;
				this.pontos += 5;
			} else if (this.s3 == 0) {
				view.message("Voce destruiu um Submarino e ganhou 5 pontos!");
				s3 = 500;
				this.pontos += 5;
			} else if (this.s4 == 0) {
				view.message("Voce destruiu um Submarino e ganhou 5 pontos!");
				s4 = 500;
				this.pontos += 5;
			} else if (this.s5 == 0) {
				view.message("Voce destruiu um Submarino e ganhou 5 pontos!");
				s5 = 500;
				this.pontos += 5;
			}
			tabuleiro[linha][colunai].setPadrao("O");
			this.pontos--;
			this.pontos += 3;
			this.fim--;
			if (this.fim == 0 && this.pontos > 0) {
				view.message("\nVocê venceu o jogo!");
				mostraTipoTabuleiro();
			}
		} else {
			Navio vazio = new Navio();
			vazio.setPadrao("-");
			tabuleiro[linha][colunai] = vazio;
			this.pontos--;
			view.message("\nErrou!");
			if (this.pontos == 0) {
				view.message("\nVocê perdeu o jogo!");
				mostraTipoTabuleiro();
			}
		}

	}

}