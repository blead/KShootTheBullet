package logic;

import static org.junit.Assert.assertEquals;
import logic.SmallTarget;

import org.junit.Test;

public class TestSmallTarget {
	
	@Test
	public void testConstructor() {
		SmallTarget target = new SmallTarget(5, 4, 3);
		assertEquals(5, target.radius);	
		assertEquals(4, target.movingDuration);	
		assertEquals(3, target.getZ());
		assertEquals(2, target.life);	
		assertEquals(5, target.reward);
		
		target = new SmallTarget(1, 2, 3, 4, 5);
		assertEquals(1, target.radius);	
		assertEquals(2, target.movingDuration);	
		assertEquals(3, target.getZ());
		assertEquals(2, target.life);	
		assertEquals(5, target.reward);	
	}
}
