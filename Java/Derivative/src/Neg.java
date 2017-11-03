
public class Neg implements Derivable {
	public Neg(Derivable a) {
		this.a = a;
	}
	
	Derivable a;
	
	@Override
	public Derivable derive() {
		a = a.derive();
		return this;
	}

	@Override
	public Derivable simplify() {
		a = a.simplify();
		return this;
	}
	
	@Override
	public String toString() {
		return "-" + a;
	}
}
