import java.util.Scanner;

public class SmallestTwo {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt(); // Read an nonnegative integer as the number of lines
		int smallestOne = 0, smallestTwo = 0;
		if (n == 0) {
			// if the number of lines is 0, nothing to do
		}else if (n == 1) {
			// if the number of lines is 1, the smallest number is the number of line 1
			smallestOne = reader.nextInt();
		}else if (n == 2) {
			// if the number of lines is 2, compare them 
			smallestOne = reader.nextInt();
			int tempN = reader.nextInt();
			if (tempN < smallestOne) {
				smallestTwo = smallestOne;
				smallestOne = tempN;
			}else {
				smallestTwo = tempN;
			}
		}else {
			/*
			 * if the number of lines is greater than 1 
			 * 1.compare the number of the first line with the second line, and then pass the smaller value to the variable SmallestOne, pass the other value to the variable SmallestTwo
			 * 2.read the rest numbers and compare with the SmallestOne and SmallestTwo
			 * 3.if the number is less than SmallestTwo but greater than SmallestOne, pass the number to SmallestTwo
			 * 4.if the number is less than SmallestOne, pass SmallestOne to SmallestTwo, and then pass the number to SmallestOne
			 * */
			for (int i = 0; i < n; i++) {
				if (i == 0) {
					smallestOne = reader.nextInt();
				}else if (i == 1) {
					int tempN = reader.nextInt();
					if (tempN < smallestOne) {
						smallestTwo = smallestOne;
						smallestOne = tempN;
					}else {
						smallestTwo = tempN;
					}
				}else {
					int tempN = reader.nextInt();
					if (tempN < smallestTwo && tempN > smallestOne) {
						smallestTwo = tempN;
					}else if (tempN < smallestOne) {
						smallestTwo = smallestOne;
						smallestOne = tempN;
					}
				}	
			}
		}
		if (n == 0) {
			
		}else if (n == 1) {
			System.out.print(smallestOne);
		}else {
			System.out.println(smallestOne + "\n" + smallestTwo);
		}
		reader.close();
	}
}
