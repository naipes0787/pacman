package modelo;

import java.io.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.serialize.*;

public class Juego {

	private Nivel unNivel; 
	private Jugador jugador;
	private int nivelActual;
	private static int cantidadDeNiveles=5;
	private boolean archivoErroneo=false;
	
	
	public Juego(){
		this.nivelActual = 1; //nivel inicial 1.
		this.jugador = new Jugador();
		try{
			this.unNivel = new Nivel(this, this.nivelActual);
		} catch (ArchivoFueraDeFormatoException e) {
			archivoErroneo=true;
		}
	}

	
	public Juego(Jugador unJugador, int unNivel, Laberinto unLaberinto) {
		this.nivelActual = unNivel;
		this.jugador = unJugador;
		this.unNivel = new Nivel(this, this.nivelActual, unLaberinto);
	}


	public void pasarDeNivel() throws JuegoGanadoException{
		this.nivelActual++;
		if(seGanoJuego()){
			throw new JuegoGanadoException();
		}
		else try {
				this.unNivel = new Nivel(this, this.nivelActual);
			} catch (ArchivoFueraDeFormatoException e) {
				archivoErroneo=true;
			}
	}
	

	//Comprobación de si ya se ha ganado el Juego.
	public boolean seGanoJuego() {
		if(cantidadDeNiveles<nivelActual) return true;
		else return false;
	}
	
	//Va sumando el puntaje al jugador, cada 10000 puntos agrega una vida.
	public void sumarPuntajeAlJugador(int puntaje){
		if(puntaje<0) throw new IllegalArgumentException();
		this.jugador.sumarPuntaje(puntaje);
		if (this.jugador.obtenerPuntaje()%10000.0 == 0) // Obtiene el resto de la division
			this.jugador.agregarVida();
	}
	
	
	public int obtenerPuntajeDelJugador(){
		return this.jugador.obtenerPuntaje();
	}
	
	
	public int obtenerNivelActual(){
		return this.nivelActual;
	}
	
	public int obtenerCantidadNiveles(){
		return this.cantidadDeNiveles;
	}
	
	
	public int obtenerVidasDisponibles(){
		return (this.jugador.obtenerVidasDisponibles());
	}
	
	//Comienza los movimientos del juego.
	public void empezarAJugar() throws NivelGanadoException, ArchivoFueraDeFormatoException{
		if(archivoErroneo) throw new ArchivoFueraDeFormatoException();
		this.unNivel.comenzarMoverFantasmas();
		this.unNivel.comenzarMoverPacman();
		if (this.seGanoNivel()){
			throw new NivelGanadoException();
		}
	}
	
	//Comprobación de si ya se ha ganado el nivel.
	public boolean seGanoNivel(){
		if (this.unNivel.obtenerMiLaberinto().obtenerCantidadPastillas() == 0){
			return true;
		}
		return false;
	}


	public void pacmanFueAtrapado() {
		this.jugador.perderVida();	
	}	
	
	
	public Nivel obtenerNivel(){
		return (this.unNivel);
	}


	public boolean archivoErroneo() {
		return this.archivoErroneo;
	}
	
	public void guardarJuego(String nombreArchivo){
		try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.newDocument();
            Element raiz = doc.createElement("Juego");
            raiz.appendChild((Element)unNivel.obtenerMiLaberinto().guardar(doc));
            raiz.appendChild((Element)this.jugador.guardar(doc));
            raiz.setAttribute("nivel",""+this.nivelActual);
            doc.appendChild(raiz);
            File archivo = new File(nombreArchivo);
            XMLSerializer serializer = new XMLSerializer();
            OutputFormat outFormat = new OutputFormat();
            outFormat.setVersion("1.0");
            outFormat.setIndenting(true);
            outFormat.setIndent(4);
            serializer.setOutputFormat(outFormat);
            serializer.setOutputCharStream(new FileWriter(archivo));
			serializer.serialize(doc);
		}
		catch (ParserConfigurationException e) {}
        catch (IOException e) {
        	e.printStackTrace();
        }
   	}
	
	public static Juego recuperarJuego(String nombreArchivo){
		Juego juego = null;
		File archivo = new File(nombreArchivo);
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document;
			document = builder.parse(archivo);
			Element raiz = document.getDocumentElement();
			NodeList listaLaberinto = raiz.getElementsByTagName("Laberinto");
			Node elemLaberinto= listaLaberinto.item(0);
			NodeList listaJugador = raiz.getElementsByTagName("Jugador");
			Node elemJugador = listaJugador.item(0);
			Laberinto unLaberinto=(Laberinto) Laberinto.recuperar((Element)elemLaberinto);
			Jugador unJugador=(Jugador) Jugador.recuperar((Element)elemJugador);
			int unNivel= Integer.parseInt(raiz.getAttribute("nivel"));
			juego=new Juego(unJugador,unNivel, unLaberinto);
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (SAXException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return juego;
	}

	
}
