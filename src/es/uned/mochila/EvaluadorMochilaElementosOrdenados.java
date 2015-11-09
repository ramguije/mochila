package es.uned.mochila;

import java.util.Arrays;

public class EvaluadorMochilaElementosOrdenados implements Evaluador {

	private Integer[] ordenObjetos=null;
	private Objeto[] objetos=null;
	private double capacidadMochila;
	
	public EvaluadorMochilaElementosOrdenados(double capacidad){
		this.capacidadMochila=capacidad;
	}
	
	@Override
	public void inicializar(Objeto[] objetos) {
		// TODO Auto-generated method stub
		//generar un array con los índices de los objetos ordenados en función del ratio
		
		/*for (int i=0;i<objetos.length;i++){
			System.out.println("indice :"+i+" valor :"+objetos[i].getRatio());
		}*/
		
		this.objetos=objetos;
		
		//Genero un array de índices que será el que ordene para no alterar el orden real de los objetos.
		ordenObjetos=new Integer[objetos.length];
		for (int i=0;i<ordenObjetos.length;i++){
			ordenObjetos[i]=i;
		}
		
		Arrays.sort(ordenObjetos, 
				(first,second) -> Double.compare(objetos[first].getRatio(), objetos[second].getRatio())*-1);
		
		/*for (int i=0;i<ordenObjetos.length;i++){
			System.out.println("indice :"+i+" orden indice :"+ordenObjetos[i]+" valor: "+objetos[ordenObjetos[i]].getRatio());
		}*/
		
	}

	@Override
	public void evaluar(Individuo[] elementos) {
		//Nota, calcular aquí las medias y el máximo de fitness en cada generación
		
		System.out.println ("Tamaño de la mochila: "+this.capacidadMochila);
		for (Individuo ind:elementos){
			//Para cada elemento calculo su función fitness y se la añado
			ind.setFitness(this.funcionFitness(ind));
			
		}

	}
	
	private double funcionFitness(Individuo ind)
	{
		boolean[] genotipo=ind.getGenotipo();
		double totalVolumen=0;
		double totalValor=0;
		boolean mochilaLlena=false;
		
		/*System.out.println ("Función Fitness. Imprimo el genotipo ordenado");
		
		for (int i=0;i<ordenObjetos.length;i++)
		{
			if (genotipo[ordenObjetos[i]]==true){
				System.out.print ("1");
			}else{
				System.out.print ("0");
			}
		}
		System.out.println("");*/
		
		
		for (int i=0;i<ordenObjetos.length&&!mochilaLlena;i++){
			if ((genotipo[ordenObjetos[i]]==true) && 
					(totalVolumen+this.objetos[ordenObjetos[i]].getVolumen()<=this.capacidadMochila)){
				//System.out.println ("El objeto "+i+" está y cabe en la mochila");
				//System.out.println ("Valor: "+this.objetos[ordenObjetos[i]].getValor());
				totalValor=totalValor+this.objetos[ordenObjetos[i]].getValor();
				totalVolumen=totalVolumen+this.objetos[ordenObjetos[i]].getVolumen();
				if (totalVolumen==this.capacidadMochila) 
					mochilaLlena=true;
				
			}
			
		}
		System.out.println ("Total Valor: "+totalValor);
		System.out.println ("Total Volumen: "+totalVolumen);
		System.out.println ("Capacidad mochila: "+this.capacidadMochila);
		return totalValor;
		
	}
	

}
