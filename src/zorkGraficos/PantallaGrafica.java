package zorkGraficos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PantallaGrafica extends JFrame{
	private static final long serialVersionUID = 4341998680674808445L;
	private JPanelGraficoPrincipal panel;

	public PantallaGrafica() {
		super("JZork");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				confirmacionCierreVentana();
			}
		});
		
		panel = new JPanelGraficoPrincipal();
		setContentPane(panel);
		
		setVisible(true);
	
	}
	
	private void confirmacionCierreVentana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea cerrar la pantalla gráfica?", "Salir",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			dispose();
		}
	}
	
	public static void main(String[] args) {
		PantallaGrafica p = new PantallaGrafica();
	}
}
