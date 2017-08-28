package Vista;


import java.awt.Graphics;



public class Circulo  extends Figura {

	private int radio;
	
	public Circulo(int radio){
		this.radio = radio;
	}
	
	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = ((Ventana)superfice).getGrafico();
		grafico.setColor(getColor());
		grafico.fillOval(getPosicionable().getX() , getPosicionable().getY(), this.radio, this.radio);
	}

}
