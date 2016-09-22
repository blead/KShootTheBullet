package logic;

import lib.DrawingUtility;

public class ItemBullet /* fill code */{

	public ItemBullet(int movingDuration, int z) {
		/* fill code */	
	}

	@Override
	public void render() {
		DrawingUtility.drawItemBullet(x, y, radius, isPointerOver);
	}

	@Override
	public void collect(PlayerStatus player) {
		/* fill code */
	}

}
