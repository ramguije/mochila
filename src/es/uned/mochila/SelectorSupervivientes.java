package es.uned.mochila;

public abstract class SelectorSupervivientes {
	
	//Referencia a la instancia Singleton 
	private static SelectorSupervivientes instancia=null;
	
	public static SelectorSupervivientes getSelectorSupervivientes()
	{
		if (instancia==null){
			instancia=new ReemplazoTotalConElitismo();
		}
		
		return instancia;	
	}
	
	public abstract Individuo[] reemplazo(Individuo[] poblacion, Individuo[] descendientes);
}
