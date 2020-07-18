package zorkPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import zorkEnum.EnumTipoLexico;

public final class Lexico {
	
	public static  String[] movimientoSimple; 
	
	public static  String[] usuarioSimple;

	public static  String[] adquiribles;

	public static  String[] rompibles;

	public static  String[] consumibles;

	public static  String[] inspeccionables;

	public static  String[] usables; 
	
	public static  String[] movimiento; 

	public static  String[] usuario;

	public static  String[] NPC; 
	
	public static  String[] ataque;
	
	public static String [] descartables;
	
	public static String [] adquirible_consumible;

	public static String [] salida;
	
	public static String [] atributos;
	
	public static String [] caracteresEspeciales;
	
	public static String [] caracteresUnion;

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
		return Arrays.asList(adquiribles).contains(verbo);
	}
	
	public static boolean inRompible(String verbo) {
		return Arrays.asList(rompibles).contains(verbo);
	}
	
	public static boolean inConsumible(String verbo) {
		return Arrays.asList(consumibles).contains(verbo);
	}
	
	public static boolean inInspeccionable(String verbo) {
		return Arrays.asList(inspeccionables).contains(verbo);
	}
	
	public static boolean inUsable(String verbo) {
		return Arrays.asList(usables).contains(verbo);
	}
	
	public static boolean inDescartar(String verbo) {
		return Arrays.asList(descartables).contains(verbo);
	}
	
	public static boolean inAdquiribles_Consumibles(String verbo) {
		return Arrays.asList(adquirible_consumible).contains(verbo);
	}
	
	public static boolean inSalida(String verbo) {
		return Arrays.asList(salida).contains(verbo);
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
				adquiribles = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, adquiribles, 0, lexico.length - 1);
				break;
			case ROMPIBLES:
				rompibles = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, rompibles, 0, lexico.length - 1);
				break;
			case CONSUMIBLES:
				consumibles = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, consumibles, 0, lexico.length - 1);
				break;
			case INSPECCIONABLES:
				inspeccionables = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, inspeccionables, 0, lexico.length - 1);
				break;
			case USABLES:
				usables = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, usables, 0, lexico.length - 1);
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
			case DESCARTABLES:
				descartables = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, descartables, 0, lexico.length - 1);
				break;
			case ADQUIRIBLE_CONSUMIBLE:
				adquirible_consumible = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, adquirible_consumible, 0, lexico.length - 1);
				break;
			case SALIDA:
				salida = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, salida, 0, lexico.length - 1);
				break;
			case ATRIBUTOS:
				atributos = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, atributos, 0, lexico.length - 1);
				break;
			case CARACTERESESPECIALES:
				caracteresEspeciales = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, caracteresEspeciales, 0, lexico.length - 1);
				break;
			case CARACTERESUNION:
				caracteresUnion = new String[lexico.length - 1];
				System.arraycopy(lexico, 1, caracteresUnion, 0, lexico.length - 1);
				break;

			default:
				break;
			}

		}

		bf.close();
	}

	public static void removerErrores(List<String> cadena) {

		List<String> errores = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add(" ");
				add("");
			}
		};

		cadena.removeAll(errores);
	}

	public static void removerAtributos(List<String> cadena) {
		cadena.removeAll(Arrays.asList(Lexico.atributos));
	}
	
	public static void removerCaracteresEspeciales(List<String> cadena) {
		cadena.removeAll(Arrays.asList(Lexico.caracteresEspeciales));
	}
	
	public static boolean noPoseeCaracterDeUnion(List<String> cadena) {
		return Collections.disjoint(cadena, Arrays.asList(Lexico.caracteresUnion));
	}
	
	public static void removerCaracterDeUnion(List<String> cadena) {
		cadena.removeAll(Arrays.asList(Lexico.caracteresUnion));
	}


}

