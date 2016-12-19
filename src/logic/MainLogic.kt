package logic

import java.util.ArrayList
import java.util.Collections

import lib.ConfigurableOption
import lib.DrawingUtility
import lib.GameAnimation
import lib.GameBackground
import lib.GameManager
import lib.HighScoreUtility
import lib.IGameLogic
import lib.IRenderableHolder
import lib.IRenderableObject
import lib.InputUtility
import lib.RandomUtility

class MainLogic : IRenderableHolder, IGameLogic {
    /* all renderable objects */
    lateinit private var background: GameBackground
    lateinit private var player: PlayerStatus
    private val onScreenObject = mutableListOf<TargetObject>()
    private val onScreenAnimation = mutableListOf<GameAnimation>()

    /*
	 * Reserved z MIN_VALUE : background MAX_VALUE-1 : animation effect
	 * MAX_VALUE : player's status
	 */
    private var zCounter = Int.MIN_VALUE + 1
    private var nextObjectCreationDelay = 0
    private var readyToRender = false /* for dealing with synchronization issue */

    /* called before entering the game loop */
    @Synchronized override fun onStart() {
        background = GameBackground()
        player = PlayerStatus()
        player.currentGun = Gun(1)
        nextObjectCreationDelay = RandomUtility.random(ConfigurableOption.objectCreationMinDelay, ConfigurableOption.objectCreationMaxDelay)
        readyToRender = true
    }

    /* called after exiting the game loop */
    @Synchronized override fun onExit() {
        readyToRender = false
        onScreenObject.clear()
    }

    override fun logicUpdate() {
        if (InputUtility.isTypeEnter())
            player.pause = !player.pause

        if (player.pause)
            return

        /* update moving background */
        background.updateBackground()

        if (player.remainingTime == 0) {
            HighScoreUtility.recordHighScore(player.score)
            GameManager.goToTitle()
            return
        }
        player.decreaseRemainingTime(1)

        /* create random target */
        createTarget()

        /* shoot and grab */
        val target: TargetObject?
        var grabbedObject: TargetObject? = null
        if (!player.isDisplayingArea(InputUtility.getMouseX(), InputUtility.getMouseY())) {
            var shoot = false
            if ((InputUtility.isMouseLeftClicked() || InputUtility.isTypeSpace()) && player.currentGun.canShoot()) {
                player.currentGun.shoot()
                onScreenAnimation.add(DrawingUtility.createShootingAnimationAt(InputUtility.getMouseX(), InputUtility.getMouseY()))
                shoot = true
            }
            target = getTopMostTargetAt(InputUtility.getMouseX(), InputUtility.getMouseY())
            if (target is CollectibleObject) {
                target.grab(player)
                grabbedObject = target
            } else if (shoot && target is ShootableObject) {
                target.hit(player)
            }

            if (shoot && player.currentGun is SpecialGun && (player.currentGun as SpecialGun).bulletQuantity <= 0) {
                player.currentGun = Gun(1)
            }
        }
        /* update objects */
        onScreenObject.filter { it !== grabbedObject }.filterIsInstance<CollectibleObject>().forEach { it.ungrab() }
        onScreenObject.forEach { it.move() }

        /* update animations */
        onScreenAnimation.forEach { it.updateAnimation() }

        /* remove unused images */
        onScreenObject.filter { it.isDestroyed }.forEach { onScreenObject.remove(it) }
        onScreenAnimation.filter { !it.isVisible }.forEach { onScreenAnimation.remove(it) }
    }

    private fun createTarget() {
        if (nextObjectCreationDelay > 0) {
            nextObjectCreationDelay--
        } else {
            /* random next creation delay */
            nextObjectCreationDelay = RandomUtility.random(ConfigurableOption.objectCreationMinDelay, ConfigurableOption.objectCreationMaxDelay)

            /* random moving duration */
            val movingDuration = RandomUtility.random(ConfigurableOption.objectMinDuration, ConfigurableOption.objectMaxDuration)

            /* random target type */
            val rand = RandomUtility.random(1, 100)
            when(rand) {
                in 1..15 -> onScreenObject.add(if (player.currentGun is SpecialGun) ItemBullet(movingDuration, zCounter) else ItemSpecialGun(movingDuration, zCounter))
                in 16..35 -> onScreenObject.add(SplitterTarget(40, movingDuration, zCounter, onScreenObject))
                in 36..70 -> onScreenObject.add(SmallTarget(15, movingDuration, zCounter))
                else -> onScreenObject.add(SimpleTarget(15, movingDuration, zCounter))
            }

            /* Increase z counter (so the next object will be created on top of the previous one) */
            zCounter++
            if (zCounter == Integer.MAX_VALUE - 1) {
                zCounter = Integer.MIN_VALUE + 1
            }
        }
    }

    private fun getTopMostTargetAt(x: Int, y: Int): TargetObject? {
        onScreenObject.filterNot { it.contains(x,y) }.forEach { it.isPointerOver = false }
        val obj = onScreenObject.filter { it.contains(x,y) }.maxBy { it.z }
        obj?.isPointerOver = true
        return obj
    }

    @Synchronized override fun getSortedRenderableObject(): List<IRenderableObject> {
        val sortedRenderable = ArrayList<IRenderableObject>()
        if (!readyToRender)
            return sortedRenderable
        onScreenObject.forEach { sortedRenderable.add(it) }
        onScreenAnimation.forEach { sortedRenderable.add(it) }
        sortedRenderable.add(player)
        sortedRenderable.add(background)

        Collections.sort(sortedRenderable) { o1, o2 ->
            if (o1.z > o2.z)
                1
            else if (o1.z < o2.z)
                -1
            else
                0
        }
        return sortedRenderable
    }
}
