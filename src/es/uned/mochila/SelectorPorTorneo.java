package es.uned.mochila;

import java.util.Arrays;
import java.util.Random;

public class SelectorPorTorneo extends SelectorPadres {
	
	private Individuo[] poblacion=null;
	
	protected SelectorPorTorneo(ProblemaMochila problema){
		this.setProblema(problema);
		
		
	}
	
	public Individuo[] getMatingPool(Individuo[] poblacion){
		Individuo[] seleccionados=new Individuo[getProblema().getTamanoPoblacion()*2];
		this.poblacion=poblacion;
		
		for (int i=0;i<seleccionados.length;i++){
			//Conducimos un torneo para seleccionar cada padre
			seleccionados[i]=torneo(getProblema().getTamanoTorneo());
			
		}
		
		return seleccionados;
	}

	private Individuo torneo (int tamanoTorneo){
		Individuo[] padresPotenciales=new Individuo[tamanoTorneo];
		
		for (int i=0;i<tamanoTorneo;i++){
			
			int indice=new Random().nextInt(getProblema().getTamanoPoblacion());
			System.out.println("Indice aleatorio: "+indice);
			
			padresPotenciales[i]=poblacion[indice];
		}
		
		//TODO Ojo, mira esto. Ordenar todo el array para buscar el máximo parece un poco bruto
		
		Arrays.sort(padresPotenciales, 
				(first,second) -> Double.compare(first.getFitness(), second.getFitness())*-1);
		
		//El ganador es el que sale primero después de ordenar
		return padresPotenciales[0];
		
	}
	

}
