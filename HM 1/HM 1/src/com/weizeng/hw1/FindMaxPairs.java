//package com.weizeng.hw1;
import java.util.Scanner;
/*
 * Wei Zeng
 * CSCI 665 HW1-4
 * 09/13/2018
 * */

public class FindMaxPairs {
	
	public static void buildMinHeap(long[] a){
		/*
		 * use an array to build a Min-heap 
		 * */
		 
		for(int i = (a.length - 2) / 2; i >= 0; i--) {
			//compare values from the last node which has child node
			minHeap(a, a.length, i);
			}
		}
	
	public static void minHeap(long[] a, int heapSize, int index){
		/*
		 *use recursion to adjust binary tree to a Min-heap
		 * 
		 * index: current node which needs compare with child nodes
		 * heapSize: the number of nodes of current binary tree
		 * */
		int left = 2 * index + 1;
		int right = 2 * index + 2;
		int smallest = index;
		if(left<heapSize && a[index] > a[left]) {
			smallest = left;
		    }
		if(right < heapSize && a[smallest] > a[right]){
			smallest = right;
			}
		if(smallest != index) {
		long temp = a[index];
		a[index] = a[smallest];
		a[smallest] = temp;
		minHeap(a,heapSize,smallest);
		    }
		}
	
	public static void heapSort(long[] a){
		/*
		 * use an array to build a Min-heap and then sort the Min-heap by recursion
		 * */
		for(int i = a.length-1;i>0;i--){
		long temp = a[0];
		a[0] = a[i];
		a[i] = temp;
		minHeap(a, i, 0);
		    }
		}


	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String nS = reader.nextLine();
		int n = Integer.valueOf(nS);
		String numberS = reader.nextLine();
		String[] numberStringA = numberS.split(" ");
		long [] numberA = new long[numberStringA.length];
		for(int i = 0; i < numberStringA.length; i++) {
			numberA[i] = Integer.valueOf(numberStringA[i]);
		}
		int index = 0;
		long[] sumA = new long[n * (n - 1) / 2];//store all sums
		for(int i = 0; i < numberA.length - 1; i++) {
			for (int j = i + 1; j < numberA.length; j ++) {
				sumA[index] = numberA[i] + numberA[j];
				index++;
			}
		}
		
		buildMinHeap(sumA);
		heapSort(sumA);
//find the number which has the most maximum number of occurrences 
		int maxAcc = 0;
		long maxV = sumA[0];
		long currentV = sumA[0];
		int currentAcc = 1;
		for(int i = 1; i < sumA.length; i++) {
			if(sumA[i] == currentV) {
				currentAcc++;
			}else {
				if(currentAcc >= maxAcc) {
					maxAcc = currentAcc;
					maxV= currentV;
				}
				currentV = sumA[i];
				currentAcc = 1;
			}
			if(i == sumA.length - 1 && currentAcc >= maxAcc) {
				maxAcc = currentAcc;
				maxV = currentV;
			}
		}
		reader.close();
		System.out.print(maxV);

	}

}
