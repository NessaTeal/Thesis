
public class Const implements Derivable {

	public Const (double a) {
		this.a = a;
	}
	
	double a;
	
	@Override
	public Derivable deriviate() {
		a = 0;
		return this;
	}

	@Override
	public Derivable simplify() {
		return this;
	}
	
	@Override
	public String toString() {
		return Double.toString(a);
	}
}