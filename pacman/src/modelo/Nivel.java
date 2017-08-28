package modelo;

public class Nivel {
	
	private Juego juego;
	private Laberinto miLaberinto;
	private Pacman pacman;
	private int nivel;
	
	//TODO
	private boolean recienEmpieza;

	private Blinky blinky;
	private Pinky pinky;
	private Inky inky;
	private Clyde clyde;
	
	public Nivel(Juego unJuego, int nivel) throws ArchivoFueraDeFormatoException{
		//La idea es q reciba el numero de nivel por parametro y en base al
		//nivel se crea el laberinto. Tmb pense en darle a velocidad el mismo
		//valor q nivel, entonces a medida q avanza niveles, la velocidad de
		//los fantasmas es mayor. Lo mismo con la duracion de puntoPoder.
		this.juego = unJuego;
		this.nivel=nivel;
		this.miLaberinto = new Laberinto (this.nivel);
		this.recienEmpieza = true;
		
		int velocidad = 7 - this.nivel;
		if (velocidad >3)
			this.crearPersonajes(velocidad);
		else
			this.crearPersonajes(3);
		new TimerFruta(15,this); 
		new TimerVacio(22,this);
	}

	//constructor para persistencia
	public Nivel(Juego juego, int nivelActual, Laberinto unLaberinto) {
		this.juego = juego;
		this.nivel=nivelActual;
		this.miLaberinto = unLaberinto;
		
		int velocidad = 7 - this.nivel;
		if (velocidad >3)
			this.crearPersonajes(velocidad);
		else
			this.crearPersonajes(3);
		new TimerFruta(15,this); 
		new TimerVacio(22,this);
	}


	private void crearPersonajes(int velocidad){
		this.blinky = new Blinky (velocidad-1,10);
		this.pinky = new Pinky (velocidad,20);
		this.inky = new Inky (velocidad-2,30);
		this.clyde = new Clyde (velocidad,40); 	
		this.pacman = new Pacman ();
	}
	
	public void llevarFantasmasAJaula() {
		this.blinky.irAJaula();
		this.pinky.irAJaula();
		this.inky.irAJaula();
		this.clyde.irAJaula(); 	
	}
	
	public void llevarPacmanAPosicionInicial() {
		this.pacman.renacer();
	}
	
	public Juego obtenerMiJuego(){
		return (this.juego);
	}
	
	
	public void comenzarMoverFantasmas(){
			this.blinky.moverFantasma(this);
			this.pinky.moverFantasma(this);
			this.clyde.moverFantasma(this);
			this.inky.moverFantasma(this);
	}		
	
	public void comenzarMoverPacman(){
			if(!pacman.moverSegunSentido(false,this)) pacman.moverSegunSentido(true,this);
	}	
	
	public int obtenerNumeroNivel() {
		return this.nivel;
	}
	
	public Laberinto obtenerMiLaberinto() {
		return miLaberinto;
	}
	
	public Pacman obtenerPacman(){
		return this.pacman;
	}
	
	public Pinky obtenerPinky(){
		return (this.pinky);
	}
	
	
	public Blinky obtenerBlinky(){
		return (this.blinky);
	}
	
	
	public Clyde obtenerClyde(){
		return (this.clyde);
	}
	
	
	public Inky obtenerInky(){
		return (this.inky);
	}

	//Tranporta al pacman, en caso de estar en un casillero Transportador.
	public void transportarPacman(int x, int y) {	
		if(x==0){
			this.pacman.serTransportadoADerecha(y);
			try{	
				int posicionPacmanX = this.pacman.obtenerPosicion().getX()-1;
				int posicionPacmanY = this.pacman.obtenerPosicion().getY();
				this.obtenerMiLaberinto().devolverContenido(posicionPacmanX,posicionPacmanY).hayPacman(this,posicionPacmanX,posicionPacmanY);
			   } catch (NoTransitableException e) {}
		}else
			if (x==27){
				this.pacman.serTransportadoAIzquierda(y);
				try{	
					int posicionPacmanX = this.pacman.obtenerPosicion().getX()+1;
					int posicionPacmanY = this.pacman.obtenerPosicion().getY();
					this.obtenerMiLaberinto().devolverContenido(posicionPacmanX,posicionPacmanY).hayPacman(this,posicionPacmanX,posicionPacmanY);
				} catch (NoTransitableException e) {}
			}
	}


	//Tranporta al fantasma, en caso de estar en un casillero Transportador.
	public void transportarFantasma(Fantasma unFantasma, int x, int y) {
		if(x==0){
			unFantasma.serTransportadoADerecha(y);
			}
		if(x==27){
			unFantasma.serTransportadoAIzquierda(y);
		}	
	}

	public void pacmanFueAtrapado() {
		this.juego.pacmanFueAtrapado();
	}

	//TODO
	public void yaEmpezo() {
		this.recienEmpieza = false;
	}

	public boolean recienEmpieza() {
		return (this.recienEmpieza);
	}
	
}
