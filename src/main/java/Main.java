import lib.GameManager;
import logic.MainLogic;


public class Main {

	public static void main(String[] args){
		MainLogic logic = new MainLogic();
		GameManager.runGame(logic);
	}
}
