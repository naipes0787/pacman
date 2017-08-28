package Vista;

import java.awt.*;
import java.io.InputStream;

import com.sun.image.codec.jpeg.*;

public class VistaContenido{
	 
	private Image imagenContenido;

	
	public VistaContenido(String nombreArchivoImagen) {
		setNombreArchivoImagen(nombreArchivoImagen);
	}	
	
	//redefino dibujar de la clase Imagen.
	public void dibujar(SuperficieDeDibujo superficeDeDibujo, int x, int y) {
			Graphics grafico = ((Ventana)superficeDeDibujo).getGrafico();
			grafico.drawImage(imagenContenido,x*20,y*20, null);
	}

	public void setNombreArchivoImagen(String nombreArchivoImagen) {
		InputStream in = getClass().getResourceAsStream(nombreArchivoImagen);
		JPEGImageDecoder decoder1 = JPEGCodec.createJPEGDecoder(in);
		try{
			this.imagenContenido= decoder1.decodeAsBufferedImage();
			in.close();
		}catch(Exception ex){
		}			
	}
}