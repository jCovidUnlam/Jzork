package zorkPackage;

public class GameMaster {
	private Mapa mapa;
	private Personaje personaje;
	
	public GameMaster(Mapa mapa, Personaje personaje) {
		super();
		this.mapa = mapa;
		this.personaje = personaje;
		Consola.mostrar(mapa.lugarActual());
	}
	
	public void ejecutar(Comando comando) {
		
		switch(comando.nombre.toLowerCase()) {
		case "norte":
			Consola.mostrar(mapa.moverNorte());
			break;
		case "sur":
			Consola.mostrar(mapa.moverSur());
			break;
		case "este":
			Consola.mostrar(mapa.moverEste());
			break;
		case "oeste":
			Consola.mostrar(mapa.moverOeste());
			break;
		case "mirar alrededor":
			Consola.mostrar(mapa.lugarActual());
			break;
		case "tomar":
			Objeto obj = mapa.tomarObjeto(comando.nombreObjeto);
			if(obj == null) {
				Consola.mostrar(Mensaje.noExisteObjeto());
				break;
			}
			
			if(!obj.isTomable()) {
				Consola.mostrar(Mensaje.noTomable(obj));
				break;
			}
			
			personaje.addObjeto(obj);
			Consola.mostrar(Mensaje.tomarObjeto(obj));
			break;
			
		default:
			Consola.mostrarComandoErroneo();
			break;
		}
	}
	
}
