package es.uned.mochila;

import java.util.ArrayList;
import java.util.Random;

public class CrucePorUnPunto extends CrucePadres {

	protected CrucePorUnPunto (EjecutorProblemaMochila p){
		this.setEjecutorProblema(p);
	}
	
	@Override
	protected Individuo[] getDescendencia(Individuo[] matingPool) {
		Individuo[] descendencia=new Individuo[getEjecutorProblema().getTamanoPoblacion()];
		
		for (int i=0;i<matingPool.length;i=i+2){
			
			//CRUCE POR UN PUNTO.
			double cruceAleatorio=Math.random();
			
			if (cruceAleatorio<this.getEjecutorProblema().getProbabilidadCruce()){

				boolean[] nuevoGenotipo1=new boolean[getEjecutorProblema().getNumObjetos()];			
				boolean[] nuevoGenotipo2=new boolean[getEjecutorProblema().getNumObjetos()];
				
				int punto=(new Random().nextInt(getEjecutorProblema().getNumObjetos()-1))+1;
				
				//Compruebo los límites del punto ([1,numObjs-1])
				if (punto<1||punto>(getEjecutorProblema().getNumObjetos()-1)){
					System.out.println("ERROR!!! - HAS CALCULADO MAL EL PUNTO DEL CROSSOVER "+punto);
					return null;
				}
				
				//debug imprimo los valores de los genotipos
				/*System.out.print("genotipo individuo 1: ");
				for (int j=0;j<matingPool[i].getGenotipo().length;j++){
					System.out.print(matingPool[i].getGenotipo()[j]?"1":"0");
					if (j==punto){
						System.out.print("|");
					}
				}
				System.out.println("");
				
				System.out.print("genotipo individuo 2: ");
				for (int j=0;j<matingPool[i+1].getGenotipo().length;j++){
					System.out.print(matingPool[i+1].getGenotipo()[j]?"1":"0");
					if (j==punto){
						System.out.print("|");
					}
				}
				System.out.println("");*/
				
				for (int j=0;j<nuevoGenotipo1.length;j++){
					
					if (j<punto){
						nuevoGenotipo1[j]=matingPool[i].getGenotipo()[j];
						nuevoGenotipo2[j]=matingPool[i+1].getGenotipo()[j];
					}else{
						nuevoGenotipo1[j]=matingPool[i+1].getGenotipo()[j];
						nuevoGenotipo2[j]=matingPool[i].getGenotipo()[j];
					}
					
				}
				
				/*System.out.print("nuevo genotipo 1: ");
				for (int j=0;j<nuevoGenotipo1.length;j++){
					System.out.print(nuevoGenotipo1[j]?"1":"0");
					if (j==punto){
						System.out.print("|");
					}
				}
				System.out.println("");
				
				System.out.print("nuevo genotipo 2: ");
				for (int j=0;j<nuevoGenotipo2.length;j++){
					System.out.print(nuevoGenotipo2[j]?"1":"0");
					if (j==punto){
						System.out.print("|");
					}
				}
				System.out.println("");*/
				
				
				descendencia[i]=new Individuo(nuevoGenotipo1);
				descendencia[i+1]=new Individuo(nuevoGenotipo2);
			}else{
				descendencia[i]=new Individuo(matingPool[i].getGenotipo().clone());
				descendencia[i+1]=new Individuo(matingPool[i].getGenotipo().clone());
			}
			
		}
		
		return descendencia;
	}

}
