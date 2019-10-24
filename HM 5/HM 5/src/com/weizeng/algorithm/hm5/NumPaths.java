//package com.weizeng.algorithm.hm5;

import java.util.Scanner;

public class NumPaths {
	static class EdgeNode {
		int vertex;
		EdgeNode next;
	}

	static class VertexNode {
		int name;
		EdgeNode firstEdge = new EdgeNode();
	}

	static class Graph {
		VertexNode[] nodeList = new VertexNode[1001];
		int vertexNum;
		int edgeNum;
	}

	static int[] initGraph(Graph G) {
		Scanner reader = new Scanner(System.in);
		String VEStr = reader.nextLine();
		int VNum = Integer.valueOf(VEStr.split(" ")[0]);
		int ENum = Integer.valueOf(VEStr.split(" ")[1]);
		G.vertexNum = VNum;
		G.edgeNum = ENum;
		String goalStr = reader.nextLine();
		int start = Integer.valueOf(goalStr.split(" ")[0]);
		int des = Integer.valueOf(goalStr.split(" ")[1]);
		for (int i = 1; i <= VNum; i++) {
			G.nodeList[i] = new VertexNode();
			G.nodeList[i].name = i;
			G.nodeList[i].firstEdge = null;
		}
		for (int i = 1; i <= G.edgeNum; i++) {
			String indexStr = reader.nextLine();
			int index1 = Integer.valueOf(indexStr.split(" ")[0]);
			int index2 = Integer.valueOf(indexStr.split(" ")[1]);

			EdgeNode edgeNode = new EdgeNode();
			edgeNode.vertex = index1;
			edgeNode.next = G.nodeList[index2].firstEdge;
			G.nodeList[index2].firstEdge = edgeNode;

			EdgeNode edgeNode2 = new EdgeNode();
			edgeNode2.vertex = index2;
			edgeNode2.next = G.nodeList[index1].firstEdge;
			G.nodeList[index1].firstEdge = edgeNode2;
		}
		int[] result = new int[2];
		result[0] = start;
		result[1] = des;
		return result;
	}

	static int[] BFS(Graph G, int start) {
		boolean[] seen = new boolean[G.vertexNum + 1];
		int[] dis = new int[G.vertexNum + 1];
		int[] path = new int[G.vertexNum + 1];
		for (int i = 1; i <= G.vertexNum; i++) {
			seen[i] = false;
			path[i] = 0;
			dis[i] = 999999;
		}
		dis[start] = 0;
		path[start] = 1;

		int beg = 1;
		int end = 2;
		int[] Q = new int[1001];
		Q[1] = start;
		while (beg < end) {
			int head = Q[beg];
			seen[head] = true;
			EdgeNode u = G.nodeList[head].firstEdge;
			while (u != null) {
				if (!seen[u.vertex]) {
					if (dis[head] + 1 < dis[u.vertex]) {
						dis[u.vertex] = dis[head] + 1;
						path[u.vertex] = path[head];
					} else if (dis[head] + 1 == dis[u.vertex]) {
						path[u.vertex] += path[head];
					}
					boolean have = false;
					for (int i = 1; i < end; i++) {
						if (Q[i] == u.vertex) {
							have = true;
						}
					}
					if (!have) {
						Q[end] = u.vertex;
						end++;
					}

				}
				u = u.next;
			}
			beg++;
		}
		return path;
	}

	public static void main(String[] args) {
		Graph G = new Graph();
		int[] result = initGraph(G);
		int[] paths = BFS(G, result[0]);
		System.out.println(paths[result[1]]);
	}

}
