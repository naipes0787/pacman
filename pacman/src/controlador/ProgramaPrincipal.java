package controlador;

import Vista.Ventana;
import Vista.VentanaPrincipal;
import modelo.*;

public class ProgramaPrincipal {
	
	public static void main(String[] args) {
		boolean enJuego = true;
		Juego unJuego = null;
		Ventana ventanaPrincipal = new VentanaPrincipal();
		while (enJuego){
			int valor=ventanaPrincipal.continuarPartida();
			if (valor==0) unJuego=Juego.recuperarJuego("files/partida.dat");
			else if(valor==1) unJuego=new Juego();
			ControladorJuego cont = new ControladorJuego(unJuego);
	
			cont.setSuperficieDeDibujo(ventanaPrincipal);		
			cont.setIntervaloSimulacion(100);
	
			cont.comenzar();
			if (unJuego.obtenerVidasDisponibles() == 0){
				valor=ventanaPrincipal.nuevaPartida();
				if(valor==1) enJuego=false;
			}
			if (unJuego.obtenerNivelActual() > unJuego.obtenerCantidadNiveles()){
				enJuego = false;
			}
		}
		ventanaPrincipal.dispose();
	}
}