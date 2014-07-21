package com.biz4people;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mlimaki
 *
 */
public class BinaryTree<T extends Comparable<T>> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int size;

	private BinaryTreeNode<T> root;
	
	public BinaryTree(T data) {
		root = new BinaryTreeNode<T>();
		root.setData(data);
		size++;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public BinaryTreeNode<T> root() {
		return root;
	}

	public BinaryTreeNode<T> parent(BinaryTreeNode<T> p) {
		return p.getParent();
	}
	
	public boolean isExternal(BinaryTreeNode<T> p) {
		return p.getLeftChild() == null && p.getRightChild() == null;
	}
	
	public boolean isInternal(BinaryTreeNode<T> p) {
		return p.getLeftChild() != null | p.getRightChild() != null;
	}
	
	public void insert(T data) {
		insertData(root, data);
		size++;
	}
	
	private void insertData(BinaryTreeNode<T> node, T data) {
		if (data.compareTo(node.getData()) < 0) {
			if (node.getLeftChild() != null) {
				insertData(node.getLeftChild(), data);
			} else {
				node.setLeftChild(new BinaryTreeNode<T>(data));
			}
		} else if (data.compareTo(node.getData()) > 0) {
			if (node.getRightChild() != null) {
				insertData(node.getRightChild(), data);
			} else {
				node.setRightChild(new BinaryTreeNode<T>(data));
			}
		}
	}
	
	public List<T> getElements() {
		return inOrderTravesal(root, new ArrayList<T>());
	}
	
	public List<T> visitPostOrder() {
		return postOrderTravesal(root, new ArrayList<T>());
	}
	
	public List<T> visitPreOrder() {
		return preOrderTravesal(root, new ArrayList<T>());
	}
	
	public List<T> visitInOrder() {
		return inOrderTravesal(root, new ArrayList<T>());
	}
	
	private List<T> preOrderTravesal(BinaryTreeNode<T> node, List<T> list) {
		if(node != null && list != null) {
			list.add(node.getData());
			if(node.getLeftChild() != null) {
				inOrderTravesal(node.getLeftChild(), list);
			}
			if(node.getRightChild() != null) {
				inOrderTravesal(node.getRightChild(), list);
			}
		}
		return list;
	}
	
	private List<T> postOrderTravesal(BinaryTreeNode<T> node, List<T> list) {
		if(node != null && list != null) {
			if(node.getLeftChild() != null) {
				inOrderTravesal(node.getLeftChild(), list);
			}
			if(node.getRightChild() != null) {
				inOrderTravesal(node.getRightChild(), list);
			}
			list.add(node.getData());
		}
		return list;
	}
	
	private List<T> inOrderTravesal(BinaryTreeNode<T> node, List<T> list) {
		if(node != null && list != null) {
			if(node.getLeftChild() != null) {
				inOrderTravesal(node.getLeftChild(), list);
			}
			
			list.add(node.getData());
			
			if(node.getRightChild() != null) {
				inOrderTravesal(node.getRightChild(), list);
			}
		}
		return list;
	}
	
	public static int nodeHeight(BinaryTreeNode<? extends Comparable<?>> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(nodeHeight(node.getLeftChild()), nodeHeight(node.getRightChild())) + 1;
    }
	
	/**
	 * 
	 * @author mlimaki
	 *
	 * @param <E>
	 */
	public static class BinaryTreeNode<E> implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private E data;
		private BinaryTreeNode<E> parent;
		private BinaryTreeNode<E> leftChild;
		private BinaryTreeNode<E> rightChild;
		
		public BinaryTreeNode() {
		}
		
		public BinaryTreeNode(E data) {
			this.data = data;
		}

		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data = data;
		}

		public BinaryTreeNode<E> getParent() {
			return parent;
		}

		public void setParent(BinaryTreeNode<E> parent) {
			this.parent = parent;
		}

		public BinaryTreeNode<E> getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(BinaryTreeNode<E> leftChild) {
			this.leftChild = leftChild;
		}

		public BinaryTreeNode<E> getRightChild() {
			return rightChild;
		}

		public void setRightChild(BinaryTreeNode<E> rightChild) {
			this.rightChild = rightChild;
		}
	}
}