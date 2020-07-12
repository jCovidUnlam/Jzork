package zorkPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.log4j.Logger;

import zorkLogger.LoggerHistory;
import zorkUI.Consola;

public class Observador {
	private Scanner in;
	private String scan;

	private final static Logger log = Logger.getLogger(Observador.class);
	
	public Observador(Aventura gameMaster) {
		super();
		in = new Scanner(System.in);
		Comando cmd = new Comando();
		LoggerHistory.loggerConfig();
		
		do {
			System.out.println();
			System.out.print(">>");
			scan = (in.nextLine()).toLowerCase();
			
			log.info(scan);
			
			ArrayList<String> cadena = new ArrayList<String>(Arrays.asList(scan.split(" ")));
			Lexico.removerErrores(cadena);
			Lexico.removerAtributos(cadena);
			Lexico.removerCaracteresEspeciales(cadena);
			
			if(cmd.isReEscanear() == false) {
				cmd = Interprete.interpretar(cadena);
			}
			else {
				try {
					cmd.setVerbo(cadena.get(0));
					cmd.setReEscanear(false);
					cmd.setPalabrasClavesPrimerObjeto(cadena.subList(0, cadena.size()));
					if(cmd.getPalabrasClavesPrimerObjeto().size() == 0)
						cmd.setReEscanear(true);
				}
				catch (Exception e) {
					Consola.mostrar("Por favor, ingrese a al NPC con el que desea hablar.");
					cmd.setReEscanear(true);
				}
			}
			
			gameMaster.ejecutar2(cmd);
			
		} while (!scan.equals("exit") && gameMaster.isEndGame() != true);

		in.close();
	}	
}
