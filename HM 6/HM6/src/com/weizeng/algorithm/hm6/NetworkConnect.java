//package com.weizeng.algorithm.hm6;

import java.util.Scanner;

public class NetworkConnect {
	static int[] BFSFindAugmentingPath(int server, int numOfV, int[][] G, int[][] weights) {
		int[] shortestPaht = new int[numOfV + 1];
		int[] queue = new int[numOfV + 1];
		int headPointer = 0;
		int tailPointer = 1;
		queue[0] = server;
		shortestPaht[server] = -1;
		int currentNode = 0;
		while (headPointer != tailPointer) {
			currentNode = queue[headPointer];
			int tempPointer = 1;
			while (tempPointer <= G[currentNode][0]) {
				int childNode = Math.abs(G[currentNode][tempPointer]);
				int weight = weights[currentNode][childNode];
				if (shortestPaht[childNode] == 0 && weight > 0) {
					shortestPaht[childNode] = currentNode;
					queue[tailPointer] = childNode;
					tailPointer++;
				}
				tempPointer++;
			}
			headPointer++;
		}
		return shortestPaht;
	}

	static int findValueOfAugmentingFlow(int server, int user, int[] shortestPath, int[][] weights) {
		int minWeight = Integer.MAX_VALUE;
		int parentNode = user;
		while (shortestPath[parentNode] > 0) {
			if (weights[shortestPath[parentNode]][parentNode] < minWeight) {
				minWeight = weights[shortestPath[parentNode]][parentNode];
			}
			parentNode = shortestPath[parentNode];
		}
		return minWeight;
	}

	static void update(int server, int user, int[] shortestPath, int[][] weights) {
		int augmentingFlow = findValueOfAugmentingFlow(server, user, shortestPath, weights);
		int parentNode = user;
		while (parentNode != server) {
			weights[shortestPath[parentNode]][parentNode] -= augmentingFlow;
			weights[parentNode][shortestPath[parentNode]] += augmentingFlow;
			parentNode = shortestPath[parentNode];
		}
	}

	static boolean fullCapacity(int vertex, int numOfV, int[][] G, int[][] weights) {
		boolean result = true;
		int num = G[vertex][0];
		for (int i = 1; i <= num; i++) {
			if (G[vertex][i] > 0) {
				if (weights[vertex][G[vertex][i]] > 0) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	static void EdmondsKarp(int server, int user, int numOfV, int[][] G, int[][] weights) {
		int[] shortestPath = BFSFindAugmentingPath(server, numOfV, G, weights);
		while (shortestPath[user] != 0) {
			update(server, user, shortestPath, weights);
			shortestPath = BFSFindAugmentingPath(server, numOfV, G, weights);
		}
		int[] S = new int[numOfV + 1];
		S[0] = server;
		int indexOfS = 1;
		int[] T = new int[numOfV + 1];
		T[0] = user;
		int indexOfT = 1;
		for (int i = 1; i < numOfV; i++) {
			if (i != server) {
				if (shortestPath[i] > 0) {
					S[indexOfS] = i;
					indexOfS++;
				} else {
					T[indexOfT] = i;
					indexOfT++;
				}
			}
		}
		for (int i = 0; i < indexOfS - 1; i++) {
			for (int j = 0; j < indexOfS - i - 1; j++) {
				if (S[j] > S[j + 1]) {
					int temp = S[j];
					S[j] = S[j + 1];
					S[j + 1] = temp;
				}
			}
		}
		for (int i = 0; i < indexOfT - 1; i++) {
			for (int j = 0; j < indexOfT - i - 1; j++) {
				if (T[j] > T[j + 1]) {
					int temp = T[j];
					T[j] = T[j + 1];
					T[j + 1] = temp;
				}
			}
		}

		int resultU = 0;
		int resultV = 0;
		for(int i = 1; i < G[server][0]; i++) {
			weights[server][G[server][i]] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < indexOfS; i++) {
			for (int j = 0; j < indexOfT; j++) {
				if (weights[S[i]][T[j]] + weights[T[j]][S[i]] == 0 && !fullCapacity(S[i], numOfV, G, weights)
						&& !fullCapacity(T[j], numOfV, G, weights)) {
					resultU = S[i];
					resultV = T[j];
					System.out.println(resultU + " " + resultV);
					System.exit(0);
				}
			}
		}
		if (resultU == 0 && resultV == 0) {
			System.out.println("NO");
		}

	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String numOfVEStr = reader.nextLine();
		int numOfV = Integer.valueOf(numOfVEStr.split(" ")[0]);
		int numOfE = Integer.valueOf(numOfVEStr.split(" ")[1]);
		String serverAndUser = reader.nextLine();
		int server = Integer.valueOf(serverAndUser.split(" ")[0]);
		int user = Integer.valueOf(serverAndUser.split(" ")[1]);
		int[][] G = new int[numOfV + 1][numOfV + 1];
		int[][] weights = new int[numOfV + 1][numOfV + 1];
		for (int i = 0; i < numOfE; i++) {
			String line = reader.nextLine();
			String[] lineStr = line.split(" ");
			int u = Integer.valueOf(lineStr[0]);
			int v = Integer.valueOf(lineStr[1]);
			int weight = Integer.valueOf(lineStr[2]);
			G[u][0]++;
			G[u][G[u][0]] = v;
			weights[u][v] = weight;
			G[v][0]++;
			G[v][G[v][0]] = -u;
		}

		EdmondsKarp(server, user, numOfV, G, weights);
		reader.close();

	}

}
