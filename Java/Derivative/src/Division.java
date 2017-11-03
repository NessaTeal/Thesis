
public class Division implements Derivable {
	
	public Division(Derivable a, Derivable b) {
		this.a = a;
		this.b = b;
	}
	
	Derivable a;
	Derivable b;
	
	@Override
	public Derivable derive() {
		Derivable newA = new Sub(new Product(a.derive(),b), new Product(a,b.derive()));
		Derivable newB = new Power(b, new Const(2));
		return new Division(newA, newB);
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
		return "(" + a + " / " + b + ")";
	}
}
