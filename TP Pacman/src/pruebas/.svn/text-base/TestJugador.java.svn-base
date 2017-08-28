package pruebas;

import modelo.Jugador;
import junit.framework.TestCase;


public class TestJugador extends TestCase {

	private Jugador unJugador;
	
	public void setUp(){
		this.unJugador= new Jugador();		
	}
	
	public void testObtenerPuntaje(){
		assertEquals(this.unJugador.obtenerPuntaje(),0);		
	}
	
	public void testSumarPuntajeUno(){
		try{
			unJugador.sumarPuntaje(10);
		}
		catch(IllegalArgumentException e){
			fail("Lanzó excepcion al intentar sumar puntaje con valor válido.");
		}
		assertEquals(unJugador.obtenerPuntaje(),10);
	}
	
	public void testSumarPuntajeDos(){
		try{
			unJugador.sumarPuntaje(-1);
			fail("Permitió sumar puntaje con un valor inválido.");
		}
		catch(IllegalArgumentException e){
			assertTrue(true);
		}
	}
	
	public void testPerderVida(){
		this.unJugador.perderVida();
		assertEquals(unJugador.obtenerVidasDisponibles(),2);
	
	}

	public void testAgregarVida(){
		this.unJugador.agregarVida();
		assertEquals(unJugador.obtenerVidasDisponibles(),4);
	}
	
}
