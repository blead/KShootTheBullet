package logic

import lib.ConfigurableOption
import lib.DrawingUtility
import lib.RandomUtility

class SmallTarget(radius: Int, movingDuration: Int, z: Int) : ShootableObject(radius, movingDuration, z, 5) {
    constructor(radius: Int, movingDuration: Int, z: Int, startX: Int, startY: Int) : this(radius, movingDuration, z) {
        movingType = 0
        movingParameter = if (RandomUtility.random(0, 1) == 1)
            intArrayOf(startX, startY, if (RandomUtility.random(0, 1) == 0) -radius else ConfigurableOption.screenWidth + radius, RandomUtility.random(-radius, ConfigurableOption.screenHeight + radius))
        else
            intArrayOf(startX, startY, RandomUtility.random(-radius, ConfigurableOption.screenWidth + radius), if (RandomUtility.random(0, 1) == 0) -radius else ConfigurableOption.screenHeight + radius)
    }

    init {
        life = 2
    }

    override fun render() {
        DrawingUtility.drawShootableObject(x, y, radius, "small", isPointerOver)
    }
}
