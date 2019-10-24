//package com.weizeng.algorithm.hw4;

import java.util.Scanner;

public class LongestConvexSubseq {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = Integer.valueOf(reader.nextLine());
		String arrStr = reader.nextLine();
		String[] strArr = arrStr.split(" ");
		int[] numArr = new int[strArr.length];
		for(int i = 0; i < strArr.length; i++) {
			numArr[i] = Integer.valueOf(strArr[i]);
		}
		
		int[][] max = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				max[i][j] = 0;
			}
		}
		for(int i = 2; i < n; i++) {
			for(int j = i - 1; j >= 1; j--) {
				max[i][j] = 0;
				for(int k = j - 1; k >= 0; k--) {
					if (numArr[i] >= 2 * numArr[j] - numArr[k] && max[j][k] + 1 > max[i][j]) {
						max[i][j] = max[j][k] + 1;
					}
				}
			}
		}
		
		int maxN = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if (max[i][j] > maxN) {
					maxN = max[i][j];
				}
			}
		}
		System.out.println(maxN + 2);
	

	}

}
