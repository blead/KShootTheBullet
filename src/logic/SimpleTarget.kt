package logic

import lib.DrawingUtility

class SimpleTarget(radius: Int, movingDuration: Int, z: Int) : ShootableObject(radius, movingDuration, z, 3) {
    init {
        life = 3
    }

    override fun render() = DrawingUtility.drawShootableObject(x, y, radius, "simple", isPointerOver)
}
