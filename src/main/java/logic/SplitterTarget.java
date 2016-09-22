package logic;

import java.util.List;
import lib.DrawingUtility;
import lib.RandomUtility;

public class SplitterTarget /* fill code */{

	private List<TargetObject> onScreenObject;
	
	public SplitterTarget(int radius, int movingDuration, int z,
			List<TargetObject> onScreenObject) {
		/* fill code */
		this.onScreenObject = onScreenObject;
	}
	
	@Override
	public void hit(PlayerStatus player){
		/* fill code */
	}

	@Override
	public void render() {
		DrawingUtility.drawShootableObject(x, y, radius, "splitter", isPointerOver);
	}

}
