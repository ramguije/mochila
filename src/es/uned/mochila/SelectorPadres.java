package es.uned.mochila;

public abstract class SelectorPadres {
	
	//Ojo, esta referencia habría que hacerla a una clase abstracta o interfaz
	private EjecutorProblemaMochila problema=null;
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
	
	protected void setProblema(EjecutorProblemaMochila p){
		this.problema=p;
	}
	
	protected EjecutorProblemaMochila getProblema(){
		return problema;
	}

}
