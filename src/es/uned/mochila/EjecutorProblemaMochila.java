package es.uned.mochila;


/**
 * Es la clase que ejecuta el algoritmo genético. 
 * @author Jesús Ramos.
 */
public class EjecutorProblemaMochila {
	
	//Encapsula los datos del problema.
	private ProblemaMochila problema=null;
	//Datos de configuracion
	private Configuracion configuracion=null;
	
	
	private Individuo[] poblacion=null; //Colección de individuos que forman la población
	private Individuo[] matingPool=null; //Colección de padres seleccionados para generar la descendencia
	private Individuo[] descendencia=null; //La descendencia de la población
	
	private Evaluador evaluador=null; //Realiza la evaluación de cada generación
	private Estadistica[] resultados=null;  //Los resultados de esta ejecución del algoritmo.
	
	
	public EjecutorProblemaMochila(ProblemaMochila problema, Configuracion configuracion){
		this.problema=problema;
		this.configuracion=configuracion;
		poblacion=new Individuo[configuracion.getTamanioPoblacion()];
	}

	/**
	 * Ejecución del algoritmo genético.
	 * @return Una colección de objetos de la clase Estadistica, uno para cada generación incluida la inicial aleatoria.
	 */
	public Estadistica[] run(){
		//Inicialización de la población del AG		
		this.inicializarPoblacion(poblacion, problema.getNumObjetos());
		resultados=new Estadistica[configuracion.getMaxGeneraciones()+1];
		
		//Inicio del ciclo
		evaluador=Evaluador.getNuevoEvaluador(this);
		
		//Evaluo la población inicial
		evaluador.evaluar(poblacion);
		resultados[0]=evaluador.calculaEstadistica(poblacion);
		
		//Ciclo principal. Se ejecuta hasta un máximo de generaciones que se debe haber específicado como parámetro. 
		for (int i=0;i<configuracion.getMaxGeneraciones();i++){
			//SeleccionarPadres
			matingPool=SelectorPadres.getSelector(this).getMatingPool(poblacion);
			
			//Recombinar padres
			descendencia=CrucePadres.getInstanciaCruce(this).getDescendencia(matingPool);
			
			//Realizar las posibles mutaciones
			this.mutacion(descendencia);
			
			//Evaluar la descendencia
			evaluador.evaluar(descendencia);
			
			//Seleccionar los supervivientes de la siguiente generación. 
			poblacion=SelectorSupervivientes.getSelectorSupervivientes().reemplazo(poblacion, descendencia);

			resultados[i+1]=evaluador.calculaEstadistica(poblacion);

		}
		
		return resultados;
		
	}
	
	/**
	 * Inicializa la población de forma aleatoria.
	 */
	private void inicializarPoblacion(Individuo[] poblacion, int numObjetos){
		for (int i=0;i<poblacion.length;i++)
		{
			poblacion[i]=new Individuo(numObjetos);
		}
	}
	
	/**
	 * Manda un mensaje a cada individuo para que realice la mutación.
	 * @param descendencia
	 */
	private void mutacion(Individuo[] descendencia)
	{
		for (int i=0;i<descendencia.length;i++){
			descendencia[i].mutacion(configuracion.getPromedioMutaciones());
		}
	}
	
	//Métodos para acceder a los parámetros y a los datos del problema.
	
	public int getTamanoPoblacion() {
		return configuracion.getTamanioPoblacion();
	}

	public int getTamanoTorneo() {
		return configuracion.getTamanioTorneo();
	}
	
	public int getNumObjetos() {
		return problema.getNumObjetos();
	}
	
	public double getCapacidadMochila() {
		return problema.getCapacidadMochila();
	}
	
	public Objeto[] getObjetos(){
		return problema.getObjetos();
	}
	
	public double getProbabilidadCruce(){
		return configuracion.getProbabilidadCruce();
	}

	public void printConfiguracion(){
		System.out.println("Tamaño población "+configuracion.getTamanioPoblacion());
		System.out.println("Tamaño torneo "+configuracion.getTamanioTorneo());
		System.out.println("Número máximo de generaciones "+configuracion.getMaxGeneraciones());
		System.out.println("Número promedio de mutaciones por individuo "+configuracion.getPromedioMutaciones());
		System.out.println("probabilidad de cruce "+configuracion.getProbabilidadCruce());
		System.out.println("");
		
	}
	
	
}
