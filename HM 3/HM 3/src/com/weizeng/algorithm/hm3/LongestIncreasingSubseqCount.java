//package com.weizeng.algorithm.hm3;

import java.util.Scanner;

public class LongestIncreasingSubseqCount {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int num = Integer.valueOf(reader.nextLine());
		String[] strArr = reader.nextLine().split(" ");
		int[] numArr = new int[strArr.length];
		for (int i = 0; i < strArr.length; i++) {
			numArr[i] = Integer.valueOf(strArr[i]);
		}
		
		System.out.println(incrSubseqDP(numArr) % 1000000);
	}

	public static long  incrSubseqDP(int[] numArr) {
		long[] s = new long[numArr.length];
		for (int j = 0; j < numArr.length; j++) {
			s[j] = 1;
			for (int k = 0; k < j; k++) {
				if (numArr[k] < numArr[j]) {
					s[j] += s[k];
				}
			}
			s[j] = s[j] % 1000000;
		}
		long max = 0;
		for (int i = 0; i < s.length; i++) {
			max += s[i];
			
		}
		return max + 1;
	}

}
