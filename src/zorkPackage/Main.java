package zorkPackage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//Esto seria un harcodeo de datos para que arranque y testear, termina siendo una BD

		Objeto llave = new Objeto("Llave oxidada", "Es una llave oxidada, debería abrir alguna puerta.", true, false, true,"");
		Objeto tapete = new Objeto("Tapete","Viejo tapete con la descripción borrosa, pareciera que dice 'Bienvenido'.",true, true, true,"");   
		Objeto puerta = new Objeto("Puerta Oxidada","Se distingue una puerta vieja y oxidada, parece estar cerrada",false,false,true,"Planeas irte con la puerta al hombro hasta tu casa?");
		List<Objeto> objetosCabania = new LinkedList<>();
		objetosCabania.add(llave);
		objetosCabania.add(tapete);
		objetosCabania.add(puerta);
		
		Lugar bosque = new Lugar("Bosque","Sientes que algo o alguien te observa", null);
		Lugar bosqueCerano = new Lugar("Bosque","Ves la cabaña más cerca, sientes temor, la sesación de que alguien te observa es más fuerte.", null);
		Lugar cabania = new Lugar("Cabaña abandonada","Te encuentras frente a una cabaña que parece estar abandonada, "
				+ "que sería ideal para refugiarte del frío y poder pensar que ocurrió, pero la puerta se encuentra cerrada."
				+ "\nJusto por debajo de la puerta notas un tapete que dice 'Bienvenido'", objetosCabania, false, "La puerta esta cerrada!"); 
		
		Lugar inicio = new Lugar("Bosque tenebroso", "Despiertas en un bosque, en donde solo escuchas el ruido de los animales y las hojas de los árboles,"
				+ "\nNo recuerdas nada de como llegaste hasta ahí, lo último que puedes recordar es estar rindiendo un parcial de programación avanzada en la facultad."
				+ "\nTe encuentras rodeado de un bosque denso, pero muy al norte puedes notar algo entre los árboles.", null);
		
		Lugar[][][] lugares = new Lugar[10][10][2];
		lugares[4][0][0] = inicio;
		lugares[4][1][0] = bosqueCerano; // 1 al norte del inicio
		lugares[4][2][0] = cabania; // 2 al norte del inicio
		lugares[3][0][0] = bosque; // 1 al oeste del inicio
		lugares[5][0][0] = bosque; // 1 al este del inicio
		
		Posicion posicionInicial = new Posicion(4, 0, 0);
		
		Mapa mapa = new Mapa("El bosque", lugares, posicionInicial , 0);
		
		///Finaliza el bloque de harcodeo
		
		Consola.mostrarMenuInicio();
		Scanner in = new Scanner(System.in);
		String scan;
		do
		{
			System.out.println();
			System.out.print(">> ");
			scan = (in.next());
			switch(scan) {
			case "1":
				System.out.print("\nIngrese su nombre: ");
				scan = (in.next());
				Personaje personaje = new Personaje(scan);
				GameMaster gm = new GameMaster(mapa,personaje);
				Observer obs = new Observer(gm);
				in.close();
				break;
			case "2":
				Consola.mostrarReglas();
			break;
			case "3":
				in.close();
				System.out.println("\nAdios cobarde!");
			break;
			default:
				System.out.println("\nPor favor, elija alguna de las opciones disponibles.");
			}
			/*Si estan pensando que cortar el scanner del main, y abrir otro cuando inicie
			el juego es muy cabeza, tienen que hacer esto con hilos. Buena suerte.
			*/
		}while(!scan.equals("3") && !scan.equals("1"));
	}

}
