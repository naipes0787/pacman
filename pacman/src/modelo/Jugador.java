package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Jugador {
	
	private int puntaje;
	private int vidas;
	
	public Jugador(){
		this.puntaje = 0;
		this.vidas = 3;
	}
	//constructor para la recuperacion del objeto
	public Jugador(int valorVida, int valorPuntaje) {
		this.puntaje = valorPuntaje;
		this.vidas = valorVida;
	}

	public void sumarPuntaje(int puntajeASumar){
		if(puntajeASumar<0) throw new IllegalArgumentException();
		this.puntaje+=puntajeASumar;
	}
	
	public int obtenerPuntaje(){
		return this.puntaje;
	}
	
	public void perderVida(){
		this.vidas--;
	}
	
	public int obtenerVidasDisponibles(){
		return (this.vidas);
	}
	
	public void agregarVida(){
		this.vidas++;
	}

	public static Jugador recuperar(Element elemJugador) {
		int valorPuntaje = Integer.parseInt(elemJugador.getAttribute("puntaje"));
		int valorVida = Integer.parseInt(elemJugador.getAttribute("vidas"));
		Jugador unJugador = new Jugador(valorVida,valorPuntaje);
		return unJugador;	
	}

	public Element guardar(Document doc) {
		Element elemJugador = doc.createElement("Jugador");
		elemJugador.setAttribute("vidas",""+this.vidas);
		elemJugador.setAttribute("puntaje",""+this.puntaje);
        return elemJugador;
	}
	
	
}
