public class Fibonacci {
	
	public static void main(String[] args) {
		
		long n;
		
		try {
			n = Long.parseLong(args[0]);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Argument is not supplied");
			return;
		} catch (NumberFormatException e) {
			System.out.println("Argument should be integer");
			return;
		}
		
		long startTime = System.nanoTime();

		System.out.println(fib(n));
		
		System.out.println(System.nanoTime() - startTime);
	}
	
	public static long fib(long n) {
		if(n == 2 || n == 1) {
			return 1;
		} else {
			return fib(n - 1) + fib(n - 2);
		}
	}

}
