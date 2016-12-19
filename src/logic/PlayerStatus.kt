package logic

import lib.ConfigurableOption
import lib.DrawingUtility
import lib.GameManager
import lib.IRenderableObject

class PlayerStatus() : IRenderableObject {
    var remainingTime = ConfigurableOption.timelimit * GameManager.TICK_PER_SECONDS
        private set
    var score = 0
        private set
    lateinit var currentGun: Gun
    var pause = false

    fun decreaseRemainingTime(amount: Int) {
        remainingTime -= amount
    }

    fun increaseScore(score: Int) {
        this.score += score
    }

    fun isDisplayingArea(x: Int, y: Int) = y < 40

    override fun isVisible() = true

    override fun getZ() = Int.MAX_VALUE

    override fun render() = DrawingUtility.drawStatusBar(remainingTime / GameManager.TICK_PER_SECONDS, score, currentGun, pause)
}
