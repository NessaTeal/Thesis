
public class X implements Derivable {
	
	@Override
	public Derivable deriviate() {
		return new Const(1);
	}
	
	@Override
	public Derivable simplify() {
		return this;
	}
	
	@Override
	public String toString() {
		return "x";
	}
}