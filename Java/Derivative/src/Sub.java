
public class Sub implements Derivable {
	
	public Sub(Derivable a, Derivable b) {
		this.a = a;
		this.b = b;
	}
	
	Derivable a;
	Derivable b;
	
	@Override
	public Derivable deriviate() {
		return new Sum(a.deriviate(), b.deriviate());
	}
	
	@Override
	public Derivable simplify() {
		Derivable aSimple = a.simplify();
		Derivable bSimple = b.simplify();
		
		if(aSimple.equals(new Const(0))) {
			return new Neg(bSimple);
		} else if(bSimple.equals(new Const(0))) {
			return aSimple;
		} else {
			a = aSimple;
			b = bSimple;
			return this;
		}
	}
	
	@Override
	public String toString() {
		return a + " - " + b;
	}
}