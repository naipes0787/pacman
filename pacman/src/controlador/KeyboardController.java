package controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

	
public class KeyboardController extends KeyAdapter{
	
	private KeyboardDireccion keyboardDireccion;
	public KeyboardController(KeyboardDireccion direccionamiento) {
		this.keyboardDireccion=direccionamiento;
	}

	public void keyPressed(KeyEvent e){ 
		int aux=e.getKeyCode();
		switch(aux){
		case 38: 
			this.keyboardDireccion.nuevoSentido(0,-1);	
			break;
		case 40: 
			this.keyboardDireccion.nuevoSentido(0,1);
			break;
		case 37: 
			this.keyboardDireccion.nuevoSentido(-1,0);
			break;
		case 39: 
			this.keyboardDireccion.nuevoSentido(1,0);
			break;
		}
	}
}
