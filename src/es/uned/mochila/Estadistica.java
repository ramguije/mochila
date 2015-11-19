package es.uned.mochila;

public class Estadistica {
	private double valorMedio;
	private double mejorValor;
	private int mejorIndice;
	private String printableGenotipo=null;
	
	public Estadistica(double valorMedio, double mejorValor, int mejorIndice, String printableGenotipo){
		this.mejorValor=mejorValor;
		this.valorMedio=valorMedio;
		this.mejorIndice=mejorIndice;
		this.printableGenotipo=printableGenotipo;
	}
	
	public double getValorMedio() {
		return valorMedio;
	}
	public double getMejorValor() {
		return mejorValor;
	}
	
	public int getMejorIndice(){
		return mejorIndice;
	}
	
	public String getPrintableGenotipo(){
		return printableGenotipo;
	}
	
	public void imprimir(){
		System.out.println("valor medio fitness: "+valorMedio);
		System.out.println("mejor fitness: "+mejorValor);
		System.out.println("indice elemento con mejor fitness: "+mejorIndice);
		System.out.println("genotipo mejor fitness: ");
		System.out.println(printableGenotipo);
		System.out.println("");
	}
	

}
