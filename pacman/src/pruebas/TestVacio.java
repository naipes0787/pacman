package pruebas;

import modelo.Juego;
import modelo.Vacio;
import junit.framework.TestCase;

public class TestVacio extends TestCase {
	
	private Juego unJuego;

	public void setUp(){
		unJuego= new Juego();
	}
	
	public void testHayPacman() {
		assertEquals(this.unJuego.obtenerPuntajeDelJugador(),0);
		Vacio unBloqueVacio= new Vacio();		
		unBloqueVacio.hayPacman(this.unJuego.obtenerNivel(),1,1);//Doy valores a x e y aleatorios, ya que no son necesarios.
		assertEquals(this.unJuego.obtenerPuntajeDelJugador(),0);
	}

}
