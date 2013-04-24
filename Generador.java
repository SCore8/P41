//package generador;



import java.util.ArrayList;
import java.util.*;

public class Generador {

	private static int tiempoMax;
	private static int tiempoTranscurrido;
	
	
	//CONSTRUCTORA
	/*----------------------------------------------------------------------------------------------------
	- PRE: Cierto
	- POST: Se crea un objeto de la clase generador.
	----------------------------------------------------------------------------------------------------*/
	public Generador(){
		tiempoMax = -1;
		tiempoTranscurrido = 0;
	}
	/*----------------------------------------------------------------------------------------------------
	- PRE: cjntContenido.size() != 0, el tiempo se define en min. si tiempo = -1 sin limite de tiempo.
	- POST: Se genera una solución a partir de la asignación del cjnt de contenido a asignar en el
			contenedor cumpliendo con las restricciones propias de cada contenido. En el caso que no se
			pueda generar o se agote el tiempo retorna un error.
	----------------------------------------------------------------------------------------------------*/
	public static void generar(Contenedor contenedor, ArrayList<Contenido> cjntcontenido, int tiempo){
		/* definir tiempo maximo*/
		/* dado un Cjnt de contenido (ArrayList) se asignan al contenedor */
		/* restriccion(contenidoEnColocacion, casillaDondeSeColoca, estadoActualMatriz);*/
		//vector ordenado de contenido???
		//ArrayList<Contenido> contenido = ArrayList<Contenido>();

		//ArrayList para los contenidos asignados.
		ArrayList<Contenido> cjntContAssig = new ArrayList<Contenido>();
		tiempoMax = tiempo;
		backtracking(contenedor, cjntcontenido, cjntContAssig);
		
	}
	
	/*----------------------------------------------------------------------------------------------------
	- PRE: cierto.
	- POST: Genera una solucion de la asignación del conjunto de contenidos cumpliendo con sus 
			restricciones. En caso que no se pueda generar, retorna un error.
	----------------------------------------------------------------------------------------------------*/
	private static Casilla<T>[][] backtracking(Contenedor solucion, ArrayList<Contenido> cjntcontenido, ArrayList<Contenido> cjntContAssig){

		if(cjntcontenido.isEmpty()){
			System.out.println("se acabo!");
			return solucion;
		}
		else {
			Contenido contenidoActual = cjntcontenido.get(0);
			cjntcontenido.remove(0);
			for(int i = 0; i < solucion.getFilas(); i++){
				for(int j = 0; j < solucion.getColumnas(); j++){
					//miramos si la posicion no esta llena y si cabe el contenido que queremos añadir
					if((solucion.estaLlena(new Posicion(i, j))) && (solucion.cabeContenido(new Posicion(i, j), contenidoActual))) {
						//anadir contenido al contenedor en esa posicion
						anadir(contenidoActual, solucion, new Posicion(i, j));
						//asignarle a ese contenido la casilla.
			
					
						//solucion valida?
						List<Restriccion> cjntRestrCA = contenidoActual.getRestricciones();
						CjtoRestricciones restricciones = new CjtoRestricciones(cjntRestrCA);
						if(restricciones.satisfaceRestricciones(contenidoActual, cjntContAssig)){
							cjntContAssig.add(contenidoActual);
							return backtracking(solucion, cjntcontenido, cjntContAssig);
						}
						else{
							quitar(contenidoActual, solucion, new Posicion(i, j));

						}
					}
				}

			}
		}
		return solucion;
	}

	
	/*----------------------------------------------------------------------------------------------------
	- PRE: 0 <= x < numColumnas, 0 <= y < numFilas
	- POST: Anade un contenido al contenedor en la posicion[x, y].
	----------------------------------------------------------------------------------------------------*/
	private static void anadir(Contenido contenido, Contenedor contenedor, Posicion posicion){
		contenedor.anadirContenido(posicion, contenido);
	}
	
	/*----------------------------------------------------------------------------------------------------
	- PRE: 0 <= x < numColumnas, 0 <= y < numFilas
	- POST: Borra un contenido del contenedor en la posicion[x, y].
	----------------------------------------------------------------------------------------------------*/
	private static void quitar(Contenido contenido, Contenedor contenedor, Posicion posicion){
		contenedor.quitarContenido(posicion, contenido);
	}

	/*----------------------------------------------------------------------------------------------------
	- PRE: 0 <= x < numColumnas, 0 <= y < numFilas
	- POST: Asigna al contenido la posicion[x, y] de la solucion.
	----------------------------------------------------------------------------------------------------*/
	private void asignar(Contenido contenido, Posicion posicion){

	}

	/*----------------------------------------------------------------------------------------------------
	- PRE: 0 <= x < numColumnas, 0 <= y < numFilas.
	- POST: Devuelve cierto en el caso que la asignacion del contenidoActual en la posición [x, y] de la 
			solucion cumpla con todas las restricciones establecidas para dicho contenido. Devuelve falso
			en el caso contrario
	----------------------------------------------------------------------------------------------------*/
	private boolean cumpleRestr(Contenido contenidoActual, Posicion posicion, Contenedor solucion){
		//llamaa cjntRestricciones.satisfase restricciones
		CjtoRestricciones cjntRestricciones;
		//cjntRestricciones(contenidoActual, )
	}

}