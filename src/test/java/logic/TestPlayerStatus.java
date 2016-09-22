package logic;

import static org.junit.Assert.*;
import lib.ConfigurableOption;
import lib.GameManager;
import logic.PlayerStatus;

import org.junit.Test;


public class TestPlayerStatus {

	@Test
	public void testConstructor(){
		PlayerStatus player = new PlayerStatus();
		assertEquals(ConfigurableOption.timelimit*GameManager.TICK_PER_SECONDS, player.getRemainingTime() );
	}
	
	@Test
	public void testDecreaseRemainingTime(){
		PlayerStatus player = new PlayerStatus();
		int initialTime = ConfigurableOption.timelimit*GameManager.TICK_PER_SECONDS;
		player.decreaseRemainingTime(20);
		assertEquals(initialTime-20, player.getRemainingTime());
		player.decreaseRemainingTime(0);
		assertEquals(initialTime-20, player.getRemainingTime());
		player.decreaseRemainingTime(12);
		assertEquals(initialTime-32, player.getRemainingTime());
		player.decreaseRemainingTime(Integer.MAX_VALUE);
		assertEquals(0, player.getRemainingTime());
	}
	
	@Test
	public void testScore(){
		PlayerStatus player = new PlayerStatus();
		assertEquals(0, player.getScore());
		player.increaseScore(30);
		assertEquals(30, player.getScore());
		player.increaseScore(0);
		assertEquals(30, player.getScore());
	}
	
	@Test
	public void testRenderable(){
		PlayerStatus player = new PlayerStatus();
		assertTrue(player.isVisible());
		assertEquals(Integer.MAX_VALUE, player.getZ());
	}
}
