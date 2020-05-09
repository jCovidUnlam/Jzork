package zorkPackage;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Observador {
	private Scanner in;
	private String scan;
	GameMaster gameMaster;

	public Observador(GameMaster gameMaster) {
		super();
		this.gameMaster = gameMaster;
		in = new Scanner(System.in);
	
		do
		{
			System.out.println();
			System.out.print(">> ");
			scan = (in.nextLine());

			ArrayList<String> cadena = new ArrayList<String>(Arrays.asList(scan.toLowerCase().split(" ")));
			
			//Esto saca vacios por ahora.
			removerErrores(cadena);
			//Hay que ver si esto aplica, si nos lo dejan hacer, creeria es lo mas comodo.
			removerArticulos(cadena);
			
			Comando com = Interprete.interpretar(cadena);
			gameMaster.ejecutar(com);
			
		}while(!scan.equals("exit".toLowerCase()));
		
		in.close();
	}	
	
	private static void removerErrores(ArrayList<String> cadena) {
		
		List<String> articulos = new ArrayList<String>() {
			/**
			 * Esto me lo hace agregar sino tira warninig.... anda a saber que mierda es
			 */
			private static final long serialVersionUID = 2L;

			{
				add(" ");
				add("");
			}		
		};
		
		cadena.removeAll(articulos);
	}
	
	private static void removerArticulos(ArrayList<String> cadena) {
		
		List<String> articulos = new ArrayList<String>() {
			/**
			 * Esto me lo hace agregar sino tira warninig.... anda a saber que mierda es
			 */
			private static final long serialVersionUID = 1L;

			{
				add("el");
				add("la");
				add("ellos");
				add("ellas");
				add("hacia");
				add("a");
				add("al");
			}		
		};
		
		cadena.removeAll(articulos);
	}
	
}
