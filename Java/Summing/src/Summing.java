import java.io.IOException;
import java.math.BigInteger;

public class Summing {
	
	static public void main(String[] args) {
		long startTime = System.nanoTime();
	
		System.out.println(Long.MAX_VALUE);
		
		System.out.println((double)(System.nanoTime() - startTime) / 1000000000);
	}
	
	static public String sum(long n, boolean useBig) {
		
		if(useBig) {
			BigInteger answer = BigInteger.ZERO;
			
			for(long i = 1; i <= n; i++) {
				answer = answer.add(new BigInteger(Long.toString(i)));
			}
			
			return answer.toString();
		} else {
			long answer = 0;
			
			for(long i = 1; i <= n; i++) {
				answer += i;
			}
			
			return Long.toString(answer);
		}
	}
}
