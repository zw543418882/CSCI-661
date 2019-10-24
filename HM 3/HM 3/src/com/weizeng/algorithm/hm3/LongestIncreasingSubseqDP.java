//package com.weizeng.algorithm.hm3;

import java.util.Scanner;

public class LongestIncreasingSubseqDP {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int num = Integer.valueOf(reader.nextLine());
		String[] strArr = reader.nextLine().split(" ");
		int[] numArr = new int[strArr.length];
		for (int i = 0; i < strArr.length; i++) {
			numArr[i] = Integer.valueOf(strArr[i]);
		}
		
		System.out.println(incrSubseqDP(numArr));
	}

	public static int incrSubseqDP(int[] numArr) {
		int[] s = new int[numArr.length];
		for (int j = 0; j < numArr.length; j++) {
			s[j] = 1;
			for (int k = 0; k < j; k++) {
				if (numArr[k] < numArr[j] && s[j] < s[k] + 1) {
					s[j] = s[k] + 1;
				}
			}
		}
		int max = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i] > max) {
				max = s[i];
			}
		}
		return max;
	}

}
