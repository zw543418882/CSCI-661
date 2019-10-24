import java.util.Scanner;

 
public class Primes {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n  = reader.nextInt(); //Read an integer from keyboard
		for (int i = 0; i <= n; i++) {
			/*
			 * Find all of primes less than or equal to the integer from user
			 * Step1: Traverse numbers from 0 to n (Time Complexity: O(n))
			 * Step2: Find the first factor besides 1 of current number, if the factor is equal to the current number, print it (Time Complexity: O(n))
			 * Total Time Complexity: O(n^2) 
			 * */
			if (i > 1) {
				int j = 2;
				while (i % j != 0) {
					j++;
				}
				if (i == j) {
					System.out.println(i);
				}
			}
		}
		reader.close();
	}
	
}
