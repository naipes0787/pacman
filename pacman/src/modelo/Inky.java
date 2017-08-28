package modelo;
public class Inky extends Fantasma {

	public Inky(int velocidadInicial,int tiempoEnJaula) {
		super(velocidadInicial, tiempoEnJaula);
	}

	//Contiene la estrategia que usará Inky para huir al pacman.
	public void huirDePacman(Nivel unNivel) {
        this.moverAbajo(unNivel);
    }
	
	//Tiene una posición predeterminada para estar en la jaula
	public void irAJaula() {
		Point posicion = new Point (14,14);
		this.posicion = posicion;
		this.encerrado = true;
		this.hacerseNoComestible();
	}
	
	//Tiene una posición predeterminada para salir de la jaula
	public void salirDeJaula (){
		Point posicion = new Point (14,11);
		this.posicion = posicion;
		this.encerrado = false;
	}
	
}