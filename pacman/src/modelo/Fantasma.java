package modelo;

import Vista.Posicionable;

public abstract class Fantasma extends Personaje implements Posicionable{

		private boolean comestible;
		private boolean casiNoComestible;
		protected int velocidad;
		private int contadorVelocidad;
		private int contadorJaula;
		private int tiempoEnJaula;
		protected boolean encerrado;
		private int ultimaDireccion;
		private boolean yaMovido;
		private int repeticionMovimiento;

		
		public Fantasma(int velocidadInicial, int tiempoEnJaula) {
			this.irAJaula();
			this.comestible = false;
			this.casiNoComestible = false;
			this.velocidad = velocidadInicial;
			this.contadorVelocidad=1;
			this.yaMovido = false;
			this.tiempoEnJaula = tiempoEnJaula;
		}

		//Devuelve true si el fantasma está en la jaula, false en caso contrario.
		public boolean estaEncerrado(){
			return (this.encerrado);
		}
		
		public void hacerseComestible(){
			this.comestible = true;
		}
		
		public void hacerseCasiNoComestible(){
			this.casiNoComestible = true;
		}
		
		public void hacerseNoComestible(){
			this.comestible = false;
			this.casiNoComestible = false;
		}
		
		public void mover(int x, int y) {
			this.posicion.setXY(x, y); 
		}
		
		//Este método decide si el fantasma tiene que huir o ir a atrapar al pacman.
		public void elegirMovimiento (Nivel unNivel){
			this.yaMovido = false;
			if(this.contadorVelocidad==velocidad){
				if (this.esComestible())
					this.huirDePacman(unNivel);
				else this.atraparPacman(unNivel);			
				this.comerFantasmaOMorirPacman(this,unNivel);
				this.contadorVelocidad=1;
			}
			else this.contadorVelocidad++;
		}
		
		public Point obtenerPosicion() {
			return (this.posicion);
		}
		
		public boolean esComestible(){
			return this.comestible;
		}
		
		public boolean esCasiNoComestible(){
			return this.casiNoComestible;
		}
		
		abstract public void salirDeJaula();
		
		abstract public void irAJaula ();
		
		//Contiene la estrategia que usará Inky para atrapar al pacman.
		public void atraparPacman(Nivel unNivel){
		    Point distancia = this.distanciaPacman(unNivel.obtenerPacman().obtenerPosicion());
		    int distanciaEnX = distancia.getX();
			int distanciaEnY = distancia.getY();
			int positivoEnX = (int)Math.sqrt(Math.pow(distancia.getX(),2));
			int positivoEnY = (int)Math.sqrt(Math.pow(distancia.getY(),2));

			//comprueba si en x o y estan a menor casilleros
			if(positivoEnX>=positivoEnY){
	            //si x esta a menor distancia y la diferencia es negativa entonces se mueve x++
				if(distanciaEnX<=0){
					this.moverDerecha(unNivel);
				}else{//si x esta a menor distancia y la diferencia es positivo entonces se mueve x--
				    this.moverIzquierda(unNivel);
			      }
				}else{//si estaba a menor distancia en y lo anterior no corre y entra en este else
				//si la distancia es negativa se mueve y++
				if(distanciaEnY<=0){
			        this.moverAbajo(unNivel);
				}else{//sino se mueve y--
			        this.moverArriba(unNivel);
			        }
				}
			}	
		
		abstract public void huirDePacman(Nivel unNivel);
		
		//Este método se utiliza una vez q el fantasma es comido.
		public void fantasmaComido(Nivel unNivel) {
			this.hacerseNoComestible();
			unNivel.obtenerMiJuego().sumarPuntajeAlJugador(200);
			this.irAJaula();
			this.contadorJaula = 0;
		}

		//Este método corrobora si el fantasma debe seguir en la jaula, debe salir o debe moverse.
		public void moverFantasma (Nivel unNivel) {
			if (this.estaEncerrado()){
				if (this.contadorJaula == this.tiempoEnJaula){
					this.salirDeJaula();
				}else{
					this.contadorJaula++;
				}
			}else{
					this.elegirMovimiento(unNivel);
				}
		}
		
		//Calcula la distancia que hay entre el fantasma en cuestión y el pacman.
		public Point distanciaPacman(Point posicionPacman) {
			Point auxiliar = new Point(0,0);
			int distanciaEnX = this.obtenerPosicion().getX() - posicionPacman.getX();
			int distanciaEnY = this.obtenerPosicion().getY() - posicionPacman.getY();
			auxiliar.setXY(distanciaEnX,distanciaEnY);
			return auxiliar;
		}
		
		//Estrategia de movimiento.
		public void moverAbajo(Nivel unNivel){
			Laberinto unLaberinto = unNivel.obtenerMiLaberinto();
			int x = this.obtenerPosicion().getX();
			int y = this.obtenerPosicion().getY();
	        try {
	        	y++;
	        	unLaberinto.devolverContenido(x,y).serTransitado(unNivel,this,x, y);
	        	if (!(unLaberinto.devolverContenido(x,y) instanceof Transportador)){
	        		this.mover(x,y);
	        	}
                this.nuevaDireccion(1);
                }catch (NoTransitableException e1) {
                	this.moverDerecha(unNivel);
                }
        }
		
