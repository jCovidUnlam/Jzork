package zorkPackage;

import java.io.Console;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//JsonReader.startAdventure("adventureTest.txt");
		//JsonReader.test("jsonPropio.txt");
		
		Consola.mostrarMenuInicio();
		Scanner in = new Scanner(System.in);
		String scan;
		boolean init = false;
		do
		{
			System.out.print("\n>> ");
			scan = (in.next());
			init = false;
			switch(scan) {
			case "1":
				Consola.imprimirMenuAventuras();
				do {
					
					System.out.print("\n>> ");
					scan = (in.next());

					switch(scan) {
					case "1":
						System.out.print("\nIngrese su nombre: ");
						scan = (in.next());
						Consola.iniciarAventura(new Aventura(scan, "jsonPropio.txt"));
						in.close();
						init = true;
						break;
					case "2":
						System.out.print("\nIngrese su nombre: ");
						scan = (in.next());
						Consola.iniciarAventura(new Aventura(scan, "otroJson.txt"));
						in.close();
						init = true;
						break;
					case "3":
						Consola.mostrarMenuInicio();
						break;
					default:
						System.out.println("\nPor favor, elija alguna de las opciones disponibles.");
					break;
					}

				} while(!scan.equals("3") && init == false);
	
				
				//GameMaster gm = new GameMaster(mapa,personaje);
				//Observer obs = new Observer(gm);
//				if(scan2 != "3")
//					in.close();
				if(init == true) {
					scan = "3";
					in.close();
				}
				else {
					if(scan.equals("3"))
						scan = "4";
				}
				break;
			case "2":
				Consola.mostrarReglas();
			break;
			case "3":
				if(in != null)
					in.close();
				System.out.println("\nAdios cobarde!");					
			break;
			default:
				System.out.println("\nPor favor, elija alguna de las opciones disponibles.");
			break;
			}
			/*Si estan pensando que cortar el scanner del main, y abrir otro cuando inicie
			el juego es muy cabeza, tienen que hacer esto con hilos. Buena suerte.
			*/
		}while(!scan.equals("3"));
	}

}
