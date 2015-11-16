package es.uned.mochila;

public class Estadistica {
	double valorMedio;
	double mejorValor;
	
	public Estadistica(double valorMedio, double mejorValor){
		this.mejorValor=mejorValor;
		this.valorMedio=valorMedio;
	}
	
	public double getValorMedio() {
		return valorMedio;
	}
	public double getMejorValor() {
		return mejorValor;
	}
	
	public void imprimir(){
		System.out.println("valor medio de los fenotipos: "+valorMedio);
		System.out.println("mejor valor de los fenotipos: "+mejorValor);
	}
	

}
