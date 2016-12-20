package logic

import lib.DrawingUtility

class ItemSpecialGun(movingDuration: Int, z: Int) : CollectibleObject(50, movingDuration, z, 50) {
    override fun collect(player: PlayerStatus) {
        player.currentGun = SpecialGun(20, 20, 3)
    }

    override fun render() = DrawingUtility.drawItemGun(x, y, radius, "gun", isPointerOver)
}
