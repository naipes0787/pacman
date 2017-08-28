package pruebas;

import modelo.ArchivoFueraDeFormatoException;
import modelo.Bloque;
import modelo.Fruta;
import modelo.Laberinto;
import modelo.Punto;
import modelo.PuntoPoder;
import modelo.Vacio;
import junit.framework.TestCase;

public class TestLaberinto extends TestCase {
		
	private Laberinto miLaberinto;

	public void setUp(){
		try {
			miLaberinto = new Laberinto(1);
		} catch (ArchivoFueraDeFormatoException e) {
			e.printStackTrace();
		}
	}
		
	/*
	 * Testea que devuelva el contenido correcto segun la posicion de los
	 * elementos en la carga del Laberinto mediante el archivo nivel1.xml 
	 */
	public void testDevolverContenido(){
		assertTrue (miLaberinto.devolverContenido(1, 2) instanceof Punto);
		assertTrue (miLaberinto.devolverContenido(1, 3)instanceof PuntoPoder);
		assertTrue (miLaberinto.devolverContenido(0, 0)instanceof Bloque);
		assertTrue (miLaberinto.devolverContenido(13, 23)instanceof Vacio);
	}
	
	/*
	 * Testea que devuelva la cantidad de pastillas (punto y puntoPoder)
	 * correcta. Hay 244 pastillas en el archivo nivel1.xml
	 */
	public void testObtenerCantPastillas(){
		assertEquals (miLaberinto.obtenerCantidadPastillas(),274);
	}
	
	/*
	 * Testea que agregue la fruta correctamente
	 */
	public void testAgregarFruta(){
		miLaberinto.agregarFruta();
		assertTrue (miLaberinto.devolverContenido(14, 17) instanceof Fruta);
	}	
	
}
