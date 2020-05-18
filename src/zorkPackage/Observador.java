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

		do {
			System.out.println();
			System.out.print(">>");
			scan = (in.nextLine());

			ArrayList<String> cadena = new ArrayList<String>(Arrays.asList(scan.toLowerCase().split(" ")));

			// Esto saca vacios por ahora.
			removerErrores(cadena);
			// Hay que ver si esto aplica, si nos lo dejan hacer, creeria es lo mas comodo.
			removerArticulos(cadena);

			Comando com = Interprete.interpretar(cadena);
			gameMaster.ejecutar(com);

		} while (!scan.equals("exit".toLowerCase()) && gameMaster.isEndGame() != true);

		in.close();
	}

	private void removerErrores(ArrayList<String> cadena) {

		List<String> articulos = new ArrayList<String>() {
			/**
			 * Esto me lo hace agregar sino tira warninig.... anda a saber que mierda es
			 */
			private static final long serialVersionUID = 1L;

			{
				add(" ");
				add("");
			}
		};

		cadena.removeAll(articulos);
	}

	private void removerArticulos(ArrayList<String> cadena) {
		cadena.removeAll(Arrays.asList(Lexico.atributos));
	}
	

	
	
	
}
