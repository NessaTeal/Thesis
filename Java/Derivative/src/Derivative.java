
public class Derivative {

	public static void main(String[] args) {
		
		Derivable x = new Power(new Const(2), new X()).deriviate();
		
		System.out.println(x);
		System.out.println(x.simplify());
	}
}
