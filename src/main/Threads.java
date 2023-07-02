package main;

import java.util.Arrays;
import java.util.Random;

public class Threads {
		
	/* Multiple thread array sum
	 * Create array of 200M
	 * Loop to place random number 1 - 10
	 * Start time
	 * Multiple thread method
	 * 		Method to sum numbers in array
	 * Stop time
	 * Start time
	 * Single thread method
	 * 		Method to sum numbers in array
	 * Stop time
	 * Print sum and time
	 */
	
	/* Implement test case - extra credit
	 * Method to sum numbers in array
	 */
	
	
//	Generate variables 
//		Array to store random numbers.
//		Int's to store sum of array.
//		Long's to store time.
	private static int[] randomArray = new int[2000000];
	private static int multiThreadSum = 0, multiThreadSum1 = 0, multiThreadSum2 = 0, singleThreadSum = 0;
	private static long multiStartTime = 0, multiEndTime = 0, multiTotalTime = 0, multiTotalTime1 = 0, multiTotalTime2 = 0, 
			singleStartTime = 0, singleEndTime = 0, singleTotalTime = 0;

	public static void main(String[] args) {
		
//		Must run randomizeArray() now to give random values to global randomArray
		randomizeArray();
		
//		First thread to take first half of randomArray
		Thread t1 = new Thread (new Runnable() {

			@Override
			public void run() {
//				Capture start time
//				Sum of first half randomArray
//				Capture end time
//				Evaluate total time
				multiStartTime = System.nanoTime();
				multiThreadSum1 = sumArray(Arrays.copyOfRange(randomArray, 0, 1000000));
				multiEndTime = System.nanoTime();
				multiTotalTime1 = multiEndTime - multiStartTime;
			}
			
		});
		
//		Second thread to take last half of randomArray
		Thread t2 = new Thread (new Runnable() {

			@Override
			public void run() {
//				Capture start time
//				Sum of last half randomArray
//				Capture end time
//				Evaluate total time
				multiStartTime = System.nanoTime();
				multiThreadSum2 = sumArray(Arrays.copyOfRange(randomArray, 1000000, 2000000));
				multiEndTime = System.nanoTime();
				multiTotalTime2 = multiEndTime - multiStartTime;
			}
			
		});
		
//		Run Threads
		t1.start();
		t2.start();
		
//		Ensure threads run before trying to print to console
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Math to combine t1 and t1 variable
		multiThreadSum = multiThreadSum1 + multiThreadSum2;
		multiTotalTime = multiTotalTime1 + multiTotalTime2;
		
//		Print Multiple Thread variables
		System.out.println("Multithread Sum: " + multiThreadSum);
		System.out.println("Multithread Time: " + multiTotalTime);
		
//		Single Thread as usual
		singleStartTime = System.nanoTime();
		singleThreadSum = sumArray(randomArray);
		singleEndTime = System.nanoTime();
		
//		Math for time variable
		singleTotalTime = singleEndTime - singleStartTime;
		
//		Print Single Thread variables
		System.out.println("Single Thread Sum: " + singleThreadSum);
		System.out.println("Single Thread Time: " + singleTotalTime);
	}
	
//	Sum the randomArray
	public static synchronized int sumArray(int[] numbers) {
		int sum = 0;
		
//		For each loop to add sum of array
		for (int num : numbers){
			sum += num;
		}
		
		return sum;
	}
	
	public static void randomizeArray() {
//		Instantiate Random object to generate random numbers for array.
		Random rand = new Random();
		
//		Loop through randomArray to give indexes value
		for (int i = 0; i < randomArray.length; i++) {
			randomArray[i] = rand.nextInt(10) + 1;
		}
	}

}
