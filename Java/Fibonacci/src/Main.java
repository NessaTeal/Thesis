public class Main {
	
	public static void main(String[] args) {
		
		long startTime = System.nanoTime();

		System.out.println(fib(40));
		
		System.out.println(System.nanoTime() - startTime);

		System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
	}
	
	public static long fib(long n) {
		if(n == 2 || n == 1) {
			return 1;
		} else {
			return fib(n - 1) + fib(n - 2);
		}
	}

}
