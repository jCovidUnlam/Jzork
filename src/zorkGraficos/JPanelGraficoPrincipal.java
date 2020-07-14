package zorkGraficos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import zorkPackage.Mapa;

public class JPanelGraficoPrincipal extends JPanel{
	private static final long serialVersionUID = 8514865478625775753L;
	private Mapa mapa;
	
	
	public JPanelGraficoPrincipal(Mapa mapa) {
		super();
		this.mapa = mapa;
	}
	
	public JPanelGraficoPrincipal() {
		super();
	}

	public void paint(Graphics g) {
		//pintar fondo del lugar
		//paintFondo(g);
		//pintar sprites de obj
		paintSprites(g);
	}

	private void paintSprites(Graphics g) {
		
		ImageIcon img = getSprite(0, 0);
		g.drawImage(img.getImage(), 100, 100, 60, 60, null);
		
	}

	private void paintFondo(Graphics g) {
		//acá obtenemos el fondo del lugar
		
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
