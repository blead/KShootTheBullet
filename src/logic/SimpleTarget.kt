package logic

import lib.DrawingUtility

class SimpleTarget() {
    fun render() = DrawingUtility.drawShootableObject(x, y, radius, "simple", isPointerOver)
}
