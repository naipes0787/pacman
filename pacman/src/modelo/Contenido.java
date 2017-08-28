package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public abstract class Contenido {
		
	//Este método se usa cuando el pacman está parado en el casillero del contenido en cuestión.
	abstract public void hayPacman(Nivel unNivel,int x,int y) throws NoTransitableException;
	
	//Este método se usa cuando un fantasma está parado en el casillero del contenido en cuestión.
	abstract public void serTransitado(Nivel unNivel, Fantasma unFantasma, int x, int y) throws NoTransitableException;

	abstract public Element guardar(Document doc, int x, int y);

	public static Contenido recuperar(Element elemContenido) {
		return null;
	}

}
