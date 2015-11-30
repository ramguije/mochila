package es.uned.mochila;

public abstract class CrucePadres {
	//Ojo, esta referencia habría que hacerla a una clase abstracta o interfaz
	private EjecutorProblemaMochila EjecutorProblema=null;
	
	//Referencia a la instancia Singleton 
	private static CrucePadres instancia=null;
		
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
