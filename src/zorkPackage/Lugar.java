package zorkPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import zorkGraficos.LugarGrafico;

public class Lugar {

	private String nombre;
	private List<String> keyWordsNombre;
	private String descripcion;
	private List<Objeto> objetos;
	private String mensajeLimite;
	private LugarGrafico grafica;
	
	public LugarGrafico getGrafica() {
		return grafica;
	}

	public void setGrafica(LugarGrafico grafica) {
		this.grafica = grafica;
	}

	public String getMensajeLimite() {
		return mensajeLimite;
	}

	public void setMensajeLimite(String mensajeLimite) {
		this.mensajeLimite = mensajeLimite;
	}

	public Lugar() {
		objetos = new ArrayList<Objeto>();
		keyWordsNombre = new ArrayList<String>();
	};
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		ArrayList<String> cadena = new ArrayList<String>(Arrays.asList(nombre.toLowerCase().split(" ")));
		Lexico.removerAtributos(cadena);
		Lexico.removerErrores(cadena);
		this.keyWordsNombre = cadena;
	}
	
	public List<String> getKeyWordsNombre() {
		return keyWordsNombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(ArrayList<Objeto> objetos) {
		this.objetos = objetos;
	}
	
	public List<Objeto> getObjeto(List<String> keyWords) {
		
		List<Objeto> objetosEnLugar = this.objetos;
		List<Objeto> resultado = new ArrayList<>(objetosEnLugar);
		
		int i = 0;
		do {
			
			for (Objeto objeto : objetosEnLugar) {
				if(objeto.getKeyWordsNombre() == null ||  objeto.getKeyWordsNombre().size() <= i || !objeto.getKeyWordsNombre().get(i).equals(keyWords.get(i)))
					resultado.remove(objeto);
			}
			
			objetosEnLugar = new ArrayList<>(resultado);

			i++;
			
		}while(i < keyWords.size() && resultado.size() > 1);

		
		if(resultado.size() > 1) {
			for (Objeto objeto : objetosEnLugar)  {
				if(objeto.getKeyWordsNombre().size() > keyWords.size())
					resultado.remove(objeto);
			}
		}
		
		//Si ya paso y no encontro nada de nada, se fija si el usuario escribio mal y al menos existe un lugar con esas palabras...
		//Sino lo encuentra o si encuentra mas de 1 ya esta, tampoco le vas a leer la mente.
		if(resultado.size() == 0)
		{
			objetosEnLugar = this.objetos;
			resultado = new ArrayList<>(objetosEnLugar);
			i = 0;
			
			do {
				
				for (Objeto objeto : objetosEnLugar) {
					if(objeto.getKeyWordsNombre() == null  || objeto.getKeyWordsNombre().size() <= i || !objeto.getKeyWordsNombre().contains(keyWords.get(i)))
						resultado.remove(objeto);
				}
				
				objetosEnLugar = new ArrayList<>(resultado);

				i++;
				
			}while(i < keyWords.size() && resultado.size() > 1);
		}
		
		return resultado;
	}
	
	public Item getItem(List<String> keyWords) {
		List<Objeto> obj = getObjeto(keyWords);
		if (obj == null  || obj.size() < 1 || !(obj.get(0) instanceof Item))
			return null;
		
		return (Item)obj.get(0);
	}
	
	public NPC getNPC(List<String> keyWords) {
		List<Objeto> obj = getObjeto(keyWords);
		if (obj == null  || obj.size() < 1 || !(obj.get(0) instanceof NPC))
			return null;
		
		return (NPC)obj.get(0);
	}
	
	public Contenedor getContenedor(List<String> keyWords) {
		List<Objeto> obj = getObjeto(keyWords);
		if (obj == null || obj.size() < 1 || !(obj.get(0) instanceof Contenedor))
			return null;
		
		return (Contenedor)obj.get(0);
	}

	public Obstaculo getObstaculo(String direccion) {
		return this.objetos.stream().filter(x -> x instanceof Obstaculo)
				.map(x -> (Obstaculo) x)
				.filter(x -> x.getDireccion().toLowerCase().equals(direccion))
				.findAny()
				.orElse(null);
	}

	public void removerObjeto(Objeto objeto) {
		
		List<Obstaculo> obstaculos = null;
		
		try {
			 obstaculos = this.objetos
						.stream()
						.filter(x -> x instanceof Obstaculo)
						.map(x -> (Obstaculo) x)
						.filter(x -> x.getObjeto().getObjetoID().equals(objeto.getObjetoID()))
						.collect(Collectors.toList());
		}
		catch(Exception e)
		{
			obstaculos = null;
		}
		
		if(obstaculos != null)
			this.objetos.removeAll(obstaculos);
		
		if(objeto.getDescripcionMapa() != null)
			cambiarDescripcion(objeto);
	
		this.getGrafica().removeSprite(objeto.getGrafica());
		this.objetos.remove(objeto);
		
	}
	
	private void cambiarDescripcion(Objeto objetoReferencia) {
		this.descripcion = this.descripcion.replace(objetoReferencia.getDescripcionMapa(), "");
	}

	public void agregarObjeto(Objeto objeto) {
		this.objetos.add(objeto);
		this.descripcion += objeto.getDescripcionMapa();
	}
	
	public String romperObjeto(Contenedor objeto) {
		
		for (Item item : objeto.getContenido()) {
			this.objetos.add(item);
		}
		
		removerObjeto(objeto);
		return objeto.getMensajeRompible();
	}
	
	

}
