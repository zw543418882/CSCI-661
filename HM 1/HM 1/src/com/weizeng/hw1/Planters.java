import java.util.Scanner;
/*
 * @author: Wei Zeng
 * @project:HW1-Problem3
 * @date:09/13/2018
 * */

public class Planters {
	
	public static void buildMinHeap(int[] a){
		/*
		 * use an array to build a Min-heap 
		 * */
		 
		for(int i = (a.length - 2) / 2; i >= 0; i--) {
			//compare values from the last node which has child node
			minHeap(a, a.length, i);
			}
		}
	
	public static void minHeap(int[] a, int heapSize, int index){
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
		int temp = a[index];
		a[index] = a[smallest];
		a[smallest] = temp;
		minHeap(a,heapSize,smallest);
		    }
		}
	
	public static void heapSort(int[] a){
		/*
		 * use an array to build a Min-heap and then sort the Min-heap by recursion
		 * */
		for(int i = a.length-1;i>0;i--){
		int temp = a[0];
		a[0] = a[i];
		a[i] = temp;
		minHeap(a, i, 0);
		    }
		}

	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		String inputString = reader.nextLine();
		String[] inputStringA = inputString.split(" ");
		int p = Integer.valueOf(inputStringA[0]);
		int r = Integer.valueOf(inputStringA[1]);
		
		String houseString = reader.nextLine();// all plants
		String[] houseStringA = houseString.split(" ");
		int[] houseStringArray = new int[houseStringA.length];
		for (int i = 0; i < houseStringA.length; i++) {
			houseStringArray[i] = Integer.valueOf(houseStringA[i]);
		}
		
		String emptyString = reader.nextLine();
		String[] emptyStringA = emptyString.split(" ");
		int[] emptyStringArray = new int[emptyStringA.length];
		for (int i = 0; i < emptyStringA.length; i++) {
			emptyStringArray[i] = Integer.valueOf(emptyStringA[i]);
		}
		
		reader.close();
		
		int[] replantArray = new int[p + r];//all planters 
		for(int i = 0; i < p; i++) {
			replantArray[i] = houseStringArray[i];
		}
		for(int i = 0; i < r; i++) {
			replantArray[p + i] = emptyStringArray[i];
		}
		
		buildMinHeap(houseStringArray);
		heapSort(houseStringArray);
		
		buildMinHeap(replantArray);
		heapSort(replantArray);
		
		Boolean canReplant = true;	
		for(int i = 0; i < p; i++) {
			if(houseStringArray[i] >= replantArray[i]) {
				canReplant= false;// if a plant can not replant to a larger planter, change this value 
			}
		}
		
		if(canReplant == false) {
			System.out.println("NO");
		}else {
			System.out.println("YES");
		}
	
		
		

	}
	

}
