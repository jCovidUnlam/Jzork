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


public class JPanelGraficoPrincipal extends JPanel{
	private static final long serialVersionUID = 8514865478625775753L;
	private LugarGrafico lugarActual;
	
	
	public JPanelGraficoPrincipal(LugarGrafico lugarActual) {
		super();
		setBounds(0, 0, 480, 320);
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
			ImageIcon aux = getSprite(o);
			
			g.drawImage(aux.getImage(), o.getX(), o.getY(), aux.getIconWidth(), aux.getIconHeight(), null);
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
		
		g.drawImage(aux, 0, 0, getWidth(), getHeight(), null);
		
	}
	
	public ImageIcon getSprite(ObjetoGrafico o) {
		BufferedImage aux = null;
		try {
			aux = ImageIO.read(new File(o.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageIcon res = new ImageIcon(aux.getSubimage(0, 0, aux.getWidth(), aux.getHeight()));
		
		return res;
	}
	
	public void actualizarPantalla() {
		repaint();
	}
	
	public LugarGrafico getLugarActual() {
		return lugarActual;
	}

	public void setLugarActual(LugarGrafico lugarActual) {
		this.lugarActual = lugarActual;
	}

}
