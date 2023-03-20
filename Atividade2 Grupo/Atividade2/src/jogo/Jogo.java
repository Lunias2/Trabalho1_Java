package jogo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
	private ArrayList<ArrayList<Celula>> tabuleiro;
	private ArrayList<Peao> peoes;
	//private Scanner sc;
	private String vencedor;
	
	
	public Jogo() {
		
		peoes = new ArrayList<>();
		//sc = new Scanner(System.in);
		vencedor = null;
	}
	
	
	public void iniciarJogo() {

		for(int i =0; i < 4; i++) {
			Peao auxiliar = new Peao("Vermelho", 0, 0);
			peoes.add(auxiliar);
		}

		for(int i =0; i < 4; i++) {
			Peao auxiliar = new Peao("Azul", 0, 0);
			peoes.add(auxiliar);
		}
		ArrayList<Celula> celulas1 = new ArrayList<>();
		ArrayList<Celula> celulas2 = new ArrayList<>();
		ArrayList<Celula> celulas3 = new ArrayList<>();
		ArrayList<Celula> celulas4 = new ArrayList<>();
		tabuleiro = new ArrayList<>();
		tabuleiro.add(celulas1);
		tabuleiro.add(celulas2);
		tabuleiro.add(celulas3);
		tabuleiro.add(celulas4);

		for(int j = 0; j < 14; j++) {
			Celula auxiliar = new Celula(j);
			auxiliar.setCelulafinal(false);
			celulas1.add(auxiliar);
			if( j > 0 && j != 13)
				celulas1.get(j - 1).setCelulaSeguinte(celulas1.get(j));
		}
		
		celulas1.get(12).setCelulaSeguinte(celulas1.get(13));

		for(int j = 0; j < 14; j++) {
			Celula auxiliar = new Celula(j);
			auxiliar.setCelulafinal(false);
			celulas2.add(auxiliar);
			if( j > 0 && j != 13)
				celulas2.get(j - 1).setCelulaSeguinte(celulas2.get(j));
		}
		celulas2.get(12).setCelulaSeguinte(celulas2.get(13));
		celulas1.get(celulas1.size() - 1).setCelulaSeguinte(celulas2.get(0));

		for(int j = 0; j < 14; j++) {
			Celula auxiliar = new Celula(j);
			auxiliar.setCelulafinal(false);
			celulas3.add(auxiliar);
			if( j > 0 && j != 13)
				celulas3.get(j - 1).setCelulaSeguinte(celulas3.get(j));
		}
		celulas3.get(12).setCelulaSeguinte(celulas3.get(13));
		celulas2.get(celulas2.size() - 1).setCelulaSeguinte(celulas3.get(0));

		for(int j = 0; j < 14; j++) {
			Celula auxiliar = new Celula(j);
			auxiliar.setCelulafinal(false);
			celulas4.add(auxiliar);
			if( j > 0 && j != 13)
				celulas4.get(j - 1).setCelulaSeguinte(celulas4.get(j));
		}
		celulas4.get(12).setCelulaSeguinte(celulas4.get(13));
		celulas3.get(celulas3.size() - 1).setCelulaSeguinte(celulas4.get(0));
		
		celulas4.get(celulas4.size() - 1).setCelulafinal(true);

		for( int i = 0; i < 8; i++) {
			tabuleiro.get(0).get(0).addPeao( peoes.get(i));
		}
		
		mostrarTabuleiro();
	}

	public int rolarDado() {
		Random dado = new Random();
		return dado.nextInt(6) + 1;
	}

	public void moverPeca(int index) {
		int movimento = rolarDado();
		System.out.println("Peao [" + index + "] rolou [" + movimento + "]");
		Peao peaoAuxiliar = peoes.get(index);

		for( int i = 0; i < movimento; i++) {
			if(!tabuleiro.get( peaoAuxiliar.getLinha()).get(peaoAuxiliar.getColuna()).isCelulafinal()) {
				if ( tabuleiro.get( peaoAuxiliar.getLinha()).get(peaoAuxiliar.getColuna()).getCelulaSeguinte().verificaPeao(peaoAuxiliar)) {
					tabuleiro.get( peaoAuxiliar.getLinha()).get(peaoAuxiliar.getColuna()).getCelulaSeguinte().addPeao(peaoAuxiliar);
					tabuleiro.get( peaoAuxiliar.getLinha()).get(peaoAuxiliar.getColuna()).removePeao(peaoAuxiliar);
					mover(peaoAuxiliar);
					System.out.println(" Peao[" + index +"] moveu!!");
				} else {
					tabuleiro.get( peaoAuxiliar.getLinha()).get(peaoAuxiliar.getColuna()).removePeao(peaoAuxiliar);
					tabuleiro.get(0).get(0).addPeao(peaoAuxiliar);
					peaoAuxiliar.setLinha(0);
					peaoAuxiliar.setColuna(0);
					System.out.println("Voltou ao inicio");
				}
			} else {
				System.out.println("Pe�o[" + index + "] esta na �ltima casa, portanto, n�o moveu" );
				return;
			}
		}
	}
	
	public void mover(Peao peao) {
		int linha = peao.getLinha();
		int coluna = peao.getColuna();
		
		if( coluna + 1 > 13) {
			if( linha + 1 <= 3) {
				peao.setLinha(linha + 1);
				peao.setColuna(0);
			} else {
				peao.setLinha(3);
				peao.setColuna(13);
			}
		} else {
			peao.setLinha(linha);
			peao.setColuna(coluna + 1);
		}
	}
	
	public void mostrarTabuleiro() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 14; j++) {
				System.out.printf(" %5s", tabuleiro.get(i).get(j).getPeoes().size());
			}
			System.out.println();
		}
		for( int i = 0; i < 8; i++) {
			System.out.println("Peao [" + i + "] esta na posicao [" + peoes.get(i).getLinha() + "][" + peoes.get(i).getColuna() + "]");
		}
		
	}
	
	public boolean fimDeJogo() {
		if(tabuleiro.get(3).get(13).getPeoes().size() == 4) {
			vencedor = tabuleiro.get(3).get(13).getPeoes().get(0).getCor();
			return true;
		}
		return false;
	}
	
	/**
	 * @return the vencedor
	 */
	public String getVencedor() {
		return vencedor;
	}
	
	public void mostrarPeao(int index) {
		System.out.println("Peao [" + index + "] esta na posicao [" + peoes.get(index).getLinha() + "][" + peoes.get(index).getColuna() + "]");
	}
	
	public void mostrarLigacao() {
		for(int i = 0; i < 4; i++) {
			System.out.println("linha [" + i + "]");
			for(int j = 0; j < 14; j++) {
				System.out.println("Celula[" + tabuleiro.get(i).get(j).getPosicao() + "] ligadao a celula [" + tabuleiro.get(i).get(j).getCelulaSeguinte().getPosicao() + "]");
			}
			System.out.println();
			
		}
	}
	
	
}
