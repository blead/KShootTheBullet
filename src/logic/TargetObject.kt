package logic

import lib.ConfigurableOption
import lib.IRenderableObject
import lib.MotionUtility
import lib.RandomUtility

class TargetObject() {
    protected var x: Int
    protected var y: Int
    var isDestroyed = false
        protected set
    protected var movingParameter = IntArray(8)
    protected var movingDurationCounter = 0
    protected var movingType: Int
    var isPointerOver = false

    init {
        /* randomly set moving parameters */
        val moveVertically = RandomUtility.random(0, 1) == 1
        if (moveVertically) {
            movingParameter[0] = RandomUtility.random(radius, ConfigurableOption.screenWidth - radius)
            movingParameter[1] = -radius
            movingParameter[2] = RandomUtility.random(radius, ConfigurableOption.screenWidth - radius)
            movingParameter[3] = ConfigurableOption.screenHeight + radius
        } else {
            movingParameter[0] = -radius
            movingParameter[1] = RandomUtility.random(radius, ConfigurableOption.screenHeight - radius)
            movingParameter[2] = ConfigurableOption.screenWidth + radius
            movingParameter[3] = RandomUtility.random(radius, ConfigurableOption.screenHeight - radius)
        }

        /* randomly flip endpoints */
        if (RandomUtility.random(0, 1) == 1) {
            val a = movingParameter[0]
            val b = movingParameter[1]
            movingParameter[0] = movingParameter[2]
            movingParameter[1] = movingParameter[3]
            movingParameter[2] = a
            movingParameter[3] = b
        }

        movingType = RandomUtility.random(0, 2)
        if (movingType == 1) {
            if (moveVertically) {
                movingParameter[4] = RandomUtility.random(radius, ConfigurableOption.screenWidth - radius)
                movingParameter[5] = (movingParameter[1] + movingParameter[3]) / 2
            } else {
                movingParameter[4] = (movingParameter[0] + movingParameter[2]) / 2
                movingParameter[5] = RandomUtility.random(radius, ConfigurableOption.screenHeight - radius)
            }
        } else if (movingType == 2) {
            movingParameter[4] = RandomUtility.random(radius, ConfigurableOption.screenWidth - radius)
            movingParameter[5] = RandomUtility.random(radius, ConfigurableOption.screenHeight - radius)
            movingParameter[6] = RandomUtility.random(radius, ConfigurableOption.screenWidth - radius)
            movingParameter[7] = RandomUtility.random(radius, ConfigurableOption.screenHeight - radius)
        }

        x = movingParameter[0]
        y = movingParameter[1]
    }

    fun contains(x: Int, y: Int) = Math.hypot((x - this.x).toDouble(), (y - this.y).toDouble()) <= radius + 6

    fun move() {
        if (isDestroyed)
            return
        if (movingDurationCounter > movingDuration) {
            isDestroyed = true
            return
        }

        val coordinates: IntArray
        when (movingType) {
            2 -> coordinates = MotionUtility.cubicCurveMotion(movingParameter[0], movingParameter[1], movingParameter[4],
                    movingParameter[5], movingParameter[6], movingParameter[7], movingParameter[2], movingParameter[3],
                    movingDuration, movingDurationCounter)
            1 -> coordinates = MotionUtility.curveMotion(movingParameter[0], movingParameter[1], movingParameter[4],
                    movingParameter[5], movingParameter[2], movingParameter[3], movingDuration, movingDurationCounter)
            else -> coordinates = MotionUtility.linearMotion(movingParameter[0], movingParameter[1], movingParameter[2],
                    movingParameter[3], movingDuration, movingDurationCounter)
        }

        x = coordinates[0]
        y = coordinates[1]
        movingDurationCounter++
    }
}
