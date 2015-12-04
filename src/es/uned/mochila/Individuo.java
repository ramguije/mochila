package es.uned.mochila;

import java.util.Arrays;

/**
 * Clase que representa a un individuo.
 * @author Jes�s Ramos Guillou
 *
 */
public class Individuo {
	
	//Para implementar el genotipo binario he elegido un array de booleanos. 
	private boolean[] genotipo=null;
	private double fitness; //Valor de la funci�n fitness que tiene el individuo.
	
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
	
	
	/**
	 * Realiza las mutaciones en el individuo.
	 * @param promedioMutaciones Par�metro establecido con el promedio de mutaciones en un s�lo individuo.
	 */
	public void mutacion(double promedioMutaciones){
		//Calcula la mutaci�n.
		double probabilidadMutacion=(promedioMutaciones/genotipo.length);
		
		for (int i=0;i<genotipo.length;i++){
			double a=Math.random();
			if (a<probabilidadMutacion){
				//Se da la mutaci�n, hago un cambio de valor del alelo
				genotipo[i]=(!genotipo[i]);
			}
		}
	}
	

	/**
	 * transforma el genotipo booleano en un String de unos y ceros. Fundamentalmente para
	 * tareas de depuraci�n.
	 * @return Cadena de unos y ceros.
	 */
	public String getPrintableGenotipo() {
		
		StringBuffer representacionGenotipo=new StringBuffer("");
		
		for (int i=0;i<genotipo.length;i++){
			representacionGenotipo.append(genotipo[i]?"1":"0");
		}
		
		return representacionGenotipo.toString();
	}
	
	public boolean[] getGenotipo() {
		return genotipo;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
}
