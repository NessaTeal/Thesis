
public class Sin implements Derivable {
	public Sin(Derivable a) {
		this.a = a;
	}
	
	Derivable a;
	
	@Override
	public Derivable derive() {
		return new Product(new Cos(a), a.derive());
	}

	@Override
	public Derivable simplify() {
		a = a.simplify();
		return this;
	}
	
	@Override
	public String toString() {
		return "Sin(" + a + ")";
	}
}
