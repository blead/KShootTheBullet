package logic;

import static org.junit.Assert.*;
import org.junit.Test;

import logic.ItemSpecialGun;
import logic.PlayerStatus;

public class TestItemSpecialGun {
	
	@Test
	public void testConstructor() {
		ItemSpecialGun itemSpecialGun = new ItemSpecialGun(10, 40);
		assertEquals(50, itemSpecialGun.radius);
		assertEquals(10, itemSpecialGun.movingDuration);
		assertEquals(40, itemSpecialGun.getZ());
		assertEquals(50, itemSpecialGun.requiredGrabbingTime);
		assertEquals(0, itemSpecialGun.grabbingTimeCount);		
	}
	
	@Test
	public void testCollect(){
		PlayerStatus player = new PlayerStatus();
		ItemSpecialGun itemSpecialGun = new ItemSpecialGun(10, 40);
		itemSpecialGun.collect(player);
		SpecialGun specialGun = (SpecialGun)player.getCurrentGun();
		assertEquals(20, specialGun.maxBullet);
		assertEquals(20, specialGun.bulletQuantity);
		assertEquals(3, specialGun.attack);
	}

}
