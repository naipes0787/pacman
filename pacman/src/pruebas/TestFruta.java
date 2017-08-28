package pruebas;

import modelo.Fruta;
import modelo.Juego;

import junit.framework.TestCase;


public class TestFruta extends TestCase {

	private Juego unJuego;


	public void setUp(){
		unJuego= new Juego();
	}
	
	public void testHayPacman() {
		assertEquals(this.unJuego.obtenerPuntajeDelJugador(),0);
		Fruta unaFruta= new Fruta();		
		unaFruta.hayPacman(this.unJuego.obtenerNivel(),1,1);//Doy valores a x e y aleatorios, ya que no son necesarios.
		assertEquals(this.unJuego.obtenerPuntajeDelJugador(),100);
	}
	
}
