package logic;

import lib.ConfigurableOption;
import lib.DrawingUtility;
import lib.RandomUtility;

public class SmallTarget /* fill code */ {

	public SmallTarget(int radius, int movingDuration, int z){
		/* fill code */
	}
	
	public SmallTarget(int radius, int movingDuration, int z, 
			int startX,int startY) {
		/* fill code */
				
		movingType = 0;
		if(RandomUtility.random(0, 1) == 1){
			movingParameter = new int[]{
					startX,startY,
					RandomUtility.random(0,1) == 0 ? -radius : ConfigurableOption.screenWidth+radius,
					RandomUtility.random(-radius, ConfigurableOption.screenHeight+radius)
			};
		}else{
			movingParameter = new int[]{
					startX,startY,
					RandomUtility.random(-radius, ConfigurableOption.screenWidth+radius),
					RandomUtility.random(0,1) == 0 ? -radius : ConfigurableOption.screenHeight+radius
			};
		}
	}

	@Override
	public void render() {
		DrawingUtility.drawShootableObject(x, y, radius, "small", isPointerOver);		
	}

}
