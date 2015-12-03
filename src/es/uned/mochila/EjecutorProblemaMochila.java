package es.uned.mochila;

public class EjecutorProblemaMochila {
	
	//Encapsula los datos del problema.
	private Problema problema=null;
	//Datos de configuracion
	private Configuracion configuracion=null;
	
	private Individuo[] poblacion=null;
	private Individuo[] descendencia=null;
	private Individuo[] matingPool=null;
	private Evaluador evaluador=null;
	private ReemplazoTotalConElitismo reemplazador=new ReemplazoTotalConElitismo();
	//private SelectorPadres selectorPadres=new SelectorPadresTorneo();
	private Estadistica[] resultados=null;
	
	
	public EjecutorProblemaMochila(Problema problema, Configuracion configuracion){
		this.problema=problema;
		this.configuracion=configuracion;
		poblacion=new Individuo[configuracion.getTamanioPoblacion()];
		//resultados=new Estadistica[configuracion.getMaxGeneraciones()+1];
	}

	public Estadistica[] run(){
		//Inicializaci�n del AG		
		this.inicializarPoblacion(poblacion, problema.getNumObjetos());
		resultados=new Estadistica[configuracion.getMaxGeneraciones()+1];
		
		//System.out.println ("Capacidad de la mochila: "+this.capacidadMochila);
		//System.out.println ("");
		
		//Inicio del ciclo
		evaluador=Evaluador.getNuevoEvaluador(this);
		evaluador.evaluar(poblacion);
		//System.out.println("Poblaci�n inicial..............................");
		resultados[0]=evaluador.calculaEstadistica(poblacion);
		//ultimaEstadistica.imprimir();
		//System.out.println ("");
		
		//Ciclo principal. Cu�l debe ser la condici�n de terminaci�n?? 
		//De momento n�mero m�ximo de generaciones
		
		for (int i=0;i<configuracion.getMaxGeneraciones();i++){
			//SeleccionarPadres
			matingPool=SelectorPadres.getSelector(this).getMatingPool(poblacion);
			
			//Recombinar padres
			descendencia=CrucePadres.getInstanciaCruce(this).getDescendencia(matingPool);
			
			this.mutacion(descendencia);
			evaluador.evaluar(descendencia);
			//Cambiar esta llamada
			poblacion=reemplazador.reemplazo(poblacion, descendencia);
			//System.out.println("Generaci�n "+(i+1)+"..............................");
			resultados[i+1]=evaluador.calculaEstadistica(poblacion);
			//ultimaEstadistica.imprimir();
			//System.out.println ("");
		}
		
		return resultados;
		
	}
	
	
	private void inicializarPoblacion(Individuo[] poblacion, int numObjetos){
		for (int i=0;i<poblacion.length;i++)
		{
			poblacion[i]=new Individuo(numObjetos);
		}
	}
	
	private void mutacion(Individuo[] descendencia)
	{
		for (int i=0;i<descendencia.length;i++){
			descendencia[i].mutacion(configuracion.getPromedioMutaciones());
		}
	}
	
	
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
