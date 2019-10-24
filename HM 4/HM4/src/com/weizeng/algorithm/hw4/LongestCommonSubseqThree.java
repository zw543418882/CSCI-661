//package com.weizeng.algorithm.hw4;

import java.util.Scanner;

public class LongestCommonSubseqThree {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String pqrStr = reader.nextLine();
		String[] pqrStrArr = pqrStr.split(" ");
		int p = Integer.valueOf(pqrStrArr[0]);
		int q = Integer.valueOf(pqrStrArr[1]);
		int r = Integer.valueOf(pqrStrArr[2]);
		
		String aStr = reader.nextLine();
		String[] aStrArr = aStr.split(" ");
		int[] A = new int[p];
		for(int i = 0; i < p; i++) {
			A[i] = Integer.valueOf(aStrArr[i]);
		}
		
		String bStr = reader.nextLine();
		String[] bStrArr = bStr.split(" ");
		int[] B = new int[q];
		for(int i = 0; i < q; i++) {
			B[i] = Integer.valueOf(bStrArr[i]);
		}
		
		String cStr = reader.nextLine();
		String[] cStrArr = cStr.split(" ");
		int[] C = new int[r];
		for(int i = 0; i < r; i++) {
			C[i] = Integer.valueOf(cStrArr[i]);
		}
		
		int[][][] S = new int[p + 1][q + 1][r + 1];
		for(int i = 0; i < p; i++) {
			for(int j = 0; j < q; j++) {
				for(int k = 0; k < r; k++) {
					if (i == 0 || j == 0 || k == 0) {
						S[i][j][k] = 0;
					}
				}
			}
		}
		
		class Index {
			int i = -1;
			int  j = -1;
			int k = -1;
			int num = -1;
			
		}
		Index[][][] indexs = new Index[p + 1][q + 1][r + 1]; 
		for(int i = 0; i <= p; i++) {
			for(int j = 0; j <= q; j++) {
				for(int k = 0;k <= r; k++) {
					if (i == 0 || j == 0 || k == 0) {
						Index tempIn = new Index();
						indexs[i][j][k] = tempIn;
					}
				}
			}
		}
		for(int i = 1; i <= p; i++) {
			for(int j = 1; j <= q; j++) {
				for(int k = 1; k <= r; k++) {
					int tempMax = 0;
					int flag = 0;
					if (S[i - 1][j][k] >= S[i][j - 1][k] && S[i - 1][j][k] >= S[i][j][k - 1]) {
						tempMax = S[i - 1][j][k];
						flag = 1;
					}
					if (S[i][j - 1][k] >= S[i - 1][j][k] && S[i][j - 1][k] >= S[i][j][k - 1]) {
						tempMax = S[i][j - 1][k];
						flag = 2;
					}
					if (S[i][j][k - 1] >= S[i - 1][j][k] && S[i][j][k - 1] >= S[i][j - 1][k]) {
						tempMax = S[i][j][k - 1];
						flag = 3;
					}
					S[i][j][k] = tempMax;
					
					Index tempIndex = new Index();
					if (flag == 1) {
						tempIndex.i = i - 1;
						tempIndex.j = j;
						tempIndex.k = k;
					}else if (flag == 2) {
						tempIndex.i = i;
						tempIndex.j = j - 1;
						tempIndex.k = k;
					}else if (flag == 3) {
						tempIndex.i = i;
						tempIndex.j = j;
						tempIndex.k = k - 1;
					}
					
					indexs[i][j][k] = tempIndex;
					
					if (A[i - 1] == B[j - 1] && A[i - 1] == C[k - 1]) {
						S[i][j][k] = S[i - 1][j - 1][k - 1] + 1;
						Index index = new Index();
						index.i = i - 1;
						index.j = j - 1;
						index.k = k - 1;
						index.num = A[i - 1]; 
						indexs[i][j][k] = index;
					}
				}
			}
		}
		System.out.println(S[p][q][r]);
		
	
		
		int tempI = p;
		int tempJ = q;
		int tempK = r;
		int[] results = new int[p];
		int indexR = 0;
		while(tempI != 0 && tempJ != 0 && tempK != 0) {
			if (indexs[tempI][tempJ][tempK].num >= 0) {
				results[indexR] = indexs[tempI][tempJ][tempK].num;
				indexR++;
			}
			
			int temppI = indexs[tempI][tempJ][tempK].i;
			int temppJ = indexs[tempI][tempJ][tempK].j;
			int temppK = indexs[tempI][tempJ][tempK].k;
			tempI = temppI;
			tempJ = temppJ;
			tempK = temppK;
		}
		for(int i = indexR - 1; i >= 0; i--) {
			System.out.print(results[i] + " ");
		}
		
	
		
	}

}
