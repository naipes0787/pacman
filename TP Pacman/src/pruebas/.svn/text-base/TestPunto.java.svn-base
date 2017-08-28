package pruebas;

import modelo.Juego;
import modelo.Punto;
import junit.framework.TestCase;


public class TestPunto extends TestCase {

	private Juego unJuego;


	public void setUp(){
		unJuego= new Juego();
	}
	
	public void testHayPacman1() {
		assertEquals(this.unJuego.obtenerPuntajeDelJugador(),0);
		Punto unPunto= new Punto();		
		unPunto.hayPacman(this.unJuego.obtenerNivel(),1,1);//Doy valores a x e y aleatorios, ya que no son necesarios.
		assertEquals(this.unJuego.obtenerPuntajeDelJugador(),10);
	}
	
}

