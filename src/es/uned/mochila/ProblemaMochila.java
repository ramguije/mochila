package es.uned.mochila;

public class ProblemaMochila {
	
	private static final int TAMANO_POBLACION=10;
	private static final int NUM_OBJETOS=100;
	private static final int MAX_GENERACIONES=1000000;
	
	private Individuo[] poblacion=new Individuo[TAMANO_POBLACION];
	private Individuo[] descendencia=null;
	private Individuo[][] matingPool=null;
	private Objeto[] objetos=new Objeto[NUM_OBJETOS];
	private Evaluador evaluador=new EvaluadorMochilaElementosOrdenados();
	private SelectorPadres selectorPadres=new SelectorPadresTorneo();
	
	public void run(){
		//BEGIN
				//Inicializar la población con candidatos al azar
				//Evaluar los candidatos
				//repetir hasta condicion de terminación
					//seleccionar padres
					//recombinar las parejas de padres
					//proceso de mutación
					//Evaluar nuevos candidatos
					//Seleccionar la siguiente generacion
				//fin repetir
		
		//Inicialización del AG
		this.inicializarPoblacion(poblacion);
		this.inicializarObjetos(objetos);
		evaluador.inicializar(objetos);
		
		//Inicio del ciclo
		evaluador.evaluar(poblacion);
		
		//Ciclo principal. Cuál debe ser la condición de terminación?? 
		//De momento número máximo de generaciones
		for (int i=0;i<MAX_GENERACIONES;i++){
			//SeleccionarPadres
			//Recombinar padres
			this.mutacion(descendencia);
			evaluador.evaluar(descendencia);
			//SeleccionarGeneracion (cambio de todos los elementos)
			//Ojo, mirar lo del elitismo que viene en el enunciado de la páctica.
			poblacion=descendencia;
		}
		
		
		
	}
	
	
	
	private void inicializarPoblacion(Individuo[] poblacion){
		
	}
	
	private void inicializarObjetos(Objeto[] objetos){
		
	}
	
	private void mutacion(Individuo[] descendencia)
	{
		for (Individuo a: descendencia){
			a.mutacion();
		}
		
	
	}
	
	
	
}
