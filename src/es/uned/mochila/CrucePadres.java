package es.uned.mochila;

public abstract class CrucePadres {
	//Ojo, esta referencia habría que hacerla a una clase abstracta o interfaz
	private ProblemaMochila problema=null;
	
	//Referencia a la instancia Singleton 
	private static CrucePadres instancia=null;
		
		public static CrucePadres getInstanciaCruce(ProblemaMochila p)
		{
			if (instancia==null){
				instancia=new CrucePorUnPunto(p);
			}
			
			return instancia;
		}
		
		//Para ser implementado por la estrategia concreta de selección.
		protected abstract Individuo[] getDescendencia(Individuo[] matingPool);
		
		protected void setProblema(ProblemaMochila p){
			this.problema=p;
		}
		
		protected ProblemaMochila getProblema(){
			return problema;
		}
}
