
public class E implements Derivable {
	
	@Override
	public Derivable deriviate() {
		return new Const(0);
	}

	@Override
	public Derivable simplify() {
		return this;
	}
}