import java.math.BigInteger;

public class Summing {
	
	static public void main(String[] args) {
		
		long n;
		boolean useBig;
		
		try {
			n = Long.parseLong(args[0]);
			useBig = Boolean.parseBoolean(args[1]);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Not enough arguments");
			return;
		} catch(NumberFormatException e) {
			System.out.println("Argument is not an integer");
			return;
		}
		
		long startTime = System.nanoTime();
	
		if(useBig) {
			System.out.println(sumBigInteger(n));
		} else {
			System.out.println(sumLong(n));
		}
		
		System.out.println((double)(System.nanoTime() - startTime) / 1000000000);
	}
	
	static public long sumLong(long n) {
		long answer = 0;
		
		for(long i = 1; i <= n; i++) {
			answer += i;
		}
		
		return answer;
	}
	
	static public BigInteger sumBigInteger(long n) {
		BigInteger answer = BigInteger.ZERO;
		
		for(long i = 1; i <= n; i++) {
			answer = answer.add(BigInteger.valueOf(i));
		}
		
		return answer;
	}
}