		//Estrategia de movimiento.
		public void moverArriba(Nivel unNivel){
			Laberinto unLaberinto = unNivel.obtenerMiLaberinto();
			int x = this.obtenerPosicion().getX();
			int y = this.obtenerPosicion().getY();
	        try {
	        	y--;
	        	unLaberinto.devolverContenido(x,y).serTransitado(unNivel,this,x, y);
	        	if (!(unLaberinto.devolverContenido(x,y) instanceof Transportador)){
	        		this.mover(x,y);
	        	}
                this.nuevaDireccion(0);
                }catch (NoTransitableException e1) {
                	this.moverIzquierda(unNivel);
            		    }
			
		}
		
		//Estrategia de movimiento.
		public void moverDerecha(Nivel unNivel){
			Laberinto unLaberinto = unNivel.obtenerMiLaberinto();
			int x = this.obtenerPosicion().getX();
			int y = this.obtenerPosicion().getY();
	        try {
	        	x++;
	        	unLaberinto.devolverContenido(x,y).serTransitado(unNivel,this,x, y);
	        	if (!(unLaberinto.devolverContenido(x,y) instanceof Transportador)){
	        		this.mover(x,y);
	        	}
                this.nuevaDireccion(3);
                }catch (NoTransitableException e1) {
                	this.moverArriba(unNivel);
            		    }
		}
		
		//Estrategia de movimiento.
		public void moverIzquierda(Nivel unNivel){
			Laberinto unLaberinto = unNivel.obtenerMiLaberinto();
			int x = this.obtenerPosicion().getX();
			int y = this.obtenerPosicion().getY();
	        try {
	        	x--;
	        	unLaberinto.devolverContenido(x,y).serTransitado(unNivel,this,x, y);
	        	if (!(unLaberinto.devolverContenido(x,y) instanceof Transportador)){
	        		this.mover(x,y);
	        	}
                this.nuevaDireccion(2);
                }catch (NoTransitableException e1) {
                	this.moverAbajo(unNivel);
            	 }
		}
		
		public int getX() {
			return this.obtenerPosicion().getX();
		}
		public int getY() {
			return this.obtenerPosicion().getY();
		}

		public void nuevaDireccion(int nuevaDireccion){
			this.ultimaDireccion = nuevaDireccion;
		}

		public int ultimaDireccion() {
			return ultimaDireccion;
		}
		
		//Transporta al fantasma desde la izquierda a la derecha
		protected void serTransportadoADerecha(int y) {
			this.mover(26,y);
		}
		
		//Transporta al fantasma desde la derecha a la izquierda
		protected void serTransportadoAIzquierda(int y) {
			this.mover(1,y);
		}

		public void setYaMovido(boolean yaMovido) {
			this.yaMovido = yaMovido;
		}

		public boolean yaMovio() {
			return yaMovido;
		}
		
		
		public void moverAbajoDos(Nivel unNivel){
			Laberinto unLaberinto = unNivel.obtenerMiLaberinto();
			int x = this.obtenerPosicion().getX();
			int y = this.obtenerPosicion().getY();
	        try {
	        	y++;
	        	unLaberinto.devolverContenido(x,y).serTransitado(unNivel,this,x, y);
	        	if (!(unLaberinto.devolverContenido(x,y) instanceof Transportador)){
	        		this.mover(x,y);
	        	}
	            this.nuevaDireccion(1);
	            }catch (NoTransitableException e1) {
	            	this.moverIzquierdaDos(unNivel);
	            }
	    }
		
		public void moverArribaDos(Nivel unNivel){
			Laberinto unLaberinto = unNivel.obtenerMiLaberinto();
			int x = this.obtenerPosicion().getX();
			int y = this.obtenerPosicion().getY();
	        try {
	        	y--;
	        	unLaberinto.devolverContenido(x,y).serTransitado(unNivel,this,x, y);
	        	if (!(unLaberinto.devolverContenido(x,y) instanceof Transportador)){
	        		this.mover(x,y);
	        	}
	            this.nuevaDireccion(3);
	            }catch (NoTransitableException e1) {
	            	this.moverDerechaDos(unNivel);
	        		    }
			
		}
		
		public void moverDerechaDos(Nivel unNivel){
			Laberinto unLaberinto = unNivel.obtenerMiLaberinto();
			int x = this.obtenerPosicion().getX();
			int y = this.obtenerPosicion().getY();
	        try {
	        	x++;
	        	unLaberinto.devolverContenido(x,y).serTransitado(unNivel,this,x, y);
	        	if (!(unLaberinto.devolverContenido(x,y) instanceof Transportador)){
	        		this.mover(x,y);
	        	}
	            this.nuevaDireccion(0);
	            }catch (NoTransitableException e1) {
	            	this.moverAbajoDos(unNivel);
	        		    }
		}
		
		public void moverIzquierdaDos(Nivel unNivel){
			Laberinto unLaberinto = unNivel.obtenerMiLaberinto();
			int x = this.obtenerPosicion().getX();
			int y = this.obtenerPosicion().getY();
	        try {
	        	x--;
	        	unLaberinto.devolverContenido(x,y).serTransitado(unNivel,this,x, y);
	        	if (!(unLaberinto.devolverContenido(x,y) instanceof Transportador)){
	        		this.mover(x,y);
	        	}
	            this.nuevaDireccion(2);
	            }catch (NoTransitableException e1) {
	            	this.moverArribaDos(unNivel);
	        	 }
		}
		
		public void setRepeticionMovimiento(int nuevoCantidadRepeticion){
			this.repeticionMovimiento = nuevoCantidadRepeticion;
		}


		public void disminuirRepeticionMovimiento() {
			this.repeticionMovimiento--;
		}


		public int repeticionMovimiento() {
			return repeticionMovimiento;
		}
	
}