package es.uned.mochila;

import java.util.Arrays;


//TODO: Conviene hace un indivio genérico?? Tengo una interfaz común??
public class Individuo {
	//De momento lo he implementado así, alguna forma mejor??
	private boolean[] genotipo=null;
	private double fitness;
	
	public Individuo(int numObjetos){
		//Generar aleatoriamente el genotipo 
		genotipo=new boolean[numObjetos];
		
		for (int i=0;i<genotipo.length;i++)
		{
			genotipo[i]=Math.random()<0.5?true:false;
		}
	}
	
	public Individuo(boolean[] gen){
		this.genotipo=gen;
	}

	public boolean[] getGenotipo() {
		return genotipo;
	}
	
	public String getPrintableGenotipo() {
		
		StringBuffer representacionGenotipo=new StringBuffer("");
		
		for (int i=0;i<genotipo.length;i++){
			representacionGenotipo.append(genotipo[i]?"1":"0");
		}
		
		return representacionGenotipo.toString();
	}
	
	public void mutacion(double probabilidadMutacion){
		//Calcula la mutación.
	
		for (int i=0;i<genotipo.length;i++){
			
			double a=Math.random();
			if (a<probabilidadMutacion){
				//Se da la mutación, hago un cambio de valor del alelo
				//System.out.println("MUTACIÓN!!");
				genotipo[i]=(!genotipo[i]);
				
			}
			
		}
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
}
