package zorkPackage;

public class Mapa {
	
	private String nombre;
	private Lugar[][][] lugares;
	private Posicion posicionActual;
	private int cantidadMovimientos;
	
	public Mapa(String nombre, Lugar[][][] lugares, Posicion posicionActual, int cantidadMovimientos) {
		super();
		this.nombre = nombre;
		this.lugares = lugares;
		this.posicionActual = posicionActual;
		this.cantidadMovimientos = cantidadMovimientos;
	}
	
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
		
		Lugar lugar = getLugarActual();
		
		if(!lugar.existeNorte())
			return lugar.getMsjExisteNorte();

		if(getPosibleLugar(0,1,0) == null) {
			//Esto seria para testear
			imprimirPosicion();
			return Mensaje.fueraLimite();
		}
		
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
