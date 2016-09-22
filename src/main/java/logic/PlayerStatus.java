package logic;

import lib.ConfigurableOption;
import lib.DrawingUtility;
import lib.GameManager;
import lib.IRenderableObject;

public class PlayerStatus /* fill code */ {

	private int remainingTime = 0;
	private int score = 0;
	private Gun currentGun = null;
	private boolean pause = false;
	
	public PlayerStatus(){
		/* fill code */
	}

	/* fill required getter & setter */

	public void decreaseRemainingTime(int amount) {
		/* fill code */ 
	}
	
	public void increaseScore(int score) {
		/* fill code */;
	}

	public boolean isDisplayingArea(int x,int y){
		return y < 40;
	}

	/* fill code interface methods */
	
}
