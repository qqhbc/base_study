package pro;

class Userful {
	public void u() {
		System.out.println("userful u");
	}

	public void f() {
		System.out.println("userful f");
	}
}

class MoreUserful extends Userful {
	public void u() {
		System.out.println("moreUserful u");
	}

	public void f() {
		System.out.println("moreUserful f");
	}

	public void m() {
		System.out.println("moreUserful m");
	}
}

class LessUserful {
	public void u() {
		System.out.println("LessUserful u");
	}

	public void f() {
		System.out.println("LessUserful f");
	}

	public void m() {
		System.out.println("LessUserful m");
	}
}

public class RTTI {
	public static void main(String[] args) {
		Userful [] userfuls = {new Userful(),new MoreUserful()};
		userfuls[0].f();
		userfuls[1].u();
		((MoreUserful) userfuls[1]).m();
	}
}
