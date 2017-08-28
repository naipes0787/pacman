package pruebas;

import modelo.NoTransitableException;
import modelo.Juego;
import junit.framework.TestCase;
import modelo.Blinky;
import modelo.Pacman;


public class testTransportador extends TestCase{
	
	private Juego unJuego;
	
	public void setUp(){
		unJuego= new Juego();
	}
	
	public void testHayPacman(){
		Pacman pacman = this.unJuego.obtenerNivel().obtenerPacman();
		assertEquals(pacman.obtenerPosicion().getX(),13);
		assertEquals(pacman.obtenerPosicion().getY(),23);
		try{
			this.unJuego.obtenerNivel().obtenerMiLaberinto().devolverContenido(0,8).hayPacman(this.unJuego.obtenerNivel(),0,8);
			//Se debería teletransportar de izquierda a derecha.
		}catch (NoTransitableException e) {
			fail("No debería lanzar excepción ya que el transportador es transitable.");
		}
		assertEquals(pacman.obtenerPosicion().getX(),27);
		assertEquals(pacman.obtenerPosicion().getY(),8);
	}
	
	public void testSerTransitado(){
		Blinky blinky = this.unJuego.obtenerNivel().obtenerBlinky();
		assertEquals(blinky.obtenerPosicion().getX(),13);
		assertEquals(blinky.obtenerPosicion().getY(),13);
		try{
			this.unJuego.obtenerNivel().obtenerMiLaberinto().devolverContenido(0,8).serTransitado(this.unJuego.obtenerNivel(),blinky,0,8);
			//Se debería teletransportar de izquierda a derecha.
		}catch (NoTransitableException e) {
			fail("No debería lanzar excepción ya que el transportador es transitable.");
		}
		assertEquals(blinky.obtenerPosicion().getX(),26);
		assertEquals(blinky.obtenerPosicion().getY(),8);
	}
}
