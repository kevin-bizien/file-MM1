import java.lang.Math;
import java.util.Random;

public class Utile {	
	private static Random random;
	public static double timePlus(double par){
		random = new Random();
		double p = random.nextDouble();
		
		while (p==0 || p==1) {              // tant que p=0 ou p=1 on refait un random 
			p = random.nextDouble();
		}	
		return (- Math.log(1-p)/par);
	}
}