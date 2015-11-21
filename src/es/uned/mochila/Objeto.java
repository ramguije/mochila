package es.uned.mochila;

public class Objeto {
	private double valor;
	private double volumen;
	public static final int MIN_RANGO=1;
	public static final int MAX_RANGO=100;
	
	public Objeto(){
		//Volumen y valor generados aleatoriamente
		this.valor=Math.random() * (MAX_RANGO-MIN_RANGO) + MIN_RANGO;
		this.volumen=Math.random() * (MAX_RANGO-MIN_RANGO) + MIN_RANGO;
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
