package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class SumTest {

	@Test
	public void test() {
		int[] testArray = {1, 1, 1, 1, 1};
		int sum = Threads.sumArray(testArray);
		assertSame(5, sum);
	}

}
