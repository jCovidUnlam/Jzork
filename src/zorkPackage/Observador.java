package zorkPackage;


import java.util.Arrays;
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

			Comando com = Interprete.interpretar(Arrays.asList(scan.toLowerCase().split(" ")));
			gameMaster.ejecutar(com);
			
		}while(!scan.equals("exit".toLowerCase()));
		
		in.close();
	}	
	
}
