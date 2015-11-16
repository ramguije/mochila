package es.uned.mochila;

public class ProblemaMochila {
	
	private static final int TAMANO_POBLACION=10;
	private static final int NUM_OBJETOS=100;
	//private static final int MAX_GENERACIONES=10000;
	private static final int MAX_GENERACIONES=50;
	private static final int TAMANO_TORNEO=2;
	
	private Individuo[] poblacion=new Individuo[TAMANO_POBLACION];
	private Individuo[] descendencia=null;
	private Individuo[] matingPool=null;
	private Objeto[] objetos=new Objeto[NUM_OBJETOS];
	private Evaluador evaluador=null;
	private ReemplazoTotalConElitismo reemplazador=new ReemplazoTotalConElitismo();
	private double capacidadMochila;
	//private SelectorPadres selectorPadres=new SelectorPadresTorneo();
	
	public ProblemaMochila(){
		//Así encapsulo la creación y cuando tenga otras puedo generar sólo los nuevos constructores.
		this.inicializarObjetos(objetos);
		capacidadMochila=this.generarCapacidadMochila(100, 10000);
	}

	public void run(){
		//Inicialización del AG		
		this.inicializarPoblacion(poblacion, NUM_OBJETOS);
		
		System.out.println ("Capacidad de la mochila: "+this.capacidadMochila);
		System.out.println ("");
		
		//Inicio del ciclo
		evaluador=Evaluador.getEvaluador(this);
		evaluador.evaluar(poblacion);
		System.out.println("Población inicial..............................");
		evaluador.calculaEstadistica(poblacion).imprimir();
		System.out.println ("");
		
		//Ciclo principal. Cuál debe ser la condición de terminación?? 
		//De momento número máximo de generaciones
		
		for (int i=0;i<MAX_GENERACIONES;i++){
			//SeleccionarPadres
			matingPool=SelectorPadres.getSelector(this).getMatingPool(poblacion);
			
			//Recombinar padres
			descendencia=CrucePadres.getInstanciaCruce(this).getDescendencia(matingPool);
			
			this.mutacion(descendencia);
			evaluador.evaluar(descendencia);
			//Cambiar esta llamada
			poblacion=reemplazador.reemplazo(poblacion, descendencia);
			System.out.println("Generación "+(i+1)+"..............................");
			evaluador.calculaEstadistica(poblacion).imprimir();
			System.out.println ("");
		}
		
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
	
	private void inicializarObjetos(Objeto[] objetos){
		//Los inicializa aleatoriamente
		for (int i=0;i<objetos.length;i++)
		{
			objetos[i]=new Objeto();
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


	public int getNumObjetos() {
		return NUM_OBJETOS;
	}


	public int getTamanoTorneo() {
		return TAMANO_TORNEO;
	}
	
	public double getCapacidadMochila() {
		return capacidadMochila;
	}
	
	public Objeto[] getObjetos(){
		return objetos;
	}

	
	
}
