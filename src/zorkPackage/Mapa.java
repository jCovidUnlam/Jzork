package zorkPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import zorkEnum.EnumDireccion;

public class Mapa {
	
	private String nombre;
	private String descripcion;
	private Lugar[][][] lugares;
	private Personaje personajeActual;
	private List<Objeto> objAux = new ArrayList<Objeto>();
	
	public Mapa() {

	}
	
	public List<Objeto> getObjAux() {
		return objAux;
	}

	public void setObjAux(List<Objeto> objAux) {
		this.objAux = objAux;
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
			return Mensaje.fueraLimite(getLugarActual().getMensajeLimite());

		Obstaculo obstaculo = getLugarActual().getObstaculo(direccion);
		if(obstaculo != null)
			return Mensaje.existeObstaculo(obstaculo);
		
		this.personajeActual.ir(pos);
		
		InitConfig.getGm().getPantalla().getPanel().setLugarActual(this.getLugarActual().getGrafica());
		InitConfig.getGm().getPantalla().getPanel().actualizarPantalla();
		
		return Mensaje.mensajeLugar(lugar);
	}
	
	public String irHacia(List<String> keyWordsLugar) {
		
		if(getLugarActual().getKeyWordsNombre().equals(keyWordsLugar))
			return "Ya te encuentras en este lugar!";
		
		Posicion pos = posicionDireccion(keyWordsLugar.get(0));
		if(pos != null)
			return mover(keyWordsLugar.get(0));
		
		List<Limitrofe> limitrofes = listarLimitrofes();
		List<Limitrofe> resultado = new ArrayList<Limitrofe>(limitrofes);
		
		int i = 0;
		do {
			
			for (Limitrofe limitrofe : limitrofes) {
				if(limitrofe.getLugar().getKeyWordsNombre().size() <= i || !limitrofe.getLugar().getKeyWordsNombre().get(i).equals(keyWordsLugar.get(i)))
					resultado.remove(limitrofe);
			}
			
			limitrofes = new ArrayList<>(resultado);

			i++;
			
		}while(i < keyWordsLugar.size() && resultado.size() > 1);
		
		
		
		if(resultado.size() > 1) {
			for (Limitrofe limitrofe : limitrofes)  {
				if(limitrofe.getLugar().getKeyWordsNombre().size() > keyWordsLugar.size())
					resultado.remove(limitrofe);
			}
		}
		
		if(resultado.size() == 0)
		{
			limitrofes = listarLimitrofes();
			resultado = new ArrayList<>(limitrofes);
			i = 0;
			
			do {
				
				for (Limitrofe limitrofe : limitrofes) {
					if(limitrofe.getLugar().getKeyWordsNombre().size() <= i || !limitrofe.getLugar().getKeyWordsNombre().contains(keyWordsLugar.get(i)))
						resultado.remove(limitrofe);
				}
				
				limitrofes = new ArrayList<>(resultado);

				i++;
				
			}while(i < keyWordsLugar.size() && resultado.size() > 1);
			
		}

		
		if(resultado.size() > 1)
			return Mensaje.lugarDuplicado(resultado.stream().map(x -> x.getLugar().getNombre()).collect(Collectors.toList()));
		
		if(resultado.size() == 0 )
			return "No existe ese lugar!";
		
		return mover(resultado.get(0).getDireccion().getValue());			
	}
	
	private List<Limitrofe> listarLimitrofes(){
		
		List<Limitrofe> returned = new ArrayList<Limitrofe>();
		
		Lugar lugar;
		
		lugar = getLugar(posicionDireccion(EnumDireccion.NORTE.getValue()));
		if(lugar != null)
			returned.add(new Limitrofe(EnumDireccion.NORTE, lugar));
		
		lugar = getLugar(posicionDireccion(EnumDireccion.SUR.getValue()));
		if(lugar != null)
			returned.add(new Limitrofe(EnumDireccion.SUR, lugar));
		
		lugar = getLugar(posicionDireccion(EnumDireccion.ESTE.getValue()));
		if(lugar != null)
			returned.add(new Limitrofe(EnumDireccion.ESTE, lugar));
		
		lugar = getLugar(posicionDireccion(EnumDireccion.OESTE.getValue()));
		if(lugar != null)
			returned.add(new Limitrofe(EnumDireccion.OESTE, lugar));
		
		lugar = getLugar(posicionDireccion(EnumDireccion.ABAJO.getValue()));
		if(lugar != null)
			returned.add(new Limitrofe(EnumDireccion.ABAJO, lugar));
		
		lugar = getLugar(posicionDireccion(EnumDireccion.ARRIBA.getValue()));
		if(lugar != null)
			returned.add(new Limitrofe(EnumDireccion.ARRIBA, lugar));
		
		return returned;
	}
	
	
}
