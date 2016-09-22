package logic;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.CollectibleObject;
import logic.PlayerStatus;

public class TestCollectibleObject {
	
	//Implemented for CollectibleObject testing
	class TestingItem extends CollectibleObject{
		public boolean isCollected = false;
		public TestingItem(int radius, int movingDuration, int z,
				int requiredGrabbingTime) {
			super(radius, movingDuration, z, requiredGrabbingTime);
		}
		
		@Override
		public void render() {}
		@Override
		public void collect(PlayerStatus player) {
			isCollected = true;
		}
		
	}
	
	@Test
	public void testConstructor(){
		TestingItem item = new TestingItem(50, 40, 30, 20);
		assertEquals(50, item.radius);
		assertEquals(40, item.movingDuration);
		assertEquals(30, item.getZ());
		assertEquals(20, item.requiredGrabbingTime);
		assertEquals(0, item.grabbingTimeCount);
	}
	
	@Test
	public void testUngrab(){
		TestingItem item = new TestingItem(50, 40, 30, 20);
		assertEquals(0, item.grabbingTimeCount);
		item.grabbingTimeCount = 5;
		item.ungrab();
		assertEquals(0, item.grabbingTimeCount);
	}
	
	@Test
	public void testGrab(){
		PlayerStatus p = new PlayerStatus();
		TestingItem item = new TestingItem(50, 40, 30, 3);
		assertEquals(0, item.grabbingTimeCount);
		
		item.grab(p);
		assertEquals(1, item.grabbingTimeCount);
		assertFalse(item.isCollected);
		
		item.grab(p);
		assertEquals(2, item.grabbingTimeCount);
		assertFalse(item.isCollected);
		
		item.grab(p);
		assertEquals(3, item.grabbingTimeCount);
		assertFalse(item.isCollected);
		
		item.grab(p);
		assertEquals(3, item.grabbingTimeCount);
		assertTrue(item.isCollected);
	}
}
