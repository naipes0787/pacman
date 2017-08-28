package Vista;


import java.awt.Color;




public class VistaFondo extends Cuadrado {

	public VistaFondo(Mesa fondo) {
		super(fondo.getAncho(),fondo.getAlto());
		setColor(Color.BLACK);
	}

}