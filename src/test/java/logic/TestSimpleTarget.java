package logic;

import static org.junit.Assert.assertEquals;
import logic.SimpleTarget;

import org.junit.Test;

public class TestSimpleTarget {
	
	@Test
	public void testConstructor() {
		SimpleTarget target = new SimpleTarget(5, 6, 4);
		assertEquals(5, target.radius);
		assertEquals(6, target.movingDuration);
		assertEquals(4, target.getZ());
		assertEquals(3, target.life);
		assertEquals(3, target.reward);
		
	}
}
