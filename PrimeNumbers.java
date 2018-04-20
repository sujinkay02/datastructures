import javax.swing.JOptionPane;

// prime numbers from 1 to 100
public class PrimeNumbers {
	
	// Return true if n is a prime number, false otherwise		
	public static boolean isPrime(int n) {
		// 2 is the only prime number that is even, so this can be checked once separately
		if (n == 2) {
			return true;
		}
		// A prime number is positive, and only divisible by itself and 1
		if ((n <= 1) || (n % 2) == 0) {
			return false;
		}
		
		// Only need to check up to the square root of the number (otherwise factors get repetitive)
		for (int i = 3; i <= (int)(Math.sqrt(n)); i++) {
			if ((n % i) == 0) {
				return false;
			}
		}
		return true;
	} // isPrime()

	// print the prime numbers from 1 to 100 to the console
	public static void main(String[] args) {
		String number = JOptionPane.showInputDialog("Enter a number: ");
		int num = Integer.parseInt(number);
		
		for (int i = 1; i <= num; i++) {
			if (isPrime(i)) {
				System.out.println(i);
			}
		}
	} // main()
} // PrimeNumbers()
