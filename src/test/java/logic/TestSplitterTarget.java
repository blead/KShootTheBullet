package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import lib.RandomUtility;

import org.junit.Test;

public class TestSplitterTarget {
	
	@Test
	public void testConstructor() {
		List<TargetObject> onScreenObject = new ArrayList<TargetObject>();
		
		SplitterTarget testingTarget = new SplitterTarget(11, 21, 31, onScreenObject);
		assertEquals(11, testingTarget.radius);	
		assertEquals(21, testingTarget.movingDuration);	
		assertEquals(31, testingTarget.getZ());
		assertEquals(5, testingTarget.life);	
		assertEquals(5, testingTarget.reward);	
		
		assertEquals(0, onScreenObject.size());	
	}
	
	@Test
	public void testHit() {
		for(int r=1; r<=100; r++){
			List<TargetObject> onScreenObject = new ArrayList<TargetObject>();
			int radius = RandomUtility.random2(10, 100);
			int movingDuration = RandomUtility.random2(10, 100);
			int z = RandomUtility.random2(10, 100);
			SplitterTarget target = new SplitterTarget(radius, movingDuration, z, onScreenObject);
			PlayerStatus player = new PlayerStatus();
			Gun gun = new Gun(1);
			player.setCurrentGun(gun);
			
			for(int i=0; i<=4; i++){
				assertEquals(5-i, target.life);
				assertFalse(target.isDestroyed);
				assertEquals(0,player.getScore());
				assertEquals(0,onScreenObject.size());
				target.hit(player);
			}
			
			assertEquals(0, target.life);
			assertTrue(target.isDestroyed);
			assertEquals(5,player.getScore());
			int splittedCount = onScreenObject.size();
			assertTrue(splittedCount >= 3 && splittedCount <= 6);
			
			for(TargetObject t : onScreenObject){
				t.move();
				assertEquals(target.x, t.x);
				assertEquals(target.y, t.y);
				assertEquals(radius/2, t.radius);
				assertEquals(movingDuration, t.movingDuration);
				assertEquals(z, t.getZ());
			}
		}
	}

}
