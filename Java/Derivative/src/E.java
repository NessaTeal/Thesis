
public class E implements Derivable {
	
	@Override
	public Derivable derive() {
		return new Const(0);
	}

	@Override
	public Derivable simplify() {
		return this;
	}
	
	@Override
	public String toString() {
		return "e";
	}
}