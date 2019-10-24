//package com.weizeng.algorithm.hm3;

import java.util.Scanner;

public class LongestIncreasingSubseqRecursive {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int num = Integer.valueOf(reader.nextLine());
		String[] strArr = reader.nextLine().split(" ");
		int[] numArr = new int[strArr.length];
		for(int i = 0; i < strArr.length; i++) {
			numArr[i] = Integer.valueOf(strArr[i]);
		}
		System.out.println(getLongestIncrSubseq(numArr));
	}
	
	public static int getLongestIncrSubseq(int[] numArr) {
		int maxLength = 0;
		for(int j = numArr.length - 1; j >= 0; j--) {
			int length = incrSubseqRecursive(j, numArr); 
			if (length > maxLength) {
				maxLength = length; 
			}
		}
		return maxLength;
	}
	
	
	public static int incrSubseqRecursive(int j, int[] numArr) {
		int maxLength = 1;
		if (j == 0) {
			return 1;
		}
		for(int i = j - 1; i >= 0; i--) {
			if (numArr[i] < numArr[j]) {
				int length = incrSubseqRecursive(i, numArr);
				if (maxLength < length + 1) {
					maxLength = length + 1;
				}
			}
		}
		return maxLength;	 
	}

}
