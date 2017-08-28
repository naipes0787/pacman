package pruebas;


import modelo.Juego;
import modelo.JuegoGanadoException;
import junit.framework.TestCase;

public class testJuego extends TestCase{

	private Juego unJuego;
	
	public void setUp() {
		this.unJuego= new Juego();
	}
	
	public void testSeGanoNivel(){
		int i=0;
		while (i<274){
			this.unJuego.obtenerNivel().obtenerMiLaberinto().restarCantidadPastillas();
			i++;
		}
		assertTrue(this.unJuego.seGanoNivel());
	}
	
	public void testGanarJuego(){
		boolean juegoGanado =false;
		try{
			this.unJuego.pasarDeNivel();
			this.unJuego.pasarDeNivel();
			this.unJuego.pasarDeNivel();
			this.unJuego.pasarDeNivel();
		}catch (JuegoGanadoException e){
			fail("No debía arrojar la excepción aún.");
		}
		try{
			this.unJuego.pasarDeNivel();
		}catch (JuegoGanadoException e){
			juegoGanado = true;
		}
		if (juegoGanado){
			assertTrue(true);
		}else{
			assertTrue(false);
		}
	}
	
	public void testSumarPuntajeYSumarVida(){
		this.unJuego.sumarPuntajeAlJugador(10000);
		assertEquals(this.unJuego.obtenerPuntajeDelJugador(),10000);
		assertEquals(this.unJuego.obtenerVidasDisponibles(),4);
	}
			
}
