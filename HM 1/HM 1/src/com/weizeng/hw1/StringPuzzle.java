//package com.weizeng.hw1;
import java.util.Scanner;
/*
 * Wei Zeng
 * Algorithms.HW1.Problem5
 * 09/14/2018
 * */



public class StringPuzzle {
	
	public static boolean isSame(int[] a, int i){
		boolean isSameFlag = false;
		if (a[i] * a[i - 1] > 0) {
			isSameFlag = true;
		}
		return isSameFlag;
	}
	
	public static boolean isDifferent(int[] a, int i){
		boolean isDifferentFlag = false;
		if (a[i] * a[i - 1] <= 0) {
			isDifferentFlag = true;
		}
		return isDifferentFlag;
	}
	
	public static int absV(int i) {
		if (i < 0) {
			i = i * (-1);
		}
		return i;
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String twoWords = reader.nextLine();
		String[] twoWwordsA = twoWords.split(" ");
		String word1 = twoWwordsA[0];
		String word2 = twoWwordsA[1];
		char[] word1A = word1.toCharArray();
		char[] word2A = word2.toCharArray();
		int[] diff = new int[word1A.length];
		for(int i = 0; i < word1A.length; i++) {
			diff[i] = word2A[i] - word1A[i];
		}
		
		int maxV = 0;
		int sum = 0;
		int troughS = 0;
		int minV = 0;
		boolean hasMin = false;
		boolean isStart = true;
		int startIndex = 0;
		int minIndex = 0;
		
		
		if(isSame(diff, 1)) {
			if (absV(diff[0]) > absV(diff[1])) {
				maxV = absV(diff[0]);
				isStart = false;
				minV = absV(diff[1]);
				troughS += absV(diff[0]);
			}else {
				maxV = absV(diff[1]);
			}
		}
		
		if(isDifferent(diff, 1)) {
			sum = absV(diff[0]);
			maxV = absV(diff[1]);
		}
		
		for(int i = 2; i < diff.length; i++) {
			
			if(i < diff.length - 1) {
				if(isSame(diff, i)) {
					if(maxV < absV(diff[i])) {
						maxV = absV(diff[i]);
					}
					if(absV(diff[i - 1]) > absV(diff[i])) {
						isStart = false;
						if (absV(diff[i]) < minV ) {
							minV = absV(diff[i]);
							troughS += absV(diff[i - 1]);
						}
						int temp1 = absV(diff[i - 1]);
						int temp2 = absV(diff[i]);
						int temp3 = absV(diff[i + 1]);
						if (temp1 > temp2 && temp2 < temp3) {
							hasMin = true;
							minIndex = i;
						}
					}
				}	
			}
			
			if(isDifferent(diff, i)) {
				if (hasMin == true) {
					sum = sum + (absV(diff[startIndex]) - absV(diff[minIndex])) + absV(diff[i - 1]); 
					maxV = absV(diff[i]);
					minIndex = 0;
					hasMin = false;
				}else {
					sum += maxV;
					maxV = absV(diff[i]);
					startIndex = i;
					
				}	
			}
			
		
			
			
			if(i == diff.length - 1) {
				if(isSame(diff, i) && absV(diff[i]) > maxV) {
					maxV = absV(diff[i]);
				}
				if (isDifferent(diff, i)) {
					maxV = absV(diff[i]);
				}
				sum += maxV;
			}
		}
		System.out.println(sum);

	}

}
