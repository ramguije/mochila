package es.uned.mochila;

public class Problema {
	private double capacidadMochila;
	private int numObjetos;
	private Objeto[] objetos=null;


	public Problema(double capacidadMochila, int numObjetos, double[] valores, double[] volumenes){
		this.capacidadMochila=capacidadMochila;
		this.numObjetos=numObjetos;
		
		objetos=new Objeto[numObjetos];
		
		for (int i=0;i<objetos.length;i++){
			objetos[i]=new Objeto(valores[i], volumenes[i]);
		}
		
	}
	
	
	public Problema(double capacidadMochila, int numObjetos){
		this.capacidadMochila=capacidadMochila;
		this.numObjetos=numObjetos;
		
		objetos=new Objeto[numObjetos];
		
		for (int i=0;i<objetos.length;i++)
		{
			objetos[i]=new Objeto();
		}
		
	}
	
	public double getCapacidadMochila() {
		return capacidadMochila;
	}


	public int getNumObjetos() {
		return numObjetos;
	}


	public Objeto[] getObjetos() {
		return objetos;
	}
	
}
