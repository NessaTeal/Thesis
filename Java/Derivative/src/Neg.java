
public class Neg implements Derivable {
	public Neg(Derivable a) {
		this.a = a;
	}
	
	Derivable a;
	
	@Override
	public Derivable deriviate() {
		a = a.deriviate();
		return this;
	}

	@Override
	public Derivable simplify() {
		a = a.simplify();
		return this;
	}
}
