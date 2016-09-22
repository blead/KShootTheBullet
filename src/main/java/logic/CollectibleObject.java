package logic;

import lib.AudioUtility;

public /* fill code */ class CollectibleObject /* fill code */{
	
	protected int requiredGrabbingTime;
	protected int grabbingTimeCount;
	
	public CollectibleObject(int radius, int movingDuration, int z, int requiredGrabbingTime) {
		/* fill code */
	}
	
	public void ungrab(){
		/* fill code */
	}

	public void grab(PlayerStatus player){
		if(isDestroyed) return;
		if(/* fill code */){
			AudioUtility.playSound("collect");
			collect(player);
			isDestroyed = true;
			return;
		}
		/* fill code */
	}
	
	public abstract void collect(PlayerStatus player);
}
