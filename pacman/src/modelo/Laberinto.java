package modelo;

import java.io.*; 

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import Vista.Posicionable;


public class Laberinto implements Posicionable{

private Contenido[][] contenidos;
private int cantidadPastillas;
private int cantidadFilas = 31;
private int cantidadColumnas = 28;

public Laberinto(int nivel) throws ArchivoFueraDeFormatoException{
	 cantidadPastillas = 0;
	 contenidos = new Contenido[cantidadColumnas][cantidadFilas];
	 this.cargarLaberintoSegunNivel(nivel);	
}
//constructor para persistencia
public Laberinto(int cantidadPastillas, int cantidadColumnas,
		int cantidadFilas, Contenido[][] contenidos) {
	this.contenidos=contenidos;
	this.cantidadPastillas=cantidadPastillas;
	this.cantidadColumnas=cantidadColumnas;
	this.cantidadFilas=cantidadFilas;
}


public Contenido devolverContenido(int x,int y){
	 if ((x<0)|(x>=cantidadColumnas)|(y<0)|(y>=cantidadFilas)) throw new IllegalArgumentException();
	 	return (contenidos[x][y]);
 }
 
//Genera el laberinto dependiendo del número de nivel que se le pase.
private void cargarLaberintoSegunNivel(int nivel) throws ArchivoFueraDeFormatoException{
	  	
		int x; 
		int y;
		int caracter = 0;
				
		BufferedReader input = null;
		File laberintoFile = new File("files/nivel"+nivel+".xml");
		try {
			input = new BufferedReader(new FileReader(laberintoFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (y = 0; y < cantidadFilas; y++) {
			for (x = 0; x < cantidadColumnas +2; x++) {				
				try {
					caracter = input.read();					
				} catch (IOException e) {
					e.printStackTrace();
				}
				if((x>cantidadColumnas-1)&(!((caracter==13)|(caracter==10)))) throw new ArchivoFueraDeFormatoException(); 
				else if (x<=cantidadColumnas-1){
					agregarContenido(caracter, x, y);
				
				}
			}
		}

	}
	

//Agrega el contenido (correspondiente a la posición que recibe) en el laberinto.
private void agregarContenido(int caracter, int x, int y) throws ArchivoFueraDeFormatoException {
		switch (caracter) {
			case 35:
				contenidos[x][y]= new Bloque();
				break;
			case 111:
				contenidos[x][y]= new Punto();
				cantidadPastillas++;
				break;
			case 79:
				contenidos[x][y]= new PuntoPoder();
				cantidadPastillas++;
				break;
			case 32:
				contenidos[x][y]= new Vacio();
				break;
			case 61:
				contenidos[x][y]= new Transportador();
				break;
			default: throw new ArchivoFueraDeFormatoException();
		}	
	} 

	public int obtenerCantidadPastillas(){
		return cantidadPastillas;
	}

	//Resta las pastillas que hay en el laberinto a medida que el pacman se las va comiendo.
	public int restarCantidadPastillas(){
		return cantidadPastillas--;
	}
	
	//Agrega una fruta en el laberinto, se usa luego de determinada cantidad de tiempo.
	public void agregarFruta(){
		contenidos[14][17] = new Fruta();
	}

	//Agrega un vacío en determinada posición, luego de que el pacman se come lo que había anteriormente.
	public void agregarVacio(int columna,int fila) {
		contenidos[columna][fila] = new Vacio();
	}

	public int getX() {
		return 0;
	}

	public int getY() {
		return 0;
	}

	public Element guardar(Document doc) {
		Element elemLaberinto = doc.createElement("Laberinto");
		elemLaberinto.setAttribute("cantidadPastillas",""+this.cantidadPastillas);
		elemLaberinto.setAttribute("cantidadColumnas",""+this.cantidadColumnas);
		elemLaberinto.setAttribute("cantidadFilas",""+this.cantidadFilas);
		for (int y = 0; y < cantidadFilas; y++) {
			for (int x = 0; x < cantidadColumnas ; x++) 	
				elemLaberinto.appendChild((Element)contenidos[x][y].guardar(doc,x,y));
			}
        return elemLaberinto;
	}

	public static Laberinto recuperar(Element elemLaberinto) {
		int cantidadPastillas = Integer.parseInt(elemLaberinto.getAttribute("cantidadPastillas"));
		int cantidadColumnas= Integer.parseInt(elemLaberinto.getAttribute("cantidadColumnas"));
		int cantidadFilas= Integer.parseInt(elemLaberinto.getAttribute("cantidadFilas"));
		Contenido[][] contenidos;
		contenidos = new Contenido[cantidadColumnas][cantidadFilas];
		NodeList listaContenido;
		Node elemContenido;
		for (int y = 0; y < cantidadFilas; y++) {
			for (int x = 0; x < cantidadColumnas ; x++){	
				listaContenido= elemLaberinto.getElementsByTagName("ContenidoColumna"+x+"Fila"+y);
				elemContenido= listaContenido.item(0);
				String tipo=((Element) elemContenido).getAttribute("tipo");				
				if (tipo.equals("PuntoPoder")){
					contenidos[x][y]=(Contenido) PuntoPoder.recuperar((Element)elemContenido);
				}else if (tipo.equals("Punto")){
					contenidos[x][y]=(Contenido) Punto.recuperar((Element)elemContenido);
				}else if (tipo.equals("Bloque")){
					contenidos[x][y]=(Contenido) Bloque.recuperar((Element)elemContenido);
				}else if (tipo.equals("Vacio")){
					contenidos[x][y]=(Contenido) Vacio.recuperar((Element)elemContenido);
				}else if (tipo.equals("Fruta")){
					contenidos[x][y]=(Contenido) Fruta.recuperar((Element)elemContenido);
				}else if (tipo.equals("Transportador")){
					contenidos[x][y]=(Contenido) Transportador.recuperar((Element)elemContenido);
				}
			}
		}
		Laberinto unLaberinto= new Laberinto(cantidadPastillas,cantidadColumnas,cantidadFilas,contenidos);
		return unLaberinto;
	}
}