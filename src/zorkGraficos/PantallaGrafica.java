package zorkGraficos;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PantallaGrafica extends JFrame{
	private JTextField txtHistorial;
	private JTextField txtComando;
	private JButton btnAyuda;
	public PantallaGrafica() {
		getContentPane().setLayout(new MigLayout("", "[grow][][][][][][][]", "[][grow][][][][][][][][][]"));
		
		
		txtHistorial = new JTextField();
		getContentPane().add(txtHistorial, "cell 0 0 1 10,grow");
		txtHistorial.setColumns(10);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "cell 1 1 7 9,grow");
		
		txtComando = new JTextField();
		txtComando.setText("Ingrese su comando...");
		txtComando.setToolTipText("Ingrese su comando");
		txtComando.setColumns(10);
		getContentPane().add(txtComando, "cell 0 10 4 1,growx");
		
		btnAyuda = new JButton("Ayuda");
		getContentPane().add(btnAyuda, "cell 4 10 4 1,growx");
		
	
	}
	
}
