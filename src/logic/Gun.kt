package logic

import lib.AudioUtility
import lib.DrawingUtility
import lib.IRenderableObject

class Gun() {
    open fun shoot() = AudioUtility.playSound("shoot")
}
