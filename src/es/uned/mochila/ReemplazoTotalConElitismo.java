package es.uned.mochila;

public class ReemplazoTotalConElitismo {
	
	//Calculo el elitismo segun consta descrito en Eiben y Smith. Es decir, s�lo mantengo el 
	//mejor elemento de la poblaci�n actual si no hay ning�n desdenciente mejor.
	public Individuo[] reemplazo(Individuo[] poblacion, Individuo[] descendientes){
		int mejorIndice=0;
		double mejorFitness=0;
		int peorIndice=0;
		double peorFitness=descendientes[0].getFitness();
		boolean encontradoMejor=false;
		
		//TODO Probablemente lo mejor ser�a que esto viniera en un objeto Poblacion que mantuviera referencia 
		//a su mejor miembro
		for (int i=0;i<poblacion.length;i++){
			if (poblacion[i].getFitness()>mejorFitness){
				mejorFitness=poblacion[i].getFitness();
				mejorIndice=i;
			}
		}
		
		//System.out.println("fitness mejor elemento poblaci�n="+poblacion[mejorIndice].getFitness());
		//System.out.println("mejor indice "+mejorIndice);
		
		if (descendientes[0].getFitness()>=mejorFitness){
			encontradoMejor=true;
		}
		
		for (int i=1;((i<descendientes.length)&&(!encontradoMejor));i++){
			
			if (descendientes[i].getFitness()>=mejorFitness){
				encontradoMejor=true;
			}else{
				if (descendientes[i].getFitness()<peorFitness){
					peorFitness=descendientes[i].getFitness();
					peorIndice=i;
				}
			}
		}
		
		//System.out.println("fitness peor elemento descendiente="+descendientes[peorIndice].getFitness());
		
		if (!encontradoMejor){
			//mantengo el mejor de la poblaci�n. Reemplazo el peor de los descendientes.
			//System.out.println("No encontrado ning�n descendiente mejor. Se sustituye.");
			//System.out.println(descendientes[peorIndice].getFitness());
			descendientes[peorIndice]=poblacion[mejorIndice];
			//System.out.println("por");
			//System.out.println("Fitness: "+ descendientes[peorIndice].getFitness());
			//System.out.println("Indice:" +peorIndice);
			//System.out.println("");
		}
		
		return descendientes;
	}

}
