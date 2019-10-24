//package com.weizeng.algorithm.hm5;

import java.util.Scanner;


public class SpanTree {
	static class EdgeNode {
		int endPoint1;
		int endPoint2;
		int weight;
		int inF;
	}
	
	static class Set {
		int[] set;
	}
	
	static void Init(int[] boss, int[] size, Set[] sets) {
		for(int i = 1; i < boss.length; i++) {
			boss[i] = i;
			size[i] = 1;
			Set set = new Set();
			int[] temp = new int[boss.length];
			temp[1] = i;
			set.set = temp;
			sets[i] = set;
		}
	}
	
	static int findIndex(int[] set) {
		int temp = 0;
		for(int i = 1; i < set.length; i++) {
			if (set[i] == 0) {
				temp = i;
				break;
			}
		}
		return temp;
	}
	
	static void Union(int u, int v, int[] boss, int[] size, Set[] sets) {
		if (size[boss[u]] > size[boss[v]]) {
			int index = findIndex(sets[boss[u]].set);
			for(int i = 1; i <= size[boss[v]]; i++) {
				sets[boss[u]].set[index] = sets[boss[v]].set[i];
				index++;
			}
			size[boss[u]] += size[boss[v]];
			for(int i = 1; i <= size[boss[v]]; i++) {
				boss[sets[boss[v]].set[i]] = boss[u];
			}
		}else {
			int index = findIndex(sets[boss[v]].set);
			for(int i = 1; i <= size[boss[u]]; i++) {
				sets[boss[v]].set[index] = sets[boss[u]].set[i];
				index++;
			}
			size[boss[v]] += size[boss[u]];
			for(int i = 1; i <= size[boss[u]]; i++) {
				boss[sets[boss[u]].set[i]] = boss[v];
			}
		}
	}
	


	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String vEString = reader.nextLine();
		int numOfV = Integer.valueOf(vEString.split(" ")[0]);
		int numOfE = Integer.valueOf(vEString.split(" ")[1]);
		EdgeNode[] edges = new EdgeNode[numOfE + 1];
		for(int i = 1; i <= numOfE; i++) {
			EdgeNode edge = new EdgeNode();
			String line = reader.nextLine();
			String[] lineArr = line.split(" ");
			edge.endPoint1 = Integer.valueOf(lineArr[0]);
			edge.endPoint2 = Integer.valueOf(lineArr[1]);
			edge.weight = Integer.valueOf(lineArr[2]);
			edge.inF = Integer.valueOf(lineArr[3]);
			edges[i] = edge;
		}
		reader.close();
		
		int[] boss = new int[numOfV + 1];
		int[] size = new int[numOfV + 1];
		Set[] sets = new Set[numOfV + 1];
		Init(boss, size, sets);
		
		EdgeNode[] T = new EdgeNode[numOfE + 1];
		int indexOfT = 1;
		for(int i = 1; i <= numOfE; i++) {
			EdgeNode edge = edges[i];
			if (boss[edge.endPoint1] != boss[edge.endPoint2] && edge.inF == 1) {
				T[indexOfT] = edge;
				indexOfT++;
				Union(edge.endPoint1, edge.endPoint2, boss, size, sets);
			}
		}
		
		
		int numOfInF = 0;
		for(int i = 1; i <= numOfE; i++) {
			EdgeNode edge = edges[i];
			if (edge.inF == 1) {
				numOfInF++;
			}
		}
		
		if (numOfInF != indexOfT - 1) {
			System.out.println("-1");
			System.exit(0);
		}
		
		int indexOfRest = 1;
		EdgeNode[] restEdges = new EdgeNode[numOfE - indexOfT + 2];
		for(int i = 1; i <= numOfE; i++) {
			if (edges[i].inF == 0) {
				restEdges[indexOfRest] = edges[i];
				indexOfRest++;
			}
		}
		
		for(int i = 1; i < restEdges.length - 1; i++) {
			for(int j = 1; j <= restEdges.length - 1 - i; j++) {
				if (restEdges[j].weight > restEdges[j + 1].weight) {
					EdgeNode temp = restEdges[j];
					restEdges[j] = restEdges[j + 1];
					restEdges[j + 1] = temp;
				}
			}
		}
		
		for(int i = 1; i < restEdges.length; i++) {
			EdgeNode node = restEdges[i];
			if (boss[node.endPoint1] != boss[node.endPoint2]) {
				T[indexOfT] = node;
				indexOfT++;
				Union(node.endPoint1, node.endPoint2, boss, size, sets);
			}
		}
		
		
		
		int maxSize = 0;
		for(int i = 1; i <= numOfV; i++) {
			if (size[i] > maxSize) {
				maxSize = size[i];
			}
		}
		
		if (maxSize != numOfV) {
			System.out.println("-1");
			System.exit(0);
		}
		
		int cost = 0;
		for(int i = 1; i < indexOfT; i++) {
			cost += T[i].weight;
		}
		System.out.println(cost);

	}
}
