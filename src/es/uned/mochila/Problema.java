package es.uned.mochila;

import java.io.IOException;
import java.io.PrintWriter;

public class Problema {
	//Constantes para la generación de problemas de la mochila binaria
	public static final int MIN_MOCHILA_FACIL=100;
	public static final int MAX_MOCHILA_FACIL=10000;
	public static final int NUM_OBJETOS_FACIL=100;
	
	public static final int MIN_MOCHILA_DIFICIL=10000;
	public static final int MAX_MOCHILA_DIFICIL=1000000;
	public static final int NUM_OBJETOS_DIFICIL=10000;
	
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
	
	
	public Problema(double capacidadMochila, int numObjetos, boolean intervalos){
		this.capacidadMochila=capacidadMochila;
		this.numObjetos=numObjetos;
		
		objetos=new Objeto[numObjetos];
		
		for (int i=0;i<objetos.length;i++)
		{
			objetos[i]=new Objeto(intervalos);
		}
		
	}
	
	public static Problema generarProblemaAleatorio(char tipoProblema){
		int minMochila=0;
		int maxMochila=0;
		int numObjetos=0;
		boolean intervalos=false;
		
		if (tipoProblema=='s'||tipoProblema=='S'){
			minMochila=MIN_MOCHILA_FACIL;
			maxMochila=MAX_MOCHILA_FACIL;
			numObjetos=NUM_OBJETOS_FACIL;
		}else{
			minMochila=MIN_MOCHILA_DIFICIL;
			maxMochila=MAX_MOCHILA_DIFICIL;
			numObjetos=NUM_OBJETOS_DIFICIL;
			if (tipoProblema=='i'||tipoProblema=='I') 
				intervalos=true;
		}
		
		return new Problema(generarCapacidadMochila(minMochila, maxMochila), numObjetos, intervalos);
	}
	
	private static double generarCapacidadMochila(int minCapacidad, int maxCapacidad) {
		//Generar aleatoriamente la capacidad de la mochila
		return Math.random() * (maxCapacidad-minCapacidad) + minCapacidad;
		
	}
	
	/**
	 * Método encargado de escribir la instancia de problema a disco con el formato definido:
	 * --------------------------------
	 * #Se permiten comentarios que empiecen por almohadilla
	 * <capacidad de la mochila>
	 * <numero de objetos>
	 * <volumen objeto 1> <valor objeto 1>
	 * <volumen objeto 2> <valor objeto 2>
	 * ...
	 * <volumen objeto n> <valor objeto n>
	 * 
	 */
	public boolean AFichero(String nombre){
		PrintWriter writer=null;
		boolean correcto=false;
		
		try{
			writer = new PrintWriter(nombre, "UTF-8");
		
			writer.println("#Capacidad de la mochila");
			writer.println(this.getCapacidadMochila());
			writer.println("#Num. de objetos");
			writer.println(this.getNumObjetos());
			writer.println("#Volumen valor de los objetos");
			for (int i=0;i<objetos.length;i++){
				writer.println(objetos[i].getVolumen()+" "+objetos[i].getValor());
			}
			writer.close();
			System.out.println("Fichero generado correctamente.");
			correcto=true;
		}
		catch(IOException io)
		{
			System.out.println("ERROR - No se ha podido escribir en el fichero.");
			System.out.println("Mensaje: "+io.getMessage());
		}
		
		return correcto;
		
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
