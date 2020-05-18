package zorkPackage;


public class Mapa {
	
	private String nombre;
	private String descripcion;
	private Lugar[][][] lugares;
	private Personaje personajeActual;
	private int cantidadMovimientos;
	
	public Mapa() {
		this.cantidadMovimientos = 0;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getCantidadMovimientos() {
		return cantidadMovimientos;
	}
	
	public void setPersonajeActual(Personaje personaje) {
		this.personajeActual = personaje;
	}
	
	public Personaje getPersonajeActual() {
		return personajeActual;
	}
	
	public Posicion getPosicionActual() {
		return this.personajeActual.getPosicionActual();
	}

	public void setPosicionActual(Posicion posicionActual) {
		this.personajeActual.setPosicionActual(posicionActual);
	}
	
	public void setTamanio(int x, int y, int z) {
		this.lugares = new Lugar[x][y][z];
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
		System.out.println(this.personajeActual.getPosicionActual());
	}

	public Lugar getLugarActual() {
		Posicion actual = this.personajeActual.getPosicionActual();
		return lugares[actual.getX()][actual.getY()][actual.getZ()];
	}
	
	private Lugar getPosibleLugar(int x, int y, int z) {
		Posicion actual = this.personajeActual.getPosicionActual();
		try { 
			return lugares[actual.getX() + x][actual.getY() + y][actual.getZ() + z]; 
			}
		catch ( IndexOutOfBoundsException e ) { 
			return null;
			}
	}
	
	public Object moverNorte() {
		
		//Se fija sino esta fuera del mapa.
		if(getPosibleLugar(0,1,0) == null)
			return null;
		
		//Se fija si el lugar donde esta parado tiene obstaculos en esa direccion.
		Obstaculo obstaculo = getLugarActual().existeObstaculo("norte");
		if(obstaculo != null)
			return obstaculo;
		
		//Si todo sale bien, suma movimientos, se mueve y devuelve el lugar pa q lo imprima el GM.
		this.cantidadMovimientos++;
		this.personajeActual.irNorte();//Mueve el personaje al norte

		return getLugarActual();
	}
	
	public Object moverSur() {
		
		if(getPosibleLugar(0,-1,0) == null)
			return null;
		
		
		Obstaculo obstaculo = getLugarActual().existeObstaculo("sur");
		if(obstaculo != null)
			return obstaculo;
		
		this.cantidadMovimientos++;
		this.personajeActual.irSur();

		return getLugarActual();
	}
	
	public Object moverEste() {
		
		if(getPosibleLugar(1,0,0) == null)
			return null;
		
		Obstaculo obstaculo = getLugarActual().existeObstaculo("este");
		if(obstaculo != null)
			return obstaculo;
		
		this.cantidadMovimientos++;
		this.personajeActual.irEste();

		return getLugarActual();
	}
	
	public Object moverOeste() {
		
		if(getPosibleLugar(-1,0,0) == null)
			return null;
		
		
		Obstaculo obstaculo = getLugarActual().existeObstaculo("oeste");
		if(obstaculo != null)
			return obstaculo;
		
		this.cantidadMovimientos++;
		this.personajeActual.irOeste();
		return getLugarActual();
	}
	
	public Object moverAbajo() {
		
		//Se fija sino esta fuera del mapa.
		if(getPosibleLugar(0,0,-1) == null)
			return null;
		
		
		//Se fija si el lugar donde esta parado tiene obstaculos en esa direccion.
		Obstaculo obstaculo = getLugarActual().existeObstaculo("abajo");
		if(obstaculo != null)
			return obstaculo;
		
		//Si todo sale bien, suma movimientos, se mueve y devuelve el lugar pa q lo imprima el GM.
		this.cantidadMovimientos++;
		this.personajeActual.irAbajo();
		//Esto seria para testear
		imprimirPosicion();
		return getLugarActual();
	}
	
	public Object moverArriba() {
		
		//Se fija sino esta fuera del mapa.
		if(getPosibleLugar(0,0,1) == null)
			return null;
		
		//Se fija si el lugar donde esta parado tiene obstaculos en esa direccion.
		Obstaculo obstaculo = getLugarActual().existeObstaculo("arriba");
		if(obstaculo != null)
			return obstaculo;
		
		//Si todo sale bien, suma movimientos, se mueve y devuelve el lugar pa q lo imprima el GM.
		this.cantidadMovimientos++;
		this.personajeActual.irArriba();
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
			this.personajeActual.irNorte();
	
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
			this.personajeActual.irSur();
		
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
			this.personajeActual.irEste();

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
			this.personajeActual.irOeste();
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
	
	public void agregarObjeto(Objeto objeto) {
		getLugarActual().agregarObjeto(objeto);
	}
	
	
}
