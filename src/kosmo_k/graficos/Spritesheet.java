package kosmo_k.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Gustavo Fiuza
 * @description Classe para controlar os sprites dentro do game.
 *
 */
public class Spritesheet {
	
	private BufferedImage spritesheet;
	
	public Spritesheet(String caminho) {
		try {
			spritesheet = ImageIO.read(getClass().getResource(caminho));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x, int y, int largura, int altura) {
		return spritesheet.getSubimage(x, y, largura, altura);
	}

}
