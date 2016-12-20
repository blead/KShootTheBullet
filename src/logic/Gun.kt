package logic

import lib.AudioUtility
import lib.DrawingUtility
import lib.IRenderableObject

open class Gun(val attack: Int) : IRenderableObject {
    open fun canShoot() = true

    open fun shoot() = AudioUtility.playSound("shoot")

    override fun isVisible() = false

    override fun getZ() = 0

    override fun render() = DrawingUtility.drawIconGun(0, 0, true)
}
