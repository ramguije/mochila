package es.uned.mochila;


/**
 * Es la clase que ejecuta el algoritmo gen�tico. 
 * @author Jes�s Ramos.
 */
public class EjecutorProblemaMochila {
	
	//Encapsula los datos del problema.
	private ProblemaMochila problema=null;
	//Datos de configuracion
	private Configuracion configuracion=null;
	
	
	private Individuo[] poblacion=null; //Colecci�n de individuos que forman la poblaci�n
	private Individuo[] matingPool=null; //Colecci�n de padres seleccionados para generar la descendencia
	private Individuo[] descendencia=null; //La descendencia de la poblaci�n
	
	private Evaluador evaluador=null; //Realiza la evaluaci�n de cada generaci�n
	private Estadistica[] resultados=null;  //Los resultados de esta ejecuci�n del algoritmo.
	
	
	public EjecutorProblemaMochila(ProblemaMochila problema, Configuracion configuracion){
		this.problema=problema;
		this.configuracion=configuracion;
		poblacion=new Individuo[configuracion.getTamanioPoblacion()];
	}

	/**
	 * Ejecuci�n del algoritmo gen�tico.
	 * @return Una colecci�n de objetos de la clase Estadistica, uno para cada generaci�n incluida la inicial aleatoria.
	 */
	public Estadistica[] run(){
		//Inicializaci�n de la poblaci�n del AG		
		this.inicializarPoblacion(poblacion, problema.getNumObjetos());
		resultados=new Estadistica[configuracion.getMaxGeneraciones()+1];
		
		//Inicio del ciclo
		evaluador=Evaluador.getNuevoEvaluador(this);
		
		//Evaluo la poblaci�n inicial
		evaluador.evaluar(poblacion);
		resultados[0]=evaluador.calculaEstadistica(poblacion);
		
		//Ciclo principal. Se ejecuta hasta un m�ximo de generaciones que se debe haber espec�ficado como par�metro. 
		for (int i=0;i<configuracion.getMaxGeneraciones();i++){
			//SeleccionarPadres
			matingPool=SelectorPadres.getSelector(this).getMatingPool(poblacion);
			
			//Recombinar padres
			descendencia=CrucePadres.getInstanciaCruce(this).getDescendencia(matingPool);
			
			//Realizar las posibles mutaciones
			this.mutacion(descendencia);
			
			//Evaluar la descendencia
			evaluador.evaluar(descendencia);
			
			//Seleccionar los supervivientes de la siguiente generaci�n. 
			poblacion=SelectorSupervivientes.getSelectorSupervivientes().reemplazo(poblacion, descendencia);

			resultados[i+1]=evaluador.calculaEstadistica(poblacion);

		}
		
		return resultados;
		
	}
	
	/**
	 * Inicializa la poblaci�n de forma aleatoria.
	 */
	private void inicializarPoblacion(Individuo[] poblacion, int numObjetos){
		for (int i=0;i<poblacion.length;i++)
		{
			poblacion[i]=new Individuo(numObjetos);
		}
	}
	
	/**
	 * Manda un mensaje a cada individuo para que realice la mutaci�n.
	 * @param descendencia
	 */
	private void mutacion(Individuo[] descendencia)
	{
		for (int i=0;i<descendencia.length;i++){
			descendencia[i].mutacion(configuracion.getPromedioMutaciones());
		}
	}
	
	//M�todos para acceder a los par�metros y a los datos del problema.
	
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
		System.out.println("Tama�o poblaci�n "+configuracion.getTamanioPoblacion());
		System.out.println("Tama�o torneo "+configuracion.getTamanioTorneo());
		System.out.println("N�mero m�ximo de generaciones "+configuracion.getMaxGeneraciones());
		System.out.println("N�mero promedio de mutaciones por individuo "+configuracion.getPromedioMutaciones());
		System.out.println("probabilidad de cruce "+configuracion.getProbabilidadCruce());
		System.out.println("");
		
	}
	
	
}
