package es.uned.mochila;


/**
 * Esta clase mantiene la configuración que se va a usar para resolver el problema de la mochila.
 * @author Jesús Ramos Guillou
 */
public class Configuracion {
	
	//El número de ejecuciones del problema
	private int numEjecuciones;
	
	//El tamaño de torneo a utilizar
	private int tamanioTorneo;

	//El tamaño de la poblacion
	private int tamanioPoblacion;
	
	//El número máximo de generaciones que se permiten
	private int maxGeneraciones;

	public Configuracion(int numEjecuciones, int tamanioTorneo, int tamanioPoblacion, int maxGeneraciones){
		this.maxGeneraciones=maxGeneraciones;
		this.numEjecuciones=numEjecuciones;
		this.tamanioPoblacion=tamanioPoblacion;
		this.tamanioTorneo=tamanioTorneo;
	}

	public int getNumEjecuciones() {
		return numEjecuciones;
	}

	public int getTamanioTorneo() {
		return tamanioTorneo;
	}

	public int getTamanioPoblacion() {
		return tamanioPoblacion;
	}

	public int getMaxGeneraciones() {
		return maxGeneraciones;
	}

}



