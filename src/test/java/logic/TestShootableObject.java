package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import logic.Gun;
import logic.PlayerStatus;
import logic.ShootableObject;

import org.junit.Test;


public class TestShootableObject {

	//Implemented for ShootableObject testing
	class TestingTarget extends ShootableObject{

		public TestingTarget(int radius,int movingDuration,int z,int reward) {
			super(radius, movingDuration, z, reward);
			setLife(3);
		}

		@Override
		public void render() {}
	}
 
	@Test
	public void testConstructor(){
		TestingTarget target = new TestingTarget(50,20,3,4);
		assertEquals(50,target.radius);
		assertEquals(20,target.movingDuration);
		assertEquals(3,target.getZ());
		assertEquals(4,target.reward);
		assertEquals(3,target.life);
	}
	
	@Test
	public void testSetLife(){
		TestingTarget target = new TestingTarget(50,20,3,4);
		target.setLife(8);
		assertEquals(8, target.life);
		assertFalse(target.isDestroyed);
		target.setLife(-5);
		assertEquals(0, target.life);
		assertTrue(target.isDestroyed);
	}
	
	@Test
	public void testHit(){
		TestingTarget target = new TestingTarget(50,20,3,4);
		PlayerStatus player = new PlayerStatus();
		Gun gun = new Gun(1);
		player.setCurrentGun(gun);
		
		for(int i=0; i<=2; i++){
			assertEquals(3-i, target.life);
			assertFalse(target.isDestroyed);
			assertEquals(0,player.getScore());
			target.hit(player);
		}
		
		assertEquals(0, target.life);
		assertTrue(target.isDestroyed);
		assertEquals(4,player.getScore());
	}
}
