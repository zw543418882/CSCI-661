//package com.weizeng.algorithm.hm5;

import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Prerequisites {
	static class Node {
		int courseNo;
		int[] preCourseArr;
		int depth = 1;
	}
	
	static Node[] init() {
		Scanner reder = new Scanner(System.in);
		String numStr = reder.nextLine();
		int numOfNodes = Integer.valueOf(numStr);
		Node[] nodes = new Node[numOfNodes + 1];
		for(int i = 1; i <= numOfNodes; i++) {
			Node node = new Node();
			node.courseNo = i;
			String line = reder.nextLine();
			String[] lineArr = line.split(" ");
			int[] neighbors = new int[lineArr.length - 1];
			for(int j = 0; j < lineArr.length; j++) {
				if (!lineArr[j].equals("0")) {
					neighbors[j] = Integer.valueOf(lineArr[j]);
				}
			}
			node.preCourseArr = neighbors;
			nodes[i] = node;
		}
		reder.close();
		return nodes;
	}
	
	static int DFSRun(Node[] nodes) {
		boolean[] seen = new boolean[nodes.length];
		int[] depth = new int[nodes.length];
		for(int i = 1; i < seen.length; i++) {
			seen[i] = false;
		}
		for(int i = 1; i < nodes.length; i++) {
			if (!seen[i]) {
				depth[i] = DFS(nodes, seen, i);
			}
		}
		int max = 0;
		for(int i = 1; i < nodes.length; i++) {
			if (depth[i] > max) {
				max = depth[i];
			}
		}
		return max;
	}
	
	static int DFS(Node[] nodes, boolean[] seen, int start) {
		seen[start] = true;
		Node node = nodes[start];
		for(int i = 0; i < node.preCourseArr.length; i++) {
			if (!seen[node.preCourseArr[i]]) {
				node.depth = Math.max(DFS(nodes, seen, node.preCourseArr[i]) + 1, node.depth);
			}else {
				node.depth = Math.max(nodes[node.preCourseArr[i]].depth + 1, node.depth);
			}
		}
		return node.depth;
	}


	public static void main(String[] args) {
		Node[] nodes = init();
		int max = DFSRun(nodes);
		System.out.println(max);
	}
}
