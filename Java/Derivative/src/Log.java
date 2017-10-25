
public class Log implements Derivable {
	
	public Log(Derivable a) {
		this.a = a;
	}
	
	Derivable a;
	
	@Override
	public Derivable deriviate() {
		return new Product(new Division(new Const(1), a), a.deriviate());
	}

	@Override
	public Derivable simplify() {
		a = a.simplify();
		return this;
	}
}