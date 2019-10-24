//package com.weizeng.algorithm.hm3;

import java.util.Scanner;



public class IntervalsBreaks {
	
	public static int findLargetNumberOfCourses(int n, int[][] interArr, int[][] costArr) {
		int max = 0;
		for(int i = 0; i < interArr.length - 1; i++) {
			for(int j = 0; j < interArr.length - 1 - i; j++) {
				if (interArr[j][1] > interArr[j + 1][1]) {
					int[] temp = interArr[j];
					interArr[j] = interArr[j + 1];
					interArr[j + 1] = temp;
					
					int[] tempTwo = costArr[j];
					costArr[j] = costArr[j + 1];
					costArr[j + 1] = tempTwo;
				}
			}
		}
		int[] s = new int[interArr.length];
		for(int j = 0; j < interArr.length; j++) {
			s[j] = 1;
			for(int k = j; k >= 0; k--) {
				if (interArr[k][1] + costArr[k][j] <= interArr[j][0] && s[j] < s[k] + 1) {
					s[j] = s[k] + 1;
				}
			}
			
		}
		for(int i = 0; i < s.length; i++) {
			if (s[i] > max) {
				max = s[i];
			}
		}
		
		return max;
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = Integer.valueOf(reader.nextLine());
		int[][] interArr = new int[n][2];
		for(int i = 0; i < n; i++) {
			String interStr = reader.nextLine();
			interArr[i][0] = Integer.valueOf(interStr.split(" ")[0]);
			interArr[i][1] = Integer.valueOf(interStr.split(" ")[1]);
		}
		int[][] costArr = new int[n][n];
		for(int i = 0; i < n; i++) {
			String interStr = reader.nextLine();
			String[] costStrArr = interStr.split(" ");
			for(int j = 0; j < n; j++) {
				costArr[i][j] = Integer.valueOf(costStrArr[j]);
			}
		}
		
		System.out.println(findLargetNumberOfCourses(n, interArr, costArr));
	}
}
