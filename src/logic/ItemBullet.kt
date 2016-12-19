package logic

import lib.DrawingUtility

class ItemBullet() {
    fun render() = DrawingUtility.drawItemBullet(x, y, radius, isPointerOver)
}
