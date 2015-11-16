package es.uned.mochila;

import java.util.Arrays;

public class Individuo {
	//De momento lo he implementado as�, alguna forma mejor??
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
	
	public void mutacion(double probabilidadMutacion){
		//Calcula la mutaci�n.
	
		for (int i=0;i<genotipo.length;i++){
			
			double a=Math.random();
			if (a<probabilidadMutacion){
				//Se da la mutaci�n, hago un cambio de valor del alelo
				//System.out.println("MUTACI�N!!");
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
