package com.weizeng.algorithm.hm6;

import java.util.Scanner;

public class Redirect {
	static class Node {
		int vertex;
		Node next;
	}
	
	static class Vertex {
		int source;
		Node firstNode;
	}
	
	static int[] DFSRun(Vertex[] vertexs) {
		boolean[] seen = new boolean[vertexs.length];
		for(int i = 1; i < seen.length; i++) {
			seen[i] = false;
		}
		int[] result = new int[vertexs.length];
		int index = 1;
		DFS(1, seen, vertexs, result, index);
		return result;
	}
	
	static void DFS(int v, boolean[] seen, Vertex[] vertexs, int[] result, int index) {
		result[index] = v;
		index++;
		seen[v] = true;
		Node node = vertexs[v].firstNode;
		while(node != null) {
			if (!seen[node.vertex]) {
				DFS(node.vertex, seen, vertexs, result, index);
			}
			node = node.next;
		}
	}
	
	static int[] inverseDFSRun(Vertex[] inverseGraph, int[] result) {
		int[] roots = new int[inverseGraph.length];
		for(int i = 1; i < roots.length; i++) {
			roots[i] = 0;
		}
		for(int i = result.length - 1; i >= 1; i--) {
			assign(result[i], result[i], inverseGraph, roots);
		}
		return roots;
	}
	
	static void assign(int u, int root, Vertex[] inverseGraph, int[] roots) {
		if (roots[u] == 0) {
			roots[u] = root;
			if (inverseGraph[u] != null) {
				Node node = inverseGraph[u].firstNode;
				while(node != null) {
					assign(node.vertex, root, inverseGraph, roots);
					node = node.next;
				}
			}
			
		}
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int n = Integer.valueOf(reader.nextLine());
		Vertex[] vertexs = new Vertex[n + 1];
		for(int i = 1; i <= n; i++) {
			String line = reader.nextLine();
			String[] lineArr = line.split(" ");
			Vertex vertex = new Vertex();
			vertex.source = i;
			for(int j = 0; j < lineArr.length - 1; j++) {
				Node node = new Node();
				node.vertex = Integer.valueOf(lineArr[j]);
				Node temp = vertex.firstNode;
				node.next = temp;
				vertex.firstNode = node;
			}
			vertexs[i] = vertex;
		}
		
		Vertex[] inverseGraph = new Vertex[n + 1];
		for(int i = 1; i <= n; i++) {
			Node node = vertexs[i].firstNode;
			while(node != null) {
				if (inverseGraph[node.vertex] == null) {
					Vertex vertex = new Vertex();
					vertex.source = node.vertex;
					Node temp = new Node();
					temp.vertex = i;
					vertex.firstNode = temp;
					inverseGraph[node.vertex] = vertex;
				}else {
					Node temp = inverseGraph[node.vertex].firstNode;
					Node tempNode = new Node();
					tempNode.vertex = i;
					tempNode.next = temp;
					inverseGraph[node.vertex].firstNode = tempNode;
				}
				node = node.next;
			}
		}
		
		for(int i = 1; i <= n; i++) {
			if (inverseGraph[i] == null) {
				Vertex vertex = new Vertex();
				vertex.source = i;
				vertex.firstNode = null;
				inverseGraph[i] = vertex;
			}
		}
		
		int[] result = DFSRun(vertexs);
		int[] roots = inverseDFSRun(inverseGraph, result);
		for(int i = 1; i <= n; i++) {
			System.out.println(i + ": " + roots[i]);
		}

	}

}
