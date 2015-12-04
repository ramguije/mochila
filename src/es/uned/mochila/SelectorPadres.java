package es.uned.mochila;

/**
 * Clase abstracta que separa la selecci�n de padres de la estrategia concreta
 * de selecci�n. Hace tambi�n de factor�a muy b�sica. Sea cual sea la estrategia concreta,
 * s�lo habr� una instancia (Singleton)
 * @author Jes�s Ramos Guillou.
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
	
	//Para ser implementado por la estrategia concreta de selecci�n.
	protected abstract Individuo[] getMatingPool(Individuo[] poblacion);
	
	protected void setEjecutorProblema(EjecutorProblemaMochila p){
		this.ejecutorProblema=p;
	}
	
	protected EjecutorProblemaMochila getEjecutorProblema(){
		return ejecutorProblema;
	}

}
