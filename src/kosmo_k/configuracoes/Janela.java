package kosmo_k.configuracoes;

public class Janela {

	private int largura;
	private int altura;
	//private int escala;
	
	/*
	 * Proporções existentes:
    	3:2
    	4:3
    	5:4
    	14:9
    	16:9
    	16:10 (ou 8:5)
    	17:9
    	21:9
	 */

	public Janela() {
		this.largura = 120;
		this.altura = 160;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	private void configuracaoJanela() {
		switch (largura) {
		case 120:
			this.largura = 120;
			break;

		default:
			break;
		}
	}
	
	
}
