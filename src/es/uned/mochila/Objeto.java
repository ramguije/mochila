package es.uned.mochila;

public class Objeto {
	private double valor;
	private double volumen;
	public static final int MIN_RANGO=1;
	public static final int MAX_RANGO=100;
	public static final int NUM_INTERVALOS=4;
	public static final int MIN_INTERVALO_1=10;
	public static final int MAX_INTERVALO_1=20;
	public static final int MIN_INTERVALO_2=30;
	public static final int MAX_INTERVALO_2=40;
	public static final int MIN_INTERVALO_3=70;
	public static final int MAX_INTERVALO_3=80;
	public static final int MIN_INTERVALO_4=90;
	public static final int MAX_INTERVALO_4=100;
	
	public Objeto(boolean intervalos){
		//Volumen y valor generados aleatoriamente
		//this.valor=Math.random() * (MAX_RANGO-MIN_RANGO) + MIN_RANGO;
		if (intervalos){
			double intervalo=Math.random();
			double limIntervalos=(1.0/Objeto.NUM_INTERVALOS);
			
			if (intervalo>(limIntervalos*3)){
				this.volumen=Math.random() * (MAX_INTERVALO_4-MIN_INTERVALO_4) + MIN_INTERVALO_4;
				this.valor=Math.random() * (MAX_INTERVALO_4-MIN_INTERVALO_4) + MIN_INTERVALO_4;
			}else if (intervalo>(limIntervalos*2)){
				this.volumen=Math.random() * (MAX_INTERVALO_3-MIN_INTERVALO_3) + MIN_INTERVALO_3;
				this.valor=Math.random() * (MAX_INTERVALO_3-MIN_INTERVALO_3) + MIN_INTERVALO_3;
			}else if (intervalo>(limIntervalos)){
				this.volumen=Math.random() * (MAX_INTERVALO_2-MIN_INTERVALO_2) + MIN_INTERVALO_2;
				this.valor=Math.random() * (MAX_INTERVALO_2-MIN_INTERVALO_2) + MIN_INTERVALO_2;
			}else{
				this.volumen=Math.random() * (MAX_INTERVALO_1-MIN_INTERVALO_1) + MIN_INTERVALO_1;
				this.valor=Math.random() * (MAX_INTERVALO_1-MIN_INTERVALO_1) + MIN_INTERVALO_1;
			}
			
		}else{
			this.volumen=Math.random() * (MAX_RANGO-MIN_RANGO) + MIN_RANGO;
			this.valor=Math.random() * (MAX_RANGO-MIN_RANGO) + MIN_RANGO;
		}
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
