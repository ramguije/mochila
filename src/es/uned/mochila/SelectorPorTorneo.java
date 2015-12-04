package es.uned.mochila;

import java.util.Random;

/**
 * Clase concreta con la estrategia de selección de padres. En este caso, selección por torneo.
 * @author Jesús Ramos Guillou
 *
 */
public class SelectorPorTorneo extends SelectorPadres {
	
	private Individuo[] poblacion=null;
	
	protected SelectorPorTorneo(EjecutorProblemaMochila ejecutorProblema){
		this.setEjecutorProblema(ejecutorProblema);

	}

	/**
	 * Genera el mating pool con los individuos que serán padres.
	 */
	public Individuo[] getMatingPool(Individuo[] poblacion){
		Individuo[] seleccionados=new Individuo[getEjecutorProblema().getTamanoPoblacion()];
		this.poblacion=poblacion;
		
		for (int i=0;i<seleccionados.length;i++){
			//Conduzco un torneo para seleccionar cada padre
			seleccionados[i]=torneo(getEjecutorProblema().getTamanoTorneo());
			
		}
		
		return seleccionados;
	}

	/**
	 * Implementa la ejecución del torneo.
	 * @param tamanoTorneo Número de individuos que participarán en el torneo.
	 * @return El ganador del torneo.
	 */
	private Individuo torneo (int tamanoTorneo){
		Individuo[] padresPotenciales=new Individuo[tamanoTorneo];
		double max=0;
		int indMax=0;
		
		for (int i=0;i<tamanoTorneo;i++){
			// Selecciono aleatoriamente un número de individuos igual al tamaño del torneo 
			// de entre la población
			int indice=new Random().nextInt(getEjecutorProblema().getTamanoPoblacion());
			
			padresPotenciales[i]=poblacion[indice];
			
		}
		
		//Busco el que tiene mayor fitness que será el ganador.
		for (int i=0;i<padresPotenciales.length;i++){
			if (padresPotenciales[i].getFitness()>max){
				max=padresPotenciales[i].getFitness();
				indMax=i;
			}
		}
		
		return padresPotenciales[indMax];
		
	}
	

}
