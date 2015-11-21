package es.uned.mochila;

public class Objeto {
	private double valor;
	private double volumen;
	
	public Objeto(){
		//Generado aleatoriamente
		int minValor=1;
		int maxValor=100;
		
		this.valor=Math.random() * (maxValor-minValor) + minValor;
		this.volumen=Math.random() * (maxValor-minValor) + minValor;
	}
	
	public Objeto(double valor, double volumen){
		this.valor=valor;
		this.volumen=volumen;
	}
	
	public double getValor() {
		return valor;
	}

	public double getVolumen() {
		return volumen;
	}

	public double getRatio(){
		return valor/volumen;
	}
	
}
