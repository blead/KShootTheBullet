package logic

import lib.DrawingUtility

class SpecialGun(bulletQuantity: Int, private val maxBullet: Int, attack: Int) : Gun(attack) {
    var bulletQuantity = 0
        set(value) {
            field = if (value < 0) 0 else if (value > maxBullet) maxBullet else value
        }

    init {
        this.bulletQuantity = bulletQuantity
    }

    override fun canShoot() = bulletQuantity > 0

    override fun shoot() {
        super.shoot()
        bulletQuantity--
    }

    override fun render() {
        DrawingUtility.drawIconGun(bulletQuantity, maxBullet, false)
    }
}
