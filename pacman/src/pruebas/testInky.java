package pruebas;

import modelo.ArchivoFueraDeFormatoException;
import modelo.Inky;
import modelo.Juego;
import modelo.Nivel;
import modelo.Point;
import junit.framework.TestCase;


public class testInky extends TestCase{

	private Inky inky;
	private Nivel unNivel;

	
	public void setUp(){
		this.inky=new Inky(1,1);	
		Juego unJuego=new Juego();
		try{
			this.unNivel =new Nivel(unJuego,unJuego.obtenerNivelActual());
		} catch (ArchivoFueraDeFormatoException e) {
			System.out.println("Formato incorrecto en laberinto correspondiente al nivel");
		}
	}
	
	public void testMoverFantasma(){
		Point unPoint=this.inky.obtenerPosicion();
		assertEquals(unPoint.getX(),14);
		assertEquals(unPoint.getY(),14);
		this.inky.elegirMovimiento(unNivel);
		unPoint=this.inky.obtenerPosicion();
		assertEquals(unPoint.getX(),14);
		assertEquals(unPoint.getY(),15);
		this.inky.elegirMovimiento(unNivel);
		unPoint=this.inky.obtenerPosicion();
		assertEquals(unPoint.getX(),15);
		assertEquals(unPoint.getY(),15);
		this.inky.elegirMovimiento(unNivel);
		unPoint=this.inky.obtenerPosicion();
		assertEquals(unPoint.getX(),16);
		assertEquals(unPoint.getY(),15);
		this.inky.elegirMovimiento(unNivel);
		unPoint=this.inky.obtenerPosicion();
		assertEquals(unPoint.getX(),16);
		assertEquals(unPoint.getY(),14);
	}
	
	public void testComestible(){
		assertFalse(this.inky.esComestible());
		this.inky.hacerseComestible();
		assertTrue(this.inky.esComestible());
		this.inky.elegirMovimiento(unNivel);
		Point unPoint=this.inky.obtenerPosicion();
		assertEquals(unPoint.getX(),14);
		assertEquals(unPoint.getY(),15);
		this.inky.elegirMovimiento(unNivel);
		this.inky.elegirMovimiento(unNivel);
		unPoint=this.inky.obtenerPosicion();
		assertEquals(unPoint.getX(),16);
		assertEquals(unPoint.getY(),15);
		this.inky.elegirMovimiento(unNivel);
		unPoint=this.inky.obtenerPosicion();
		assertEquals(unPoint.getX(),16);
		assertEquals(unPoint.getY(),14);
	}
	
	public void testMoverDerecha(){
		this.inky.elegirMovimiento(unNivel);
		this.inky.irAJaula();
		Point unPoint=this.inky.obtenerPosicion();
		assertEquals(unPoint.getX(),14);
		assertEquals(unPoint.getY(),14);
	}
	
	public void testSalirDeJaula(){
		assertTrue(this.inky.estaEncerrado());
		this.inky.salirDeJaula();
		assertFalse(this.inky.estaEncerrado());
		Point unPoint=this.inky.obtenerPosicion();
		assertEquals(unPoint.getX(),14);
		assertEquals(unPoint.getY(),11);		
	}
	
}