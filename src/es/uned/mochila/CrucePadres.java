package es.uned.mochila;
/**
 * Clase abstracta utilizada para no depender de la implementaci�n concreta del mecanismo de crossover.
 * @author Jes�s Ramos Guillou.
 */
public abstract class CrucePadres {
	
	private EjecutorProblemaMochila EjecutorProblema=null; 
	private static CrucePadres instancia=null; 	//Referencia a la instancia Singleton 
		
	/**
	 * Devuelve una instancia concreta de CrucePadres.
	 * @param p
	 * @return
	 */
	public static CrucePadres getInstanciaCruce(EjecutorProblemaMochila p)
	{
		if (instancia==null){
			instancia=new CrucePorUnPunto(p);
		}
		
		return instancia;
	}
	
	//Para ser implementado por la estrategia concreta de selecci�n.
	protected abstract Individuo[] getDescendencia(Individuo[] matingPool);
	
	protected void setEjecutorProblema(EjecutorProblemaMochila p){
		this.EjecutorProblema=p;
	}
	
	protected EjecutorProblemaMochila getEjecutorProblema(){
		return EjecutorProblema;
	}
}
