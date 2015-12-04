package es.uned.mochila;

/**
 * Clase abstracta que separa la selección de padres de la estrategia concreta
 * de selección. Hace también de factoría muy básica. Sea cual sea la estrategia concreta,
 * sólo habrá una instancia (Singleton)
 * @author Jesús Ramos Guillou.
 *
 */
public abstract class SelectorPadres {
	
	private EjecutorProblemaMochila ejecutorProblema=null;
	//Referencia a la instancia Singleton 
	private static SelectorPadres instancia=null;
	
	public static SelectorPadres getSelector(EjecutorProblemaMochila p)
	{
		if (instancia==null){
			instancia=new SelectorPorTorneo(p);
		}
		
		return instancia;
	}
	
	//Para ser implementado por la estrategia concreta de selección.
	protected abstract Individuo[] getMatingPool(Individuo[] poblacion);
	
	protected void setEjecutorProblema(EjecutorProblemaMochila p){
		this.ejecutorProblema=p;
	}
	
	protected EjecutorProblemaMochila getEjecutorProblema(){
		return ejecutorProblema;
	}

}
