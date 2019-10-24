//package com.weizeng.algorithm.hm2;
import java.util.Scanner;
/*
 * Wei Zeng
 * HW2-Problem 1
 * 09/28/2018
 * */

public class MaxRectanglePerimeter {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = Integer.valueOf(reader.nextLine());
		int[][] pointsA = new int[n + 1][2];
		for(int i = 1; i <= n; i++) {
			String tempS = reader.nextLine();
			String[] tempStrA = tempS.split(" ");
			pointsA[i][0] = Integer.valueOf(tempStrA[0]);
			pointsA[i][1] = Integer.valueOf(tempStrA[1]);
		}
		
		//find all rising and falling points
		//O(n)
		int[] upArr = new int[(n - 2) / 2 + 1];
		int[] downArr = new int[(n - 2) / 2 + 1];
		int upIndex = 0, downIndex = 0;
		for(int i = 2; i <= n; i = i + 2) {
			if (pointsA[i][1] > pointsA[i - 1][1]) {
				upArr[upIndex] = i;
				upIndex++;
			}else {
				downArr[downIndex] = i;
				downIndex++;
			}
		}
		
		//find matched points
		//O(n)
		int[][] recArr = new int[upIndex][2];
		int recArrIndex = 0;
		int tempUp = 0;
		int tempDown = 0;
		while(tempUp < upIndex) {
			if(pointsA[downArr[tempDown]][1] <= pointsA[upArr[tempUp] - 1][1] && pointsA[downArr[tempDown]][0] > pointsA[upArr[tempUp]][0]) {
				recArr[recArrIndex][0] = upArr[tempUp];
				recArr[recArrIndex][1] = downArr[tempDown];
				recArrIndex++;
				tempUp++;
				tempDown = 0;
			}else {
				tempDown++;
			}
		}
		
	

		//find lower point and calculate perimeters
		//O(n)
		int[] perArr = new int[upIndex];
		int perArrIndex = 0;
		
//		int k = 0;
//		int t = 0;
//		int width = 0;
//		while(k < recArrIndex) {
//			int tempPoint = recArr[k][0];
//			int tempPointD = recArr[k][1];
//			t = tempPoint;
//			if (t == tempPoint) {
//				width = pointsA[tempPoint][1];
//			}
//			if(t >= tempPointD) {
//				perArr[perArrIndex] = (pointsA[tempPointD][0] - pointsA[tempPoint][0] + width) * 2;
//				perArrIndex++;
//				k++;
//				t = 0;
//			}else {
//				if (pointsA[t][1] < width) {
//					width = pointsA[t][1];
//					t++;
//				}else {
//						t++;
//						}
//			}
//			
//		}
		//O(n^2) bad function 
		for(int i = 0; i < recArrIndex; i++) {
			int leftPoint = recArr[i][0];
			int rightPoint = recArr[i][1];
			int lenght = pointsA[rightPoint][0] - pointsA[leftPoint][0];
			int width = pointsA[leftPoint][1];
			for(int j = leftPoint; j <rightPoint; j++) {
				if(pointsA[j][1] < width) {
					width = pointsA[j][1];
				}
			}
			perArr[perArrIndex] = (lenght + width) * 2;
			perArrIndex++;
		}
		//find the largest perimeter
		//O(n)
		int largestPer = 0;
		for(int i = 0; i < perArrIndex; i++) {
			if (perArr[i] > largestPer) {
				largestPer = perArr[i];
			}
		}
		System.out.println(largestPer);
	
	

	}

}
