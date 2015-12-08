package es.uned.mochila;

/**
 * Clase abstracta que se encarga de evaluar a los diferentes individuos y 
 * de calcular la estadística de cada generación
 * @author Jesús Ramos Guillou
 */
public abstract class Evaluador {
	
	private int numEvaluacionesTotales;
	private int numEvaluacionesUltimaGeneracion;

	public static Evaluador getNuevoEvaluador(EjecutorProblemaMochila p)
	{
		return new EvaluadorMochilaElementosOrdenados(p);
	}
	
	//Método abstracto. A reescribir en clases que extiendan a ésta. 
	public abstract void evaluar(Individuo[] elementos);
	
	public Estadistica calculaEstadistica(Individuo[] elementos){
		double valorMedio=0;
		double mejorValor=0;
		double sumValorMedio=0;
		int mejorIndice=0;
		
		for (int i=0;i<elementos.length;i++){
			sumValorMedio=sumValorMedio+elementos[i].getFitness();
			
			if (elementos[i].getFitness()>mejorValor){
				mejorValor=elementos[i].getFitness();
				mejorIndice=i;
			}
			
		}
		
		valorMedio=sumValorMedio/elementos.length;
		
		return new Estadistica(valorMedio, 
				mejorValor, mejorIndice, 
				getNumEvaluacionesTotales());
		
	}
	
	
	
	protected void setNumEvaluacionesTotales(int numEvaluacionesTotales) {
		this.numEvaluacionesTotales = numEvaluacionesTotales;
	}

	protected void setNumEvaluacionesUltimaGeneracion(int numEvaluacionesUltimaGeneracion) {
		this.numEvaluacionesUltimaGeneracion = numEvaluacionesUltimaGeneracion;
	}

	public int getNumEvaluacionesTotales() {
		return numEvaluacionesTotales;
	}

	public int getNumEvaluacionesUltimaGeneracion() {
		return numEvaluacionesUltimaGeneracion;
	}
		
}
