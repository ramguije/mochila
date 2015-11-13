package es.uned.mochila;

public abstract class SelectorPadres {
	
	//Ojo, esta referencia habr�a que hacerla a una clase abstracta o interfaz
	private ProblemaMochila problema=null;
	//Referencia a la instancia Singleton 
	private static SelectorPadres instancia=null;
	
	public static SelectorPadres getSelector(ProblemaMochila p)
	{
		if (instancia==null){
			instancia=new SelectorPorTorneo(p);
		}
		
		return instancia;
	}
	
	//Para ser implementado por la estrategia concreta de selecci�n.
	protected abstract Individuo[] getMatingPool(Individuo[] poblacion);
	
	protected void setProblema(ProblemaMochila p){
		this.problema=p;
	}
	
	protected ProblemaMochila getProblema(){
		return problema;
	}

}
