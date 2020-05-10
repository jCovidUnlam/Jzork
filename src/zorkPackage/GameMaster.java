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
		
		switch(comando.getTipo()) {
		case Mover:
			moverPersonaje(comando);
			break;
		case Adquirir:
			adquirirItem(comando);
			break;
		case Usuario:
			ejecutarComandoUsuario(comando);
			break;
		case Invalido:
		default:
			Consola.mostrarComandoErroneo();
			break;
		}
		
		
		/*case "mirar alrededor":
			Consola.mostrar(aventura.getMapa().lugarActual());
			break;
		case "tomar":
			Objeto obj = aventura.getMapa().tomarObjeto(comando.getNombreObjeto());
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
		}*/
	}
	
	private void moverPersonaje(Comando comando) {
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
		{
			Consola.mostrar(Mensaje.fueraLimite());
			return;
		}
		
		//No me cambien un nombre de las clases porque esto no funca
		switch(obj.getClass().getSimpleName()) {
		case "Lugar":
			Consola.mostrar(Mensaje.mensajeLugar((Lugar)obj));
			break;
		case "Obstaculo":
			Consola.mostrar(Mensaje.existeObstaculo((Obstaculo)obj));
			break;
			//Esto es por si tengo que devolver algo raro
		case "String":
			Consola.mostrar((String)obj);
		break;
		}
	}
	
	private void adquirirItem(Comando comando) {
		//Lo busca en el lugar
		Item item = aventura.getMapa().tomarItem(comando.getNombreObjeto());
		
		//Si es nulo, muetra que no existe
		if(item == null)
			Consola.mostrar(Mensaje.noExisteObjeto());
		
		//Sino es tomable, le manda una descripcion de porque no lo es
		if(item.isTomable() == false)
			Consola.mostrar(item.getDescNoTomable());
		else {
			//Remueve el atributo descripcionMapa del item en el Lugar.
			aventura.getMapa().cambiarDescripcionLugarActual(item);
			//Si es tomable, lo agrega al inventario
			aventura.getPersonaje().addObjeto(item);
			//Finalmente, muetra mensaje de que lo tomo.
			Consola.mostrar(item.getDescTomable());
		}
	}
	
	private void ejecutarComandoUsuario(Comando comando)
	{
		switch (comando.getNombre()) {
		case "inventario":
			Consola.mostrar(Mensaje.inventario(aventura.getPersonaje().getInventario(), aventura.getPersonaje().getNombre()));
			break;

		default:
			break;
		}
	}
	
}
