package zorkGraficos;

import javax.swing.ImageIcon;

public class ObjetoGrafico {
	public static final int ANCHO_TILE = 32;
	public static final int ALTO_TILE = 32;
	private int anchoImg;
	private int altoImg;
	private String path;
	private ImageIcon sprite;
	private Boolean isAnimado;
	
	public ObjetoGrafico(String path) {
		sprite = new ImageIcon(path);

		this.path = path;
		this.anchoImg = sprite.getIconWidth();
		this.altoImg = sprite.getIconHeight();
		this.isAnimado = false;
	}
	
	
}
