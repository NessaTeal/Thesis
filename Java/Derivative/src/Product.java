
public class Product implements Derivable {

	public Product (Derivable a, Derivable b) {
		this.a = a;
		this.b = b;
	}
	
	Derivable a;
	Derivable b;
	
	@Override
	public Derivable deriviate() {
		return new Sum(new Product(a.deriviate(), b), new Product(a,b.deriviate()));
	}

	@Override
	public Derivable simplify() {

		Derivable aSimple = a.simplify();
		Derivable bSimple = b.simplify();
		
		if(aSimple.equals(new Const(0)) || bSimple.equals(new Const(0))) {
			return new Const(0);
		} else if(aSimple.equals(new Const(1))) {
			return bSimple;
		} else if(bSimple.equals(new Const(1))) {
			return aSimple;
		} else {
			a = aSimple;
			b = bSimple;
			return this;
		}
	}
	
	@Override
	public String toString() {
		return "( " + a + "*" + b + ")";
	}
}
