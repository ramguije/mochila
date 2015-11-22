package es.uned.mochila;


/**
 * Esta clase mantiene la configuraci�n que se va a usar para resolver el problema de la mochila.
 * @author Jes�s Ramos Guillou
 */
public class Configuracion {
	
	//El n�mero de ejecuciones del problema
	private int numEjecuciones;
	
	//El tama�o de torneo a utilizar
	private int tamanioTorneo;

	//El tama�o de la poblacion
	private int tamanioPoblacion;
	
	//El n�mero m�ximo de generaciones que se permiten
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



