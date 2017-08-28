package modelo;

import controlador.KeyboardDireccion;
import Vista.*;

public class Pacman extends Personaje implements Posicionable,Movible,KeyboardDireccion {

	
	private int sentidoY=0;
	private int sentidoX=-1;
	private int sentidoGraficoY=0;
	private int sentidoGraficoX=-1;
	private int ultimoSentidoPacmanX=-1;
	private int ultimoSentidoPacmanY=0;

	public Pacman() {
		this.renacer();
	}
	
	//El pacman se mueve en una dirección dependiendo del sentido q se le haya dado.
	public boolean moverSegunSentido(boolean moverSegunUltimoSentido, Nivel unNivel){
		int x;
		int y;
		int sentidoEnX=this.getSentidoX();
		int sentidoEnY=this.getSentidoY();
		if(moverSegunUltimoSentido){	
			sentidoEnX=ultimoSentidoPacmanX;
			sentidoEnY=ultimoSentidoPacmanY;
		}
		x = this.getX();
		y = this.getY();
		
		switch (sentidoEnX){		
		case -1:
			//Si se desea ir para la izquierda.
			x = x-1;
			try {
				 unNivel.obtenerMiLaberinto().devolverContenido(x,y).hayPacman(unNivel,x,y);
				 this.irIzquierda();
				 ultimoSentidoPacmanX=-1;
				 ultimoSentidoPacmanY=0;
				 if (!unNivel.obtenerMiJuego().seGanoJuego()){
					this.compararPosicionesConFantasmas(unNivel);
					return true;
				 }
			} catch (NoTransitableException e) {}
			 break;
		case 1:
			//Si se desea ir para la derecha.
			x = x+1;
			  try {
				 unNivel.obtenerMiLaberinto().devolverContenido(x,y).hayPacman(unNivel,x,y);
				 this.irDerecha();
				 ultimoSentidoPacmanX=1;
				 ultimoSentidoPacmanY=0;
				 if (!unNivel.obtenerMiJuego().seGanoJuego()){
					 this.compararPosicionesConFantasmas(unNivel);
					 return true;
				 }
			  } catch (NoTransitableException e) {}
			 break;
		}
		switch (sentidoEnY){
		case 1:
			//Si se desea ir para abajo.
			y = y+1;
			try {
				 unNivel.obtenerMiLaberinto().devolverContenido(x,y).hayPacman(unNivel,x,y);
				 this.irAbajo();
				 ultimoSentidoPacmanY=1;
				 ultimoSentidoPacmanX=0;
				 if (!unNivel.obtenerMiJuego().seGanoJuego()){
					this.compararPosicionesConFantasmas(unNivel);
					return true;
				 }
			} catch (NoTransitableException e) {}
			 break;
		
		case -1:
			y = y-1;
			try {
				 unNivel.obtenerMiLaberinto().devolverContenido(x,y).hayPacman(unNivel,x,y);
				 this.irArriba();
				 ultimoSentidoPacmanY=-1;
				 ultimoSentidoPacmanX=0;
				 if (!unNivel.obtenerMiJuego().seGanoJuego()){
					 this.compararPosicionesConFantasmas(unNivel);
					 return true;
				 }
			} catch (NoTransitableException e) {}
			 break;
		}
		return false;
	}
		
	protected void mover(int x, int y) {
		this.posicion.setXY(x, y);
	}
	
	private void irIzquierda() {
		this.mover((this.posicion.getX()-1),this.posicion.getY());
		this.nuevoSentidoGrafico(-1,0);
	}
	
	private void irDerecha() {
		this.mover((this.posicion.getX()+1),this.posicion.getY());
		this.nuevoSentidoGrafico(1,0);
	}
	
	private void irArriba() {
		this.mover(this.posicion.getX(),(this.posicion.getY()-1));
		this.nuevoSentidoGrafico(0,-1);
	}
	
	private void irAbajo() {
		this.mover(this.posicion.getX(),(this.posicion.getY()+1));
		this.nuevoSentidoGrafico(0,1);
	}
	
	public Point obtenerPosicion() {
		return (this.posicion);
	}
	
	//Situa al pacman en la posicion inicial.
	public void renacer() {
		Point posicion = new Point(13,23);
		this.posicion=posicion;
		sentidoY=0;
		sentidoX=-1;
		sentidoGraficoY=0;
		sentidoGraficoX=-1;
	}
	
	public void nuevoSentido(int i, int j) {
		if((((i==1)|(i==-1))&(j==0))|(((j==1)|(j==-1))&(i==0))) {
			this.sentidoX=i;
			this.sentidoY=j;
		}
		else throw new IllegalArgumentException();
	}

	public void nuevoSentidoGrafico(int i, int j) {
		if((((i==1)|(i==-1))&(j==0))|(((j==1)|(j==-1))&(i==0))) {
			this.sentidoGraficoX=i;
			this.sentidoGraficoY=j;
		}
		else throw new IllegalArgumentException();
	}
	
	public int getX() {
		Point unPoint=obtenerPosicion();
		return unPoint.getX();
	}

	public int getY() {
		Point unPoint=obtenerPosicion();
		return unPoint.getY();
	}

	public int getSentidoX() {
		return sentidoX;
	}

	public int getSentidoY() {
		return sentidoY;
	}

	public int getSentidoGraficoX() {
		return sentidoGraficoX;
	}

	public int getSentidoGraficoY() {
		return sentidoGraficoY;
	}

	protected void serTransportadoADerecha(int y) {
		this.mover(27,y);
	}
	
	protected void serTransportadoAIzquierda(int y) {
		this.mover(0,y);
	}
	
}
