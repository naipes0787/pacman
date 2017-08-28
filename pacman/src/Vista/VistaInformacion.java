package Vista;

import java.awt.*;
import modelo.Nivel;

public class VistaInformacion implements Dibujable{

	private Nivel miNivel;
	private int contador;
	

	public VistaInformacion(Nivel unNivel){
		this.miNivel = unNivel;
		this.contador = 0;
	}
	
	public void dibujar(SuperficieDeDibujo superfice) {
			Graphics grafico = ((Ventana)superfice).getGrafico();
			Graphics grafico2 = ((Ventana)superfice).getGrafico();
			Color miColor = new Color(500);
			grafico.setColor(miColor);
			grafico2.setColor(Color.white);
			grafico.setFont(new Font("fuente",  Font.BOLD, 20));
			int vidas=this.miNivel.obtenerMiJuego().obtenerVidasDisponibles();
			grafico.drawString("VIDAS: "+ vidas, 70, 640);
			grafico.drawString("NIVEL: "+ this.miNivel.obtenerMiJuego().obtenerNivelActual(), 220, 640);
			grafico.drawString("PUNTAJE: "+ this.miNivel.obtenerMiJuego().obtenerPuntajeDelJugador(), 370, 640);
			if (this.miNivel.recienEmpieza()){
				grafico2.setFont(new Font("fuente",  Font.BOLD, 40));
				grafico2.drawString("NIVEL "+this.miNivel.obtenerMiJuego().obtenerNivelActual(), 70, 80);
				this.contador++;
				if (this.contador == 20){
					this.miNivel.yaEmpezo();
					this.contador = 0;
				}
			}
			if(vidas==0) {
				grafico2.setFont(new Font("fuente",  Font.BOLD, 40));
				grafico2.drawString("JUEGO PERDIDO", 127, 160);
			}
			else if(this.miNivel.obtenerMiJuego().seGanoJuego()){
					grafico2.setFont(new Font("fuente",  Font.BOLD, 40));
					grafico2.drawString("JUEGO GANADO", 127, 160);
			}
			else if(this.miNivel.obtenerMiJuego().archivoErroneo()){
				grafico.setFont(new Font("fuente",  Font.BOLD, 30));
				grafico.drawString("Archivo de Nivel fuera de formato", 47, 360);
		}
	}

	public Posicionable getPosicionable() {
		return null;
	}

	public void setPosicionable(Posicionable posicionable) {
	}		
}
