package zorkGraficos;

import javax.swing.ImageIcon;

public class Sprite {
	public static final int ANCHO_TILE = 32;
	public static final int ALTO_TILE = 32;
	private int anchoImg;
	private int altoImg;
	private int anchoFrame;
	private int altoFrame;
	private String path;
	private ImageIcon sprite;
	
	public Sprite(String path, int anchoFrame, int altoFrame) {
		sprite = new ImageIcon(path);

		this.path = path;
		this.anchoImg = sprite.getIconWidth();
		this.altoImg = sprite.getIconHeight();
		this.anchoFrame = anchoFrame;
		this.altoFrame = altoFrame;
	}
	
	

}
