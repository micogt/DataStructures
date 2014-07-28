package com.biz4people.binarytree;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.biz4people.binarytree.BinaryTree.BinaryTreeNode;

/**
 * @author mlimaki
 *
 */
@RunWith(Parameterized.class)
public class BinaryTreeTest {

	private BinaryTree<Integer> binaryTree;
	private Integer[][] visited;
	
	public BinaryTreeTest(BinaryTree<Integer> binaryTree, Integer[][] visited) {
		this.binaryTree = binaryTree;
		this.visited = visited;
	}

	@Test
	public void testVisitPostOrder() {
		assertEquals(binaryTree.visitPostOrder(), Arrays.asList(visited[1]));
	}

	@Test
	public void testVisitPreOrder() {
		assertEquals(binaryTree.visitPreOrder(), Arrays.asList(visited[2]));
	}

	@Test
	public void testVisitInOrder() {
		assertEquals(binaryTree.visitInOrder(), Arrays.asList(visited[0]));
	}

	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
    		{
    			new BinaryTree<Integer>(Arrays.asList(new Integer[] {8, 9, 2, 0, 100, 55, 6, 773, 20, 9, 5, 11, 67, 1, 10, 62, 98})),
    			new Integer[][]{
    				new Integer[]{0, 1, 2, 5, 6, 8, 9, 10, 11, 20, 55, 62, 67, 98, 100, 773}, //in
    				new Integer[]{0, 1, 2, 5, 6, 9, 10, 11, 20, 55, 62, 67, 98, 100, 773, 8}, //post
    				new Integer[]{8, 0, 1, 2, 5, 6, 9, 10, 11, 20, 55, 62, 67, 98, 100, 773} //pre
    			}
    		},
    		{
    			new BinaryTree<Integer>(Arrays.asList(new Integer[] {9, 7, 9, 3, 10, 9, 0, 13, 10, 30, 7})),
    			new Integer[][]{
    				new Integer[]{0, 3, 7, 9, 10, 13, 30}, //in
    				new Integer[]{0, 3, 7, 10, 13, 30, 9}, //post
    				new Integer[]{9, 0, 3, 7, 10, 13, 30} //pre
    			}
    		},
    		{
    			new BinaryTree<Integer>(Arrays.asList(new Integer[] {1, 2, 3, 40, 5, 16, 7, 80, -10, 0, 11, -23})),
    			new Integer[][]{
    				new Integer[]{-23, -10, 0, 1, 2, 3, 5, 7, 11, 16, 40, 80}, //in
    				new Integer[]{-23, -10, 0, 2, 3, 5, 7, 11, 16, 40, 80, 1}, //post
    				new Integer[]{1, -23, -10, 0, 2, 3, 5, 7, 11, 16, 40, 80} //pre
    			}
    		}
    	});
    }
    
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree<Integer> binary = new BinaryTree<Integer>(Arrays.asList(new Integer[] {1, 2, 3, 40, 5, 16, 7, 80, -10, 0, 11, -23}));
		
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
