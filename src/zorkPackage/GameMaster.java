package zorkPackage;

public class GameMaster {

	private Mapa mapa;
	private boolean endGame;
	
	public GameMaster(Mapa mapa) {
		super();
		this.mapa = mapa;
		endGame = false;
		Consola.iniciarAventura(mapa);
		Consola.mostrar(Mensaje.mensajeLugar(mapa.getLugarActual()));
	}

	public void ejecutar(Comando comando) {

		String devolucion = "";

		switch (comando.getTipo()) {
		case MOVER:
			devolucion = moverPersonaje(comando);
			break;
		case ADQUIRIR:
			devolucion = tomarItem(comando);
			break;
		case DESCARTAR:
			devolucion = soltarItem(comando);
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
			devolucion = ejecutarTriggerItem(comando);
			break;
		case ATACAR:
			devolucion = atacarObjeto(comando);
			break;
		case INVALIDO:
		default:
			devolucion = Mensaje.comandoErroneo();
			break;
		}
		
		Consola.mostrar(devolucion);
	}
	
	public boolean isEndGame() {
		return endGame;
	}

	private String moverPersonaje(Comando comando) {
		if(comando.getNombre().equals("ir"))
			return mapa.irHacia(comando.getNombreObjeto());
		else
			return mapa.mover(comando.getNombre());
	}

	private String tomarItem(Comando comando) {
		Item item = mapa.getLugarActual().getItem(comando.getNombreObjeto());

		// Si es nulo, muetra que no existe
		if (item == null)
			return Mensaje.noExisteObjeto();

		// Sino es tomable, le manda una descripcion de porque no lo es
		if (item.isTomable() == false)
			return item.getMensajeNoTomable();
		else {
		
			// Remueve el atributo descripcionMapa del item en el Lugar.
			mapa.getLugarActual().removerObjeto(item);
			// Si es tomable, lo agrega al inventario
			mapa.getPersonajeActual().addObjeto(item);
			// Finalmente, muetra mensaje de que lo tomo.
			return item.getMensajeTomable();
		}
	}

	private String soltarItem(Comando comando) {

		// obtenemos el objeto de nuestro inventario
		Item item = mapa.getPersonajeActual().getObjetoInventario(comando.getNombreObjeto());

		// Si es nulo, muetra que no existe
		if (item == null)
			return Mensaje.noTienesItem(mapa.getPersonajeActual().getNombre());

		mapa.getPersonajeActual().removerDeInventario(item);
		mapa.getLugarActual().agregarObjeto(item);

		return Mensaje.soltoItem(item);
	}

	private String ejecutarComandoUsuario(Comando comando) {
		switch (comando.getNombre()) {
		case "inventario":
			return Mensaje.mostrarInventario(mapa.getPersonajeActual());
		case "estado":
			return Mensaje.estadoPersonaje(mapa.getPersonajeActual());
		case "equipar":
			Arma arma = mapa.getPersonajeActual().equiparArma(comando.getNombreObjeto());

			if (arma == null)
				return Mensaje.noTienesItem(mapa.getPersonajeActual().getNombre());
			else
				return Mensaje.armaEquipada(arma);

		default:
			return Mensaje.comandoErroneo();
		}
	}

	private String mostrarObjeto(Comando comando) {

		// Si el comando es "mirar alrededor" muestra el mensaje del mapa
		if (comando.getNombreObjeto().toLowerCase().equals("alrededor"))
			return Mensaje.mensajeLugar(mapa.getLugarActual());
		else {
			Objeto obj = mapa.getLugarActual().getObjeto(comando.getNombreObjeto());
			if (obj != null)
				return Mensaje.mostrarObjeto(obj);
			return Mensaje.noExisteObjeto();
		}

	}

	private String hablarNPC(Comando comando) {

		NPC npc = mapa.getLugarActual().getNPC(comando.getNombreObjeto());
		if (npc == null)
			return Mensaje.noExisteNPC();

		return Mensaje.ncpMensaje(npc);
	}

	public String ejecutarTriggerItem(Comando comando) {

		// El personaje tiene que tener el objeto en el inventario
		Item item = mapa.getPersonajeActual().getObjetoInventario(comando.getNombreObjeto());
		// El lugar tiene que tener el objeto con el que voy a interactuar
		Objeto afectado = mapa.getLugarActual().getObjeto(comando.getNombreAfectado());

		if (item == null)
			return Mensaje.noTienesItem(mapa.getPersonajeActual().getNombre());

		if (afectado == null)
			return Mensaje.noExisteObjeto();

		return TriggerMaster.EjecutarTriggerItem(mapa, afectado, item);
	}

	public String usarObjeto(Comando comando) {

		// El personaje tiene que tener el objeto en el inventario
		Item item = mapa.getPersonajeActual().getObjetoInventario(comando.getNombreObjeto());

		if (item == null)
			return Mensaje.noTienesItem(mapa.getPersonajeActual().getNombre());

		// Aca se dispara el trigger propio del item hacia el jugador.
		// Tomar pocion
		// Usar espejo
		// Equipar arma
		// ....

		return "trigerItem";
	}

	public String atacarObjeto(Comando comando) {
		
		// Se puede atacar a cualquier cosa, luego se chequea si es atacable o no.
		Objeto atacado = mapa.getLugarActual().getObjeto(comando.getNombreObjeto());
		if (atacado == null)
			return Mensaje.noExisteObjeto();
		
		if(atacado.isMuerto())
			return Mensaje.yaEstaMuerto(atacado.getNombre());
		
		if(!atacado.isMatable())
			return Mensaje.noEsAtacable(atacado.getNombre());

		// Si no hay ninguna secuencia loca al atacar, es simplemente ida y vuelta de golpes.
		TriggerAtaque trigger = atacado.getTriggerAtaque();
		if (trigger != null)
			return TriggerMaster.EjecutarTriggerAtacar(mapa, trigger, atacado);
			
			String msj = "";
			mapa.getPersonajeActual().atacar(atacado);
			msj += Mensaje.atacarObjeto(mapa.getPersonajeActual(), atacado);
			
			if(atacado.isMuerto()) {
				mapa.getLugarActual().removerObjeto(atacado);
				msj += "\n";
				return msj += Mensaje.muerteObjeto(atacado);
			}
			else {
				atacado.atacar(mapa.getPersonajeActual());
				msj += "\n";
				msj += Mensaje.contraAtaqueRecibido(atacado, mapa.getPersonajeActual());
				if(mapa.getPersonajeActual().isMuerto()) {
					this.endGame = true;//Adios
					msj += "\n";
					msj += Mensaje.endGameMuerte(mapa.getPersonajeActual());
				}
			}

			return msj;
		}
}
