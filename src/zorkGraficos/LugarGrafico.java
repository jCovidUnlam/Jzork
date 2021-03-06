package zorkGraficos;

import java.util.ArrayList;
import java.util.List;

public class LugarGrafico {
	
	public static final String rutaImagenes = "./Recursos/Graficos/";
	private static final String imagenPorDefecto = "defaultLugar.jpg";
	
	private String path;
	
	private List<ObjetoGrafico> sprites;
	
	public LugarGrafico(String path, List<ObjetoGrafico> sprites) {
		super();
		this.path = path;
		this.sprites = sprites;
	}

	public List<ObjetoGrafico> getSprites() {
		return sprites;
	}

	public LugarGrafico(String path) {
		super();
		sprites = new ArrayList<ObjetoGrafico>();

		if(path == null || path.equals(""))
			this.path = rutaImagenes + imagenPorDefecto;
		else
			this.path = path;
	}
	
	public String getPath() {
		return path;
	}

	public void addSprite(ObjetoGrafico sprite) {
		this.sprites.add(sprite);
	}
	
	public void removeSprite(ObjetoGrafico sprite) {
		this.sprites.remove(sprite);
	}
}
