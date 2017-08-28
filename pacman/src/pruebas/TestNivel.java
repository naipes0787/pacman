package pruebas;


import modelo.*;




import junit.framework.TestCase;


public class TestNivel extends TestCase{
	
	private Juego unJuego;
	private Nivel unNivel;
	
	public void setUp() {
		this.unJuego= new Juego();
		this.unNivel = this.unJuego.obtenerNivel();
	}
	
	public void testTransportarPacman(){
		this.unNivel.transportarPacman(0, 8);
		assertEquals(this.unNivel.obtenerPacman().obtenerPosicion().getX(),27);
		assertEquals(this.unNivel.obtenerPacman().obtenerPosicion().getY(),8);
	}
	
	public void testTransportarFantasma(){
		this.unNivel.transportarFantasma(this.unNivel.obtenerBlinky(),0,8);
		assertEquals(this.unNivel.obtenerBlinky().obtenerPosicion().getX(),26);
		assertEquals(this.unNivel.obtenerBlinky().obtenerPosicion().getY(),8);
	}
	
	public void pacmanFueAtrapado(){
		this.unNivel.pacmanFueAtrapado();
		assertEquals(this.unJuego.obtenerVidasDisponibles(),2);
	}
	
}
