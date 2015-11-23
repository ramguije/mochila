package es.uned.mochila;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MochilaGenetica {
	private static Estadistica[][] resultados=null;
	private static Problema problema=null;
	private static Configuracion configuracion=null;
	private static String ficheroProblema=null;
	
	/**
	 * Clase principal
	 * @param args Espera como primer parámetro el fichero con los parámetros de configuración y como
	 * segundo parámetro opcional el fichero de texto con la definición del problema. 
	 * Si no se aporta un segundo parámetro se genera un problema aleatorio. 
	 */
	public static void main(String[] args) {
		
		if (args.length > 0){
			if (args[0]!=null){
				configuracion=leerConfiguracionDeFichero(args[0]);
			}
			if (args.length > 1){
				if (args[1]!=null){
					problema=leerProblemaDeFichero(args[1]);
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
				//ejecuto el problema
				resultados[i]=ejecutor.run();
				
				//Muestro el resultado de la ejecución.
				System.out.println("Ejecución "+i+": ");
				resultados[i][configuracion.getMaxGeneraciones()].imprimir();
			}
			
			//Volcar los resultados a un fichero
			escribirResultadosAFichero();
			
		}
		else{
			if (configuracion==null){
				System.out.println("No se han especificado los parámetros de ejecución. Saliendo.");
			}
			else{
				System.out.println("No hay problema que solucionar. Saliendo.");
			}
		}

		
	}
	
	
	private static Problema generarProblema(){
		Problema problema=null;
		
		System.out.println("No se ha especificado un fichero con la definición del problema.");
		System.out.println("¿Desea crear una definición nueva?");
		System.out.println("[S]í/[N]o (presione intro)");
		try {
			char nueva=(char)System.in.read();
			System.in.read(new byte[10]);
						
			if (nueva=='s' || nueva=='S'){
				boolean sencillo=false;
				System.out.println("Prefiere un problema sencillo: "+Problema.NUM_OBJETOS_FACIL+" objetos y la capacidad de la mochila ["+Problema.MIN_MOCHILA_FACIL+","+Problema.MAX_MOCHILA_FACIL+"]");
				System.out.println("o complejo: "+Problema.NUM_OBJETOS_DIFICIL+" objetos y la capacidad de la mochila ["+Problema.MIN_MOCHILA_DIFICIL+","+Problema.MAX_MOCHILA_DIFICIL+"]");
				System.out.println("[s]encillo / [c]omplejo (presione intro)");
				char sencillooComplejo=(char)System.in.read();
				if (sencillooComplejo=='s' || sencillooComplejo=='S'){
					sencillo=true;
				}
				problema=Problema.generarProblemaAleatorio(sencillo);
				System.out.println("Guardando instancia del problema a disco");
				String path=System.getProperty("user.dir");
				String name=null;
				if (sencillo){
					name="mochila_sencilla_";
				}else{
					name="mochila_compleja_";
				}
				name=name+System.currentTimeMillis();
				ficheroProblema=name;
				boolean generado=problema.AFichero(path+System.getProperty("file.separator")+name);
				if (generado)
					System.out.println("Fichero de instancia generado en "+path+System.getProperty("file.separator")+name);
			}
			
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		return problema;
	}
	
	
	//TODO Si el problema se vuelca a fichero en la clase problema parece que tiene sentido que también se lea de fichero 
	//en esa clase.
	private static Problema leerProblemaDeFichero(String nombreFicheroProblema) throws IllegalArgumentException{
		double capacidadMochila=0;
		int numElementos=0;
		double[] volumenes=null;
		double[] valores=null;
		Problema problema=null;
		
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
			problema=new Problema(capacidadMochila, numElementos, valores, volumenes);
			
			//Guardo el nombre del fichero del problema, lo utilizo para el fichero de resultados.
			ficheroProblema=nombreFicheroProblema.
					substring(nombreFicheroProblema.lastIndexOf(System.getProperty("file.separator"))+1,
					nombreFicheroProblema.length());
			
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
	 * Lee un fichero de texto con los parámetros deseados para el algoritmo genético.
	 * El formato es <nombre_parámetro>=<valor_parámetro> y debe contener los parámetros 
	 * {ejecuciones, torneo, poblacion, generaciones}
	 * Ejemplo 
	 * #Parámetros de ejecución
	 *	ejecuciones=50
	 *	torneo=2
	 *	poblacion=10
	 *	generaciones=50
	 * @param nombreFicheroConfiguracion El nombre del fichero a leer.
	 * @return Un objeto de la clase Configuracion con los parámetros.
	 */
	private static Configuracion leerConfiguracionDeFichero(String nombreFicheroConfiguracion) throws IllegalArgumentException{
		
		int ejecuciones=0;
		int poblacion=0;
		int torneo=0;
		int generaciones=0;
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
						default:
							throw new IllegalArgumentException("El fichero no tiene el formato apropiado");
					}
				}
				lineaTratada++;
			}
			configuracion=new Configuracion(ejecuciones, torneo, poblacion, generaciones);			
			
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
			System.out.println("ERROR - Formato de número incorrecto en la línea "+lineaTratada+" del fichero. El valor debe ser un número entero");
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
	
	
	private static int escribirResultadosAFichero(){
		PrintWriter writer=null;
		int filasEscritas=0;
		//El nombre será algo como "resultados_mochilaCompleja_100_10000_1000_4.txt"
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
