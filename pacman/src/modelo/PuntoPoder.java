package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PuntoPoder extends Comestible {

	public PuntoPoder (){
		this.puntaje = 50;
	}
	
	//Para el constructor por persistencia
	public PuntoPoder(int puntaje) {
		this.puntaje=puntaje;
	}
	
	public void hayPacman(Nivel unNivel,int x,int y) {
		unNivel.obtenerMiJuego().sumarPuntajeAlJugador(this.puntaje);
		this.hacerFantasmasComestibles(unNivel);
		unNivel.obtenerMiLaberinto().restarCantidadPastillas();
		unNivel.obtenerMiLaberinto().agregarVacio(x,y);
	}

	public void serTransitado(Nivel unNivel, Fantasma unFantasma, int x, int y){		
	}	

	public void hacerFantasmasComestibles(Nivel unNivel){
		unNivel.obtenerBlinky().hacerseComestible();
		unNivel.obtenerPinky().hacerseComestible();
		unNivel.obtenerInky().hacerseComestible();
		unNivel.obtenerClyde().hacerseComestible();
		int segundos = (7 - unNivel.obtenerNumeroNivel());
		if (segundos > 1){
			new TimerComestible (segundos, unNivel);
		}else{
			new TimerComestible (2, unNivel);
		}
	}
	
	public Element guardar(Document doc, int x, int y) {
		Element elemPuntoPoder = doc.createElement("ContenidoColumna"+x+"Fila"+y);
		elemPuntoPoder.setAttribute("puntaje",""+this.puntaje);
		elemPuntoPoder.setAttribute("tipo",""+"PuntoPoder");
		return elemPuntoPoder;
	}	

	public static Contenido recuperar(Element elemContenido) {
		int puntaje= Integer.parseInt(elemContenido.getAttribute("puntaje"));
		Contenido unPuntoPoder = new PuntoPoder(puntaje);
		return unPuntoPoder;
	}
	
}