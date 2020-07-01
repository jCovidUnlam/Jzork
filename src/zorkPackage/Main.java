package zorkPackage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import zorkUI.Consola;

public class Main {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		String scan;
		String name;
		Date fecha = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyMMdd_HHmmss"); 
		Consola.mostrarMensajeBienvenida();
		System.out.print("\nIngrese su nombre: ");
		name = (in.next());
		
		System.setProperty("logfilename", name +"_"+ formatter.format(fecha).toString());
		
		Consola.mostrarMenuInicio(name);
		boolean init = false;
		do {
			System.out.print("\n>> ");
			scan = (in.next());
			init = false;
			switch (scan) {
			case "1":
				Consola.imprimirMenuAventuras();
				do {

					System.out.print("\n>> ");
					scan = (in.next());

					switch (scan) {
					case "1":
						InitConfig.getMapa(name, "./Recursos/jsonpirata.txt");
						in.close();
						init = true;
						break;
					case "2":
						InitConfig.getMapa(name, "./Recursos/deDragonesYDesiertos.txt");
						in.close();
						init = true;
						break;
					case "3":
						Consola.mostrarMenuInicio(name);
						break;
					default:
						System.out.println("\nPor favor, elija alguna de las opciones disponibles.");
						break;
					}

				} while (!scan.equals("3") && init == false);

				if (init == true) {
					scan = "3";
					in.close();
				} else {
					if (scan.equals("3"))
						scan = "4";
				}
				break;
			case "2":
				Consola.mostrarReglas();
				do {
					System.out.print("\n>> ");
					scan = (in.next());
					if(!scan.equals("0"))
						System.out.println("\nOpcion incorrecta, para volver al menu principal ingrese 0.");
				}while(!scan.equals("0"));
				Consola.mostrarMenuInicio(name);
				break;
			case "3":
				if (in != null)
					in.close();
				System.out.println("\nAdios!");
				break;
			default:
				System.out.println("\nPor favor, elija alguna de las opciones disponibles.");
				break;
			}
			/*
			 * Si estan pensando que cortar el scanner del main, y abrir otro cuando inicie
			 * el juego es muy cabeza, tienen que hacer esto con hilos. Buena suerte.
			 */
		} while (!scan.equals("3"));
	}

}
