package logic;

import lib.ConfigurableOption;
import lib.IRenderableObject;
import lib.MotionUtility;
import lib.RandomUtility;

public /* fill code */ class TargetObject /* fill code */{

	protected int x,y,z = 0;
	protected int radius;
	protected boolean isDestroyed = false;
	
	protected int[] movingParameter;
	protected int movingDuration;
	protected int movingDurationCounter = 0;
	protected int movingType;
	protected boolean isPointerOver = false;

	public TargetObject(int radius,int movingDuration,int z){
		this.z = z;
		this.radius = radius;
		this.movingDuration = movingDuration;
		
		//Setup moving parameters
		movingParameter = new int[8];
		
		boolean moveVertically = RandomUtility.random(0, 1) == 1;
		if(moveVertically){
			movingParameter[0] = RandomUtility.random(radius, ConfigurableOption.screenWidth-radius);
			movingParameter[1] = -radius;
			movingParameter[2] = RandomUtility.random(radius, ConfigurableOption.screenWidth-radius);
			movingParameter[3] = ConfigurableOption.screenHeight+radius;
		}else{
			movingParameter[0] = -radius;
			movingParameter[1] = RandomUtility.random(radius, ConfigurableOption.screenHeight-radius);
			movingParameter[2] = ConfigurableOption.screenWidth+radius;
			movingParameter[3] = RandomUtility.random(radius, ConfigurableOption.screenHeight-radius);
		}
		
		//Randomly flip endpoint
		if(RandomUtility.random(0, 1) == 1){
			int a = movingParameter[0], b = movingParameter[1]; 
			movingParameter[0] = movingParameter[2];
			movingParameter[1] = movingParameter[3];
			movingParameter[2] = a;
			movingParameter[3] = b;
		}
		
		
		movingType = RandomUtility.random(0, 2);
		if(movingType == 1){
			if(moveVertically){
				movingParameter[4] = RandomUtility.random(radius, ConfigurableOption.screenWidth-radius);
				movingParameter[5] = (movingParameter[1]+movingParameter[3])/2;
			}else{
				movingParameter[4] = (movingParameter[0]+movingParameter[2])/2;
				movingParameter[5] = RandomUtility.random(radius, ConfigurableOption.screenHeight-radius);
			}
		}else if(movingType == 2){
			movingParameter[4] = RandomUtility.random(radius, ConfigurableOption.screenWidth-radius);
			movingParameter[5] = RandomUtility.random(radius, ConfigurableOption.screenHeight-radius);
			movingParameter[6] = RandomUtility.random(radius, ConfigurableOption.screenWidth-radius);
			movingParameter[7] = RandomUtility.random(radius, ConfigurableOption.screenHeight-radius);
		}
		
		this.x = movingParameter[0];
		this.y = movingParameter[1];
	}
	
	public boolean contains(int x,int y){
		return Math.hypot(x-this.x, y-this.y) <= radius+6;
	}
	
	public void setPointerOver(boolean isPointerOver) {
		this.isPointerOver = isPointerOver;
	}
	
	public void move(){
		if(isDestroyed) return;
		if(movingDurationCounter > movingDuration){
			isDestroyed = true;
			return;
		}
		
		int[] coord;
		switch(movingType){
		case 2:
			coord = MotionUtility.cubicCurveMotion(movingParameter[0], movingParameter[1],
					movingParameter[4], movingParameter[5],
					movingParameter[6], movingParameter[7],
					movingParameter[2], movingParameter[3],
					movingDuration, movingDurationCounter);
			break;
		case 1:
			coord = MotionUtility.curveMotion(movingParameter[0], movingParameter[1],
					movingParameter[4], movingParameter[5],
					movingParameter[2], movingParameter[3],
					movingDuration, movingDurationCounter);
			break;
		case 0:
		default:
			coord = MotionUtility.linearMotion(movingParameter[0], movingParameter[1],
					movingParameter[2], movingParameter[3],
					movingDuration, movingDurationCounter);
		}
		
		x = coord[0];
		y = coord[1];
		movingDurationCounter++;
	}
	
	/* fill code interface methods */
		
}
