package pruebas;

import modelo.ArchivoFueraDeFormatoException;
import modelo.Juego;
import modelo.Nivel;
import modelo.Pinky;
import modelo.Point;
import junit.framework.TestCase;

public class testPinky extends TestCase{

	private Pinky pinky;
	private Nivel unNivel;

	
	public void setUp(){
		this.pinky=new Pinky(1,1);	
		Juego unJuego=new Juego();
		try{
			this.unNivel =new Nivel(unJuego,unJuego.obtenerNivelActual());
		} catch (ArchivoFueraDeFormatoException e) {
			System.out.println("Formato incorrecto en laberinto correspondiente al nivel");
		}
	}
	
	public void testMoverPorRandom(){
		Point unPoint=this.pinky.obtenerPosicion();
		assertEquals(unPoint.getX(),13);
		assertEquals(unPoint.getY(),14);
		this.pinky.elegirMovimiento(unNivel);
		unPoint=this.pinky.obtenerPosicion();
		assertTrue((unPoint.getX()==13)|(unPoint.getY()==14));
	}
	
	public void testComestible(){
		assertFalse(this.pinky.esComestible());
		this.pinky.hacerseComestible();
		assertTrue(this.pinky.esComestible());
		this.pinky.elegirMovimiento(unNivel);
		Point unPoint=this.pinky.obtenerPosicion();
		assertEquals(unPoint.getX(),12);
		assertEquals(unPoint.getY(),14);
		this.pinky.elegirMovimiento(unNivel);
		this.pinky.elegirMovimiento(unNivel);
		unPoint=this.pinky.obtenerPosicion();
		assertEquals(unPoint.getX(),11);
		assertEquals(unPoint.getY(),15);
	}
		
	public void testIrAJaula(){
		this.pinky.elegirMovimiento(unNivel);
		this.pinky.irAJaula();
		Point unPoint=this.pinky.obtenerPosicion();
		assertEquals(unPoint.getX(),13);
		assertEquals(unPoint.getY(),14);
	}
	
	public void testSalirDeJaula(){
		assertTrue(this.pinky.estaEncerrado());
		this.pinky.salirDeJaula();
		assertFalse(this.pinky.estaEncerrado());
		Point unPoint=this.pinky.obtenerPosicion();
		assertEquals(unPoint.getX(),13);
		assertEquals(unPoint.getY(),11);		
	}
}