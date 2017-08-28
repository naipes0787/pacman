package modelo;
	import java.util.Timer;
	import java.util.TimerTask;

	public class TimerCasiNoComestible {
		
		Timer timer;
		Nivel miNivel;
		public TimerCasiNoComestible ( int segundos, Nivel miNivel ) {			
			this.timer = new Timer ( ) ;
			this.miNivel = miNivel;
			this.timer.schedule ( new RemindTask ( ) , segundos*1000 ) ;
		}

		private void hacerFantasmasNoComestibles(){
			this.miNivel.obtenerBlinky().hacerseNoComestible();
			this.miNivel.obtenerInky().hacerseNoComestible();
			this.miNivel.obtenerPinky().hacerseNoComestible();
			this.miNivel.obtenerClyde().hacerseNoComestible();
		}

		public void cancelarTimer(){
			timer.cancel ();
		}
		
		//Al pasar el tiempo que se pasó por parámetro se accede al método hacerFantasmasNoComestibles.
		class RemindTask extends TimerTask {
			public void run ( ) {
				hacerFantasmasNoComestibles();
				timer.cancel () ; //Cancela el hilo del timer
			}
		}
		
}
	
