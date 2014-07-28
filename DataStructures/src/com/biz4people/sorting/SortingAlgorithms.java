package com.biz4people.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * 
 * @author mlimaki
 *
 */
public class SortingAlgorithms {
	
	/**
	 * 
	 * @param unsorted
	 * @return
	 */
	public static <T extends Comparable<T>> List<T> bubbleSort(List<T> unsorted) {
		if(unsorted != null && !unsorted.isEmpty()) {
			for(int i = 0; i < unsorted.size(); i++) {
				for(int j = unsorted.size() - 1; j > i; j--) {
					if(unsorted.get(j).compareTo(unsorted.get(j -  1)) < 0) {
						T temp = unsorted.get(j);
						unsorted.set(j, unsorted.get(j - 1));
						unsorted.set(j - 1, temp);
					}
				}
			}
		}
		return unsorted;
	}
	
	
	/**
	 * 
	 * @param unsorted
	 * @return
	 */
	public static <T extends Comparable<T>> List<T> insertionSort(List<T> unsorted) {
		if(unsorted != null && !unsorted.isEmpty()) {
			for(int i = 1; i < unsorted.size(); i++) {
				T temp = unsorted.get(i);
				int j = i;
				while(j > 0 && (unsorted.get(j - 1).compareTo(temp) > 0)) {
					unsorted.set(j, unsorted.get(j - 1));
					j--;
				}
				unsorted.set(j, temp);
			}
		}
		return unsorted;
	}
	
	/**
	 * 
	 * @param unsorted
	 * @param gapSequence
	 * @return
	 */
	public static <T extends Comparable<T>> List<T> shellSort(List<T> unsorted, GAP gapSequence)
	{
		if(unsorted != null && !unsorted.isEmpty()) {
			Stack<Integer> gapStack = getGAPSequence(gapSequence, unsorted.size());
			Integer gap = gapStack.pop();
			
			while(gap > 0) {
				for(int i = gap; i < unsorted.size(); i++) {
					T temp = unsorted.get(i);
					int j = i;
					while(j >= gap && (unsorted.get(j - gap).compareTo(temp) > 0)) {
						unsorted.set(j, unsorted.get(j - gap));
						j -= gap;
					}
					unsorted.set(j, temp);
				}
				if(!gapStack.isEmpty()) {
					gap = gapStack.pop();
				} else {
					gap = 0;
				}
			}
		}
		return unsorted;
	}
	
	public static Stack<Integer> getGAPSequence(GAP gap, int elementsCount) {
		Stack<Integer> ret = new Stack<Integer>();
		int value = elementsCount;
		switch(gap) {
			case SHELL_SEQUENCE:
				int k = 1;
				while(value > 0) {
					value = (int) Math.floor(elementsCount / Math.pow(2, k));
					if(value > 0) {
						ret.push(value);
						k++;
					}
				}
				break;
			case PRATT_SEQUENCE :
				int last2ind = 0, last3ind = 0; 
				ret.push(1);
				for(int i = 1; i < elementsCount; ++i) {
					if(ret.get(last2ind)*2 < ret.get(last3ind)*3) {
						ret.push(ret.get(last2ind)*2);
						last2ind++;
					} else if(ret.get(last2ind)*2 > ret.get(last3ind)*3) {
						ret.push(ret.get(last3ind)*3);
						last3ind++;
					} else {
						ret.push(ret.get(last2ind)*2);
						last2ind++; 
						last3ind++; 
					}
				}
				break;
			case KNUTH_SEQUENCE :
				for(int i = 1; i < elementsCount; i++) {
					value = (int) (Math.pow(3, i)/2);
					ret.push(value);
				}
				break;
		}
		return ret;
	}
	
	
	/**
	 * Gap sizes
	 * SHELL_SEQUENCE FLOOR(N/2^k) i.e. [500, 250, 125, 62, 31, 15, 7, 3, 1] for N equals to 1000
	 * PRATT_SEQUENCE successive numbers of the form 2^p3^q i.e. [2 3 4 6 8 9 12 16 18 24 27 32 36 48 54 64 72 81 96 108 128 144 162 192 216 243 256 288 324 384 432 486 ...]
	 * KNUTH_SEQUENCE (3^k – 1)/2 i.e. [1, 4, 13, 40, 121, ...]
	 * @author mlimaki
	 *
	 */
	public enum GAP { SHELL_SEQUENCE, PRATT_SEQUENCE, KNUTH_SEQUENCE }
	
	/**
	 * 
	 * @param unsorted
	 * @return
	 */
	public static <T extends Comparable<T>> List<T> mergeSort(List<T> unsorted)
	{
		if(unsorted != null && !unsorted.isEmpty()) {
			int length = unsorted.size();
			if(length <= 1) {
				return unsorted;
			}
			
			List<T> left = mergeSort(new ArrayList<T>(unsorted.subList(0, length/2)));
			List<T> right = mergeSort(new ArrayList<T>(unsorted.subList(length/2, length)));
			
			return merge(left, right);
		}
		return unsorted;
	}
	
	private static <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right) {
		List<T> ret = new ArrayList<T>();
		while(!left.isEmpty() && !right.isEmpty()) {
			if(left.get(0).compareTo(right.get(0)) < 0) {
				ret.add(left.remove(0));
			} else {
				ret.add(right.remove(0));
			}	
		}
		
		while(!left.isEmpty()) {
			ret.add(left.remove(0));
		}
		while(!right.isEmpty()) {
			ret.add(right.remove(0));
		}
		
		return ret;
	}
	
	/**
	 * 
	 * @param unsorted
	 * @return
	 */
	public static <T extends Comparable<T>> List<T> quickSort(List<T> unsorted)
	{
		if(unsorted == null  || unsorted.size() <= 1) {
			return unsorted;
		}
		int pivotIndex = new Random().nextInt(unsorted.size()); // random not good enough???!!
		
		List<T> left = new ArrayList<T>();
		List<T> right = new ArrayList<T>();
		
		for(int i = 0; i < unsorted.size(); i++) {
			if(pivotIndex != i) {
				if(unsorted.get(i).compareTo(unsorted.get(pivotIndex)) <= 0) {
					left.add(unsorted.get(i));
				} else if(unsorted.get(i).compareTo(unsorted.get(pivotIndex)) > 0) {
					right.add(unsorted.get(i));
				}
			}
		}

		List<T> ret = new ArrayList<T>(); // are we hitting the memory heavily here?!!?
		if(!left.isEmpty()) {
			ret.addAll(quickSort(left));
		}
		
		ret.add(unsorted.get(pivotIndex));
		
		if(!right.isEmpty()) {
			ret.addAll(quickSort(right));
		}
		return ret;
	}
	
	public static <T extends Comparable<T>> List<T> heapSort(List<T> unsorted)
	{
		
		
		
		
		
		return null;
	}
	
	public static <T extends Comparable<T>> List<T> bucketSort(List<T> unsorted)
	{
		
		
		
		
		
		return null;
	}
	
	public static <T extends Comparable<T>> List<T> radixSort(List<T> unsorted)
	{
		
		
		
		
		
		return null;
	}
}