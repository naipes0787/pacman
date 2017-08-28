package modelo;


public class Pinky extends Fantasma  {

	public Pinky(int velocidadInicial,int tiempoEnJaula) {
		super(velocidadInicial, tiempoEnJaula);
		this.setRepeticionMovimiento(20);
	}
	
	//Contiene la estrategia que usará Pinky para atrapar al pacman.
	public void atraparPacman(Nivel unNivel){
		if(this.repeticionMovimiento()!=0){
		int numeroAleatorio = (int) (Math.random()*3);
		 switch(numeroAleatorio) {
		 case 0:
			 this.moverDerecha(unNivel);
			 break;
		 case 1: 
			 this.moverArriba(unNivel);
			 break;
		 case 2: 
			 this.moverIzquierda(unNivel);
			 break;
		 case 3: 
			 this.moverAbajo(unNivel);
			 break;
		  }
		 this.disminuirRepeticionMovimiento();
		 }else{
			super.atraparPacman(unNivel);
			}
	}

	//Contiene la estrategia que usará Pinky para huir al pacman.
	public void huirDePacman(Nivel unNivel) {
        this.moverIzquierda(unNivel);
    }
	
	//Tiene una posición predeterminada para estar en la jaula
	public void irAJaula() {
		Point posicion = new Point (13,14);
		this.posicion = posicion;
		this.encerrado = true;
		this.hacerseNoComestible();
		this.setRepeticionMovimiento(20);

	}
	
	//Tiene una posición predeterminada para salir de la jaula
	public void salirDeJaula (){
		Point posicion = new Point (13,11);
		this.posicion = posicion;
		this.encerrado = false;
	}

}