package zorkPackage;

import java.util.Arrays;
import java.util.Comparator;

public class Mapa {
	
	private Posicion posicionActual;
	private Lugar[][][] lugares;
	private int cantidadMovimientos;
	
	public int getCantidadMovimientos() {
		return cantidadMovimientos;
	}
	
	public Posicion getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(Posicion posicionActual) {
		this.posicionActual = posicionActual;
	}
	
	public Mapa(Posicion posicionActual) {
		super();

		this.lugares = new Lugar[20][20][2];
		this.posicionActual = posicionActual;
		this.cantidadMovimientos = 0;
	}
	
	public void addLugar(Lugar lugar, Posicion posicion) {
		this.lugares[posicion.getX()][posicion.getY()][posicion.getZ()] = lugar;
	}
	
	//Esto por ahora lo dejo para testear, dp se lo podemos dar al loco cuando encuentre un mapa o algo asi
	public void verMapa() {
		
		for (int i = 0; i < 19; i++) {
			System.out.println();
			for (int j = 0; j < 19; j++) {
				if(lugares[j][i][0] == null)
					System.out.format("%10s", " ");
				else {
					System.out.print("[ " + lugares[j][i][0].getNombre() +" ]");
				}

			}
		}
	}
	
	//testeo
	private void imprimirPosicion() {
		System.out.println(this.posicionActual);
	}
	
	public String lugarActual() {
		return Mensaje.mensajeLugar(getLugarActual());
	}

	public Lugar getLugarActual() {
		return lugares[this.posicionActual.getX()][this.posicionActual.getY()][this.posicionActual.getZ()];
	}
	
	private Lugar getPosibleLugar(int x, int y, int z) {
		try { 
			return lugares[this.posicionActual.getX() + x][this.posicionActual.getY() + y][this.posicionActual.getZ() + z]; 
			}
		catch ( IndexOutOfBoundsException e ) { 
			return null;
			}
	}
	
	public String moverNorte() {
		
		if(getPosibleLugar(0,1,0) == null) {
			//Esto seria para testear
			imprimirPosicion();
			return Mensaje.fueraLimite();
		}
		
		Lugar lugar = getLugarActual();
		
		/*if(lugar.getNorte() == null)
			return lugar.getNoExisteNorte();*/


		
		this.cantidadMovimientos++;
		this.posicionActual.setY(this.posicionActual.getY() + 1);
		//Esto seria para testear
		imprimirPosicion();
		return Mensaje.mensajeLugar(getLugarActual());
	}
	
	public String moverSur() {
		
		if(getPosibleLugar(0,-1,0) == null) 
		    return Mensaje.fueraLimite();
		
		this.cantidadMovimientos++;
		this.posicionActual.setY(this.posicionActual.getY() - 1);
		
		//Esto seria para testear
		imprimirPosicion();
		return Mensaje.mensajeLugar(getLugarActual());
	}
	
	public String moverEste() {
		if(getPosibleLugar(1,0,0) == null) 
		    return Mensaje.fueraLimite();

		this.cantidadMovimientos++;
		this.posicionActual.setX(this.posicionActual.getX() + 1);
		
		//Esto seria para testear
		imprimirPosicion();
		return Mensaje.mensajeLugar(getLugarActual());
	}
	
	public String moverOeste() {
		if(getPosibleLugar(-1,0,0) == null) 
		    return Mensaje.fueraLimite();
			
		this.cantidadMovimientos++;
		this.posicionActual.setX(this.posicionActual.getX() -1);
		
		//Esto seria para testear
		imprimirPosicion();
		
		return Mensaje.mensajeLugar(getLugarActual());
	}
	
	public Objeto tomarObjeto(String nombreObjeto) {
		return getLugarActual().tomarObjeto(nombreObjeto);
	}


	
}
