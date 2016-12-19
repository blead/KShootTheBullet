package logic

import lib.DrawingUtility

class ItemBullet(movingDuration: Int, z: Int) : CollectibleObject(50, movingDuration, z, 30) {
    override fun render() = DrawingUtility.drawItemBullet(x, y, radius, isPointerOver)

    override fun collect(player: PlayerStatus) {
        if (player.currentGun is SpecialGun)
            (player.currentGun as SpecialGun).bulletQuantity = Int.MAX_VALUE
    }
}
