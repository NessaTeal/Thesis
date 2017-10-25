
public class Division implements Derivable {
	
	public Division(Derivable a, Derivable b) {
		this.a = a;
		this.b = b;
	}
	
	Derivable a;
	Derivable b;
	
	@Override
	public Derivable deriviate() {
		a = new Sub(new Product(a.deriviate(),b), new Product(a,b.deriviate()));
		b = new Power(b, new Const(2));
		return this;
	}

	@Override
	public Derivable simplify() {
		
		Derivable aSimple = a.simplify();
		
		if(aSimple.equals(new Const(0))) {
			return new Const(0);
		} else {
			a = aSimple;
			b = b.simplify();
			return this;
		}
	}
	
	@Override
	public String toString() {
		return "(" + a + ")/(" + b + ")";
	}
}
