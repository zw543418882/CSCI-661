//package com.weizeng.algorithm.hw4;

import java.util.Scanner;

public class MinWeightKnapsack {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String nCStr = reader.nextLine();
		int n = Integer.valueOf(nCStr.split(" ")[0]);
		int C = Integer.valueOf(nCStr.split(" ")[1]);
		
		int[] weights = new int[n];
		int[] costs = new int[n];
		for(int i = 0; i < n; i++) {
			String tempStr = reader.nextLine();
			int tempW = Integer.valueOf(tempStr.split(" ")[0]);
			int tempC = Integer.valueOf(tempStr.split(" ")[1]);
			weights[i] = tempW;
			costs[i] = tempC;
		}
		
		int[][] S = new int[n + 1][C + 1];
		for(int i = 0; i <= n; i++) {
			S[i][0] = 99999999;
		}
		for(int i = 0; i <= C; i++) {
			S[0][i] = 99999999;
		}
		
		for(int i = 1; i <= C; i++) {
			for(int j = 1; j <= n; j++) {
				S[j][i] = S[j-1][i];
				if (costs[j - 1] >= i) {
					if (weights[j - 1] < S[j - 1][i]) {
						S[j][i] = weights[j - 1];
					}
				}else {
						if (S[j - 1][i - costs[j - 1]] + weights[j - 1] < S[j][i]) {
							S[j][i] = S[j - 1][i - costs[j - 1]] + weights[j - 1];
						}
				}
			}
		}
		System.out.println(S[n][C]);
	}

}
