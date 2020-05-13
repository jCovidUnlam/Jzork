package zorkPackage;

public class GameMaster {
	
	private Aventura aventura;

	public GameMaster(Aventura aventura) {
		super();
		this.aventura = aventura;
		Consola.iniciarAventura(aventura);
		Consola.mostrar(Mensaje.mensajeLugar(aventura.getMapa().getLugarActual()));
	}
	
	public void ejecutar(Comando comando) {
		
		String devolucion = "";
		
		switch(comando.getTipo()) {
		case MOVER:
			devolucion = moverPersonaje(comando);
			break;
		case ADQUIRIR:
			devolucion = adquirirItem(comando);
			break;
		case USUARIO:
			devolucion = ejecutarComandoUsuario(comando);
			break;
		case INSPECCIONAR:
			devolucion = mostrarObjeto(comando);
			break;
		case NPC:
			devolucion = hablarNPC(comando);
			break;
		case USAR:
			devolucion = usarObjeto(comando);
			break;
		case TRIGGER:
			devolucion = ejecutarTrigger(comando);
			break;
		case INVALIDO:
		default:
			devolucion = Mensaje.comandoErroneo();
			break;
		}
		
		Consola.mostrar(devolucion);
	}
	
	private String moverPersonaje(Comando comando) {
		Object obj = null;
		
		switch(comando.getNombre()) {
		case "norte":
			obj = aventura.getMapa().moverNorte();			
			break;
		case "sur":
			obj =  aventura.getMapa().moverSur();
			break;
		case "este":
			obj = aventura.getMapa().moverEste();
			break;
		case "oeste":
			obj = aventura.getMapa().moverOeste();
			break;
		case "ir":
			obj = aventura.getMapa().irHacia(comando.getNombreObjeto());
			break;
		}
		
		if(obj == null)
			return Mensaje.fueraLimite();
		
		//No me cambien un nombre de las clases porque esto no funca
		switch(obj.getClass().getSimpleName()) {
		case "Lugar":
			return Mensaje.mensajeLugar((Lugar)obj);
		case "Obstaculo":
			return Mensaje.existeObstaculo((Obstaculo)obj);
			//Esto es por si tengo que devolver algo raro
		case "String":
			return (String)obj;
		}
		
		return Mensaje.comandoErroneo();
	}
	
	private String adquirirItem(Comando comando) {
		//Lo busca en el lugar
		Item item = aventura.getMapa().tomarItem(comando.getNombreObjeto());
		
		//Si es nulo, muetra que no existe
		if(item == null)
			return Mensaje.noExisteObjeto();
		
		//Sino es tomable, le manda una descripcion de porque no lo es
		if(item.isTomable() == false)
			return item.getMensajeNoTomable();
		else {
			//Remueve el atributo descripcionMapa del item en el Lugar.
			aventura.getMapa().cambiarDescripcionLugarActual(item);
			//Si es tomable, lo agrega al inventario
			aventura.getPersonaje().addObjeto(item);
			//Finalmente, muetra mensaje de que lo tomo.
			return item.getMensajeTomable();
		}
	}
	
	private String ejecutarComandoUsuario(Comando comando)
	{
		switch (comando.getNombre()) {
		case "inventario":
			return Mensaje.inventario(aventura.getPersonaje().getInventario(), aventura.getPersonaje().getNombre());
		default:
			return Mensaje.comandoErroneo();
		}
	}
	
	private String mostrarObjeto(Comando comando) {
		
		//Si el comando es "mirar alrededor" muestra el mensaje del mapa
		if(comando.getNombreObjeto().toLowerCase().equals("alrededor"))
			return Mensaje.mensajeLugar(aventura.getMapa().getLugarActual());
		else {
			Objeto obj = aventura.getMapa().mostrarObjeto(comando.getNombreObjeto());
			if(obj != null)
				return Mensaje.mostrarObjeto(obj);
			return Mensaje.noExisteObjeto();
		}
		
	}
	
	private String hablarNPC(Comando comando) {
		
		NPC npc = aventura.getMapa().getNPC(comando.getNombreObjeto());
		if(npc == null)
			return Mensaje.noExisteNPC();
		
		return Mensaje.ncpMensaje(npc);
	}
	
	public String ejecutarTrigger(Comando comando) {		
		
		//El personaje tiene que tener el objeto en el inventario
		Item item = aventura.getPersonaje().getObjetoInventario(comando.getNombreObjeto());
		//El lugar tiene que tener el objeto con el que voy a interactuar
		Objeto afectado = aventura.getMapa().getObjeto(comando.getNombreAfectado());
		
		if(item == null)
			return Mensaje.noTienesItem(aventura.getPersonaje().getNombre());
		
		if(afectado == null)
			return Mensaje.noExisteObjeto();
		
		return TriggerMaster.EjecutarTriggerItem(aventura, comando, afectado, item);
	}
	
	public String usarObjeto(Comando comando) {
		
		//El personaje tiene que tener el objeto en el inventario
		Item item = aventura.getPersonaje().getObjetoInventario(comando.getNombreObjeto());
	
		if(item == null)
			return Mensaje.noTienesItem(aventura.getPersonaje().getNombre());
	
		//Aca se dispara el trigger propio del item hacia el jugador.
		//Tomar pocion
		//Usar espejo
		//Equipar arma
		//....
		
		return "trigerItem";
	}
	
	
}
