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
		
		for (int i=0;i<matingPool.length;i=i+2){
			
			//CRUCE POR UN PUNTO.
			
			boolean[] nuevoGenotipo1=new boolean[getProblema().getNumObjetos()];
			boolean[] nuevoGenotipo2=new boolean[getProblema().getNumObjetos()];
			
			int punto=(new Random().nextInt(getProblema().getNumObjetos()-1))+1;
			
			//Compruebo los límites del punto ([1,numObjs-1])
			if (punto<1||punto>(getProblema().getNumObjetos()-1)){
				System.out.println("ERROR!!! - HAS CALCULADO MAL EL PUNTO DEL CROSSOVER "+punto);
				return null;
			}
			
			for (int j=0;j<nuevoGenotipo1.length;j++){
				if (i<punto){
					nuevoGenotipo1[i]=matingPool[i].getGenotipo()[i];
					nuevoGenotipo2[i]=matingPool[i+1].getGenotipo()[i];
				}else{
					nuevoGenotipo1[i]=matingPool[i+1].getGenotipo()[i];
					nuevoGenotipo2[i]=matingPool[i].getGenotipo()[i];
				}
				
			}
			
			descendencia[i]=new Individuo(nuevoGenotipo1);
			descendencia[i+1]=new Individuo(nuevoGenotipo2);
			
		}
		
		return descendencia;
	}

}
