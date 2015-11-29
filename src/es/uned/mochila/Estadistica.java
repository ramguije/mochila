package es.uned.mochila;

public class Estadistica {
	private double fitnessMedio;
	private double mejorFitness;
	private int indiceMejorElemento; //El índice al elemento de la población con mejor fitness
	private String printableGenotipo=null; //El genotipo en un formato ya preparado para imprimir
	private int numEvaluaciones; //número de evaluaciones de la función fitness
	
	public Estadistica(double valorMedio, double mejorValor, int mejorIndice, String printableGenotipo, int numEvaluaciones){
		this(valorMedio, mejorValor, mejorIndice, numEvaluaciones);
		
		this.printableGenotipo=printableGenotipo;
	}
	
	public Estadistica(double valorMedio, double mejorValor, int mejorIndice, int numEvaluaciones){
		this.mejorFitness=mejorValor;
		this.fitnessMedio=valorMedio;
		this.indiceMejorElemento=mejorIndice;
		this.numEvaluaciones=numEvaluaciones;
	}
	
	public double getFitnessMedio() {
		return fitnessMedio;
	}
	public double getMejorFitness() {
		return mejorFitness;
	}
	
	public int getMejorIndice(){
		return indiceMejorElemento;
	}
	
	public String getPrintableGenotipo(){
		return printableGenotipo;
	}
	
	public int getNumEvaluaciones(){
		return numEvaluaciones;
	}
	
	public void imprimir(){
		System.out.println("valor medio fitness: "+getFitnessMedio());
		System.out.println("mejor fitness: "+getMejorFitness());
		//System.out.println("indice elemento con mejor fitness: "+getMejorIndice());
		//System.out.println("genotipo mejor fitness: ");
		//System.out.println(getPrintableGenotipo());
		System.out.println("");
	}
	

}
