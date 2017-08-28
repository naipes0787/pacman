package modelo;

//Esta clase fue generada para poder guardar posiciones de dos coordenadas.
public class Point {

  int x,y;
  
  public Point(int x, int y) {
    this.x= x; this.y= y;
  }
  
  public void setXY (int x, int y){
	  this.x = x;
	  this.y = y;
  }
  
  public int getX(){
	  return(this.x);
  }
  
  public int getY(){
	  return(this.y);
  }
  
}
