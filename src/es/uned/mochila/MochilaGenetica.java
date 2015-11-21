package es.uned.mochila;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MochilaGenetica {
	static final int REPETICIONES=50;
	
	
	/**
	 * Clase principal
	 * @param args Espera como primer par�metro el fichero con los par�metros de configuraci�n y como
	 * segundo par�metro opcional el fichero de texto con la definici�n del problema. 
	 * Si no se aporta un segundo par�metro se genera un problema aleatorio. 
	 */
	public static void main(String[] args) {
		Estadistica resultado=null;
		Problema problema=null;
		
		if (args[0]!=null){
			problema=leerProblemaDeFichero(args[0]);
		}
		else{
			System.out.println("Falta el fichero del problema");
		}
		
		if (problema!=null){
		
			EjecutorProblemaMochila ejecutor=new EjecutorProblemaMochila(problema);
			ejecutor.printConfiguracion();
			
			for (int i=0; i<REPETICIONES;i++){
				resultado=ejecutor.run();
				System.out.println("Ejecuci�n "+i+": ");
				resultado.imprimir();
			}
		}

		
	}
	
	
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
			System.out.println("ERROR - Formato de n�mero incorrecto en la l�nea "+lineaTratada+" del fichero.");
			if (valorLeido==0) 
				System.out.println("La capacidad de la mochila debe ser un n�mero real usando '.' para separar los decimales");
			else if (valorLeido==1) 
				System.out.println("El n�mero de elementos debe ser un valor entero");
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
	

}
