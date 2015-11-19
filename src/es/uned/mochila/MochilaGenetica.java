package es.uned.mochila;

public class MochilaGenetica {
	static final int REPETICIONES=50;
	
	public static void main(String[] args) {
		Estadistica resultado;
		
		ProblemaMochila problema=new ProblemaMochila();
		problema.printConfiguracion();
		
		for (int i=0; i<REPETICIONES;i++){
			resultado=problema.run();
			System.out.println("Ejecución "+i+": ");
			resultado.imprimir();
		}

		
	}
	

}
