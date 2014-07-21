package com.biz4people;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.biz4people.BinaryTree.BinaryTreeNode;

public class TestBinaryTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree<Integer> binary = new BinaryTree<Integer>(9);

		binary.insert(2);
		binary.insert(5);
		binary.insert(7);
		binary.insert(4);
		binary.insert(8);
		binary.insert(15);
		binary.insert(6);
		binary.insert(11);
		binary.insert(20);
		binary.insert(10);
		
		System.out.println("In Order: ");
		for(Integer in : binary.visitInOrder()) {
			System.out.print(in + ", ");
		}
		System.out.println("");
		
		System.out.println("Post Order: ");
		for(Integer in : binary.visitPostOrder()) {
			System.out.print(in + ", ");
		}
		System.out.println("");
		
		System.out.println("Pre Order: ");
		for(Integer in : binary.visitPreOrder()) {
			System.out.print(in + ", ");
		}
		System.out.println("");
		
		System.out.println("Print the TREE: ");
		printTheTree(binary.root());
	}
	
	public static  void printTheTree(BinaryTreeNode< ? extends Comparable<?>> root) {
        printInternalTree(Collections.singletonList(root), 1, BinaryTree.nodeHeight(root));
    }
	
	private static <T extends Comparable<?>> void printInternalTree(List<BinaryTreeNode<T>> nodes, int step, int treeHeight) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) {
            return;
        }
        int floor = treeHeight - step;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<BinaryTreeNode<T>> newNodes = new ArrayList<BinaryTreeNode<T>>();
        for (BinaryTreeNode<T> node : nodes) {
            if (node != null) {
                System.out.print("[" + node.getData() + "]");
                newNodes.add(node.getLeftChild());
                newNodes.add(node.getRightChild());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeftChild() != null) {
                    System.out.print("/");
                } else {
                    printWhitespaces(1);
                }
                printWhitespaces(i + i - 1);

                if (nodes.get(j).getRightChild() != null) {
                    System.out.print("\\");
                } else {
                    printWhitespaces(1);
                }
                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printInternalTree(newNodes, step + 1, treeHeight);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }
    
    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null) {
                return false;
            }
        }
        return true;
    }
}