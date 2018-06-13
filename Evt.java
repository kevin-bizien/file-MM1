
public class Evt {
	double date;             //date de l'évènement (arrivée ou départ)
	boolean type;            //true=arrivée, false=départ
	int num;                 //numéro de l'évènement
	
	public Evt(){
		type=true;
		date=0;
	}
	
	public Evt(double date, boolean type){
		this.type=type;
		this.date=date;
	}
}