package es.uned.mochila;

public class ReemplazoTotalConElitismo {
	int mejorIndice=0;
	double mejorFitness=0;
	
	//TODO Probablemente lo mejor sería que esto viniera en un objeto Poblacion que mantuviera referencia 
	//a su mejor miembro
	private void mejorElementoPoblacion(Individuo[] poblacion){
		
		for (int i=0;i<poblacion.length;i++){
			if (poblacion[i].getFitness()>mejorFitness){
				mejorFitness=poblacion[i].getFitness();
				mejorIndice=i;
			}
		}
	}
	
	//Calculo el elitismo segun consta descrito en Eiben y Smith. Es decir, sólo mantengo el 
	//mejor elemento de la población actual si no hay ningún desdenciente mejor.
	public Individuo[] reemplazo(Individuo[] poblacion, Individuo[] descendientes){
		int peorIndice=0;
		double peorFitness=descendientes[0].getFitness();
		boolean encontradoMejor=false;
		
		for (int i=1;(i<descendientes.length&&!encontradoMejor);i++){
			
			if (descendientes[i].getFitness()>=poblacion[mejorIndice].getFitness()){
				encontradoMejor=true;
			}else{
				if (descendientes[i].getFitness()<peorFitness){
					peorFitness=descendientes[i].getFitness();
					peorIndice=i;
				}
			}
		}
		
		if (!encontradoMejor){
			//mantengo el mejor de la población. Reemplazo el peor de los descendientes.
			descendientes[peorIndice]=poblacion[mejorIndice];
		}
		
		return descendientes;
	}

}
