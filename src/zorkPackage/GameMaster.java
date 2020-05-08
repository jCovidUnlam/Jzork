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
		
		switch(comando.nombre.toLowerCase()) {
		case "norte":
			Consola.mostrar(aventura.getMapa().moverNorte());
			break;
		case "sur":
			Consola.mostrar(aventura.getMapa().moverSur());
			break;
		case "este":
			Consola.mostrar(aventura.getMapa().moverEste());
			break;
		case "oeste":
			Consola.mostrar(aventura.getMapa().moverOeste());
			break;
		case "mirar alrededor":
			Consola.mostrar(aventura.getMapa().lugarActual());
			break;
		case "tomar":
			Objeto obj = aventura.getMapa().tomarObjeto(comando.nombreObjeto);
			if(obj == null) {
				Consola.mostrar(Mensaje.noExisteObjeto());
				break;
			}
			
			if(!obj.isTomable()) {
				Consola.mostrar(Mensaje.noTomable(obj));
				break;
			}
			
			aventura.getPersonaje().addObjeto(obj);
			Consola.mostrar(Mensaje.tomarObjeto(obj));
			break;
			
		default:
			Consola.mostrarComandoErroneo();
			break;
		}
	}
	
}
