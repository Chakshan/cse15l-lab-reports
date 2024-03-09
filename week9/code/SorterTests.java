import org.junit.*;
import static org.junit.Assert.*;

public class SorterTests {
	@Test
	public void testBubbleSort() {
		int[] input = {3, 5, 6, 2, 1, 3, 5};
		int[] expect = {1, 2, 3, 3, 5, 5, 6};
 
 
		Sorter.bubbleSort(input);
		assertArrayEquals(expect, input);
	}
}
