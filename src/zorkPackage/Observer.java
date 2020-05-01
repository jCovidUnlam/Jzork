package zorkPackage;

import java.util.Scanner;

public class Observer {
	private Scanner in;
	private String scan;
	private String nombreObjeto;
	GameMaster gameMaster;

	public Observer(GameMaster gameMaster) {
		super();
		this.gameMaster = gameMaster;
		in = new Scanner(System.in);
	
		do
		{
			System.out.println();
			System.out.print(">> ");
			scan = (in.nextLine());
			
			if(scan.contains(" ")) {
				nombreObjeto = scan.substring(scan.indexOf(" ")+1); // Aca deberia buscar el objeto con ese nombre
				scan = scan.substring(0,scan.indexOf(" ")); // Aca separa comando de objeto				
			}
		
			///Toda esta saraseada se va con una BD
			Comando com = new Comando(scan, nombreObjeto);
			// Esto cuando tengamos una BD le pegaria a alguna clase de la capa de acceso a datos
			//if(!Comando.existe(scan))
			gameMaster.ejecutar(com);
			
		}while(!scan.equals("exit".toLowerCase()));
		
		in.close();
	}	
	
	
	
	
}
