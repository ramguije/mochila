package es.uned.mochila;

public abstract class Evaluador {
	
	//Ojo, esta referencia habría que hacerla a una clase abstracta o interfaz
	private EjecutorProblemaMochila problema=null;
	//Referencia a la instancia Singleton 
	private static Evaluador instancia=null;
	
	private int numEvaluacionesTotales;
	private int numEvaluacionesUltimaGeneracion;

	public static Evaluador getEvaluador(EjecutorProblemaMochila p)
	{
		if (instancia==null){
			instancia=new EvaluadorMochilaElementosOrdenados(p);
		}
		
		return instancia;
	}
	
	//public void inicializar(Objeto[] objetos);
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
				elementos[mejorIndice].getPrintableGenotipo(), 
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
