package zorkGraficos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import zorkPackage.Mapa;

public class JPanelGraficoPrincipal extends JPanel{
	private static final long serialVersionUID = 8514865478625775753L;
	private LugarGrafico lugarActual;
	
	
	public JPanelGraficoPrincipal(LugarGrafico lugarActual) {
		super();
		this.lugarActual = lugarActual;
	}

	public void paint(Graphics g) {
		//pintar fondo del lugar
		paintFondo(g);
		//pintar sprites de obj
		paintSprites(g);
	}

	private void paintSprites(Graphics g) {
		List<ObjetoGrafico> objetos = new ArrayList<ObjetoGrafico>();
		objetos = lugarActual.getSprites();
		for(ObjetoGrafico o : objetos) {
			BufferedImage aux = null;
			try {
				aux = ImageIO.read(new File(o.getPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			g.drawImage(aux, o.getX(), o.getY(), aux.getWidth(), aux.getHeight(), null);
		}		
	}

	private void paintFondo(Graphics g) {
		//acá obtenemos el fondo del lugar
		BufferedImage aux = null;
		try {
			aux = ImageIO.read(new File(lugarActual.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g.drawImage(aux, 0, 0, 480, 320, null);
		
	}
	
	public ImageIcon getSprite(int x, int y) {
		BufferedImage aux = null;
		try {
			aux = ImageIO.read(new File("./imagenes/deDragonesYDesiertos/taberna/sprites/esqueleto.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageIcon res = new ImageIcon(aux.getSubimage(0, 0, 60, 60));
		
		return res;
	}

}
