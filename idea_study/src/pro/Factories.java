package pro;

interface Service{
	void method1();
	void method2();
}
interface ServiceFactory{
	Service getService();
}
class Implemention1 implements Service{
	Implemention1(){}
	@Override
	public void method1() {
		System.out.println("Implemention1 method1");
	}

	@Override
	public void method2() {
		System.out.println("Implemention1 method2");
	}
	
}

class Implemention1Factory implements ServiceFactory{

	@Override
	public Service getService() {
		return new Implemention1();
	}
	
}

class Implemention2 implements Service{
	Implemention2(){}
	@Override
	public void method1() {
		System.out.println("Implemention2 method1");
	}

	@Override
	public void method2() {
		System.out.println("Implemention2 method2");
	}
	
}

class Implemention2Factory implements ServiceFactory{

	@Override
	public Service getService() {
		return new Implemention2();
	}
	
}
public class Factories {
	public static void serviceConsumer(ServiceFactory serviceFactory){
		Service service = serviceFactory.getService();
		service.method1();
		service.method2();
	}
	public static void main(String[] args) {
		serviceConsumer(new Implemention1Factory());
		serviceConsumer(new Implemention2Factory());
	}
}
