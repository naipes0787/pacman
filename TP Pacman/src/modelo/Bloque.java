package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Bloque extends Contenido {

	public Bloque(){	
	}

	public void hayPacman(Nivel unNivel,int x,int y) throws NoTransitableException{
		throw new NoTransitableException();
	}

	public void serTransitado(Nivel unNivel, Fantasma unFantasma, int x, int y) throws NoTransitableException {
		throw new NoTransitableException();		
	}

	public Element guardar(Document doc, int x, int y) {
		Element elemBloque = doc.createElement("ContenidoColumna"+x+"Fila"+y);
		elemBloque.setAttribute("tipo",""+"Bloque");
		return elemBloque;
	}

	public static Contenido recuperar(Element elemBloque) {
		Contenido unBloque = new Bloque();
		return unBloque;
	}

}
