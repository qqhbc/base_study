package pro;

import java.util.Random;

public class RandommDoubles {
	private static Random rand = new Random();
	public double next(){
		return rand.nextDouble();
	}
	public static void main(String[] args) {
		RandommDoubles rd = new RandommDoubles();
		for(int i=0;i<7;i++){
			System.out.println(rd.next());
		}
	}
}
