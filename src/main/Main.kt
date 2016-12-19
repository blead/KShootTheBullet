import lib.GameManager
import logic.MainLogic

fun main(args: Array<String>) {
    val logic = MainLogic()
    GameManager.runGame(logic)
}
