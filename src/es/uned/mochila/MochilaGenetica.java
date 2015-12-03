package es.uned.mochila;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase MochilaGenetica
 * @author Jes�s Ramos
 * Es la clase inicial. Lee los par�metros y el fichero del problema y se encarga de las ejecuciones y de volcar los 
 * resultados a fichero.
 */
public class MochilaGenetica {
	private static Estadistica[][] resultados=null;
	private static ProblemaMochila problema=null;
	private static Configuracion configuracion=null;
	private static String ficheroProblema=null;
	
	
	/**
	 * M�todo inicial
	 * @param args Espera como primer par�metro el fichero con los par�metros de configuraci�n y como
	 * segundo par�metro opcional el fichero de texto con la definici�n del problema. 
	 * Si no se aporta un segundo par�metro se posibilita la generaci�n un problema aleatorio. 
	 */
	public static void main(String[] args) {
		
		if (args.length > 0){
			if (args[0]!=null){
				configuracion=leerConfiguracionDeFichero(args[0]);
			}
			if (args.length > 1){
				if (args[1]!=null){
					problema=ProblemaMochila.leerProblemaDeFichero(args[1]);
					//Guardo el nombre del fichero del problema, lo utilizo para el fichero de resultados.
					ficheroProblema=args[1].
							substring(args[1].lastIndexOf(System.getProperty("file.separator"))+1);
					
					if (ficheroProblema.contains("/")){
						ficheroProblema=ficheroProblema.substring(ficheroProblema.lastIndexOf('/')+1);
						
					}
				}
			}
			else{
				problema=generarProblema();
			}
		}
		else{
			System.out.println("MochilaGenetica <fichero_parametros> [<fichero_problema>]");
			System.out.println("Consulte el formato de los ficheros.");
		}
		
		if (configuracion!=null && problema!=null){
		
			EjecutorProblemaMochila ejecutor=new EjecutorProblemaMochila(problema, configuracion);
			ejecutor.printConfiguracion();
			resultados=new Estadistica[configuracion.getNumEjecuciones()][configuracion.getMaxGeneraciones()+1];
			
			for (int i=0; i<configuracion.getNumEjecuciones();i++){
				System.out.println("Hora de inicio ejecuci�n "+i+": "+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSSS").format(new Date()));
				
				//ejecuto el problema
				resultados[i]=ejecutor.run();
				
				//Muestro el resultado de la ejecuci�n.
				System.out.println("Hora de fin ejecuci�n "+i+": "+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSSS").format(new Date()));
				resultados[i][configuracion.getMaxGeneraciones()].imprimir();
			}
			
			//Vuelco los resultados a fichero
			escribirResultadosAFichero();
			
		}
		else{
			if (configuracion==null){
				System.out.println("No se han especificado los par�metros de ejecuci�n. Saliendo.");
			}
			else{
				System.out.println("No hay problema que solucionar. Saliendo.");
			}
		}

		
	}
	
	/**
	 * Permite generar porblemas de la mochila binaria de distintos tipos: Mochila simple y compleja seg�n el enunciado
	 * de la actividad 1 y, adem�s, problemas complejos con los valores posibles de volumen truncados, con los valores de
	 * densidad truncados y con los valores de volumen y valor correlacionados. El objetivo es intentar generar problemas de
	 * la mochila de caracter multimodal.
	 * @return Un objeto Problema
	 */
	private static ProblemaMochila generarProblema(){
		ProblemaMochila problema=null;
		
		System.out.println("No se ha especificado un fichero con la definici�n del problema.");
		System.out.println("�Desea crear una definici�n nueva?");
		System.out.println("[S]�/[N]o (presione intro)");
		try {
			char nueva=(char)System.in.read();
			System.in.read(new byte[10]);
						
			if (nueva=='s' || nueva=='S'){
				System.out.println("Prefiere un problema :");
				System.out.println(" * Sencillo: "+ProblemaMochila.NUM_OBJETOS_FACIL+" objetos y la capacidad de la mochila ["+ProblemaMochila.MIN_MOCHILA_FACIL+","+ProblemaMochila.MAX_MOCHILA_FACIL+"]");
				System.out.println(" * Complejo: "+ProblemaMochila.NUM_OBJETOS_DIFICIL+" objetos y la capacidad de la mochila ["+ProblemaMochila.MIN_MOCHILA_DIFICIL+","+ProblemaMochila.MAX_MOCHILA_DIFICIL+"]");
				System.out.println(" * Complejo con volumen truncado: "+ProblemaMochila.NUM_OBJETOS_DIFICIL+" objetos y la capacidad de la mochila ["+ProblemaMochila.MIN_MOCHILA_DIFICIL+","+ProblemaMochila.MAX_MOCHILA_DIFICIL+"]");
				System.out.println(" * Complejo con densidad truncada: "+ProblemaMochila.NUM_OBJETOS_DIFICIL+" objetos y la capacidad de la mochila ["+ProblemaMochila.MIN_MOCHILA_DIFICIL+","+ProblemaMochila.MAX_MOCHILA_DIFICIL+"]");
				System.out.println(" * Complejo con volumen y valor correlacionados: "+ProblemaMochila.NUM_OBJETOS_DIFICIL+" objetos y la capacidad de la mochila ["+ProblemaMochila.MIN_MOCHILA_DIFICIL+","+ProblemaMochila.MAX_MOCHILA_DIFICIL+"]");
				System.out.println("[s]encillo / [c]omplejo / [v]olumen truncado / [d]ensidad truncada / co[r]relaci�n volumen y valor (presione intro)");
				char tipoProblema=Character.toLowerCase((char)System.in.read());
				
				if (tipoProblema==ProblemaMochila.MOCHILA_SIMPLE || tipoProblema==ProblemaMochila.MOCHILA_COMPLEJA 
						|| tipoProblema==ProblemaMochila.MOCHILA_COMPLEJA_INTERVALOS_VOLUMEN
						|| tipoProblema==ProblemaMochila.MOCHILA_COMPLEJA_INTERVALOS_DENSIDAD
						|| tipoProblema==ProblemaMochila.MOCHILA_COMPLEJA_CORRELACION_VOLUMEN_VALOR)
				{
						
					problema=ProblemaMochila.generarProblemaAleatorio(tipoProblema);
					
					System.out.println("Guardando instancia del problema a disco");
					String path=System.getProperty("user.dir");
					String name=null;
					
					if (tipoProblema==ProblemaMochila.MOCHILA_SIMPLE){
						name="mochila_sencilla_";
					}else if (tipoProblema==ProblemaMochila.MOCHILA_COMPLEJA){
						name="mochila_compleja_";
					}else if (tipoProblema==ProblemaMochila.MOCHILA_COMPLEJA_INTERVALOS_VOLUMEN){
						name="mochila_compleja_intervalos_volumen_";
					}else if (tipoProblema==ProblemaMochila.MOCHILA_COMPLEJA_INTERVALOS_DENSIDAD){
						name="mochila_compleja_intervalos_densidad_";
					}else{
						name="mochila_compleja_correlacion_volumen_valor_";
					}
					
					name=name+System.currentTimeMillis();
					ficheroProblema=name;
					boolean generado=problema.AFichero(path+System.getProperty("file.separator")+name);
					if (generado)
						System.out.println("Fichero de instancia generado en "+path+System.getProperty("file.separator")+name);
				}
				else{
					System.out.println("Opci�n no v�lida.");
				}
				
			}
			
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		return problema;
	}
	
	
	
	/**
	 * Lee un fichero de texto con los par�metros deseados para el algoritmo gen�tico.
	 * El formato es <nombre_par�metro>=<valor_par�metro> y puede contener los par�metros:
	 * {ejecuciones, torneo, poblacion, generaciones, mutaciones, probabilidad cruce}
	 * Significado
	 *		ejecuciones: n�mero de ejecuciones del problema. (por defecto 50)
	 *		torneo: Tama�o del torneo para la selecci�n de padres. (por defecto 100)
	 *		poblacion: Tama�o de la poblaci�n. (por defecto 2)
	 *		generaciones: N�mero de generaciones antes de finalizar. (por defecto 50000)
	 *  	mutaciones: Tasa promedio de mutaciones para cada indiv�duo. (por defecto 1)
	 * 		probabilidad cruce: Probabilidad de que se produzca el cruce de dos padres. (por defecto 1)
	 * @param nombreFicheroConfiguracion El nombre del fichero a leer.
	 * @return Un objeto de la clase Configuracion con los par�metros.
	 */
	private static Configuracion leerConfiguracionDeFichero(String nombreFicheroConfiguracion) throws IllegalArgumentException{
		
		int ejecuciones=50;
		int poblacion=100;
		int torneo=2;
		int generaciones=5000;
		double promedioMutaciones=1;
		double probabilidadCruce=1;
		
		Configuracion configuracion=null;
		
		FileReader f =null;
		BufferedReader b=null;
		String cadena=null;
		
		int lineaTratada=0;
		
		try{
			f = new FileReader(nombreFicheroConfiguracion);
			b = new BufferedReader(f);
			
			
			while((cadena = b.readLine())!=null) {
				if (!cadena.startsWith("#")){
					String[] valoresConfiguracion=cadena.split("=");
					switch (valoresConfiguracion[0]){
						case "ejecuciones":
							ejecuciones=Integer.parseInt(valoresConfiguracion[1]);
							break;
						case "torneo":
							torneo=Integer.parseInt(valoresConfiguracion[1]);
							break;
						case "poblacion":
							poblacion=Integer.parseInt(valoresConfiguracion[1]);
							break;
						case "generaciones":
							generaciones=Integer.parseInt(valoresConfiguracion[1]);
							break;
						case "mutaciones":
							promedioMutaciones=Double.parseDouble(valoresConfiguracion[1]);
							break;
						case "probabilidad cruce":
							probabilidadCruce=Double.parseDouble(valoresConfiguracion[1]);
							break;
						default:
							throw new IllegalArgumentException("El fichero no tiene el formato apropiado");
					}
				}
				lineaTratada++;
			}
			configuracion=new Configuracion(ejecuciones, torneo, poblacion, generaciones, promedioMutaciones, probabilidadCruce);			
			
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
			System.out.println("ERROR - Formato de n�mero incorrecto en la l�nea "+lineaTratada+" del fichero. El valor debe ser un n�mero entero");
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
		return configuracion;
	}
	
	/**
	 * Escribe los resultados del total de ejecuciones a un fichero de texto con el formato:
	 * "ejecuci�n generaci�n mejorFitness fitnessMedio numeroEvaluaciones". Los campos se separan por espacios.
	 * El fichero de salida se puede cargar en R directamente mediante read.table
	 * @return n�mero de filas escritas en el fichero.
	 */
	private static int escribirResultadosAFichero(){
		PrintWriter writer=null;
		int filasEscritas=0;
		
		//El nombre ser� algo como "resultados_mochilaCompleja_100_10000_1000_4.txt"
		String nombre=System.getProperty("user.dir")+System.getProperty("file.separator")+
				"resultados_"+ficheroProblema+"_"+configuracion.getNumEjecuciones()+
				"_"+configuracion.getMaxGeneraciones()+"_"+configuracion.getTamanioPoblacion()+
				"_"+configuracion.getTamanioTorneo()+"_"+System.currentTimeMillis();
		
		try{
			writer = new PrintWriter(nombre, "UTF-8");
		
			writer.println("ejecucion generacion mejorFitness fitnessMedio numEvals");
			for (int i=0;i<configuracion.getNumEjecuciones();i++){
				for (int j=0;j<configuracion.getMaxGeneraciones()+1;j++){
					writer.println(i+" "+j+" "+resultados[i][j].getMejorFitness()+" "+
							resultados[i][j].getFitnessMedio()+" "+resultados[i][j].getNumEvaluaciones());
					filasEscritas++;
				}
				
			}
			writer.close();
			System.out.println("Fichero de salida con los resultados generado en:");
			System.out.println(nombre);

		}
		catch(IOException io)
		{
			System.out.println("ERROR - No se ha podido escribir el fichero de salida con los resultados.");
			System.out.println("Mensaje: "+io.getMessage());
		}
		
		return filasEscritas;
	}
	

}
