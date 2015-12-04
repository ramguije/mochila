package es.uned.mochila;
/**
 * Clase abstracta utilizada para no depender de la implementación concreta del mecanismo de crossover.
 * Hace también de factoría muy básica. Sea cual sea la estrategia concreta,
 * sólo habrá una instancia (Singleton).
 * @author Jesús Ramos Guillou.
 */
public abstract class CrucePadres {
	
	private EjecutorProblemaMochila EjecutorProblema=null; 
	private static CrucePadres instancia=null; 	//Referencia a la instancia Singleton 
		
	public static CrucePadres getInstanciaCruce(EjecutorProblemaMochila p)
	{
		if (instancia==null){
			instancia=new CrucePorUnPunto(p);
		}
		
		return instancia;
	}
	
	//Para ser implementado por la estrategia concreta de selección.
	protected abstract Individuo[] getDescendencia(Individuo[] matingPool);
	
	protected void setEjecutorProblema(EjecutorProblemaMochila p){
		this.EjecutorProblema=p;
	}
	
	protected EjecutorProblemaMochila getEjecutorProblema(){
		return EjecutorProblema;
	}
}
