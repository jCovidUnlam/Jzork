package zorkPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import zorkEnum.EnumTipoLexico;

public final class Lexico {
	
	public static  String[] movimientoSimple; 
	
	public static  String[] usuarioSimple;

	public static  String[] adquirible;

	public static  String[] rompibles;

	public static  String[] consumible;

	public static  String[] inspeccionable;

	public static  String[] usable; 
	
	public static  String[] movimiento; 

	public static  String[] usuario;

	public static  String[] NPC; 
	
	public static  String[] ataque;
	
	public static String [] atributos;
	
	public static String [] descartables;
	
	public static String [] caracteresEspeciales;

	public static boolean inMovimientoSimple(String verbo) {
		return Arrays.asList(movimientoSimple).contains(verbo);
	}
	
	public static boolean inUsuarioSimple(String verbo) {
		return Arrays.asList(usuarioSimple).contains(verbo);
	}
	
	public static boolean inAtacar(String verbo) {
		return Arrays.asList(ataque).contains(verbo);
	}
	
	public static boolean inNPC(String verbo) {
		return Arrays.asList(NPC).contains(verbo);
	}
	
	public static boolean inMovimiento(String verbo) {
		return Arrays.asList(movimiento).contains(verbo);
	}
	
	public static boolean inUsuario(String verbo) {
		return Arrays.asList(usuario).contains(verbo);
	}
	
	public static boolean inAdquirible(String verbo) {
		return Arrays.asList(adquirible).contains(verbo);
	}
	
	public static boolean inRompible(String verbo) {
		return Arrays.asList(rompibles).contains(verbo);
	}
	
	public static boolean inConsumible(String verbo) {
		return Arrays.asList(consumible).contains(verbo);
	}
	
	public static boolean inInspeccionable(String verbo) {
		return Arrays.asList(inspeccionable).contains(verbo);
	}
	
	public static boolean inUsable(String verbo) {
		return Arrays.asList(usable).contains(verbo);
	}
	
	public static boolean inDescartar(String verbo) {
		return Arrays.asList(descartables).contains(verbo);
	}
	
	public static void cargarLexico() throws IOException {

		FileReader fr = new FileReader("./Recursos/lexico.txt");
		BufferedReader bf = new BufferedReader(fr);
		String linea;

		while (!(linea = bf.readLine()).trim().isEmpty()) {

			String[] lexico = linea.split(";");

			EnumTipoLexico tipolexico = EnumTipoLexico.valueOf(lexico[0].toUpperCase());

			switch (tipolexico) {
			case ADQUIRIBLES:
				adquirible = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, adquirible, 0, lexico.length - 1);
				break;
			case ROMPIBLES:
				rompibles = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, rompibles, 0, lexico.length - 1);
				break;
			case CONSUMIBLE:
				consumible = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, consumible, 0, lexico.length - 1);
				break;
			case INSPECCIONABLE:
				inspeccionable = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, inspeccionable, 0, lexico.length - 1);
				break;
			case USABLE:
				usable = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, usable, 0, lexico.length - 1);
				break;
			case MOVIMIENTO:
				movimiento = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, movimiento, 0, lexico.length - 1);
				break;
			case MOVIMIENTOSIMPLE:
				movimientoSimple = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, movimientoSimple, 0, lexico.length - 1);
				break;
			case USUARIO:
				usuario = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, usuario, 0, lexico.length - 1);
				break;
			case USUARIOSIMPLE:
				usuarioSimple = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, usuarioSimple, 0, lexico.length - 1);
				break;
			case NPC:
				NPC = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, NPC, 0, lexico.length - 1);
				break;
			case ATAQUE:
				ataque = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, ataque, 0, lexico.length - 1);
				break;
			case ATRIBUTO:
				atributos = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, atributos, 0, lexico.length - 1);
				break;
			case CARACTERESESPECIALES:
				caracteresEspeciales = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, caracteresEspeciales, 0, lexico.length - 1);
				break;
			case DESCARTABLES:
				descartables = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, descartables, 0, lexico.length - 1);
				break;	

			default:
				break;
			}

		}

		bf.close();
	}
}

