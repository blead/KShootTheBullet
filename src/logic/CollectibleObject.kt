package logic

import lib.AudioUtility

class CollectibleObject() {
    fun grab(player: PlayerStatus) {
        if (isDestroyed)
            return
        if (/* condition */) {
            AudioUtility.playSound("collect")
            collect(player)
            isDestroyed = true
            return
        }
    }
}
