package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Punto extends Comestible {

	public Punto (){
		this.puntaje = 10;
	}
	//constructor para persistencia
	public Punto(int puntaje) {
		this.puntaje = puntaje;
	}

	public void hayPacman(Nivel unNivel,int x,int y) {
		unNivel.obtenerMiJuego().sumarPuntajeAlJugador(this.puntaje);
		unNivel.obtenerMiLaberinto().restarCantidadPastillas();
		unNivel.obtenerMiLaberinto().agregarVacio(x,y);
	}
	
	public Element guardar(Document doc, int x, int y) {
		Element elemPunto = doc.createElement("ContenidoColumna"+x+"Fila"+y);
		elemPunto.setAttribute("puntaje",""+this.puntaje);
		elemPunto.setAttribute("tipo",""+"Punto");
		return elemPunto;
	}	

	public static Contenido recuperar(Element elemContenido) {
		int puntaje= Integer.parseInt(elemContenido.getAttribute("puntaje"));
		Contenido unPunto = new Punto(puntaje);
		return unPunto;
	}

	public void serTransitado(Nivel unNivel, Fantasma unFantasma, int x, int y){
		
	}

}
