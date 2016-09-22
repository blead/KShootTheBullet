package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import logic.Gun;

import org.junit.Test;


public class TestGun {

	@Test
	public void testConstructor(){
		Gun gun = new Gun(3);
		assertEquals(3, gun.getAttack());
		gun = new Gun(0);
		assertEquals(0, gun.getAttack());
	}
	
	@Test
	public void testShoot(){
		Gun gun = new Gun(3);
		assertTrue(gun.canShoot());
		gun = new Gun(0);
		assertTrue(gun.canShoot());
	}
}
