import java.util.LinkedList;

public class Stats {
	static LinkedList<Double> listeTps;				//liste des temps d'arrivee
	static double nbClients;						//nombre de clients dans le système
	static double nbEvnmt;						    //nombre total de clients
	static double sansAtt;							//nombre de clients servis sans attente
	static double nbClientsMoy;						//nombre moyen de clients dans le systeme
	static double tpsMoy;							//temps moyen de séjour
	static double tpsSimu;							//temps de simulation
	
	public Stats(){
		listeTps=new LinkedList<Double>();			//afin de récupérer le temps d'arrivée d'un événement de type départ
		nbEvnmt=0;
		nbClients=0;
		nbClientsMoy=0;
		sansAtt=0;
		tpsMoy=0;
		tpsSimu=0;
	}

	public static void statsTheo() {              // Calcul des résultats théoriques
		System.out.println("\n--------------------");
		System.out.println("RESULTATS THEORIQUES");
		System.out.println("--------------------");
		if (MM1.lambda < MM1.mu)  {
			System.out.println("lambda < mu : file stable");
		}
		else {
			System.out.println("lambda > mu : file instable");
		}

		double ro= MM1.lambda/MM1.mu;
		System.out.println("ro (lambda/mu) = " + ro);

		System.out.println("Nombre de clients attendus (lambda x duree) = " + MM1.lambda*MM1.dureeSim);

		System.out.println("Prob de service sans attente (1-ro) = " + (1-ro));
		System.out.println("Prob file occupée (ro) = " + ro);

		System.out.println("Débit (lambda) = " + MM1.lambda);

		double nbClients = ro/(1-ro);
		System.out.println("Esp nb de clients (ro/1-ro) = " + nbClients);

		double tpsMoyenSejour = 1/(MM1.mu*(1-ro));
		System.out.println("Temps moyen de séjour (1/mu(1-ro)) = " + tpsMoyenSejour);
	}
	
	public static void statsArr(Evt e){      //traitement statistique d'une arrivée
		nbEvnmt++;                          
		listeTps.add(e.date);
		if(e.type==true && nbClients==0 || nbClients==nbEvnmt/2){   //si le dernier est sorti ou que c'est le premier
			sansAtt++; 
		}
		nbClients++;                         
	}
	
	public static void statsDep(Evt e){           //traitement statistique d'un départ 
		tpsSimu=e.date;
		nbClients--;   

		tpsMoy=Ech.TmpsMoy/nbEvnmt;
	}
	
	public static void statsSimu(){                    //Calculs des Resultats de simulation

		System.out.println("\n--------------------");
		System.out.println("RESULTATS SIMULATION");
		System.out.println("--------------------");

		System.out.println("Nombre total de clients = " + nbEvnmt);

		double proporSansAtt=sansAtt/nbEvnmt;          //proportion de clients servis sans attente
		System.out.println("Proportion clients sans attente = " + proporSansAtt);
		System.out.println("Proportion clients avec attente = " + (1-proporSansAtt));

		double debit=nbEvnmt/tpsSimu;                  //débit de la file
		System.out.println("Debit = " + debit);

		System.out.println("Temps moyen de séjour = " + tpsMoy + "\n");
	}
}