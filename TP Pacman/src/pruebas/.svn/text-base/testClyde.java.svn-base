package pruebas;

import modelo.ArchivoFueraDeFormatoException;
import modelo.Clyde;
import modelo.Juego;
import modelo.Nivel;
import modelo.Point;
import junit.framework.TestCase;
public class testClyde extends TestCase{

	private Clyde clyde;
	private Nivel unNivel;
		
	public void setUp(){
		this.clyde=new Clyde(1,1);	
		Juego unJuego=new Juego();
		try{
			this.unNivel =new Nivel(unJuego,unJuego.obtenerNivelActual());
		} catch (ArchivoFueraDeFormatoException e) {
			System.out.println("Formato incorrecto en laberinto correspondiente al nivel");
		}
	}
	
	public void testMoverDerecha(){
		Point unPoint=this.clyde.obtenerPosicion();
		assertEquals(unPoint.getX(),14);
		assertEquals(unPoint.getY(),13);
		this.clyde.elegirMovimiento(unNivel);
		unPoint=this.clyde.obtenerPosicion();
		assertEquals(unPoint.getX(),13);
		assertEquals(unPoint.getY(),13);
		this.clyde.elegirMovimiento(unNivel);
		this.clyde.elegirMovimiento(unNivel);
		unPoint=this.clyde.obtenerPosicion();
		assertEquals(unPoint.getX(),11);
		assertEquals(unPoint.getY(),13);
		this.clyde.elegirMovimiento(unNivel);
		unPoint=this.clyde.obtenerPosicion();
		assertEquals(unPoint.getX(),11);
		assertEquals(unPoint.getY(),14);
		this.clyde.elegirMovimiento(unNivel);
		unPoint=this.clyde.obtenerPosicion();
		assertEquals(unPoint.getX(),11);
		assertEquals(unPoint.getY(),15);
	}
	
	public void testComestible(){
		assertFalse(this.clyde.esComestible());
		this.clyde.hacerseComestible();
		assertTrue(this.clyde.esComestible());
		this.clyde.elegirMovimiento(unNivel);
		Point unPoint=this.clyde.obtenerPosicion();
		assertEquals(unPoint.getX(),15);
		assertEquals(unPoint.getY(),13);
		this.clyde.elegirMovimiento(unNivel);
		this.clyde.elegirMovimiento(unNivel);
		this.clyde.elegirMovimiento(unNivel);
		unPoint=this.clyde.obtenerPosicion();
		assertEquals(unPoint.getX(),16);
		assertEquals(unPoint.getY(),13);
	}
		
	public void testIrAJaula(){
		this.clyde.elegirMovimiento(unNivel);
		this.clyde.irAJaula();
		Point unPoint=this.clyde.obtenerPosicion();
		assertEquals(unPoint.getX(),14);
		assertEquals(unPoint.getY(),13);
	}
	
	public void testSalirDeJaula(){
		assertTrue(this.clyde.estaEncerrado());
		this.clyde.salirDeJaula();
		assertFalse(this.clyde.estaEncerrado());
		Point unPoint=this.clyde.obtenerPosicion();
		assertEquals(unPoint.getX(),13);
		assertEquals(unPoint.getY(),11);		
	}
}