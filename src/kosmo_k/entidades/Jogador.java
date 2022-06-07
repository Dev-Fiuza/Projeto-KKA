package kosmo_k.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import kosmo_k.jogo.Jogo;

public class Jogador extends Entidade {

	// Variáveis para sinalizar qual direção vamos andar
	private boolean direita, esquerda, cima, baixo;
	// Variáveis para controlar a direção que sprite está olhando
	private int direitaD = 1;
	private int esquerdaD = 2;
	private int cimaD = 3;
	private int baixoD = 4;
	private int direcao = baixoD;

	private double velocidade = 2.5;

	// Controle da Sprite
	private int maxFrames = 4;
	private int frames = 0;
	private int indiceAnimacao = 0;
	private int maxIndice = 8;
	private BufferedImage[] jogadorDireita = new BufferedImage[9];
	private BufferedImage[] jogadorEsquerda = new BufferedImage[9];
	private BufferedImage[] jogadorCima = new BufferedImage[9];
	private BufferedImage[] jogadorBaixo = new BufferedImage[9];
	private boolean movimento;

	public Jogador(int x, int y, int largura, int altura, BufferedImage sprite) {
		super(x, y, largura, altura, sprite);

		for (int i = 0; i < 9; i++) {
			jogadorDireita[i] = Jogo.spritesheet.getSprite(0 + (i * 64), 192, 64, 64);
		}
		for (int i = 0; i < 9; i++) {
			jogadorEsquerda[i] = Jogo.spritesheet.getSprite(0 + (i * 64), 64, 64, 64);
		}
		for (int i = 0; i < 9; i++) {
			jogadorCima[i] = Jogo.spritesheet.getSprite(0 + (i * 64), 0, 64, 64);
		}
		for (int i = 0; i < 9; i++) {
			jogadorBaixo[i] = Jogo.spritesheet.getSprite(0 + (i * 64), 128, 64, 64);
		}

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

	public double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public void tick() {
		// Configuração de movimentação do jogador
		movimento = false;
		if (direita) {
			movimento = true;
			direcao = direitaD;
			movimentarDireita(velocidade);
		} else if (esquerda) {
			movimento = true;
			direcao = esquerdaD;
			movimentarEsquerda(velocidade);
		}

		if (cima) {
			movimento = true;
			direcao = cimaD;
			movimentarCima(velocidade);
		} else if (baixo) {
			movimento = true;
			direcao = baixoD;
			movimentarBaixo(velocidade);
		}

		if (movimento) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				indiceAnimacao++;
				if (indiceAnimacao > maxIndice) {
					indiceAnimacao = 0;
				}
			}
		}
		///////////////
	}

	public void renderizar(Graphics g) {
		if (direcao == direitaD) {
			g.drawImage(jogadorDireita[indiceAnimacao], x, y, null);
		} else if (direcao == esquerdaD) {
			g.drawImage(jogadorEsquerda[indiceAnimacao], x, y, null);
		} else if (direcao == cimaD) {
			g.drawImage(jogadorCima[indiceAnimacao], x, y, null);
		} else if (direcao == baixoD) {
			g.drawImage(jogadorBaixo[indiceAnimacao], x, y, null);
		}

	}

}
