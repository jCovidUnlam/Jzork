package zorkGraficos;

public class ObjetoGrafico {
	
	private static final String imagenPorDefecto = "defaultObjeto.png";

	private int x;
	private int y;
	private String path;
	
	public ObjetoGrafico() {
		super();
	}
	
	
	
	public ObjetoGrafico(String path) {
		this.path = path;
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
	}
	
}
