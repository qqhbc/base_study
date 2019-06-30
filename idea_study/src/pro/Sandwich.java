package pro;
class Meal{
	Meal(){System.out.println("Meal()");}
}
class Bread{
	Bread(){System.out.println("Bread()");}
}
class Cheese{
	Cheese(){System.out.println("Cheese()");}
}
class Lettuce{
	Lettuce(){System.out.println("Lettuce()");}
}
class Lunch extends Meal{
	Lunch(){System.out.println("Lunch");}
}
class ProtableLunch extends Lunch{
	ProtableLunch(){System.out.println("ProtableLunch()");}
}
public class Sandwich extends ProtableLunch{
	public Sandwich(){
		System.out.println("Sandwich()");
	}
		Bread b = new Bread();
		Cheese c = new Cheese();
		Lettuce l = new Lettuce();
		
		public static void main(String[] args) {
			new Sandwich();
	}
}
