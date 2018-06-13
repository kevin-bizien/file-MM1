import java.util.LinkedList;

public class Ech {
	static LinkedList<Evt> ech;
	static double dateDep;
	static double TmpsMoy;
	
	
	
	public static void insert(LinkedList<Evt> ech, Evt e){  //insertion de l'événement e dans l'éch en fonction de la palce qu'il doit avoir
		int i=0;
		while(i<ech.size()&&ech.get(i).date<e.date){       //tant qu'on n'atteint pas la taille de l'ech et
														   //que la date limite n'est pas dépassée 
			i++;
		}
			ech.add(i,e);                                  //on ajoute e à l'échéancier au bon emplacement (i)
	}

	public static LinkedList<Evt> processArr(LinkedList<Evt> ech, Evt e){
		Evt arr;
		Evt dep;

		double curTmps = e.date;
		int id = e.num;

		double arrTmps;
		double depTmps;
		double tMoy;

		Stats.statsArr(e);                         // si on a une arrivée, on utilise les stats pour les arrivées
		if(curTmps<=MM1.dureeSim){
			if((arrTmps=curTmps+Utile.timePlus(MM1.lambda))<=MM1.dureeSim){
				arr=new Evt(arrTmps,true);         //on créé l'événement arrivée
				arr.num=id+1;                      //on incrémente son numéro
				insert(ech,arr);                   //on l'insert dans l'ech
			}
		}

		depTmps=dateDep+Utile.timePlus(MM1.mu); 
		while(depTmps<curTmps){                   //on fait cela afin d'éviter que le départ n'arrive dans le bon ordre
			depTmps=dateDep+Utile.timePlus(MM1.mu); 
		}

		dep=new Evt(depTmps,false);              //on crée l'événement départ dès son arrivée
		dateDep=dep.date;                        //on lui attribue sa date de départ
		dep.num=id;                              //il garde le même numéro qu'en arrivant
		insert(ech,dep);                         //on l'insère dans l'échéancier

		tMoy=dateDep-curTmps;                    //calcul du temps moyen dans le systeme (reultats simulation)
		TmpsMoy=TmpsMoy+tMoy;

		return ech;
	}

	public static LinkedList<Evt> process(LinkedList<Evt> ech, Evt e){

		if(e.type==true){        // true = événement de type arrivée
			ech = processArr(ech,e);
		}

		else{					 // false = événement de type départ
			Stats.statsDep(e);   //sinon on utilise les stats pour les départs
		}
		return ech;              //on retourne l'échéancier
	}
	
	public static void fileMM1(){ 

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.###########");
		
		double arrTime;            //date d'arrivée du 2ème evénement

		Stats stats=new Stats();
		Evt e=new Evt();
		ech=new LinkedList<Evt>();

		insert(ech,e);             //on insère le 1er événement 
		
		Stats.statsArr(e);
		
		e=ech.poll();  
		
		if((arrTime=e.date+Utile.timePlus(MM1.lambda))<=MM1.dureeSim){ //si l'arrivée de l'événement suivant ne dépasse pas la date maximale
			Evt arr=new Evt(arrTime,true);
			arr.num=e.num+1;
			insert(ech,arr);
		}

		Evt dep=new Evt(e.date+Utile.timePlus(MM1.mu),false);
		dep.num=e.num;
		insert(ech,dep);
		dateDep=dep.date;
		
		while(!ech.isEmpty()){                  //on traite l'échéancier
			e=ech.poll(); 
			ech=process(ech,e); 
			if(MM1.debug==1){
				if(e.type==true){               //on affiche les arrivées
					System.out.println("Date : " + df.format(e.date) + "       Arrivee client n*" +e.num);
				}
				else{                           //sinon les départs
					System.out.println("Date : " + df.format(e.date) + "       Depart client n*" +e.num +"    Arrivé à t= " + Stats.listeTps.get(e.num));
				}
			}
		}
	}
}