
public class Summing {
	
	static public void main(String[] args) {
		long startTime = System.nanoTime();
	
		System.out.println(sum());
		
		System.out.println(System.nanoTime() - startTime);
	
		System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
	}
	
	static public long sum() {
		
		long answer = 0;
		
		for(long i = 1; i <= 1000000000; i++) {
			answer += i;
		}
		
		return answer;
	}
}
