package com.biz4people;

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
	}
}
