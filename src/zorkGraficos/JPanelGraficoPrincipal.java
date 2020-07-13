package zorkGraficos;

import java.awt.Graphics;
import javax.swing.JPanel;

public class JPanelGraficoPrincipal extends JPanel{
	private static final long serialVersionUID = 8514865478625775753L;
	
	
	public void paint(Graphics g) {
		//pintar fondo del lugar
		paintFondo(g);
		//pintar sprites de obj
		paintSprites(g);
	}

	private void paintSprites(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	private void paintFondo(Graphics g) {
		//acá obtenemos el fondo del lugar
		
	}

}
