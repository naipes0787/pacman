package pruebas;

import modelo.ArchivoFueraDeFormatoException;
import modelo.Blinky;
import modelo.Juego;
import modelo.Nivel;
import modelo.Point;
import junit.framework.TestCase;
public class testBlinky extends TestCase{

	private Blinky blinky;
	private Nivel unNivel;

	
	public void setUp(){
		this.blinky=new Blinky(1,1);	
		Juego unJuego=new Juego();
		try{
			this.unNivel =new Nivel(unJuego,unJuego.obtenerNivelActual());
		} catch (ArchivoFueraDeFormatoException e) {
			System.out.println("Formato incorrecto en laberinto correspondiente al nivel");
		}
	}
	
	public void testMoverIzquierda(){
		Point unPoint=this.blinky.obtenerPosicion();
		assertEquals(unPoint.getX(),13);
		assertEquals(unPoint.getY(),13);
		this.blinky.elegirMovimiento(unNivel);
		unPoint=this.blinky.obtenerPosicion();
		assertEquals(unPoint.getX(),12);
		assertEquals(unPoint.getY(),13);
		this.blinky.elegirMovimiento(unNivel);
		this.blinky.elegirMovimiento(unNivel);
		unPoint=this.blinky.obtenerPosicion();
		assertEquals(unPoint.getX(),11);
		assertEquals(unPoint.getY(),14);
		this.blinky.elegirMovimiento(unNivel);
		unPoint=this.blinky.obtenerPosicion();
		assertEquals(unPoint.getX(),11);
		assertEquals(unPoint.getY(),15);
	}
	
	public void testComestible(){
		assertFalse(this.blinky.esComestible());
		this.blinky.hacerseComestible();
		assertTrue(this.blinky.esComestible());
		this.blinky.elegirMovimiento(unNivel);
		Point unPoint=this.blinky.obtenerPosicion();
		assertEquals(unPoint.getX(),14);
		assertEquals(unPoint.getY(),13);
		this.blinky.elegirMovimiento(unNivel);
		this.blinky.elegirMovimiento(unNivel);
		this.blinky.elegirMovimiento(unNivel);
		unPoint=this.blinky.obtenerPosicion();
		assertEquals(unPoint.getX(),15);
		assertEquals(unPoint.getY(),13);
		this.blinky.elegirMovimiento(unNivel);
		this.blinky.elegirMovimiento(unNivel);
		unPoint=this.blinky.obtenerPosicion();
		assertEquals(unPoint.getX(),15);
		assertEquals(unPoint.getY(),13);
	}
	
	public void testIrAJaula(){
		this.blinky.elegirMovimiento(unNivel);
		this.blinky.irAJaula();
		Point unPoint=this.blinky.obtenerPosicion();
		assertEquals(unPoint.getX(),13);
		assertEquals(unPoint.getY(),13);
	}
	
	public void testSalirDeJaula(){
		assertTrue(this.blinky.estaEncerrado());
		this.blinky.salirDeJaula();
		assertFalse(this.blinky.estaEncerrado());
		Point unPoint=this.blinky.obtenerPosicion();
		assertEquals(unPoint.getX(),13);
		assertEquals(unPoint.getY(),11);		
	}
}
