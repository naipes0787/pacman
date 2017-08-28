package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Transportador extends Contenido {


	public void hayPacman(Nivel unNivel, int x, int y){
		unNivel.transportarPacman(x,y);
	}

	public void serTransitado(Nivel unNivel, Fantasma unFantasma, int x, int y) throws NoTransitableException {
		unNivel.transportarFantasma(unFantasma,x,y);
	}


	public Element guardar(Document doc, int x, int y) {
		Element elemTransportador = doc.createElement("ContenidoColumna"+x+"Fila"+y);
		elemTransportador.setAttribute("tipo",""+"Transportador");
		return elemTransportador;
	}

	public static Contenido recuperar(Element elemContenido) {
		Contenido unTransportador = new Transportador();
		return unTransportador;
	}
}
