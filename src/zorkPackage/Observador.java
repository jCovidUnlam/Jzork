package zorkPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import zorkLogger.LoggerHistory;
import zorkTrigger.OpcionDialogo;
import zorkTrigger.TriggerConversacion;
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
			
			if(cmd.getTrigger() != null) {
				if(ejecutarTriggerDialogo(in, cmd) == true)
					continue;
			}
			else {
				System.out.println();
				System.out.print(">>");
				scan = (in.nextLine()).toLowerCase();
				
				log.info(scan);
				
				ArrayList<String> cadena = new ArrayList<String>(Arrays.asList(scan.split(" ")));
				Lexico.removerErrores(cadena);
				Lexico.removerAtributos(cadena);
				Lexico.removerCaracteresEspeciales(cadena);
				
				if(cmd.isReEscanear() == false) 
					cmd = Interprete.interpretar(cadena);
				else 
					reEscanear(cmd, cadena);
				
				
				gameMaster.ejecutar(cmd);
			}
		} while (gameMaster.isEndGame() != true);

		in.close();
	}
	
	private void reEscanear(Comando cmd, List<String> cadena) {
		
		try {
			cmd.setVerbo(cadena.get(0));
			cmd.setReEscanear(false);
			cmd.setPalabrasClavesPrimerObjeto(cadena.subList(0, cadena.size()));
			if(cmd.getPalabrasClavesPrimerObjeto().size() == 0)
				cmd.setReEscanear(true);
		}
		catch (Exception e) {
			Consola.mostrar("Por favor, ingrese el objeo con el que desea interactuar.");
			cmd.setReEscanear(true);
		}
		
	}
	
	private static boolean ejecutarTriggerDialogo(Scanner in, Comando cmd) {
		
		String scan = "";
		String msj = cmd.getTrigger().getMensajeInicial() + "\n\n";
	
		for (OpcionDialogo opt : cmd.getTrigger().getOpciones()) {
			msj += opt.getNumero() + "- " + opt.getTexto() + "\n"; 
		}
		
		boolean exit = false;
		
		do {

			Consola.mostrar(msj);
			System.out.print(">> ");
			
			scan = in.next();
			if(scan.equals("0")) {
				exit = true;
			}
			
			Consola.mostrar(evaluarSeleccion(scan,cmd.getTrigger()));
			

		
			
		}while(exit == false);
		in.nextLine();
		cmd.setTrigger(null);
		return exit;
	}
	
	private static String evaluarSeleccion(String scan, TriggerConversacion trigger) {
		
		int result;
		
		try {
			result = Integer.parseInt(scan);
		}
		catch(Exception e) {
			return "No existe esa opcion, no confundas al pobre " + trigger.getNombreNpc() + "!";
		}
		
		String response = trigger.getOpciones().stream().filter(x -> x.getNumero() == result).map(x -> x.getRespuesta()).findAny().orElse(null);
		
		if(response == null)
			return "No existe esa opcion, no confundas al pobre " + trigger.getNombreNpc() + "!";
		
		if(scan.equals("0"))
			return trigger.getMensajeSalida();
		
		return trigger.getNombreNpc() + " responde: " + response;
	}
	

	
}
