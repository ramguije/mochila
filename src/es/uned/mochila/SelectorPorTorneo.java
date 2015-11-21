package es.uned.mochila;

import java.util.Random;

public class SelectorPorTorneo extends SelectorPadres {
	
	private Individuo[] poblacion=null;
	
	protected SelectorPorTorneo(EjecutorProblemaMochila problema){
		this.setProblema(problema);
		
		
	}
	
	public Individuo[] getMatingPool(Individuo[] poblacion){
		Individuo[] seleccionados=new Individuo[getProblema().getTamanoPoblacion()];
		this.poblacion=poblacion;
		
		for (int i=0;i<seleccionados.length;i++){
			//Conducimos un torneo para seleccionar cada padre
			seleccionados[i]=torneo(getProblema().getTamanoTorneo());
			
		}
		
		/*for (int i=0;i<seleccionados.length;i++){
			//Conducimos un torneo para seleccionar cada padre
			System.out.println("Padre "+i+" fitness: "+seleccionados[i].getFitness());
			
		}*/
		return seleccionados;
	}

	private Individuo torneo (int tamanoTorneo){
		Individuo[] padresPotenciales=new Individuo[tamanoTorneo];
		double max=0;
		int indMax=0;
		
		for (int i=0;i<tamanoTorneo;i++){
			
			int indice=new Random().nextInt(getProblema().getTamanoPoblacion());
			//System.out.println("Indice aleatorio: "+indice);
			
			padresPotenciales[i]=poblacion[indice];
			
			//System.out.println("Padre: "+i+", valor fitness: "+padresPotenciales[i].getFitness());
		}
		
		for (int i=0;i<padresPotenciales.length;i++){
			if (padresPotenciales[i].getFitness()>max){
				max=padresPotenciales[i].getFitness();
				indMax=i;
			}
		}
		
		//System.out.println("Ganador: "+indMax+", valor fitness: "+padresPotenciales[indMax].getFitness());
		return padresPotenciales[indMax];
		
	}
	

}
