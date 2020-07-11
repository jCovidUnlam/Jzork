package zorkGraficos;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PantallaGrafica extends JFrame{
	private JTextField textField_1;
	private JTextField txtIngreseSuComando;
	private JButton btnNewButton;
	public PantallaGrafica() {
		getContentPane().setLayout(new MigLayout("", "[grow][][][][][][][]", "[][grow][][][][][][][][][]"));
		
		
		textField_1 = new JTextField();
		getContentPane().add(textField_1, "cell 0 0 1 10,grow");
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "cell 1 1 7 9,grow");
		
		txtIngreseSuComando = new JTextField();
		txtIngreseSuComando.setText("Ingrese su comando...");
		txtIngreseSuComando.setToolTipText("Ingrese su comando");
		txtIngreseSuComando.setColumns(10);
		getContentPane().add(txtIngreseSuComando, "cell 0 10 4 1,growx");
		
		btnNewButton = new JButton("Ayuda");
		getContentPane().add(btnNewButton, "cell 4 10 4 1,growx");
		
	
	}
	
}
