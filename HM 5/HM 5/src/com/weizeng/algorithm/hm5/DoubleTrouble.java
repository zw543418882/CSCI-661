package com.weizeng.algorithm.hm5;

import java.util.Scanner;



public class DoubleTrouble {
	
	static class Node {
		int ax;
		int ay;
		int bx;
		int by;
		Node up;
		Node down;
		Node left;
		Node right;
	}
	
	static Node handleUPNode(Node upNode, String[][] maze) {
		if (maze[upNode.ax][upNode.ay].equals("x") && maze[upNode.bx][upNode.by].equals("x")) {
			upNode = null;
		}else if (maze[upNode.ax][upNode.ay].equals("x")) {
			upNode.ax = upNode.ax + 1;
		}else if (maze[upNode.bx][upNode.by].equals("x")) {
			upNode.bx = upNode.bx + 1;
		}
		return upNode;
	}
	
	static Node handleDownNode(Node downNode, String[][] maze) {
		if (maze[downNode.ax][downNode.ay].equals("x") && maze[downNode.bx][downNode.by].equals("x")) {
			downNode = null;
		}else if (maze[downNode.ax][downNode.ay].equals("x")) {
			downNode.ax = downNode.ax - 1;
		}else if (maze[downNode.bx][downNode.by].equals("x")) {
			downNode.bx = downNode.bx - 1;
		}
		return downNode;
	}
	
	static Node handleLeftNode(Node leftNode, String[][] maze) {
		if (maze[leftNode.ax][leftNode.ay].equals("x") && maze[leftNode.bx][leftNode.by].equals("x")) {
			leftNode = null;
		}else if (maze[leftNode.ax][leftNode.ay].equals("x")) {
			leftNode.ay = leftNode.ay + 1;
		}else if (maze[leftNode.bx][leftNode.by].equals("x")) {
			leftNode.by = leftNode.by + 1;
		}
		return leftNode;
	}
	
	static Node handleRightNode(Node rightNode, String[][] maze) {
		if (maze[rightNode.ax][rightNode.ay].equals("x") && maze[rightNode.bx][rightNode.by].equals("x")) {
			rightNode = null;
		}else if (maze[rightNode.ax][rightNode.ay].equals("x")) {
			rightNode.ay = rightNode.ay - 1;
		}else if (maze[rightNode.bx][rightNode.by].equals("x")) {
			rightNode.by = rightNode.by - 1;
		}
		return rightNode;
	}
	
	static Node GenerateNode(Node node,String[][] maze) {
		Node upNode = new Node();
		upNode.ax = node.ax - 1;
		upNode.ax = node.ay;
		upNode.bx = node.bx - 1;
		upNode.by = node.by;
		upNode = handleUPNode(upNode, maze);
		upNode = GenerateNode(upNode, maze);
		
		Node downNode = new Node();
		downNode.ax = node.ax + 1;
		downNode.ay = node.ay;
		downNode.bx = node.bx + 1;
		downNode.by = node.by;
		downNode = handleDownNode(downNode, maze);
		downNode = GenerateNode(downNode, maze);
		
		Node leftNode = new Node();
		leftNode.ax = node.ax;
		leftNode.ay = node.ay - 1;
		leftNode.bx = node.bx;
		leftNode.by = node.by - 1;
		leftNode = handleLeftNode(leftNode, maze);
		leftNode = GenerateNode(leftNode, maze);
		
		Node rightNode = new Node();
		rightNode.ax = node.ax;
		rightNode.ay = node.ay + 1;
		rightNode.bx = node.bx;
		rightNode.by = node.by + 1;
		rightNode = handleRightNode(rightNode, maze);
		rightNode = GenerateNode(rightNode, maze);
		
		node.up = upNode;
		node.down = downNode;
	    node.left = leftNode;
	    node.right = rightNode;
	    return node;
	}
	
	

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String abString = reader.nextLine();
		int a = Integer.valueOf(abString.split(" ")[0]);
		int b = Integer.valueOf(abString.split(" ")[1]);
		String[][] maze = new String[a + 1][b + 1];
		Node start = new Node();
		for(int i = 1; i <= a; i++) {
			String line = reader.nextLine();
			for(int j = 0; j < line.length(); j++) {
				maze[i][j + 1] = line.substring(j, j + 1);
				if (line.substring(j, j + 1).equals("1")) {
					start.ax = i;
					start.ay = j + 1;
				}
				if (line.substring(j, j + 1).equals("2")) {
					start.bx = i;
					start.by = j + 1;
				}
			}
		}
		reader.close();
		
		GenerateNode(start, maze);
		
		
		
		
		
		

	}

}
