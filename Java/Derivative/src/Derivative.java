public class Derivative {

	public static void main(String[] args) {
		
		long startTime = System.nanoTime();

		Derivable x = new Product(new X(), new Power(new E(), new Sum(new X(), new Const(1))));
		System.out.println(x.derive().simplify());
		
		System.out.println(System.nanoTime() - startTime);
	}
}
