package kosmo_k.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entidade {

	protected int x;
	protected int y;
	protected int largura;
	protected int altura;
	private BufferedImage sprite;

	public Entidade(int x, int y, int largura, int altura, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
		this.sprite = sprite;
	}

	public void movimentarDireita(int movimentacao) {
		this.x += movimentacao;
	}

	public void movimentarEsquerda(int movimentacao) {
		this.x -= movimentacao;
	}

	public void movimentarCima(int movimentacao) {
		this.y -= movimentacao;
	}

	public void movimentarBaixo(int movimentacao) {
		this.y += movimentacao;
	}

	public void tick() {

	}

	public void renderizar(Graphics g) {
		g.drawImage(this.sprite,this.x, this.y, null);
	}

}
