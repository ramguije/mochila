package es.uned.mochila;

import java.util.ArrayList;
import java.util.Random;

public class CrucePorUnPunto extends CrucePadres {

	protected CrucePorUnPunto (ProblemaMochila p){
		this.setProblema(p);
	}
	
	@Override
	protected Individuo[] getDescendencia(Individuo[] matingPool) {
		Individuo[] descendencia=new Individuo[getProblema().getTamanoPoblacion()];
		
		for (int i=0, j=0;i<matingPool.length;i=i+2, j++){
			descendencia[j]=cruceUnPunto(matingPool[i],matingPool[i+1]);
		}
		
		return null;
	}
	
	private Individuo cruceUnPunto (Individuo ind1, Individuo ind2){
		boolean[] nuevoGenotipo=new boolean[getProblema().getNumObjetos()];
		
		int punto=(new Random().nextInt(getProblema().getNumObjetos()-1))+1;
		
		//Compruebo los límites del punto ([1,numObjs-1])
		if (punto<1||punto>(getProblema().getNumObjetos()-1)){
			System.out.println("ERROR!!! - HAS CALCULADO MAL EL PUNTO DEL CROSSOVER "+punto);
			return null;
		}
		
		for (int i=0;i<getProblema().getNumObjetos();i++){
			if (i<punto){
				nuevoGenotipo[i]=ind1.getGenotipo()[i];
			}else{
				nuevoGenotipo[i]=ind2.getGenotipo()[i];
			}
			
		}
		
		return new Individuo(nuevoGenotipo);
		
	}
	
	

}
