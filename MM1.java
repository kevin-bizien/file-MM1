public class MM1 {
	public static double lambda;
	public static double mu;
	public static double dureeSim;
	public static int debug;
	private static Evt e;

	public static void main(String[] args){
		
		double time=System.currentTimeMillis();

		lambda=Double.parseDouble(args[0]);
		mu=Double.parseDouble(args[1]);
		dureeSim=Double.parseDouble(args[2]);
		debug=Integer.parseInt(args[3]);
				
		System.out.println("Calcul en cours...");
		
		Ech.fileMM1(); 		    //Programme principal (gestion de l'échéancier)
		
		Stats.statsTheo();		//Affichage des Résultats Théoriques
		Stats.statsSimu();		//Affichage des Résultats de Simulation

		double finaltime=(System.currentTimeMillis()-time)/1000;       //Affichage temps d'éxécution, conversion en seconde
		System.out.println("Temps d'execution : " + finaltime + "s");
	}
}