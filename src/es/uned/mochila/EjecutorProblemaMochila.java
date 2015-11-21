package es.uned.mochila;

public class EjecutorProblemaMochila {
	//TODO Separar el objeto que resuelve del objeto Problema que encapsular�a los datos del problema y no de la
	//resoluci�n.
	
	private static final int TAMANO_POBLACION=10;
	//private static final int MAX_GENERACIONES=10000;
	private static final int MAX_GENERACIONES=1000;
	private static final int TAMANO_TORNEO=2;
	private static final int MIN_MOCHILA=100;
	private static final int MAX_MOCHILA=10000;
	
	//Encapsula los datos del problema.
	private Problema problema=null;
	
	private Individuo[] poblacion=new Individuo[TAMANO_POBLACION];
	private Individuo[] descendencia=null;
	private Individuo[] matingPool=null;
	private Evaluador evaluador=null;
	private ReemplazoTotalConElitismo reemplazador=new ReemplazoTotalConElitismo();
	//private SelectorPadres selectorPadres=new SelectorPadresTorneo();
	
	public EjecutorProblemaMochila(){
		int numObjetos=100;
		
		//As� encapsulo la creaci�n y cuando tenga otras puedo generar s�lo los nuevos constructores.
		problema=new Problema(this.generarCapacidadMochila(MIN_MOCHILA, MAX_MOCHILA), numObjetos);
		
	}
	
	public EjecutorProblemaMochila(Problema problema){
		this.problema=problema;
		
	}

	public Estadistica run(){
		//Inicializaci�n del AG		
		this.inicializarPoblacion(poblacion, problema.getNumObjetos());
		Estadistica ultimaEstadistica=null;
		
		//System.out.println ("Capacidad de la mochila: "+this.capacidadMochila);
		//System.out.println ("");
		
		//Inicio del ciclo
		evaluador=Evaluador.getEvaluador(this);
		evaluador.evaluar(poblacion);
		//System.out.println("Poblaci�n inicial..............................");
		ultimaEstadistica=evaluador.calculaEstadistica(poblacion);
		//ultimaEstadistica.imprimir();
		//System.out.println ("");
		
		//Ciclo principal. Cu�l debe ser la condici�n de terminaci�n?? 
		//De momento n�mero m�ximo de generaciones
		
		for (int i=0;i<MAX_GENERACIONES;i++){
			//SeleccionarPadres
			matingPool=SelectorPadres.getSelector(this).getMatingPool(poblacion);
			
			//Recombinar padres
			descendencia=CrucePadres.getInstanciaCruce(this).getDescendencia(matingPool);
			
			this.mutacion(descendencia);
			evaluador.evaluar(descendencia);
			//Cambiar esta llamada
			poblacion=reemplazador.reemplazo(poblacion, descendencia);
			//System.out.println("Generaci�n "+(i+1)+"..............................");
			ultimaEstadistica=evaluador.calculaEstadistica(poblacion);
			//ultimaEstadistica.imprimir();
			//System.out.println ("");
		}
		
		return ultimaEstadistica;
		
	}
	
	private double generarCapacidadMochila(int minCapacidad, int maxCapacidad) {
		//Generar aleatoriamente la capacidad de la mochila entre 100 y 10.000
		return Math.random() * (maxCapacidad-minCapacidad) + minCapacidad;
		
	}
	
	private void inicializarPoblacion(Individuo[] poblacion, int numObjetos){
		for (int i=0;i<poblacion.length;i++)
		{
			poblacion[i]=new Individuo(numObjetos);
		}
	}
	
	private void mutacion(Individuo[] descendencia)
	{
		double probabilidadMutacion=((double)1/getNumObjetos());
		for (int i=0;i<descendencia.length;i++){
			descendencia[i].mutacion(probabilidadMutacion);
		}
	}
	
	
	public int getTamanoPoblacion() {
		return TAMANO_POBLACION;
	}


	public int getTamanoTorneo() {
		return TAMANO_TORNEO;
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

	public void printConfiguracion(){
		System.out.println("Sin hacer.");
	}
	
	
}
