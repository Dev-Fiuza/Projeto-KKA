package kosmo_k.entidades;

import java.awt.image.BufferedImage;

public class Jogador extends Entidade {

	// Variáveis de direção
	private boolean direita;
	private boolean esquerda;
	private boolean cima;
	private boolean baixo;

	private int velocidade = 2;

	public Jogador(int x, int y, int largura, int altura, BufferedImage sprite) {
		super(x, y, largura, altura, sprite);
	}

	public boolean isDireita() {
		return direita;
	}

	public void setDireita(boolean direita) {
		this.direita = direita;
	}

	public boolean isEsquerda() {
		return esquerda;
	}

	public void setEsquerda(boolean esquerda) {
		this.esquerda = esquerda;
	}

	public boolean isCima() {
		return cima;
	}

	public void setCima(boolean cima) {
		this.cima = cima;
	}

	public boolean isBaixo() {
		return baixo;
	}

	public void setBaixo(boolean baixo) {
		this.baixo = baixo;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public void tick() {
		// Configuração de movimentação do jogador
		if (direita) {
			movimentarDireita(velocidade);
		} else if (esquerda) {
			movimentarEsquerda(velocidade);
		}

		if (cima) {
			movimentarCima(velocidade);
		} else if (baixo) {
			movimentarBaixo(velocidade);
		}

		///////////////
	}

}
