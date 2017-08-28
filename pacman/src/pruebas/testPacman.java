package pruebas;

import modelo.ArchivoFueraDeFormatoException;
import modelo.Juego;
import modelo.Nivel;
import modelo.Pacman;
import modelo.Point;
import junit.framework.TestCase;

public class testPacman extends TestCase{

	private Pacman unPacman;
	private Nivel unNivel;
	
	public void setUp(){
		try {
			this.unNivel=new Nivel(new Juego(),1);
			assertTrue(true);
		} catch (ArchivoFueraDeFormatoException e) {
			fail("No devió devolver error.");
		}
		unPacman=new Pacman();
		Point unPoint;
		unPoint=unPacman.obtenerPosicion();
		assertEquals(unPoint.getX(),13);
		assertEquals(unPoint.getY(),23);
	}

	public void testNuevaPosicion(){
		unPacman.renacer();
		Point unPoint;
		unPoint=unPacman.obtenerPosicion();
		assertEquals(unPoint.getX(),13);
		assertEquals(unPoint.getY(),23);
		assertSame(unPacman.obtenerPosicion(),unPoint);
	}

	//Los métodos irIzquierda, irArriba e irAbajo son similares.
	public void testIrDerecha(){
		unNivel.obtenerPacman().nuevoSentido(1,0);
		unNivel.comenzarMoverPacman();
		Point unPoint=unNivel.obtenerPacman().obtenerPosicion();
		assertEquals(unPoint.getX(),14);
		assertEquals(unPoint.getY(),23);
	}
	
	public void testIrIzquierdaHabiendoBloque(){
		Point unPoint=unNivel.obtenerPacman().obtenerPosicion();
		assertEquals(unPoint.getX(),13);
		assertEquals(unPoint.getY(),23);
		unNivel.obtenerPacman().nuevoSentido(-1,0);
		unNivel.comenzarMoverPacman();
		//Se debe quedar en la misma posición porque a la izquierda hay un bloque.
		assertEquals(unPoint.getX(),13);
		assertEquals(unPoint.getY(),23);
	}

}
