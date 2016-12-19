package logic

import lib.DrawingUtility
import lib.RandomUtility

class SplitterTarget() {
    fun render() = DrawingUtility.drawShootableObject(x, y, radius, "splitter", isPointerOver)
}

