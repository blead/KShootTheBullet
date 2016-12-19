package logic

import lib.DrawingUtility
import lib.RandomUtility

class SplitterTarget(radius: Int, movingDuration: Int, z: Int, private val onScreenObject: MutableList<TargetObject>) : ShootableObject(radius, movingDuration, z, 5) {
    init {
        life = 5
    }

    override fun hit(player: PlayerStatus) {
        super.hit(player)
        if (isDestroyed)
            for (i in 0..RandomUtility.random(3, 6) - 1)
                onScreenObject.add(SmallTarget(radius / 2, movingDuration, z, x, y))
    }

    override fun render() = DrawingUtility.drawShootableObject(x, y, radius, "splitter", isPointerOver)
}

