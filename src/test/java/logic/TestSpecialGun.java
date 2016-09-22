package logic;
import static org.junit.Assert.*;
import logic.SpecialGun;

import org.junit.Test;


public class TestSpecialGun {

	@Test
	public void testConstructor(){
		SpecialGun gun = new SpecialGun(10, 20, 4);
		assertEquals(4, gun.getAttack());
		assertEquals(10, gun.getBulletQuantity());
		assertEquals(20, gun.maxBullet);
		
		gun = new SpecialGun(50, 20, 4);
		assertEquals(4, gun.getAttack());
		assertEquals(20, gun.getBulletQuantity());
		assertEquals(20, gun.maxBullet);
		
		gun = new SpecialGun(-5, 20, 4);
		assertEquals(4, gun.getAttack());
		assertEquals(0, gun.getBulletQuantity());
		assertEquals(20, gun.maxBullet);
	}
	
	@Test
	public void testSetBullet(){
		SpecialGun gun = new SpecialGun(20, 20, 4);
		gun.setBulletQuantity(10);
		assertEquals(10, gun.getBulletQuantity());
		gun.setBulletQuantity(30);
		assertEquals(20, gun.getBulletQuantity());
		gun.setBulletQuantity(-100);
		assertEquals(0, gun.getBulletQuantity());
	}
	
	@Test
	public void testCanShoot(){
		SpecialGun gun = new SpecialGun(20, 20, 4);
		assertTrue(gun.canShoot());
		
		gun.setBulletQuantity(0);
		assertFalse(gun.canShoot());
	}
	
	@Test
	public void testShoot(){
		SpecialGun gun = new SpecialGun(20, 20, 4);
		gun.shoot();
		gun.shoot();
		assertEquals(18, gun.getBulletQuantity());
	}

}
