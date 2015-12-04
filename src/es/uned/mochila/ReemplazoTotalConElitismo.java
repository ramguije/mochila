package es.uned.mochila;

/**
 * Clase que realiza la selección de supervivientes mediante un modelo generacional con elitismo.
 * @author Jesús Ramos Guillou
 *
 */
public class ReemplazoTotalConElitismo extends SelectorSupervivientes{
	
	/**
	 * Realiza el reemplazo total de la población aplicando elitismo. Sólo se mantiene el mejor 
	 * elemento de la población actual si no hay ningún descendiente mejor.
	 */
	public Individuo[] reemplazo(Individuo[] poblacion, Individuo[] descendientes){
		int mejorIndice=0;
		double mejorFitness=0;
		int peorIndice=0;
		double peorFitness=descendientes[0].getFitness();
		boolean encontradoMejor=false;
		
		//Busco el mejor elemento de la población
		for (int i=0;i<poblacion.length;i++){
			if (poblacion[i].getFitness()>mejorFitness){
				mejorFitness=poblacion[i].getFitness();
				mejorIndice=i;
			}
		}
		
		if (descendientes[0].getFitness()>=mejorFitness){
			encontradoMejor=true;
		}
		
		for (int i=1;((i<descendientes.length)&&(!encontradoMejor));i++){
			
			if (descendientes[i].getFitness()>=mejorFitness){
				encontradoMejor=true;
			}else{
				//Me quedo con el peor de los descendientes que será el que reemplace.
				if (descendientes[i].getFitness()<peorFitness){
					peorFitness=descendientes[i].getFitness();
					peorIndice=i;
				}
			}
		}
		
		//Si no encuentro ningún descendiente mejor, reemplazo el descendiente con peor 
		//fitness por el individuo con mejor fitness. 
		if (!encontradoMejor){

			descendientes[peorIndice]=poblacion[mejorIndice];

		}
		
		return descendientes;
	}

}
