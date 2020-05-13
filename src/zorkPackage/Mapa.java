package zorkPackage;

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
	
	
	public Object moverNorte() {
		
		//Se fija sino esta fuera del mapa.
		if(getPosibleLugar(0,1,0) == null) {
			//Esto seria para testear
			imprimirPosicion();
			return null;
		}
		
		//Se fija si el lugar donde esta parado tiene obstaculos en esa direccion.
		Obstaculo obstaculo = getLugarActual().existeObstaculo("norte");
		if(obstaculo != null)
			return obstaculo;
		
		//Si todo sale bien, suma movimientos, se mueve y devuelve el lugar pa q lo imprima el GM.
		this.cantidadMovimientos++;
		this.posicionActual.setY(this.posicionActual.getY() + 1);
		//Esto seria para testear
		imprimirPosicion();
		return getLugarActual();
	}
	
	public Object moverSur() {
		
		if(getPosibleLugar(0,-1,0) == null) {
			//Esto seria para testear
			imprimirPosicion();
			return null;
		}
		
		Obstaculo obstaculo = getLugarActual().existeObstaculo("sur");
		if(obstaculo != null)
			return obstaculo;
		
		this.cantidadMovimientos++;
		this.posicionActual.setY(this.posicionActual.getY() - 1);
		//Esto seria para testear
		imprimirPosicion();
		return getLugarActual();
	}
	
	public Object moverEste() {
		
		if(getPosibleLugar(1,0,0) == null)  {
			//Esto seria para testear
			imprimirPosicion();
			return null;
		}
		
		Obstaculo obstaculo = getLugarActual().existeObstaculo("este");
		if(obstaculo != null)
			return obstaculo;
		
		this.cantidadMovimientos++;
		this.posicionActual.setX(this.posicionActual.getX() + 1);
		//Esto seria para testear
		imprimirPosicion();
		return getLugarActual();
	}
	
	public Object moverOeste() {
		
		if(getPosibleLugar(-1,0,0) == null) {
			//Esto seria para testear
			imprimirPosicion();
			return null;
		}
		
		Obstaculo obstaculo = getLugarActual().existeObstaculo("oeste");
		if(obstaculo != null)
			return obstaculo;
		
		this.cantidadMovimientos++;
		this.posicionActual.setX(this.posicionActual.getX() -1);
		//Esto seria para testear
		imprimirPosicion();
		return getLugarActual();
	}
	
	public Object moverAbajo() {
		
		//Se fija sino esta fuera del mapa.
		if(getPosibleLugar(0,0,-1) == null) {
			//Esto seria para testear
			imprimirPosicion();
			return null;
		}
		
		//Se fija si el lugar donde esta parado tiene obstaculos en esa direccion.
		Obstaculo obstaculo = getLugarActual().existeObstaculo("abajo");
		if(obstaculo != null)
			return obstaculo;
		
		//Si todo sale bien, suma movimientos, se mueve y devuelve el lugar pa q lo imprima el GM.
		this.cantidadMovimientos++;
		this.posicionActual.setZ(this.posicionActual.getZ() - 1);
		//Esto seria para testear
		imprimirPosicion();
		return getLugarActual();
	}
	
	public Object moverArriba() {
		
		//Se fija sino esta fuera del mapa.
		if(getPosibleLugar(0,0,1) == null) {
			//Esto seria para testear
			imprimirPosicion();
			return null;
		}
		
		//Se fija si el lugar donde esta parado tiene obstaculos en esa direccion.
		Obstaculo obstaculo = getLugarActual().existeObstaculo("arriba");
		if(obstaculo != null)
			return obstaculo;
		
		//Si todo sale bien, suma movimientos, se mueve y devuelve el lugar pa q lo imprima el GM.
		this.cantidadMovimientos++;
		this.posicionActual.setZ(this.posicionActual.getZ() + 1);
		//Esto seria para testear
		imprimirPosicion();
		return getLugarActual();
	}
	
	public Object moverSurEste() {
		
		//Se fija sino esta fuera del mapa.
		if(getPosibleLugar(1,-1,0) == null) {
			//Esto seria para testear
			imprimirPosicion();
			return null;
		}
		
		//Se fija si el lugar donde esta parado tiene obstaculos en esa direccion.
		Obstaculo obstaculo = getLugarActual().existeObstaculo("sureste");
		if(obstaculo != null)
			return obstaculo;
		
		//Si todo sale bien, suma movimientos, se mueve y devuelve el lugar pa q lo imprima el GM.
		this.cantidadMovimientos++;
		this.posicionActual.setX(this.posicionActual.getX() + 1);
		this.posicionActual.setY(this.posicionActual.getY() - 1);
		//Esto seria para testear
		imprimirPosicion();
		return getLugarActual();
	}
	
	public Object moverSurOeste() {
		
		//Se fija sino esta fuera del mapa.
		if(getPosibleLugar(-1,-1,0) == null) {
			//Esto seria para testear
			imprimirPosicion();
			return null;
		}
		
		//Se fija si el lugar donde esta parado tiene obstaculos en esa direccion.
		Obstaculo obstaculo = getLugarActual().existeObstaculo("suroeste");
		if(obstaculo != null)
			return obstaculo;
		
		//Si todo sale bien, suma movimientos, se mueve y devuelve el lugar pa q lo imprima el GM.
		this.cantidadMovimientos++;
		this.posicionActual.setX(this.posicionActual.getX() - 1);
		this.posicionActual.setY(this.posicionActual.getY() - 1);
		//Esto seria para testear
		imprimirPosicion();
		return getLugarActual();
	}
	
	public Object moverNorEste() {
		
		//Se fija sino esta fuera del mapa.
		if(getPosibleLugar(1,1,0) == null) {
			//Esto seria para testear
			imprimirPosicion();
			return null;
		}
		
		//Se fija si el lugar donde esta parado tiene obstaculos en esa direccion.
		Obstaculo obstaculo = getLugarActual().existeObstaculo("noreste");
		if(obstaculo != null)
			return obstaculo;
		
		//Si todo sale bien, suma movimientos, se mueve y devuelve el lugar pa q lo imprima el GM.
		this.cantidadMovimientos++;
		this.posicionActual.setX(this.posicionActual.getX() + 1);
		this.posicionActual.setY(this.posicionActual.getY() + 1);
		//Esto seria para testear
		imprimirPosicion();
		return getLugarActual();
	}
	
	public Object moverNorOeste() {
		
		//Se fija sino esta fuera del mapa.
		if(getPosibleLugar(-1,1,0) == null) {
			//Esto seria para testear
			imprimirPosicion();
			return null;
		}
		
		//Se fija si el lugar donde esta parado tiene obstaculos en esa direccion.
		Obstaculo obstaculo = getLugarActual().existeObstaculo("noroeste");
		if(obstaculo != null)
			return obstaculo;
		
		//Si todo sale bien, suma movimientos, se mueve y devuelve el lugar pa q lo imprima el GM.
		this.cantidadMovimientos++;
		this.posicionActual.setX(this.posicionActual.getX() - 1);
		this.posicionActual.setY(this.posicionActual.getY() + 1);
		//Esto seria para testear
		imprimirPosicion();
		return getLugarActual();
	}
	
	public Object irHacia(String nombreLugar) {
		
		Lugar lugar;
		
		if(getLugarActual().getNombre().equals(nombreLugar))
			return "Ya te encuentras en este lugar!";
		
		//Norte
		lugar = getPosibleLugar(0,1,0);
		if(lugar != null && lugar.getNombre().toLowerCase().equals(nombreLugar))
		{
			Obstaculo obs = lugar.existeObstaculo("norte");
			if(obs != null)
				return obs;
			
			this.cantidadMovimientos++;
			this.posicionActual.setY(this.posicionActual.getY() + 1);
			//Esto seria para testear
			imprimirPosicion();
			return lugar;
		}
		
		//Sur
		lugar = getPosibleLugar(0,-1,0);
		if(lugar != null && lugar.getNombre().toLowerCase().equals(nombreLugar))
		{
			Obstaculo obs = lugar.existeObstaculo("sur");
			if(obs != null)
				return obs;
			
			this.cantidadMovimientos++;
			this.posicionActual.setY(this.posicionActual.getY() - 1);
			//Esto seria para testear
			imprimirPosicion();
			return lugar;
		}
			
		//Este
		lugar = getPosibleLugar(1,0,0);
		if(lugar != null && lugar.getNombre().toLowerCase().equals(nombreLugar))
		{
			Obstaculo obs = lugar.existeObstaculo("este");
			if(obs != null)
				return obs;
			
			this.cantidadMovimientos++;
			this.posicionActual.setX(this.posicionActual.getX() + 1);
			//Esto seria para testear
			imprimirPosicion();
			return lugar;
		}
		
		//Oeste
		lugar = getPosibleLugar(-1,0,0);
		if(lugar != null && lugar.getNombre().toLowerCase().equals(nombreLugar))
		{
			Obstaculo obs = lugar.existeObstaculo("oeste");
			if(obs != null)
				return obs;
			
			this.cantidadMovimientos++;
			this.posicionActual.setX(this.posicionActual.getX() - 1);
			return lugar;
		}
		
		return null;
	}
	
	//Esto esta armado solo para respetar que GAMEMASTER llame solo al mapa.
	public Item tomarItem(String nombreObjeto) {
		return getLugarActual().tomarItem(nombreObjeto);
	}
	
	//Esto tambien
	public void cambiarDescripcionLugarActual(Item item) {
		getLugarActual().cambiarDescripcion(item);
	}
	
	public Objeto mostrarObjeto(String nombreObjeto) {
		return getLugarActual().mostrarDescripcion(nombreObjeto);
	}
	
	public NPC getNPC(String nombreNPC) {
		return getLugarActual().getNPC(nombreNPC);
	}
	
	public Objeto getObjeto(String nombreObjeto) {
		return getLugarActual().getObjeto(nombreObjeto);
	}
	
	public void removerObjeto(Objeto objeto) {
		getLugarActual().removerObjeto(objeto);
	}
	
	
	
}
