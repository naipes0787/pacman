package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Vacio extends Contenido {

	public Vacio(){
		
	}
	//El método queda así para poder ser un Contenido sin ser una clase abstracta
	public void hayPacman(Nivel unNivel,int x,int y) {
				
	}

	public void serTransitado(Nivel unNivel, Fantasma unFantasma, int x, int y) {
		
	}
	
	public Element guardar(Document doc, int x, int y) {
		Element elemVacio = doc.createElement("ContenidoColumna"+x+"Fila"+y);
		elemVacio.setAttribute("tipo",""+"Vacio");
		return elemVacio;
	}
	
	public static Contenido recuperar(Element elemContenido) {
		Contenido unVacio = new Vacio();
		return unVacio;
	}
	
}
