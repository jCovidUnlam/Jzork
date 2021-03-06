package zorkPackage;

import java.io.File;
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

		Consola.mostrarMensajeBienvenida();
		System.out.print("\nIngrese su nombre: ");
		name = (in.nextLine());
		
		Mensaje.setFileName(name.replace(" ","_") + "_" + new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date()).toString());
		System.setProperty("logfilename", "SaveGames/" + Mensaje.getFileName());
		
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

					if(scan == "3") {
						Consola.mostrarMenuInicio(name);
					} else {
						File[] files = new File("./Recursos/Aventuras/").listFiles();
						for (File file : files) {
							if(file.getName().contains(scan)) {
								InitConfig.getMapa(name, "./Recursos/Aventuras/" + file.getName());
								in.close();
								init = true;
								break;
							}
						}

						if(!init) {
							System.out.println("\nPor favor, elija alguna de las opciones disponibles.");
						}
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

		} while (!scan.equals("3"));
	}

}
