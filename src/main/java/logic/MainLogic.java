package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lib.ConfigurableOption;
import lib.DrawingUtility;
import lib.GameAnimation;
import lib.GameBackground;
import lib.GameManager;
import lib.HighScoreUtility;
import lib.IGameLogic;
import lib.IRenderableHolder;
import lib.IRenderableObject;
import lib.InputUtility;
import lib.RandomUtility;

public class MainLogic implements IRenderableHolder,IGameLogic{

	//All renderable objects
	private GameBackground background;
	private PlayerStatus player;
	private List<TargetObject> onScreenObject = new ArrayList<TargetObject>();
	private List<GameAnimation> onScreenAnimation = new ArrayList<GameAnimation>();
	
	/*
	 * Reserved z
	 * MIN_VALUE : background
	 * MAX_VALUE-1 : animation effect
	 * MAX_VALUE : player's status
	 */
	private int zCounter = Integer.MIN_VALUE+1;
	private int nextObjectCreationDelay;	
	private boolean readyToRender = false; //For dealing with synchronization issue
	
	//Called before enter the game loop
	public synchronized void onStart(){
		background = new GameBackground();
		player = new PlayerStatus();
		player.setCurrentGun(new Gun(1));
		nextObjectCreationDelay = RandomUtility.random(ConfigurableOption.objectCreationMinDelay, 
				ConfigurableOption.objectCreationMaxDelay);
		readyToRender = true;
	}
	
	//Called after exit the game loop
	public synchronized void onExit(){
		readyToRender = false;
		onScreenObject.clear();
	}
	
	public void logicUpdate(){
		//Paused
		if(InputUtility.isTypeEnter()){
			/* fill code1 */
		}
		
		if(player.isPause()){
			return;
		}
		
		//Update moving background
		background.updateBackground();
		
		//Time up
		if(player.getRemainingTime() == 0){
			HighScoreUtility.recordHighScore(player.getScore());
			GameManager.goToTitle();
			return;
		}
		player.decreaseRemainingTime(1);
		
		//Create random target
		createTarget();
		
		//Shoot and grab
		TargetObject target = null;
		TargetObject grabbedObject = null;
		if(!player.isDisplayingArea(InputUtility.getMouseX(), InputUtility.getMouseY())){
			
			boolean shoot = false;
			if((InputUtility.isMouseLeftClicked() || InputUtility.isTypeSpace()) && 
					player.getCurrentGun() != null &&
					player.getCurrentGun().canShoot()){
				
				/* fill code2 */
								
				onScreenAnimation.add(DrawingUtility.createShootingAnimationAt(
						InputUtility.getMouseX(), InputUtility.getMouseY()));
				shoot = true;
			}
			
			target = getTopMostTargetAt(InputUtility.getMouseX(), InputUtility.getMouseY());
			if(target != null){
				
				/* fill code3 */				
			}
			
			if(shoot && player.getCurrentGun() instanceof SpecialGun && 
					((SpecialGun)player.getCurrentGun()).getBulletQuantity() <= 0){
				
				/* fill code4 */				
			}
		}
		
		//Update target object
		for(TargetObject obj : onScreenObject){
			if(obj instanceof CollectibleObject && grabbedObject != obj){
				((CollectibleObject)obj).ungrab(); //Ungrab
			}
			obj.move();
		}
		
		//Update animation
		for(GameAnimation obj : onScreenAnimation){
			obj.updateAnimation();
		}
		
		//Remove unused image
		for(int i=onScreenObject.size()-1; i>=0; i--){
			if(onScreenObject.get(i).isDestroyed)
				onScreenObject.remove(i);
		}
		for(int i=onScreenAnimation.size()-1; i>=0; i--){
			if(!onScreenAnimation.get(i).isVisible())
				onScreenAnimation.remove(i);
		}
	}
	
	private void createTarget(){
		if(nextObjectCreationDelay > 0){
			nextObjectCreationDelay--;
		}else{
			//Random next creation delay
			nextObjectCreationDelay = RandomUtility.random(ConfigurableOption.objectCreationMinDelay, 
					ConfigurableOption.objectCreationMaxDelay);
			
			//Random moving duration
			int movingDuration = RandomUtility.random(ConfigurableOption.objectMinDuration, ConfigurableOption.objectMaxDuration);
			
			//Random target type
			/* fill code */
			
			//Increase z counter (so the next object will be created on top of the previous one)
			zCounter++;
			if(zCounter == Integer.MAX_VALUE-1){
				zCounter = Integer.MIN_VALUE+1;
			}
		}
	}
	
	private TargetObject getTopMostTargetAt(int x,int y){
		TargetObject obj = null;
		for(TargetObject target : onScreenObject){
			if(target.contains(x, y)){
				if(obj != null){
					if(target.getZ() > obj.getZ()){
						obj.setPointerOver(false);
						obj = target;
						obj.setPointerOver(true);
					}
				}else{
					obj = target;
					obj.setPointerOver(true);
				}
			}else{
				target.setPointerOver(false);
			}
		}
		return obj;
	}

	@Override
	public synchronized List<IRenderableObject> getSortedRenderableObject() {
		List<IRenderableObject> sortedRenderable = new ArrayList<IRenderableObject>();
		if(!readyToRender) return sortedRenderable;
		for(TargetObject object : onScreenObject){
			sortedRenderable.add(object);
		}
		for(GameAnimation object : onScreenAnimation){
			sortedRenderable.add(object);
		}
		sortedRenderable.add(player);
		sortedRenderable.add(background);
		
		Collections.sort(sortedRenderable, new Comparator<IRenderableObject>() {
			@Override
			public int compare(IRenderableObject o1, IRenderableObject o2) {
				if(o1.getZ() > o2.getZ())
					return 1;
				else if(o1.getZ() < o2.getZ())
					return -1;
				else
					return 0;
			}
		});
		return sortedRenderable;
	}
}
