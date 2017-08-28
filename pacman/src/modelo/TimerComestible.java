package modelo;
	import java.util.Timer;
import java.util.TimerTask;

	public class TimerComestible {
		
		Timer timer;
		Nivel miNivel;
		public TimerComestible ( int segundos, Nivel miNivel ) {			
			this.timer = new Timer ( ) ;
			this.miNivel = miNivel;
			this.timer.schedule ( new RemindTask ( ) , segundos*1000 ) ;
		}

		private void hacerFantasmasCasiNoComestibles(){
			this.miNivel.obtenerBlinky().hacerseCasiNoComestible();
			this.miNivel.obtenerInky().hacerseCasiNoComestible();
			this.miNivel.obtenerPinky().hacerseCasiNoComestible();
			this.miNivel.obtenerClyde().hacerseCasiNoComestible();
			new TimerCasiNoComestible (2, this.miNivel);
		}

		public void cancelarTimer(){
			timer.cancel ();
		}
		
		//Al pasar el tiempo que se pasó por parámetro se accede al método hacerFantasmasNoComestibles.
		class RemindTask extends TimerTask {
			public void run ( ) {
				hacerFantasmasCasiNoComestibles();
				timer.cancel () ; //Cancela el hilo del timer
			}
		}
		
}
	
