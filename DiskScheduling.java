package Homework;
import java.util.Arrays;

public class DiskScheduling {
	
	public static void main(String[] args) {
		int[] cyl = new int[1000];
		
		for (int i = 0; i < 1000; i++) {
			cyl[i] = (int) (Math.random() * 5000);
		}
		
		int num = Integer.parseInt(args[0]);
		
		int[] test = {2069, 1212, 2296, 2800, 544, 1618, 356, 1523, 4965, 3681};
		
		FCFS(cyl, num);
		SSTF(cyl, num);
		SCAN(cyl, num);
		CSCAN(cyl, num);
		SCAN(test, num);
		CSCAN(test, num);
//		LOOK(cyl, num);
//		CLOOK(cyl, num);
	}
	
	public static void FCFS(int[] cyl, int start) {
		int movement = cyl[0] - start;
		
		for (int i = 1; i < cyl.length; i++) {
			movement += Math.abs(cyl[i] - cyl[i-1]);
		}
		
		System.out.println("First Come First Serve Algorithm goes through " + movement + " cylinders.");
	}

	public static void SSTF(int[] cyl, int start) {
		int movement = cyl[0] - start;
		int iClosest = closestIndex(cyl, start);
		
		int temp = cyl[0]; cyl[0] = cyl[iClosest]; cyl[iClosest] = temp;
		
		// After going through n-1 elements, the nth element will
		// always be the last one visited
		for (int i = 0; i < cyl.length-1; i++) {
			int closest = Math.abs(cyl[i] - cyl[i+1]);
			iClosest = 0;
			
			for (int j = i+1; j < cyl.length; j++) {
				int val = Math.abs(cyl[i] - cyl[j]);
				if (val < closest) {
					closest = val;
					iClosest = j;
				}
			}
			
			temp = cyl[i]; cyl[i] = cyl[iClosest]; cyl[iClosest] = temp;
		}
		
		for (int i = 1; i < cyl.length; i++) {
			movement += Math.abs(cyl[i] - cyl[i-1]);
		}
		
		System.out.println("Shortest Seek Time First Algorithm goes through " + movement + " cylinders.");
	}

	public static void SCAN(int[] cyl, int start) {
		int movement = cyl[0];
		Arrays.sort(cyl);
		
		int iStart = closestIndex(cyl, start);
		iStart = cyl[iStart] > start ? iStart - 1 : iStart; 
		
		movement = Math.abs(cyl[iStart] - start);
//		System.out.println("From " + iStart + ", " + Arrays.toString(cyl));
		
		for (int i = iStart; i > 0; i--) {
			movement += Math.abs(cyl[i] - cyl[i-1]);
		}
		
		movement += Math.abs(cyl[0] - cyl[iStart+1]);
		
		for (int i = iStart+1; i < cyl.length-1; i++) {
			movement += Math.abs(cyl[i] - cyl[i+1]);
		}
		
		System.out.println("SCAN Algorithm goes through " + movement + " cylinders.");
	}

	public static void CSCAN(int[] cyl, int start) {
		int movement = cyl[0] - start;
		Arrays.sort(cyl);
		int iStart = closestIndex(cyl, start);
//		System.out.println("From " + iStart + ", " + Arrays.toString(cyl));
		
		for (int i = iStart; i < cyl.length-1; i++) {
			movement += Math.abs(cyl[i] - cyl[i+1]);
		}
		
		movement += Math.abs(cyl[cyl.length-1] - 5000) + cyl[0];
		
		for (int i = 0; i < iStart-1; i++) {
			movement += Math.abs(cyl[i] - cyl[i+1]);
		}
		
		System.out.println("C-SCAN Algorithm goes through " + movement + " cylinders.");
	}

//	public static void LOOK(int[] cyl, int start) {
//		int movement = cyl[0] - start;
//		
//		for (int i = 1; i < cyl.length; i++) {
//			movement += Math.abs(cyl[i] - cyl[i-1]);
//		}
//		
//		System.out.println("First Come First Serve Algorithm goes through " + movement + " cylinders.");
//	}
//
//	public static void CLOOK(int[] cyl, int start) {
//		int movement = cyl[0] - start;
//		
//		for (int i = 1; i < cyl.length; i++) {
//			movement += Math.abs(cyl[i] - cyl[i-1]);
//		}
//		
//		System.out.println("First Come First Serve Algorithm goes through " + movement + " cylinders.");
//	}
	
	public static int closestIndex(int[] arr, int val) {
		int closest = Math.abs(arr[0] - val);
		int retVal = 0;
		
		for (int i = 0; i < arr.length; i++) {
			int dist = Math.abs(arr[i]-val);
			if (dist < closest) {
				closest = dist;
				retVal = i;
			}
		}
		
		return retVal;
	}
	
}
