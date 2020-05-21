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
	
	//BORRAR
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
	
	public Lugar getLugarActual() {
		Posicion actual = this.personajeActual.getPosicionActual();
		return lugares[actual.getX()][actual.getY()][actual.getZ()];
	}
	
	private Lugar getLugar(Posicion p) {
		Posicion actual = this.personajeActual.getPosicionActual();
		try {
			return lugares[actual.getX() + p.getX()][actual.getY() + p.getY()][actual.getZ() + p.getZ()];
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	private Posicion posicionDireccion(String direccion) {
		switch (direccion) {
		case "norte":
			return new Posicion(0,1,0);
		case "sur":
			return new Posicion(0,-1,0);
		case "este":
			return new Posicion(1,0,0);
		case "oeste":
			return new Posicion(-1,0,0);
		case "abajo":
		case "bajar":
			return new Posicion(0,0,-1);
		case "arriba":
		case "subir":
		case "escalar":
		case "trepar":
			return new Posicion(0,0,1);
			default:
				return null;
		}
	}
	
	public String mover(String direccion) {
		Posicion pos = posicionDireccion(direccion);
		Lugar lugar = getLugar(pos);
		if(lugar == null)
			return Mensaje.fueraLimite();

		Obstaculo obstaculo = getLugarActual().getObstaculo(direccion);
		if(obstaculo != null)
			return Mensaje.existeObstaculo(obstaculo);
		
		this.cantidadMovimientos++;
		this.personajeActual.ir(pos);
		return Mensaje.mensajeLugar(lugar);
	}
	
	public String irHacia(String nombreLugar) {
		
		if(getLugarActual().getNombre().equals(nombreLugar))
			return "Ya te encuentras en este lugar!";
		
		Posicion pos = posicionDireccion(nombreLugar);
		if(pos != null)
			return mover(nombreLugar);
		
		Lugar lugar;
		
		lugar = getLugar(posicionDireccion("norte"));
		if(lugar != null && lugar.getNombre().toLowerCase().equals(nombreLugar))
			return mover("norte");
		
		lugar = getLugar(posicionDireccion("sur"));
		if(lugar != null && lugar.getNombre().toLowerCase().equals(nombreLugar))
			return mover("sur");
		
		lugar = getLugar(posicionDireccion("este"));
		if(lugar != null && lugar.getNombre().toLowerCase().equals(nombreLugar))
			return mover("este");
		
		lugar = getLugar(posicionDireccion("oeste"));
		if(lugar != null && lugar.getNombre().toLowerCase().equals(nombreLugar))
			return mover("oeste");
		
		lugar = getLugar(posicionDireccion("abajo"));
		if(lugar != null && lugar.getNombre().toLowerCase().equals(nombreLugar))
			return mover("abajo");
		
		lugar = getLugar(posicionDireccion("arriba"));
		if(lugar != null && lugar.getNombre().toLowerCase().equals(nombreLugar))
			return mover("arriba");
		
		return "No existe ese lugar!";
	}
}
