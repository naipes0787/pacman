package Vista;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import modelo.Juego;

import controlador.KeyboardController;
import controlador.WindowAdapterClosing;

/*
 * ESta clase representa la superficie de dibujo, tipicamente será el formulario
 * principal de la aplicación y donde se dibujará la vista.
 * Esta clase utiliza la tecnica de doble buffering para evitar los efectos de flicking
 */

	
public class Ventana extends Frame implements SuperficieDeDibujo{

	/**
	 * Esta yerba es generada automática y aún no se su utilidad
	 */
	private static final long serialVersionUID = 1L;
    private Image imagen;
	
	public Ventana(int ancho,int alto){
		setSize(ancho, alto);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		int x = (screenSize.width - frameSize.width) / 2;
		int y = (screenSize.height - frameSize.height) / 2;
		setLocation(x, y);
	}
	// es llamado internamente por el metodo repaint() de la clase Frame
	public void update(Graphics g) {
		paint(g);
	}

	/* 
	 *Esta es la imagen en que se realiza todo el dibujo utilizando la tecnica
	 *de doble buffering.
	 */

    
	public void paint(Graphics g) {
		g.drawImage(this.imagen, 4, 24, null);
	}

	public void limpiar() {
		if(this.imagen == null)
			this.imagen = this.createImage(getSize().width, getSize().height);
		Graphics superficieParaDibujar =  this.imagen.getGraphics();
		superficieParaDibujar.setColor(Color.BLACK);// 
		superficieParaDibujar.fillRect(0, 0, this.getSize().width, this.getSize().height);		
	}

	public Graphics getGrafico(){
		return this.imagen.getGraphics();
	}
	
	public void actualizar(){
		this.repaint();
	}
	
	public void addWindowListeners(final Juego unJuego){
		addWindowListener(new WindowAdapterClosing(this) {
			public void windowClosing(WindowEvent e) {
				this.ventanaPrincipal.salvarPartida(unJuego);
				dispose();
				System.exit(0);
			}
		});
	}
	
	public void addKeyboard(KeyboardController unControladorTeclado){
		this.addKeyListener(unControladorTeclado);
	}
	public int continuarPartida() {
		return 0;
	}
	public int nuevaPartida() {
		return 0;
	}	
	public void salvarPartida(Juego unJuego){
		int n = JOptionPane.showConfirmDialog(this,"Desea salvar esta partida?",
			    "Pacman",
			    JOptionPane.YES_NO_OPTION);
		if(n==0) unJuego.guardarJuego("files/partida.dat");
	}
	
}
