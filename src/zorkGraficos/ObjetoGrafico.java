package zorkGraficos;

import javax.swing.ImageIcon;

public class ObjetoGrafico {
	
	private static final String imagenPorDefecto = "defaultObjeto.png";

	public static final int ANCHO_TILE = 32;
	public static final int ALTO_TILE = 32;
	private int anchoImg;
	private int altoImg;
	private int x;
	private int y;
	private String path;
	private ImageIcon sprite;
	private Boolean isAnimado;
	
	public ObjetoGrafico() {
		super();
	}
	
	public ObjetoGrafico(String path) {
		sprite = new ImageIcon(path);
		this.path = path;
		this.anchoImg = sprite.getIconWidth();
		this.altoImg = sprite.getIconHeight();
		this.isAnimado = false;
	}
		
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		if(path == null || path.equals(""))
			this.path = LugarGrafico.rutaImagenes + imagenPorDefecto;
		else
			this.path = path;
		
		sprite = new ImageIcon(this.path);
		this.anchoImg = sprite.getIconWidth();
		this.altoImg = sprite.getIconHeight();
		this.isAnimado = false;
	}
	
}
