package es.uned.mochila;

/**
 * Clase abstracta que separa la selección de supervivientes de la estrategia concreta
 * de reemplazo. Hace también de factoría muy básica. Sea cual sea la estrategia concreta,
 * sólo habrá una instancia (Singleton)
 * @author Jesús Ramos Guillou.
 *
 */
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
	
	//Para ser implementado por la estrategia concreta de selección de supervivientes.
	public abstract Individuo[] reemplazo(Individuo[] poblacion, Individuo[] descendientes);
}
