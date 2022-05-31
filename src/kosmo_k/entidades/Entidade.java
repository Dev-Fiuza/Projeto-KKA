package kosmo_k.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entidade {

	private int x;
	private int y;
	private int largura;
	private int altura;
	private BufferedImage sprite;

	public Entidade(int x, int y, int largura, int altura, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
		this.sprite = sprite;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getLargura() {
		return largura;
	}

	public int getAltura() {
		return altura;
	}

	public void tick() {

	}

	public void renderizar(Graphics g) {
		g.drawImage(sprite, this.getX(), this.getY(), null);
	}

}
