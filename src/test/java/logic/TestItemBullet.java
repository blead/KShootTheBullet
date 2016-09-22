package logic;

import static org.junit.Assert.*;
import logic.ItemBullet;
import logic.PlayerStatus;
import logic.SpecialGun;

import org.junit.Test;


public class TestItemBullet {
	
	@Test
	public void testConstructor(){
		ItemBullet itemBullet = new ItemBullet(20, 25);
		assertEquals(50, itemBullet.radius);
		assertEquals(20, itemBullet.movingDuration);
		assertEquals(25, itemBullet.getZ());
		assertEquals(30, itemBullet.requiredGrabbingTime);
		assertEquals(0, itemBullet.grabbingTimeCount);		
	}
	
	@Test
	public void testCollect(){
		ItemBullet itemBullet = new ItemBullet(20, 5);
		PlayerStatus player = new PlayerStatus();
		SpecialGun specialGun = new SpecialGun(15,30,4);
		player.setCurrentGun(specialGun);
		
		assertEquals(15, specialGun.bulletQuantity);
		itemBullet.collect(player);
		assertEquals(30, specialGun.bulletQuantity);
		
		player.setCurrentGun(new Gun(5));
		itemBullet = new ItemBullet(20, 5);
		itemBullet.collect(player);
		assertTrue(player.getCurrentGun() instanceof Gun);
	}

}
