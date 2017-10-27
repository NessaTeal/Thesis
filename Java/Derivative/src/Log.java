
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
		if(a.getClass() == E.class) {
			return new Const(1);
		} else {
			Derivable newA = a.simplify();
			return new Log(newA);
		}
	}
	
	@Override
	public String toString() {
		return "Log(" + a + ")";
	}
}