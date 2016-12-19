package logic

import lib.ConfigurableOption
import lib.DrawingUtility
import lib.RandomUtility

class SmallTarget() {
    constructor( /* fill code */ ) {
        movingType = 0
        movingParameter = if (RandomUtility.random(0, 1) == 1)
            intArrayOf(startX, startY, if (RandomUtility.random(0, 1) == 0) -radius else ConfigurableOption.screenWidth + radius, RandomUtility.random(-radius, ConfigurableOption.screenHeight + radius))
        else
            intArrayOf(startX, startY, RandomUtility.random(-radius, ConfigurableOption.screenWidth + radius), if (RandomUtility.random(0, 1) == 0) -radius else ConfigurableOption.screenHeight + radius)
    }

    fun render() {
        DrawingUtility.drawShootableObject(x, y, radius, "small", isPointerOver)
    }
}
