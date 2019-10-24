//package com.weizeng.algorithm.hm2;
import java.util.Scanner;
/*
 * Wei Zeng
 * HW2-Problem 3
 * 09/27/2018
 * */


public class WeightedByDiffInversions {

	 public static long merSort(long[] arr,int left,int right) {
		 /*
		  * Divide array recursively
		  * 
		  * */
	    	long account = 0;
	        if(left<right){
	            int mid = (left+right)/2;
	            account += merSort(arr,left,mid);//T(n / 2)
	            account += merSort(arr,mid+1,right);//T(n / 2 ) 
	            account += merge(arr,left,mid,right);//c * n
	        }
	        return account;
	    }
	 
	    private static long merge(long[] arr, int left, int mid, int right) {
	    	/*
	    	 * calculate differences when merging arrays
	    	 * */
	    	long count = 0;;
	        long[] temp = new long[right - left + 1];
	        int i = left;
	        int j = mid+1;
	        int k = 0;
	        
	        long[] tempArr = new long[mid -left + 1];// all sums of each element plus all elements behind
			long sum = 0;
			if (left == mid) {
				tempArr[0] = arr[left];
			}
	        if(mid > left) {
	        	 for(int t = mid; t >= left; t--) {
	 	        	sum += arr[t];
	 	        	tempArr[t - left] = sum;
	 	        }
	        }
	        
	        while(i<=mid&&j<=right){
	            if (arr[i] <= arr[j]) {
	                temp[k++] = arr[i++];
	            } else {
	            	/*
	            	 * if an element in a sorted array is greater than another element in another sorted array, 
	            	 * all elements behind the first element in the same array are greater than the second element
	            	 * */
	            	long tempV = tempArr[i - left] - (mid - i + 1) * arr[j];
	            	count += tempV;
	                temp[k++] = arr[j++];
	            }
	        }
	        while(i<=mid){
	            temp[k++] = arr[i++];
	        }
	        while(j<=right){
	            temp[k++] = arr[j++];
	        }
	        for (int k2 = 0; k2 < temp.length; k2++) {
	            arr[k2 + left] = temp[k2];
	        }
	        return count;
	    }
	    
	    public static void main(String args[]){
	    	Scanner reader = new Scanner(System.in);
			int n = Integer.valueOf(reader.nextLine());
			String tempS = reader.nextLine();
			String[] tempA = tempS.split(" ");
			long [] numberA = new long[tempA.length];
			for (int i = 0; i < tempA.length; i++) {
				numberA[i] = Long.parseLong(tempA[i]);
			}
	        long count = merSort(numberA,0,numberA.length-1);
	        System.out.println(count);
	        reader.close();
	    }
	}

