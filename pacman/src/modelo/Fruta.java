package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Fruta extends Comestible {

	public Fruta (){
		this.puntaje = 100;
	}
	//constructor para persistencia
	public Fruta(int puntaje) {
		this.puntaje =puntaje;
	}

	public void hayPacman(Nivel unNivel,int x,int y) {
		unNivel.obtenerMiJuego().sumarPuntajeAlJugador(this.puntaje);
		unNivel.obtenerMiLaberinto().agregarVacio(x,y);
	}
	
	public void serTransitado(Nivel unNivel, Fantasma unFantasma, int x, int y){
		
	}

	public Element guardar(Document doc, int x, int y) {
		Element elemFruta = doc.createElement("ContenidoColumna"+x+"Fila"+y);
		elemFruta.setAttribute("puntaje",""+this.puntaje);
		elemFruta.setAttribute("tipo",""+"Fruta");
		return elemFruta;
	}	

	public static Contenido recuperar(Element elemContenido) {
		int puntaje= Integer.parseInt(elemContenido.getAttribute("puntaje"));
		Contenido unFruta = new Fruta(puntaje);
		return unFruta;
	}

}