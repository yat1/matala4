import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * a junit test class, creates two random arrays from the same length, sorts them, compares the result between
 * bigThanMedianAlgo and bigThanMedianMerge
 * @author yehud
 */
class BigThanMedianTest {

	
	@Test
	void test() {
		Random rnd = new Random(124);
		int amount = 10000;
		int[] a = new int[amount];
		int[] b = new int[amount];
		for (int i = 0; i < amount; i++) {
			a[i] = rnd.nextInt(1000000);
			b[i] = rnd.nextInt(1000000);
		}
		Arrays.sort(a); 
		Arrays.sort(b); 
		
		assertArrayEquals(BigThanMedian.bigThanMedianAlgo(a,b), BigThanMedian.bigThanMedianMerge(a,b));
		
	}

}
