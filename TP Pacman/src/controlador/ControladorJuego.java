package controlador;

import java.util.*;

import Vista.*;
import modelo.*;

public class ControladorJuego {
	
	private List dibujables;
	private Juego miJuego;
	private boolean estaEnEjecucion;
	private long intervaloSimulacion;
	private Ventana superficieDeDibujo;
	
	
	public ControladorJuego(Juego unJuego){
		this.dibujables = new ArrayList();
		this.miJuego = unJuego;
	}
	
	
	public void comenzar(){
		estaEnEjecucion = true;
		boolean juegoGanado = false;
		while (!juegoGanado){
			try{
				this.generarEscenario();
				while(estaEnEjecucion){
					miJuego.empezarAJugar();
					this.dibujar();					
					Thread.sleep(intervaloSimulacion);
					if (this.miJuego.obtenerVidasDisponibles() == 0){
						this.dibujar();
						return;
					}
				}
			}catch (NivelGanadoException e) {
				this.dibujar();
				try {
					miJuego.pasarDeNivel();
				} catch (JuegoGanadoException e1){ 
					this.dibujar();
					return;
				}
			}catch (InterruptedException e) {
					e.printStackTrace();
			} catch (ArchivoFueraDeFormatoException e) {
				this.dibujar();
				return;
			}
		}	
	}

	
	public void generarEscenario(){
		Mesa fondo=new Mesa(565,650);
		VistaFondo vistaFondo=new VistaFondo(fondo);
		vistaFondo.setPosicionable(fondo);
		
		VistaLaberinto vistaLaberinto=new VistaLaberinto(miJuego.obtenerNivel().obtenerMiLaberinto());
		VistaFantasma vistaPinky=new VistaFantasma(miJuego.obtenerNivel().obtenerPinky(),"pinky.jpg");
		vistaPinky.setPosicionable(miJuego.obtenerNivel().obtenerPinky());

		VistaFantasma vistaBlinky=new VistaFantasma(miJuego.obtenerNivel().obtenerBlinky(),"blinky.jpg");
		vistaBlinky.setPosicionable(miJuego.obtenerNivel().obtenerBlinky());
		
		VistaFantasma vistaClyde=new VistaFantasma(miJuego.obtenerNivel().obtenerClyde(),"clyde.jpg");
		vistaClyde.setPosicionable(miJuego.obtenerNivel().obtenerClyde());
		
		VistaFantasma vistaInky=new VistaFantasma(miJuego.obtenerNivel().obtenerInky(),"inky.jpg");
		vistaInky.setPosicionable(miJuego.obtenerNivel().obtenerInky());
		
		VistaPacman vistaPacman=new VistaPacman(miJuego.obtenerNivel().obtenerPacman());		
		vistaPacman.setPosicionable(miJuego.obtenerNivel().obtenerPacman());
		vistaPacman.setMovible(miJuego.obtenerNivel().obtenerPacman());
		
		KeyboardDireccion direccionamiento=miJuego.obtenerNivel().obtenerPacman();
		KeyboardController teclado= new KeyboardController(direccionamiento);
		this.superficieDeDibujo.addKeyboard(teclado);
		
		VistaInformacion informacion = new VistaInformacion(miJuego.obtenerNivel());
		
		this.agregarDibujable(vistaFondo);
		this.agregarDibujable(vistaLaberinto);
		this.agregarDibujable(vistaPacman);
		this.agregarDibujable(vistaPinky);
		this.agregarDibujable(vistaClyde);
		this.agregarDibujable(vistaBlinky);
		this.agregarDibujable(vistaInky);
		this.agregarDibujable(informacion);
	}
	
	
	public void agregarDibujable(Dibujable unDibujable){
		dibujables.add(unDibujable);
	}
	
	public void removerDibujable(Dibujable unDibujable){
		dibujables.remove(unDibujable);
	}
	
	public long getIntervaloSimulacion() {
		return intervaloSimulacion;
	}

	public void setIntervaloSimulacion(long intervaloSimulacion) {
		this.intervaloSimulacion = intervaloSimulacion;
	}


	private void dibujar() {
		this.superficieDeDibujo.limpiar();
		Iterator iterador = dibujables.iterator();
		while(iterador.hasNext()){
			Dibujable dibujable = (Dibujable)iterador.next();
			dibujable.dibujar(this.superficieDeDibujo);
		}		
		this.superficieDeDibujo.actualizar();
	}
	

	public SuperficieDeDibujo obtenerSuperficieDeDibujo() {
		return superficieDeDibujo;
	}

	public void setSuperficieDeDibujo(Ventana superficieDeDibujo) {
		this.superficieDeDibujo = superficieDeDibujo;
		this.superficieDeDibujo.addWindowListeners(miJuego);
	}
	
}
