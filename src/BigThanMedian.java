import java.util.Arrays;
import java.util.Random;

public class BigThanMedian {
	
	/**
	 * function gets two sorted arrays with random numbers, it returns an array with all the numbers that are bigger then 
	 * the median, each comparison is done in a separate thread, the results are added to the bigger array that will be returned
	 * the time it takes is counted and printed
	 * @param a
	 * @param b
	 * @return
	 */
	public static int[] bigThanMedianAlgo(int[] a, int[] b) {
		long start = System.currentTimeMillis();
		int[] bigger = new int[a.length];
		checkMax[] comparers = new checkMax[a.length];
		for (int i = 0; i < a.length; i++) {
			comparers[i] = new checkMax(a[i], b[a.length-i-1]);
			comparers[i].start();
		}
		for (checkMax cm : comparers) {
			try {
				cm.join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int i = 0;
		for (checkMax cm : comparers) {
			bigger[i] = cm.getMax();
			i++;
		}	
		System.out.println("in regular threads Lapse = " + (System.currentTimeMillis() - start)+ " miliseconds");
		Arrays.sort(bigger);
		return bigger;
	}
	
	/**
	 *  function gets two sorted arrays with random numbers, it returns an array with all the numbers that are bigger then 
	 * the median, both arrays are merged together, the biger half of the merged array is added to the bigger array 
	 * that will be returned the time it takes is counted and printed
	 * @param a
	 * @param b
	 * @return
	 */
	public static int[] bigThanMedianMerge(int[]a, int[] b){
		long start = System.currentTimeMillis();
		int[] res = new int[a.length+b.length];
		// Merge two sorted arrays into res[] 
	        int i = 0, j = 0, k = 0; 
	        while (i < a.length && j < b.length) { 
	            if (a[i] <= b[j]) { 
	                res[k] = a[i]; 
	                i += 1; 
	                k += 1; 
	            } else { 
	                res[k] = b[j]; 
	                j += 1; 
	                k += 1; 
	            } 
	        }        
	        while (i < a.length) {  // Merging remaining elements of a[] (if any) 
	            res[k] = a[i]; 
	            i += 1; 
	            k += 1; 
	        }     
	        while (j < b.length) {   // Merging remaining  elements of b[] (if any) 
	            res[k] = b[j]; 
	            j += 1; 
	            k += 1; 
	        }   
	        int[] bigger = new int[a.length];
	        for (int l = 0; l < bigger.length; l++) {
				bigger[l] = res[a.length+l];
			}
	        System.out.println("in merge Lapse = " + (System.currentTimeMillis() - start)+ " miliseconds");
	        Arrays.sort(bigger);
	        return bigger;
	    }



	public static void main(String[] args) {
		Random rnd = new Random(124);
		int amount = 100000;
		int[] a = new int[amount];
		int[] b = new int[amount];
		for (int i = 0; i < amount; i++) {
			a[i] = rnd.nextInt(1000000);
			b[i] = rnd.nextInt(1000000);
		}
		Arrays.sort(a); 
		Arrays.sort(b); 
		int[] c = bigThanMedianAlgo(a,b);
		int[] d = bigThanMedianMerge(a,b);
		/*System.out.print("a: ");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + ", ");
		}
		System.out.println("");
		System.out.print("b: ");
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i] + ", ");
		}
		System.out.println("");
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i] + ", ");
		}
		System.out.println("");
		
		for (int i = 0; i < d.length; i++) {
			System.out.print(d[i] + ", ");
		}
		System.out.println("");*/
		 if (Arrays.equals(c, d)) 
			  System.out.println("Same"); 
	        else
	            System.out.println("Not same"); 
	}		
}
