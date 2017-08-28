package modelo;

import java.util.Timer;
import java.util.TimerTask;

public class TimerFruta {
	
	private Timer timer;
	private Nivel miNivel;
	private long periodo = 5000;

	public TimerFruta ( int segundos, Nivel miNivel ){
		this.timer = new Timer ( ) ;
		this.miNivel = miNivel;
		this.timer.schedule( new RemindTask ( ) , segundos*1000, periodo);
	}

	private void aparecerFruta(){
			this.miNivel.obtenerMiLaberinto().agregarFruta();
	}

	//Al pasar el tiempo que se pasó por parámetro se accede al método aparecerFruta.
	class RemindTask extends TimerTask {
		public void run ( ) {
			aparecerFruta();
			timer.cancel ( ) ; //Cancela el hilo del timer
		}
	}
}
