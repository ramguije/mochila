package es.uned.mochila;

import java.util.ArrayList;

public class ProblemaMochila {
	
	private static final int TAMANO_POBLACION=10;
	private static final int NUM_OBJETOS=100;
	private static final int MAX_GENERACIONES=10000;
	
	private Individuo[] poblacion=new Individuo[TAMANO_POBLACION];
	private Individuo[] descendencia=null;
	private Individuo[][] matingPool=null;
	private Objeto[] objetos=new Objeto[NUM_OBJETOS];
	private Evaluador evaluador=null;
	//private SelectorPadres selectorPadres=new SelectorPadresTorneo();
	
	public ProblemaMochila(){
		//As� encapsulo la creaci�n y cuando tenga otras puedo generar s�lo los nuevos constructores.
		this.inicializarObjetos(objetos);
		evaluador=new EvaluadorMochilaElementosOrdenados(this.generarCapacidadMochila(100, 10000));
	}
	

	public void run(){
		//BEGIN
				//Inicializar la poblaci�n con candidatos al azar
				//Evaluar los candidatos
				//repetir hasta condicion de terminaci�n
					//seleccionar padres
					//recombinar las parejas de padres
					//proceso de mutaci�n
					//Evaluar nuevos candidatos
					//Seleccionar la siguiente generacion
				//fin repetir
		
		//Inicializaci�n del AG		
		this.inicializarPoblacion(poblacion, NUM_OBJETOS);
		evaluador.inicializar(objetos);
		
		//Inicio del ciclo
		evaluador.evaluar(poblacion);
		
		//Ciclo principal. Cu�l debe ser la condici�n de terminaci�n?? 
		//De momento n�mero m�ximo de generaciones
		/*
		for (int i=0;i<MAX_GENERACIONES;i++){
			//SeleccionarPadres
			//Recombinar padres
			this.mutacion(descendencia);
			evaluador.evaluar(descendencia);
			//SeleccionarGeneracion (cambio de todos los elementos)
			//Ojo, mirar lo del elitismo que viene en el enunciado de la p�ctica.
			poblacion=descendencia;
		}
		*/
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
		for (Individuo a: descendencia){
			a.mutacion();
		}
	}
	
	
	
}
