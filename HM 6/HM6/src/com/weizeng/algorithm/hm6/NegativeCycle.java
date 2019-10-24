//package com.weizeng.algorithm.hm6;

import java.util.Scanner;

public class NegativeCycle {
	static class Node {
		int vertex;
		int weight;
		Node nextNode;
	}

	static class Edge {
		int startPoint;
		Node firstNode;
	}
	
	static void update(Edge edge, Edge[] edges, int[] dist, int[] H) {
		Node neighbor = edge.firstNode;
		while (neighbor != null) {
			if (dist[neighbor.vertex] > dist[edge.startPoint] + neighbor.weight) {
				dist[neighbor.vertex] = dist[edge.startPoint] + neighbor.weight;
				if (H[neighbor.vertex] == 0) {
					H[neighbor.vertex] = 1;
				}
			}
			neighbor = neighbor.nextNode;
		}
		H[edge.startPoint] = 0;
	}

	static int smallestCostInH(int[] H, int[] dist) {
		int smallest = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 1; i < H.length; i++) {
			if (H[i] != 0) {
				if (dist[i] < smallest) {
					smallest = dist[i];
					index = i;
				}
			}
		}
		return index;
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String numOfVE = reader.nextLine();
		int numOfV = Integer.valueOf(numOfVE.split(" ")[0]);
		int numOfE = Integer.valueOf(numOfVE.split(" ")[1]);
		Edge[] edges = new Edge[numOfV + 1];
		int start = 0;
		int end = 0;
		for (int i = 0; i < numOfE; i++) {
			String line = reader.nextLine();
			String[] lineStr = line.split(" ");
			if (Integer.valueOf(lineStr[2]) < 0) {
				start = Integer.valueOf(lineStr[1]);
				end = Integer.valueOf(lineStr[0]);
			}
			if (edges[Integer.valueOf(lineStr[0])] == null) {
				Edge edge = new Edge();
				edge.startPoint = Integer.valueOf(lineStr[0]);
				Node node = new Node();
				node.vertex = Integer.valueOf(lineStr[1]);
				node.weight = Integer.valueOf(lineStr[2]);
				edge.firstNode = node;
				edges[Integer.valueOf(lineStr[0])] = edge;
			} else {
				Node node = new Node();
				node.vertex = Integer.valueOf(lineStr[1]);
				node.weight = Integer.valueOf(lineStr[2]);
				Node temp = edges[Integer.valueOf(lineStr[0])].firstNode;
				node.nextNode = temp;
				edges[Integer.valueOf(lineStr[0])].firstNode = node;
			}
		}
		reader.close();

		int[] H = new int[numOfV + 1];
		int[] dist = new int[numOfV + 1];
		for (int i = 1; i <= numOfV; i++) {
			H[i] = 0;
			dist[i] = Integer.MAX_VALUE;
		}
		dist[start] = 0;
		update(edges[start], edges, dist, H);
		for (int i = 1; i <= numOfV - 1; i++) {
			int u = smallestCostInH(H, dist);
			if (edges[u] != null) {
				update(edges[u], edges, dist, H);
			}else {
				H[u] = 0;
			}
			

		}

		Node endNode = edges[end].firstNode;
		while (endNode.vertex != start) {
			endNode = endNode.nextNode;
		}
		if (dist[end] + endNode.weight < 0) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

}
