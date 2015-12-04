package es.uned.mochila;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implementación del mecanismo de cruce: cruce por un punto entre los individuos que hay en el mating pool.
 * @author Jesús Ramos Guillou
 */
public class CrucePorUnPunto extends CrucePadres {

	protected CrucePorUnPunto (EjecutorProblemaMochila p){
		this.setEjecutorProblema(p);
	}
	
	/**
	 * Calcula los descendientes a partir del mating pool.
	 */
	protected Individuo[] getDescendencia(Individuo[] matingPool) {
		Individuo[] descendencia=new Individuo[getEjecutorProblema().getTamanoPoblacion()];
		
		for (int i=0;i<matingPool.length;i=i+2){
			
			//Genera el valor que se usa para comparar con la probabilidad de cruce.
			double comparadorProbabilidadCruce=Math.random();
			
			//Si la población es impar el último elemento del matingpool no se cruza.
			if (i+1==matingPool.length){
				descendencia[i]=new Individuo(matingPool[i].getGenotipo().clone());
				
			}//Si se da el cruce
			else if (comparadorProbabilidadCruce<this.getEjecutorProblema().getProbabilidadCruce()){

				boolean[] nuevoGenotipo1=new boolean[getEjecutorProblema().getNumObjetos()];			
				boolean[] nuevoGenotipo2=new boolean[getEjecutorProblema().getNumObjetos()];
				
				int punto=(new Random().nextInt(getEjecutorProblema().getNumObjetos()-1))+1;
				
				//Compruebo los límites del punto ([1,numObjs-1])
				if (punto<1||punto>(getEjecutorProblema().getNumObjetos()-1)){
					System.out.println("ERROR!!! - SE HA PRODUCIDO UN ERROR EN EL CÁLCULO DEL PUNTO DEL CROSSOVER "+punto);
					return null;
				}
				
				for (int j=0;j<nuevoGenotipo1.length;j++){
					//Realizo el cruce del genotipo.
					if (j<punto){
						nuevoGenotipo1[j]=matingPool[i].getGenotipo()[j];
						nuevoGenotipo2[j]=matingPool[i+1].getGenotipo()[j];
					}else{
						nuevoGenotipo1[j]=matingPool[i+1].getGenotipo()[j];
						nuevoGenotipo2[j]=matingPool[i].getGenotipo()[j];
					}
				}
				
				descendencia[i]=new Individuo(nuevoGenotipo1);
				descendencia[i+1]=new Individuo(nuevoGenotipo2);
			}//Si no se da el cruce, directamente se clonan los padres.
			else{
				descendencia[i]=new Individuo(matingPool[i].getGenotipo().clone());
				descendencia[i+1]=new Individuo(matingPool[i].getGenotipo().clone());
			}
			
		}
		
		return descendencia;
	}

}
