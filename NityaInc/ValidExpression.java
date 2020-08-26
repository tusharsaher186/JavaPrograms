
public class ValidExpression {

	public static void main(String[] args) {
		a.b.c = c.b.a;
		
		System.out.println("Program Runs and Compiles successfully with a.b.c = c.b.a;");
	}
}

class a {
	static class b {
		static int c;
	}

}

class c {
	static class b {
		static int a;
	}
}
