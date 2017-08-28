package modelo;
public abstract class Personaje {

	protected Point posicion;
	
	protected void mover(int x, int y){};
	
	public Point obtenerPosicion() {
		return null;
	}
	
	//compara las posiciones de unFantasma y unPcman, devuelve true si son iguales, o false en caso contrario.
	public boolean mismaPosicion(Fantasma unFantasma,Pacman unPacman){
		int fantasmaX = unFantasma.obtenerPosicion().getX();
		int fantasmaY = unFantasma.obtenerPosicion().getY();
		int pacmanX = unPacman.obtenerPosicion().getX();
		int pacmanY = unPacman.obtenerPosicion().getY();
		if(fantasmaX == pacmanX){
			if (fantasmaY == pacmanY){
				return true;
			}
		}
		return false;
	}	
	
	//Se fija si el pacman y algun fantasma están en la misma posición.
	public void compararPosicionesConFantasmas(Nivel unNivel) {
		this.comerFantasmaOMorirPacman(unNivel.obtenerBlinky(),unNivel);
		this.comerFantasmaOMorirPacman(unNivel.obtenerPinky(),unNivel);
		this.comerFantasmaOMorirPacman(unNivel.obtenerClyde(),unNivel);
		this.comerFantasmaOMorirPacman(unNivel.obtenerInky(),unNivel);		
	}
	
	//Según el estado del Fantasma muere el pacman o el fantasma y se reinicializan las posiciones.
	public void comerFantasmaOMorirPacman(Fantasma unFantasma,Nivel unNivel){
		if (this.mismaPosicion(unFantasma,unNivel.obtenerPacman())){
			if(unFantasma.esComestible()){
				unFantasma.fantasmaComido(unNivel);
			}else{
				unNivel.llevarFantasmasAJaula();
				unNivel.llevarPacmanAPosicionInicial();
				unNivel.pacmanFueAtrapado();
			}				
		}
	}
}
