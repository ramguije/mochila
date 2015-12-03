package es.uned.mochila;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase ProblemaMochila.
 * Representa un problema de la mochila binaria.
 * @author Jesús Ramos
 */
public class ProblemaMochila {
	//Constantes para la generación de problemas de la mochila binaria
	public static final int MIN_MOCHILA_FACIL=100;
	public static final int MAX_MOCHILA_FACIL=10000;
	public static final int NUM_OBJETOS_FACIL=100;
	
	public static final int MIN_MOCHILA_DIFICIL=10000;
	public static final int MAX_MOCHILA_DIFICIL=1000000;
	public static final int NUM_OBJETOS_DIFICIL=10000;
	
	//Tipos de problemas de la mochila.
	public static final char MOCHILA_SIMPLE='s';
	public static final char MOCHILA_COMPLEJA='c';
	public static final char MOCHILA_COMPLEJA_INTERVALOS_VOLUMEN='v';
	public static final char MOCHILA_COMPLEJA_INTERVALOS_DENSIDAD='d';
	public static final char MOCHILA_COMPLEJA_CORRELACION_VOLUMEN_VALOR='r';
	
	private double capacidadMochila;
	private int numObjetos;
	private Objeto[] objetos=null;


	public ProblemaMochila(double capacidadMochila, int numObjetos, double[] valores, double[] volumenes){
		this.capacidadMochila=capacidadMochila;
		this.numObjetos=numObjetos;
		
		objetos=new Objeto[numObjetos];
		
		for (int i=0;i<objetos.length;i++){
			objetos[i]=new Objeto(valores[i], volumenes[i]);
		}
		
	}
	
	
	public ProblemaMochila(double capacidadMochila, int numObjetos,char tipoProblema){
		this.capacidadMochila=capacidadMochila;
		this.numObjetos=numObjetos;
		
		objetos=new Objeto[numObjetos];
		
		for (int i=0;i<objetos.length;i++)
		{
			objetos[i]=new Objeto(tipoProblema);
		}
		
	}
	
	public static ProblemaMochila generarProblemaAleatorio(char tipoProblema){
		int minMochila=0;
		int maxMochila=0;
		int numObjetos=0;
		
		char tipoLower=Character.toLowerCase(tipoProblema);
		
		if (tipoLower==ProblemaMochila.MOCHILA_SIMPLE){
			minMochila=MIN_MOCHILA_FACIL;
			maxMochila=MAX_MOCHILA_FACIL;
			numObjetos=NUM_OBJETOS_FACIL;
		}else{
			minMochila=MIN_MOCHILA_DIFICIL;
			maxMochila=MAX_MOCHILA_DIFICIL;
			numObjetos=NUM_OBJETOS_DIFICIL;
		}
		
		return new ProblemaMochila(generarCapacidadMochila(minMochila, maxMochila), numObjetos, tipoLower);
	}
	
	private static double generarCapacidadMochila(int minCapacidad, int maxCapacidad) {
		//Generar aleatoriamente la capacidad de la mochila
		return Math.random() * (maxCapacidad-minCapacidad) + minCapacidad;
		
	}
	
	//TODO Si el problema se vuelca a fichero en la clase problema parece que tiene sentido que también se lea de fichero 
		//en esa clase.
	public static ProblemaMochila leerProblemaDeFichero(String nombreFicheroProblema) throws IllegalArgumentException{
		double capacidadMochila=0;
		int numElementos=0;
		double[] volumenes=null;
		double[] valores=null;
		ProblemaMochila problema=null;
		
		String cadena=null;
		FileReader f =null;
		BufferedReader b=null;
		
		int valorLeido=0;
		int lineaTratada=0;
		
		try{
			f = new FileReader(nombreFicheroProblema);
			b = new BufferedReader(f);
			
			
			while((cadena = b.readLine())!=null) {
				if (!cadena.startsWith("#")){
					if (valorLeido==0){
						capacidadMochila=Double.parseDouble(cadena);
					}
					else if (valorLeido==1){
						numElementos=Integer.parseInt(cadena);
						volumenes=new double[numElementos];
						valores=new double[numElementos];
					}
					else{
						String[] datosObjeto=cadena.split(" ");
						volumenes[valorLeido-2]=Double.parseDouble(datosObjeto[0]);
						valores[valorLeido-2]=Double.parseDouble(datosObjeto[1]);
					}
						
					valorLeido++;
				}
				lineaTratada++;
			}
			problema=new ProblemaMochila(capacidadMochila, numElementos, valores, volumenes);
			
			
			/*ficheroProblema=nombreFicheroProblema.
					substring(nombreFicheroProblema.lastIndexOf(System.getProperty("file.separator"))+1);
			
			
			if (ficheroProblema.contains("/")){
				ficheroProblema=ficheroProblema.substring(ficheroProblema.lastIndexOf('/')+1);
				
			}*/
			
        }
		catch (FileNotFoundException fnf){
			System.out.println("ERROR - No se ha encontrado el fichero especificado.");
			System.out.println("Mensaje: "+fnf.getMessage());
		}
		catch (IOException io){
			System.out.println("ERROR - No se ha podido leer el fichero.");
			System.out.println("Mensaje: "+io.getMessage());
		}
		catch (NumberFormatException nfe){
			System.out.println("ERROR - Formato de número incorrecto en la línea "+lineaTratada+" del fichero.");
			if (valorLeido==0) 
				System.out.println("La capacidad de la mochila debe ser un número real usando '.' para separar los decimales");
			else if (valorLeido==1) 
				System.out.println("El número de elementos debe ser un valor entero");
			else 
				System.out.println("Para el volumen y el valor de los objetos se esperan dos reales separados por un espacio");
			System.out.println("Mensaje: "+nfe.getMessage());
			throw new IllegalArgumentException("El fichero no tiene el formato apropiado");
		}
		finally{
			if (b!=null)
				try {
					b.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (f!=null)
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}
		
		return problema;
		
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
