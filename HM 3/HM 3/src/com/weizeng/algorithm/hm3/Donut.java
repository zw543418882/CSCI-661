//package com.weizeng.algorithm.hm3;

import java.util.Scanner;

public class Donut {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = Integer.valueOf(reader.nextLine());
		int[] coorArrX = new int[n];
		int[] coorArrY = new int[n];
		for (int i = 0; i < n; i++) {
			String[] tempStrArr = reader.nextLine().split(" ");
			coorArrX[i] = Integer.valueOf(tempStrArr[0]);
			coorArrY[i] = Integer.valueOf(tempStrArr[1]);
		}

		System.out.println(minDistance(coorArrX, coorArrY));

	}

	public static int minDistance(int[] coorArrX, int[] coorArrY) {
		int min = 0;
		int xMedian = findMedian(coorArrX, 0, coorArrX.length - 1, (coorArrX.length + 1) / 2);
		int yMedian = findMedian(coorArrY, 0, coorArrY.length - 1, (coorArrY.length + 1) / 2);
		for (int i = 0; i < coorArrX.length; i++) {
			min += Math.abs(coorArrX[i] - xMedian);
		}
		for (int i = 0; i < coorArrX.length; i++) {
			min += Math.abs(coorArrY[i] - yMedian);
		}
		return min;
	}

	public static int findMedian(int[] nums, int start, int end, int size) {
		int mid = (start + end) / 2;
		int median = nums[mid];
		int i = start - 1;
		int j = end + 1;
		for (int k = start; k < j; k++) {
			if (nums[k] < median) {
				i++;
				int temp = nums[i];
				nums[i] = nums[k];
				nums[k] = temp;
			} else if (nums[k] > median) {
				j--;
				int temp = nums[j];
				nums[j] = nums[k];
				nums[k] = temp;
				k--;
			}
		}
		if (i - start + 1 >= size) {
			return findMedian(nums, start, i, size);
		} else if (j - start >= size) {
			return nums[j - 1];
		} else {
			return findMedian(nums, j, end, size - (j - start));
		}
	}
}
