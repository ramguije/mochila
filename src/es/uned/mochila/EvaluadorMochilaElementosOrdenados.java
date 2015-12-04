package es.uned.mochila;

import java.util.Arrays;

/**
 * Clase EvaluadorMochilaElementosOrdenados
 * @author Jesús Ramos
 * La clase se encarga de implementar la función fitness y 
 * de mantener los valores a través de las diferentes ejecuciones. 
 * Valor medio y valor máximo de fitness de cada generación.
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
		//Genero un array de índices que será el que ordene para no alterar el orden real de los objetos.
		ordenObjetos=new Integer[objetos.length];
		for (int i=0;i<ordenObjetos.length;i++){
			ordenObjetos[i]=i;
		}
		
		//Expresión lambda para ordenar el array en función del ratio.
		Arrays.sort(ordenObjetos, 
				(first,second) -> Double.compare(objetos[first].getRatio(), objetos[second].getRatio())*-1);
	}

	//Evalúa una colección de Individuos.
	public void evaluar(Individuo[] elementos) {
		
		//reseteo el contador de evaluaciones
		this.setNumEvaluacionesUltimaGeneracion(0);
		
		for (Individuo ind:elementos){
			//Para cada elemento calculo su función fitness y se la añado
			ind.setFitness(this.funcionFitness(ind));	
		}
	}
	
	//Método que implementa la función Fitness.
	private double funcionFitness(Individuo ind)
	{
		boolean[] genotipo=ind.getGenotipo();
		double totalVolumen=0;
		double totalValor=0;
		boolean mochilaLlena=false;
		
		//Mientras no se hayan recorrido todos los objetos y la mochila no esté exactamente llena
		for (int i=0;i<ordenObjetos.length&&!mochilaLlena;i++){
			//Si el genotipo en esa posición es "1" y el volumen del objeto cabe en la mochila
			if ((genotipo[ordenObjetos[i]]==true) && 
					((totalVolumen+this.objetos[ordenObjetos[i]].getVolumen())<=this.capacidadMochila)){
				//sumo valor y sumo volumen.
				totalValor=totalValor+this.objetos[ordenObjetos[i]].getValor();
				totalVolumen=totalVolumen+this.objetos[ordenObjetos[i]].getVolumen();
				
				//Compruebo si la mochila está llena.
				if (totalVolumen==this.capacidadMochila) 
					mochilaLlena=true;		
			}
			
		}
		this.setNumEvaluacionesTotales(this.getNumEvaluacionesTotales()+1);
		this.setNumEvaluacionesUltimaGeneracion(this.getNumEvaluacionesUltimaGeneracion()+1);
		
		return totalValor;
		
	}
	

}
