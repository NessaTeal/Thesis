
public class Const implements Derivable {

	public Const (double a) {
		this.a = a;
	}
	
	double a;
	
	@Override
	public Derivable deriviate() {
		return new Const(0);
	}

	@Override
	public Derivable simplify() {
		return this;
	}
	
	@Override
	public String toString() {
		return Double.toString(a);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(a);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Const other = (Const) obj;
		if (Double.doubleToLongBits(a) != Double.doubleToLongBits(other.a))
			return false;
		return true;
	}
}