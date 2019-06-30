package pro;

import java.util.Random;

class Status{
	public void status(){
		System.out.println("正常");
	}
}
class AlertStatus extends Status{
	public void status(){
		System.out.println("警告");
	}
}
class DangerStatus extends Status{
	public void status(){
		System.out.println("危险");
	}
}
class DestroyStatus extends Status{
	public void status(){
		System.out.println("销毁");
	}
}
class StatusFactory{
	static Random random = new Random();
	public static Status change(){
		switch(random.nextInt(3)){
		default:return new Status();
		case 0: return new AlertStatus();
		case 1: return new DangerStatus();
		case 2: return new DestroyStatus();
		}
	}
}
public class Starship {
	public static void main(String[] args) {
		StatusFactory.change().status();
	}
}
