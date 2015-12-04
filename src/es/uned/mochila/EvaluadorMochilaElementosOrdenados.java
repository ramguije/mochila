package es.uned.mochila;

import java.util.Arrays;

/**
 * Clase EvaluadorMochilaElementosOrdenados
 * @author Jes�s Ramos
 * La clase se encarga de implementar la funci�n fitness y 
 * de mantener los valores a trav�s de las diferentes ejecuciones. 
 * Valor medio y valor m�ximo de fitness de cada generaci�n.
 */
public class EvaluadorMochilaElementosOrdenados extends Evaluador {

	private Integer[] ordenObjetos=null;
	private Objeto[] objetos=null;
	private double capacidadMochila;
	
	public EvaluadorMochilaElementosOrdenados(EjecutorProblemaMochila p){
		this.capacidadMochila=p.getCapacidadMochila();
		
		this.objetos=p.getObjetos();
		inicializar();
		
	}
	
	
	private void inicializar() {
		//Genero un array de �ndices que ser� el que ordene para no alterar el orden real de los objetos.
		ordenObjetos=new Integer[objetos.length];
		for (int i=0;i<ordenObjetos.length;i++){
			ordenObjetos[i]=i;
		}
		
		//Expresi�n lambda para ordenar el array en funci�n del ratio.
		Arrays.sort(ordenObjetos, 
				(first,second) -> Double.compare(objetos[first].getRatio(), objetos[second].getRatio())*-1);
	}

	//Eval�a una colecci�n de Individuos.
	public void evaluar(Individuo[] elementos) {
		
		//reseteo el contador de evaluaciones
		this.setNumEvaluacionesUltimaGeneracion(0);
		
		for (Individuo ind:elementos){
			//Para cada elemento calculo su funci�n fitness y se la a�ado
			ind.setFitness(this.funcionFitness(ind));	
		}
	}
	
	//M�todo que implementa la funci�n Fitness.
	private double funcionFitness(Individuo ind)
	{
		boolean[] genotipo=ind.getGenotipo();
		double totalVolumen=0;
		double totalValor=0;
		boolean mochilaLlena=false;
		
		//Mientras no se hayan recorrido todos los objetos y la mochila no est� exactamente llena
		for (int i=0;i<ordenObjetos.length&&!mochilaLlena;i++){
			//Si el genotipo en esa posici�n es "1" y el volumen del objeto cabe en la mochila
			if ((genotipo[ordenObjetos[i]]==true) && 
					((totalVolumen+this.objetos[ordenObjetos[i]].getVolumen())<=this.capacidadMochila)){
				//sumo valor y sumo volumen.
				totalValor=totalValor+this.objetos[ordenObjetos[i]].getValor();
				totalVolumen=totalVolumen+this.objetos[ordenObjetos[i]].getVolumen();
				
				//Compruebo si la mochila est� llena.
				if (totalVolumen==this.capacidadMochila) 
					mochilaLlena=true;		
			}
			
		}
		this.setNumEvaluacionesTotales(this.getNumEvaluacionesTotales()+1);
		this.setNumEvaluacionesUltimaGeneracion(this.getNumEvaluacionesUltimaGeneracion()+1);
		
		return totalValor;
		
	}
	

}
