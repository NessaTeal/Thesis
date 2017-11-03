
public class Cos implements Derivable {
	
	public Cos(Derivable a) {
		this.a = a;
	}
	
	Derivable a;
	
	@Override
	public Derivable derive() {
		return new Product(new Neg(new Sin(a)), a.derive());
	}

	@Override
	public Derivable simplify() {
		a = a.simplify();
		return this;
	}
	
	@Override
	public String toString() {
		return "Cos(" + a + ")";
	}
}
