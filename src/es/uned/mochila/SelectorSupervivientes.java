package es.uned.mochila;

/**
 * Clase abstracta que separa la selecci�n de supervivientes de la estrategia concreta
 * de reemplazo. Hace tambi�n de factor�a muy b�sica. Sea cual sea la estrategia concreta,
 * s�lo habr� una instancia (Singleton)
 * @author Jes�s Ramos Guillou.
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
	
	//Para ser implementado por la estrategia concreta de selecci�n de supervivientes.
	public abstract Individuo[] reemplazo(Individuo[] poblacion, Individuo[] descendientes);
}
