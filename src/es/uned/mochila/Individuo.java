package es.uned.mochila;

public class Individuo {
	//De momento lo he implementado así, alguna forma mejor??
	private boolean[] genotipo=null;
	
	public Individuo(int numObjetos){
		//Generar aleatoriamente el genotipo 
		genotipo=null;
	}
	
	public Individuo(boolean[] gen){
		this.genotipo=gen;
	}

	public boolean[] getGenotipo() {
		return genotipo;
	}
	
	public void mutacion(){
		//Calcula la mutación.
	}
}
