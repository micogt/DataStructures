package com.biz4people.sorting;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.biz4people.sorting.SortingAlgorithms.GAP;

/**
 * 
 * @author mlimaki
 *
 */
@RunWith(Parameterized.class)
public class SortingAlgorithmsTest {
	
	private List<Integer> unsorted;
	private List<Integer> sorted;
	
	public SortingAlgorithmsTest(List<Integer> unsorted, List<Integer> sorted) {
		this.unsorted = unsorted;
		this.sorted = sorted;
	}
	
	@Test
	public void testBubbleSort() {
		assertEquals(sorted, SortingAlgorithms.bubbleSort(unsorted));
	}

	@Test
	public void testInsertionSort() {
		assertEquals(sorted, SortingAlgorithms.insertionSort(unsorted));
	}

	@Test
	public void testShellSort() {
		assertEquals(sorted, SortingAlgorithms.shellSort(unsorted, GAP.SHELL_SEQUENCE));
		assertEquals(sorted, SortingAlgorithms.shellSort(unsorted, GAP.PRATT_SEQUENCE));
		assertEquals(sorted, SortingAlgorithms.shellSort(unsorted, GAP.KNUTH_SEQUENCE));
	}

	@Test
	public void testGetGAPSequence() {
		assertEquals(SortingAlgorithms.getGAPSequence(GAP.SHELL_SEQUENCE, 1000), Arrays.asList(new Integer[] {500, 250, 125, 62, 31, 15, 7, 3, 1})); 
		assertEquals(SortingAlgorithms.getGAPSequence(GAP.PRATT_SEQUENCE, 10), Arrays.asList(new Integer[] {1, 2, 3, 4, 6, 8, 9, 12, 16, 18})); 
		assertEquals(SortingAlgorithms.getGAPSequence(GAP.KNUTH_SEQUENCE, 10), Arrays.asList(new Integer[] {1, 4, 13, 40, 121, 364, 1093, 3280, 9841}));
	}

	@Test
	public void testMergeSort() {
		assertEquals(sorted, SortingAlgorithms.mergeSort(unsorted));
	}

	@Test
	public void testQuickSort() {
		assertEquals(sorted, SortingAlgorithms.quickSort(unsorted));
	}

	@Test
	public void testHeapSort() {
		//fail("Not yet implemented");
	}

	@Test
	public void testBucketSort() {
		//fail("Not yet implemented");
	}

	@Test
	public void testRadixSort() {
		//fail("Not yet implemented");
	}
	
	
	
	@Parameters
    public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
        		{
        			Arrays.asList(new Integer[] {8, 9, 2, 0, 0, -11100, 55, -44, 6, 773, 0, 9, -1111, 5, 11, 67, 5, -1, 0, 62, 98}),
        			Arrays.asList(new Integer[] {-11100, -1111, -44, -1, 0, 0, 0, 0, 2, 5, 5, 6, 8, 9, 9, 11, 55, 62, 67, 98, 773})
        		},
        		{
        			Arrays.asList(new Integer[] {5, 3, 1, -1, 9, 11, 18, 15, 2, 5, 0, 33, 78, 4, -20, 3, 0, 9, -1111, 5, 11, 67, 330, 56, 20, 1, 77, 2}),
        			Arrays.asList(new Integer[] {-1111, -20, -1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 5, 5, 9, 9, 11, 11, 15, 18, 20, 33, 56, 67, 77, 78, 330})
        		},
        		{
        			Arrays.asList(new Integer[] {6, 5, 3, 1, -1, 8, 7, 2, 4, 7, 9, 11, 18, 15, 2, 5, 0, 33, 78, 4, -20, 66, 99, 4, 1000, 4, 6, 3, 0, 9, -1111, 5, 11, 67, 330, 56, 20, 1, 77, 2}),
        			Arrays.asList(new Integer[] {-1111, -20, -1, 0, 0, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 7, 7, 8, 9, 9, 11, 11, 15, 18, 20, 33, 56, 66, 67, 77, 78, 99, 330, 1000})
        		}
        	});
    }
    
    
    public static void main(String[] args) {
		List<Integer> number = Arrays.asList(new Integer[]{8, 9, 2, 0, 0, -11100, 55, -44, 6, 773, 0, 9, -1111, 5, 11, 67, 5, -1, 0, 62, 98});
		System.out.println("Before: ");
		printList(number);
		System.out.println("");
		System.out.println("After: ");
		printList(SortingAlgorithms.quickSort(number));
		
		//printList(SortingAlgorithms.getGAPSequence(GAP.KNUTH_SEQUENCE, 10));
	}
	
	private static <T> void printList(List<T> printList)
	{
		System.out.print("[");
		int length = printList.size();
		for(int i = 0; i < length; i++) {
			System.out.print(printList.get(i));
			if(i < length - 1) {
				System.out.print(", ");
			}
		}
		System.out.print("]");	
	}
}
