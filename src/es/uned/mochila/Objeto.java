package es.uned.mochila;

public class Objeto {
	private float valor;
	private float volumen;
	
	public Objeto(float valor, float volumen){
		this.valor=valor;
		this.volumen=volumen;
	}
	
	public float getValor() {
		return valor;
	}

	public float getVolumen() {
		return volumen;
	}

	public float getRation(){
		return valor/volumen;
	}
	
}
