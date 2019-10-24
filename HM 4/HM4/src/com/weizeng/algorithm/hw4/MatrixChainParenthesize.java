//package com.weizeng.algorithm.hw4;

import java.util.Scanner;

public class MatrixChainParenthesize {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = Integer.valueOf(reader.nextLine());
		String Astr = reader.nextLine();
		String[] AstrArr = Astr.split(" ");
		int[] A = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			A[i] = Integer.valueOf(AstrArr[i]);
		}

		int S[][] = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			S[i][i] = 0;
		}

		int[][] M = new int[n + 1][n + 1];
		for (int d = 1; d <= n; d++) {
			for (int L = 1; L <= n - d; L++) {
				int R = L + d;
				S[L][R] = 9999999;
				for (int k = L; k <= R - 1; k++) {
					int temp = S[L][k] + S[k + 1][R] + A[L - 1] * A[k] * A[R];
					if (S[L][R] > temp) {
						S[L][R] = temp;
						M[L][R] = k;
					}
				}

			}
		}
		System.out.println(S[1][n]);

		String[][] result = new String[n + 1][n + 1];
		Result(1, n, M, result);
		System.out.println(result[1][n]);

	}

	public static void Result(int i, int j, int[][] s, String[][] result) {
		if (i < j) {
			Result(i, s[i][j], s, result);
			Result(s[i][j] + 1, j, s, result);
			if (i == s[i][j] && (s[i][j] + 1) == j) {
				result[i][j] = "( A" + (i) + " x A" + (j) + " )";
			} else if (i == s[i][j] && (s[i][j] + 1) != j) {
				result[i][j] = "( A" + (i) + " x " + result[i + 1][j] + " )";
			} else if (i != s[i][j] && (s[i][j] + 1) == j) {
				result[i][j] = "( " + result[i][s[i][j]] + " x A" + (j) + " )";
			} else {
				result[i][j] = "( " + result[i][s[i][j]] + " x " + result[s[i][j] + 1][j] + " )";
			}
		}
	}

}
