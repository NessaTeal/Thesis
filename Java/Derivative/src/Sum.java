
class Sum implements Derivable {

	public Sum (Derivable a, Derivable b) {
		this.a = a;
		this.b = b;
	}
	
	Derivable a;
	Derivable b;
	
	@Override
	public Derivable deriviate() {
		a = a.deriviate();
		b = b.deriviate();
		return this;
	}
	
	@Override
	public Derivable simplify() {
		Derivable aSimple = a.simplify();
		Derivable bSimple = b.simplify();
		
		if(aSimple.equals(new Const(0))) {
			return bSimple;
		} else if(bSimple.equals(new Const(0))) {
			return aSimple;
		} else {
			a = aSimple;
			b = bSimple;
			return this;
		}
	}
}