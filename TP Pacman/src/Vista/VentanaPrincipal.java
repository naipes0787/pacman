package Vista;

import javax.swing.JOptionPane;

public class VentanaPrincipal extends Ventana {

	public  VentanaPrincipal() {
		super(567,670);
		this.setTitle("Pacman Grupo 8");	
		this.setVisible(true);
	}

	private static final long serialVersionUID = 1L;
	
			  
	public int continuarPartida(){
		int n = JOptionPane.showConfirmDialog(this,"Desea continuar la última partida?",
			    "Pacman",
			    JOptionPane.YES_NO_OPTION);
		return n;
	}	
	
	public int nuevaPartida(){
		int n = JOptionPane.showConfirmDialog(this,"Desea empezar una nueva partida?",
			    "Pacman",
			    JOptionPane.YES_NO_OPTION);
		return n;
	}	
}
