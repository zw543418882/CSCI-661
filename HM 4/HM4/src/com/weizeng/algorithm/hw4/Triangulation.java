//package com.weizeng.algorithm.hw4;

import java.util.Scanner;

public class Triangulation {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = Integer.valueOf(reader.nextLine());
		String coorStr = reader.nextLine();
		if (coorStr.length() > 0) {
			
		}else {
			coorStr = reader.nextLine();
		}
		String[] coorStrArr = coorStr.split(" ");
		float[][] coorArr = new float[2 * n][2];
		int index = 1;
		for (int i = 0; i < coorStrArr.length; i += 2) {
			coorArr[index][0] = Float.valueOf(coorStrArr[i]);
			coorArr[index][1] = Float.valueOf(coorStrArr[i + 1]);
			index++;
		}

		double S[][] = new double[n + 1][n + 1];
		for (int d = 2; d <= n - 1; d++) {
			for (int i = 1; i <= n - d; i++) {
				int j = i + d;
				S[i][j] = Double.MAX_VALUE;
				for (int k = i + 1; k < j; k++) {
					if (d <= 2) {
						S[i][j] = 0;
					} else if (k == i + 1) {
						S[i][j] = Math.min(S[i][j], S[k][j] + Math.sqrt(Math.pow(coorArr[k][0] - coorArr[j][0], 2)
								+ Math.pow(coorArr[k][1] - coorArr[j][1], 2)));
					} else if (k == j - 1) {
						S[i][j] = Math.min(S[i][j], S[i][k] + Math.sqrt(Math.pow(coorArr[k][0] - coorArr[i][0], 2)
								+ Math.pow(coorArr[k][1] - coorArr[i][1], 2)));
					} else {
						S[i][j] = Math.min(S[i][j],
								S[i][k] + S[k][j]
										+ Math.sqrt(Math.pow(coorArr[k][0] - coorArr[j][0], 2)
												+ Math.pow(coorArr[k][1] - coorArr[j][1], 2))
										+ Math.sqrt(Math.pow(coorArr[k][0] - coorArr[i][0], 2)
												+ Math.pow(coorArr[k][1] - coorArr[i][1], 2)));
					}
				}

			}
		}
	
		 System.out.printf("%.4f", S[1][n]);
		

	}

}
