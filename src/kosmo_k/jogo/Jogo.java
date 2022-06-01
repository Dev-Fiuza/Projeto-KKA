package kosmo_k.jogo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import kosmo_k.entidades.Entidade;
import kosmo_k.entidades.Jogador;
import kosmo_k.graficos.Spritesheet;

/**
 * 
 * @author Gustavo Fiuza
 * @description Classe responsável por trabalhar com os gráficos e desenhar
 *              todos os objetos na janela, contendo então o método principal.
 *
 */

@SuppressWarnings("serial")
public class Jogo extends Canvas implements Runnable, KeyListener {

	private JFrame quadro;
	private Thread thread;
	private boolean jogoRodando;
	private int largura = 256;
	private int altura = 144;
	private int escala = 4;
	public List<Entidade> entidades;
	private Jogador jogador;

	// Layer fixa de fundo, iniciada no construtor
	private BufferedImage imagem;
	public Spritesheet spritesheet;

	public Jogo() {
		// Comando para implementar o Key Listener
		addKeyListener(this);
		// Configurando janela e iniciando ela
		setPreferredSize(new Dimension(largura * escala, altura * escala));
		iniciarQuadro();
		// Inicializando objetos
		imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		entidades = new ArrayList<>();
		spritesheet = new Spritesheet("/Spritesheet_Feiticeiro_Poses.png");
		jogador = new Jogador(0, 0, 16, 16, spritesheet.getSprite(0, 128, 64, 64));
		entidades.add(jogador);
	}

	/**
	 * @description Este método é responsável pela inicialização do game,
	 *              instanciando uma thread e colocando nossa variável de validação
	 *              para saber se o jogo está rodando ou não como true, para de
	 *              fato, iniciar a repetição do programa.
	 */
	private synchronized void iniciar() {
		thread = new Thread(this);
		jogoRodando = true;
		thread.start();
	}

	/**
	 * @description Este método é uma garantia, para que após fechar a janela o game
	 *              irá de fato parar.
	 */
	private synchronized void parar() {
		jogoRodando = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @description Método para iniciar os gráficos do jogo, iniciando o quadro,
	 *              configurando seu tamanho, definindo para não ser
	 *              redimensionável, fechar quando clicar para sair e sempre iniciar
	 *              do centro.
	 */
	private void iniciarQuadro() {
		quadro = new JFrame("Kosmo K: Arcanismo");
		quadro.add(this);
		quadro.pack();
		quadro.setResizable(false);
		quadro.setLocationRelativeTo(null);
		quadro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		quadro.setVisible(true);
	}

	// Método Principal
	public static void main(String[] args) {
		Jogo jogo = new Jogo();
		jogo.iniciar();
	}

	/**
	 * @description Método responsável por atualizar os quadros do game.
	 */
	private void tick() {
		for (int i = 0; i < entidades.size(); i++) {
			Entidade e = entidades.get(i);
			e.tick();
		}
	}

	/**
	 * @description Método responsável por cuidar da renderização dos gráficos
	 *              dentro da janela do jogo.
	 */
	private void renderizar() {
		// Sequência de buffers na tela para otimizar a renderização
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		// Configuração do desenho de gráficos na tela
		Graphics g = imagem.getGraphics();

		// Desenhando coisas Novas
		g.setColor(new Color(255, 255, 0));
		g.fillRect(0, 0, largura, altura);

		// Renderização do Jogo
		for (int i = 0; i < entidades.size(); i++) {
			Entidade e = entidades.get(i);
			e.renderizar(g);
		}

		// Chamada do método para desenhar as coisas na tela de fato.
		g.dispose();
		g = bs.getDrawGraphics();
		// Fundo da tela
		g.drawImage(imagem, 0, 0, largura * escala, altura * escala, null);
		// Mostrando os gráficos de fato
		bs.show();
	}

	/**
	 * @description Método run de Runnable que tem como função fazer todo o processo
	 *              de execução do game. Enquanto o while estiver ativo ele irá
	 *              ficar em um looping infinito para renderizar as coisas na tela e
	 *              processar o que precisa.
	 */
	public void run() {

		long ultimoMomento = System.nanoTime();

		// Ticks que eu quero ter a cada segundo, serve para limitar o FPS
		double quantidadeTicks = 60.0;

		// Cálculo feito para pegar o momento certo para poder "atualizar" o jogo, ou
		// seja
		// realizar as mudanças captadas no ultimo segundo, na tela.
		double ns = 1000000000 / quantidadeTicks;

		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();

		// Este while é responsável por controlar a atualização do game na tela. Ele
		// pega o tempo de agora,
		// e subtrai do ultimo momento, isso tudo vai ser dividido pelo ns. Se for igual
		// a 1, significa que
		// passou 1 segundo como deveria, mas caso ultrapasse por algum problema de
		// atraso, ele também detecterá
		// caso seja maior que 1. Nesse momento ele atualizará o jogo novamente e
		// renderizará as coisas na tela.
		while (jogoRodando) {
			long agora = System.nanoTime();
			delta += (agora - ultimoMomento) / ns;
			ultimoMomento = agora;
			if (delta >= 1) {
				tick();
				renderizar();
				frames++;
				delta--;
			}
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		parar();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		// Configuração de movimentação Direita-Esquerda
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			jogador.setDireita(true);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			jogador.setEsquerda(true);
		}

		// Configuração de movimentação Cima-Baixo
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			jogador.setCima(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			jogador.setBaixo(true);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Configuração de movimentação Direita-Esquerda
				if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
					jogador.setDireita(false);
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
					jogador.setEsquerda(false);
				}

				// Configuração de movimentação Cima-Baixo
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
					jogador.setCima(false);
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
					jogador.setBaixo(false);
				}

	}

}
