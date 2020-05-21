package zorkPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Observador {
	private Scanner in;
	private String scan;
	private GameMaster gameMaster;
	
	public Observador(GameMaster gameMaster) {
		super();
		this.gameMaster = gameMaster;
		in = new Scanner(System.in);
		Comando cmd = new Comando();
		do {
			System.out.println();
			System.out.print(">>");
			scan = (in.nextLine());
			
			ArrayList<String> cadena = new ArrayList<String>(Arrays.asList(scan.toLowerCase().split(" ")));

			removerErrores(cadena);
			removerAtributos(cadena);
			removerCaracteresEspeciales(cadena);
			
			if(cmd.isReEscanear() == false) {
				cmd = Interprete.interpretar(cadena);
			}
			else {
				cmd.setNombreObjeto(cadena.get(0));
				cmd.setReEscanear(false);
			}
			
			gameMaster.ejecutar(cmd);
			
		} while (!scan.equals("exit".toLowerCase()) && gameMaster.isEndGame() != true);

		in.close();
	}

	private void removerErrores(ArrayList<String> cadena) {

		List<String> articulos = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add(" ");
				add("");
			}
		};

		cadena.removeAll(articulos);
	}

	private void removerAtributos(ArrayList<String> cadena) {
		cadena.removeAll(Arrays.asList(Lexico.atributos));
	}
	
	private void removerCaracteresEspeciales(ArrayList<String> cadena) {
		cadena.removeAll(Arrays.asList(Lexico.caracteresEspeciales));
	}
	

	
	
	
}
