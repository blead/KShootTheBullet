package logic;


import lib.DrawingUtility;

public class SimpleTarget /* fill code */ {

	public SimpleTarget(int radius,int movingDuration,int z) {
		/* fill code */
	}

	@Override
	public void render() {
		DrawingUtility.drawShootableObject( x, y, radius, "simple", isPointerOver);
	}

}
