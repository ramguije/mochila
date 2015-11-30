package es.uned.mochila;

public class Objeto {
	private double valor;
	private double volumen;
	
	public static final int MIN_RANGO=1;
	public static final int MAX_RANGO=100;
	public static final int NUM_INTERVALOS_VALOR=4;
	public static final int NUM_INTERVALOS_DENSIDAD=3;
	public static final int MIN_INTERVALO_1=10;
	public static final int MAX_INTERVALO_1=20;
	public static final int MIN_INTERVALO_2=30;
	public static final int MAX_INTERVALO_2=40;
	public static final int MIN_INTERVALO_3=70;
	public static final int MAX_INTERVALO_3=80;
	public static final int MIN_INTERVALO_4=90;
	public static final int MAX_INTERVALO_4=100;
	
	public Objeto(char tipoProblema){
		//Volumen y valor generados aleatoriamente
		
		switch (Character.toLowerCase(tipoProblema)){
			case Problema.MOCHILA_SIMPLE:
			case Problema.MOCHILA_COMPLEJA:
				this.valor=Math.random() * (MAX_RANGO-MIN_RANGO) + MIN_RANGO;
				this.volumen=Math.random() * (MAX_RANGO-MIN_RANGO) + MIN_RANGO;
				break;
			case Problema.MOCHILA_COMPLEJA_INTERVALOS_VOLUMEN:
				//Cuatro intervalos en los que se puede dar un volumen
				this.valor=Math.random() * (MAX_RANGO-MIN_RANGO) + MIN_RANGO;
				double intervalo=Math.random();
				double limIntervalos=(1.0/Objeto.NUM_INTERVALOS_VALOR);
				
				if (intervalo>(limIntervalos*3)){
					this.volumen=Math.random() * (MAX_INTERVALO_4-MIN_INTERVALO_4) + MIN_INTERVALO_4;
				}else if (intervalo>(limIntervalos*2)){
					this.volumen=Math.random() * (MAX_INTERVALO_3-MIN_INTERVALO_3) + MIN_INTERVALO_3;
				}else if (intervalo>(limIntervalos)){
					this.volumen=Math.random() * (MAX_INTERVALO_2-MIN_INTERVALO_2) + MIN_INTERVALO_2;
				}else{
					this.volumen=Math.random() * (MAX_INTERVALO_1-MIN_INTERVALO_1) + MIN_INTERVALO_1;
				}
				break;
			case Problema.MOCHILA_COMPLEJA_INTERVALOS_DENSIDAD:
				//tres valores de densidad
				this.volumen=Math.random() * (MAX_RANGO-MIN_RANGO) + MIN_RANGO;
				double intervaloDensidad=Math.random();
				double limIntervalosDensidad=(1.0/Objeto.NUM_INTERVALOS_DENSIDAD);
				
				if (intervaloDensidad>(limIntervalosDensidad*2)){
					this.valor=this.volumen;
				}else if (intervaloDensidad>(limIntervalosDensidad)){
					this.valor=this.volumen/2;
				}else{
					this.valor=this.volumen/10;
				}
				break;
			case Problema.MOCHILA_COMPLEJA_CORRELACION_VOLUMEN_VALOR:
				//Usando el mecanismo definido como "Weakly Correlated Instances" en Bansal_2015
				this.volumen=Math.random() * (MAX_RANGO-MIN_RANGO) + MIN_RANGO;
				
				double minValor=this.volumen-(MAX_RANGO/10);
				if (minValor<MIN_RANGO){
					minValor=MIN_RANGO;
				}
				
				double maxValor=this.volumen+(MAX_RANGO/10);
				if (maxValor>MAX_RANGO){
					maxValor=MAX_RANGO;
				}
				
				this.valor=Math.random() * (maxValor-minValor) + minValor;
				
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
