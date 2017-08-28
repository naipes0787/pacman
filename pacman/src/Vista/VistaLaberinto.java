package Vista;



import modelo.Bloque;
import modelo.Contenido;
import modelo.Fruta;
import modelo.Laberinto;
import modelo.Punto;
import modelo.PuntoPoder;
import modelo.Vacio;


public class VistaLaberinto implements Dibujable{

	private Posicionable laberinto;
	private Laberinto unLaberinto;
	private VistaContenido vistaPunto;
	private VistaContenido vistaPuntoPoder;
	private VistaContenido vistaBloque;
	private VistaContenido vistaFruta;
	
	public VistaLaberinto(Laberinto unLaberinto){
		this.unLaberinto=unLaberinto;
		this.setPosicionable(unLaberinto);
		this.vistaPunto = new VistaContenido("punto.jpg");
		this.vistaPuntoPoder = new VistaContenido("puntopoder.jpg");
		this.vistaBloque = new VistaContenido("bloque.jpg");
		this.vistaFruta = new VistaContenido("fruta.jpg");
	}
	
	public void dibujar(SuperficieDeDibujo superficie) {
		int cantidadFilas=31;
		int cantidadColumnas=28;
		Contenido unContenido;
		for (int y = 0; y < cantidadFilas; y++) {
			for (int x = 0; x < cantidadColumnas ; x++) {				
				unContenido=unLaberinto.devolverContenido(x,y);
				if (unContenido instanceof Punto){
					this.vistaPunto.dibujar(superficie,x,y);
				}else if (unContenido instanceof PuntoPoder){
					this.vistaPuntoPoder.dibujar(superficie,x,y);
				}else if (unContenido instanceof Bloque){
					this.vistaBloque.dibujar(superficie,x,y);
				}else if (unContenido instanceof Vacio){
				}else if (unContenido instanceof Fruta){
					this.vistaFruta.dibujar(superficie,x,y);
				}
			}						
		}
	}

	public Posicionable getPosicionable() {
		return this.laberinto;
	}

	public void setPosicionable(Posicionable posicionable) {
		this.laberinto=posicionable;
	}

}