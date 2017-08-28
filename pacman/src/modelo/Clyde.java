package modelo;
public class Clyde extends Fantasma {

	public Clyde(int velocidadInicial,int tiempoEnJaula) {
		super(velocidadInicial, tiempoEnJaula);
		this.nuevaDireccion(2);
		this.setRepeticionMovimiento(45);
	}

	//Contiene la estrategia que usará Clyde para atrapar al pacman.
	public void atraparPacman(Nivel unNivel) {
		if(this.repeticionMovimiento()!=0){
		switch(this.ultimaDireccion()) {
		 case 0:
			 this.moverArriba(unNivel);
		     break;
		 case 1:
			 this.moverAbajo(unNivel);
			 break;
		 case 2:
			 this.moverIzquierda(unNivel);
			 break;
		 case 3:
			 this.moverDerecha(unNivel);
			 break;
		 }
		this.disminuirRepeticionMovimiento();
		}else{
			super.atraparPacman(unNivel);
	
		}
	}
	
	//Contiene la estrategia que usará Clyde para huir al pacman.
	public void huirDePacman(Nivel unNivel) {
        this.moverDerecha(unNivel);
    }
	
	//Tiene una posición predeterminada para estar en la jaula
	public void irAJaula() {
		Point posicion = new Point (14,13);
		this.posicion = posicion;
		this.encerrado = true;
		this.hacerseNoComestible();
		this.setRepeticionMovimiento(45);
	}
	
	//Tiene una posición predeterminada para salir de la jaula
	public void salirDeJaula (){
		Point posicion = new Point (13,11);
		this.posicion = posicion;
		this.encerrado = false;
	}	
}